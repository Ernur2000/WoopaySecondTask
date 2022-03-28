package com.bolatovyernur.woopaysecondtask.api;

import com.bolatovyernur.woopaysecondtask.authentication.AuthRequest;
import com.bolatovyernur.woopaysecondtask.category.CategoryRequest;
import com.bolatovyernur.woopaysecondtask.registration.ErrorResponse;
import com.bolatovyernur.woopaysecondtask.registration.PasswordRequest;
import com.bolatovyernur.woopaysecondtask.registration.RegisterRequest;
import com.bolatovyernur.woopaysecondtask.registration.SmsRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @POST("login/")
    Call<AuthRequest> login(@Body AuthRequest authRequest);

    @POST("registration/create-account")
    Call<ErrorResponse> register(@Body RegisterRequest registerRequest);

    @POST("registration/activate-account")
    Call<List<ErrorResponse>> sendSms(@Body SmsRequest smsRequest);

    @POST("registration/set-password")
    Call<List<ErrorResponse>> createPassword(@Body PasswordRequest passwordRequest);

    @GET("category/")
    Call<CategoryRequest> getCategory();
}
