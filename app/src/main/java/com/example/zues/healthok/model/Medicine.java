package com.example.zues.healthok.model;

public class Medicine {
    private int medicineId;
    private String medicineName;
    private String composition;
    private String dosage;
    private String company;
    private int quantity;

    public Medicine() {

    }

    public Medicine(String medicineName, int quantity, float price) {
        super();
        this.medicineName = medicineName;
        this.quantity = quantity;
        this.price = price;
    }

    public Medicine(int medicineId, String medicineName, int quantity,
                    float price) {
        super();
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.quantity = quantity;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    private float price;

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }


}
