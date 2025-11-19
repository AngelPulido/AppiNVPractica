package com.example.appinvpractica;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.appinvpractica.model.LoginResponse;
import com.example.appinvpractica.viewmodel.LoginViewModel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnIniciarProceso;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIniciarProceso = findViewById(R.id.btn_iniciar_proceso);
        btnIniciarProceso.setOnClickListener(this);

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        final Observer<LoginResponse> loginObserver = new Observer<LoginResponse>() {
            @Override
            public void onChanged(LoginResponse loginResponse) {
                if (loginResponse != null) {
                    Toast.makeText(MainActivity.this, "Login OK: " + loginResponse.getMensaje(), Toast.LENGTH_SHORT).show();

                    lanzarListaActivity(loginResponse);

                } else {
                    Toast.makeText(MainActivity.this, "Error en el login", Toast.LENGTH_SHORT).show();
                    Log.d("MainActivity", "Respuesta de login fue nula");
                }
            }
        };

        loginViewModel.getLoginResponse().observe(this, loginObserver);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_iniciar_proceso) {
            Toast.makeText(this, "Enviando login...", Toast.LENGTH_SHORT).show();
            loginViewModel.doLogin("test@gmail.com", "12345678");
        }
    }

    private void lanzarListaActivity(LoginResponse data) {
        Intent intentLista = new Intent(MainActivity.this, ListaACtivity.class);
        intentLista.putExtra("mensaje", data.getMensaje());
        intentLista.putExtra("rol", data.getRol());
        intentLista.putExtra("token", data.getToken());
        startActivity(intentLista);
    }
}