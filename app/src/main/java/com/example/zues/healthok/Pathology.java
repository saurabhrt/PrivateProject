package com.example.zues.healthok;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zues.healthok.model.User;
import com.example.zues.healthok.util.ServiceHandler;
import com.example.zues.healthok.util.ServiceURL;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Pathology extends Activity implements AdapterView.OnItemSelectedListener {


    static final int CAM_REQUEST = 1;
    SessionManager sessionManager;
    ProgressDialog progressDialog;
    User user = null;
    String jsonStr;
    JSONObject result = null;
    String status = "-5";
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    TextView myText;
    int i = 1;
    ImageView camera_button;
    ImageView captured_image;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pathology);
        spinner = findViewById(R.id.Test_spinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.spinner_string, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }


    private File getFile() {
        File folder = new File("sdcard/cam_app");
        if (!folder.exists()) {
            folder.mkdir();
        }

        File imagefile = new File(folder, "cam.jpg");
        return imagefile;

    }


    public void onClickCaptuteButton() {

        captured_image = findViewById(R.id.captured_image);
        camera_button = findViewById(R.id.camera_button);
        camera_button.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = getFile();
                camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(camera_intent, CAM_REQUEST);

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        myText = (TextView) view;


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        i = -1;
    }

    // inputting date , photo and item selected
    public void Submit() {


        new SubmitRequest().execute();
    }


    private class SubmitRequest extends AsyncTask<Void, Void, Void>

    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            progressDialog = new ProgressDialog(Pathology.this);
            progressDialog.setMessage("Creating Request");
            progressDialog.setCancelable(false);
            progressDialog.show();
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);


        }


        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();


            List<NameValuePair> params = new ArrayList<>(2);

            params.add(new BasicNameValuePair("loginid", "username"));
            params.add(new BasicNameValuePair("password", "password"));
            jsonStr = sh.makeServiceCall(ServiceURL.Login, ServiceHandler.POST, params);


            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    result = new JSONObject(jsonStr);
                    status = result.getString("UserId");
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
            if (progressDialog.isShowing())
                progressDialog.dismiss();
            if (status.equals("-1")) {
                Toast.makeText(getApplicationContext(), "Attach Prescription \n or Select Test and \n Select Date", Toast.LENGTH_LONG).show();
            } else {

                user = new User(jsonStr);
                sessionManager.createLoginSession(user);


                Intent intent = new Intent(getApplicationContext(), HomePage.class);
                startActivity(intent);
            }

        }

    }

}

