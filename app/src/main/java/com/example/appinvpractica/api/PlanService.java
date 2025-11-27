package com.example.appinvpractica.api;
import com.example.appinvpractica.model.NutritionPlan;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface PlanService {
    @GET("my-plans")
    Call<List<NutritionPlan>> getMyPlans(@Header("Authorization") String bearer);
}
