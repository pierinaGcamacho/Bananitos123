package com.example.bananitos;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;

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
}
