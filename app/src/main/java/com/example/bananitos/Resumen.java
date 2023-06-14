package com.example.bananitos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class Resumen extends AppCompatActivity {

    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen);


        tableLayout = findViewById(R.id.tableLayout);

        Intent intent = getIntent();
        int plantNumberIzquierdo = intent.getIntExtra("plantNumberIzquierdo", 0);

        // Crear la matriz con 4 fila y el número de plantas ingresado
        String[][] matriz = new String[4][plantNumberIzquierdo];

        // Llenar la matriz con los valores deseados
        for (int i = 0; i < matriz[0].length; i++) {
            matriz[0][i] = "P" + (i + 1);
        }

        // Mostrar la matriz en una tabla
        for (int i = 0; i < matriz.length; i++) {
            TableRow row = new TableRow(this);
            for (int j = 0; j < matriz[i].length; j++) {
                TextView textView = new TextView(this);
                textView.setText(matriz[i][j]);
                row.addView(textView);
            }
            tableLayout.addView(row);
        }

    }



}