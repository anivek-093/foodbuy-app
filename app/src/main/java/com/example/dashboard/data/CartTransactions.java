package com.example.dashboard.data;

import android.content.Context;

import com.example.dashboard.executer.AppExecutors;
import com.example.dashboard.model.Product;

public class CartTransactions {
    ProductDatabase productDatabase;
    Context context;

    public CartTransactions(Context context) {
        this.context = context.getApplicationContext();
        productDatabase = ProductDatabase.getInstance(this.context);
    }

    public void addProductToCart(Product product) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                productDatabase.productDao().insertProduct(product);
            }
        });
    }

    public void deleteProductFromCart(Product product) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                productDatabase.productDao().deleteProduct(product);
            }
        });
    }

    public void clearCart() {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                productDatabase.productDao().deleteAllProducts();
            }
        });
    }

    public void updateProductInCart(Product product) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                productDatabase.productDao().updateProduct(product);
            }
        });
    }
}
