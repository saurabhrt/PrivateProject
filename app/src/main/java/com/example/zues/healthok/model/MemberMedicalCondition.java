package com.example.zues.healthok.model;

/**
 * Created by Abhay-Jaiswal on 7/18/2016.
 */
public class MemberMedicalCondition {

    private MedicalConditionType medicalConditionTypeId;
    private int memberMedicalConditionId;
    private String description;
    private int memberId;

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public MedicalConditionType getMedicalConditionTypeId() {
        return medicalConditionTypeId;
    }

    public void setMedicalConditionTypeId(MedicalConditionType medicalConditionTypeId) {
        this.medicalConditionTypeId = medicalConditionTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMemberMedicalConditionId() {
        return memberMedicalConditionId;
    }

    public void setMemberMedicalConditionId(int memberMedicalConditionId) {
        this.memberMedicalConditionId = memberMedicalConditionId;
    }

}
