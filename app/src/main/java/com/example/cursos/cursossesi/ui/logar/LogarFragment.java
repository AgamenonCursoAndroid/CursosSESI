package com.example.cursos.cursossesi.ui.logar;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.cursos.cursossesi.models.Login;
import com.example.cursos.cursossesi.retorno.RetornoCadastroOk;
import com.example.cursos.cursossesi.retorno.colaborador.ColaboradorRetornoErro;
import com.example.cursos.cursossesi.services.ColaboradorService;
import com.example.cursos.cursossesi.services.LoginService;
import com.example.cursos.cursossesi.services.LoginServiceImpl;
import com.example.cursos.cursossesi.ui.activity.MainActivity;
import com.example.cursos.cursossesi.R;
import com.example.cursos.cursossesi.ui.aluno.AlunoFragment;
import com.example.cursos.cursossesi.ui.colaborador.ColaboradorDashBoardFragment;
import com.example.cursos.cursossesi.ui.colaborador.ColaboradorFragment;
import com.example.cursos.cursossesi.ui.utils.Mensagem;
import com.example.cursos.cursossesi.ui.utils.TokenAcesso;
import com.example.cursos.cursossesi.ui.utils.VisibilidadeSenha;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LogarFragment extends Fragment implements View.OnClickListener {
    private boolean mostrarSenha = false;
    private boolean retorno;
    private String tipoLogin = null;
    private Button btnEntrar, btnRetornar;
    private ImageView imgViewSenha;
    private EditText txtUsuario, txtSenha;
    private Retrofit retrofit;
    private RetornoCadastroOk retornoCadastroOk = null;

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
        txtSenha = (EditText)view.findViewById(R.id.txtSenha);
        txtUsuario = (EditText)view.findViewById(R.id.txtUsuario);

        btnEntrar = (Button)view.findViewById(R.id.btnEntrar);
        btnEntrar.setOnClickListener(this);

        imgViewSenha = view.findViewById(R.id.imgViewSenha);
        imgViewSenha.setOnClickListener(this);
        mostrarSenha = false;

        btnRetornar = (Button)view.findViewById(R.id.btnRetornar);
        btnRetornar.setOnClickListener(this);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://identidade.sesi.agamenon.eti.br/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRetornar:
                retornar();
                break;

            case R.id.btnEntrar:
                if (!consistir()) return;
                if (!efetuarLogon((MainActivity)this.getActivity())) return;
                break;

            case R.id.imgViewSenha:
                mostrarSenha = VisibilidadeSenha.definir(txtSenha, imgViewSenha, mostrarSenha);
                break;
        }
    }

    private boolean consistir() {
        Boolean resultado = true;
        if (TextUtils.isEmpty(txtUsuario.getText())) {
            resultado = false;
            txtUsuario.setError("Informe seu userName");
            txtUsuario.requestFocus();
        }
        if (TextUtils.isEmpty(txtSenha.getText())) {
            resultado = false;
            txtSenha.setError("Informe sua senha");
            txtSenha.requestFocus();
        }
        return resultado;
    }

    private boolean efetuarLogon(Activity context) {
        if (tipoLogin == "Colaborador") {
            logarDashboardColaborador(context);
        }
        return true;
    }

    private boolean logarDashboardColaborador(Activity context) {
        /*
        String userName = txtUsuario.getText().toString();
        String senha = txtSenha.getText().toString();
        Login login = new Login(userName, senha);

        LoginServiceImpl service = new LoginServiceImpl();
        retornoCadastroOk = service.logar(login);
        if (retornoCadastroOk == null)
        {
            Mensagem.enviarMensagem(context, "Usuário e/ou senha Inválido(s)");
            return false;
        }
        carregarDashBoardColaborador(retornoCadastroOk);
        return true;
        */


        String userName = txtUsuario.getText().toString();
        String senha = txtSenha.getText().toString();
        Login login = new Login(userName, senha);
        LoginService service = retrofit.create(LoginService.class);
        Call<RetornoCadastroOk> call = service.logar(login);
        call.enqueue(new Callback<RetornoCadastroOk>() {
            @Override
            public void onResponse(Call<RetornoCadastroOk> call, Response<RetornoCadastroOk> response) {
                if (response.isSuccessful()) {
                    carregarDashBoardColaborador(response.body());
                    retorno = true;
                }
                else
                {
                    Mensagem.enviarMensagem(context, "Usuário e/ou senha Inválido(s)");
                }
            }
            @Override
            public void onFailure(Call<RetornoCadastroOk> call, Throwable t) {
            }
        });
        return retorno;
    }

    private void carregarDashBoardColaborador(RetornoCadastroOk retornoCadastroOk) {
        TokenAcesso token = new TokenAcesso();
        token.salvarToken(getActivity(), retornoCadastroOk);
        Fragment fragment = null;
        fragment = new ColaboradorDashBoardFragment();
        Bundle args = new Bundle();
        args.putSerializable("Token", retornoCadastroOk);
        fragment.setArguments(args);
        replaceFragment(fragment);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Dashboard Colaborador");
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
