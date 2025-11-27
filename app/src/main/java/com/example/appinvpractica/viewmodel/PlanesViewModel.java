package com.example.appinvpractica.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appinvpractica.api.PlanService;
import com.example.appinvpractica.model.NutritionPlan;
import com.example.appinvpractica.service.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlanesViewModel extends ViewModel {

    private MutableLiveData<List<NutritionPlan>> planes = new MutableLiveData<>();
    private PlanService api = RetrofitClient.getClient().create(PlanService.class);

    public LiveData<List<NutritionPlan>> getPlanes() {
        return planes;
    }

    public void cargarPlanes(String bearer) {
        api.getMyPlans(bearer).enqueue(new Callback<List<NutritionPlan>>() {
            @Override
            public void onResponse(Call<List<NutritionPlan>> call, Response<List<NutritionPlan>> response) {
                if (response.isSuccessful())
                    planes.postValue(response.body());
                else
                    planes.postValue(null);
            }

            @Override
            public void onFailure(Call<List<NutritionPlan>> call, Throwable t) {
                planes.postValue(null);
            }
        });
    }
}
