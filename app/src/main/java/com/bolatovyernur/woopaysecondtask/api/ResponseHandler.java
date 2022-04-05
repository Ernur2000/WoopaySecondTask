package com.bolatovyernur.woopaysecondtask.api;

import android.util.Log;

import androidx.annotation.NonNull;

import com.bolatovyernur.woopaysecondtask.model.ErrorResponses;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResponseHandler<T> implements Callback<T> {
    private final ResponseCallback<T> responseCallback;

    public ResponseHandler(ResponseCallback<T> responseCallback) {
        this.responseCallback = responseCallback;
    }

    @Override
    public void onResponse(@NonNull Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            responseCallback.onSuccess((T) response.body());
        }if(response.code()>=500){
            Log.d("Fail", "Server side error");
        }
        else {
            if (response.errorBody() != null) {
                try {
                    Type list =  new TypeToken<List<ErrorResponses>>(){}.getType();
                    List<ErrorResponses> errorResponses =new Gson().fromJson(response.errorBody().string(), list);
                    responseCallback.onError(errorResponses);
                } catch (IOException e) {
                    responseCallback.onError(null);
                    e.printStackTrace();
                }
            } else {
                //responseCallback.onError(null);
            }
        }
    }

    @Override
    public void onFailure(@NonNull Call<T> call, Throwable t) {
        t.printStackTrace();
    }
}
