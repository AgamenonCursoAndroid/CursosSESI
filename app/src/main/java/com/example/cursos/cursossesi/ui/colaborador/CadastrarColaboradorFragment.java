package com.example.cursos.cursossesi.ui.colaborador;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cursos.cursossesi.MainActivity;
import com.example.cursos.cursossesi.R;
import com.example.cursos.cursossesi.models.Colaborador;
import com.example.cursos.cursossesi.retorno.ColaboradorRetorno;
import com.example.cursos.cursossesi.services.ColaboradorService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CadastrarColaboradorFragment extends Fragment implements View.OnClickListener {
    Boolean retorno;
    EditText txtNome, txtEmail, txtTelefone, txtUsuario, txtSenha, txtConfirmaSenha;
    Retrofit retrofit;
    ListView listErros;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cadastrar_colaborador, container, false);

        Button btnVoltar = (Button) view.findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(this);

        Button btnSalvar = (Button) view.findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(this);

        definirInstanciaDosObjetosDaView(view);

        return view;
    }

    private void definirInstanciaDosObjetosDaView(View view) {
        txtNome = (EditText) view.findViewById(R.id.txtNome);
        txtEmail = (EditText) view.findViewById(R.id.txtEmail);
        txtTelefone = (EditText) view.findViewById(R.id.txtTelefone);
        txtUsuario = (EditText) view.findViewById(R.id.txtUsuario);
        txtSenha = (EditText) view.findViewById(R.id.txtSenha);
        txtConfirmaSenha = (EditText) view.findViewById(R.id.txtConfirmaSenha);
        retrofit = new Retrofit.Builder()
                .baseUrl("https://cadastros.sesi.agamenon.eti.br/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        listErros = (ListView) view.findViewById(R.id.listErros);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnVoltar:
                prepararRetorno();
                break;

            case R.id.btnSalvar:
                if (!consistirDados()) return;
                if (!salvarColaborador()) return;
                break;
        }
    }

    private void enviarMensagem(String mensagem) {
        Toast toast = Toast.makeText((MainActivity)getContext(), mensagem, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    private Boolean salvarColaborador() {
        retorno = false;
        String nome = txtNome.getText().toString();
        String email = txtEmail.getText().toString();
        String telefone = txtTelefone.getText().toString();
        String usuario = txtUsuario.getText().toString();
        String senha = txtSenha.getText().toString();
        String confirmacaoSenha = txtConfirmaSenha.getText().toString();
        Colaborador colaborador = new Colaborador(nome, email, telefone, usuario, senha, confirmacaoSenha);

        ColaboradorService service = retrofit.create(ColaboradorService.class);
        Call<Colaborador> call = service.incluir(colaborador);
        call.enqueue(new Callback<Colaborador>() {
            @Override
            public void onResponse(Call<Colaborador> call, Response<Colaborador> response) {
                if (response.isSuccessful()) {
                    prepararRetorno();
                    retorno = true;
                }
                if (response.code() == 400) {
                    Gson gson = new GsonBuilder().create();
                    ColaboradorRetorno colaboradorRetorno = new ColaboradorRetorno();
                    try {
                        colaboradorRetorno = gson.fromJson(response.errorBody().string(),ColaboradorRetorno.class);
                        mostrarErros(colaboradorRetorno.getErrors().getMensagens());
                    } catch (IOException e) {
                        // handle failure to read error
                    }
                }
            }

            @Override
            public void onFailure(Call<Colaborador> call, Throwable t) {

                //enviarMensagem("Erro na gravação dos dados");
            }
        });

        return retorno;
    }

    private void mostrarErros(String[] erros) {
        // Criar Adapter para a listview;
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                erros
        );
        // Adicionar adaptador para lista;
        listErros.setAdapter(adaptador);
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment_content_main, fragment);
        transaction.commit();
    }

    private void prepararRetorno() {
        Fragment fragment = null;
        fragment = new ColaboradorFragment();
        replaceFragment(fragment);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Colaborador");
    }

    private Boolean consistirDados() {
        Boolean resultado = true;
        if (TextUtils.isEmpty(txtNome.getText())) {
            resultado = false;
            txtNome.setError("Informe seu nome");
            txtNome.requestFocus();
        }
        if (TextUtils.isEmpty(txtEmail.getText())) {
            resultado = false;
            txtEmail.setError("Informe seu E-Mail");
            txtEmail.requestFocus();
        }
        else
        {
            if (!isValidEmail(txtEmail.getText())) {
                resultado = false;
                txtEmail.setError("E-mail incorreto");
                txtEmail.requestFocus();
            }
        }
        if (TextUtils.isEmpty(txtTelefone.getText())) {
            resultado = false;
            txtTelefone.setError("Informe seu telefone");
            txtTelefone.requestFocus();
        }
        if (TextUtils.isEmpty(txtUsuario.getText())) {
            resultado = false;
            txtUsuario.setError("Informe seu Usuário");
            txtUsuario.requestFocus();
        }
        else
        {
            if (!isValidEmail(txtUsuario.getText())) {
                resultado = false;
                txtUsuario.setError("Informe seu usuãrio no padrão de e-mail");
                txtUsuario.requestFocus();
            }
        }
        if (TextUtils.isEmpty(txtSenha.getText())) {
            resultado = false;
            txtSenha.setError("Informe sua senha");
            txtSenha.requestFocus();
        }
        else
        {
            if (txtSenha.getText().length() < 6) {
                resultado = false;
                txtSenha.setError("Senha deve ter pelo menos 6 caracteres");
                txtSenha.requestFocus();
            }
        }
        if (TextUtils.isEmpty(txtConfirmaSenha.getText())) {
            resultado = false;
            txtConfirmaSenha.setError("Informe a confirmação da sua senha");
            txtConfirmaSenha.requestFocus();
        }
        else
        {
            if (txtSenha.getText().length() > 5 && !TextUtils.isEmpty(txtSenha.getText())) {
                String senha = txtSenha.getText().toString();
                String confirmacao = txtConfirmaSenha.getText().toString();
                if (!Objects.equals(senha, confirmacao))
                {
                    resultado = false;
                    txtConfirmaSenha.setError("as senhas não conferem - Verifique");
                    txtConfirmaSenha.requestFocus();
                }
            }
        }
        return resultado;
    }

    public boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

}