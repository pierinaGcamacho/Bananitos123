package com.example.bananitos;

import androidx.appcompat.app.AppCompatActivity;

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
    private EditText etPlantas;
    private Button btnRegistrar;
    public static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    public static final String DATABASE = "basedatos1";
    public static final String username = "a78rv36twvfy7g2dy8l6";
    public static final String host = "aws.connect.psdb.cloud";
    public static final String PASSWORD = "pscale_pw_tqAfJZhZ5PQGzEGwcjuUFuOeKyIgeENbSBR3Lq9CxOz";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_datos);
        etEvaluador =findViewById(R.id.etEvaluador);
        etLugar = findViewById(R.id.etLugar);
        etPlantas = findViewById(R.id.etPlantas);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        //10.0.2.2
        btnRegistrar.setOnClickListener(this::fetchData);
        //"https://192.168.0.11:80/basedatos_1/insertar_registro.php"
    }
    private void fetchData(View view){
        try {
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://aws.connect.psdb.cloud/basedatos1?sslMode=VERIFY_IDENTITY",
                    "a78rv36twvfy7g2dy8l6",
                    "pscale_pw_tqAfJZhZ5PQGzEGwcjuUFuOeKyIgeENbSBR3Lq9CxOz");

            System.out.println("Connected !");
            Toast.makeText(RegistroDatos.this, "Connected", Toast.LENGTH_SHORT).show();
            Statement st = conn.createStatement();
            StringBuffer stringBuffer = new StringBuffer();
            ResultSet resultSet = st.executeQuery("SELECT * FROM registro;");

            while(resultSet.next()){
                String nombre = resultSet.getString("nombreEvaluador");
                System.out.println(nombre);
            }
        }catch (Exception err){
            System.out.println(err);
        }
    }
    private void ejecutarServicio(String URL){
        int tiempoEspera = 50000; // Tiempo de espera en milisegundos (30 segundos)
        long startTime = System.currentTimeMillis();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                long endTime = System.currentTimeMillis();
                long totalTime = endTime - startTime;
                Toast.makeText(getApplicationContext(), "Operación exitosa. Tiempo de conexión: " + totalTime + " milisegundos", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
    }){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String, String> parametros=new HashMap<String,String>();
                parametros.put("Evaluador", etEvaluador.getText().toString());
                parametros.put("Lugar", etLugar.getText().toString());

                int plantaValue = Integer.parseInt(etPlantas.getText().toString());
                parametros.put("Planta", String.valueOf(plantaValue));

                return parametros;
            }
        };
        // Configurar la política de reintento
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                tiempoEspera,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}