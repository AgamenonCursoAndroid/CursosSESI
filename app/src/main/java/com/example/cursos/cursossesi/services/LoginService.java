package com.example.cursos.cursossesi.services;

import com.example.cursos.cursossesi.models.Colaborador;
import com.example.cursos.cursossesi.models.Login;
import com.example.cursos.cursossesi.retorno.RetornoCadastroOk;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {
    @POST("api/identidade/autenticar")
    Call<RetornoCadastroOk> logar(@Body Login login);
}
