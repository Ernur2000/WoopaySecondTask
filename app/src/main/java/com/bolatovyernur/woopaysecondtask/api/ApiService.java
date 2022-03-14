package com.bolatovyernur.woopaysecondtask.api;

import com.bolatovyernur.woopaysecondtask.authentication.AuthRequest;
import com.bolatovyernur.woopaysecondtask.category.CategoryRequest;
import com.bolatovyernur.woopaysecondtask.registration.RegisterRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @POST("login/")
    Call<AuthRequest> login(@Body AuthRequest authRequest);

    @POST("register/")
    Call<RegisterRequest> register(@Body RegisterRequest registerRequest);

    @GET("category/")
    Call<CategoryRequest> getCategory();
}
