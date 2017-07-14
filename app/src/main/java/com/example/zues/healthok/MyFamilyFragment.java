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
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zues.healthok.model.MemberDetail;
import com.example.zues.healthok.model.User;
import com.example.zues.healthok.model.UserFull;
import com.example.zues.healthok.util.ServiceHandler;
import com.example.zues.healthok.util.ServiceURL;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MyFamilyFragment extends Fragment {
    JSONObject result = null;
    View inflate;
    private SessionManager sessionManager;
    private User user;
    private HomeActivity homeActivity;
    private ProgressDialog pDialog;
    private String jsonStr;
    private UserFull userFull;
    public MyFamilyFragment() {
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
        inflate = inflater.inflate(R.layout.fragment_my_family, container, false);
        return inflate;
    }


    private void showFamily() {
        ArrayList<MemberDetail> memberDetails = userFull.getMemberDetail();
        TableLayout tableLayout = inflate.findViewById(R.id.familyTable);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);

        MemberDetail md;
        for (int i = 0; i < memberDetails.size(); i++) {
            md = memberDetails.get(i);
            View view = LayoutInflater.from(getContext()).inflate(R.layout.member_detail_row, null);

            ((TextView) view.findViewById(R.id.numberText)).setText("" + i);
            ((TextView) view.findViewById(R.id.nameText)).setText(md.getFirstName() + " " + md.getLastName());
            ((TextView) view.findViewById(R.id.sexText)).setText(md.getSex());
            ((TextView) view.findViewById(R.id.ageText)).setText("" + md.getAge());
            ((TextView) view.findViewById(R.id.bloodGroupText)).setText(md.getBloodGroup());
            ((TextView) view.findViewById(R.id.allergiesText)).setText(md.getAllergies());
            String temp = "";
            for (int j = 0; j < md.getMemberMedicalCondition().size(); j++) {
                temp += md.getMemberMedicalCondition().get(i).getDescription();
                if (j != md.getMemberMedicalCondition().size() - 1)
                    temp += ",";

            }
            ((TextView) view.findViewById(R.id.medicalConditionText)).setText(temp);
            ((TextView) view.findViewById(R.id.currentMedicationsText)).setText(md.getCurrentMedications());
            ((TextView) view.findViewById(R.id.recurringTestsText)).setText(md.getRecurringTests());
            ((TextView) view.findViewById(R.id.logTermCareNeedsText)).setText(md.getLongTermCareNeeds());
            tableLayout.addView(view);
        }

    }


    private class GetUserDetails extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            //super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(homeActivity);
            pDialog.setMessage("Retrieving family member Details... ");
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
            if (jsonStr.isEmpty()) {
                Toast.makeText(homeActivity, "Unable to connect!!", Toast.LENGTH_SHORT).show();
            } else {
                userFull = UserFull.fromJSON(jsonStr);
                showFamily();
            }

        }


    }



}
