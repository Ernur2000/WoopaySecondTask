package com.bolatovyernur.woopaysecondtask.registration.Sms;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.navigation.Navigation;
import com.bolatovyernur.woopaysecondtask.api.AbstractPresenter;
import com.bolatovyernur.woopaysecondtask.R;
import com.bolatovyernur.woopaysecondtask.api.ResponseCallback;
import com.bolatovyernur.woopaysecondtask.api.ResponseHandler;
import com.bolatovyernur.woopaysecondtask.model.ErrorResponses;
import com.bolatovyernur.woopaysecondtask.model.SmsRequest;

import java.util.List;

public class SmsPresenter extends AbstractPresenter {
    SmsView smsView = new SmsFragment();
    public void sendSms(String login, String activationCode, View view) {
        SmsRequest smsRequest = new SmsRequest();
        smsRequest.setLogin(login);
        smsRequest.setActivation_code(activationCode);
        getApiService().sendSms(smsRequest).enqueue(new ResponseHandler<>(new ResponseCallback<ErrorResponses>() {
            @Override
            public void onSuccess(ErrorResponses response) {
                Bundle bundle = new Bundle();
                bundle.putString("Auth",login);
                bundle.putString("activationCode",activationCode);
                Navigation.findNavController(view).navigate(R.id.action_smsFragment_to_passwordFragment, bundle);
                smsView.onSmsSuccessResponse(view);
            }

            @Override
            public void onError(List<ErrorResponses> error) {
                Log.d("Error", error.get(0).getMessage());
                Toast.makeText(view.getContext(), error.get(0).getMessage() + "server returned error", Toast.LENGTH_LONG).show();
            }
        }));
    }

}
