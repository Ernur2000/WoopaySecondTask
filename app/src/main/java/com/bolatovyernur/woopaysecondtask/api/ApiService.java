package com.bolatovyernur.woopaysecondtask.api;

import com.bolatovyernur.woopaysecondtask.model.AuthRequest;
import com.bolatovyernur.woopaysecondtask.model.AuthResponse;
import com.bolatovyernur.woopaysecondtask.category.CategoryRequest;
import com.bolatovyernur.woopaysecondtask.model.ErrorResponses;
import com.bolatovyernur.woopaysecondtask.model.PasswordRequest;
import com.bolatovyernur.woopaysecondtask.model.RegisterRequest;
import com.bolatovyernur.woopaysecondtask.model.SmsRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @POST("auth")
    Call<AuthResponse> login(@Body AuthRequest authRequest);

    @POST("registration/create-account")
    Call<ErrorResponses> register(@Body RegisterRequest registerRequest);

    @POST("registration/activate-account")
    Call<ErrorResponses> sendSms(@Body SmsRequest smsRequest);

    @POST("registration/set-password")
    Call<ErrorResponses> createPassword(@Body PasswordRequest passwordRequest);

    @GET("category/")
    Call<CategoryRequest> getCategory();
}
