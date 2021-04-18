package com.example.dashboard.cart;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.dashboard.data.ProductDatabase;
import com.example.dashboard.executer.AppExecutors;
import com.example.dashboard.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CartViewModel extends AndroidViewModel {
    private ProductDatabase productDatabase;

    private MutableLiveData<Float> currentCost;
    private MutableLiveData<List<Product>> cartProductList;

    public CartViewModel(@NonNull Application application) {
        super(application);

    }

    public void addProduct(Product product, Float quantity) {

    }

}
