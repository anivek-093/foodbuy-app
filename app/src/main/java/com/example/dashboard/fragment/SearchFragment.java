package com.example.dashboard.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dashboard.R;
import com.example.dashboard.adapter.RecyclerViewCustomAdapter;
import com.example.dashboard.data.Preferences;
import com.example.dashboard.model.Product;
import com.example.dashboard.model.ProductsResponse;
import com.example.dashboard.model.User;
import com.example.dashboard.network.DataService;
import com.example.dashboard.network.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {
    private RecyclerView recyclerView;
    private TextView searchButton;
    private EditText searchText;

    private RecyclerViewCustomAdapter recyclerViewCustomAdapter;
    private ProgressDialog searchProgressDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchButton = (TextView)getView().findViewById(R.id.done_text_fragment_search);
        searchText = (EditText)getView().findViewById(R.id.fragment_search_search_edit_text);

        searchProgressDialog = new ProgressDialog(getContext());
        searchProgressDialog.setMessage("Finding products...");

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getProductsWithSearchText(searchText.getText().toString());
            }
        });
    }

    public void getProductsWithSearchText(String searchText) {
        searchProgressDialog.show();

        Preferences preferences = Preferences.getPreferences(getActivity().getApplicationContext());
        User currentUser = preferences.getCurrentUser();

        DataService service = RetrofitClientInstance.getRetrofitInstance()
                .create(DataService.class);

        Call<ProductsResponse> call = service.getProductByName(searchText, currentUser.userType); //currentUser.userType

        call.enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
                searchProgressDialog.dismiss();
                if(response.body().error) {
                    Log.e("SearchFragment.java", "onResponse: " + response.message());
                    Toast.makeText(getContext(), "Error finding products.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Log.e("SearchFragment.java", Integer.toString(response.body().productList.size()));
                    generateProductList(response.body().productList);
                }
            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {
                searchProgressDialog.dismiss();
                Log.e("SearchFragment.java", "onFailure: " + t.getMessage());
                Toast.makeText(getContext(), "Some error occurred.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void generateProductList(List<Product> productList) {
        recyclerView = (RecyclerView)getView().findViewById(R.id.fragment_search_recycler_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        recyclerViewCustomAdapter = new RecyclerViewCustomAdapter(getContext(), productList);
        recyclerView.setAdapter(recyclerViewCustomAdapter);
    }
}
