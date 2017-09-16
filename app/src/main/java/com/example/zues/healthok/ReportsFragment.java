package com.example.zues.healthok;


import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zues.healthok.model.Order;
import com.example.zues.healthok.model.User;
import com.example.zues.healthok.util.ServiceHandler;
import com.example.zues.healthok.util.ServiceURL;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class ReportsFragment extends Fragment {
    JSONArray result = null;
    View inflate;
    private SessionManager sessionManager;
    private User user;
    private HomeActivity homeActivity;
    private ProgressDialog pDialog;
    private String jsonStr;
    private ArrayList<Order> orders;
    private ImageView prescriptionImageView;
    private String prescriptionImageURL = "";
    private ImageView testReportImageView;
    private String testReportImageURL = "";


    public ReportsFragment() {
        // Required empty public constructor
        orders = new ArrayList<>();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        sessionManager = new SessionManager(context);
        homeActivity = (HomeActivity) getActivity();
        user = sessionManager.getUser();
        new GetOrderDetails().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_reports, container, false);
        return inflate;
    }

    private void showOrders() {
        TableLayout tableLayout = inflate.findViewById(R.id.reportTable);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");

        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            View view = LayoutInflater.from(getContext()).inflate(R.layout.report_row, null);
            if (i % 2 != 0)
                view.setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.border));
            ((TextView) view.findViewById(R.id.familyMemberText)).setText("");
            ((TextView) view.findViewById(R.id.testDescriptionTest)).setText(order.getOrderDescription());
            if (order.getOrderCompletionDate() != null)
            ((TextView) view.findViewById(R.id.testDateText)).setText(sdf.format(
                    order.getOrderCompletionDate()));
            ((TextView) view.findViewById(R.id.statusText)).setText(order.getOrderStatusType().toString());
            int prescriptionImageId = 0;
            if (order.getLabOrder() != null)
                prescriptionImageId = order.getLabOrder().getPrescriptionImageId();
            if (prescriptionImageId != 0) {
                prescriptionImageView = inflate.findViewById(R.id.prescriptionImageView);
                prescriptionImageURL = ServiceURL.ImageDisplayPath + prescriptionImageId;
            }
            int labResultImageId = 0;
            if (order.getLabOrder() != null)
                labResultImageId = order.getLabOrder().getLabResultImageId();
            if (labResultImageId != 0) {
                testReportImageView = inflate.findViewById(R.id.testReportImageView);
                testReportImageURL = ServiceURL.ImageDisplayPath + labResultImageId;
            }
            new DownloadImage().execute();
            tableLayout.addView(view);
        }

    }


    private class GetOrderDetails extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            //super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(homeActivity);
            pDialog.setMessage("Retrieving Order Details... ");
            // pDialog.setMax(16);
            pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

            pDialog.setCancelable(false);
            pDialog.show();


        }


        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();
            String url = ServiceURL.reportsPath + user.getUserId();
            // Making a request to url and getting response

            jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    result = new JSONArray(jsonStr);
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
                for (int i = 0; i < result.length(); i++) {
                    String jstr = null;
                    try {
                        jstr = result.getJSONObject(i).toString();
                        Log.d("jstr", jstr);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Order order = Order.fromJSON(jstr);
                    orders.add(order);
                }
                showOrders();

            }


        }

    }

    private class DownloadImage extends AsyncTask<String, Void, Bitmap[]> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            pDialog = new ProgressDialog(getActivity());
            // Set progressdialog title
            pDialog.setTitle("Downloading Prescription Image");
            // Set progressdialog message
            pDialog.setMessage("Loading...");
            pDialog.setIndeterminate(false);
            // Show progressdialog
            pDialog.show();
        }

        @Override
        protected Bitmap[] doInBackground(String... URL) {

            Bitmap bitmap[] = new Bitmap[2];
            try {
                if (prescriptionImageURL != "") {
                    // Download Image from URL
                    InputStream input = new java.net.URL(prescriptionImageURL).openStream();
                    // Decode Bitmap
                    bitmap[0] = BitmapFactory.decodeStream(input);
                }
                if (testReportImageURL != "") {
                    // Download Image from URL
                    InputStream input = new java.net.URL(testReportImageURL).openStream();
                    // Decode Bitmap
                    bitmap[1] = BitmapFactory.decodeStream(input);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result[]) {
            // Set the bitmap into ImageView
            if (prescriptionImageURL != "") {
                prescriptionImageView.setImageBitmap(result[0]);
                prescriptionImageView.setVisibility(View.VISIBLE);
            }
            if (testReportImageURL != "") {
                testReportImageView.setImageBitmap(result[0]);
                testReportImageView.setVisibility(View.VISIBLE);
            }
            // Close progressdialog
            pDialog.dismiss();
        }
    }
}
