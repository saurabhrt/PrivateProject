package com.example.zues.healthok.model;

/**
 * Created by Abhay-Jaiswal on 6/16/2016.
 */
public enum OrderStatusType {

    NEW(1),
    INPROGRESS(2),
    ONHOLD(3),
    CANCELLED(4),
    DISPATCHED(5),
    COMPLETE(6);


    private final int orderStatusType;

    OrderStatusType(int orderStatusType) {

        this.orderStatusType = orderStatusType;
    }

    public static OrderStatusType item(int id) {
        return values()[id - 1];
    }

    public int getOrderStatusType() {

        return this.orderStatusType;
    }


}