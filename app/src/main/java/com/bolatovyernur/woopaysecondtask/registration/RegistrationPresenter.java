package com.bolatovyernur.woopaysecondtask.registration;

import com.bolatovyernur.woopaysecondtask.AbstractPresenter;
import com.bolatovyernur.woopaysecondtask.api.Api;
import com.bolatovyernur.woopaysecondtask.api.ResponseHandler;

import retrofit2.Call;
import retrofit2.Response;

public class RegistrationPresenter extends AbstractPresenter {
    public void register(int phoneNum, String login, String password) {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setPhoneNum(phoneNum);
        registerRequest.setLogin(login);
        registerRequest.setPassword(password);
        Call<RegisterRequest> registerRequestCall = Api.getInstance().getApi().register(registerRequest);
        registerRequestCall.enqueue(new ResponseHandler<RegisterRequest>() {

        });
    }

    @Override
    public void onSuccess(Response response) {

    }

    @Override
    public void onError(Response response) {

    }
}
