package com.bolatovyernur.woopaysecondtask.api;

import android.util.Log;

import androidx.annotation.NonNull;

import com.bolatovyernur.woopaysecondtask.registration.ErrorResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResponseHandler<T> implements Callback<T> {
    private final ResponseCallback responseCallback;
    private final ErrorResponse errorResponse;

    public ResponseHandler(ResponseCallback responseCallback, ErrorResponse errorResponse) {
        this.responseCallback = responseCallback;
        this.errorResponse = errorResponse;
    }

    @Override
    public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
        if (response.isSuccessful()) {
            responseCallback.onSuccess(response.body());

        } else {
            Log.d("error", String.valueOf(response));
            responseCallback.onError(errorResponse);
        }
    }

    @Override
    public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
        Log.d("error", t.getLocalizedMessage());
    }
}
