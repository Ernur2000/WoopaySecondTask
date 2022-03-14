package com.bolatovyernur.woopaysecondtask;

import android.content.Context;

import com.bolatovyernur.woopaysecondtask.api.ApiService;

public abstract class   AbstractPresenter {
    private ApiService apiService;
    protected ApiService ApiService(){
        return apiService;
    }
    protected abstract void onSuccess(Context obj);
    protected abstract void onError(Context obj);
}