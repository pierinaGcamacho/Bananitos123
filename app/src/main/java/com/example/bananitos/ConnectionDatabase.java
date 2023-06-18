package com.example.bananitos;

import android.content.Context;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ConnectionDatabase {
    private static final String URL = "http://192.168.0.117:8080/";
    private RequestQueue requestQueue;
    public ConnectionDatabase(Context context){
        requestQueue = Volley.newRequestQueue(context);
    }
    public void fetchLocations(){
        String url = URL + "location";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.err.println(error);
                    }
                });

        requestQueue.add(request);
    }
    public void savePlant(JSONObject dataObject){
        String url = URL+"plant";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.err.println(error);
                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> data2Send = new HashMap<>();
                data2Send.put("data",dataObject.toString());
                data2Send.put("nombre","Pierina");
                return data2Send;
            }

            @Override
            public byte[] getBody() {
                byte [] body = new byte[0];
                try {
                    String strDataMap = dataObject.toString();
                    HashMap<String, String> data2Send = new HashMap<>();
                    data2Send.put("body", strDataMap);
                    body = data2Send.toString().getBytes("UTF-8");
                }catch (Exception errr){

                }
                return body;
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String,String> header = new HashMap<>();
                header.put("Content-Type","application/x-www-form-urlencoded");
                return header;
            }
        };

        requestQueue.add(request);
    }
}
