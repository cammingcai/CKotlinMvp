package com.example.kotlinmvp.mvp;

import com.camming.mvp.mvp.retrofit.MvpApi;
import com.example.kotlinmvp.JsonResult;
import com.example.kotlinmvp.Result;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitMvpApi  {


    @GET("historyWeather/province")
    Call<JsonResult<List<Result>>> loadWetherByRetrofit(@Query("key") String key);


    @GET("historyWeather/province")
    Call<ResponseBody> loadWetherByRetrofit2(@Query("key") String key);

//    @GET("historyWeather/province")
//    Call<String> loadWetherByRetrofitString(@Query("key") String key);

}
