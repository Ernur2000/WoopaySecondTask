package com.bolatovyernur.woopaysecondtask.registration.Registration;

import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.bolatovyernur.woopaysecondtask.api.AbstractPresenter;
import com.bolatovyernur.woopaysecondtask.api.ResponseCallback;
import com.bolatovyernur.woopaysecondtask.api.ResponseHandler;
import com.bolatovyernur.woopaysecondtask.model.AuthRequest;
import com.bolatovyernur.woopaysecondtask.model.AuthResponse;
import com.bolatovyernur.woopaysecondtask.model.ErrorResponses;
import com.bolatovyernur.woopaysecondtask.model.RegisterRequest;
import java.util.List;

public class RegistrationPresenter extends AbstractPresenter {
    RegistrationView registrationView = new RegistrationFragment();
    public void register(String login, String email, View view) {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setLogin(login);
        registerRequest.setEmail(email);
        getApiService().register(registerRequest).enqueue(new ResponseHandler<>(new ResponseCallback<ErrorResponses>() {
            @Override
            public void onSuccess(ErrorResponses response) {
                registrationView.onRegistrationSuccessResponse(login,email,view);
            }

            @Override
            public void onError(List<ErrorResponses> error) {
                Log.d("Error", error.get(0).getMessage());
                Toast.makeText(view.getContext(), error.get(0).getMessage(), Toast.LENGTH_LONG).show();
            }
        }));
    }
}
