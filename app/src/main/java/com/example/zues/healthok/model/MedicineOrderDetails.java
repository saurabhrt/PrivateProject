package com.example.zues.healthok.model;

public class MedicineOrderDetails {
    private int MedicineOrderDetailsId;
    private int MedicineOrderId;
    private String MedicineName;
    private String Dosage;
    private int Quantity;
    private float Price;

    public MedicineOrderDetails() {

    }

    public MedicineOrderDetails(int medicineorderdetailsid, int medicineorderid, String medicinename, String dosage, int quantity, float price) {
        super();
        this.MedicineOrderDetailsId = medicineorderdetailsid;
        this.MedicineOrderId = medicineorderid;
        this.MedicineName = medicinename;
        this.Dosage = dosage;
        this.Quantity = quantity;
        this.Price = price;
    }

    public int getMedicineOrderDetailsId() {
        return MedicineOrderDetailsId;
    }

    public void setMedicineOrderDetailsId(int medicineOrderDetailsId) {
        MedicineOrderDetailsId = medicineOrderDetailsId;
    }

    public int getMedicineOrderId() {
        return MedicineOrderId;
    }

    public void setMedicineOrderId(int medicineOrderId) {
        MedicineOrderId = medicineOrderId;
    }

    public String getMedicineName() {
        return MedicineName;
    }

    public void setMedicineName(String medicineName) {
        MedicineName = medicineName;
    }

    public String getDosage() {
        return Dosage;
    }

    public void setDosage(String dosage) {
        Dosage = dosage;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float price) {
        Price = price;
    }

}
