package com.bolatovyernur.woopaysecondtask.mainPage;

import android.content.ContentValues;
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
import com.bolatovyernur.woopaysecondtask.model.AuthRequest;
import com.bolatovyernur.woopaysecondtask.model.AuthResponse;
import com.bolatovyernur.woopaysecondtask.model.ErrorResponses;
import com.bolatovyernur.woopaysecondtask.registration.Registration.LoginView;
import com.bolatovyernur.woopaysecondtask.util.Constants;
import com.bolatovyernur.woopaysecondtask.util.PreferenceUtils;
import com.google.gson.JsonSyntaxException;
import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

public class MainPagePresenter extends AbstractPresenter {
    DBHelper dbHelper;
    LoginView loginView = new MainPageFragment();
    private final ArrayList<CategoryResponse> arrayList = new ArrayList<>();
    public void login(String login,String password, View view){
        AuthRequest authRequest = new AuthRequest();
        authRequest.setLogin(login);
        authRequest.setPassword(password);
        getApiService().login(authRequest).enqueue(new ResponseHandler<>(new ResponseCallback<AuthResponse>() {
            @Override
            public void onSuccess(AuthResponse response) {
                String token = response.getToken();
                try {
                    String encryptedMsg = AESCrypt.encrypt(login, token);
                    Log.d("EncryptedMSG", encryptedMsg);
                    PreferenceUtils.saveString(Constants.KEY_TOKEN,encryptedMsg);
                } catch (IllegalStateException | JsonSyntaxException | GeneralSecurityException exception) {
                    Toast.makeText(view.getContext(), "Error" + exception.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
                loginView.onSuccessResponse(login,password,view);
            }

            @Override
            public void onError(List<ErrorResponses> error) {

            }
        }));
    }
    public void getCategories(View view,ProgressBar progressBar){
        dbHelper = new DBHelper(view.getContext());
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        ProgressBar progress = progressBar;
        progressBar.setVisibility(progress.VISIBLE);
        getApiService().getCategory().enqueue(new ResponseHandler<>(new ResponseCallback<List<CategoryResponse>>() {
            @Override
            public void onSuccess(List<CategoryResponse> response) {
                for (CategoryResponse c: response) {
                    arrayList.add(new CategoryResponse(c.getId(),c.getParent_id(),c.getTitle(),c.getName(),c.getPicture(),c.getParent(),c.getChildren(),c.getBlacklist(),c.getLanguage(),c.getDescription(),c.getPosition(),c.getPicture_url()));
                    contentValues.put(DBHelper.KEY_ID, (byte[]) null);
                    contentValues.put(DBHelper.KEY_PARENT_ID,c.getParent_id());
                    contentValues.put(DBHelper.KEY_TITLE,c.getTitle());
                    contentValues.put(DBHelper.KEY_NAME,c.getName());
                    contentValues.put(DBHelper.KEY_PICTURE,c.getPicture());
                    contentValues.put(DBHelper.KEY_PARENT,c.getParent());
                    contentValues.put(DBHelper.KEY_CHILDREN,c.getChildren());
                    contentValues.put(DBHelper.KEY_BLACKLIST,c.getBlacklist());
                    contentValues.put(DBHelper.KEY_LANGUAGE,c.getLanguage());
                    contentValues.put(DBHelper.KEY_DESCRIPTION,c.getDescription());
                    contentValues.put(DBHelper.KEY_POSITION,c.getPosition());
                    contentValues.put(DBHelper.KEY_PICTURE_URL,c.getPicture_url());
                    database.insert(DBHelper.TABLE_CATEGORIES,null,contentValues);
                }
                database.close();

                Toast.makeText(view.getContext(),"Successful fetched data",Toast.LENGTH_LONG).show();
                progressBar.setVisibility(progress.INVISIBLE);
            }

            @Override
            public void onError(List<ErrorResponses> error) {
                Toast.makeText(view.getContext(),"Can't fetch data",Toast.LENGTH_LONG).show();
                progressBar.setVisibility(progress.INVISIBLE);
            }
        }));
    }
    public void readDB(View view){
        dbHelper = new DBHelper(view.getContext());
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(DBHelper.TABLE_CATEGORIES,null,null,null,null,null,null);
        if (cursor.moveToFirst()){
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
            do {
                Log.d("mLog","ID = " + cursor.getInt(idIndex) + ", parent_id = " + cursor.getString(parentIDIndex) +
                        ", title = " + cursor.getString(titleIndex) +   ", name = " + cursor.getString(nameIndex) +
                        ", picture = " + cursor.getString(pictureIndex) + ", parent = " + cursor.getString(parentIndex) +
                        ", children = " + cursor.getString(childrenIndex) + ", blacklist = " + cursor.getString(blacklistIndex) +
                        ", language = " + cursor.getString(languageIndex) + ", description = " + cursor.getString(descriptionIndex) +
                        ", position = " + cursor.getString(positionIndex) + ", picture_url = " + cursor.getString(pictureURLIndex));
            }while (cursor.moveToNext());
        }else {
            Log.d("mLog","0 rows");
        }
        cursor.close();
    }
}
