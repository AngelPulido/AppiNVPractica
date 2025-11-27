package com.example.appinvpractica;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.example.appinvpractica.model.LoginResponse;
import com.example.appinvpractica.utils.TokenManager;
import com.example.appinvpractica.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {
    private EditText etCorreo, etPassword;
    private Button btnLogin;
    private LoginViewModel vm;

    @Override protected void onCreate(Bundle s){
        super.onCreate(s);
        setContentView(R.layout.activity_login);

        etCorreo = findViewById(R.id.etCorreo);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        vm = new ViewModelProvider(this).get(LoginViewModel.class);
        vm.getLoginResponse().observe(this, new Observer<LoginResponse>() {
            @Override public void onChanged(LoginResponse lr) {
                if (lr != null && lr.getToken() != null) {
                    TokenManager.saveToken(LoginActivity.this, lr.getToken(), lr.getRol());
                    Toast.makeText(LoginActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Credenciales inválidas", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnLogin.setOnClickListener(v -> {
            String correo = etCorreo.getText().toString().trim();
            String pass = etPassword.getText().toString().trim();
            if (correo.isEmpty() || pass.isEmpty()){
                Toast.makeText(this, "Completa correo y contraseña", Toast.LENGTH_SHORT).show();
                return;
            }
            vm.doLogin(correo, pass);
        });
    }
}
