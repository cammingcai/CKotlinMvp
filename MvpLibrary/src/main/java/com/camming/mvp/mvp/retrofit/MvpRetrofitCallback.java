package com.camming.mvp.mvp.retrofit;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**

 */

public abstract class MvpRetrofitCallback<M> implements Callback<M> {

    public abstract void onSuccess(M model) throws IOException;

    public abstract void onFailure(int code, String msg);

    public abstract void onThrowable(Throwable t);

    public abstract void onFinish();

    @Override
    public void onResponse(Call<M> call, Response<M> response) {
        if (response.isSuccessful()) {
            try {
                onSuccess(response.body());
            } catch (IOException e) {
                e.printStackTrace();
                onFailure(response.code(), response.errorBody().toString());
            }
        } else {
            onFailure(response.code(), response.errorBody().toString());
        }
        onFinish();
    }

    @Override
    public void onFailure(Call<M> call, Throwable t) {
        onThrowable(t);
        onFinish();
    }
}
