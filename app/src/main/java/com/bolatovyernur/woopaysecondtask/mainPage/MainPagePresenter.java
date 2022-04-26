package com.bolatovyernur.woopaysecondtask.mainPage;

import android.view.View;
import android.widget.Toast;

import com.bolatovyernur.woopaysecondtask.api.AbstractPresenter;
import com.bolatovyernur.woopaysecondtask.api.ResponseCallback;
import com.bolatovyernur.woopaysecondtask.api.ResponseHandler;
import com.bolatovyernur.woopaysecondtask.db.AppDatabase;
import com.bolatovyernur.woopaysecondtask.db.Category;
import com.bolatovyernur.woopaysecondtask.model.CategoryResponse;
import com.bolatovyernur.woopaysecondtask.model.ErrorResponses;

import java.util.List;

public class MainPagePresenter extends AbstractPresenter {
    AppDatabase db;

    public void getCategories(View view) {
        db = AppDatabase.getDbInstance(view.getContext().getApplicationContext());
        Category category = new Category();
        getApiService().getCategory().enqueue(new ResponseHandler<>(new ResponseCallback<List<CategoryResponse>>() {
            @Override
            public void onSuccess(List<CategoryResponse> response) {
                for (CategoryResponse c : response) {
                    category.uid = c.getId();
                    category.title = c.getTitle();
                    category.name = c.getName();
                    category.picture_url = c.getPicture_url();
                    db.categoryDao().insertCategories(category);
                }
            }

            @Override
            public void onError(List<ErrorResponses> error) {
                Toast.makeText(view.getContext(), "Can't fetch data", Toast.LENGTH_LONG).show();
            }
        }));
    }
}
