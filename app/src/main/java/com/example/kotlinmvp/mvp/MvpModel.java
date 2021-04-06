package com.example.kotlinmvp.mvp;

import com.camming.mvp.mvp.BaseModel;
import com.camming.mvp.mvp.retrofit.MvpApi;
import com.camming.mvp.mvp.retrofit.MvpRetrofit;

public class MvpModel implements BaseModel {

    private RetrofitMvpApi mRetrofitMvpApi;


    @Override
    public MvpApi getMvpApi() {

        return null;
    }


    public RetrofitMvpApi getRetrofitMvpApi() {
        if(mRetrofitMvpApi==null)
            mRetrofitMvpApi = MvpRetrofit.getInstance().retrofit().create(RetrofitMvpApi.class);
        return mRetrofitMvpApi;
    }
}
