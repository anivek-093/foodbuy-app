package com.example.dashboard.model.order;

import com.google.gson.annotations.SerializedName;

public class OrderDispatchDetails {
    @SerializedName("isDispatched")
    public Boolean isDispatched;

    @SerializedName("dispatchTime")
    public Long dispatchTimeInMillis;

    public OrderDispatchDetails(Boolean isDispatched, Long dispatchTimeInMillis) {
        this.isDispatched = isDispatched;
        this.dispatchTimeInMillis = dispatchTimeInMillis;
    }
}
