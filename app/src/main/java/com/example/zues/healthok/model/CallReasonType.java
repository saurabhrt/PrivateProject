package com.example.zues.healthok.model;

public enum CallReasonType {

    COLDCALL(1),
    ORDERFEEDBACK(2),
    CUSTOMERQUERY(3),
    FOLLOWUP(4),
    QUERYRESPONSE(5),
    POSTCARE(6);

    private final int callReasonType;

    CallReasonType(int callReasonType) {

        this.callReasonType = callReasonType;
    }

    public int getCallReasonType() {

        return this.callReasonType;
    }


    public static CallReasonType item(int id) {
        return values()[id - 1];
    }


}
