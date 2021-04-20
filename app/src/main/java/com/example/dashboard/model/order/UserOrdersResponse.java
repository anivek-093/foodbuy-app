package com.example.dashboard.model.order;

import com.example.dashboard.model.Product;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserOrdersResponse {
    @SerializedName("error")
    public Boolean error;

    @SerializedName("message")
    public String message;

    @SerializedName("orders")
    public List<OrderDetails> orders;

    @SerializedName("products")
    public List<Product> products;

    public UserOrdersResponse(Boolean error, String message, List<OrderDetails> orders, List<Product> products) {
        this.error = error;
        this.message = message;
        this.orders = orders;
        this.products = products;
    }
}
