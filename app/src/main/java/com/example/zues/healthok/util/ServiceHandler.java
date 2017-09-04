package com.example.zues.healthok.util;


import android.util.Log;

import com.google.gson.Gson;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

import static android.content.ContentValues.TAG;


public class ServiceHandler {

    public final static int GET = 1;
    public final static int POST = 2;
    public static String urlbase = ServiceURL.Base;
    static String response = null;

    public ServiceHandler() {

    }

    /**
     * Making service call
     *
     * @url - url to make request
     * @method - http request method
     */
    public String makeServiceCall(String url, int method) {
        return this.makeServiceCall(url, method, null);
    }

    /**
     * Making service call
     *
     * @url - url to make request
     * @method - http request method
     * @params - http request params
     */
    public String makeServiceCall(String url, int method,
                                  List<NameValuePair> params) {
        String fullURL = urlbase + url;
        Log.d("URL:------->", fullURL);
        try {
            // http client
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpEntity httpEntity = null;
            HttpResponse httpResponse = null;

            // Checking http request method type
            if (method == POST) {
                HttpPost httpPost = new HttpPost(fullURL);
                // adding post params

                if (params != null) {
                    httpPost.setHeader("Content-Type", "application/json");
                    JSONObject obj = new JSONObject();

                    for (NameValuePair nameValuePair : params) {
                        obj.put(nameValuePair.getName(), nameValuePair.getValue());
                    }

                    StringEntity param = new StringEntity(obj.toString());

                    httpPost.setEntity(param);

                    httpResponse = httpClient.execute(httpPost);
                }

            } else if (method == GET) {
                // appending params to url
                if (params != null) {
                    String paramString = URLEncodedUtils
                            .format(params, "UTF-8");
                    fullURL += "?" + paramString;
                }
                HttpGet httpGet = new HttpGet(fullURL);

                httpResponse = httpClient.execute(httpGet);
            }
            httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                response = EntityUtils.toString(httpEntity);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return response;

    }


    /**
     * Making service call
     *
     * @url - url to make request
     * @method - http request method
     * @params - http request params
     */
    public String makeServiceCall(String url, int method,
                                  Object data) {
        String fullURL = urlbase + url;
        try {
            // http client
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpEntity httpEntity = null;
            HttpResponse httpResponse = null;

            // Checking http request method type
            if (method == POST) {
                HttpPost httpPost = new HttpPost(fullURL);
                // adding post params

                if (data != null) {
                    httpPost.setHeader("Content-Type", "application/json");

                    Gson gson = new Gson();

                    StringEntity param = new StringEntity(gson.toJson(data));

                    httpPost.setEntity(param);


                    httpResponse = httpClient.execute(httpPost);
                }

            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;

    }


    /**
     * Making service call
     *
     * @url - url to make request
     * @method - http request method
     * @params - http request params
     */
    public String makeServiceCall(String url,
                                  Object data) {
        String fullURL = urlbase + url;
        try {
            // http client
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpEntity httpEntity = null;
            HttpResponse httpResponse = null;

            HttpPost httpPost = new HttpPost(fullURL);
            // adding post params

            if (data != null) {
                httpPost.setHeader("Content-Type", "application/json");

                Gson gson = new Gson();

                StringEntity param = new StringEntity(gson.toJson(data));

                httpPost.setEntity(param);


                httpResponse = httpClient.execute(httpPost);
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;

    }
    public String makeServiceCall(String reqUrl) {
        String response = null;
        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertStreamToString(in);
        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
        return response;
    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }


    public int uploadFile(String filePath) {
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            File file = new File(filePath);
            HttpPost post = new HttpPost("http://localhost:8010/healthokapp/rest/files/uploaddocpic");

            MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
            entityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            entityBuilder.addBinaryBody("file", file);
            entityBuilder.addTextBody("descrip", filePath);
            // add more key/value pairs here as needed

            HttpEntity entity = entityBuilder.build();
            post.setEntity(entity);

            HttpResponse response = client.execute(post);
            HttpEntity httpEntity = response.getEntity();

            Log.v("result", EntityUtils.toString(httpEntity));

            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    public String uploadFile(String url, String filePath) {
        try {
            String fullURL = urlbase + url;
            DefaultHttpClient client = new DefaultHttpClient();
            File file = new File(filePath);
//            HttpPost post = new HttpPost("http://localhost:8010/healthokapp/rest/files/uploaddocpic");
            HttpPost post = new HttpPost(fullURL);

            MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
            entityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            entityBuilder.addBinaryBody("file", file);
            entityBuilder.addTextBody("descrip", filePath);
            // add more key/value pairs here as needed

            HttpEntity entity = entityBuilder.build();
            post.setEntity(entity);

            HttpResponse response = client.execute(post);
            HttpEntity httpEntity = response.getEntity();
            String result = EntityUtils.toString(httpEntity);
            Log.v("result", result);

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

}
