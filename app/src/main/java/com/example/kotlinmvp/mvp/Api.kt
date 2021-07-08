package com.example.kotlinmvp.mvp

import com.camming.mvp.mvp.retrofit.MvpRetrofit

/**
 * Create by Cabbage on 2021/7/7.
 *
 */
class Api {

    companion object{
        //baseUrl
        var API_SERVER_URL = "http://v.juhe.cn/"
        var mApi = MvpRetrofit.getInstance().retrofit.create<RetrofitMvpApi>(RetrofitMvpApi::class.java)
    }
}