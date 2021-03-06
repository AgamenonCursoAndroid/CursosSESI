package com.example.cursos.cursossesi.ui.colaborador;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cursos.cursossesi.retorno.RetornoCadastroOk;
import com.example.cursos.cursossesi.ui.activity.MainActivity;
import com.example.cursos.cursossesi.R;
import com.example.cursos.cursossesi.ui.logar.LogarFragment;
import com.example.cursos.cursossesi.ui.utils.TokenAcesso;

import java.util.Objects;

public class ColaboradorFragment extends Fragment {
    RetornoCadastroOk retornoCadastroOk;
    Button btnEfetuarCadastro, btnEntrarComoColaborador;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_colaborador, container, false);
        btnEfetuarCadastro = (Button) view.findViewById(R.id.btnEfetuarCadastro);
        btnEfetuarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new CadastrarColaboradorFragment();
                replaceFragment(fragment);
                ((MainActivity) getActivity()).getSupportActionBar().setTitle("Novo Colaborador");
            }
        });

        btnEntrarComoColaborador = (Button) view.findViewById(R.id.btnEntrarComoColaborador);
        btnEntrarComoColaborador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tokenValido()) {
                    entrarDashBoard();
                }
                else {
                    Fragment fragment = new LogarFragment();
                    Bundle args = new Bundle();
                    args.putString("TipoLogin", "Colaborador");
                    fragment.setArguments(args);
                    replaceFragment(fragment);
                    ((MainActivity) getActivity()).getSupportActionBar().setTitle("Logar como Colaborador");
                }
            }
        });

        return view;
    }

/*
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
                if (tokenValido())
                    entrarDashBoard();
                fragment = new LogarFragment();
                Bundle args = new Bundle();
                args.putString("TipoLogin", "Colaborador");
                fragment.setArguments(args);
                replaceFragment(fragment);
                ((MainActivity) getActivity()).getSupportActionBar().setTitle("Logar como Colaborador");
                break;
        }
    }
*/

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment_content_main, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private boolean tokenValido() {
        TokenAcesso token = new TokenAcesso();
        retornoCadastroOk = token.recuperarToken(this.getActivity());
        if (retornoCadastroOk == null) return false;
        return false;
    }

    private void entrarDashBoard() {
        Fragment fragment = new ColaboradorDashBoardFragment();
        Bundle args = new Bundle();
        args.putSerializable("Token", retornoCadastroOk);
        fragment.setArguments(args);
        replaceFragment(fragment);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Dashboard Colaborador");
    }

}