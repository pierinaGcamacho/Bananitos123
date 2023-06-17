package com.example.bananitos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.sql.Connection;
import java.sql.DriverManager;

public class MainActivity extends AppCompatActivity {

    Handler h= new Handler();
    Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(), ReportePlagas.class);
                startActivity(i);
                finish();
            }
        }, 2000);
    }
    public void connectDatabase(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://aws.connect.psdb.cloud/basedatos1?sslMode=VERIFY_IDENTITY",
                    "ivjhdk1tzngqz08tw05m", "pscale_pw_an50MhjZqS4PWVPreY4sgxmuH2VdNXxjoBgooggG4hf");

            System.out.println("Connected !");
        }catch (Exception err){
            System.out.println(err);
        }

    }
}