package com.bolatovyernur.woopaysecondtask.api;

import com.bolatovyernur.woopaysecondtask.AbstractPresenter;
import com.bolatovyernur.woopaysecondtask.authentication.AuthRequest;
import com.bolatovyernur.woopaysecondtask.util.Constants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    private static Retrofit retrofit;
    private Api() {}
    public static Retrofit getRequest(String endPoint){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient =  new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(endPoint)
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }
}
