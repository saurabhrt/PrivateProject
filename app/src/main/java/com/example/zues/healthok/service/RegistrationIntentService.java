package com.example.zues.healthok.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.zues.healthok.R;
import com.example.zues.healthok.SessionManager;
import com.example.zues.healthok.util.ServiceHandler;
import com.example.zues.healthok.util.ServiceURL;
import com.example.zues.healthok.util.StatusCode;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abhay-Jaiswal on 3/19/2016.
 */
public class RegistrationIntentService extends IntentService {

    public static final String SENT_TOKEN_TO_SERVER = "sentTokenToServer";
    public static final String GCM_TOKEN = "gcmToken";
    // abbreviated tag name
    private static final String TAG = "RegIntentService";

    public RegistrationIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // Make a call to Instance API
        InstanceID instanceID = InstanceID.getInstance(this);
//        InstanceID instanceID = InstanceID.getInstance(getApplicationContext());
        String senderId = getResources().getString(R.string.gcm_defaultSenderId);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        try {
            // request token that will be used by the server to send push notifications
            String token = instanceID.getToken(senderId, GoogleCloudMessaging.INSTANCE_ID_SCOPE);
            Log.d(TAG, "GCM Registration Token: " + token);

            // save token
            new SessionManager(getApplicationContext()).setGCMToken(token);

            // pass along this data
            sendRegistrationToServer(token);
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG, "Failed to complete token refresh", e);
            // If an exception happens while fetching the new token or updating our registration data
            // on a third-party server, this ensures that we'll attempt the update at a later time.
            new SessionManager(getApplicationContext()).setSentTokenToServer(false);

        }
    }

    private void sendRegistrationToServer(String token) {
        // call service to save token to server
        StatusCode result = StatusCode.UnknownError;
        SessionManager sessionManager = new SessionManager(getApplicationContext());
        int userId = sessionManager.getUser().getUserId();
        List<NameValuePair> params = new ArrayList<>(2);

        params.add(new BasicNameValuePair("userid", Integer.toOctalString(userId)));
        params.add(new BasicNameValuePair("token", token));
        ServiceHandler serviceHandler = new ServiceHandler();
        String response = serviceHandler.makeServiceCall(ServiceURL.GCMRegister, ServiceHandler.POST, params);

        try {
            JSONObject resultJSON = new JSONObject(response);
            if (resultJSON.getInt("result") >= 0)
                result = StatusCode.Success;
        } catch (Exception e) {


        }

        Log.d(TAG, "Token registration successful");
        //int userid = SessionManager.
        // if registration sent was successful, store a boolean that indicates whether the generated token has been sent to server
        if (result == StatusCode.Success) {
            new SessionManager(getApplicationContext()).setSentTokenToServer(true);
        } else {
            new SessionManager(getApplicationContext()).setSentTokenToServer(false);
        }


    }
}