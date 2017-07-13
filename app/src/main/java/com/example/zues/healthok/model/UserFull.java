package com.example.zues.healthok.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Abhay-Jaiswal on 7/18/2016.
 */
public class UserFull {

    private int id;
    private int userId;
    private String registrationNumber;
    //Typing Mistake
//    private Date regstrationDate;
    private Date registrationDate;
    private MembershipType membershipTypeId;
    private String firstName = null;
    private String lastName = null;
    //	private int AddressId;
    private String emailId = null;
    private String mobile;
    private String password = null;
    private String doctorGenerallyVisited = null;
    private String comments = null;
    private int prepaidBalance;
    private int cashbackBonusBalance;
    private int totalDiscount;
    private String addressLine1 = null;
    private String addressLine2 = null;
    // private String AddressLine3=null;
    private int cityId;
    private String pinCode = null;
    private Date createdOn = null;
    private Date updatedOn = null;
    private String source = null;
    private String enteredBy = null;
    private String longtermNeed = null;
    private Date registrationExpiryDate = null;
    private int registrationFees;
    private ArrayList<MemberDetail> memberDetail;
    private ArrayList<UserPhoneNumber> userPhoneNumber;

    public static UserFull fromJSON(String jsonString) {
        UserFull userFull = null;
        Gson gson = new GsonBuilder().setDateFormat(
                "yyyy-MM-dd'T'HH:mm:ssZ").create();
        try {
            userFull = gson.fromJson(jsonString, UserFull.class);
            return userFull;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return userFull;

    }

    public int getId() {
        return userId;
    }

    public void setId(int id) {
        this.id = id;
        this.userId = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public MembershipType getMembershipTypeId() {
        return membershipTypeId;
    }

    public void setMembershipTypeId(MembershipType membershipTypeId) {
        this.membershipTypeId = membershipTypeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDoctorGenerallyVisited() {
        return doctorGenerallyVisited;
    }

    public void setDoctorGenerallyVisited(String doctorGenerallyVisited) {
        this.doctorGenerallyVisited = doctorGenerallyVisited;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getPrepaidBalance() {
        return prepaidBalance;
    }

    public void setPrepaidBalance(int prepaidBalance) {
        this.prepaidBalance = prepaidBalance;
    }

    public int getCashbackBonusBalance() {
        return cashbackBonusBalance;
    }

    public void setCashbackBonusBalance(int cashbackBonusBalance) {
        this.cashbackBonusBalance = cashbackBonusBalance;
    }

    public int getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(int totalDiscount) {
        this.totalDiscount = totalDiscount;
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

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getEnteredBy() {
        return enteredBy;
    }

    public void setEnteredBy(String enteredBy) {
        this.enteredBy = enteredBy;
    }

    public String getLongtermNeed() {
        return longtermNeed;
    }

    public void setLongtermNeed(String longtermNeed) {
        this.longtermNeed = longtermNeed;
    }

    public Date getRegistrationExpiryDate() {
        return registrationExpiryDate;
    }

    public void setRegistrationExpiryDate(Date registrationExpiryDate) {
        this.registrationExpiryDate = registrationExpiryDate;
    }

    public int getRegistrationFees() {
        return registrationFees;
    }

    public void setRegistrationFees(int registrationFees) {
        this.registrationFees = registrationFees;
    }

    public ArrayList<MemberDetail> getMemberDetail() {
        return memberDetail;
    }

    public void setMemberDetail(ArrayList<MemberDetail> memberDetail) {
        this.memberDetail = memberDetail;
    }

    public ArrayList<UserPhoneNumber> getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(ArrayList<UserPhoneNumber> userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

}
