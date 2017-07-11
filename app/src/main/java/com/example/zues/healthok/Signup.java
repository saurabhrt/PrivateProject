package com.example.zues.healthok;


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zues.healthok.model.User;
import com.example.zues.healthok.util.ServiceHandler;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Signup extends Activity {
    //    private static String url="EmailRegistration/access";
    private static String url = "users/register";
    EditText fname, lname, mail, pass, pnum, cpass;
    String firstName, lastName, email, password, phone, cpassword;
    String status = "-5";
    JSONObject result = null;
    String jsonStr;
    private ProgressDialog pDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);
       /* ActionBar actionBar=getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.rgb(24,178,244)));
        Typeface cg=Typeface.createFromAsset(getAssets(),"centurygothic.ttf");
        EditText uname=(EditText)findViewById(R.id.uname);
        EditText pswd=(EditText)findViewById(R.id.pswd);
        Button signin=(Button)findViewById(R.id.signin);
        signin.setTypeface(cg);
        uname.setTypeface(cg);
        pswd.setTypeface(cg);*/
    }

    public void register(View view) {
        fname = findViewById(R.id.firstNameText);
        lname = findViewById(R.id.lastNameText);
        mail = findViewById(R.id.emailText);
        pnum = findViewById(R.id.mobileNoText);
        pass = findViewById(R.id.passwordText);
        cpass = findViewById(R.id.confirmPasswordText);
        firstName = fname.getText().toString();
        lastName = lname.getText().toString();
        email = mail.getText().toString();
        phone = pnum.getText().toString();
        password = pass.getText().toString();
        cpassword = cpass.getText().toString();
        // url="signup/"+firstname+"/"+lastname+"/"+email+"/"+password+"/"+phone;
        if (password.equals(cpassword)) {
            new Register().execute();
        } else Toast.makeText(this, "Passwords do not match!!", Toast.LENGTH_SHORT);
    }

    private class Register extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            //super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(Signup.this);
            pDialog.setMessage("Creating Account... ");
            // pDialog.setMax(16);
            pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

            pDialog.setCancelable(false);
            pDialog.show();


        }


        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();

            // Making a request to url and getting response
            //NameValuePair values code
            List<NameValuePair> params = new ArrayList<>(5);
            params.add(new BasicNameValuePair("firstName", firstName));
            params.add(new BasicNameValuePair("lastName", lastName));
//            params.add(new BasicNameValuePair("email",email));
            params.add(new BasicNameValuePair("emailId", email));
            params.add(new BasicNameValuePair("password", password));
//            params.add(new BasicNameValuePair("phone",phone));
            params.add(new BasicNameValuePair("mobile", phone));

            jsonStr = sh.makeServiceCall(url, ServiceHandler.POST, params);

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
            if (pDialog.isShowing())
                pDialog.dismiss();

            new SessionManager(getApplicationContext()).createLoginSession(new User(jsonStr));
            setResult(RESULT_OK);
            finish();
/*          //Server do not return any status
            if(status.equals("1"))
            {
                Toast.makeText(getApplicationContext(), "Data submitted", Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();

            }
*/
        }


    }

}

