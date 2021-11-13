package com.example.cursos.cursossesi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cursos.cursossesi.R;
import com.example.cursos.cursossesi.models.Curso;

import java.util.List;

public class CursosAdapter extends RecyclerView.Adapter<CursosAdapter.MyViewHolder> {
    private List<Curso> cursoLista;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cursoLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cursos_detalhe, parent, false);
        return new MyViewHolder(cursoLista);
    }

    public CursosAdapter(List<Curso> cursoLista) {
        this.cursoLista = cursoLista;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Curso curso = cursoLista.get(position);
        String dataConvertida = curso.getDataDeInicio();
        dataConvertida = dataConvertida.substring(0, 10);
        holder.txtNomeCurso.setText(curso.getNome());
        holder.txtDataInicioCurso.setText(dataConvertida);
    }

    @Override
    public int getItemCount() {
        return cursoLista.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNomeCurso;
        private TextView txtDataInicioCurso;
        private Button btnAlterar, btnExcluir;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNomeCurso = itemView.findViewById(R.id.txtNomeCurso);
            txtDataInicioCurso = itemView.findViewById(R.id.txtDataInicioCurso);
            btnAlterar = itemView.findViewById(R.id.btnAlterar);
            btnExcluir = itemView.findViewById(R.id.btnExcluir);
        }
    }

}
