package com.example.zues.healthok.model;

public class Buffer {

    private int bufferId;
    private int quantity;
    private int UserId;
    private int medicineId;

    public Buffer() {

    }

    public Buffer(int bufferId, int quantity, int userId, int medicineId) {
        super();
        this.bufferId = bufferId;
        this.quantity = quantity;
        UserId = userId;
        this.medicineId = medicineId;
    }

    public int getBufferId() {
        return bufferId;
    }

    public void setBufferId(int bufferId) {
        this.bufferId = bufferId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

}
