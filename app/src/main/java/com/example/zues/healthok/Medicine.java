package com.example.zues.healthok;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.zues.healthok.model.OrderBase;
import com.example.zues.healthok.model.OrderType;
import com.example.zues.healthok.util.ServiceHandler;
import com.example.zues.healthok.util.ServiceURL;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Raghuveer on 05/03/2016.
 */
public class Medicine extends AppCompatActivity {

    static final int CAM_REQUEST = 1;
    static final int GALLERY_REQUEST = 2;
    // URL to get contacts JSON
    private static int ERROR = -1;
    int status = ERROR;
    String jsonStr;
    // contacts JSONArray
    JSONObject result = null;
    ImageView camera_button;
    ImageView viewimage;
    OrderBase orderBase = null;
    String picturePath = null;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medicine);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        //   myToolbar.setTitle("Medicine");
        setSupportActionBar(myToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

//        camera_button=(ImageView) findViewById(R.id.cameraButton);
//        viewimage=(ImageView) findViewById(R.id.uploaded_Image);
//        camera_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View V) {
//                selectImage();
//            }

//        });

    }

    public void selectImage() {
        final CharSequence[] options = {"Take Photo", "Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(Medicine.this);
        builder.setTitle("Attach Prescription");

        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    File file = new File(android.os.Environment.getExternalStorageDirectory(), "x.jpg");
                    startActivityForResult(intent, CAM_REQUEST);
                } else if (options[item].equals("Gallery")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, GALLERY_REQUEST);
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }

            }
        });
        builder.show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CAM_REQUEST) {
                try {
                    Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
                    viewimage.setImageBitmap(thumbnail);

                    File destination = new File(Environment.getExternalStorageDirectory(),
                            System.currentTimeMillis() + ".jpg");
                    FileOutputStream fo;
                    destination.createNewFile();
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();
                    picturePath = destination.getPath();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (requestCode == GALLERY_REQUEST) {

                Uri selectedImage = data.getData();
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                picturePath = c.getString(columnIndex);
                c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                Log.w("path of image..", picturePath + "");
                viewimage.setImageBitmap(thumbnail);
//                ServiceHandler sh = new ServiceHandler();
//                sh.uploadFIle(picturePath);
            }
        }
    }


    public void placeOrder(View view) {

        orderBase = new OrderBase();
        orderBase.setOrderType(OrderType.MEDICINE);
        orderBase.setOrderDescription(findViewById(R.id.medicineDescription).toString());
//        DatePicker datePicker = ((DatePicker)findViewById(R.id.medicineNeededBy));
//orderBase.setOrderFulfillDate( new Date(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth()));
    }


    public void testevent(View view) {
        // do nothing
    }

    private class SubmitOrder extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(Medicine.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();


        }


        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();

// if picturePath is not null then an image is uploaded. Upload this and get imageId before saving Order

//            orderBase.setImageId(sh.uploadFile(picturePath));

            List<NameValuePair> params = new ArrayList<>(2);
            params.add(new BasicNameValuePair("orderDescription", orderBase.getOrderDescription()));
            params.add(new BasicNameValuePair("orderType", orderBase.getOrderType().toString()));
            params.add(new BasicNameValuePair("orderFulfillDate", orderBase.getOrderFulfillDate().toString()));
            params.add(new BasicNameValuePair("userId", "1"));


            jsonStr = sh.makeServiceCall(ServiceURL.Order, ServiceHandler.POST, params);


//jsonStr=sh.makeServiceCall("medicineorder/1",ServiceHandler.GET);
//jsonStr = "not called";
            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    result = new JSONObject(jsonStr);
                    status = Integer.parseInt(result.getString("userId"));
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
            if (status == ERROR) {
                Toast.makeText(getApplicationContext(), "WRONG Username / Password", Toast.LENGTH_LONG).show();
            } else if (status == -1) {
                Toast.makeText(getApplicationContext(), "Unable to reach server try again...", Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent(getApplicationContext(), HomePage.class);
                startActivity(intent);
            }

        }

    }


}





