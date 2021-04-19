package com.example.dashboard.model.order;

import com.google.gson.annotations.SerializedName;

public class OrderDeliveryDetails {
    @SerializedName("isDelivered")
    public Boolean isDelivered;

    @SerializedName("deliveryTime")
    public Long deliveryTimeInMillis;

    public OrderDeliveryDetails(Boolean isDelivered, Long deliveryTimeInMillis){
        this.isDelivered = isDelivered;
        this.deliveryTimeInMillis = deliveryTimeInMillis;
    }
}
