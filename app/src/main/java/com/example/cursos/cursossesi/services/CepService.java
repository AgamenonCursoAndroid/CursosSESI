package com.example.cursos.cursossesi.services;

import com.example.cursos.cursossesi.models.Cep;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CepService {
    @GET("{cep}/json/")
    Call<Cep> obterCep(@Path("cep") String cep);
}
