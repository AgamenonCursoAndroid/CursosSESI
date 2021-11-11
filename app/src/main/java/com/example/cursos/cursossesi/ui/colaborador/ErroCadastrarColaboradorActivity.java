package com.example.cursos.cursossesi.ui.colaborador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.cursos.cursossesi.R;

public class ErroCadastrarColaboradorActivity extends AppCompatActivity {
    ListView listErros;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erro_cadastrar_colaborador);

        listErros = (ListView) findViewById(R.id.listErros);
        Intent intent = getIntent();
        String[] erros = intent.getStringArrayExtra("Erros");
        mostrarErros(erros);
    }

    private void mostrarErros(String[] erros) {
        // Criar Adapter para a listview;
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                erros
        );
        // Adicionar adaptador para lista;
        listErros.setAdapter(adaptador);
    }

    public void fecharErrosColaborador(View view) {
        finish();
    }


}