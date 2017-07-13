package com.example.zues.healthok;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zues.healthok.model.User;
import com.example.zues.healthok.model.UserFull;
import com.example.zues.healthok.model.UserPhoneNumber;
import com.example.zues.healthok.util.ServiceHandler;
import com.example.zues.healthok.util.ServiceURL;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MyProfileFragment extends Fragment {
    String status = "-5";
    JSONObject result = null;
    View inflate;
    private SessionManager sessionManager;
    private User user;
    private HomeActivity homeActivity;
    private ProgressDialog pDialog;
    private String jsonStr;
    private UserFull userFull;
    //For password change
    private String oldPassword = "";
    private String newPassword = "";

    public MyProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        sessionManager = new SessionManager(context);
        homeActivity = (HomeActivity) getActivity();
        user = sessionManager.getUser();
        new GetUserDetails().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_my_profile, container, false);
        inflate.findViewById(R.id.toggleFormView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View formView = inflate.findViewById(R.id.formView);
                if (formView.getVisibility() == View.VISIBLE)
                    formView.setVisibility(View.INVISIBLE);
                else
                    formView.setVisibility(View.VISIBLE);
            }
        });
        inflate.findViewById(R.id.changePasswordButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePassword(view);
            }
        });
        return inflate;
    }

    public void showProfile() {
        ((TextView) inflate.findViewById(R.id.nameTextView)).setText(userFull.getFirstName() + " " + userFull.getLastName());
        ((TextView) inflate.findViewById(R.id.regNoTextView)).setText(userFull.getRegistrationNumber());
        ((TextView) inflate.findViewById(R.id.emailTextView)).setText(userFull.getEmailId());
        ((TextView) inflate.findViewById(R.id.mobileNoTextView)).setText(userFull.getMobile());
        ((TextView) inflate.findViewById(R.id.membershipTypeTextView)).setText(userFull.getMembershipTypeId().toString());
        String str = "";
        ArrayList<UserPhoneNumber> userPhoneNumbers = userFull.getUserPhoneNumber();
        for (int i = 0; i < userPhoneNumbers.size(); i++) {
            str += userPhoneNumbers.get(i).getAdditionalPhoneNumber();
            if (i < userPhoneNumbers.size())
                str += ", ";
        }
        ((TextView) inflate.findViewById(R.id.addPhoneTextView)).setText(str);


        //Todo: Get complete address with city from cityid
        String address = userFull.getAddressLine1() + ", " + userFull.getAddressLine2();
        ((TextView) inflate.findViewById(R.id.addressTextView)).setText(address);
    }

    public void changePassword(View view) {
        oldPassword = ((EditText) inflate.findViewById(R.id.oldPasswordText)).getText().toString();
        newPassword = ((EditText) inflate.findViewById(R.id.newPasswordText)).getText().toString();
        String confirmPassword = ((EditText) inflate.findViewById(R.id.confirmPasswordText)).getText().toString();
        if (validate(oldPassword, newPassword, confirmPassword))
            new ChangeUserPassword().execute();
    }

    public boolean validate(String oldPassword, String newPassword, String confirmPassword) {
        if (oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(homeActivity.getApplicationContext(), "All fields are compulsory", Toast.LENGTH_LONG).show();
            return false;
        }
        if (!newPassword.equals(confirmPassword)) {
            Toast.makeText(homeActivity.getApplicationContext(), "Passwords do not match", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private class GetUserDetails extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            //super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(homeActivity);
            pDialog.setMessage("Retrieving Details... ");
            // pDialog.setMax(16);
            pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

            pDialog.setCancelable(false);
            pDialog.show();


        }


        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();
            String url = ServiceURL.UserDetails + user.getUserId();
            // Making a request to url and getting response

            jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    result = new JSONObject(jsonStr);
//                    status = result.getString("status");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void resultt) {
            super.onPostExecute(resultt);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

            userFull = UserFull.fromJSON(jsonStr);
            showProfile();

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


    private class ChangeUserPassword extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            //super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(homeActivity);
            pDialog.setMessage("Changing Password... ");
            // pDialog.setMax(16);
            pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

            pDialog.setCancelable(false);
            pDialog.show();
        }


        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();
            String url = ServiceURL.ChangePassword;
            // Making a request to url and getting response
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("userId", "" + user.getUserId()));
            params.add(new BasicNameValuePair("password", newPassword));
            params.add(new BasicNameValuePair("registrationNumber", oldPassword));
            jsonStr = sh.makeServiceCall(url, ServiceHandler.POST, params);

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                status = jsonStr;
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

            //Server do not return any status
            if (status.equals("1")) {
                Toast.makeText(homeActivity.getApplicationContext(), "Password changed successfully", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(homeActivity.getApplicationContext(), "Error in changing password", Toast.LENGTH_LONG).show();
            }

        }
    }

}
