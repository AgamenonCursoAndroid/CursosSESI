package com.example.cursos.cursossesi.ui.utils;

import android.app.Activity;
import android.content.SharedPreferences;

import androidx.datastore.preferences.core.Preferences;

import com.example.cursos.cursossesi.retorno.RetornoCadastroOk;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TokenAcesso {
    private static final String ARQ_LOGIN = "ArqLogin";
    private SharedPreferences sp;

    public void salvarToken(Activity app, RetornoCadastroOk retornoCadastroOk) {
        sp = app.getSharedPreferences(ARQ_LOGIN, 0);
        SharedPreferences.Editor editor = sp.edit();
        Gson gson = new GsonBuilder().create();
        String retorno = gson.toJson(retornoCadastroOk);
        editor.putString("retornoLogin",retorno);
        editor.commit();
    }

    public RetornoCadastroOk recuperarToken(Activity app) {
        sp = app.getSharedPreferences(ARQ_LOGIN, 0);
        if (sp.contains("retornoLogin")) {
            String retorno = sp.getString("retornoLogin", "");
            Gson gson = new Gson();
            RetornoCadastroOk ret = gson.fromJson(retorno, RetornoCadastroOk.class);
            return ret;
        }
        else {
            return null;
        }
    }
}
