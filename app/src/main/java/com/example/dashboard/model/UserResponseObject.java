package com.example.dashboard.model;

import com.google.gson.annotations.SerializedName;

public class UserResponseObject {
    @SerializedName("error")
    public Boolean error;

    @SerializedName("message")
    public String message;

    @SerializedName("user")
    public User user;

    public String toString() {
        String str = "error: " + error + ", mess: " + message + ", name: " + user.name + ", address street: " + user.address.addressText;
        return str;
    }
}
