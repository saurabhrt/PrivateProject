package com.example.zues.healthok.model;

public class NurseOrder extends OrderBase {

    private int nurseOrderId;
    //	private int orderId;
    private String description;

    public int getNurseOrderId() {
        return nurseOrderId;
    }

    public void setNurseOrderId(int nurseOrderId) {
        this.nurseOrderId = nurseOrderId;
    }

    /*	public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
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
