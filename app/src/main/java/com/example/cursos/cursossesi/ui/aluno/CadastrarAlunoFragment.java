package com.example.cursos.cursossesi.ui.aluno;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.cursos.cursossesi.ui.activity.MainActivity;
import com.example.cursos.cursossesi.R;
import com.example.cursos.cursossesi.models.Cep;
import com.example.cursos.cursossesi.services.CepService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CadastrarAlunoFragment extends Fragment implements View.OnClickListener {
    ImageView imgPesquisarCep;
    EditText txtCep, txtEndereco, txtBairro, txtCidade, txtUf;
    Retrofit retrofit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cadastrar_aluno, container, false);

        Button btnVoltar = (Button) view.findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(this);

        Button btnSalvar = (Button) view.findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(this);

        definirInstanciaDosObjetosDaView(view);

        return view;
    }

    private void definirInstanciaDosObjetosDaView(View view) {
        txtCep = (EditText) view.findViewById(R.id.txtCep);
        txtEndereco =(EditText) view.findViewById(R.id.txtEndereco);
        txtBairro = (EditText) view.findViewById(R.id.txtBairro);
        txtCidade = (EditText) view.findViewById(R.id.txtCidade);
        txtUf = (EditText) view.findViewById(R.id.txtUf);

        imgPesquisarCep = (ImageView) view.findViewById(R.id.imgPesquisarCep);
        imgPesquisarCep.setOnClickListener(this);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://viacep.com.br/ws/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgPesquisarCep:
                pesquisarCep();
                break;

            case R.id.btnVoltar:
                prepararRetorno();
                break;

            case R.id.btnSalvar:
                // todo: Salvar dados;
                prepararRetorno();
                break;
        }
    }

    private void pesquisarCep() {
        CepService cepService = retrofit.create(CepService.class);
        String cep = txtCep.getText().toString();
        Call<Cep> call = cepService.obterCep(cep);
        call.enqueue(new Callback<Cep>() {
            @Override
            public void onResponse(Call<Cep> call, Response<Cep> response) {
                if (response.isSuccessful()) {
                    Cep cep = response.body();
                    txtEndereco.setText(cep.getLogradouro());
                    txtBairro.setText(cep.getBairro());
                    txtCidade.setText(cep.getLocalidade());
                    txtUf.setText(cep.getUf());
                }
            }

            @Override
            public void onFailure(Call<Cep> call, Throwable t) {

            }
        });
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment_content_main, fragment);
        transaction.commit();
    }

    private void prepararRetorno() {
        Fragment fragment = null;
        fragment = new AlunoFragment();
        replaceFragment(fragment);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Aluno");
    }

}