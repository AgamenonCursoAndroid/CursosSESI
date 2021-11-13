package com.example.cursos.cursossesi.retorno;

import java.util.List;

public class TokenUsuario {
    private String id;
    private String userName;
    List<Claims> claims;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Claims> getClaims() {
        return claims;
    }

    public void setClaims(List<Claims> claims) {
        this.claims = claims;
    }
}
