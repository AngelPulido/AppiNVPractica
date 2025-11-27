package com.example.appinvpractica;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appinvpractica.adapter.PlanAdapter;
import com.example.appinvpractica.model.NutritionPlan;
import com.example.appinvpractica.utils.TokenManager;
import com.example.appinvpractica.viewmodel.PlanesViewModel;
import java.util.List;

public class PlanesActivity extends AppCompatActivity {
    private RecyclerView rv;
    private PlanesViewModel vm;

    @Override protected void onCreate(Bundle s){
        super.onCreate(s);
        setContentView(R.layout.activity_planes);
        rv = findViewById(R.id.rvPlanes);
        rv.setLayoutManager(new LinearLayoutManager(this));
        vm = new ViewModelProvider(this).get(PlanesViewModel.class);

        String token = TokenManager.getToken(this);
        if (token == null) { Toast.makeText(this, "Inicia sesión", Toast.LENGTH_SHORT).show(); finish(); return; }

        vm.getPlanes().observe(this, plans -> {
            if (plans != null) {
                rv.setAdapter(new com.example.appinvpractica.adapter.PlanAdapter(plans));
            } else {
                Toast.makeText(this, "No hay planes o error", Toast.LENGTH_SHORT).show();
            }
        });

        vm.cargarPlanes("Bearer " + token);
    }
}
