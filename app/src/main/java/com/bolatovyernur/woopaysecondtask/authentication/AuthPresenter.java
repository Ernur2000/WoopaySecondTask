package com.bolatovyernur.woopaysecondtask.authentication;

import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.bolatovyernur.woopaysecondtask.api.AbstractPresenter;
import com.bolatovyernur.woopaysecondtask.util.PreferenceUtils;
import com.bolatovyernur.woopaysecondtask.api.ResponseCallback;
import com.bolatovyernur.woopaysecondtask.api.ResponseHandler;
import com.bolatovyernur.woopaysecondtask.model.AuthRequest;
import com.bolatovyernur.woopaysecondtask.model.AuthResponse;
import com.bolatovyernur.woopaysecondtask.model.ErrorResponses;
import com.bolatovyernur.woopaysecondtask.util.Constants;
import com.google.gson.JsonSyntaxException;
import com.scottyab.aescrypt.AESCrypt;
import java.security.GeneralSecurityException;
import java.util.List;

public class AuthPresenter extends AbstractPresenter {
    AuthView authView = new LoginFragment();

    public void login(String login, String password, View view) {
        AuthRequest authRequest = new AuthRequest();
        authRequest.setLogin(login);
        authRequest.setPassword(password);
        getApiService().login(authRequest).enqueue(new ResponseHandler<>(new ResponseCallback<AuthResponse>() {
            @Override
            public void onSuccess(AuthResponse response) {
                String encryptedMsg;
                authView.onSuccessResponse(view);
                try {
                    encryptedMsg = AESCrypt.encrypt(login, password);
                    Log.d("EncryptedMSG", encryptedMsg);
                     PreferenceUtils.saveString(Constants.KEY_EMAIL,login);
                     PreferenceUtils.saveString(Constants.KEY_PASSWORD,encryptedMsg);
                } catch (IllegalStateException | JsonSyntaxException | GeneralSecurityException exception) {
                    Toast.makeText(view.getContext(), "Error" + exception.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onError(List<ErrorResponses> error) {
                Log.d("Error", error.get(0).getMessage());
                Toast.makeText(view.getContext(), error.get(0).getMessage(), Toast.LENGTH_LONG).show();
            }
        }));
    }
}
