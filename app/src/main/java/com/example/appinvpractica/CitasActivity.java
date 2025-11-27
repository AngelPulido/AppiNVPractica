package com.example.appinvpractica;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.appinvpractica.model.Cita;
import com.example.appinvpractica.utils.TokenManager;
import com.example.appinvpractica.viewmodel.CitasViewModel;
import com.kizitonwose.calendarview.CalendarView;
import com.kizitonwose.calendarview.model.CalendarDay;
import com.kizitonwose.calendarview.model.CalendarMonth;
import com.kizitonwose.calendarview.model.DayOwner;
import com.kizitonwose.calendarview.ui.DayBinder;
import com.kizitonwose.calendarview.ui.ViewContainer;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class CitasActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private CitasViewModel vm;
    private List<Cita> citas = new ArrayList<>();
    private Map<LocalDate, List<Cita>> map = new HashMap<>();
    private TextView tvMes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas);

        tvMes = findViewById(R.id.tvMes);
        calendarView = findViewById(R.id.calendarView);
        vm = new ViewModelProvider(this).get(CitasViewModel.class);

        String token = TokenManager.getToken(this);
        if (token == null) {
            Toast.makeText(this, "Inicia sesión", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        vm.getCitas().observe(this, cs -> {
            if (cs != null) {
                citas = cs;
                map.clear();
                for (Cita c : citas) {
                    String fecha = c.getFecha().substring(0, 10); // "YYYY-MM-DD"
                    LocalDate d = LocalDate.parse(fecha);
                    map.computeIfAbsent(d, k -> new ArrayList<>()).add(c);
                }
                calendarView.notifyCalendarChanged();
            } else {
                Toast.makeText(this, "No hay citas o error", Toast.LENGTH_SHORT).show();
            }
        });

        vm.cargarCitas("Bearer " + token);
        setupCalendar();
    }

    private void setupCalendar() {
        YearMonth currentMonth = YearMonth.now();
        YearMonth start = currentMonth.minusMonths(6);
        YearMonth end = currentMonth.plusMonths(6);

        calendarView.setup(start, end, DayOfWeek.MONDAY);
        calendarView.scrollToMonth(currentMonth);

        // Listener al hacer scroll a otro mes (y al inicio también)
        calendarView.setMonthScrollListener(new Function1<CalendarMonth, Unit>() {
            @Override
            public Unit invoke(CalendarMonth month) {
                if (month != null) {
                    updateMonthTitle(month);
                }
                return Unit.INSTANCE;
            }
        });

        calendarView.setDayBinder(new DayBinder<DayViewContainer>() {
            @Override
            public DayViewContainer create(android.view.View view) {
                return new DayViewContainer(view);
            }

            @Override
            public void bind(DayViewContainer container, CalendarDay day) {
                TextView tv = container.textView;

                if (day.getOwner() != DayOwner.THIS_MONTH) {
                    tv.setText("");
                    tv.setBackgroundColor(Color.TRANSPARENT);
                    return;
                }

                LocalDate date = day.getDate();
                tv.setText(String.valueOf(date.getDayOfMonth()));
                tv.setTextColor(Color.WHITE);
                tv.setBackgroundColor(Color.TRANSPARENT);

                if (date.equals(LocalDate.now())) {
                    tv.setBackgroundColor(Color.parseColor("#69A81E"));
                }

                if (map.containsKey(date)) {
                    tv.setBackgroundColor(Color.parseColor("#E87D1A"));
                    tv.setOnClickListener(v -> showCitas(date));
                } else {
                    tv.setOnClickListener(v ->
                            Toast.makeText(CitasActivity.this, "No hay cita", Toast.LENGTH_SHORT).show()
                    );
                }
            }
        });
    }


    private void updateMonthTitle(CalendarMonth month) {
        String nombreMes = month.getYearMonth().getMonth()
                .getDisplayName(java.time.format.TextStyle.FULL, new Locale("es", "ES"));
        tvMes.setText(nombreMes + " " + month.getYearMonth().getYear());
    }

    private void showCitas(LocalDate date) {
        List<Cita> lista = map.get(date);

        if (lista == null || lista.isEmpty()) {
            Toast.makeText(this, "No hay citas", Toast.LENGTH_SHORT).show();
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (Cita c : lista) {
            sb.append("Fecha: ").append(c.getFecha())
                    .append("\nEstado: ").append(c.getEstado())
                    .append("\nNotas: ").append(c.getNotas() == null ? "N/A" : c.getNotas())
                    .append("\n\n");
        }

        new AlertDialog.Builder(this)
                .setTitle("Citas " + date)
                .setMessage(sb.toString())
                .setPositiveButton("OK", null)
                .show();
    }

    static class DayViewContainer extends ViewContainer {
        TextView textView;

        DayViewContainer(android.view.View view) {
            super(view);
            textView = view.findViewById(R.id.tvDay);
        }
    }
}
