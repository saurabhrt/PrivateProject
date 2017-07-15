package com.example.zues.healthok.model;

public class OpenOrder extends Order {

    boolean isFeedbackComplete;
    boolean isPostCareComplete;

    public boolean getIsFeedbackComplete() {
        return isFeedbackComplete;
    }

    public void setIsFeedbackComplete(boolean isFeedbackComplete) {
        this.isFeedbackComplete = isFeedbackComplete;
    }

    public boolean getIsPostCareComplete() {
        return isPostCareComplete;
    }

    public void setPostCareComplete(boolean isPostCareComplete) {
        this.isPostCareComplete = isPostCareComplete;
    }


}
