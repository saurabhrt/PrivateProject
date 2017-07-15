package com.example.zues.healthok.model;

public class ImageData {
    private int imageid;
    private int imageidtype;
    private String image;

    public ImageData() {

    }

    public ImageData(int imageidt, String image1) {
        super();
        this.imageidtype = imageidt;
        this.image = image1;
    }

    public int getImageid() {
        return imageid;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }

    public int getImageidtype() {
        return imageidtype;
    }

    public void setImageidtype(int imageidtype) {
        this.imageidtype = imageidtype;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
