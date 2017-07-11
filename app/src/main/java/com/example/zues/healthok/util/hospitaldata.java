package com.example.zues.healthok.util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.widget.TextView;

/**
 * Created by Raghuveer on 15/03/2016.
 */
public class hospitaldata extends Activity {

    private static String url = "http://localhost:8080/healthokapp/rest/Hospital/Add";
    TextView hname;
    TextView AddId;
    TextView Facilities;
    TextView opdfee;
    TextView bed;
    TextView Addline1;
    TextView Addline2;
    TextView Addline3;
    TextView cityid;
    TextView pincode;
    TextView Regdate;
    TextView website;
    TextView phone;
    TextView Radiology;
    TextView Diagnistic;
    TextView Ambulance;

    // url to display json data
    String status = "-3";
    String jsonstr;
    private ProgressDialog pdialog;
}
