package com.example.zues.healthok.model;

public class HospitalPhoneNumber {

    private int hospitalPhoneNumberId;
    private PhoneNumberType phoneNumberTypeId;
    private int hospitalId;
    private String phoneNumber;
    private String contact;
    private String comments;
    private int phoneNumberType;

    public int getHospitalPhoneNumberId() {
        return hospitalPhoneNumberId;
    }

    public void setHospitalPhoneNumberId(int hospitalPhoneNumberId) {
        this.hospitalPhoneNumberId = hospitalPhoneNumberId;
    }

    public PhoneNumberType getPhoneNumberTypeId() {
        return phoneNumberTypeId;
    }

    public void setPhoneNumberTypeId(PhoneNumberType phoneNumberTypeId) {
        this.phoneNumberTypeId = phoneNumberTypeId;
    }

    public int getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(int hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getPhoneNumberType() {
        return phoneNumberType;
    }

    public void setPhoneNumberType(int phoneNumberType) {
        this.phoneNumberType = phoneNumberType;
    }


}
