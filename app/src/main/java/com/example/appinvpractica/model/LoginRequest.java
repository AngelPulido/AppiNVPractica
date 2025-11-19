package com.example.appinvpractica.model;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {

    @SerializedName("correo")
    private String correo;

    @SerializedName("contraseña")
    private String contrasena;

    public LoginRequest(String correo, String contrasena) {
        this.correo = correo;
        this.contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}