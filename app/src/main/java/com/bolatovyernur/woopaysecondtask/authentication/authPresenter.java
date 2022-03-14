package com.bolatovyernur.woopaysecondtask.authentication;

import android.content.Context;
import android.widget.Toast;

import com.bolatovyernur.woopaysecondtask.AbstractPresenter;
import com.bolatovyernur.woopaysecondtask.api.ApiService;
import com.bolatovyernur.woopaysecondtask.util.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class authPresenter extends AbstractPresenter {
    int statusCode;
    ApiService apiService;
    public void login(){
        AuthRequest authRequest = new AuthRequest();
        authRequest.setLogin("login");
        authRequest.setPassword("pass");
        apiService = Constants.apiService;
        Call<AuthRequest> authRequestCall = apiService.login(authRequest);
        authRequestCall.enqueue(new Callback<AuthRequest>() {
            @Override
            public void onResponse(Call<AuthRequest> call, Response<AuthRequest> response) {
                statusCode = response.code();
            }

            @Override
            public void onFailure(Call<AuthRequest> call, Throwable t) {

            }
        });
    }
    @Override
    protected void onSuccess(Context obj) {
        if (statusCode==200){
            Toast.makeText(obj, "Successfully authorized!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onError(Context obj) {
        if (statusCode==401){
            Toast.makeText(obj, "Unauthorized user", Toast.LENGTH_SHORT).show();
        }
    }
}
