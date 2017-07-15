package com.example.zues.healthok.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Order extends OrderBase {
    private int totalCost;
    private int discount;
    private int cashbackBonusApplied;
    private int netAmount;
    //	private Date orderFulfillDate;
    private LabOrder labOrder;
    private MedicineOrder medicineOrder;
    private DoctorAppointment doctorAppointment;
    private Ambulance ambulance;
    private NurseOrder nurseOrder;
    // similarly create model for DoctorAppointment, ambulance order and nurse
    // order and add those model classes here

    boolean isFeedbackComplete;
    boolean isPostCareComplete;
    boolean isDeleted;

    public boolean getIsFeedbackComplete() {
        return isFeedbackComplete;
    }

    public void setIsFeedbackComplete(boolean isFeedbackComplete) {
        this.isFeedbackComplete = isFeedbackComplete;
    }

    public boolean getIsPostCareComplete() {
        return isPostCareComplete;
    }

    public void setIsPostCareComplete(boolean isPostCareComplete) {
        this.isPostCareComplete = isPostCareComplete;
    }


    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }


    public Order() {

    }

    public static Order fromJSON(String jsonString) {
        Order order = null;
        Gson gson = new GsonBuilder().setDateFormat(
                "yyyy-MM-dd'T'HH:mm:ssZ").create();
        try {
            order = gson.fromJson(jsonString, Order.class);
            return order;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return order;

    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getCashbackBonusApplied() {
        return cashbackBonusApplied;
    }

    public void setCashbackBonusApplied(int cashbackBonusApplied) {
        this.cashbackBonusApplied = cashbackBonusApplied;
    }

    public int getNetAmount() {
        return totalCost - (discount + cashbackBonusApplied);
    }

    public void setNetAmount(int netAmount) {
        this.netAmount = netAmount;
    }

    /*
        public void setOrderFulfillDate(Date orderFulfillDate) {
            this.orderFulfillDate = orderFulfillDate;
        }

        public Date getOrderFulfillDate() {

            return orderFulfillDate;
        }
    */
    public LabOrder getLabOrder() {
        return labOrder;
    }

    public void setLabOrder(LabOrder labOrder) {
        this.labOrder = labOrder;
    }

    public MedicineOrder getMedicineOrder() {
        return medicineOrder;
    }

    public void setMedicineOrder(MedicineOrder medicineOrder) {
        this.medicineOrder = medicineOrder;
    }

    public DoctorAppointment getDoctorAppointment() {
        return doctorAppointment;
    }

    public void setDoctorAppointment(DoctorAppointment doctorAppointment) {
        this.doctorAppointment = doctorAppointment;
    }

    public Ambulance getAmbulance() {
        return ambulance;
    }

    public void setAmbulance(Ambulance ambulance) {
        this.ambulance = ambulance;
    }

    public NurseOrder getNurseOrder() {
        return nurseOrder;
    }

    public void setNurseOrder(NurseOrder nurseOrder) {
        this.nurseOrder = nurseOrder;
    }

}
