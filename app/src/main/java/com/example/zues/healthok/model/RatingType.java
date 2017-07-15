package com.example.zues.healthok.model;

public enum RatingType {

    BAD(1),
    NOTSATISFIED(2),
    SATISFIED(3),
    VERYHAPPY(4),
    EXCELLENT(5);

    private final int ratingType;

    RatingType(int ratingType) {

        this.ratingType = ratingType;
    }

    public int getRatingType() {

        return this.ratingType;
    }


    public static RatingType item(int id) {
        return values()[id - 1];
    }


}
