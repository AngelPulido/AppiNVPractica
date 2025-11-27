package com.example.appinvpractica.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appinvpractica.R;
import com.example.appinvpractica.model.NutritionPlan;
import java.util.List;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.VH> {
    private List<NutritionPlan> lista;

    public PlanAdapter(List<NutritionPlan> lista){ this.lista = lista; }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_plan, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        NutritionPlan p = lista.get(position);
        holder.titulo.setText(p.getTitulo());
        holder.descripcion.setText(p.getDescripcion());
    }

    @Override
    public int getItemCount() { return lista == null ? 0 : lista.size(); }

    static class VH extends RecyclerView.ViewHolder {
        TextView titulo, descripcion;
        VH(View itemView){
            super(itemView);
            titulo = itemView.findViewById(R.id.txtTitulo);
            descripcion = itemView.findViewById(R.id.txtDescripcion);
        }
    }
}
