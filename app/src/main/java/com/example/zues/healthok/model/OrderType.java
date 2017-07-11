package com.example.zues.healthok.model;

/**
 * Created by Abhay-Jaiswal on 6/16/2016.
 */
public enum OrderType {

    MEDICINE(1),
    LAB(2),
    APPT(3),
    AMBULANCE(4),
    NURSE(5);


    private final int orderType;

    OrderType(int orderType) {

        this.orderType = orderType;
    }

    public static OrderType item(int id) {
        return values()[id - 1];
    }

    public int getOrderType() {

        return this.orderType;
    }

}

