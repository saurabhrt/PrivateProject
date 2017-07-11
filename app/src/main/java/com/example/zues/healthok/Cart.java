package com.example.zues.healthok;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zues.healthok.util.ServiceHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class Cart extends ActionBarActivity {

    double dprice;
    double amount = 0;
    int iquantity;

    String url;
    String jsonStr;
    String medicinename, quantity, price, medicineId;
    JSONObject orderlist;
    CartProduct sr;
    ArrayList<CartProduct> results;
    ListView lv;
    ArrayList<CartProduct> searchResults;
    JSONArray responsearr;

    TextView amounttv;

    CartCustomAdapter adapter;
    SessionManager session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        String username = user.get(SessionManager.KEY_USERNAME);

        lv = (ListView) findViewById(R.id.cartitems);
        amounttv = (TextView) findViewById(R.id.amount);
        results = new ArrayList<CartProduct>();
        url = "buffer/cart/" + username;

        new GetContacts().execute();

    }

    public void placeOrder(View view) {
        Intent intent = new Intent(Cart.this, OrderPlace.class);
        startActivity(intent);
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
                        medicinename = result.getString("medicineName");
                        price = result.getString("price");
                        quantity = result.getString("quantity");
                        medicineId = result.getString("medicineId");
                        iquantity = Integer.parseInt(quantity);
                        dprice = Double.parseDouble(price);
                        amount = amount + dprice * iquantity;
                        sr = new CartProduct();
                        sr.setName(medicinename);
                        sr.setCityState(quantity);
                        sr.setPhone(price);
                        sr.setMedicineId(medicineId);
                        results.add(sr);
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


            String amountStr = Double.toString(amount);
            amounttv.setText(amountStr);
            lv.setAdapter(new CartCustomAdapter(Cart.this, results));


        }

    }


}
