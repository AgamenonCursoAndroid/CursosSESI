package com.example.cursos.cursossesi.ui.utils;

import android.text.InputType;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.cursos.cursossesi.R;

public class VisibilidadeSenha {
    public static boolean definir(EditText txtPwd, ImageView img, boolean mostrarSenha) {
        mostrarSenha = !mostrarSenha;
        if (mostrarSenha) {
            img.setImageResource(R.drawable.view);
            txtPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        }
        else {
            img.setImageResource(R.drawable.hidden);
            txtPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
        return mostrarSenha;
    }
}
