package com.example.zues.healthok.model;

import java.util.ArrayList;

public class MedicineOrder extends OrderBase {

    private int medicineOrderId;
    //private int orderId;
    private int id;

    public int getId() {
        return medicineOrderId;
    }

    public void setId(int id) {
        this.id = id;
        this.medicineOrderId = id;
    }

    private int prescriptionImageId;
    private String description;
    private ArrayList<MedicineOrderDetails> medicineDetail;

    public ArrayList<MedicineOrderDetails> getMedicineDetail() {
        return medicineDetail;
    }

    public void setMedicineDetail(ArrayList<MedicineOrderDetails> medicineDetail) {
        this.medicineDetail = medicineDetail;
    }

    public MedicineOrder() {
    }

    public MedicineOrder(int medicineOrderId, int orderId, int prescriptionImageId, String description) {
        super();
        this.medicineOrderId = medicineOrderId;
        this.description = description;
        this.prescriptionImageId = prescriptionImageId;
//	this.orderId=orderId;
    }

    public int getMedicineOrderId() {
        return medicineOrderId;
    }

    public void setMedicineOrderId(int medicineOrderId) {
        this.medicineOrderId = medicineOrderId;
    }

    /*
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    */
    public int getPrescriptionImageId() {
        return prescriptionImageId;
    }

    public void setPrescriptionImageId(int prescriptionImageId) {
        this.prescriptionImageId = prescriptionImageId;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        this.setOrderDescription(description);
    }


}
