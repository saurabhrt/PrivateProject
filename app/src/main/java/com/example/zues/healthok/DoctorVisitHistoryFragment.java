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
import android.widget.TableRow;
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


public class DoctorVisitHistoryFragment extends Fragment {
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


    public DoctorVisitHistoryFragment() {
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
        inflate = inflater.inflate(R.layout.fragment_doctor_visit_history, container, false);
        return inflate;
    }

    private void showOrders() {
        TableLayout tableLayout = inflate.findViewById(R.id.doctorVisitHistoryTable);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");

        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            if (order == null)
                continue;
            View view = LayoutInflater.from(getContext()).inflate(R.layout.doctor_visit_history_row, null);
            if (i % 2 != 0)
                view.setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.border));
            ((TextView) view.findViewById(R.id.familyMemberText)).setText("");
            if (order.getDoctor() != null) {
                ((TextView) view.findViewById(R.id.doctorNameText)).setText(order.getDoctor().getFirstName()
                        + " " + order.getDoctor().getLastName());
                ((TextView) view.findViewById(R.id.specialityText)).setText(order.getDoctor().getSpeciality());
                ((TextView) view.findViewById(R.id.appointmentDateText)).setText(sdf.format(
                        order.getDoctorAppointment().getAppointmentDate()));
            }
            ((TextView) view.findViewById(R.id.reasonText)).setText(order.getOrderDescription());
            ((TextView) view.findViewById(R.id.statusText)).setText(order.getOrderStatusType().toString());
            int prescriptionImageId = 0;
            if (order.getDoctorAppointment() != null)
                prescriptionImageId = order.getDoctorAppointment().getPrescriptionImageId();
            if (prescriptionImageId != 0) {
                prescriptionImageView = inflate.findViewById(R.id.prescriptionImageView);
                prescriptionImageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
                prescriptionImageURL = ServiceURL.ImageDisplayPath + prescriptionImageId;
                new DownloadImage().execute();
            }
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
            String url = ServiceURL.UserAppointmentsPath + user.getUserId();
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

    private class DownloadImage extends AsyncTask<String, Void, Bitmap> {

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
        protected Bitmap doInBackground(String... URL) {

            Bitmap bitmap = null;
            try {
                // Download Image from URL
                InputStream input = new java.net.URL(prescriptionImageURL).openStream();
                // Decode Bitmap
                bitmap = BitmapFactory.decodeStream(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            // Set the bitmap into ImageView
            prescriptionImageView.setImageBitmap(result);
            // Close progressdialog
            pDialog.dismiss();
        }
    }
}
