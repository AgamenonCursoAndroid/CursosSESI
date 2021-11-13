package com.example.cursos.cursossesi.ui.utils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {
    private String accessToken = "";

    public TokenInterceptor(String token) {
        this.accessToken = token;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request().newBuilder()
                .header("Authorization", "Bearer " + accessToken)
                .build();
        return chain.proceed(request);
    }
}
