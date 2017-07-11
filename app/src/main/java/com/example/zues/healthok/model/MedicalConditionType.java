package com.example.zues.healthok.model;

/**
 * Created by Abhay-Jaiswal on 7/18/2016.
 */
public enum MedicalConditionType {

    BP(1),
    SUGAR(2),
    THYROID(3);

    private final int medicalConditionType;

    MedicalConditionType(int medicalConditionType) {
        this.medicalConditionType = medicalConditionType;
    }

    public static MedicalConditionType item(int id) {
        return values()[id - 1];
    }

    public int getmedicalConditionType() {
        return this.medicalConditionType;
    }

}
