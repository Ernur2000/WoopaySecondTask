package com.bolatovyernur.woopaysecondtask.registration;

import android.content.Context;
import android.text.Editable;

import androidx.annotation.NonNull;

import com.bolatovyernur.woopaysecondtask.AbstractPresenter;
import com.bolatovyernur.woopaysecondtask.api.Api;
import com.bolatovyernur.woopaysecondtask.api.ResponseHandler;

import retrofit2.Call;
import retrofit2.Response;

public class RegistrationPresenter extends AbstractPresenter {
    public void register(int phoneNum, String login, String password){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setPhoneNum(phoneNum);
        registerRequest.setLogin(login);
        registerRequest.setPassword(password);
        Call<RegisterRequest> registerRequestCall = Api.getInstance().getApi().register(registerRequest);
        registerRequestCall.enqueue(new ResponseHandler<RegisterRequest>(){
            @Override
            public void onResponse(@NonNull Call<RegisterRequest> call, @NonNull Response<RegisterRequest> response) {
                super.onResponse(call, response);
            }
        });
    }
    @Override
    protected void onSuccess(Object obj) {

    }

    @Override
    protected void onError(Object obj) {

    }
}
