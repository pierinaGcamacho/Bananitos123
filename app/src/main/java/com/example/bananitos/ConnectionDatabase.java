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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ConnectionDatabase {
    private static final String URL = "https://bananitosapp.fly.dev/";
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
    public void savePlant(JSONObject dataObject, String evaluador, String ubicacion){
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
                }
                ){

            @Override
            public byte[] getBody() {
                byte [] body = new byte[0];
                try {
                    String strDataMap = dataObject.toString();
                    JSONObject data2send = new JSONObject();
                    data2send.put("evaluador", evaluador);
                    data2send.put("ubicacion",ubicacion);
                    data2send.put("body", dataObject);
                    body = data2send.toString().getBytes("UTF-8");
                }catch (Exception errr){
                    System.out.println(errr);
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
    public void saveRegister(String evaluador, String ubicacion){
        String url = URL+"register";
        int idRegister = 0;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int idRegister = response.getInt("message");

                        }catch (JSONException err){
                            System.err.println(err);
                        }
                        return;
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error);
                    }
                }
        ){
            @Override
            public byte[] getBody() {
                byte [] body = new byte[0];
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("evaluador",evaluador);
                    jsonObject.put("ubicacion",ubicacion);
                    body = jsonObject.toString().getBytes("UTF-8");
                }catch (Exception err){

                }
                return  body;
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
