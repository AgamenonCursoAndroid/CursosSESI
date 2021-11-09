package com.example.cursos.cursossesi.ui.logar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cursos.cursossesi.MainActivity;
import com.example.cursos.cursossesi.R;
import com.example.cursos.cursossesi.ui.aluno.AlunoFragment;
import com.example.cursos.cursossesi.ui.aluno.CadastrarAlunoFragment;
import com.example.cursos.cursossesi.ui.colaborador.ColaboradorFragment;

public class LogarFragment extends Fragment implements View.OnClickListener {
    private String tipoLogin = null;
    private Button btnEntrar, btnRetornar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            tipoLogin = getArguments().getString("TipoLogin");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_logar, container, false);
        btnEntrar = (Button)view.findViewById(R.id.btnEntrar);
        btnEntrar.setOnClickListener(this);

        btnRetornar = (Button)view.findViewById(R.id.btnRetornar);
        btnRetornar.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRetornar:
                retornar();
                break;

            case R.id.btnEntrar:
                efetuarLogon();
                break;
        }

    }

    private void efetuarLogon() {
        Fragment fragment = null;
        fragment = new LogarFragment();
        Bundle args = new Bundle();
        args.putString("TipoLogin", "Aluno");
        fragment.setArguments(args);
        replaceFragment(fragment);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Logar como Aluno");
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment_content_main, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void retornar() {
        String title = null;
        Fragment fragment = null;
        if (tipoLogin == "Aluno")
        {
            fragment = new AlunoFragment();
            title = "Aluno";
        }
        else
        {
            fragment = new ColaboradorFragment();
            title = "Colaborador";
        }
        replaceFragment(fragment);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(title);
    }

}