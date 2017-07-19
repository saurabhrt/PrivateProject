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

import com.example.zues.healthok.model.Order;
import com.example.zues.healthok.model.User;
import com.example.zues.healthok.util.ServiceHandler;
import com.example.zues.healthok.util.ServiceURL;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class MyOrdersFragment extends Fragment {
    JSONArray result = null;
    View inflate;
    private SessionManager sessionManager;
    private User user;
    private HomeActivity homeActivity;
    private ProgressDialog pDialog;
    private String jsonStr;
    private ArrayList<Order> orders;


    public MyOrdersFragment() {
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
        inflate = inflater.inflate(R.layout.fragment_my_orders, container, false);
        if (orders.size() > 0)
            showOrders();
        return inflate;
    }

    private void showOrders() {
        TableLayout tableLayout = inflate.findViewById(R.id.orderTable);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");

        for (int i = 0; i < orders.size(); i++) {
            final Order order = orders.get(i);
            View view = LayoutInflater.from(getContext()).inflate(R.layout.order_detail_row, null);
            if (i % 2 != 0)
                view.setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.border));
            TextView orderNumberText = view.findViewById(R.id.orderNumberText);
            orderNumberText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showOneOrder(order);
                }
            });
            orderNumberText.setText("HO6588" + (5000 - 0 + order.getOrderId()));
            ((TextView) view.findViewById(R.id.orderDateText)).setText(sdf.format(order.getOrderDate()));
            ((TextView) view.findViewById(R.id.nameText)).setText(user.getFirstName() + " " + user.getLastName());
            ((TextView) view.findViewById(R.id.orderTypeText)).setText("" + order.getOrderType());
            ((TextView) view.findViewById(R.id.descriptionText)).setText(order.getOrderDescription());
            ((TextView) view.findViewById(R.id.statusText)).setText(order.getOrderStatusType().toString());
            ((TextView) view.findViewById(R.id.neededByText)).setText(sdf.format(order.getOrderFulfillDate()));
            ((TextView) view.findViewById(R.id.completedOnText)).setText(sdf.format(order.getOrderCompletionDate()));
            ((TextView) view.findViewById(R.id.totalCostText)).setText("" + order.getTotalCost());
            ((TextView) view.findViewById(R.id.discountText)).setText("" + order.getDiscount());
            ((TextView) view.findViewById(R.id.cashBackBonusText)).setText("" + order.getCashbackBonusApplied());
            ((TextView) view.findViewById(R.id.netAmountText)).setText("" + order.getNetAmount());
            tableLayout.addView(view);
        }

    }

    public void showOneOrder(Order order) {
        //@TODO show specific order details
        homeActivity.orderForOtherFragments = order;
        switch (order.getOrderType()) {
            case MEDICINE:
                homeActivity.swapFragment(new MedicineOrderDetailsFragment(), true);
                break;
            //TODO Implement others
            case LAB:
                homeActivity.swapFragment(new LabOrderDetailsFragment(), true);
                break;
            case APPT:
                break;
            case AMBULANCE:
                break;
            case NURSE:
                break;
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
            String url = ServiceURL.OrderDetails + user.getUserId();
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
}