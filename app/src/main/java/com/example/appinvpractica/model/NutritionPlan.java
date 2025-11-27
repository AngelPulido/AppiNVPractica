package com.example.appinvpractica.model;
import com.google.gson.annotations.SerializedName;

public class NutritionPlan {
    @SerializedName("titulo")
    private String titulo;
    @SerializedName("descripcion")
    private String descripcion;

    public String getTitulo(){ return titulo; }
    public String getDescripcion(){ return descripcion; }
}
