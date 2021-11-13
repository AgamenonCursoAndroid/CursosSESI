package com.example.cursos.cursossesi.models;

public class Curso {
    private String id;
    private String nome;
    private String dataDeInicio;
    private String situacao;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataDeInicio() {
        return dataDeInicio;
    }

    public void setDataDeInicio(String dataDeInicio) {
        this.dataDeInicio = dataDeInicio;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Curso(String id, String nome, String dataDeInicio, String situacao) {
        this.id = id;
        this.nome = nome;
        this.dataDeInicio = dataDeInicio;
        this.situacao = situacao;
    }
}
