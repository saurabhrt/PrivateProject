package com.example.zues.healthok.model;

/**
 * Created by Abhay-Jaiswal on 6/16/2016.
 */
public class DoctorHospitalAffiliation {


    private int doctorHospitalAffiliationId;
    private int doctorId;
    private int hospitalId;
    private String additionalDetails;
    private String hospitalName;

    public int getDoctorHospitalAffiliationId() {
        return doctorHospitalAffiliationId;
    }

    public void setDoctorHospitalAffiliationId(int doctorHospitalAffiliationId) {
        this.doctorHospitalAffiliationId = doctorHospitalAffiliationId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(int hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getAdditionalDetails() {
        return additionalDetails;
    }

    public void setAdditionalDetails(String additionalDetails) {
        this.additionalDetails = additionalDetails;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

}

