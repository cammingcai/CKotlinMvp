package com.camming.mvp.ui;

import android.os.Bundle;

import com.camming.mvp.mvp.BasePresenter;
import com.camming.mvp.mvp.MainView;
import com.camming.mvp.utils.ProgressDialog;
import com.camming.mvp.utils.ToastUtils;


/**

 */
public abstract class MvpActivity<P extends BasePresenter> extends BaseActivity {
    protected P mvpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mvpPresenter = createPresenter();
        super.onCreate(savedInstanceState);
    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
    }
    public void showLoading(String msg) {
        ProgressDialog.getInstance().show(this,msg);
    }
    public void hideLoading() {
        ProgressDialog.getInstance().dismiss();
    }

    public void showTips(String msg){
        ToastUtils.show(this,msg);
    }

}


