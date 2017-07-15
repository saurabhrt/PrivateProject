package com.example.zues.healthok.model;

import java.util.ArrayList;
import java.util.Date;

public class Hospital {

    private int hospitalId;
    private String name;
    private boolean hasER;
    private String facilities;
    private int opdFees;
    private int beds;
    private String addressLine1;
    private String addressLine2;
    //private String addressLine3;
    private int cityId;
    private String pincode;
    private Date registrationDate;
    private String website;
    //	private String phonenumber;
    private boolean hasRadiology;
    private boolean hasDiagnostics;
    private boolean hasAmbulance;
    private String addmissionProcess;


    private ArrayList<HospitalPhoneNumber> hospitalPhoneNumbers;
    private ArrayList<DoctorHospitalAffiliation> doctorHospitalAffiliation;

    public Hospital() {
    }

    public int getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(int hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHasER() {
        return hasER;
    }

    public void setHasER(boolean hasER) {
        this.hasER = hasER;
    }

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    public int getOpdFees() {
        return opdFees;
    }

    public void setOpdFees(int opdFees) {
        this.opdFees = opdFees;
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public boolean getHasRadiology() {
        return hasRadiology;
    }

    public void setHasRadiology(boolean hasRadiology) {
        this.hasRadiology = hasRadiology;
    }

    public boolean getHasDiagnostics() {
        return hasDiagnostics;
    }

    public void setHasDiagnostics(boolean hasDiagnostics) {
        this.hasDiagnostics = hasDiagnostics;
    }

    public boolean getHasAmbulance() {
        return hasAmbulance;
    }

    public void setHasAmbulance(boolean hasAmbulance) {
        this.hasAmbulance = hasAmbulance;
    }

    public String getAddmissionProcess() {
        return addmissionProcess;
    }

    public void setAddmissionProcess(String addmissionProcess) {
        this.addmissionProcess = addmissionProcess;
    }

    public ArrayList<HospitalPhoneNumber> getHospitalPhoneNumbers() {
        return hospitalPhoneNumbers;
    }

    public void setHospitalPhoneNumbers(ArrayList<HospitalPhoneNumber> hospitalPhoneNumbers) {
        this.hospitalPhoneNumbers = hospitalPhoneNumbers;
    }

    public ArrayList<DoctorHospitalAffiliation> getDoctorHospitalAffiliation() {
        return doctorHospitalAffiliation;
    }

    public void setDoctorHospitalAffiliation(ArrayList<DoctorHospitalAffiliation> doctorHospitalAffiliation) {
        this.doctorHospitalAffiliation = doctorHospitalAffiliation;
    }


}
