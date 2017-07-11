package com.example.zues.healthok.util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.zues.healthok.R;

import org.json.JSONObject;

/**
 * Created by hp1 on 13-03-2016.
 */
public class Adddoctor extends Activity {
    private static String url = "Add/Doctor";
    EditText fname, mname, lname, dspec, ddegree, dtiming, doffday, dfees, daddress, dcityid, dpincode;
    String firstName, middleName, lastName, emailId, speciality, degree, docotrPhoneId, clinicTiming;
    RadioButton dvrec, dinpanel, daenable, dpcare;
    Button dsubmit;
    String status = "-5";
    JSONObject result = null;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adddoctor);

    }

    public void adddoctor(View view) {
        fname = findViewById(R.id.dfname);
        mname = findViewById(R.id.dmname);
        lname = findViewById(R.id.dlname);
        dspec = findViewById(R.id.dspec);
        ddegree = findViewById(R.id.ddegree);
        dtiming = findViewById(R.id.dtiming);
        doffday = findViewById(R.id.doffday);
        dfees = findViewById(R.id.dfees);
        daddress = findViewById(R.id.daddress);
        dcityid = findViewById(R.id.dcityid);
        dpincode = findViewById(R.id.dpincode);
        dvrec = findViewById(R.id.dvrec);
        dinpanel = findViewById(R.id.dinpanel);
        daenable = findViewById(R.id.daenable);
        dpcare = findViewById(R.id.dpcare);
        dsubmit = findViewById(R.id.dsubmit);


    }
}
