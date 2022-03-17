package com.bolatovyernur.woopaysecondtask.category;

import com.bolatovyernur.woopaysecondtask.AbstractPresenter;
import com.bolatovyernur.woopaysecondtask.api.Api;
import com.bolatovyernur.woopaysecondtask.api.ResponseHandler;

import retrofit2.Call;
import retrofit2.Response;

public class CategoryPresenter extends AbstractPresenter {
    public void getCategories() {
        Call<CategoryRequest> categoryRequestCall = Api.getInstance().getApi().getCategory();
        categoryRequestCall.enqueue(new ResponseHandler<CategoryRequest>() {

        });
    }

    @Override
    public void onSuccess(Response response) {

    }

    @Override
    public void onError(Response response) {

    }
}
