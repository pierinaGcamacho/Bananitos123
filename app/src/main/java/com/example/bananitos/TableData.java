package com.example.bananitos;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TableData extends AppCompatActivity {
    private static final String URL = "http://192.168.0.115:8080/";
    private Button buttonBackHome;
    private AutoCompleteTextView autoCompleteTxt;
    private ConnectionDatabase conn;
    private RecyclerView containerRegister;
    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private RecyclerView.Adapter adapter;
    public ArrayList<Registro> listRegistro;
    private String fechaCreacion = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_data);

        containerRegister = findViewById(R.id.containerRegistroData);

        listRegistro = new ArrayList<>();
        adapter = new RegisterAdapter(getApplicationContext(), listRegistro);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(containerRegister.getContext(),linearLayoutManager.getOrientation());

        containerRegister.addItemDecoration(dividerItemDecoration);
        containerRegister.setHasFixedSize(true);
        containerRegister.setLayoutManager(linearLayoutManager);
        containerRegister.setAdapter(adapter);

        autoCompleteTxt = findViewById(R.id.text_autocomplete);

        conn = new ConnectionDatabase(getApplicationContext());
        conn.fetchDatesRegister(autoCompleteTxt);

        buttonBackHome = findViewById(R.id.button_back_home);

        buttonBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TableData.this, Principal.class);
                startActivity(intent);
            }
        });

        if (fechaCreacion.isEmpty()){
            long milis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(milis);
            fechaCreacion = date.toString();
            getRegisters(fechaCreacion);
        }

    }
    public void getRegisters(String fechaCreacion){
        String url = URL+"register/";
        JsonObjectRequest jsonObject = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            JSONArray valores = response.getJSONArray("valores");
                            if (valores.length() == 0){
                                Toast.makeText(getApplicationContext(), "No existe data",Toast.LENGTH_SHORT).show();
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
                                registro.setTotalAdultos(Integer.parseInt(jsonAdultos.getString("Total")));
                                registro.setMaxAdultos(jsonAdultos.getInt("Maximo"));
                                registro.setMinAdultos(jsonAdultos.getInt("Minimo"));
                                registro.setPromAdultos(jsonAdultos.getString("Promedio"));

                                JSONObject jsonNinfas = valores_tipo.getJSONObject(1);
                                registro.setTotalNinfas(Integer.parseInt(jsonNinfas.getString("Total")));
                                registro.setMaxNinfas(jsonNinfas.getInt("Maximo"));
                                registro.setMinNinfas(jsonNinfas.getInt("Minimo"));
                                registro.setPromNinfas(jsonNinfas.getString("Promedio"));
                                listRegistro.add(registro);
                            }
                            adapter.notifyDataSetChanged();
                        }catch (Exception err){
                            System.out.println(err);
                        }
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
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObject);
    }
}
