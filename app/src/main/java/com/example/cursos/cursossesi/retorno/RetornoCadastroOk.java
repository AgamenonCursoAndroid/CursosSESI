package com.example.cursos.cursossesi.retorno;

import com.example.cursos.cursossesi.retorno.TokenUsuario;

import java.io.Serializable;

public class RetornoCadastroOk implements Serializable {
    private String accessToken;
    private int expiresIn;
    private TokenUsuario tokenUsuario;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public TokenUsuario getTokenUsuario() {
        return tokenUsuario;
    }

    public void setTokenUsuario(TokenUsuario tokenUsuario) {
        this.tokenUsuario = tokenUsuario;
    }
}

