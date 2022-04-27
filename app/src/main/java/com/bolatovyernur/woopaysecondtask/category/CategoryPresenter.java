package com.bolatovyernur.woopaysecondtask.category;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.bolatovyernur.woopaysecondtask.api.AbstractPresenter;
import com.bolatovyernur.woopaysecondtask.api.ResponseCallback;
import com.bolatovyernur.woopaysecondtask.api.ResponseHandler;
import com.bolatovyernur.woopaysecondtask.db.AppDatabase;
import com.bolatovyernur.woopaysecondtask.db.Category;
import com.bolatovyernur.woopaysecondtask.model.ErrorResponses;
import com.bolatovyernur.woopaysecondtask.model.TopServiceResponse;

import java.util.List;

public class CategoryPresenter extends AbstractPresenter {
    CategoryView categoryView;

    public CategoryPresenter(CategoryView categoryView) {
        this.categoryView = categoryView;
    }

    public void getTopServices(View view, String token, ProgressBar progressBar) {
        progressBar.setVisibility(progressBar.VISIBLE);
        getApiService().getTopService(token, "service", "mobile").enqueue(new ResponseHandler<>(new ResponseCallback<List<TopServiceResponse>>() {
            @Override
            public void onSuccess(List<TopServiceResponse> response) {
                categoryView.putTopServiceDataToRecyclerView(response, view);
                progressBar.setVisibility(progressBar.INVISIBLE);
            }

            @Override
            public void onError(List<ErrorResponses> error) {
                Log.d("Error", error.toString());
                progressBar.setVisibility(progressBar.INVISIBLE);
            }
        }));
    }

    public void readServiceCategory(View view) {
        AppDatabase db = AppDatabase.getDbInstance(view.getContext().getApplicationContext());
        List<Category> categoryList = db.categoryDao().getAllCategories();
        categoryView.putCategoryDataToRecyclerView(categoryList, view);
    }
}
