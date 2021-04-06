package com.example.kotlinmvp.mvp;


import com.camming.mvp.mvp.BasePresenter;
import com.camming.mvp.mvp.MainView;
import com.example.kotlinmvp.Result;

import java.util.List;

public class MvpKtPresenter extends BasePresenter<MainView> {

    private MvpModel model;
    public MvpKtPresenter(MainView view) {
        attachView(view);
        model = new MvpModel();
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

        model.getRetrofitMvpApi().loadWetherByRetrofit(key).enqueue(new MyRetrofitCallback<List<Result>>() {
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
