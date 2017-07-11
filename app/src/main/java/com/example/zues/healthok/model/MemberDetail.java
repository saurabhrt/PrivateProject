package com.example.zues.healthok.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Abhay-Jaiswal on 7/18/2016.
 */
public class MemberDetail {
    private int userid;
    private int memberId;
    private String firstName;
    private String lastName;
    private String sex;
    private Date dateOfBirth;
    private String bloodGroup;
    private ArrayList<MemberMedicalCondition> memberMedicalCondition;
    private String allergies;
    private String currentMedications;
    private String recurringTests;
    private String longTermCareNeeds;
    private String comments;
    private int age;
    private int memberNumber;
    private boolean isSelf;

    public ArrayList<MemberMedicalCondition> getMemberMedicalCondition() {
        return memberMedicalCondition;
    }

    public void setMemberMedicalCondition(ArrayList<MemberMedicalCondition> memberMedicalCondition) {
        this.memberMedicalCondition = memberMedicalCondition;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }


    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }


    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getCurrentMedications() {
        return currentMedications;
    }

    public void setCurrentMedications(String currentMedications) {
        this.currentMedications = currentMedications;
    }

    public String getRecurringTests() {
        return recurringTests;
    }

    public void setRecurringTests(String recurringTests) {
        this.recurringTests = recurringTests;
    }

    public String getLongTermCareNeeds() {
        return longTermCareNeeds;
    }

    public void setLongTermCareNeeds(String longTermCareNeeds) {
        this.longTermCareNeeds = longTermCareNeeds;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(int memberNumber) {
        this.memberNumber = memberNumber;
    }

    public boolean getIsSelf() {
        return isSelf;
    }

    public void setIsSelf(boolean isSelf) {
        this.isSelf = isSelf;
    }

}
