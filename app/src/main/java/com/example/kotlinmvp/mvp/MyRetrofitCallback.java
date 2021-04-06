package com.example.kotlinmvp.mvp;

import com.example.kotlinmvp.JsonResult;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**

 */

public abstract class MyRetrofitCallback<M> implements Callback<JsonResult<M>> {

    public abstract void onSuccess(M model);

    public abstract void onFailure( String msg);


    @Override
    public void onResponse(Call<JsonResult<M>> call, Response<JsonResult<M>> response) {
        if (response.isSuccessful()) {
            JsonResult<M> result = response.body();
            onSuccess(result.getResult());
        } else {
            onFailure(response.errorBody().toString());
        }

    }

    @Override
    public void onFailure(Call<JsonResult<M>> call, Throwable t) {
        onFailure(call.toString());
    }
}
