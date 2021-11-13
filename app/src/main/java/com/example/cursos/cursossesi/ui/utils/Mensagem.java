package com.example.cursos.cursossesi.ui.utils;

import android.app.Activity;
import android.view.Gravity;
import android.widget.Toast;

public class Mensagem {
    public static void enviarMensagem(Activity context, String mensagem) {
        Toast toast = Toast.makeText(context, mensagem, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }
}
