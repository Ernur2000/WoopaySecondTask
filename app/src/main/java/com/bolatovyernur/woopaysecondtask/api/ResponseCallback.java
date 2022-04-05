package com.bolatovyernur.woopaysecondtask.api;

import com.bolatovyernur.woopaysecondtask.model.ErrorResponses;

import java.util.List;

public interface ResponseCallback<T> {
    void onSuccess(T response);
    void onError(List<ErrorResponses> error);
}
