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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public abstract void showBaseLoading(String msg);
    public abstract void dissmissBaseLoading();
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);

    }



    @Override
    protected void onDestroy() {

        super.onDestroy();
    }



}
