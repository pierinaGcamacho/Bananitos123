package com.example.bananitos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Driver;
import java.sql.DriverManager;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class RegistroDatos extends AppCompatActivity {

    private EditText etEvaluador;
    private EditText etLugar;
    private Button btnRegistrar;
    private ConnectionDatabase connectionDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(getApplicationContext(), ReportePlagas.class);
        setContentView(R.layout.activity_registro_datos);
        etEvaluador =findViewById(R.id.etEvaluador);
        etLugar = findViewById(R.id.etLugar);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        connectionDatabase = new ConnectionDatabase(getApplicationContext());
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String evaluador = etEvaluador.getText().toString();
                String lugar = etLugar.getText().toString();
                if (evaluador.isEmpty() || lugar.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Complete el formulario", Toast.LENGTH_SHORT).show();
                    return;
                }
                intent.putExtra("evaluador",evaluador);
                intent.putExtra("ubicacion",lugar);
                startActivity(intent);
            }
        });

    }



}