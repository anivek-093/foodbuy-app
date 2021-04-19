package com.example.dashboard;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dashboard.adapter.RecyclerViewCustomAdapter;
import com.example.dashboard.model.Product;
import com.example.dashboard.model.ProductsResponse;
import com.example.dashboard.network.DataService;
import com.example.dashboard.network.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsActivity extends AppCompatActivity {
    private RecyclerViewCustomAdapter recyclerViewCustomAdapter;
    private RecyclerView recyclerView;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        progressDialog = new ProgressDialog(ProductsActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        getAllProducts();
    }

    private void getAllProducts() {
        DataService service = RetrofitClientInstance.getRetrofitInstance()
                .create(DataService.class);

        Call<ProductsResponse> call = service.getAllProducts();

        call.enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
                progressDialog.dismiss();
                Toast.makeText(ProductsActivity.this, response.body().error.toString(), Toast.LENGTH_LONG);
                if(response.body().error) {
                    Toast.makeText(ProductsActivity.this, response.body().message, Toast.LENGTH_LONG);
                    Log.d("ProductsActivity.java", "onResponse: " + response.body().message);
                }
                else {

                }
            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ProductsActivity.this, t.getMessage(), Toast.LENGTH_LONG);
                Log.d("ProductsActivity.java", "onFailure: " + t.getMessage());
            }
        });
    }

//    private void generateProductsList(List<Product> productList) {
//        recyclerView = findViewById(R.id.products_recycler_view);
//
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ProductsActivity.this);
//        recyclerView.setLayoutManager(layoutManager);
//
//        recyclerViewCustomAdapter = new RecyclerViewCustomAdapter(this, productList);
//        recyclerView.setAdapter(recyclerViewCustomAdapter);
//    }
}
