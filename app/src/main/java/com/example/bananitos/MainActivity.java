package com.example.bananitos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.sql.Connection;
import java.sql.DriverManager;

public class MainActivity extends AppCompatActivity {

    Handler h= new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(), Principal.class);
                startActivity(i);
                finish();
            }
        }, 2000);
    }
}