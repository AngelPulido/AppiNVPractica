package com.example.appinvpractica;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appinvpractica.adapter.LoginResponseAdapter; // Asegúrate que la ruta sea correcta
import com.example.appinvpractica.model.LoginResponse; // Asegúrate que la ruta sea correcta

import java.util.ArrayList;
import java.util.List;


public class ListaACtivity extends AppCompatActivity { // <- Yo usaré "ListaActivity"

    private RecyclerView recyclerView;
    private LoginResponseAdapter adapter;
    private List<LoginResponse> responseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String mensaje = extras.getString("mensaje");
            String rol = extras.getString("rol");
            String token = extras.getString("token");

            LoginResponse response = new LoginResponse();
            response.setMensaje(mensaje);
            response.setRol(rol);
            response.setToken(token);


            responseList = new ArrayList<>();
            responseList.add(response);

        } else {
            responseList = new ArrayList<>();
        }

        recyclerView = findViewById(R.id.rv_lista_login);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new LoginResponseAdapter(this, responseList);
        recyclerView.setAdapter(adapter);
    }
}