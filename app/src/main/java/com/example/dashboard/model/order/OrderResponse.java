package com.example.dashboard.model.order;

import com.google.gson.annotations.SerializedName;

public class OrderResponse {
    @SerializedName("error")
    public Boolean error;

    @SerializedName("message")
    public String message;

    @SerializedName("order")
    public OrderDetails order;

    public OrderResponse(Boolean error, String message, OrderDetails order) {
        this.error = error;
        this.message = message;
        this.order = order;
    }
}
