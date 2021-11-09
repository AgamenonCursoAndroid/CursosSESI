package com.example.cursos.cursossesi.services;

public interface IService<T> {
    public boolean incluir(T obj);
    public boolean alterar(T obj);
    public boolean excluir(T obj);
    public boolean listar(T obj);
}
