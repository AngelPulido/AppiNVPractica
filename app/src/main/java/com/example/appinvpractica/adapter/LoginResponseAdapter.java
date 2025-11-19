package com.example.appinvpractica.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appinvpractica.MainActivity;
import com.example.appinvpractica.R;
import com.example.appinvpractica.model.LoginResponse;

import java.util.List;

public class LoginResponseAdapter extends RecyclerView.Adapter<LoginResponseAdapter.LoginViewHolder> {

    private Context context;
    private List<LoginResponse> responseList;

    public LoginResponseAdapter(Context context, List<LoginResponse> responseList) {
        this.context = context;
        this.responseList = responseList;
    }

    @NonNull
    @Override
    public LoginViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.login_row, parent, false);
        return new LoginViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoginViewHolder holder, int position) {
        LoginResponse response = responseList.get(position);

        holder.tvMensaje.setText("Mensaje: " + response.getMensaje());
        holder.tvRol.setText("Rol: " + response.getRol());
        holder.tvToken.setText("Token: " + response.getToken());

        // 🔹 Acción del botón "Regresar"
        holder.btnRegresar.setOnClickListener(v -> {
            Intent intent = new Intent(context, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return (responseList != null) ? responseList.size() : 0;
    }

    public static class LoginViewHolder extends RecyclerView.ViewHolder {
        TextView tvMensaje, tvRol, tvToken;
        Button btnRegresar;

        public LoginViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMensaje = itemView.findViewById(R.id.tv_item_mensaje);
            tvRol = itemView.findViewById(R.id.tv_item_rol);
            tvToken = itemView.findViewById(R.id.tv_item_token);
            btnRegresar = itemView.findViewById(R.id.btn_regresar); // 🔹 Nuevo botón
        }
    }
}
