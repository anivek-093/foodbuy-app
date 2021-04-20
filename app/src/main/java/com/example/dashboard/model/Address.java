package com.example.dashboard.model;

import androidx.room.ColumnInfo;

import com.google.gson.annotations.SerializedName;

public class Address {
    @ColumnInfo(name = "addressText")
    @SerializedName("addressText")
    public String addressText;

    public Address(String addressText) {
        this.addressText = addressText;
    }
}