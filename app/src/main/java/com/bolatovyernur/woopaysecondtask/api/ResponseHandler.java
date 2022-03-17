package com.bolatovyernur.woopaysecondtask.api;

import androidx.annotation.NonNull;

import com.bolatovyernur.woopaysecondtask.AbstractPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResponseHandler<T> implements Callback<T> {
    protected AbstractPresenter abstractPresenter;

    public ResponseHandler() {
    }

    @Override
    public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
        if (response.isSuccessful()) {
            abstractPresenter.onSuccess(response);
        } else {
            abstractPresenter.onError(response);
        }
    }

    @Override
    public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
        abstractPresenter.onError(null);
    }
}
