package com.example.appinvpractica.api;

import com.example.appinvpractica.model.Cita;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface CitasAPI {

    @GET("my-appointments")
    @Headers({"Content-Type: application/json"})
    Call<List<Cita>> getCitas(
            @Header("Authorization") String token
    );
}
