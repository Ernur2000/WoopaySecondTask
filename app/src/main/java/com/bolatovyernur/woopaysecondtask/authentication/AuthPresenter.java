package com.bolatovyernur.woopaysecondtask.authentication;

import android.util.Log;

import androidx.annotation.NonNull;
import com.bolatovyernur.woopaysecondtask.AbstractPresenter;
import com.bolatovyernur.woopaysecondtask.api.Api;
import com.bolatovyernur.woopaysecondtask.api.ResponseHandler;

import retrofit2.Call;
import retrofit2.Response;

public class AuthPresenter extends AbstractPresenter {
    int statusCode;
    public void login(String login, String password){
        AuthRequest authRequest = new AuthRequest();
        authRequest.setLogin(login);
        authRequest.setPassword(password);
        Call<AuthRequest> authRequestCall = Api.getInstance().getApi().login(authRequest);
        authRequestCall.enqueue(new ResponseHandler<AuthRequest>(){
            @Override
            public void onResponse(@NonNull Call<AuthRequest> call, @NonNull Response<AuthRequest> response) {
                super.onResponse(call, response);
                if (response.code() == 0000) {
                    Log.d("Success", "post success to Api");
                }
            }
        });
    }

    @Override
    protected void onSuccess(Object obj) {
        if (statusCode == 200) {
            // Something
        }
    }

    @Override
    protected void onError(Object obj) {
        if (statusCode == 401) {
            // Something
        }
    }
}
