package com.bolatovyernur.woopaysecondtask.category;

import android.content.Context;

import androidx.annotation.NonNull;

import com.bolatovyernur.woopaysecondtask.AbstractPresenter;
import com.bolatovyernur.woopaysecondtask.api.Api;
import com.bolatovyernur.woopaysecondtask.api.ResponseHandler;

import retrofit2.Call;
import retrofit2.Response;

public class CategoryPresenter extends AbstractPresenter {
    public void getCategories(){
        Call<CategoryRequest> categoryRequestCall = Api.getInstance().getApi().getCategory();
        categoryRequestCall.enqueue(new ResponseHandler<CategoryRequest>(){
            @Override
            public void onResponse(@NonNull Call<CategoryRequest> call, @NonNull Response<CategoryRequest> response) {
                super.onResponse(call, response);
            }
        });
    }
    @Override
    protected void onSuccess(Object obj) {

    }

    @Override
    protected void onError(Object obj) {

    }
}
