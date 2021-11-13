package com.example.cursos.cursossesi.ui.colaborador;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cursos.cursossesi.R;

public class ColaboradorDashBoardFragment extends Fragment {
    Button btnCursos, btnAlunos, btnEncerrar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_colaborador_dash_board, container, false);

        btnCursos = (Button) view.findViewById(R.id.btnCursos);
        btnCursos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carregarFragmentCursos();
            }
        });

        btnAlunos = (Button) view.findViewById(R.id.btnAlunos);
        btnAlunos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carregarFragmentAlunos();
            }
        });

        btnEncerrar = (Button) view.findViewById(R.id.btnEncerrar);
        btnEncerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                encerrarDashboardColaborador();
            }
        });
        carregarFragmentCursos();
        return view;
    }

    private void carregarFragmentCursos() {
        btnCursos.setTextSize(20);
        btnAlunos.setTextSize(14);
        Fragment fragment = new CursosColaboradorDashBoardFragment();
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.frameCursosAlunos, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void carregarFragmentAlunos() {
        btnCursos.setTextSize(14);
        btnAlunos.setTextSize(20);
        Fragment fragment = new AlunosColaboradorDashBoardFragment();
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.frameCursosAlunos, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void encerrarDashboardColaborador() {
        Fragment fragment = new ColaboradorFragment();
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment_content_main, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
