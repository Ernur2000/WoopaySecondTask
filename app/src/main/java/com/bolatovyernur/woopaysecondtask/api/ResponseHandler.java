package com.bolatovyernur.woopaysecondtask.api;

import android.util.Log;

import androidx.annotation.NonNull;

import com.bolatovyernur.woopaysecondtask.registration.ErrorResponses;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResponseHandler implements Callback {
    private final ResponseCallback responseCallback;

    private final ErrorResponses errorResponse;

    public ResponseHandler(ResponseCallback responseCallback, ErrorResponses errorResponse) {
        this.responseCallback = responseCallback;
        this.errorResponse = errorResponse;
    }


    @Override
    public void onResponse(@NonNull Call call, @NonNull Response response) {
        if (response.isSuccessful()) {
            responseCallback.onSuccess(response.body());

        } else {
            Log.d("error", String.valueOf(response));
            responseCallback.onError(errorResponse);
        }
    }

    @Override
    public void onFailure(@NonNull Call call, @NonNull Throwable t) {
        t.printStackTrace();
    }
}
