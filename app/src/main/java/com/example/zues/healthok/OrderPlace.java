package com.example.zues.healthok;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zues.healthok.util.ServiceHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class OrderPlace extends ActionBarActivity {

    TextView add1tv, add2tv, citytv, statetv, countrytv, pintv;
    String url, url2;
    String username;
    String jsonStr;
    JSONObject result;
    String status;
    JSONArray responsearr;
    String houseno, street, city, state, country, pin;
    int addressid;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_place);
        session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        username = user.get(SessionManager.KEY_USERNAME);

        add1tv = (TextView) findViewById(R.id.addLine1);
        add2tv = (TextView) findViewById(R.id.addLine2);
        citytv = (TextView) findViewById(R.id.cityold);
        statetv = (TextView) findViewById(R.id.stateold);
        countrytv = (TextView) findViewById(R.id.countryold);
        pintv = (TextView) findViewById(R.id.pinold);
        url = "address/" + username;
        new GetContacts().execute();


    }

    public void orderplaced(View view) {
        String addId = Integer.toString(addressid);
        url2 = "order/deobrat811@gmail.com/500/20/50/" + addId;
        new PlaceAtOld().execute();

    }


    private class GetContacts extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();

            // Making a request to url and getting response
            jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

            Log.d("Response: ", "> " + jsonStr);


            if (jsonStr != null) {
                try {

                    responsearr = new JSONArray(jsonStr);
                    for (int i = 0; i < responsearr.length(); i++) {
                        JSONObject result = responsearr.getJSONObject(i);
                        houseno = result.getString("housenumber");
                        street = result.getString("street");
                        city = result.getString("city");
                        state = result.getString("state");
                        country = result.getString("country");
                        pin = result.getString("pin");
                        addressid = result.getInt("addressId");

                    }

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


            add1tv.setText(houseno);
            add2tv.setText(street);
            citytv.setText(city);
            statetv.setText(state);
            countrytv.setText(country);
            pintv.setText(pin);

            Toast.makeText(OrderPlace.this, "hello", Toast.LENGTH_LONG).show();


        }

    }

    private class PlaceAtOld extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();

            // Making a request to url and getting response
            jsonStr = sh.makeServiceCall(url2, ServiceHandler.GET);

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    result = new JSONObject(jsonStr);
                    status = result.getString("status");
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

            Toast.makeText(getApplicationContext(), "Placed", Toast.LENGTH_LONG).show();

        }

    }

}
