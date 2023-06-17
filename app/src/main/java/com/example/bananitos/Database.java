package com.example.bananitos;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Database {
    private Connection conn;
    private PreparedStatement query;
    private Statement query2;
    private HashMap<String,ArrayList> response;
    private ResultSet resultSet;
    private ArrayList values;
    public Database(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://aws.connect.psdb.cloud/basedatos1?sslMode=VERIFY_IDENTITY",
                    "ivjhdk1tzngqz08tw05m", "pscale_pw_an50MhjZqS4PWVPreY4sgxmuH2VdNXxjoBgooggG4hf");

        }catch (Exception err){
            System.out.println(err);
        }
    }
    public void saveData(HashMap<String, ArrayList> dataPlanta){
        try {
            long milis = System.currentTimeMillis();
            java.sql.Date fechaCreacion = new java.sql.Date(milis);
            String nombreEvaluador = "Pierina";
            int nroPlantas = 3;
            query = conn.prepareStatement("INSERT INTO registro (evaluador, nroPlantas, fechaCreacion) VALUES(?,?,?)");
            query.setString(1, nombreEvaluador);
            query.setInt(2, nroPlantas);
            query.setDate(3, fechaCreacion);
            int idRegistro = query.executeUpdate();


        }catch (Exception err){

        }
    }
    public int saveData(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://aws.connect.psdb.cloud/basedatos1?sslMode=VERIFY_IDENTITY",
                    "ivjhdk1tzngqz08tw05m", "pscale_pw_an50MhjZqS4PWVPreY4sgxmuH2VdNXxjoBgooggG4hf");

            long milis = System.currentTimeMillis();
            java.sql.Date fechaCreacion = new java.sql.Date(milis);
            String nombreEvaluador = "Pierina";
            int nroPlantas = 3;
            query = conn.prepareStatement("INSERT INTO registro (evaluador, nroPlantas, fechaCreacion) VALUES(?,?,?)");
            query.setString(1, nombreEvaluador);
            query.setInt(2, nroPlantas);
            query.setDate(3, fechaCreacion);
            int idRegistro = query.executeUpdate();
            System.out.println(idRegistro);
            return  idRegistro;

        }catch (Exception err){
            System.err.println(err);
            return 0;
        }
    }
    public HashMap<String, ArrayList> getPlantas(){
        try {
            query2 = conn.createStatement();
            response = new HashMap<>();
            resultSet = query2.executeQuery("SELECT * FROM planta");
            System.out.println(resultSet);

            return  response;
        }catch (Exception err){
            return null;
        }

    }
    public HashMap <String,ArrayList> getAllRegistro(){
        try{
            response = new HashMap<>();
            query = conn.prepareStatement("SELECT * FROM registro WHERE evaluador = ?");
            return  response;
        }catch (Exception err){
            return  null;
        }
    }
    public ArrayList getRegistroByName(String nombre){
        try {
            values = new ArrayList();
            query = conn.prepareStatement("SELECT * FROM registro WHERE evaluador = ?");
            query.setString(1,nombre);

            return values;
        }catch (Exception err){
            return  null;
        }
    }

}
