package com.bolatovyernur.woopaysecondtask.api;

import android.util.Log;
import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResponseHandler<T> implements Callback<T>{
    @Override
    public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
        if (response.body() == null || response.code() == 404){
            Log.d("ResponseHandler","Response is null");
        }else{
            switch (response.code()){
                        case 3333:
                            // session timed out
                            break;
                        case 1111:
                            // failed
                            break;
                        case 401:
                            // unauthorized user
                            break;
                        case 404:
                            // not found
                            break;
                        case 500:
                            // internal server error
                            break;
                    }
        }
    }

    @Override
    public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
        if (t.getMessage()!=null){
            Log.e("error",t.getLocalizedMessage());
        }
    }
}
