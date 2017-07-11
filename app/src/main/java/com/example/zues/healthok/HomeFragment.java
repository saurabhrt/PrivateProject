package com.example.zues.healthok;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    HomeActivity homeActivity;
    TextView textView;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        homeActivity = (HomeActivity) getActivity();
    }

    @Override
    public void onResume() {
        super.onResume();
        textView.setText("Welcome " + homeActivity.fullName + " !\n Now you can manage all your \n healthcare needs with one click.");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_home, container, false);
        textView = inflate.findViewById(R.id.welcomeTextView);
        inflate.findViewById(R.id.doctorAppointmentButton).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        homeActivity.selectNavigationItem(R.id.nav_menu_book_appointment);
                    }
                }
        );
        inflate.findViewById(R.id.pathologyButton).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        homeActivity.selectNavigationItem(R.id.nav_menu_order_test);
                    }
                }
        );
        inflate.findViewById(R.id.ambulanceButton).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        homeActivity.selectNavigationItem(R.id.nav_menu_order_ambulance);
                    }
                }
        );
        inflate.findViewById(R.id.medicineButton).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        homeActivity.selectNavigationItem(R.id.nav_menu_order_medicine);
                    }
                }
        );
        inflate.findViewById(R.id.nurseButton).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        homeActivity.selectNavigationItem(R.id.nav_menu_home_nursing);
                    }
                }
        );
        inflate.findViewById(R.id.myHealthButton).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        homeActivity.selectNavigationItem(R.id.nav_menu_my_profile);
                    }
                }
        );

        inflate.findViewById(R.id.telephoneButton).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String uri = "tel:1800-3000-6588";
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(uri));
                        startActivity(intent);
                    }
                }
        );
        return inflate;
    }
}
