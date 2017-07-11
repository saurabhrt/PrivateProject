package com.example.zues.healthok;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Ashu on 2/28/2016.
 */
public class HomePage extends AppCompatActivity {


    Button b;
    ImageView bookAppointment;
    ImageView im1;
    ImageView im2;
    ImageView im3;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
//        myToolbar.setTitle("Home");
        setSupportActionBar(myToolbar);


/*
        im=(ImageView)findViewById(R.id.medicine_button_homepage);

        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent in=new Intent(HomePage.this,Medicine.class);
                // startActivity(in);
                //setContentView(R.layout.medicine);
                Intent intent=new Intent(getApplicationContext(),Medicine.class);
                startActivity(intent);
            }
        });
        im1=(ImageView)findViewById(R.id.transport_button_homepage);
        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // setContentView(R.layout.transport);
                Intent i=new Intent(getApplicationContext(),transport.class);
                startActivity(i);

            }
        });


        im2=(ImageView)findViewById(R.id.doctor_appoint_homepage);
        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.doctor);

            }
        });

        im3=(ImageView)findViewById(R.id.pathlab_button_homepage);
        im3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Pathology.class);
                startActivity(intent);
            }
        });

*/

    }


    //    public void onClick ( View view)
    public void onClick2(View view) {

        int btnId = view.getId();
        Intent intent = null;

        intent = new Intent(getApplicationContext(), Medicine.class);

/*
        switch ( btnId)

        {
            case R.id.btnDoctorAppointment:
//                intent = new Intent(getApplicationContext(),Medicine.class);
                break;
            case R.id.btnOrderMedicine:
                intent = new Intent(getApplicationContext(),Medicine.class);
                break;
            case R.id.btnOrderLab:
//                intent = new Intent(getApplicationContext(),Medicine.class);
                break;
            case R.id.btnOrderAmbulance:
//                intent = new Intent(getApplicationContext(),Medicine.class);
                break;
            case R.id.btnOrderNurse:
//                intent = new Intent(getApplicationContext(),Medicine.class);
                break;
            default:
                break;

        }
*/
        if (intent != null) {

            startActivity(intent);

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home_page, menu);


        return true;
    }


}
