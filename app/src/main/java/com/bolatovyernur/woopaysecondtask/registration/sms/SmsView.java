package com.bolatovyernur.woopaysecondtask.registration.sms;

import android.view.View;

public interface SmsView {
    void onSmsSuccessResponse(String login, String activationCode,View view);
}
