package com.example.cursos.cursossesi.ui.aluno;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cursos.cursossesi.ui.activity.MainActivity;
import com.example.cursos.cursossesi.R;
import com.example.cursos.cursossesi.ui.logar.LogarFragment;

public class AlunoFragment extends Fragment implements View.OnClickListener {
    Button btnCadastrarAluno, btnEntrarComoAluno;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_aluno, container, false);
        btnCadastrarAluno = view.findViewById(R.id.btnCadastrarAluno);
        btnEntrarComoAluno = view.findViewById(R.id.btnEntrarComoColaborador);
        btnCadastrarAluno.setOnClickListener(this);
        btnEntrarComoAluno.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        Fragment fragment = null;
        switch (view.getId()) {
            case R.id.btnCadastrarAluno:
                fragment = new CadastrarAlunoFragment();
                replaceFragment(fragment);
                ((MainActivity) getActivity()).getSupportActionBar().setTitle("Novo Aluno");
                break;

            case R.id.btnEntrarComoColaborador:
                fragment = new LogarFragment();
                Bundle args = new Bundle();
                args.putString("TipoLogin", "Aluno");
                fragment.setArguments(args);
                replaceFragment(fragment);
                ((MainActivity) getActivity()).getSupportActionBar().setTitle("Logar como Aluno");
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
