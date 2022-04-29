package com.bolatovyernur.woopaysecondtask.registration.password;

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
import com.bolatovyernur.woopaysecondtask.registration.registration.LogAfterRegView;
import com.bolatovyernur.woopaysecondtask.util.Constants;
import com.bolatovyernur.woopaysecondtask.util.PreferenceUtils;
import com.google.gson.JsonSyntaxException;
import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;
import java.util.List;

public class PasswordPresenter extends AbstractPresenter {
    PasswordView passwordView = new PasswordFragment();
    LogAfterRegView logAfterRegView = new PasswordFragment();
    PreferenceUtils preferenceUtils;
    public void createPassword(String login, String activationCode, String password, View view) {
        preferenceUtils = new PreferenceUtils(view.getContext());
        PasswordRequest passwordRequest = new PasswordRequest();
        passwordRequest.setLogin(login);
        passwordRequest.setActivationCode(activationCode);
        passwordRequest.setPassword(password);
        getApiService().createPassword(passwordRequest).enqueue(new ResponseHandler<>(new ResponseCallback<List<ErrorResponses>>() {
            @Override
            public void onSuccess(List<ErrorResponses> response) {
                passwordView.onSuccessResponse(view);
                String encryptedMsg;
                try {
                    encryptedMsg = AESCrypt.encrypt(login, password);
                    Log.d("EncryptedMSG", encryptedMsg);
                    preferenceUtils.saveString(Constants.KEY_EMAIL,login);
                    preferenceUtils.saveString(Constants.KEY_PASSWORD,encryptedMsg);
                } catch (IllegalStateException | JsonSyntaxException | GeneralSecurityException exception) {
                    Toast.makeText(view.getContext(), "Error" + exception.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
                loginAfterRegister(login,password,view);
            }

            @Override
            public void onError(List<ErrorResponses> error) {
                Log.d("Error", error.get(0).getMessage());
                Toast.makeText(view.getContext(), error.get(0).getMessage(), Toast.LENGTH_LONG).show();
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
                Toast.makeText(view.getContext(), error.get(0).getMessage(), Toast.LENGTH_LONG).show();
            }
        }));
    }

}
