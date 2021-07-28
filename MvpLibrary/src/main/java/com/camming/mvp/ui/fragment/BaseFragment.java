package com.camming.mvp.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.camming.mvp.utils.ProgressDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;


/**
 * Created by camming on 2018\11\22 0022.
 * 继承  不是为了使用某个类的某个功能而继承
 * 而是类与类之间有所属关系
 *
 *
 */

public abstract class BaseFragment extends Fragment {

    /**
     * 是否初始化过布局
     */
    protected boolean isViewInitiated;
    /**
     * 当前界面是否可见
     */
    protected boolean isVisibleToUser;
    /**
     * 是否加载过数据
     */
    protected boolean isDataInitiated;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =null ;
        if(getLayoutId()>0){
            view  = inflater.inflate(getLayoutId(), null);
//            unbinder = ButterKnife.bind(this,view);
            //unbinder = ButterKnife.bind(view);//这个方法在fragment 无效
        }else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
        return view;
    }

    public void showDialog(String msg){
        ProgressDialog.getInstance().show(getActivity(),msg);
    }
    public void dismissDialog(){
        ProgressDialog.getInstance().dismiss();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        if(isLazyLoad()){
            isViewInitiated = true;
            prepareFetchData();
        }else{
            initData();
        }
        initListener();
    }
    /**
     * 启用懒加载，就是仅仅在fragment第一次可见的时候加载数据
     */
    protected Boolean isLazyLoad(){
        return false;
    }

    public abstract int getLayoutId();
    public abstract void initView();

    /**
     * 懒加载
     */
    public abstract void initData();
    public abstract void initListener();
    @Override
    public void onDestroy() {
        super.onDestroy();

        onUnsubscribe();
    }



    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.i("BaseFragment","setVisibleToUser isVisibleToUser="+isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        if (isVisibleToUser&&isLazyLoad()) {
            prepareFetchData();
        }
    }


    /**
     * 判断懒加载条件

     */
    public void prepareFetchData() {
        Log.i("BaseFragment","prepareFetchData isVisibleToUser="+isVisibleToUser+"--isViewInitiated="+isViewInitiated+"---isDataInitiated="+isDataInitiated);

        if (isVisibleToUser && isViewInitiated && (!isDataInitiated)) {
            initData();
            isDataInitiated = true;
        }
    }
    private CompositeDisposable mCompositeDisposable;

    public void onUnsubscribe() {
        //取消注册，以避免内存泄露
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }

    public void addSubscription(DisposableObserver observer) {
//        if (mCompositeDisposable == null) {
        mCompositeDisposable = new CompositeDisposable();
//        }
        mCompositeDisposable.add(observer);
    }
}
