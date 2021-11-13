package com.example.cursos.cursossesi.services;

import com.example.cursos.cursossesi.models.Curso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CursoServiceImpl {
    private Retrofit retrofit;
    private CursoService cursoService;
    private List<Curso> listaCurso = new ArrayList<>();

    public CursoServiceImpl() {
       retrofit = new Retrofit.Builder()
           .baseUrl("https://cadastros.sesi.agamenon.eti.br/")
           .addConverterFactory(GsonConverterFactory.create())
           .build();
        cursoService = retrofit.create(CursoService.class);
    }

    public List<Curso> obterTodos() {
        listaCurso = new ArrayList<>();
        Call<List<Curso>> call = cursoService.obterTodos();
        call.enqueue(new Callback<List<Curso>>() {
            @Override
            public void onResponse(Call<List<Curso>> call, Response<List<Curso>> response) {
                if (response.isSuccessful())
                    listaCurso = response.body();
            }

            @Override
            public void onFailure(Call<List<Curso>> call, Throwable t) {
            }
        });
        return listaCurso;
    }
}
