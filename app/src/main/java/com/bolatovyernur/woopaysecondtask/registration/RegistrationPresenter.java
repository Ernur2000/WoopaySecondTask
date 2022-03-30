package com.bolatovyernur.woopaysecondtask.registration;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.navigation.Navigation;

import com.bolatovyernur.woopaysecondtask.AbstractPresenter;
import com.bolatovyernur.woopaysecondtask.R;
import com.bolatovyernur.woopaysecondtask.api.ResponseCallback;
import com.bolatovyernur.woopaysecondtask.api.ResponseHandler;

public class RegistrationPresenter extends AbstractPresenter {
    ErrorResponses error;
    private Bundle bundle;
    RegistrationView registrationView = new RegistrationFragment();

    public void register(String login, String email, View view) {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setLogin(login);
        registerRequest.setEmail(email);
        getApiService().register(registerRequest).enqueue(new ResponseHandler(new ResponseCallback() {
            @Override
            public void onSuccess(Object response) {
                bundle = new Bundle();
                bundle.putString("Auth", login);
                bundle.putString("Email", email);
                Navigation.findNavController(view).navigate(R.id.action_registrationFragment_to_smsFragment, bundle);
                registrationView.onRegistrationSuccessResponse(view);
            }

            @Override
            public void onError(ErrorResponses error) {
                Toast.makeText(view.getContext(), "server returned error", Toast.LENGTH_LONG).show();
            }
        }, error));
    }
}
