package com.camming.mvp.mvp;

import com.camming.mvp.mvp.retrofit.MvpApi;
import com.camming.mvp.mvp.retrofit.MvpRetrofit;




/**
 *  Model接口 创建对应的联网请求的方法
 *  将Presenter提交的字段放到联网请求中，发送给服务器
 */
public class MainModel implements BaseModel  {

    @Override
    public MvpApi getMvpApi() {
        return MvpRetrofit.getInstance().getMvpApi();

    }


}
