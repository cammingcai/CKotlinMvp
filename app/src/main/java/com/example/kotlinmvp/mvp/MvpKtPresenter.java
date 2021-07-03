package com.example.kotlinmvp.mvp;


import com.camming.mvp.mvp.BasePresenter;
import com.camming.mvp.mvp.MvpViewCallback;
import com.example.kotlinmvp.Result;
import com.example.kotlinmvp.model.PhoneData;

import java.util.List;

public class MvpKtPresenter extends BasePresenter<MvpViewCallback> {

    private MvpModel mMvpModel;
    public MvpKtPresenter(MvpViewCallback view) {
        attachView(view);
        mMvpModel = new MvpModel();
    }


    public void queryPhoneArea(String phone){

        mMvpModel.getRetrofitMvpApi().queryPhone("374feaa91ac57e84e159505d0e78ed05",phone).enqueue(new MyRetrofitCallback<PhoneData>() {
            @Override
            public void onSuccess(PhoneData model) {
                mvpView.getDataSuccess(model);
            }

            @Override
            public void onFailure(String msg) {
                mvpView.getDataFail(msg);
            }
        });
    }
    public void queryPhoneArea(String phone,MyRetrofitCallback callback){

        mMvpModel.getRetrofitMvpApi().queryPhone("374feaa91ac57e84e159505d0e78ed05",phone).enqueue(callback);
    }

    public void queryNews(String type,String page,String size,MyRetrofitCallback callback){

        mMvpModel.getRetrofitMvpApi().queryNews("2fe35f86e4320abe2d55612f2dbf67ee",type,page,size).enqueue(callback);
    }
    public void getWether(String key){

//        model.getRetrofitMvpApi().loadWetherByRetrofit(key).enqueue(new Callback<List<Result>>() {
//            @Override
//            public void onResponse(Call<List<Result>> call, Response<List<Result>> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Result>> call, Throwable t) {
//
//            }
//        });

        //retrofit + okhttp

        mMvpModel.getRetrofitMvpApi().loadWetherByRetrofit(key).enqueue(new MyRetrofitCallback<List<Result>>() {
            @Override
            public void onSuccess(List<Result> model) {
                mvpView.getDataSuccess(model);
            }

            @Override
            public void onFailure(String msg) {
                mvpView.getDataFail(msg);
            }
        });

    }


/*    public void getWetherRxjava(String key){

          requestRxjavaDataObservable(model.getMvpApi().queryWether(key), new RxjavaCallback<JsonRootBean>() {
            @Override
            public void onSuccess(JsonRootBean model) {
                mvpView.getDataSuccess(model);
            }

            @Override
            public void onFailure(String msg) {
                mvpView.getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });

    }*/

}
