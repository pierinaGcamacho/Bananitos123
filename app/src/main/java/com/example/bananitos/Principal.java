package com.example.bananitos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class Principal extends AppCompatActivity {

    private CardView cvOpcion1, cvOpcion2;
    private ArrayList arrayDates;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        cvOpcion1 = findViewById(R.id.opcion1);
        cvOpcion1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Principal.this, TableData.class);
                startActivity(intent);
            }
        });

        cvOpcion2 = findViewById(R.id.opcion2);
        cvOpcion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Principal.this, RegistroDatos.class);
                startActivity(intent);
            }
        });
    }
}