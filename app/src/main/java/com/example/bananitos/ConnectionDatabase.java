package com.example.bananitos;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ConnectionDatabase {
    // http://192.168.0.115:8080/
    private static final String URL = "http://192.168.0.115:8080/";
    private RequestQueue requestQueue;
    private Context contextApplication;
    ArrayList<String> arrDates;
    public ConnectionDatabase(Context context){
        this.contextApplication = context;
        requestQueue = Volley.newRequestQueue(context);
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
    public void fetchDatesRegister(AutoCompleteTextView autoCompleteTextView){
        String url = URL + "register/dates";
        ArrayList<String> registroList = new ArrayList<>();
        ArrayAdapter adapterRegistro = new ArrayAdapter(this.contextApplication, R.layout.list_items, registroList);
        AutoCompleteTextView auto = autoCompleteTextView;
        auto.setAdapter(adapterRegistro);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("message");
                            for (int i=0; i<jsonArray.length(); i++){
                                Registro registro = new Registro();
                                registro.setFechaCreacion(jsonArray.get(i).toString());
                                registroList.add(jsonArray.get(i).toString());
                            }
                            adapterRegistro.notifyDataSetChanged();
                        }catch (Exception err){
                            System.out.println(err );
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error);
                    }
        });
        requestQueue.add(request);
    }
    public void fetchDataRegistroByFecha(RecyclerView container, String fechaCreacion){
        ArrayList <Registro> RegisterList = new ArrayList<>();
        RegisterAdapter registerAdapter = new RegisterAdapter(contextApplication, RegisterList);
        String url = URL+"register/";
        JsonObjectRequest jsonObject = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            JSONArray valores = response.getJSONArray("valores");
                            if (valores.length() == 0){
                                Toast.makeText(contextApplication, "No existe data",Toast.LENGTH_SHORT).show();
                                return;
                            }
                            for (int i=0; i<valores.length(); i++){
                                JSONObject json_valores = valores.getJSONObject(i);
                                String ubicacion = json_valores.getString("ubicacion");
                                Registro registro = new Registro(ubicacion);
                                registro.setNombreAdultos("Adultos");
                                registro.setNombreNinfas("Ninfas");

                                JSONArray valores_tipo = json_valores.getJSONArray("valores_tipo");
                                JSONObject jsonAdultos = valores_tipo.getJSONObject(0);
                                System.out.println(jsonAdultos);
                                registro.setTotalAdultos(Integer.parseInt(jsonAdultos.getString("Total")));
                                registro.setMaxAdultos(jsonAdultos.getInt("Maximo"));
                                registro.setMinAdultos(jsonAdultos.getInt("Minimo"));

                                JSONObject jsonNinfas = valores_tipo.getJSONObject(1);
                                registro.setTotalNinfas(Integer.parseInt(jsonNinfas.getString("Total")));
                                registro.setMaxNinfas(jsonNinfas.getInt("Maximo"));
                                registro.setMinNinfas(jsonNinfas.getInt("Minimo"));

                                RegisterList.add(registro);
                            }
                        }catch (Exception err){
                            System.out.println(err);

                        }
                        registerAdapter.notifyDataSetChanged();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error);
                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> headers = new HashMap<>();
                headers.put("fechaCreacion", fechaCreacion.toString());
                return  headers;
            }
        };
        requestQueue.add(jsonObject);
    }
}
