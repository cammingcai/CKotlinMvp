package com.example.kotlinmvp.mvp

import com.camming.mvp.mvp.retrofit.MvpRetrofit

/**
 * Create by Cabbage on 2021/7/7.
 *
 */
class Api {

    companion object{
        var mApi = MvpRetrofit.getInstance().retrofit().create<RetrofitMvpApi>(RetrofitMvpApi:: class.java)
    }
}