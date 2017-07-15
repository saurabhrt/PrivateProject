package com.example.zues.healthok.model;

public class LabOrderDetail {

    private int laborderdetailId;
    private String testname;
    private float price;
    private int laborderId;

    public int getLaborderdetailId() {
        return laborderdetailId;
    }

    public void setLaborderdetailId(int laborderdetailId) {
        this.laborderdetailId = laborderdetailId;
    }

    public String getTestname() {
        return testname;
    }

    public void setTestname(String testname) {
        this.testname = testname;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getLaborderId() {
        return laborderId;
    }

    public void setLaborderId(int laborderId) {
        this.laborderId = laborderId;
    }


}
