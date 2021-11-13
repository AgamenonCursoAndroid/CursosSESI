package com.example.cursos.cursossesi.ui.colaborador;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.cursos.cursossesi.R;
import com.example.cursos.cursossesi.adapter.CursosAdapter;
import com.example.cursos.cursossesi.models.Curso;
import com.example.cursos.cursossesi.retorno.RetornoCadastroOk;
import com.example.cursos.cursossesi.services.CursoService;
import com.example.cursos.cursossesi.ui.utils.TokenAcesso;
import com.example.cursos.cursossesi.ui.utils.TokenInterceptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CursosColaboradorDashBoardFragment extends Fragment {
    private List<Curso> cursos = new ArrayList<>();
    private Retrofit retrofit;
    private RecyclerView recyclerCursos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cursos_colaborador_dash_board, container, false);

        TokenAcesso token = new TokenAcesso();
        RetornoCadastroOk retornoCadastroOk = token.recuperarToken(getActivity());
        TokenInterceptor interceptor = new TokenInterceptor(retornoCadastroOk.getAccessToken());
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("https://cadastros.sesi.agamenon.eti.br/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        obterCursos(view);

        return view;
    }

    private List<Curso> obterCursos(View view) {
        cursos = new ArrayList<>();
        CursoService service = retrofit.create(CursoService.class);
        Call<List<Curso>> call = service.obterTodos();
        call.enqueue(new Callback<List<Curso>>() {
            @Override
            public void onResponse(Call<List<Curso>> call, Response<List<Curso>> response) {
                if (response.isSuccessful())
                {
                    cursos = response.body();
                    montarLista(view);
                }
                else
                {
                    try {
                        Log.d("Erro Cursos", "Erro Response: " + response.code() + response.errorBody().string());
                    } catch (IOException e) {
                        Log.d("Erro Try Cursos", "Erro Response: " + e.getMessage().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Curso>> call, Throwable t) {
                Log.d("Erro Throw Cursos", "Erro throw: " + t.getMessage().toString());
            }
        });
        return cursos;
    }

    private void montarLista(View view) {
        recyclerCursos = (RecyclerView) view.findViewById(R.id.recyclerCursos);
        // definir layout
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerCursos.setLayoutManager(layoutManager);
        // definir adapter
        CursosAdapter adapter = new CursosAdapter(cursos);
        recyclerCursos.addItemDecoration( new DividerItemDecoration(view.getContext(), LinearLayout.VERTICAL));
        recyclerCursos.setAdapter(adapter);
    }

}