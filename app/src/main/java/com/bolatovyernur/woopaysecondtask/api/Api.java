package com.bolatovyernur.woopaysecondtask.api;

import com.bolatovyernur.woopaysecondtask.util.Constants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    private static Api mInstance;
    private final Retrofit mRetrofit;

    private Api() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
        mRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .client(okHttpClient)
                .build();
    }

    public static synchronized Api getInstance() {
        if (mInstance == null) {
            mInstance = new Api();
        }
        return mInstance;
    }

    public ApiService getApi() {
        return mRetrofit.create(ApiService.class);
    }
}
