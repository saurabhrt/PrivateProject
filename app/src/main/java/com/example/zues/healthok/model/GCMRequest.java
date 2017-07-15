package com.example.zues.healthok.model;

import java.util.ArrayList;

public class GCMRequest {

    ArrayList<String> registration_ids;
    GCMRequestData data;

    public ArrayList<String> getRegistration_ids() {
        return registration_ids;
    }

    public void setRegistration_ids(ArrayList<String> registration_ids) {
        this.registration_ids = registration_ids;
    }

    public GCMRequestData getData() {
        return data;
    }

    public void setData(GCMRequestData data) {
        this.data = data;
    }


}
