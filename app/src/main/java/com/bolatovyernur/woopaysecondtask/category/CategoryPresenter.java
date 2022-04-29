package com.bolatovyernur.woopaysecondtask.category;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bolatovyernur.woopaysecondtask.api.AbstractPresenter;
import com.bolatovyernur.woopaysecondtask.api.ResponseCallback;
import com.bolatovyernur.woopaysecondtask.api.ResponseHandler;
import com.bolatovyernur.woopaysecondtask.db.AppDatabase;
import com.bolatovyernur.woopaysecondtask.db.Category;
import com.bolatovyernur.woopaysecondtask.model.ErrorResponses;
import com.bolatovyernur.woopaysecondtask.model.TopServiceResponse;
import com.bolatovyernur.woopaysecondtask.util.Constants;
import com.bolatovyernur.woopaysecondtask.util.PreferenceUtils;
import com.google.gson.JsonSyntaxException;
import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;
import java.util.List;

public class CategoryPresenter extends AbstractPresenter {
    CategoryView categoryView;
    String messageAfterDecrypt;
    PreferenceUtils preferenceUtils;

    public CategoryPresenter(CategoryView categoryView) {
        this.categoryView = categoryView;
    }

    public void getTopServices(View view) {
        preferenceUtils = new PreferenceUtils(view.getContext());
        String login = preferenceUtils.getString(Constants.KEY_EMAIL);
        String token = preferenceUtils.getString(Constants.KEY_TOKEN);
        try {
            messageAfterDecrypt = AESCrypt.decrypt(login, token);
        } catch (IllegalStateException | JsonSyntaxException | GeneralSecurityException exception) {
            Toast.makeText(view.getContext(), "Error" + exception.getLocalizedMessage(), Toast.LENGTH_LONG).show();
        }
        getApiService().getTopService(messageAfterDecrypt, "service", "mobile").enqueue(new ResponseHandler<>(new ResponseCallback<List<TopServiceResponse>>() {
            @Override
            public void onSuccess(List<TopServiceResponse> response) {
                categoryView.putTopServiceDataToRecyclerView(response, view);
            }

            @Override
            public void onError(List<ErrorResponses> error) {
                Log.d("Error", error.toString());
            }
        }));
    }

    public void readServiceCategory(View view) {
        AppDatabase db = AppDatabase.getDbInstance(view.getContext().getApplicationContext());
        List<Category> categoryList = db.categoryDao().getAllCategories();
        categoryView.putCategoryDataToRecyclerView(categoryList, view);
    }
}
