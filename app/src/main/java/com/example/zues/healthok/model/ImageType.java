package com.example.zues.healthok.model;

public enum ImageType {

    DocPic(1),
    Prescription(2),
    Report(3),
    UserPic(4),
    HospitalPic(5);

    private final int imageType;

    ImageType(int imageType) {

        this.imageType = imageType;
    }

    public int getimageType() {

        return this.imageType;
    }


    public static ImageType item(int id) {
        return values()[id - 1];
    }


}
