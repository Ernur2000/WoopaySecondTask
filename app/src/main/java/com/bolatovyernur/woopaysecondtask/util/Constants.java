package com.bolatovyernur.woopaysecondtask.util;

import com.bolatovyernur.woopaysecondtask.api.Api;
import com.bolatovyernur.woopaysecondtask.api.ApiService;

public interface Constants {
    String base_URL = "http/Base_URL";
    ApiService apiService = Api.getRequest(Constants.base_URL).create(ApiService.class);
}
