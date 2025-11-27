package com.example.appinvpractica.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appinvpractica.api.AppointmentService;
import com.example.appinvpractica.model.Cita;
import com.example.appinvpractica.service.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CitasViewModel extends ViewModel {

    private MutableLiveData<List<Cita>> citas = new MutableLiveData<>();
    private AppointmentService api = RetrofitClient.getClient().create(AppointmentService.class);

    public LiveData<List<Cita>> getCitas() {
        return citas;
    }

    public void cargarCitas(String bearer) {
        api.getMyAppointments(bearer).enqueue(new Callback<List<Cita>>() {
            @Override
            public void onResponse(Call<List<Cita>> call, Response<List<Cita>> response) {
                if (response.isSuccessful())
                    citas.postValue(response.body());
                else
                    citas.postValue(null);
            }

            @Override
            public void onFailure(Call<List<Cita>> call, Throwable t) {
                citas.postValue(null);
            }
        });
    }
}
