package com.example.cursos.cursossesi.services;

import com.example.cursos.cursossesi.models.Curso;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CursoService {
    @GET("api/curso/obter-todos-os-cursos")
    Call<List<Curso>> obterTodos();
}
