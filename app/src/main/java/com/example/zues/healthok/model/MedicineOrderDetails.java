package com.example.zues.healthok.model;

public class MedicineOrderDetails {
    private int medicineOrderDetailsId;
    private int medicineOrderId;
    private String medicineName;
    private String dosage;
    private int quantity;
    private float price;

    public MedicineOrderDetails() {

    }

    public MedicineOrderDetails(int medicineorderdetailsid, int medicineorderid, String medicinename, String dosage, int quantity, float price) {
        super();
        this.medicineOrderDetailsId = medicineorderdetailsid;
        this.medicineOrderId = medicineorderid;
        this.medicineName = medicinename;
        this.dosage = dosage;
        this.quantity = quantity;
        this.price = price;
    }

    public int getMedicineOrderDetailsId() {
        return medicineOrderDetailsId;
    }

    public void setMedicineOrderDetailsId(int medicineOrderDetailsId) {
        this.medicineOrderDetailsId = medicineOrderDetailsId;
    }

    public int getMedicineOrderId() {
        return medicineOrderId;
    }

    public void setMedicineOrderId(int medicineOrderId) {
        this.medicineOrderId = medicineOrderId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}
