package com.example.zues.healthok;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zues.healthok.model.User;
import com.example.zues.healthok.model.UserFull;
import com.example.zues.healthok.service.RegistrationIntentService;
import com.example.zues.healthok.util.ServiceHandler;
import com.example.zues.healthok.util.ServiceURL;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {
    private static int ERROR = -1;
    private static int NETWORK_ERROR = -2;
    private static int REQUEST_CODE = 1024;
    EditText uname;
    EditText pass;
    String username;
    String password;
    Button log;
    SessionManager sessionManager;
    TextView textview;
    int status = ERROR;
    // URL to get contacts JSON
    String jsonStr;
    // contacts JSONArray
    JSONObject result = null;
    User user = null;
    private ProgressDialog pDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        sessionManager = new SessionManager(getApplicationContext());

        /*textview=(TextView)findViewById(R.id.textView20);
        textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(), Signup.class);
                startActivity(in);

            }
        });*/
    }

    public void signup(View view) {
        Intent in = new Intent(getApplicationContext(), Signup.class);
        startActivityForResult(in, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != REQUEST_CODE || resultCode != RESULT_OK) return;
        startGCMListener();
        finish();
    }

    // code for GCM
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {

            Log.d("Login", "Google play not supported");
            return false;
        }
        return true;
    }

    private void startGCMListener() {
        SessionManager sessionManager = new SessionManager(getApplicationContext());
        boolean sentToken = sessionManager.getSentTokenToServer();
        if (!sentToken) {
            if (checkPlayServices()) {
                // Start IntentService to register this application with GCM.
                Intent intent = new Intent(this, RegistrationIntentService.class);
                startService(intent);
            }
        }
//        else{
//            Intent intent_receiver = new Intent(this, MyGcmListenerService.class);
//            startService(intent_receiver);
//        }
        else {
            Log.d("StartService", "Registration already done. Do nothing");
        }
    }

    public void gotoprofile(View view) {
        uname = (EditText) findViewById(R.id.uname);
        pass = (EditText) findViewById(R.id.pswd);
        username = uname.getText().toString();
        password = pass.getText().toString();
        sessionManager = new SessionManager(getApplicationContext());
//        url="url"+username+"/"+password;
        if (!validate()) return;
        new GetContacts().execute();
    }

    public boolean validate() {
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "All fields are compulsory", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }


    public void gotosignup(View view) {
        Intent intent = new Intent(getApplicationContext(), Signup.class);
        startActivity(intent);
        startGCMListener();
    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(Login.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }


        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();

            // Making a request to url and getting response
            // buid name value pair
            List<NameValuePair> params = new ArrayList<>(2);
//            params.add(new BasicNameValuePair("email", "len.7206@gmail.com"));
//            params.add(new BasicNameValuePair("password", "123456"));
//            params.add(new BasicNameValuePair("loginid", username));
//            params.add(new BasicNameValuePair("password", password));
            params.add(new BasicNameValuePair("username", username));
            params.add(new BasicNameValuePair("password", password));
            jsonStr = sh.makeServiceCall(ServiceURL.Login, ServiceHandler.POST, params);


//jsonStr=sh.makeServiceCall("medicineorder/1",ServiceHandler.GET);
//jsonStr = "not called";
            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    result = new JSONObject(jsonStr);
                    status = Integer.parseInt(result.getString("userId"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            if (status == ERROR) {
                Toast.makeText(getApplicationContext(), "WRONG Username / Password", Toast.LENGTH_LONG).show();
            } else if (status == NETWORK_ERROR) {
                Toast.makeText(getApplicationContext(), "Unable to reach server try again...", Toast.LENGTH_LONG).show();
            } else {
                user = new User(jsonStr);
                UserFull userfull = UserFull.fromJSON(jsonStr);
                if (userfull != null) {
                    Log.d("LOGIN", "Userfull Name is " + userfull.getFirstName());
                } else {
                    Log.d("LOGIN", "Unable to parse json string " + jsonStr);
                }
                sessionManager.createLoginSession(user);
                // start GCM Listener
                startGCMListener();

//                Intent intent=new Intent(getApplicationContext(),HomePage.class);
                finish();
            }

        }

    }

}
/*
public class Login extends AppCompatActivity
{
    private ProgressDialog pDialog;

    EditText uname;
    EditText pass;
    String username ;
    String password;
    Button log;
    SessionManager sessionManager;
    TextView textview;
    private static int ERROR = -1;
    private static int NETWORK_ERROR = -2;
    // URL to get contacts JSON

    int status=ERROR;
    String jsonStr;
    // contacts JSONArray
    JSONObject result = null;
    User user = null;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        /*textview=(TextView)findViewById(R.id.textView20);
        textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(), Signup.class);
                startActivity(in);

            }
        });* /
        }
// code for GCM
private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {

        Log.d("Login","Google play not supported");
        return false;
        }
        return true;
        }

private void startGCMListener ()
        {

        SessionManager sessionManager = new SessionManager(getApplicationContext());
        boolean sentToken = sessionManager.getSentTokenToServer();
        if(!sentToken) {
        if (checkPlayServices()) {
        // Start IntentService to register this application with GCM.
        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);
        }
        }
//        else{
//            Intent intent_receiver = new Intent(this, MyGcmListenerService.class);
//            startService(intent_receiver);
//        }
        else {
        Log.d("StartService","Registration already done. Do nothing");
        }
        }

public void gotoprofile(View view)
        {
        uname=(EditText)findViewById(R.id.uname);
        pass=(EditText)findViewById(R.id.pswd);
        username=uname.getText().toString();
        password=pass.getText().toString();
        sessionManager = new SessionManager(getApplicationContext());
//        url="url"+username+"/"+password;
        new GetContacts().execute();

        }


public  void gotosignup(View view)
        {
        Intent intent=new Intent(getApplicationContext(),Signup.class);
        startActivity(intent);

        }

private class GetContacts extends AsyncTask<Void, Void, Void> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Showing progress dialog
        pDialog = new ProgressDialog(Login.this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();


    }


    @Override
    protected Void doInBackground(Void... arg0) {
        // Creating service handler class instance
        ServiceHandler sh = new ServiceHandler();

        // Making a request to url and getting response
        // buid name value pair
        List<NameValuePair> params = new ArrayList<>(2);
//            params.add(new BasicNameValuePair("email", "len.7206@gmail.com"));
//            params.add(new BasicNameValuePair("password", "123456"));
        params.add(new BasicNameValuePair("loginid", username));
        params.add(new BasicNameValuePair("password", password));
        jsonStr=sh.makeServiceCall( ServiceURL.Login, ServiceHandler.POST,params);



//jsonStr=sh.makeServiceCall("medicineorder/1",ServiceHandler.GET);
//jsonStr = "not called";
        Log.d("Response: ", "> " + jsonStr);

        if (jsonStr != null) {
            try {
                result = new JSONObject(jsonStr);
                status =  Integer.parseInt(result.getString("userId"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Log.e("ServiceHandler", "Couldn't get any data from the url");
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        // Dismiss the progress dialog
        if (pDialog.isShowing())
            pDialog.dismiss();
        if(status == ERROR)
        {
            Toast.makeText(getApplicationContext(), "WRONG Username / Password", Toast.LENGTH_LONG).show();
        }
        else if ( status == NETWORK_ERROR)
        {
            Toast.makeText(getApplicationContext(), "Unable to reach server try again...", Toast.LENGTH_LONG).show();
        }
        else
        {
            user = new User(jsonStr);
            UserFull userfull = UserFull.fromJSON(jsonStr);
            if ( userfull != null) {
                Log.d("LOGIN", "Userfull Name is " + userfull.getFirstName());
            }
            else
            {
                Log.d("LOGIN", "Unable to parse json string " + jsonStr);
            }
            sessionManager.createLoginSession(user);
            // start GCM Listener
            startGCMListener();

            Intent intent=new Intent(getApplicationContext(),HomePage.class);
            startActivity(intent);
        }

    }

}

}
*/