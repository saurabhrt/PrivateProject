package com.example.zues.healthok.model;

/**
 * Created by Abhay-Jaiswal on 6/16/2016.
 */
public enum PhoneNumberType {

    Personal(1),
    Assistant(2),
    ER(3),
    APPT(4),
    ADMISSIONS(5);


    private final int phoneNumberType;

    PhoneNumberType(int phoneNumberType) {

        this.phoneNumberType = phoneNumberType;
    }

    public static PhoneNumberType item(int id) {
        return values()[id - 1];
    }

    public int getPhoneNumberType() {

        return this.phoneNumberType;
    }

}
