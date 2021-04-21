package com.example.dashboard.model;

import androidx.room.ColumnInfo;

import com.google.gson.annotations.SerializedName;

public class Address {
    @ColumnInfo(name = "addressText")
    @SerializedName("addressText")
    public String addressText;

    @ColumnInfo(name = "longitude")
    @SerializedName("longitude")
    public Double longitude;

    @ColumnInfo(name = "latitude")
    @SerializedName("latitude")
    public Double latitude;

    public Address(String addressText, Double longitude, Double latitude) {
        this.addressText = addressText;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}