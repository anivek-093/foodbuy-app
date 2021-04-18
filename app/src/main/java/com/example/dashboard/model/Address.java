package com.example.dashboard.model;

import androidx.room.ColumnInfo;

import com.google.gson.annotations.SerializedName;

public class Address {
    @ColumnInfo(name = "sellerStreet")
    @SerializedName("street")
    public String street;

    @ColumnInfo(name = "sellerPincode")
    @SerializedName("pincode")
    public String pincode;

    @ColumnInfo(name = "sellerCity")
    @SerializedName("city")
    public String city;

    @ColumnInfo(name = "sellerState")
    @SerializedName("state")
    public String state;

    public Address(
            String street,
            String pincode,
            String city,
            String state) {
        this.street = street;
        this.pincode = pincode;
        this.city = city;
        this.state = state;
    }
}