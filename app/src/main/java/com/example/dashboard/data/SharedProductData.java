package com.example.dashboard.data;

import com.example.dashboard.model.Product;

public class SharedProductData {
    private static SharedProductData instance;

    private Product sharedProduct;

    private SharedProductData() {
        sharedProduct = null;
    }

    public static SharedProductData getInstance() {
        if(instance == null) {
            instance = new SharedProductData();
        }
        return instance;
    }

    public void saveProduct(Product product) {
        sharedProduct = product;
    }

    public Product getProduct() {
        return sharedProduct;
    }
}
