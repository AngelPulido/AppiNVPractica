package com.example.appinvpractica.api;
import com.example.appinvpractica.model.Cita;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface AppointmentService {
    @GET("my-appointments")
    Call<List<Cita>> getMyAppointments(@Header("Authorization") String bearer);
}
