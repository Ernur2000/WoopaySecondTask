package com.bolatovyernur.woopaysecondtask.registration;

import android.os.Bundle;
import android.view.View;

import androidx.navigation.Navigation;

import com.bolatovyernur.woopaysecondtask.AbstractPresenter;
import com.bolatovyernur.woopaysecondtask.R;
import com.bolatovyernur.woopaysecondtask.api.ResponseCallback;
import com.bolatovyernur.woopaysecondtask.api.ResponseHandler;

public class SmsPresenter extends AbstractPresenter {
    SmsView smsView = new SmsFragment();
    private ErrorResponse errorResponse;

    public SmsPresenter(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public void sendSms(String login, String activationCode, View view) {
        SmsRequest smsRequest = new SmsRequest();
        smsRequest.setLogin(login);
        smsRequest.setActivation_code(activationCode);
        //Call<List<ErrorResponse>> registerResponseCall = Api.getInstance().getApi().sendSms(smsRequest);
        getApiService().sendSms(smsRequest).enqueue(new ResponseHandler<>(new ResponseCallback() {
            @Override
            public void onSuccess(Object response) {
                Bundle bundle = new Bundle();
                bundle.putString("Auth",login);
                bundle.putString("activationCode",activationCode);
                Navigation.findNavController(view).navigate(R.id.action_smsFragment_to_passwordFragment, bundle);
                smsView.onSmsSuccessResponse(view);
            }

            @Override
            public void onError(ErrorResponse error) {
                String message = error.getMessage();
                smsView.onSmsErrorResponse(view,message);
            }
        }, errorResponse));
    }

}
