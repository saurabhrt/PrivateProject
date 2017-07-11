package com.example.zues.healthok.util;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;

import com.example.zues.healthok.R;

/**
 * Created by hp1 on 28-02-2016.
 */
public class PatientHistory extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patienthistory);
        ActionBar actionBar = getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.rgb(24, 178, 244)));
        Typeface cg = Typeface.createFromAsset(getAssets(), "centurygothic.ttf");
        TextView tv10 = findViewById(R.id.textView10);
        TextView tv11 = findViewById(R.id.textView11);
        TextView tv12 = findViewById(R.id.textView12);
        TextView tv13 = findViewById(R.id.textView13);
        TextView tv14 = findViewById(R.id.textView14);
        tv10.setTypeface(cg);
        tv11.setTypeface(cg);
        tv12.setTypeface(cg);
        tv13.setTypeface(cg);
        tv14.setTypeface(cg);


    }
}
