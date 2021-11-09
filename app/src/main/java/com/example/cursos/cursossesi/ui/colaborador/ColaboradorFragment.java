package com.example.cursos.cursossesi.ui.colaborador;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cursos.cursossesi.MainActivity;
import com.example.cursos.cursossesi.R;
import com.example.cursos.cursossesi.ui.aluno.CadastrarAlunoFragment;
import com.example.cursos.cursossesi.ui.logar.LogarFragment;

public class ColaboradorFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_colaborador, container, false);
        Button btnEfetuarCadastro = (Button) view.findViewById(R.id.btnEfetuarCadastro);
        btnEfetuarCadastro.setOnClickListener(this);

        Button btnEntrarComoColaborador = (Button) view.findViewById(R.id.btnEntrarComoColaborador);
        btnEntrarComoColaborador.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        Fragment fragment = null;
        switch (view.getId()) {
            case R.id.btnEfetuarCadastro:
                fragment = new CadastrarColaboradorFragment();
                replaceFragment(fragment);
                ((MainActivity) getActivity()).getSupportActionBar().setTitle("Novo Colaborador");
                break;

            case R.id.btnEntrarComoColaborador:
                fragment = new LogarFragment();
                Bundle args = new Bundle();
                args.putString("TipoLogin", "Colaborador");
                fragment.setArguments(args);
                replaceFragment(fragment);
                ((MainActivity) getActivity()).getSupportActionBar().setTitle("Logar como Colaborador");
                break;
        }

    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment_content_main, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}