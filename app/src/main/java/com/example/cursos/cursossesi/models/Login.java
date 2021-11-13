package com.example.cursos.cursossesi.models;

public class Login {
    private String userName;
    private String senha;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Login(String userName, String senha) {
        this.userName = userName;
        this.senha = senha;
    }

}
