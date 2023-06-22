package com.example.bananitos;

import java.util.Date;

public class Registro {
    private String evaluador, ubicacion, fechaCreacion, nombreAdultos, nombreNinfas,promAdultos, promNinfas;
    private int totalAdultos, totalNinfas, minAdultos, minNinfas, maxAdultos, maxNinfas;

    public Registro(String evaluador, String ubicacion, String fechaCreacion){
        this.evaluador = evaluador;
        this.ubicacion = ubicacion;
        this.fechaCreacion = fechaCreacion;
    }
    public Registro(String ubicacion){
        this.ubicacion = ubicacion;
    }
    public Registro(String ubicacion, String nombreAdultos, String nombreNinfas, int totalAdultos, int totalNinfas, int minAdultos, int minNinfas, int maxAdultos, int maxNinfas ){
        this.ubicacion = ubicacion;
        this.nombreAdultos = nombreAdultos;
        this.nombreNinfas = nombreNinfas;
        this.totalAdultos = totalAdultos;
        this.totalNinfas = totalNinfas;
        this.minAdultos = minAdultos;
        this.minNinfas = minNinfas;
        this.maxAdultos = maxAdultos;
        this.maxNinfas = maxNinfas;
    }
    public Registro(){

    }

    public int getMinAdultos() {
        return minAdultos;
    }

    public void setMinAdultos(int minAdultos) {
        this.minAdultos = minAdultos;
    }

    public int getMinNinfas() {
        return minNinfas;
    }

    public void setMinNinfas(int minNinfas) {
        this.minNinfas = minNinfas;
    }

    public int getMaxAdultos() {
        return maxAdultos;
    }

    public void setMaxAdultos(int maxAdultos) {
        this.maxAdultos = maxAdultos;
    }

    public int getMaxNinfas() {
        return maxNinfas;
    }

    public void setMaxNinfas(int maxNinfas) {
        this.maxNinfas = maxNinfas;
    }

    public String getNombreAdultos() {
        return nombreAdultos;
    }

    public void setNombreAdultos(String nombreAdultos) {
        this.nombreAdultos = nombreAdultos;
    }

    public String getNombreNinfas() {
        return nombreNinfas;
    }

    public void setNombreNinfas(String nombreNinfas) {
        this.nombreNinfas = nombreNinfas;
    }

    public int getTotalAdultos() {
        return totalAdultos;
    }

    public void setTotalAdultos(int totalAdultos) {
        this.totalAdultos = totalAdultos;
    }

    public int getTotalNinfas() {
        return totalNinfas;
    }

    public void setTotalNinfas(int totalNinfas) {
        this.totalNinfas = totalNinfas;
    }

    public String getPromAdultos() {
        return promAdultos;
    }

    public void setPromAdultos(String promAdultos) {
        this.promAdultos = promAdultos;
    }

    public String getPromNinfas() {
        return promNinfas;
    }

    public void setPromNinfas(String promNinfas) {
        this.promNinfas = promNinfas;
    }



    public String getEvaluador() {
        return evaluador;
    }

    public void setEvaluador(String evaluador) {
        this.evaluador = evaluador;
    }

    public String getUbicacion() {
        return ubicacion.toUpperCase();
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
