package com.example.appinvpractica.screens;

import android.os.Bundle;
import android.widget.CalendarView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appinvpractica.R;
import com.example.appinvpractica.api.AppointmentService;
import com.example.appinvpractica.model.Cita;
import com.example.appinvpractica.utils.TokenManager;
import com.example.appinvpractica.service.RetrofitClient;

import java.util.ArrayList;
import java.util.List;
import java.util.GregorianCalendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalendarioActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private List<Cita> citasMarcadas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        calendarView = findViewById(R.id.calendarView);

        cargarCitas();
    }

    private void cargarCitas() {
        AppointmentService api = RetrofitClient.getClient().create(AppointmentService.class);
        String token = "Bearer " + TokenManager.getToken(this);

        api.getMyAppointments(token).enqueue(new Callback<List<Cita>>() {
            @Override
            public void onResponse(Call<List<Cita>> call, Response<List<Cita>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    citasMarcadas = response.body();
                    marcarEnCalendario();
                }
            }

            @Override
            public void onFailure(Call<List<Cita>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void marcarEnCalendario() {
        // Nota: CalendarView nativo solo permite fijar 1 fecha, no marcar múltiples días visualmente
        if (citasMarcadas.isEmpty()) return;

        // Por ejemplo, establecer la primera cita
        Cita c = citasMarcadas.get(0);
        String[] partes = c.getFecha().split("-");
        int año = Integer.parseInt(partes[0]);
        int mes = Integer.parseInt(partes[1]) - 1;
        int dia = Integer.parseInt(partes[2]);

        long milis = new GregorianCalendar(año, mes, dia).getTimeInMillis();
        calendarView.setDate(milis, false, true);
    }
}
