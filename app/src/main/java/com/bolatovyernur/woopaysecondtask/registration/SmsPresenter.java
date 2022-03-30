package com.bolatovyernur.woopaysecondtask.registration;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.navigation.Navigation;
import com.bolatovyernur.woopaysecondtask.AbstractPresenter;
import com.bolatovyernur.woopaysecondtask.R;
import com.bolatovyernur.woopaysecondtask.api.ResponseCallback;
import com.bolatovyernur.woopaysecondtask.api.ResponseHandler;

public class SmsPresenter extends AbstractPresenter {
    SmsView smsView = new SmsFragment();
    ErrorResponses error;
    public void sendSms(String login, String activationCode, View view) {
        SmsRequest smsRequest = new SmsRequest();
        smsRequest.setLogin(login);
        smsRequest.setActivation_code(activationCode);
        getApiService().sendSms(smsRequest).enqueue(new ResponseHandler(new ResponseCallback() {
            @Override
            public void onSuccess(Object response) {
                Bundle bundle = new Bundle();
                bundle.putString("Auth",login);
                bundle.putString("activationCode",activationCode);
                Navigation.findNavController(view).navigate(R.id.action_smsFragment_to_passwordFragment, bundle);
                smsView.onSmsSuccessResponse(view);
            }

            @Override
            public void onError(ErrorResponses error) {
                Toast.makeText(view.getContext(), "server returned error",Toast.LENGTH_LONG).show();
            }
        }, error));
    }

}
