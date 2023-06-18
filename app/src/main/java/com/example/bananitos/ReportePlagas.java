package com.example.bananitos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ReportePlagas extends AppCompatActivity {

    private Button generateFieldsButton,generateFieldsButtonCentro,generateFieldsButtonIzquierdo;
    private EditText plantNumberEditText,plantNumberEditTextCentro,plantNumberEditTextIzquierdo;
    private LinearLayout plantFieldsContainer,plantFieldsContainerCentro,plantFieldsContainerIzquierdo;

    private Button buttonGenerate;
    private ArrayList <EditText> ninfasDerecha, adultosDerecha, ninfasCentrop, adultosCentrop, ninfasIzquierda, adultosIzquierda;
    private ConnectionDatabase connectionDatabase ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte_plagas);

        EditText plantNumberEditText = findViewById(R.id.plantNumberEditText);
        Button generateFieldsButton = findViewById(R.id.generateFieldsButton);
        LinearLayout plantFieldsContainer = findViewById(R.id.plantFieldsContainer);

        EditText plantNumberEditTextCentro = findViewById(R.id.plantNumberEditTextCentro);
        Button generateFieldsButtonCentro = findViewById(R.id.generateFieldsButtonCentro);
        LinearLayout plantFieldsContainerCentro = findViewById(R.id.plantFieldsContainerCentro);

        EditText plantNumberEditTextIzquierdo = findViewById(R.id.plantNumberEditTextIzquierdo);
        Button generateFieldsButtonIzquierdo = findViewById(R.id.generateFieldsButtonIzquierdo);
        LinearLayout plantFieldsContainerIzquierdo = findViewById(R.id.plantFieldsContainerIzquierdo);

        Button buttonGenerate = findViewById(R.id.buttonGenerate);
        adultosDerecha = new ArrayList<>();
        adultosCentrop = new ArrayList<>();
        adultosIzquierda = new ArrayList<>();

        ninfasDerecha = new ArrayList<>();
        ninfasCentrop = new ArrayList<>();
        ninfasIzquierda = new ArrayList<>();

        generateFieldsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plantNumberString = plantNumberEditText.getText().toString();
                // Verificar si se ingresó un número válido
                if (!plantNumberString.isEmpty()) {
                    int plantNumber = Integer.parseInt(plantNumberString);

                    // Limpiar el contenedor de campos de planta existentes
                    plantFieldsContainer.removeAllViews();

                    // Generar campos de entrada para cada planta
                    for (int i = 1; i <= plantNumber; i++) {
                        TextView plantLabel = new TextView(ReportePlagas.this);
                        plantLabel.setText("Planta " + i);
                        LinearLayout.LayoutParams labelLayoutParams = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        );

                        labelLayoutParams.setMargins(20, 5, 20, 0); // Margen de 20 píxeles a la izquierda y derecha
                        plantLabel.setLayoutParams(labelLayoutParams);
                        plantLabel.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                        plantLabel.setTypeface(null, Typeface.BOLD);
                        plantFieldsContainer.addView(plantLabel);

                        TextView adultos = new TextView(ReportePlagas.this);
                        adultos.setText("Número de adultos");
                        LinearLayout.LayoutParams labelLayoutParamstvDerechaA = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        );
                        labelLayoutParamstvDerechaA.setMargins(20, 0, 20, 0); // Margen de 16 píxeles a la izquierda y derecha
                        adultos.setLayoutParams(labelLayoutParamstvDerechaA);
                        adultos.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                        plantFieldsContainer.addView(adultos);

                        EditText plantDataEditText = new EditText(ReportePlagas.this);
                        plantDataEditText.setHint("Adultos de la planta " + i);
                        adultosDerecha.add(plantDataEditText);

                        LinearLayout.LayoutParams labelLayoutParamsedtDerechaA = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        );
                        labelLayoutParamsedtDerechaA.setMargins(20, 0, 20, 0); // Margen de 16 píxeles a la izquierda y derecha
                        plantDataEditText.setLayoutParams(labelLayoutParamsedtDerechaA);
                        plantDataEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
                        plantFieldsContainer.addView(adultosDerecha.get(i-1));

                        TextView ninfas = new TextView(ReportePlagas.this);
                        ninfas.setText("Número de ninfas");
                        LinearLayout.LayoutParams labelLayoutParamstvDerechaN = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        );
                        labelLayoutParamstvDerechaN.setMargins(20, 0, 20, 0); // Margen de 16 píxeles a la izquierda y derecha
                        ninfas.setLayoutParams(labelLayoutParamstvDerechaN);
                        ninfas.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                        plantFieldsContainer.addView(ninfas);

                        EditText plantDataEditTextNinfas = new EditText(ReportePlagas.this);
                        plantDataEditTextNinfas.setHint("Ninfas de la planta " + i);
                        ninfasDerecha.add(plantDataEditTextNinfas);

                        LinearLayout.LayoutParams labelLayoutParamsedtDerechaN = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        );
                        labelLayoutParamsedtDerechaN.setMargins(20, 0, 20, 0); // Margen de 16 píxeles a la izquierda y derecha
                        plantDataEditTextNinfas.setLayoutParams(labelLayoutParamsedtDerechaN);
                        plantDataEditTextNinfas.setInputType(InputType.TYPE_CLASS_NUMBER);
                        plantFieldsContainer.addView(ninfasDerecha.get(i-1));

                    }

                } else {
                    Toast.makeText(ReportePlagas.this, "Ingrese un número de planta", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //BOTON DEL CENTRO

        generateFieldsButtonCentro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plantNumberStringCentro = plantNumberEditTextCentro.getText().toString();

                // Verificar si se ingresó un número válido
                if (!plantNumberStringCentro.isEmpty()) {
                    int plantNumber = Integer.parseInt(plantNumberStringCentro);

                    // Limpiar el contenedor de campos de planta existentes
                    plantFieldsContainerCentro.removeAllViews();

                    // Generar campos de entrada para cada planta
                    for (int i = 1; i <= plantNumber; i++) {
                        TextView plantLabelCentro = new TextView(ReportePlagas.this);
                        plantLabelCentro.setText("Planta " + i);
                        LinearLayout.LayoutParams labelLayoutParamsCentro = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        );
                        labelLayoutParamsCentro.setMargins(20, 5, 20, 0); // Margen de 20 píxeles a la izquierda y derecha
                        plantLabelCentro.setLayoutParams(labelLayoutParamsCentro);
                        plantLabelCentro.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                        plantLabelCentro.setTypeface(null, Typeface.BOLD);
                        plantFieldsContainerCentro.addView(plantLabelCentro);

                        TextView adultosCentro = new TextView(ReportePlagas.this);
                        adultosCentro.setText("Número de adultos");
                        LinearLayout.LayoutParams labelLayoutParamstvCentroA = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        );
                        labelLayoutParamstvCentroA.setMargins(20, 0, 20, 0); // Margen de 16 píxeles a la izquierda y derecha
                        adultosCentro.setLayoutParams(labelLayoutParamstvCentroA);
                        adultosCentro.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                        plantFieldsContainerCentro.addView(adultosCentro);

                        EditText plantDataEditTextCentro = new EditText(ReportePlagas.this);
                        plantDataEditTextCentro.setHint("Adultos de la planta " + i);
                        adultosCentrop.add(plantDataEditTextCentro);

                        LinearLayout.LayoutParams labelLayoutParamsedtCentroA = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        );
                        labelLayoutParamsedtCentroA.setMargins(20, 0, 20, 0); // Margen de 16 píxeles a la izquierda y derecha
                        plantDataEditTextCentro.setLayoutParams(labelLayoutParamsedtCentroA);
                        plantDataEditTextCentro.setInputType(InputType.TYPE_CLASS_NUMBER);
                        plantFieldsContainerCentro.addView(adultosCentrop.get(i-1));

                        TextView ninfasCentro = new TextView(ReportePlagas.this);
                        ninfasCentro.setText("Número de ninfas");
                        LinearLayout.LayoutParams labelLayoutParamstvCentroN = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        );
                        labelLayoutParamstvCentroN.setMargins(20, 0, 20, 0); // Margen de 16 píxeles a la izquierda y derecha
                        ninfasCentro.setLayoutParams(labelLayoutParamstvCentroN);
                        ninfasCentro.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                        plantFieldsContainerCentro.addView(ninfasCentro);

                        EditText plantDataEditTextNinfasCentro = new EditText(ReportePlagas.this);
                        plantDataEditTextNinfasCentro.setHint("Ninfas de la planta " + i);
                        ninfasCentrop.add(plantDataEditTextNinfasCentro);

                        LinearLayout.LayoutParams labelLayoutParamsedtCentroN = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        );
                        labelLayoutParamsedtCentroN.setMargins(20, 0, 20, 0); // Margen de 16 píxeles a la izquierda y derecha
                        plantDataEditTextNinfasCentro.setLayoutParams(labelLayoutParamsedtCentroN);
                        plantDataEditTextNinfasCentro.setInputType(InputType.TYPE_CLASS_NUMBER);
                        plantFieldsContainerCentro.addView(ninfasCentrop.get(i-1));

                    }

                } else {
                    Toast.makeText(ReportePlagas.this, "Ingrese un número de planta", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //BOTON IZQUIERDA
        generateFieldsButtonIzquierdo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plantNumberStringIzquierdo = plantNumberEditTextIzquierdo.getText().toString();

                // Verificar si se ingresó un número válido
                if (!plantNumberStringIzquierdo.isEmpty()) {
                    int plantNumberIzquierdo = Integer.parseInt(plantNumberStringIzquierdo);

                    // Crear un intent para iniciar la actividad Resumen
                    //Intent intent = new Intent(ReportePlagas.this, Resumen.class);

                    // Agregar el número de plantas al intent como extra
                    //intent.putExtra("plantNumberIzquierdo", plantNumberIzquierdo);

                    // Iniciar la actividad Resumen
                    //startActivity(intent);

                    // Limpiar el contenedor de campos de planta existentes
                    plantFieldsContainerIzquierdo.removeAllViews();

                    // Generar campos de entrada para cada planta
                    for (int i = 1; i <= plantNumberIzquierdo; i++) {
                        TextView plantLabelIzquierda = new TextView(ReportePlagas.this);
                        plantLabelIzquierda.setText("Planta " + i);
                        LinearLayout.LayoutParams labelLayoutParamsIzquierda = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        );
                        labelLayoutParamsIzquierda.setMargins(20, 5, 20, 0); // Margen de 20 píxeles a la izquierda y derecha
                        plantLabelIzquierda.setLayoutParams(labelLayoutParamsIzquierda);
                        plantLabelIzquierda.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                        plantLabelIzquierda.setTypeface(null, Typeface.BOLD);
                        plantFieldsContainerIzquierdo.addView(plantLabelIzquierda);

                        TextView adultosIzq = new TextView(ReportePlagas.this);
                        adultosIzq.setText("Número de adultos");
                        LinearLayout.LayoutParams labelLayoutParamstvIzqA = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        );
                        labelLayoutParamstvIzqA.setMargins(20, 0, 20, 0); // Margen de 16 píxeles a la izquierda y derecha
                        adultosIzq.setLayoutParams(labelLayoutParamstvIzqA);
                        adultosIzq.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                        plantFieldsContainerIzquierdo.addView(adultosIzq);

                        EditText plantDataEditTextIZQ = new EditText(ReportePlagas.this);
                        plantDataEditTextIZQ.setHint("Adultos de la planta " + i);
                        adultosIzquierda.add(plantDataEditTextIZQ);

                        LinearLayout.LayoutParams labelLayoutParamsedtCentrIzqA = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        );
                        labelLayoutParamsedtCentrIzqA.setMargins(20, 0, 20, 0); // Margen de 16 píxeles a la izquierda y derecha
                        plantDataEditTextIZQ.setLayoutParams(labelLayoutParamsedtCentrIzqA);
                        plantDataEditTextIZQ.setInputType(InputType.TYPE_CLASS_NUMBER);
                        plantFieldsContainerIzquierdo.addView(adultosIzquierda.get(i-1));

                        TextView ninfasIzq = new TextView(ReportePlagas.this);
                        ninfasIzq.setText("Número de ninfas");
                        LinearLayout.LayoutParams labelLayoutParamstvIzqN = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        );
                        labelLayoutParamstvIzqN.setMargins(20, 0, 20, 0); // Margen de 16 píxeles a la izquierda y derecha
                        ninfasIzq.setLayoutParams(labelLayoutParamstvIzqN);
                        ninfasIzq.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                        plantFieldsContainerIzquierdo.addView(ninfasIzq);

                        EditText plantDataEditTextNinfasIzq = new EditText(ReportePlagas.this);
                        plantDataEditTextNinfasIzq.setHint("Ninfas de la planta " + i);
                        ninfasIzquierda.add(plantDataEditTextNinfasIzq);

                        LinearLayout.LayoutParams labelLayoutParamsedtIzqN = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        );
                        labelLayoutParamsedtIzqN.setMargins(20, 0, 20, 0); // Margen de 16 píxeles a la izquierda y derecha
                        plantDataEditTextNinfasIzq.setLayoutParams(labelLayoutParamsedtIzqN);
                        plantDataEditTextNinfasIzq.setInputType(InputType.TYPE_CLASS_NUMBER);
                        plantFieldsContainerIzquierdo.addView(ninfasIzquierda.get(i-1));
                    }

                } else {
                    Toast.makeText(ReportePlagas.this, "Ingrese un número de planta", Toast.LENGTH_SHORT).show();
                }
            }
        });
        buttonGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtener los datos ingresados en el contenedor
                //Intent intent = new Intent(ReportePlagas.this, Resumen.class);
                //startActivity(intent);
                try {
                    saveData();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
        });

    }
    private void saveData() throws Exception{
        HashMap<String, ArrayList> dataPlant = new HashMap();

        ArrayList<String> arr_adultosIzquierda = new ArrayList();
        ArrayList<String> arr_ninfasIzquierda = new ArrayList<>();
        ArrayList<String> arr_adultosCentrop = new ArrayList<>();
        ArrayList<String> arr_ninfasCentrop = new ArrayList<>();
        ArrayList<String> arr_adultosDerecha = new ArrayList<>();
        ArrayList<String> arr_ninfasDerecha = new ArrayList<>();

        for (EditText editTextAdultosIzquierda : adultosIzquierda){
            arr_adultosIzquierda.add(editTextAdultosIzquierda.getText().toString());
        }
        for (EditText editTextNinfasIzquierda : ninfasIzquierda){
            arr_ninfasIzquierda.add(editTextNinfasIzquierda.getText().toString());
        }
        for (EditText editTextAdultosCentro : adultosCentrop){
            arr_adultosCentrop.add(editTextAdultosCentro.getText().toString());
        }
        for (EditText editTextNinfasCentro : ninfasCentrop){
            arr_ninfasCentrop.add(editTextNinfasCentro.getText().toString());
        }
        for (EditText editTextAdultosDerecha : adultosDerecha){
            arr_adultosDerecha.add(editTextAdultosDerecha.getText().toString());
        }
        for (EditText editTextNinfasDerecha : ninfasDerecha){
            arr_ninfasDerecha.add(editTextNinfasDerecha.getText().toString());
        }
        JSONObject jsonData = new JSONObject();
        jsonData.put("LI_Adultos", arr_adultosIzquierda);
        jsonData.put("LI_Ninfas", arr_ninfasIzquierda);

        jsonData.put("LC_Adultos", arr_adultosCentrop);
        jsonData.put("LC_Ninfas", arr_ninfasCentrop);

        jsonData.put("LD_Adultos",arr_adultosDerecha);
        jsonData.put("LD_Ninfas",arr_ninfasDerecha);

        connectionDatabase = new ConnectionDatabase(getApplicationContext());
        connectionDatabase.savePlant(jsonData);
        Toast.makeText(ReportePlagas.this, "Connected", Toast.LENGTH_SHORT).show();
    }

}