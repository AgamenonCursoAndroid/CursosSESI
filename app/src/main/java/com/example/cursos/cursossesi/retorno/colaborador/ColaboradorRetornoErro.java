package com.example.cursos.cursossesi.retorno.colaborador;

import com.example.cursos.cursossesi.retorno.ListaDeErros;

public class ColaboradorRetornoErro {
    private String title;
    private int status;
    private ListaDeErros errors;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ListaDeErros getErrors() {
        return errors;
    }

    public void setErrors(ListaDeErros errors) {
        this.errors = errors;
    }

}

