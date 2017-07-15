package com.example.zues.healthok.model;

public class Ambulance extends OrderBase {

    private int ambulanceOrderId;
    //	private int OrderId;
    private String description;

    public int getAmbulanceOrderId() {
        return ambulanceOrderId;
    }

    public void setAmbulanceOrderId(int ambulanceOrderId) {
        this.ambulanceOrderId = ambulanceOrderId;
    }

    /*
        public int getOrderId() {
            return OrderId;
        }

        public void setOrderId(int orderId) {
            OrderId = orderId;
        }
    */
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        this.setOrderDescription(description);
    }
}
