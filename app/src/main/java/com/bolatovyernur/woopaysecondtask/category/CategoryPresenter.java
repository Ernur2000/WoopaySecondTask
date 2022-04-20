package com.bolatovyernur.woopaysecondtask.category;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bolatovyernur.woopaysecondtask.api.AbstractPresenter;
import com.bolatovyernur.woopaysecondtask.api.DBHelper;
import com.bolatovyernur.woopaysecondtask.api.ResponseCallback;
import com.bolatovyernur.woopaysecondtask.api.ResponseHandler;
import com.bolatovyernur.woopaysecondtask.model.CategoryResponse;
import com.bolatovyernur.woopaysecondtask.model.ErrorResponses;
import com.bolatovyernur.woopaysecondtask.model.TopServiceResponse;

import java.util.ArrayList;
import java.util.List;

public class CategoryPresenter extends AbstractPresenter {
    CategoryView categoryView;
    DBHelper dbHelper;

    public CategoryPresenter(CategoryView categoryView) {
        this.categoryView = categoryView;
    }

    public void getTopService(String token, View view) {
        getApiService().getTopService(token).enqueue(new ResponseHandler<>(new ResponseCallback<List<TopServiceResponse>>() {
            @Override
            public void onSuccess(List<TopServiceResponse> response) {
                categoryView.putDataIntoRecyclerView(response, view);
            }

            @Override
            public void onError(List<ErrorResponses> error) {
                Log.d("Error", error.toString());
            }
        }));
    }

    public void getCategories(View view, ProgressBar progressBar) {
        ProgressBar progress = progressBar;
        progressBar.setVisibility(progress.VISIBLE);
        getApiService().getCategory().enqueue(new ResponseHandler<>(new ResponseCallback<List<CategoryResponse>>() {
            @Override
            public void onSuccess(List<CategoryResponse> response) {
                categoryView.putCategoryDataIntoRecyclerView(response, view);
                progressBar.setVisibility(progress.INVISIBLE);
            }

            @Override
            public void onError(List<ErrorResponses> error) {
                Toast.makeText(view.getContext(), "Can't fetch data", Toast.LENGTH_LONG).show();
                progressBar.setVisibility(progress.INVISIBLE);
            }
        }));
    }

    public void readDB(View view) {
        dbHelper = new DBHelper(view.getContext());
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(DBHelper.TABLE_CATEGORIES, null, null, null, null, null, null);
        int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
        int parentIDIndex = cursor.getColumnIndex(DBHelper.KEY_PARENT_ID);
        int titleIndex = cursor.getColumnIndex(DBHelper.KEY_TITLE);
        int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
        int pictureIndex = cursor.getColumnIndex(DBHelper.KEY_PICTURE);
        int parentIndex = cursor.getColumnIndex(DBHelper.KEY_PARENT);
        int childrenIndex = cursor.getColumnIndex(DBHelper.KEY_CHILDREN);
        int blacklistIndex = cursor.getColumnIndex(DBHelper.KEY_BLACKLIST);
        int languageIndex = cursor.getColumnIndex(DBHelper.KEY_LANGUAGE);
        int descriptionIndex = cursor.getColumnIndex(DBHelper.KEY_DESCRIPTION);
        int positionIndex = cursor.getColumnIndex(DBHelper.KEY_POSITION);
        int pictureURLIndex = cursor.getColumnIndex(DBHelper.KEY_PICTURE_URL);
        if (!cursor.moveToFirst()) {
            return;
        }
        final ArrayList<CategoryResponse> categoryResponses = new ArrayList<>();
        do {
            final int id = cursor.getInt(idIndex);
            final int parent_id = cursor.getInt(parentIDIndex);
            final String title = cursor.getString(titleIndex);
            final String name = cursor.getString(nameIndex);
            final String picture = cursor.getString(pictureIndex);
            final String parent = cursor.getString(parentIndex);
            final String children = cursor.getString(childrenIndex);
            final String blacklist = cursor.getString(blacklistIndex);
            final String language = cursor.getString(languageIndex);
            final String description = cursor.getString(descriptionIndex);
            final int position = cursor.getInt(positionIndex);
            final String picture_url = cursor.getString(pictureURLIndex);
            categoryResponses.add(new CategoryResponse(id,
                    parent_id, title, name, picture, parent, children, blacklist, language, description, position, picture_url));
        } while (cursor.moveToNext());

        cursor.close();
        database.close();
        categoryView.putCategoryDataIntoRecyclerView(categoryResponses, view);
    }

}
