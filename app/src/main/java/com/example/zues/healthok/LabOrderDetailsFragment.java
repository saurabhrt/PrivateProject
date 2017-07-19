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

import com.example.zues.healthok.model.LabOrder;
import com.example.zues.healthok.model.LabOrderDetail;
import com.example.zues.healthok.model.Order;
import com.example.zues.healthok.model.User;
import com.example.zues.healthok.util.ServiceHandler;
import com.example.zues.healthok.util.ServiceURL;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

//TODO implement failure of request in each fragment

public class LabOrderDetailsFragment extends Fragment {
    JSONObject result = null;
    private HomeActivity homeActivity;
    private SessionManager sessionManager;
    private User user;
    private Order order;
    private LabOrder labOrder;
    private View inflate;
    private ProgressDialog pDialog;
    private String jsonStr;
    private ArrayList<LabOrderDetail> lods;
    private ImageView prescriptionImageView;
    private String prescriptionImageURL = "";
    private ImageView reportImageView;
    private String reportImageURL = "";


    public LabOrderDetailsFragment() {
        // Required empty public constructor
        Log.d("LODF", "Creating");
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_lab_order_details, container, false);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
        ((TextView) inflate.findViewById(R.id.orderDateText)).setText(sdf.format(order.getOrderDate()));
        ((TextView) inflate.findViewById(R.id.nameText)).setText(user.getFirstName() + " " + user.getLastName());
        ((TextView) inflate.findViewById(R.id.orderTypeText)).setText("" + order.getOrderType());
        ((TextView) inflate.findViewById(R.id.descriptionText)).setText(order.getOrderDescription());
        ((TextView) inflate.findViewById(R.id.statusText)).setText(order.getOrderStatusType().toString());
        ((TextView) inflate.findViewById(R.id.neededByText)).setText(sdf.format(order.getOrderFulfillDate()));
        ((TextView) inflate.findViewById(R.id.completedOnText)).setText(sdf.format(order.getOrderCompletionDate()));
        ((TextView) inflate.findViewById(R.id.totalCostText)).setText("" + order.getTotalCost());
        ((TextView) inflate.findViewById(R.id.discountText)).setText("" + order.getDiscount());
        ((TextView) inflate.findViewById(R.id.cashBackBonusText)).setText("" + order.getCashbackBonusApplied());
        ((TextView) inflate.findViewById(R.id.netAmountText)).setText("" + order.getNetAmount());
        return inflate;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        sessionManager = new SessionManager(context);
        homeActivity = (HomeActivity) getActivity();
        user = sessionManager.getUser();
        order = homeActivity.orderForOtherFragments;
        new GetLabOrderDetails().execute();
    }


    private void showLabOrderDetails() {
        TableLayout labOrderTable = inflate.findViewById(R.id.labOrderTable);
        for (int i = 0; i < lods.size(); i++) {
            LabOrderDetail lod = lods.get(i);
            View view = LayoutInflater.from(getContext()).inflate(R.layout.lab_order_detail_row, null);
            if (i % 2 != 0)
                view.setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.border));
            ((TextView) view.findViewById(R.id.testNameText)).setText(lod.getTestname());
            ((TextView) view.findViewById(R.id.priceText)).setText("" + lod.getPrice());
            labOrderTable.addView(view);
        }
        if (labOrder.getPrescriptionImageId() > 0) {
            inflate.findViewById(R.id.prescriptionImageView).setLayoutParams(
                    new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            prescriptionImageView = inflate.findViewById(R.id.prescriptionImageView);
            prescriptionImageURL = ServiceURL.ImageDisplayPath + labOrder.getPrescriptionImageId();
        }
        if (labOrder.getLabResultImageId() > 0) {
            reportImageView = inflate.findViewById(R.id.reportImageView);
            reportImageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            reportImageURL = ServiceURL.ImageDisplayPath + labOrder.getLabResultImageId();
        }
        new DownloadImage().execute();

    }


    private class GetLabOrderDetails extends AsyncTask<Void, Void, Void> {

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
            String url = ServiceURL.Order + "/" + order.getOrderId();
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
                Order order = Order.fromJSON(jsonStr);
                if (order != null) {
                    labOrder = order.getLabOrder();
                    if (labOrder != null) {
                        lods = labOrder.getLabOrderDetail();
                        showLabOrderDetails();
                    }
                }
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
                if (reportImageURL != "") {
                    // Download Image from URL
                    InputStream input = new java.net.URL(reportImageURL).openStream();
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
            if (prescriptionImageURL != "")
                prescriptionImageView.setImageBitmap(result[0]);
            if (reportImageURL != "")
                reportImageView.setImageBitmap(result[0]);
            // Close progressdialog
            pDialog.dismiss();
        }
    }


}

