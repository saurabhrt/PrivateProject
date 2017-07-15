package com.example.zues.healthok.model;

import java.util.Date;

public class CallLog {

    int callLogId;
    int userId;
    int orderId;
    int memberId;
    CallReasonType callReasonType;
    CallResultType callResultType;
    int employeeId;
    RatingType ratingType;
    Date followupDate;
    String callDetails;
    String comments;
    String supervisorRemarks;
    Date createDate;
    Date updatedOn;
    String customerProblem;
    boolean followupComplete;
    String registrationNumber;
    String firstName;
    String lastName;
    String mobile;

    public int getCallLogId() {
        return callLogId;
    }

    public void setCallLogId(int callLogId) {
        this.callLogId = callLogId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public CallReasonType getCallReasonType() {
        return callReasonType;
    }

    public void setCallReasonType(CallReasonType callReasonType) {
        this.callReasonType = callReasonType;
    }

    public CallResultType getCallResultType() {
        return callResultType;
    }

    public void setCallResultType(CallResultType callResultType) {
        this.callResultType = callResultType;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public RatingType getRatingType() {
        return ratingType;
    }

    public void setRatingType(RatingType ratingType) {
        this.ratingType = ratingType;
    }

    public Date getFollowupDate() {
        return followupDate;
    }

    public void setFollowupDate(Date followupDate) {
        this.followupDate = followupDate;
    }

    public String getCallDetails() {
        return callDetails;
    }

    public void setCallDetails(String callDetails) {
        this.callDetails = callDetails;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getSupervisorRemarks() {
        return supervisorRemarks;
    }

    public void setSupervisorRemarks(String supervisorRemarks) {
        this.supervisorRemarks = supervisorRemarks;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getCustomerProblem() {
        return customerProblem;
    }

    public void setCustomerProblem(String customerProblem) {
        this.customerProblem = customerProblem;
    }

    public boolean getFollowupComplete() {
        return followupComplete;
    }

    public void setFollowupComplete(boolean followupComplete) {
        this.followupComplete = followupComplete;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


}
