package com.example.kotlinmvp.mvp;

import android.app.Activity;
import android.util.Log;

import com.camming.mvp.ui.BaseActivity;
import com.example.kotlinmvp.model.JsonResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Create by Camming on 2020/02/20
 *统一处理统一格式的回调
 */

public abstract class MyRetrofitCallback<T> implements Callback<JsonResult<T>> {

    public abstract void onSuccess(T model);

    public abstract void onFailure( String msg);

    private BaseActivity mActivity;
    public MyRetrofitCallback(){

    }
    public MyRetrofitCallback(BaseActivity mAct){
        mActivity = mAct;
    }

    @Override
    public void onResponse(Call<JsonResult<T>> call, Response<JsonResult<T>> response) {
        if(mActivity!=null){
            mActivity.dissmissBaseLoading();
        }
        if (response.isSuccessful()) {
            JsonResult<T> result = response.body();
            if(result==null){
                onFailure("解析错误");
            }else{
                onSuccess(result.getResult());
            }

        } else {
            onFailure(response.errorBody().toString());
        }


    }

    @Override
    public void onFailure(Call<JsonResult<T>> call, Throwable t) {


        if(mActivity!=null){
            mActivity.dissmissBaseLoading();
        }
        if (t != null && t.toString().contains("JsonSyntaxException")) {
            onFailure("JSON解析异常:"+t.getLocalizedMessage());
        }else{
            onFailure(t.getLocalizedMessage());
        }

        Log.i("MyRetrofitCallback","onFailure="+t.toString());
    }
}
