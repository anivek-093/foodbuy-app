package com.example.dashboard.model.feedback;

import com.google.gson.annotations.SerializedName;

public class FeedbackPostResponse {
    @SerializedName("error")
    public Boolean error;

    @SerializedName("message")
    public String message;

    public FeedbackPostResponse(Boolean error, String message) {
        this.error = error;
        this.message = message;
    }
}
