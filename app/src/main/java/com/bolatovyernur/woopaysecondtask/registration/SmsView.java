package com.bolatovyernur.woopaysecondtask.registration;

import android.view.View;

public interface SmsView {
    void onSmsSuccessResponse(View view);
    void onSmsErrorResponse(View view,String message);
}
