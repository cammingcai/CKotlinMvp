package com.example.kotlinmvp.mvp;

import android.app.Activity;
import android.util.Log;

import com.example.kotlinmvp.model.JsonResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *统一处理统一格式的回调
 */

public abstract class MyRetrofitCallback<M> implements Callback<JsonResult<M>> {

    public abstract void onSuccess(M model);

    public abstract void onFailure( String msg);

//    private Activity mActivity;
//    MyRetrofitCallback(){
//
//    }
//    MyRetrofitCallback(Activity mAct){
//        mActivity = mAct;
//    }

    @Override
    public void onResponse(Call<JsonResult<M>> call, Response<JsonResult<M>> response) {
        if (response.isSuccessful()) {
            JsonResult<M> result = response.body();
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
    public void onFailure(Call<JsonResult<M>> call, Throwable t) {

//        if(mActivity!=null){
//            mActivity.
//        }
        if (t != null && t.toString().contains("JsonSyntaxException")) {
            onFailure("JSON解析异常"+t.toString());
        }else{
            onFailure(call.toString());
        }

        Log.i("MyRetrofitCallback","onFailure="+t.toString());
    }
}
