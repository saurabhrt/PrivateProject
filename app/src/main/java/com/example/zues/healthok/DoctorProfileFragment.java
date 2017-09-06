package com.example.zues.healthok;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zues.healthok.model.Doctor;

public class DoctorProfileFragment extends Fragment {

    ImageView imageView;
    TextView name, speciality, address, timing, fee;
    Button button;
    Bundle bundle;
    int position;
    HomeActivity homeActivity;
    View inflate;

    public DoctorProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_doctor_profile, container, false);
        imageView = inflate.findViewById(R.id.Doctorimage);
        name = inflate.findViewById(R.id.DoctorName);
        fee = inflate.findViewById(R.id.Fees);
        timing = inflate.findViewById(R.id.timing);
        address = inflate.findViewById(R.id.address);
        speciality = inflate.findViewById(R.id.speciality);
        button = inflate.findViewById(R.id.appointment);

        Doctor doctordata = homeActivity.doctorForOtherFragments;
        name.setText("Dr. " + doctordata.getFirstName() + " " + doctordata.getLastName());
        speciality.setText(doctordata.getSpeciality());
        fee.setText("Rs " + doctordata.getFees());
        timing.setText(doctordata.getClinicTiming());
        address.setText(doctordata.getAddressLine1() + "\n" + doctordata.getAddressLine2() + doctordata.getAddressLine3());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  startActivity(new Intent(Profile.this,Patientform.class));
                //  homeActivity.swapFragment(new Profilefragment());
                homeActivity.swapFragment(new OrderDoctorAppointmentFragment());
            }
        });
        return inflate;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        homeActivity = (HomeActivity) getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


}
