package com.example.zues.healthok;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zues.healthok.util.ServiceHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class Address extends ActionBarActivity {

    private static String url;
    EditText housetxt, streettxt, citytxt, statetxt, countrytxt, pincodetxt;
    TextView name;
    Button submit;
    String house, street, city, state, country, pincode;
    SessionManager session;
    String status;
    JSONObject response;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);


    }

    public void submitAddress(View view) {
        session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        String username = user.get(SessionManager.KEY_USERNAME);

        housetxt = (EditText) findViewById(R.id.house);
        streettxt = (EditText) findViewById(R.id.street);
        citytxt = (EditText) findViewById(R.id.city);
        statetxt = (EditText) findViewById(R.id.state);
        countrytxt = (EditText) findViewById(R.id.country);
        pincodetxt = (EditText) findViewById(R.id.pin);
        name = (TextView) findViewById(R.id.user);

        house = housetxt.getText().toString();
        street = streettxt.getText().toString();
        city = citytxt.getText().toString();
        state = statetxt.getText().toString();
        country = countrytxt.getText().toString();
        pincode = pincodetxt.getText().toString();

        name.setText(username);
        url = "address/" + house + "/" + street + "/" + city + "/" + state + "/" + country + "/" + pincode + "/" + username;
        new GetContacts().execute();
    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(Address.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();


        }


        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    response = new JSONObject(jsonStr);
                    status = response.getString("status");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void response) {
            super.onPostExecute(response);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            if (status.equals("1")) {
                Toast.makeText(getApplicationContext(), "Address submitted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Data Submission failed", Toast.LENGTH_LONG).show();
            }

        }

    }

}
