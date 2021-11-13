package com.example.cursos.cursossesi.ui.colaborador;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cursos.cursossesi.R;

public class AlunosColaboradorDashBoardFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alunos_colaborador_dash_board, container, false);
        return view;
    }
}