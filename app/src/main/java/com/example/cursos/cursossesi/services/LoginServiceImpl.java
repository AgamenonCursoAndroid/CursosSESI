package com.example.cursos.cursossesi.services;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.cursos.cursossesi.models.Login;
import com.example.cursos.cursossesi.retorno.RetornoCadastroOk;
import com.example.cursos.cursossesi.ui.activity.MainActivity;
import com.example.cursos.cursossesi.ui.colaborador.ColaboradorDashBoardFragment;
import com.example.cursos.cursossesi.ui.utils.Mensagem;
import com.example.cursos.cursossesi.ui.utils.TokenAcesso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginServiceImpl extends AppCompatActivity {
    private Retrofit retrofit;
    private LoginService loginService;
    private RetornoCadastroOk retornoCadastroOk = null;

    public LoginServiceImpl() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://identidade.sesi.agamenon.eti.br/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        loginService = retrofit.create(LoginService.class);
    }

    public RetornoCadastroOk logar(Login login) {
        retornoCadastroOk = null;
        LoginService service = retrofit.create(LoginService.class);
        Call<RetornoCadastroOk> call = service.logar(login);
        call.enqueue(new Callback<RetornoCadastroOk>() {
            @Override
            public void onResponse(Call<RetornoCadastroOk> call, Response<RetornoCadastroOk> response) {
                if (response.isSuccessful()) {
                    retornoCadastroOk = response.body();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Usuárioadadasd  e/ou senha inválidos", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<RetornoCadastroOk> call, Throwable t) {
            }
        });
        return retornoCadastroOk;
    }
}
