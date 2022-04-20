package com.bolatovyernur.woopaysecondtask.api;

import com.bolatovyernur.woopaysecondtask.model.TopServiceResponse;
import com.bolatovyernur.woopaysecondtask.model.AuthRequest;
import com.bolatovyernur.woopaysecondtask.model.AuthResponse;
import com.bolatovyernur.woopaysecondtask.model.CategoryResponse;
import com.bolatovyernur.woopaysecondtask.model.ErrorResponses;
import com.bolatovyernur.woopaysecondtask.model.PasswordRequest;
import com.bolatovyernur.woopaysecondtask.model.RegisterRequest;
import com.bolatovyernur.woopaysecondtask.model.SmsRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {
    @POST("auth")
    Call<AuthResponse> login(@Body AuthRequest authRequest);

    @POST("registration/create-account")
    Call<ErrorResponses> register(@Body RegisterRequest registerRequest);

    @POST("registration/activate-account")
    Call<List<ErrorResponses>> sendSms(@Body SmsRequest smsRequest);

    @POST("registration/set-password")
    Call<List<ErrorResponses>> createPassword(@Body PasswordRequest passwordRequest);

    @GET("service-category")
    Call<List<CategoryResponse>> getCategory();

    @GET("service-top")
    Call<List<TopServiceResponse>> getTopService(@Header("Authorization") String token);
}
