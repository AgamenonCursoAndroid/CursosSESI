package com.example.cursos.cursossesi.models;

public class Colaborador {
    private String nome;
    private String email;
    private String telefone;
    private String userName;
    private String senha;
    private String confirmacaoSenha;

    public Colaborador(String nome, String email, String telefone, String userName, String senha, String confirmacaoSenha) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.userName = userName;
        this.senha = senha;
        this.confirmacaoSenha = confirmacaoSenha;
    }


}
