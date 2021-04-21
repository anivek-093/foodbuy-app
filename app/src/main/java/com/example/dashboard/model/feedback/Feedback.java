package com.example.dashboard.model.feedback;


import com.google.gson.annotations.SerializedName;

/*
    request body:
    {
        submitterId: String,
        receiverId: String,
        productId: String,
        rating: Number,
        text: String
    }
*/
public class Feedback {
    @SerializedName("submitterId")
    public String submitterId;

    @SerializedName("receiverId")
    public String receiverId;

    @SerializedName("productId")
    public String productId;

    @SerializedName("rating")
    public Integer rating;

    @SerializedName("text")
    public String text;

    public Feedback(String submitterId, String receiverId, String productId, Integer rating, String text) {
        this.submitterId = submitterId;
        this.receiverId = receiverId;
        this.productId = productId;
        this.rating = rating;
        this.text = text;
    }
}
