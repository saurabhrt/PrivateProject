package com.example.zues.healthok.model;

public enum CallResultType {

    POSITIVE(1),
    ORDERPLACED(2),
    NOTREACHABLE(3),
    NEGATIVE(4),
    FOLLOWUP(5),
    CUSTCANCELLEDORDER(6),
    HOKCANCELLEDORDER(7),
    WRONGNUMBER(8);
    private final int callResultType;

    CallResultType(int callResultType) {

        this.callResultType = callResultType;
    }

    public int getCallResultType() {

        return this.callResultType;
    }


    public static CallResultType item(int id) {
        return values()[id - 1];
    }


}
