package com.example.dashboard.model;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Ignore;

import com.google.gson.annotations.SerializedName;

public class User {
    @ColumnInfo(name = "sellerId")
    @SerializedName("_id")
    public String _id;

    @ColumnInfo(name = "sellerEmail")
    @SerializedName("email")
    public String email;

    @ColumnInfo(name = "sellerName")
    @SerializedName("name")
    public String name;

    @ColumnInfo(name = "sellerUserType")
    @SerializedName("userType")
    public String userType;

    @ColumnInfo(name = "sellerStoreName")
    @SerializedName("storeName")
    public String storeName;

    @ColumnInfo(name = "sellerPhone")
    @SerializedName("phone")
    public String phone;

    @Embedded
    @SerializedName("address")
    public Address address;



    public User(String _id,
                String email,
                String name,
                String userType,
                String storeName,
                String phone,
                Address address) {
        this._id = _id;
        this.email = email;
        this.name = name;
        this.userType = userType;
        this.storeName = storeName;
        this.phone = phone;
        this.address = address;
    }

    @Ignore
    public User(String email,
                String name,
                String userType,
                String storeName,
                String phone,
                String street,
                String pincode,
                String city,
                String state) {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.storeName = storeName;
        this.userType = userType;
        this.address = new Address(street, pincode, city, state);
    }
}
