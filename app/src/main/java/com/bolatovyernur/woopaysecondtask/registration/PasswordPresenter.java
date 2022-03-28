package com.bolatovyernur.woopaysecondtask.registration;

import android.view.View;

import com.bolatovyernur.woopaysecondtask.AbstractPresenter;
import com.bolatovyernur.woopaysecondtask.api.ResponseCallback;
import com.bolatovyernur.woopaysecondtask.api.ResponseHandler;

public class PasswordPresenter extends AbstractPresenter {
    PasswordView passwordView = new PasswordFragment();
    private ErrorResponse errorResponse;

    public PasswordPresenter(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public void createPassword(String login, String activationCode, String password, View view){
        PasswordRequest passwordRequest = new PasswordRequest();
        passwordRequest.setLogin(login);
        passwordRequest.setActivation_code(activationCode);
        passwordRequest.setPassword(password);
        getApiService().createPassword(passwordRequest).enqueue(new ResponseHandler<>(new ResponseCallback() {
            @Override
            public void onSuccess(Object response) {
                passwordView.onSuccessResponse(view);
            }

            @Override
            public void onError(ErrorResponse error) {

            }

        }, errorResponse));
    }

}