package com.example.appinvpractica;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import com.example.appinvpractica.utils.TokenManager;

public class DashboardActivity extends AppCompatActivity {
    private Spinner spMenu;
    private Button btnLogout;

    @Override protected void onCreate(Bundle s){
        super.onCreate(s);
        setContentView(R.layout.activity_dashboard);

        spMenu = findViewById(R.id.spMenu);
        btnLogout = findViewById(R.id.btnLogout);

        String[] items = {"Seleccionar","Planes","Citas"};
        spMenu.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items));

        spMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if (pos==1) startActivity(new Intent(DashboardActivity.this, PlanesActivity.class));
                if (pos==2) startActivity(new Intent(DashboardActivity.this, CitasActivity.class));
                spMenu.setSelection(0);
            }
            @Override public void onNothingSelected(AdapterView<?> parent) {}
        });

        btnLogout.setOnClickListener(v -> {
            TokenManager.clear(this);
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }
}
