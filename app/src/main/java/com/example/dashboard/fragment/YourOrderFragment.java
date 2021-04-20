package com.example.dashboard.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dashboard.R;
import com.example.dashboard.adapter.RecyclerViewOrdersAdapter;
import com.example.dashboard.data.Preferences;
import com.example.dashboard.model.Product;
import com.example.dashboard.model.User;
import com.example.dashboard.model.order.OrderDetails;
import com.example.dashboard.model.order.OrderResponse;
import com.example.dashboard.model.order.UserOrdersResponse;
import com.example.dashboard.network.DataService;
import com.example.dashboard.network.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YourOrderFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerViewOrdersAdapter recyclerViewOrdersAdapter;

    private ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_your_orders, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Getting your order.");

        getOrdersForCurrentUser();
    }

    private void getOrdersForCurrentUser() {
        progressDialog.show();

        Preferences preferences = Preferences.getPreferences(getActivity().getApplicationContext());
        User currentUser = preferences.getCurrentUser();

        DataService service = RetrofitClientInstance.getRetrofitInstance()
                .create(DataService.class);

        Call<UserOrdersResponse> call = service.getOrdersByUserId(currentUser._id);
        call.enqueue(new Callback<UserOrdersResponse>() {
            @Override
            public void onResponse(Call<UserOrdersResponse> call, Response<UserOrdersResponse> response) {
                progressDialog.dismiss();
                if(response.body().error) {
                    Toast.makeText(getContext(), "Error getting orders.", Toast.LENGTH_SHORT).show();
                    Log.e("YourOrdersFragment", "onResponse: " + response.body().message);
                }
                else {
                    if(response.body().orders != null)
                        generateOrderList(response.body().orders, response.body().products);
                }
            }

            @Override
            public void onFailure(Call<UserOrdersResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), "Error getting orders.", Toast.LENGTH_SHORT).show();
                Log.e("YourOrdersFragment", "onFailure: " + t.getMessage());
            }
        });
    }

    private void generateOrderList(List<OrderDetails> orderList, List<Product> productList) {
        recyclerView = getView().findViewById(R.id.fragment_your_orders_recycler_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        recyclerViewOrdersAdapter = new RecyclerViewOrdersAdapter(getActivity(), orderList, productList);
        recyclerView.setAdapter(recyclerViewOrdersAdapter);
    }
}
