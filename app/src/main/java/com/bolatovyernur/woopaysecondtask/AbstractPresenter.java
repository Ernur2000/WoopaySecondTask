package com.bolatovyernur.woopaysecondtask;

import com.bolatovyernur.woopaysecondtask.api.Api;
import com.bolatovyernur.woopaysecondtask.api.ApiService;

public abstract class AbstractPresenter {
    private final ApiService apiService = Api.getInstance().getApi();
    protected ApiService getApiService() {
        return apiService;
    }
}
