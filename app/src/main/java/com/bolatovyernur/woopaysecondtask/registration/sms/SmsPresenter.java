package com.bolatovyernur.woopaysecondtask.registration.sms;

import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.bolatovyernur.woopaysecondtask.api.AbstractPresenter;
import com.bolatovyernur.woopaysecondtask.api.ResponseCallback;
import com.bolatovyernur.woopaysecondtask.api.ResponseHandler;
import com.bolatovyernur.woopaysecondtask.model.ErrorResponses;
import com.bolatovyernur.woopaysecondtask.model.RegisterRequest;
import com.bolatovyernur.woopaysecondtask.model.SmsRequest;

import java.util.List;

public class SmsPresenter extends AbstractPresenter {
    SmsView smsView = new SmsFragment();
    public void sendSms(String login, String activationCode, View view) {
        SmsRequest smsRequest = new SmsRequest();
        smsRequest.setLogin(login);
        smsRequest.setActivationCode(activationCode);
        getApiService().sendSms(smsRequest).enqueue(new ResponseHandler<>(new ResponseCallback<List<ErrorResponses>>() {
            @Override
            public void onSuccess(List<ErrorResponses> response) {
                smsView.onSmsSuccessResponse(login,activationCode,view);
            }
            @Override
            public void onError(List<ErrorResponses> error) {
                Log.d("Error", error.get(0).getMessage());
                Toast.makeText(view.getContext(), error.get(0).getMessage(), Toast.LENGTH_LONG).show();
            }
        }));
    }
    public void sendNewSms(String login,String email,View view){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setLogin(login);
        registerRequest.setEmail(email);
        getApiService().register(registerRequest).enqueue(new ResponseHandler<>(new ResponseCallback<ErrorResponses>() {
            @Override
            public void onSuccess(ErrorResponses response) {
                Toast.makeText(view.getContext(), "New sms sent", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(List<ErrorResponses> error) {
                Log.d("Error", error.get(0).getMessage());
                Toast.makeText(view.getContext(), error.get(0).getMessage(), Toast.LENGTH_LONG).show();
            }
        }));
    }
}
