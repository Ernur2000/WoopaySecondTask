package com.bolatovyernur.woopaysecondtask.pinCodePage;

import android.view.View;
import android.widget.Toast;

import com.bolatovyernur.woopaysecondtask.api.AbstractPresenter;
import com.bolatovyernur.woopaysecondtask.api.ResponseCallback;
import com.bolatovyernur.woopaysecondtask.api.ResponseHandler;
import com.bolatovyernur.woopaysecondtask.model.AuthRequest;
import com.bolatovyernur.woopaysecondtask.model.AuthResponse;
import com.bolatovyernur.woopaysecondtask.model.ErrorResponses;
import com.bolatovyernur.woopaysecondtask.util.Constants;
import com.bolatovyernur.woopaysecondtask.util.PreferenceUtils;
import com.google.gson.JsonSyntaxException;
import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;
import java.util.List;

public class PinCodePagePresenter extends AbstractPresenter {
    PinCodePageView pinCodePageView = new PinCodePageFragment();
    String messageAfterDecrypt;
    PreferenceUtils preferenceUtils;

    public void login(View view) {
        preferenceUtils = new PreferenceUtils(view.getContext());
        String login = preferenceUtils.getString(Constants.KEY_EMAIL);
        String password = preferenceUtils.getString(Constants.KEY_PASSWORD);
        try {
            messageAfterDecrypt = AESCrypt.decrypt(login, password);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        AuthRequest authRequest = new AuthRequest();
        authRequest.setLogin(login);
        authRequest.setPassword(messageAfterDecrypt);
        getApiService().login(authRequest).enqueue(new ResponseHandler<>(new ResponseCallback<AuthResponse>() {
            @Override
            public void onSuccess(AuthResponse response) {
                String token = response.getToken();
                try {
                    String encryptedToken = AESCrypt.encrypt(login, token);
                    preferenceUtils.saveString(Constants.KEY_TOKEN, encryptedToken);
                } catch (IllegalStateException | JsonSyntaxException | GeneralSecurityException exception) {
                    Toast.makeText(view.getContext(), "Error" + exception.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
                pinCodePageView.onSuccessResponse(view);
            }

            @Override
            public void onError(List<ErrorResponses> error) {
                Toast.makeText(view.getContext(), "error", Toast.LENGTH_LONG).show();
            }
        }));
    }
}
