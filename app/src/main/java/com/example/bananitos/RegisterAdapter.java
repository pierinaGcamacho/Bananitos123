package com.example.bananitos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RegisterAdapter extends RecyclerView.Adapter<RegisterAdapter.ViewHolder> {
    private Context context;
    public List<Registro> registerAdapters;
    public RegisterAdapter(Context context, List<Registro> registerAdapters){
        this.context = context;
        this.registerAdapters = registerAdapters;
    }
    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(this.context).inflate(R.layout.single_registro, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Registro registro = this.registerAdapters.get(position);
        holder.titleRegistro.setText(registro.getUbicacion());
        holder.nombreNInfas.setText(registro.getNombreNinfas());
        holder.nombreAdulto.setText(registro.getNombreAdultos());
        holder.totalAdultos.setText(String.valueOf(registro.getTotalAdultos()));
        holder.totalNinfas.setText(String.valueOf(registro.getTotalNinfas()));
        holder.maxAdultos.setText(String.valueOf(registro.getMaxAdultos()));
        holder.maxNinfas.setText(String.valueOf(registro.getMaxNinfas()));
        holder.minAdultos.setText(String.valueOf(registro.getMinAdultos()));
        holder.minNinfas.setText(String.valueOf(registro.getMinNinfas()));
        holder.promAdultos.setText(registro.getPromAdultos());
        holder.promNinfas.setText(registro.getPromNinfas());
    }
    @Override
    public int getItemCount() {
        return registerAdapters.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView titleRegistro, nombreAdulto, nombreNInfas, totalAdultos, totalNinfas, maxAdultos, maxNinfas, minAdultos, minNinfas, promAdultos, promNinfas;

        public ViewHolder(View intenView){
            super(intenView);
            titleRegistro = intenView.findViewById(R.id.titleRegistro);
            nombreAdulto = intenView.findViewById(R.id.nombreAdultoRegistro);
            nombreNInfas = intenView.findViewById(R.id.nombreNinfasRegistro);
            totalAdultos = intenView.findViewById(R.id.totalAdultos);
            totalNinfas = intenView.findViewById(R.id.totalNinfas);
            maxAdultos = intenView.findViewById(R.id.maxAdultos);
            maxNinfas = intenView.findViewById(R.id.maxNinfas);
            minAdultos = intenView.findViewById(R.id.minAdultos);
            minNinfas = intenView.findViewById(R.id.minNinfas);
            promAdultos = intenView.findViewById(R.id.promAdultos);
            promNinfas = intenView.findViewById(R.id.promNinfas);
        }
    }

}
