package com.camming.mvp;


import com.camming.mvp.mvp.BasePresenter;

/**

 */
public abstract class Mvp<P extends BasePresenter> {
    protected P mvpPresenter;


    protected abstract P createPresenter();


    public void onDestroyPresenter(){
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
    }
    public void showLoading(String msg) {
      //  ProgressDialog.getInstance().show(context,msg);
    }
    public void hideLoading() {
      //  ProgressDialog.getInstance().dismiss();
    }
    public void showTips(String msg){

    }
}


