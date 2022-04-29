package com.bolatovyernur.woopaysecondtask.mainPage;

import android.view.View;
import android.widget.Toast;

import com.bolatovyernur.woopaysecondtask.api.AbstractPresenter;
import com.bolatovyernur.woopaysecondtask.api.ResponseCallback;
import com.bolatovyernur.woopaysecondtask.api.ResponseHandler;
import com.bolatovyernur.woopaysecondtask.db.AppDatabase;
import com.bolatovyernur.woopaysecondtask.db.Category;
import com.bolatovyernur.woopaysecondtask.model.ErrorResponses;

import java.util.List;

public class MainPagePresenter extends AbstractPresenter {
    AppDatabase db;

    public void getCategories(View view) {
        db = AppDatabase.getDbInstance(view.getContext().getApplicationContext());
        {
            getApiService().getCategory().enqueue(new ResponseHandler<>(new ResponseCallback<List<Category>>() {
                @Override
                public void onSuccess(List<Category> response) {
                    db.categoryDao().insertCategories(response);
                }

                @Override
                public void onError(List<ErrorResponses> error) {
                    Toast.makeText(view.getContext(), "Can't fetch data", Toast.LENGTH_LONG).show();
                }
            }));
        }
    }
}

