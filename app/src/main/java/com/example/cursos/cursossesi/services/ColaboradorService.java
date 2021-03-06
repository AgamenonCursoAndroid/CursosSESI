package com.example.cursos.cursossesi.services;

import com.example.cursos.cursossesi.models.Colaborador;
import com.example.cursos.cursossesi.retorno.RetornoCadastroOk;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ColaboradorService  {
    @POST("api/colaborador/novo-colaborador")
    Call<RetornoCadastroOk> incluir(@Body Colaborador colaborador);
}
