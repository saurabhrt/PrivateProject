package com.example.zues.healthok;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

    private static final int CAMERA_REQUEST = 1888;
    ImageView search;

    ImageView call;

    Button logout;
    SessionManager session;
    SharedPreference sharedPreference;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new SessionManager(getApplicationContext());
        session.checkLogin();

        setContentView(R.layout.activity_main);

        logout = findViewById(R.id.logoutbtn);
        if (session.isLoggedIn() == true) {
            logout.setVisibility(View.VISIBLE);
        }
        sharedPreference = new SharedPreference();


        search = findViewById(R.id.searchbtn);
        call = findViewById(R.id.call);
        search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Search.class);
                startActivity(intent);
            }
        });


    }

    public void callHealthok(View view) {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("1234567890"));
        startActivity(callIntent);
    }

    public void loginPage(View v) {
        Intent intent = new Intent(MainActivity.this, Login.class);
        startActivity(intent);
    }

    public void logoutUser(View view) {
        session.logoutUser();
        sharedPreference.delete(getApplicationContext());
    }

    public void openCamera(View view) {
        Intent intent = new Intent(MainActivity.this, Camera.class);
        startActivity(intent);
    }

}