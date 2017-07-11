package com.example.zues.healthok.model;

/**
 * Created by Abhay-Jaiswal on 6/16/2016.
 */

import java.util.ArrayList;
import java.util.Date;

// TODO Define class for Doctor Phone Numbers and Also for Doctor Hospital Association
// Add ArrayList<DOctorPhoneNumber> and ArrayList<DoctorHospitalAssociation> to this class and populate from Dal whenever you query doctor data.

public class Doctor {
    private int doctorId;
    private int doctorImageid;
    private int imageTypeId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String emailId;
    private int specialityId;
    private String degree;
    private Date doctorRegistrationDate;
    private String clinicTiming;
    private String offDay;
    private int emergencyFees;
    private int fees;
    private boolean isPharmacy;
    private boolean isProvideHomecare;
    private String isBelongToAnyHospital;
    private boolean inPanel;
    private boolean appointmnet;
    private boolean virtualReceptionist;
    private boolean postcare;
    private int yearofExperience;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private int cityId;
    private String pincode;
    private String Speciality;
    private boolean provideEmergencyCare;
    private boolean isTaleMedicineEnabled;
    private String hasOwnHospital;
    private String webSite;
    private String specialization;
    private boolean isProvideHomeConsultationFees;
    private boolean IsDiagnostics;
    private boolean IsProvideAnsweringService;
    private boolean IsProvidePostCallFollowup;
    private boolean Health_panel;
    private ArrayList<DoctorPhoneNumber> doctorPhoneNumbers;
    private ArrayList<DoctorHospitalAffiliation> doctorHospitalAffiliation;

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getDoctorImageid() {
        return doctorImageid;
    }

    public void setDoctorImageid(int doctorImageid) {
        this.doctorImageid = doctorImageid;
    }

    public int getImageTypeId() {
        return imageTypeId;
    }

    public void setImageTypeId(int imageTypeId) {
        this.imageTypeId = imageTypeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public int getSpecialityId() {
        return specialityId;
    }

    public void setSpecialityId(int specialityId) {
        this.specialityId = specialityId;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public Date getDoctorRegistrationDate() {
        return doctorRegistrationDate;
    }

    public void setDoctorRegistrationDate(Date doctorRegistrationDate) {
        this.doctorRegistrationDate = doctorRegistrationDate;
    }

    public String getClinicTiming() {
        return clinicTiming;
    }

    public void setClinicTiming(String clinicTiming) {
        this.clinicTiming = clinicTiming;
    }

    public String getOffDay() {
        return offDay;
    }

    public void setOffDay(String offDay) {
        this.offDay = offDay;
    }

    public int getEmergencyFees() {
        return emergencyFees;
    }

    public void setEmergencyFees(int emergencyFees) {
        this.emergencyFees = emergencyFees;
    }

    public int getFees() {
        return fees;
    }

    public void setFees(int fees) {
        this.fees = fees;
    }

    public boolean isPharmacy() {
        return isPharmacy;
    }

    public void setPharmacy(boolean isPharmacy) {
        this.isPharmacy = isPharmacy;
    }

    public boolean isProvideHomecare() {
        return isProvideHomecare;
    }

    public void setProvideHomecare(boolean isProvideHomecare) {
        this.isProvideHomecare = isProvideHomecare;
    }


    public String getIsBelongToAnyHospital() {
        return isBelongToAnyHospital;
    }

    public void setIsBelongToAnyHospital(String isBelongToAnyHospital) {
        this.isBelongToAnyHospital = isBelongToAnyHospital;
    }

    public boolean isInPanel() {
        return inPanel;
    }

    public void setInPanel(boolean inPanel) {
        this.inPanel = inPanel;
    }

    public boolean isAppointmnet() {
        return appointmnet;
    }

    public void setAppointmnet(boolean appointmnet) {
        this.appointmnet = appointmnet;
    }

    public boolean isVirtualReceptionist() {
        return virtualReceptionist;
    }

    public void setVirtualReceptionist(boolean virtualReceptionist) {
        this.virtualReceptionist = virtualReceptionist;
    }

    public boolean isPostcare() {
        return postcare;
    }

    public void setPostcare(boolean postcare) {
        this.postcare = postcare;
    }

    public int getYearofExperience() {
        return yearofExperience;
    }

    public void setYearofExperience(int yearofExperience) {
        this.yearofExperience = yearofExperience;
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

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
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

    public String getSpeciality() {
        return Speciality;
    }

    public void setSpeciality(String speciality) {
        Speciality = speciality;
    }

    public boolean isProvideHomeConsultationFees() {
        return isProvideHomeConsultationFees;
    }

    public void setProvideHomeConsultationFees(boolean isProvideHomeConsultationFees) {
        this.isProvideHomeConsultationFees = isProvideHomeConsultationFees;
    }

    public boolean isIsDiagnostics() {
        return IsDiagnostics;
    }

    public void setIsDiagnostics(boolean isDiagnostics) {
        IsDiagnostics = isDiagnostics;
    }

    public boolean isIsProvideAnsweringService() {
        return IsProvideAnsweringService;
    }

    public void setIsProvideAnsweringService(boolean isProvideAnsweringService) {
        IsProvideAnsweringService = isProvideAnsweringService;
    }

    public boolean isIsProvidePostCallFollowup() {
        return IsProvidePostCallFollowup;
    }

    public void setIsProvidePostCallFollowup(boolean isProvidePostCallFollowup) {
        IsProvidePostCallFollowup = isProvidePostCallFollowup;
    }

    public boolean isHealth_panel() {
        return Health_panel;
    }

    public void setHealth_panel(boolean health_panel) {
        Health_panel = health_panel;
    }

    public boolean isProvideEmergencyCare() {
        return provideEmergencyCare;
    }

    public void setProvideEmergencyCare(boolean provideEmergencyCare) {
        this.provideEmergencyCare = provideEmergencyCare;
    }

    public boolean isTaleMedicineEnabled() {
        return isTaleMedicineEnabled;
    }

    public void setTaleMedicineEnabled(boolean isTaleMedicineEnabled) {
        this.isTaleMedicineEnabled = isTaleMedicineEnabled;
    }

    public String getHasOwnHospital() {
        return hasOwnHospital;
    }

    public void setHasOwnHospital(String hasOwnHospital) {
        this.hasOwnHospital = hasOwnHospital;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public ArrayList<DoctorPhoneNumber> getDoctorPhoneNumbers() {
        return doctorPhoneNumbers;
    }

    public void setDoctorPhoneNumbers(ArrayList<DoctorPhoneNumber> doctorPhoneNumbers) {
        this.doctorPhoneNumbers = doctorPhoneNumbers;
    }

    public ArrayList<DoctorHospitalAffiliation> getDoctorHospitalAffiliation() {
        return doctorHospitalAffiliation;
    }

    public void setDoctorHospitalAffiliation(ArrayList<DoctorHospitalAffiliation> doctorHospitalAffiliation) {
        this.doctorHospitalAffiliation = doctorHospitalAffiliation;
    }


}

