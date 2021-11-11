package com.example.cursos.cursossesi.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.cursos.cursossesi.ui.activity.MainActivity;
import com.example.cursos.cursossesi.R;
import com.example.cursos.cursossesi.ui.aluno.AlunoFragment;
import com.example.cursos.cursossesi.ui.colaborador.ColaboradorFragment;

public class HomeFragment extends Fragment implements View.OnClickListener {
    Button btnAluno;
    Button btnColaborador;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        btnAluno = (Button)view.findViewById(R.id.btnAluno);
        btnColaborador = (Button)view.findViewById(R.id.btnColaborador);

        btnAluno.setOnClickListener(this);
        btnColaborador.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        Fragment fragment = null;
        switch (view.getId()) {
            case R.id.btnColaborador:
                fragment = new ColaboradorFragment();
                replaceFragment(fragment);
                ((MainActivity) getActivity()).getSupportActionBar().setTitle("Colaborador");
                break;

            case R.id.btnAluno:
                fragment = new AlunoFragment();
                replaceFragment(fragment);
                ((MainActivity) getActivity()).getSupportActionBar().setTitle("Aluno");
                break;
        }
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment_content_main, fragment);
        transaction.commit();
    }

}
