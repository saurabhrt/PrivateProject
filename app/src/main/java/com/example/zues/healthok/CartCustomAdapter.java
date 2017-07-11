package com.example.zues.healthok;

/**
 * Created by zUeS on 28-06-2015.
 */

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zues.healthok.util.ServiceHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class CartCustomAdapter extends BaseAdapter {
    private static ArrayList<CartProduct> searchArrayList;

    SessionManager session;
    String jsonStr;
    JSONObject result;
    String status;
    String url;
    String medid;
    Context context;
    private LayoutInflater mInflater;

    public CartCustomAdapter(Context context, ArrayList<CartProduct> results) {
        searchArrayList = results;
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    public int getCount() {
        return searchArrayList.size();
    }

    public Object getItem(int position) {
        return searchArrayList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.cartproductslist, null);
            holder = new ViewHolder();
            holder.txtName = convertView.findViewById(R.id.name);
            holder.txtCityState = convertView
                    .findViewById(R.id.cityState);
            holder.txtPhone = convertView.findViewById(R.id.phone);

            holder.deletebtn = convertView.findViewById(R.id.remove);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtName.setText(searchArrayList.get(position).getName());
        holder.txtCityState.setText(searchArrayList.get(position)
                .getCityState());
        holder.txtPhone.setText(searchArrayList.get(position).getPhone());
        session = new SessionManager(context);

        HashMap<String, String> user = session.getUserDetails();
        String username = user.get(SessionManager.KEY_USERNAME);

        holder.deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CartProduct item = new CartProduct();
                item = searchArrayList.get(position);
                medid = item.getMedicineId();
                String pos = Integer.toString(position);
                url = "buffer/deobrat811@gmail.com/" + medid;
                new GetContacts().execute();

                searchArrayList.remove(position);
                notifyDataSetChanged();

            }
        });

        return convertView;
    }

    static class ViewHolder {
        TextView txtName;
        TextView txtCityState;
        TextView txtPhone;
        Button deletebtn;
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

            Toast.makeText(context, medid, Toast.LENGTH_LONG).show();

        }

    }


}