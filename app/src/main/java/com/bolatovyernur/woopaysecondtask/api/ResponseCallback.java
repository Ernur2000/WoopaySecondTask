package com.bolatovyernur.woopaysecondtask.api;

import com.bolatovyernur.woopaysecondtask.registration.ErrorResponses;

public interface ResponseCallback<T> {
    void onSuccess(T response);

    void onError(ErrorResponses error);
}
