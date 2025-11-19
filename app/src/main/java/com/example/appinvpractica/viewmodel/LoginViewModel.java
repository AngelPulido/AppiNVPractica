package com.example.appinvpractica.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appinvpractica.api.LocalNetworkAPI;
import com.example.appinvpractica.model.LoginRequest;
import com.example.appinvpractica.model.LoginResponse;
import com.example.appinvpractica.service.ServiceRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginResponse> loginResponseData;
    private LocalNetworkAPI localNetworkAPI;

    public LoginViewModel() {
        this.loginResponseData = new MutableLiveData<>();
        this.localNetworkAPI = ServiceRetrofit.getClient().create(LocalNetworkAPI.class);
    }

    public LiveData<LoginResponse> getLoginResponse() {
        return loginResponseData;
    }

    public void doLogin(String correo, String contrasena) {
        LoginRequest request = new LoginRequest(correo, contrasena);

        Call<LoginResponse> call = localNetworkAPI.login(request);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    loginResponseData.postValue(response.body());
                } else {

                    loginResponseData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                // Error de red
                loginResponseData.postValue(null);
            }
        });
    }
}