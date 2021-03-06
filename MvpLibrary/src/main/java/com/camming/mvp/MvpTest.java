package com.camming.mvp;


import com.camming.mvp.mvp.MainModel;
import com.camming.mvp.mvp.MainPresenter;
import com.camming.mvp.mvp.MvpViewCallback;

/**
 * Created by camming on 2019\3\4 0004.
 * code is data  data is code
 */
public class MvpTest extends Mvp<MainPresenter> implements MvpViewCallback<MainModel> {

    public void  startData(){
        mvpPresenter = createPresenter();
//        mvpPresenter.loadDataByRetrofitRxjava("101310222");
       // mvpPresenter.queryGrades("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwaG9uZSI6IjEzNTg4ODg4ODg4IiwiZXhwIjoxNTUzOTk4OTY0fQ.Nb3ptdr_c67HT9a-0iaUjVJr_HUBDs99-jHNzht1wBk");
    }
    @Override
    protected MainPresenter createPresenter() {
//        mvpPresenter = new MainPresenter(this);
//        return mvpPresenter;
        return new MainPresenter(this);
    }


    @Override
    public void getDataSuccess(MainModel model) {
       // Log.i("MvpTest","model="+model.getWeatherinfo().getCity());
    }

    @Override
    public void getDataFail(String msg) {

    }

    @Override
    public void showErrorMessage() {

    }
}
