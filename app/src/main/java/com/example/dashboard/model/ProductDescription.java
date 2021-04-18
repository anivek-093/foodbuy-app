package com.example.dashboard.model;

import androidx.room.ColumnInfo;

import com.google.gson.annotations.SerializedName;

public class ProductDescription {
    @ColumnInfo(name = "type")
    @SerializedName("type")
    public String type;

    @ColumnInfo(name = "subtype")
    @SerializedName("subtype")
    public String subtype;

    @ColumnInfo(name = "text")
    @SerializedName("text")
    public String text;

    public ProductDescription(String type, String subtype, String text) {
        this.type = type;
        this.subtype = subtype;
        this.text = text;
    }
}
