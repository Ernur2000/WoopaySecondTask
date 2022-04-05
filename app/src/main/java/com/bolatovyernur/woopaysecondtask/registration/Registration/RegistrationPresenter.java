package com.bolatovyernur.woopaysecondtask.registration.Registration;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bolatovyernur.woopaysecondtask.api.AbstractPresenter;
import com.bolatovyernur.woopaysecondtask.api.ResponseCallback;
import com.bolatovyernur.woopaysecondtask.api.ResponseHandler;
import com.bolatovyernur.woopaysecondtask.model.ErrorResponses;
import com.bolatovyernur.woopaysecondtask.model.RegisterRequest;

import java.util.List;

public class RegistrationPresenter extends AbstractPresenter {
    private Bundle bundle;
    RegistrationView registrationView = new RegistrationFragment();

    public void register(String login, String email, View view) {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setLogin(login);
        registerRequest.setEmail(email);
        getApiService().register(registerRequest).enqueue(new ResponseHandler<>(new ResponseCallback<ErrorResponses>() {
            @Override
            public void onSuccess(ErrorResponses response) {
//                bundle = new Bundle();
//                bundle.putString("Auth", login);
//                bundle.putString("Email", email);
//                Navigation.findNavController(view).navigate(R.id.action_registrationFragment_to_smsFragment, bundle);
                registrationView.onRegistrationSuccessResponse(login,email);
            }

            @Override
            public void onError(List<ErrorResponses> error) {
                Log.d("Error", error.get(0).getMessage());
                Toast.makeText(view.getContext(), error.get(0).getMessage() + "server returned error", Toast.LENGTH_LONG).show();
            }
//            @Override
//            public void onError(ErrorResponses error) {
//                Toast.makeText(view.getContext(), "server returned error", Toast.LENGTH_LONG).show();
//            }
        }));
    }
}
