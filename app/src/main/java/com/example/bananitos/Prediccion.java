package com.example.bananitos;

import static android.graphics.Insets.add;
import static android.graphics.Insets.subtract;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class Prediccion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prediccion);

        /**
         *

        ArrayList<String[]> data_entrada = new ArrayList<>();
        ArrayList<String[]> data_salida = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("dataEntrada2.csv"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(";");
                data_entrada.add(row);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader("dataSalida2.csv"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(";");
                data_salida.add(row);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String[]> data_junta = new ArrayList<>();
        for (String[] salidaRow : data_salida) {
            for (String[] entradaRow : data_entrada) {
                if (salidaRow[0].equals(entradaRow[1])) {
                    String[] joinedRow = new String[salidaRow.length + entradaRow.length - 1];
                    System.arraycopy(salidaRow, 0, joinedRow, 0, salidaRow.length);
                    System.arraycopy(entradaRow, 1, joinedRow, salidaRow.length, entradaRow.length - 1);
                    data_junta.add(joinedRow);
                    break;
                }
            }
        }

        String[] original_targets = new String[data_junta.size()];
        String[] time = new String[data_junta.size()];
        for (int i = 0; i < data_junta.size(); i++) {
            original_targets[i] = data_junta.get(i)[data_junta.get(i).length - 1];
            time[i] = data_junta.get(i)[0];
        }

        ArrayList<String[]> new_targets = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("dataset.csv"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                new_targets.add(row);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < original_targets.length - 1; i++) {
            double new_target = 1 / (Double.parseDouble(time[i + 1]) - Double.parseDouble(time[i])) * Math.log(Double.parseDouble(original_targets[i + 1]) / Double.parseDouble(original_targets[i]));
            new_targets.add(String.valueOf(new_target));
        }

        List<String[]> new_df = data_junta.subList(0, data_junta.size() - 1);

        for (String[] row : new_df) {
            row[row.length - 1] = "";
        }

        for (String[] row : new_df) {
            row[row.length - 1] = new_targets.get(new_df.indexOf(row));
        }

// Guardar en un archivo CSV

        String csvFilename = "dataset.csv";

        try (PrintWriter writer = new PrintWriter(csvFilename)) {
            for (String[] row : new_df) {
                writer.println(String.join(",", row));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] param = {"TempAtm", "TempMic", "Hum", "Vel Viento", "TasaLluvia", "tF1", "tF2", "tL"};
        List<String[]> caract = new ArrayList<>();

        for (String[] row : df) {
            String[] rowData = new String[param.length];
            for (int i = 0; i < param.length; i++) {
                rowData[i] = row[Arrays.asList(df[0]).indexOf(param[i])];
            }
            caract.add(rowData);
        }

        String[] etiq_title = {"r_target"};
        List<String[]> etiq = new ArrayList<>();

        for (String[] row : df) {
            String[] rowData = new String[etiq_title.length];
            for (int i = 0; i < etiq_title.length; i++) {
                rowData[i] = row[Arrays.asList(df[0]).indexOf(etiq_title[i])];
            }
            etiq.add(rowData);
        }

// Separación en conjuntos de entrenamiento y validación

        int testSize = (int) (caract.size() * 0.10);
        int randomState = 42;

        List<String[]> X_train = new ArrayList<>(caract.subList(0, caract.size() - testSize));
        List<String[]> X_val = new ArrayList<>(caract.subList(caract.size() - testSize, caract.size()));
        List<String[]> y_train = new ArrayList<>(etiq.subList(0, etiq.size() - testSize));
        List<String[]> y_val = new ArrayList<>(etiq.subList(etiq.size() - testSize, etiq.size()));

        // Resto del código previo...

// Asignación de valores a variables existentes

        String[][] df = new String[<cantidad de filas>][<cantidad de columnas>];
// Asigna los valores correspondientes a la matriz df

        String[][] y_train = new String[<cantidad de filas>][<cantidad de columnas>];
// Asigna los valores correspondientes a la matriz y_train

        String[][] X_train = new String[<cantidad de filas>][<cantidad de columnas>];
// Asigna los valores correspondientes a la matriz X_train

        String[][] X_val = new String[<cantidad de filas>][<cantidad de columnas>];
// Asigna los valores correspondientes a la matriz X_val

        String[][] X_train_NN = new String[<cantidad de filas>][3];
        for (int i = 0; i < X_train.length; i++) {
            X_train_NN[i][0] = X_train[i][Arrays.asList(X_train[0]).indexOf("Hum")];
            X_train_NN[i][1] = X_train[i][Arrays.asList(X_train[0]).indexOf("Vel Viento")];
            X_train_NN[i][2] = X_train[i][Arrays.asList(X_train[0]).indexOf("TasaLluvia")];
        }

        String[][] X_val_NN = new String[<cantidad de filas>][3];
        for (int i = 0; i < X_val.length; i++) {
            X_val_NN[i][0] = X_val[i][Arrays.asList(X_val[0]).indexOf("Hum")];
            X_val_NN[i][1] = X_val[i][Arrays.asList(X_val[0]).indexOf("Vel Viento")];
            X_val_NN[i][2] = X_val[i][Arrays.asList(X_val[0]).indexOf("TasaLluvia")];
        }

        String[][] X_train_NN = new String[X_train_NN.length][X_train_NN[0].length];
        for (int i = 0; i < X_train_NN.length; i++) {
            for (int j = 0; j < X_train_NN[0].length; j++) {
                X_train_NN[i][j] = X_train_NN[i][j].replace(",", ".");
            }
        }

        String[][] X_train = new String[X_train.length][X_train[0].length];
        for (int i = 0; i < X_train.length; i++) {
            for (int j = 0; j < X_train[0].length; j++) {
                X_train[i][j] = X_train[i][j].replace(",", ".");
            }
        }

        String[][] y_train = new String[y_train.length][y_train[0].length];
        for (int i = 0; i < y_train.length; i++) {
            for (int j = 0; j < y_train[0].length; j++) {
                y_train[i][j] = y_train[i][j].replace(",", ".");
            }
        }

        String[][] X_val = new String[X_val.length][X_val[0].length];
        for (int i = 0; i < X_val.length; i++) {
            for (int j = 0; j < X_val[0].length; j++) {
                X_val[i][j] = X_val[i][j].replace(",", ".");
            }
        }

        String[][] y_val = new String[y_val.length][y_val[0].length];
        for (int i = 0; i < y_val.length; i++) {
            for (int j = 0; j < y_val[0].length; j++) {
                y_val[i][j] = y_val[i][j].replace(",", ".");
            }
        }

        // Resto del código previo...

// Asignación de valores a variables existentes

        String[][] x = new String[1][X_train_NN[0].length];
        for (int i = 0; i < X_train_NN[0].length; i++) {
            x[0][i] = X_train_NN[0][i];
        }

// Definición de función initialize_adam
        public static Map<String, double[][]> initialize_adam(Map<String, double[][]> parameters) {
            int L = parameters.size() / 2; // number of layers in the neural networks
            Map<String, double[][]> v = new HashMap<>();
            Map<String, double[][]> s = new HashMap<>();

            for (int l = 1; l <= L; l++) {
                v.put("dW" + l, new double[parameters.get("W" + l).length][parameters.get("W" + l)[0].length]);
                v.put("db" + l, new double[parameters.get("b" + l).length][parameters.get("b" + l)[0].length]);
                s.put("dW" + l, new double[parameters.get("W" + l).length][parameters.get("W" + l)[0].length]);
                s.put("db" + l, new double[parameters.get("b" + l).length][parameters.get("b" + l)[0].length]);
            }

            return v, s;
        }

// Definición de función update_parameters_with_adam
        public static Map<String, double[][]> update_parameters_with_adam(Map<String, double[][]> parameters,
                Map<String, double[][]> grads,
                Map<String, double[][]> v,
                Map<String, double[][]> s,
        int t,
        double learning_rate,
        double beta1,
        double beta2,
        double epsilon) {
            int L = parameters.size() / 2; // number of layers in the neural networks
            Map<String, double[][]> v_corrected = new HashMap<>();
            Map<String, double[][]> s_corrected = new HashMap<>();

            for (int l = 1; l <= L; l++) {
                v.put("dW" + l, beta1 * v.get("dW" + l) + (1 - beta1) * grads.get("dW" + l));
                v.put("db" + l, beta1 * v.get("db" + l) + (1 - beta1) * grads.get("db" + l));

                v_corrected.put("dW" + l, v.get("dW" + l) / (1 - Math.pow(beta1, t)));
                v_corrected.put("db" + l, v.get("db" + l) / (1 - Math.pow(beta1, t)));

                s.put("dW" + l, beta2 * s.get("dW" + l) + (1 - beta2) * Math.pow(grads.get("dW" + l), 2));
                s.put("db" + l, beta2 * s.get("db" + l) + (1 - beta2) * Math.pow(grads.get("db" + l), 2));

                s_corrected.put("dW" + l, s.get("dW" + l) / (1 - Math.pow(beta2, t)));
                s_corrected.put("db" + l, s.get("db" + l) / (1 - Math.pow(beta2, t)));

                parameters.put("W" + l, parameters.get("W" + l) - learning_rate * v_corrected.get("dW" + l) / (Math.sqrt(s_corrected.get("dW" + l)) + epsilon));
                parameters.put("b" + l, parameters.get("b" + l) - learning_rate * v_corrected.get("db" + l) / (Math.sqrt(s_corrected.get("db" + l)) + epsilon));
            }

            return parameters, v, s;
        }

        // Función tanh
        public static double[][] tanh(double[][] x) {
            double[][] result = new double[x.length][x[0].length];
            for (int i = 0; i < x.length; i++) {
                for (int j = 0; j < x[0].length; j++) {
                    result[i][j] = Math.tanh(x[i][j]);
                }
            }
            return result;
        }

// Función de retropropagación
        public static Map<String, double[][]> backpropagation(double[][] X, double[][] y,
        double[][] W1, double[][] b1,
        double[][] W2, double[][] b2,
        double a, double b, double mu,
        double alfa1, double alfa2, double gamma,
        double degF1, double degF2, double degL,
        double lr) {
            int n = X.length;
            int[] index = new int[n];
            for (int i = 0; i < n; i++) {
                index[i] = i;
            }
            shuffleArray(index);

            Map<String, double[][]> parameters = new HashMap<>();
            parameters.put("W1", W1);
            parameters.put("b1", b1);
            parameters.put("W2", W2);
            parameters.put("b2", b2);

            Map<String, double[][]> v, s;
            v, s = initialize_adam(parameters);  // Inicializar los momentos de Adam

            for (int i = 0; i < n; i++) {
                int row = index[i];
                double[][] x = { { X[row][2] }, { X[row][3] }, { X[row][4] } };
                double temp = X[row][0];
                double tempMic = X[row][1];
                double time_F1 = X[row][5];
                double time_F2 = X[row][6];
                double time_L = X[row][7];
                double r_t = y[row][0];

                // NN stuff
                // Forward de la primera capa densa
                double[][] z1 = add(dot(W1, x), b1);
                double[][] a1 = tanh(z1);

                // Forward de la capa de salida
                double[][] z2 = add(dot(W2, a1), b2);
                double[][] a2 = z2;  // Función de activación lineal (identidad)

                double F1 = Math.exp(-degF1 * time_F1);
                double F2 = Math.exp(-degF2 * time_F2);
                double L = Math.exp(-degL * time_L);

                double[][] r_hat = add(add(add(a2, a), multiply(b, temp)), multiply(-mu, 1), multiply(-alfa1, F1), multiply(-alfa2, F2), multiply(-gamma, L));

                double e = r_hat[0][0] - r_t;  // el error entre las salidas de la red y los valores objetivo

                Map<String, double[][]> grads = new HashMap<>();
                alfa2 = alfa2 + lr * e * F2;
                alfa1 = alfa1 + lr * e * F1;
                gamma = gamma + lr * e * L;
                degF1 = degF1 - lr * e * (alfa1 * time_F1 * Math.exp(-degF1 * time_F1));
                degF2 = degF2 - lr * e * (alfa2 * time_F2 * Math.exp(-degF2 * time_F2));
                degL = degL - lr * e * (gamma * time_L * Math.exp(-degL * time_L));

                mu = mu + lr * e;
                b = b - lr * e * temp;
                a = a - lr * e;

                // Backward de la capa de salida
                double[][] dz2 = { { e } };
                double[][] db2 = dz2;
                double[][] dW2 = dot(dz2, transpose(a1));
                double[][] da1 = dot(transpose(W2), dz2);

                // Backward de la capa oculta
                double[][] dz1 = multiply(da1, subtract(1, power(a1, 2)));
                double[][] db1 = dz1;
                double[][] dW1 = dot(dz1, transpose(x));

                grads.put("dW1", dW1);
                grads.put("db1", db1);
                grads.put("dW2", dW2);
                grads.put("db2", db2);

                int t = i + 1;

                parameters, v, s = update_parameters_with_adam(parameters, grads, v, s, t, learning_rate=lr);
            }

            Map<String, double[][]> result = new HashMap<>();
            result.put("parameters", parameters);
            result.put("a", a);
            result.put("b", b);
            result.put("mu", mu);
            result.put("alfa1", alfa1);
            result.put("alfa2", alfa2);
            result.put("gamma", gamma);
            result.put("degF1", degF1);
            result.put("degF2", degF2);
            result.put("degL", degL);

            return result;
        }

// Método para mezclar un array en forma aleatoria
        public static void shuffleArray(int[] array) {
            Random rnd = new Random();
            for (int i = array.length - 1; i > 0; i--) {
                int index = rnd.nextInt(i + 1);
                int temp = array[index];
                array[index] = array[i];
                array[i] = temp;
            }
        }

        public static Map<String, Object> eval_model(double[][] X, double[][] y,
        double[][] W1, double[][] b1,
        double[][] W2, double[][] b2,
        double a, double b, double mu,
        double alfa1, double alfa2, double gamma,
        double degF1, double degF2, double degL) {
            double loss = 0;
            List<Double> predictions = new ArrayList<>();
            for (int i = 0; i < X.length; i++) {
                double[][] x = { { X[i][2] }, { X[i][3] }, { X[i][4] } };
                double temp = X[i][0];
                double tempMic = X[i][1];
                double time_F1 = X[i][5];
                double time_F2 = X[i][6];
                double time_L = X[i][7];
                double r_t = y[i][0];

                // NN stuff
                // Forward de la primera capa densa
                double[][] z1 = add(dot(W1, x), b1);
                double[][] a1 = tanh(z1);

                // Forward de la capa de salida
                double[][] z2 = add(dot(W2, a1), b2);
                double[][] a2 = z2;  // Función de activación lineal (identidad)

                double F1 = Math.exp(-degF1 * time_F1);
                double F2 = Math.exp(-degF2 * time_F2);
                double L = Math.exp(-degL * time_L);

                double[][] r_hat = add(add(add(a2, a), multiply(b, temp)), multiply(-mu, 1), multiply(-alfa1, F1), multiply(-alfa2, F2), multiply(-gamma, L));
                predictions.add(r_hat[0][0]);
                loss += Math.pow(r_hat[0][0] - r_t, 2);
            }

            Map<String, Object> result = new HashMap<>();
            result.put("loss", loss);
            result.put("predictions", predictions);

            return result;
        }

        List<Double> train_loss_history = new ArrayList<>();
        List<Double> val_loss_history = new ArrayList<>();

        List<List<Double>> train_predictions = new ArrayList<>();
        List<List<Double>> val_predictions = new ArrayList<>();

        List<Double> train_rmse_values = new ArrayList<>();
        List<Double> val_rmse_values = new ArrayList<>();

        int epochs = 1025;
        double lr = 1e-04;
        int h = 100;

        Random rand = new Random(64);
        double[][] W1 = random(h, 3, rand);
        double[][] b1 = zeros(h, 1);
        double[][] W2 = random(1, h, rand);
        double[][] b2 = zeros(1, 1);

        double a = -0.10659105;
        double b = 0.04543748;
        double mu = 0.17233691;
        double alfa1 = 0.5999726;
        double alfa2 = 0.05301236;
        double gamma = 0.04999256;
        double degF1 = 0.05238576;
        double degF2 = 0.42;
        double degL = 0.10002858;

        List<Double> a_list = new ArrayList<>();
        List<Double> b_list = new ArrayList<>();
        List<Double> mu_list = new ArrayList<>();
        List<Double> alfa1_list = new ArrayList<>();
        List<Double> alfa2_list = new ArrayList<>();
        List<Double> gamma_list = new ArrayList<>();
        List<Double> degF1_list = new ArrayList<>();
        List<Double> degF2_list = new ArrayList<>();
        List<Double> degL_list = new ArrayList<>();

        List<Double> r2_scores = new ArrayList<>();
        List<Double> rmse_scores = new ArrayList<>();

// Entrenamiento de la red neuronal
        for (int epoch = 0; epoch < epochs; epoch++) {
            // Retropropagación en el conjunto de entrenamiento
            Map<String, Object> backpropResult = backpropagation(X_train, y_train, W1, b1, W2, b2,
                    a, b, mu, alfa1, alfa2, gamma, degF1, degF2, degL, lr);
            double[][] updatedParametersW1 = (double[][]) backpropResult.get("W1");
            double[][] updatedParametersb1 = (double[][]) backpropResult.get("b1");
            double[][] updatedParametersW2 = (double[][]) backpropResult.get("W2");
            double[][] updatedParametersb2 = (double[][]) backpropResult.get("b2");
            a = (double) backpropResult.get("a");
            b = (double) backpropResult.get("b");
            mu = (double) backpropResult.get("mu");
            alfa1 = (double) backpropResult.get("alfa1");
            alfa2 = (double) backpropResult.get("alfa2");
            gamma = (double) backpropResult.get("gamma");
            degF1 = (double) backpropResult.get("degF1");
            degF2 = (double) backpropResult.get("degF2");
            degL = (double) backpropResult.get("degL");

            Map<String, Object> trainResult = eval_model(X_train, y_train, updatedParametersW1, updatedParametersb1, updatedParametersW2, updatedParametersb2,
                    a, b, mu, alfa1, alfa2, gamma, degF1, degF2, degL);
            double train_loss = (double) trainResult.get("loss");
            List<Double> train_pred = (List<Double>) trainResult.get("predictions");

            Map<String, Object> valResult = eval_model(X_val, y_val, updatedParametersW1, updatedParametersb1, updatedParametersW2, updatedParametersb2,
                    a, b, mu, alfa1, alfa2, gamma, degF1, degF2, degL);
            double val_loss = (double) valResult.get("loss");
            List<Double> val_pred = (List<Double>) valResult.get("predictions");

            train_loss_history.add(train_loss);
            val_loss_history.add(val_loss);

            a_list.add(a);
            b_list.add(b);
            mu_list.add(mu);
            alfa1_list.add(alfa1);
            alfa2_list.add(alfa2);
            gamma_list.add(gamma);
            degF1_list.add(degF1);
            degF2_list.add(degF2);
            degL_list.add(degL);

            train_predictions.add(train_pred);
            val_predictions.add(val_pred);

            double train_rmse = Math.sqrt(mean_squared_error(y_train, train_pred));
            double val_rmse = Math.sqrt(mean_squared_error(y_val, val_pred));
            train_rmse_values.add(train_rmse);
            val_rmse_values.add(val_rmse);
        }

// Mostrar la pérdida final
        System.out.println("Pérdida en el conjunto de validación: " + val_loss_history.get(val_loss_history.size() - 1));
        System.out.println("Pérdida en el conjunto de entrenamiento: " + train_loss_history.get(train_loss_history.size() - 1));

// train_predictions = []
// val_predictions = []

        double[] x = new double[train_predictions.get(train_predictions.size() - 1).size()];
        for (int i = 0; i < x.length; i++) {
            x[i] = i;
        }
        double[] train_pred = train_predictions.get(train_predictions.size() - 1).stream().mapToDouble(Double::doubleValue).toArray();
        double[] y_train_flatten = Arrays.stream(y_train).flatMapToDouble(Arrays::stream).toArray();
// double[] val_pred = val_predictions.get(val_predictions.size() - 1).stream().mapToDouble(Double::doubleValue).toArray();
// double[] y_val_flatten = Arrays.stream(y_val).flatMapToDouble(Arrays::stream).toArray();

        Plot2DPanel plot = new Plot2DPanel();
        plot.addScatterPlot("Predichos", x, train_pred);
        plot.addScatterPlot("Reales", x, y_train_flatten);
// plot.addScatterPlot("Predichos (Validación)", x, val_pred);
// plot.addScatterPlot("Reales (Validación)", x, y_val_flatten);
        plot.setAxisLabel(0, "Días");
        plot.setAxisLabel(1, "Crecimiento de Plagas");
        plot.setLegendOrientation(PlotPanel.SOUTH);
        JFrame frame = new JFrame("Comparación de datos de salida reales y predichos");
        frame.setContentPane(plot);
        frame.setSize(800, 600);
        frame.setVisible(true);

        double[] xVal = new double[val_predictions.get(val_predictions.size() - 1).size()];
        for (int i = 0; i < xVal.length; i++) {
            xVal[i] = i;
        }
        double[] val_pred = val_predictions.get(val_predictions.size() - 1).stream().mapToDouble(Double::doubleValue).toArray();
        double[] y_val_flatten = Arrays.stream(y_val).flatMapToDouble(Arrays::stream).toArray();

        Plot2DPanel plotVal = new Plot2DPanel();
        plotVal.addScatterPlot("Predichos (Validación)", xVal, val_pred);
        plotVal.addScatterPlot("Reales (Validación)", xVal, y_val_flatten);
        plotVal.setAxisLabel(0, "Días");
        plotVal.setAxisLabel(1, "Crecimiento de Plagas");
        plotVal.setLegendOrientation(PlotPanel.SOUTH);
        JFrame frameVal = new JFrame("Comparación de datos de salida reales y predichos (Validación)");
        frameVal.setContentPane(plotVal);
        frameVal.setSize(800, 600);
        frameVal.setVisible(true);







        // Continúa con el procesamiento de los datos en Java para Android Studio



    }
         */
    }
}

