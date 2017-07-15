package com.example.zues.healthok.model;

public class GetSetMemberRegistration {

    public int UserId;
    public String MemberID = null;
    public int MembershipTypeId;
    public String FirstName = null;
    public String LastName = null;
    public int AddressId;
    public String EmailId = null;
    public String Mobile;
    public String Password = null;
    public int PrimaryDoctor;
    public String DoctorGenerallyVisited = null;
    public String Comments = null;
    public int PrepaidBalance;
    public int CashbackBousBalance;
    public int TotalDiscount;
    public String AddressLine1 = null;
    public String AddressLine2 = null;
    public String AddressLine3 = null;
    public int CityId;
    public String PinCode;


    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getPinCode() {
        return PinCode;
    }

    public void setPinCode(String pinCode) {
        PinCode = pinCode;
    }

    public String getEmailId() {
        return EmailId;
    }

    public void setEmailId(String emailId) {
        EmailId = emailId;
    }

    public String getAddressLine1() {
        return AddressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        AddressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return AddressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        AddressLine2 = addressLine2;
    }

    public int getCityId() {
        return CityId;
    }

    public void setCityId(int cityId) {
        CityId = cityId;
    }

    public String getDoctorGenerallyVisited() {
        return DoctorGenerallyVisited;
    }

    public void setDoctorGenerallyVisited(String doctorGenerallyVisited) {
        DoctorGenerallyVisited = doctorGenerallyVisited;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getMemberID() {
        return MemberID;
    }

    public void setMemberID(String memberID) {
        MemberID = memberID;
    }

    public int getMembershipTypeId() {
        return MembershipTypeId;
    }

    public void setMembershipTypeId(int membershipTypeId) {
        MembershipTypeId = membershipTypeId;
    }

    public int getAddressId() {
        return AddressId;
    }

    public void setAddressId(int addressId) {
        AddressId = addressId;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getPrimaryDoctor() {
        return PrimaryDoctor;
    }

    public void setPrimaryDoctor(int primaryDoctor) {
        PrimaryDoctor = primaryDoctor;
    }

    public String getComments() {
        return Comments;
    }

    public void setComments(String comments) {
        Comments = comments;
    }

    public int getPrepaidBalance() {
        return PrepaidBalance;
    }

    public void setPrepaidBalance(int prepaidBalance) {
        PrepaidBalance = prepaidBalance;
    }

    public int getCashbackBousBalance() {
        return CashbackBousBalance;
    }

    public void setCashbackBousBalance(int cashbackBousBalance) {
        CashbackBousBalance = cashbackBousBalance;
    }

    public int getTotalDiscount() {
        return TotalDiscount;
    }

    public void setTotalDiscount(int totalDiscount) {
        TotalDiscount = totalDiscount;
    }

    public String getAddressLine3() {
        return AddressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        AddressLine3 = addressLine3;
    }


}
/*
{
	  "firstName" : "1",
	  "lastName" : "1",
	  "phone" : "1",
	  "memberEmail" : "1",
	  "addressLine1" : "1",
	  "addressLine2" : "1",
	  "city" : "1",
	  "state" : "1",
	  "pin" : "1",
	  "country" : "1",
	  "doctorVisit" : "1",
	  "labTest" : "1",
	  "longCare" : "1",
	  "otherCare" : "1"
	  "family" : [{
	  "name" : "a",
	  "age" : "a",
	  "bloodGroup" : "a",
	  "allergies" : "a",
	  "medicalCondition" : "a",
	  "currentMedicalCondition" : "a",
	  "familyId" : "A"
	}]
	}*/
