package com.camming.mvp.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;

import com.camming.mvp.utils.StatusBarUtil;

/**

 */
public abstract class BaseActivity extends XActivity {
    public Activity mActivity;
//    private CompositeDisposable mCompositeDisposable;
//    private List<Call> calls;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        mActivity = this;
    }


    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        mActivity = this;
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        mActivity = this;
    }


    @Override
    protected void onDestroy() {

        super.onDestroy();
    }



}
