package com.example.appinvpractica.model;
import com.google.gson.annotations.SerializedName;

public class Cita {
    @SerializedName("id")
    private int id;
    @SerializedName("fecha")
    private String fecha; // "YYYY-MM-DD HH:MM:SS" o ISO
    @SerializedName("estado")
    private String estado;
    @SerializedName("notas")
    private String notas;

    public int getId(){ return id; }
    public String getFecha(){ return fecha; }
    public String getEstado(){ return estado; }
    public String getNotas(){ return notas; }
}
