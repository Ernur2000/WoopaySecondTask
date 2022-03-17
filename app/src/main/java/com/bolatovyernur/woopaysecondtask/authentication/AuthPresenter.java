package com.bolatovyernur.woopaysecondtask.authentication;

import com.bolatovyernur.woopaysecondtask.AbstractPresenter;
import com.bolatovyernur.woopaysecondtask.api.Api;
import com.bolatovyernur.woopaysecondtask.api.ResponseHandler;

import retrofit2.Call;
import retrofit2.Response;

public class AuthPresenter extends AbstractPresenter {
    public void login(String login, String password) {
        AuthRequest authRequest = new AuthRequest();
        authRequest.setLogin(login);
        authRequest.setPassword(password);
        Call<AuthRequest> authRequestCall = Api.getInstance().getApi().login(authRequest);
        authRequestCall.enqueue(new ResponseHandler<AuthRequest>() {
        });
    }


    @Override
    public void onSuccess(Response response) {

    }

    @Override
    public void onError(Response response) {

    }
}
