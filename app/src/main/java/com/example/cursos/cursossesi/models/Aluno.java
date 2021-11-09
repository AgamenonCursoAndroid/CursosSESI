package com.example.cursos.cursossesi.models;

import com.example.cursos.cursossesi.valueObjects.Endereco;

public class Aluno {
    private String userName;
    private String nome;
    private String email;
    private String telefone;
    private Endereco endereco;

    public String getUserName() {
        return userName;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public Aluno(String userName, String nome, String email, String telefone, Endereco endereco) {
        this.userName = userName;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }
}
