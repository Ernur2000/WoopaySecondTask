package com.bolatovyernur.woopaysecondtask.authentication;

import com.bolatovyernur.woopaysecondtask.AbstractPresenter;

public class AuthPresenter extends AbstractPresenter {
    AbstractPresenter abstractPresenter;

    public void login(String login, String password) {
        AuthRequest authRequest = new AuthRequest();
        authRequest.setLogin(login);
        authRequest.setPassword(password);
        //Call<AuthRequest> authRequestCall = Api.getInstance().getApi().login(authRequest);
//        authRequestCall.enqueue(new ResponseHandler<AuthRequest>(abstractPresenter, responseCallback) {
//        });
    }


}
