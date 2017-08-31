package com.example.zues.healthok;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.zues.healthok.model.LabOrderDetail;
import com.example.zues.healthok.model.MemberDetail;
import com.example.zues.healthok.model.User;
import com.example.zues.healthok.model.UserFull;
import com.example.zues.healthok.util.ServiceHandler;
import com.example.zues.healthok.util.ServiceURL;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//TODO implement failure of request in each fragment

public class OrderAmbulanceFragment extends Fragment {
    private final int SELECT_PHOTO = 1;
    JSONObject result = null;
    String status = "-5";
    Uri imageUri;
    private HomeActivity homeActivity;
    private SessionManager sessionManager;
    private User user;
    private UserFull userFull;
    private View inflate;
    private ProgressDialog pDialog;
    private String jsonStr;
    private ArrayList<LabOrderDetail> lods;
    private ImageView prescriptionImageView;
    private String memberId;
    private String orderDescription;
    private String orderType = "AMBULANCE";
    private String orderFulfillDate;
    public OrderAmbulanceFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_order_ambulance, container, false);
        inflate.findViewById(R.id.orderButton).setEnabled(true);
        inflate.findViewById(R.id.orderButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedItemPosition = ((Spinner) inflate.findViewById(R.id.familyMemberSpinner)).getSelectedItemPosition();
                memberId = "" + userFull.getMemberDetail().get(selectedItemPosition).getMemberId();
                orderDescription = ((EditText) inflate.findViewById(R.id.medicineEditText)).getText().toString();
                String from = ((EditText) inflate.findViewById(R.id.fromEditText)).getText().toString();
                String to = ((EditText) inflate.findViewById(R.id.toEditText)).getText().toString();
                orderFulfillDate = ((EditText) inflate.findViewById(R.id.dateEditText)).getText().toString();
                if (memberId == "") {
                    Toast.makeText(homeActivity, "Please select family member!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (orderDescription == "") {
                    Toast.makeText(homeActivity, "Please enter any specific ambulance details!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (from == "") {
                    Toast.makeText(homeActivity, "Please enter pickup location!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (to == "") {
                    Toast.makeText(homeActivity, "Please enter drop location!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (orderFulfillDate == "") {
                    Toast.makeText(homeActivity, "Please enter need by date!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                orderDescription = orderDescription + " From: " + from + " To: " + to;
                new UploadDetails().execute();
            }
        });
        return inflate;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        sessionManager = new SessionManager(context);
        homeActivity = (HomeActivity) getActivity();
        user = sessionManager.getUser();
        new GetUserDetails().execute();
    }

    private void showFamilyMembers() {
        Spinner familyMemberSpinner = inflate.findViewById(R.id.familyMemberSpinner);
        ArrayList<MemberDetail> memberDetail = userFull.getMemberDetail();
        ArrayList<String> array = new ArrayList<>();
        for (MemberDetail member : memberDetail
                ) {
            array.add(member.getFirstName() + " " + member.getLastName());
        }
        ArrayAdapter aa = new ArrayAdapter(homeActivity, android.R.layout.simple_spinner_dropdown_item, array);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        familyMemberSpinner.setAdapter(aa);
    }

    private class UploadDetails extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            //super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(homeActivity);
            pDialog.setMessage("Placing order... ");
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

            int imageId = 0;

            List<NameValuePair> params = new ArrayList<>(5);
            params.add(new BasicNameValuePair("orderDescription", orderDescription));
            params.add(new BasicNameValuePair("orderType", orderType));
            params.add(new BasicNameValuePair("orderFulfillDate", orderFulfillDate));
            params.add(new BasicNameValuePair("memberId", "" + memberId));
            params.add(new BasicNameValuePair("prescriptionImageId", "" + imageId));
            jsonStr = sh.makeServiceCall(ServiceURL.Order, ServiceHandler.POST, params);
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
        protected void onPostExecute(Void resultt) {
            super.onPostExecute(resultt);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            if (status.equals("1")) {
                Toast.makeText(homeActivity, "Order placed successfully", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(homeActivity, "Error placing order - Please call us toll free 1-800-3000-6588 to place order", Toast.LENGTH_LONG).show();

            }

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
                showFamilyMembers();
            }

        }


    }
}

