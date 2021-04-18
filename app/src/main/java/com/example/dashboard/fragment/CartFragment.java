package com.example.dashboard.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dashboard.ProductsActivity;
import com.example.dashboard.R;
import com.example.dashboard.adapter.RecyclerViewCartProductsAdapter;
import com.example.dashboard.adapter.RecyclerViewCustomAdapter;
import com.example.dashboard.cart.CartViewModel;
import com.example.dashboard.data.ProductDatabase;
import com.example.dashboard.model.Product;
import com.example.dashboard.utility.MathUtility;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class CartFragment extends Fragment {
    private ProductDatabase productDatabase;

    private RecyclerView recyclerView;
    private RecyclerViewCartProductsAdapter recyclerViewCartProductsAdapter;

    private LinearLayout cartTotalCostLL;
    private TextView totalCost;
    private MaterialButton placeOrderButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cart,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cartTotalCostLL = view.findViewById(R.id.fragment_card_linear_layout);
        totalCost = view.findViewById(R.id.fragment_cart_total_text_view);
        placeOrderButton = view.findViewById(R.id.fragment_cart_place_order_button);

        productDatabase = ProductDatabase.getInstance(getActivity());
        productDatabase.productDao().getProductList().observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> productList) {
                if(productList.size() == 0) {
                    cartTotalCostLL.setVisibility(View.GONE);
                }
                else{
                    cartTotalCostLL.setVisibility(View.VISIBLE);
                }
                generateProductsList(view, productList);
            }
        });
        productDatabase.productDao().getTotalCost().observe(getViewLifecycleOwner(), new Observer<Float>() {
            @Override
            public void onChanged(Float aFloat) {
                totalCost.setText("Total: " + MathUtility.truncateFloatToTwoDecimalPlaces(aFloat));
            }
        });
    }

    private void generateProductsList(@NonNull View view, List<Product> productList) {
        recyclerView = view.findViewById(R.id.fragment_cart_recyclerview);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        recyclerViewCartProductsAdapter = new RecyclerViewCartProductsAdapter(getContext(), productList);
        recyclerView.setAdapter(recyclerViewCartProductsAdapter);
    }
}
