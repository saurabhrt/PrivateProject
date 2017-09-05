package com.example.zues.healthok;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.zues.healthok.model.Doctor;

import static android.media.CamcorderProfile.get;


public class Profile extends Activity {

    ImageView imageView;
    TextView name,speciality,address,timing,fee;
    Button button;
    Bundle bundle;
    int position;
    HomeActivity homeActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctorprofile);



            imageView=  findViewById(R.id.Doctorimage);
            name=  findViewById(R.id.DoctorName);
            fee=  findViewById(R.id.Fees);
            timing=  findViewById(R.id.timing);
            address= findViewById(R.id.address);
            speciality=  findViewById(R.id.speciality);
            button=  findViewById(R.id.appointment);


            position=BookAppointmentFragment.pos;
           dataset(position);



        }
    void dataset(int pos)
    {
        Doctor doctordata=BookAppointmentFragment.listresult.get(pos);
       //imageView.setImageBitmap();
        name.setText("Dr. "+doctordata.getFirstName()+" "+doctordata.getLastName());
        speciality.setText(doctordata.getSpeciality());
        fee.setText("Rs "+doctordata.getFees());
        timing.setText(doctordata.getClinicTiming());
        address.setText(doctordata.getAddressLine1()+ "\n"+doctordata.getAddressLine2()+doctordata.getAddressLine3());
         button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
               //  startActivity(new Intent(Profile.this,Patientform.class));
                 homeActivity.swapFragment(new Profilefragment());
             }
         });



    }

}
