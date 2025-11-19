package com.example.appinvpractica.api;

import com.example.appinvpractica.model.LoginRequest;
import com.example.appinvpractica.model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface LocalNetworkAPI {

    @Headers({"Content-Type: application/json"})
    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

}