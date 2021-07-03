package com.example.kotlinmvp.mvp;

import com.example.kotlinmvp.model.JsonResult;
import com.example.kotlinmvp.Result;
import com.example.kotlinmvp.model.PhoneData;
import com.example.kotlinmvp.model.news.NewsBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitMvpApi  {


    @GET("historyWeather/province")
    Call<JsonResult<List<Result>>> loadWetherByRetrofit(@Query("key") String key);


    @GET("historyWeather/province")
    Call<ResponseBody> loadWetherByRetrofit2(@Query("key") String key);

    @GET("mobile/get")
    Call<JsonResult<PhoneData>> queryPhone(@Query("key") String key, @Query("phone") String phone);

    @POST("toutiao/index")
    Call<JsonResult<NewsBean>> queryNews(@Query("key") String key, @Query("type") String type,
                                         @Query("page") String page, @Query("page_size") String page_size);

}
