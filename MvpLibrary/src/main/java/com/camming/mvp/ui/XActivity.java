package com.camming.mvp.ui;

import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.camming.mvp.utils.ActivityUtils;
import com.camming.mvp.utils.XPermission;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by camming on 2019\1\22 0022.
 * code is data  data is code
 */

public abstract class XActivity extends AppCompatActivity {
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityUtils.getInstance().addActivity(this);
        if(initLayoutId()>0){
            setContentView(initLayoutId());
            unbinder = ButterKnife.bind(this);
        }
        initView();
        initData();
        initListener();
    }
    /**初始化布局*/
    protected abstract void initView();
    /**初始化数据*/
    protected abstract void initData();
    /**初始化监听*/
    protected abstract void initListener();
    /**设置布局UI xml*/
    protected abstract int initLayoutId();

    /**
     *
     *
     * Android M 全局权限申请回调
     * @param requestCode  请求码
     * @param permissions  权限数组
     * @param grantResults 授权数组 item 为0 说明已授权 否则未授权
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[]
            grantResults) {
        XPermission.onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(unbinder!=null){
            unbinder.unbind();
        }
        ActivityUtils.getInstance().finishActivity();
    }
}
