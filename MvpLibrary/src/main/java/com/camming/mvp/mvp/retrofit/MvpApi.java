package com.camming.mvp.mvp.retrofit;


import com.camming.mvp.mvp.MainModel;

import io.reactivex.Observable;



import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * API DEMO
 *  @ Headers 添加请求头
 *  @ Path 替换路径
 *  @ Query 替代参数值，通常是结合get请求的 (GET请求):用于在url后拼接上参数
 *  @ QueryMap(GET请求):如果入参比较多，就可以把它们都放在Map中
 *  @ Body(POST请求):可以指定一个对象作为HTTP请求体
 *  @ Field(POST请求):用于传送表单数据：开头必须多加上@FormUrlEncoded这句注释  替换参数值，是结合post请求的
 *  @ Headers 请求头
 *  post提交数据方式
 *  application/x-www-form-urlencoded  表单数据
 *  multipart/form-data 文件上传
 *  application/json json数据格式
 *  text/xml  xml数据格式
 *
 */
public interface MvpApi {
    //baseUrl

//    String API_SERVER_URL = "http://apis.juhe.cn/";
    String API_SERVER_URL = "http://v.juhe.cn/";


//    query?type=shunfeng&postid=12312312312
    @GET("query")
    Observable<String> getKD(@Query("type") String type,@Query("postid") int postid);

//    //加载天气
    @GET("adat/sk/{cityId}.html")
    Observable<MainModel> loadDataByRetrofitRxJava(@Path("cityId") String cityId);



    @GET("historyWeather/province")
    Observable<ResponseBody> queryWether(@Query("key") String key);


    @POST("api/alipay/createOrder")
    Observable<ResponseBody> createAliOrder(@Query("token") String token,@Query("id") String id,
                                      @Query("study_coin") String study_coin,@Query("platform") String platform);




    @Multipart
    @POST("api/user/avatar")
    Observable<ResponseBody> uploadFile(@Query("token") String token, @Part MultipartBody.Part file);


    @Streaming
    @GET
    Observable<ResponseBody> downloadFile(@Url String fileUrl);


}
