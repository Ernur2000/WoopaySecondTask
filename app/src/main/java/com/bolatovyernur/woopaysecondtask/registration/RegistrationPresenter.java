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
    private ErrorResponse errorResponse;
    private Bundle bundle;
    RegistrationView registrationView = new RegistrationFragment();
    RegistrationFragment registrationFragment = new RegistrationFragment();

    public RegistrationPresenter(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public void register(String login, String email, View view) {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setLogin(login);
        registerRequest.setEmail(email);
        //Call<ErrorResponse> registerResponseCall = Api.getInstance().getApi().register(registerRequest);
        bundle = new Bundle();
        bundle.putString("Login",login);
        registrationFragment.setArguments(bundle);
        getApiService().register(registerRequest).enqueue(new ResponseHandler<>(new ResponseCallback() {
            @Override
            public void onSuccess(Object response) {
                bundle = new Bundle();
                bundle.putString("Auth",login);
                Navigation.findNavController(view).navigate(R.id.action_registrationFragment_to_smsFragment,bundle);
                registrationView.onRegistrationSuccessResponse(view);
            }

            @Override
            public void onError(ErrorResponse error) {
                Toast.makeText(view.getContext(), error.getMessage() + "occured",Toast.LENGTH_LONG).show();
            }
        }, errorResponse));
    }
}
