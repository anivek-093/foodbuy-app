package com.example.dashboard.model.order;

import com.google.gson.annotations.SerializedName;

public class OrderDetails {
    @SerializedName("buyerId")
    public String buyerId;

    @SerializedName("productId")
    public String productId;

    @SerializedName("orderedQuantity")
    public Float orderedQuantity;

    @SerializedName("time")
    public Long orderPlacedTimeInMillis;

    @SerializedName("cost")
    public Float cost;

    @SerializedName("dispatch")
    public OrderDispatchDetails dispatch;

    @SerializedName("delivery")
    public OrderDispatchDetails delivery;

    public OrderDetails(String buyerId,
                        String productId,
                        Float orderedQuantity,
                        Long orderPlacedTimeInMillis,
                        Float cost,
                        OrderDispatchDetails dispatch,
                        OrderDispatchDetails delivery) {
        this.buyerId = buyerId;
        this.productId = productId;
        this.orderedQuantity = orderedQuantity;
        this.orderPlacedTimeInMillis = orderPlacedTimeInMillis;
        this.cost = cost;
        this.dispatch = dispatch;
        this.delivery = delivery;
    }
}
