package com.bolatovyernur.woopaysecondtask.registration.Password;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bolatovyernur.woopaysecondtask.api.AbstractPresenter;
import com.bolatovyernur.woopaysecondtask.api.ResponseCallback;
import com.bolatovyernur.woopaysecondtask.api.ResponseHandler;
import com.bolatovyernur.woopaysecondtask.model.AuthRequest;
import com.bolatovyernur.woopaysecondtask.model.AuthResponse;
import com.bolatovyernur.woopaysecondtask.model.ErrorResponses;
import com.bolatovyernur.woopaysecondtask.model.PasswordRequest;
import com.bolatovyernur.woopaysecondtask.registration.Registration.LogAfterRegView;

import java.util.List;

public class PasswordPresenter extends AbstractPresenter {
    PasswordView passwordView = new PasswordFragment();
    LogAfterRegView logAfterRegView = new PasswordFragment();

    public void createPassword(String login, String activationCode, String password, View view) {
        PasswordRequest passwordRequest = new PasswordRequest();
        passwordRequest.setLogin(login);
        passwordRequest.setActivation_code(activationCode);
        passwordRequest.setPassword(password);
        getApiService().createPassword(passwordRequest).enqueue(new ResponseHandler<>(new ResponseCallback<ErrorResponses>() {
            @Override
            public void onSuccess(ErrorResponses response) {
                passwordView.onSuccessResponse(view);
            }

            @Override
            public void onError(List<ErrorResponses> error) {
                Log.d("Error", error.get(0).getMessage());
                Toast.makeText(view.getContext(), error.get(0).getMessage() + "server returned error", Toast.LENGTH_LONG).show();
            }
        }));
    }
    public void loginAfterRegister(String login, String password,View view){
        AuthRequest authRequest = new AuthRequest();
        authRequest.setLogin(login);
        authRequest.setPassword(password);
        getApiService().login(authRequest).enqueue(new ResponseHandler<>(new ResponseCallback<AuthResponse>() {
            @Override
            public void onSuccess(AuthResponse response) {
                logAfterRegView.onSuccessLogin(view);
            }

            @Override
            public void onError(List<ErrorResponses> error) {
                Log.d("Error", error.get(0).getMessage());
                Toast.makeText(view.getContext(), error.get(0).getMessage() + "server returned error", Toast.LENGTH_LONG).show();
            }
        }));
    }

}
