package com.example.dashboard.fragment;

import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.dashboard.model.Product;
import com.example.dashboard.network.DataService;
import com.example.dashboard.network.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddProductFragment extends Fragment {


    private void postOneProduct(Product product) {


        DataService service = RetrofitClientInstance.getRetrofitInstance()
                .create(DataService.class);

        Call<Product> call = service.postOneProduct(product);

        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {

                if(response.body().error){
                    Log.e("AddProductsFragment", "onResponse: "  + response.body().message);
                    Toast.makeText(getContext(), "Error adding product", Toast.LENGTH_SHORT).show();
                }
                else {


                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {

            }
        });
    }
}
