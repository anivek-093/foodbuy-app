package com.example.dashboard.model.order;

import com.google.gson.annotations.SerializedName;

public class OrderRequest {
    @SerializedName("buyerId")
    public String buyerId;

    @SerializedName("productId")
    public String productId;

    @SerializedName("productQuantity")
    public Float productQuantity;

    public OrderRequest(String buyerId, String productId, Float productQuantity) {
        this.buyerId = buyerId;
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
