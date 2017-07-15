package com.example.zues.healthok.model;

public enum MedicalCondition {


    NORISK(1),
    LOWRISK(2),
    HIGHRISK(3),
    DIAGNOSED(4);


    private final int medicalCondition;

    MedicalCondition(int medicalCondition) {

        this.medicalCondition = medicalCondition;
    }

    public int getMedicalCondition() {

        return this.medicalCondition;
    }


    public static MedicalCondition item(int id) {
        return values()[id - 1];
    }


}
