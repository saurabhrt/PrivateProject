package com.example.zues.healthok.model;

/**
 * Created by Abhay-Jaiswal on 7/18/2016.
 */
public enum MembershipType {


    REGISTERED(1),
    CAREPLUS(2),
    CAREGOLD(3),
    FREEREGISTRATION(4),
    CARE(5);


    private final int membershipType;

    MembershipType(int membershipType) {

        this.membershipType = membershipType;
    }

    public static MembershipType item(int id) {
        return values()[id - 1];
    }

    public int getmembershipType() {

        return this.membershipType;
    }


}