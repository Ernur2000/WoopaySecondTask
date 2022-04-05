package com.bolatovyernur.woopaysecondtask.api;

public abstract class AbstractPresenter {
    private final ApiService apiService = Api.getInstance().getApi();

    protected ApiService getApiService() {
        return apiService;
    }
}
