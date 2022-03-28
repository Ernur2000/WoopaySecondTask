package com.bolatovyernur.woopaysecondtask.api;

import com.bolatovyernur.woopaysecondtask.registration.ErrorResponse;

public interface ResponseCallback<T> {
    void onSuccess(T response);

    void onError(ErrorResponse error);
}
