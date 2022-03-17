package com.bolatovyernur.woopaysecondtask;

import com.bolatovyernur.woopaysecondtask.api.ApiService;

import retrofit2.Response;

public abstract class AbstractPresenter {
    private ApiService apiService;

    protected ApiService ApiService() {
        return apiService;
    }

    public abstract void onSuccess(Response response);

    public abstract void onError(Response response);
}
