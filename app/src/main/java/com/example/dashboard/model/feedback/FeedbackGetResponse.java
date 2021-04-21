package com.example.dashboard.model.feedback;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FeedbackGetResponse {
    @SerializedName("error")
    public Boolean error;

    @SerializedName("message")
    public String message;

    @SerializedName("feedbacks")
    public List<Feedback> feedbacks;

    public FeedbackGetResponse(Boolean error, String message, List<Feedback> feedbacks) {
        this.error = error;
        this.message = message;
        this.feedbacks = feedbacks;
    }
}
