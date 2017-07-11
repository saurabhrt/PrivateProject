package com.example.zues.healthok.model;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.zues.healthok.R;

/**
 * Created by Raghuveer on 17/05/2016.
 */
public class medicine1 extends Activity {
    ImageView iv;

    public void onCreate(Bundle savedInstanceState) {

        iv = findViewById(R.id.medicine_button_homepage);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.medicine);

            }
        });


    }

}
