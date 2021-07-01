package com.camming.mvp.ui.widget.recyclerview;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

/**
 * Created by yanggl on 2019/10/29 14:09
 */
public class AppOnItemClickListener implements BaseQuickAdapter.OnItemClickListener {


    public static int CLICK_INTERVAL=800;

    private BaseQuickAdapter.OnItemClickListener mListener;

    public AppOnItemClickListener(BaseQuickAdapter.OnItemClickListener mListener){
        this.mListener = mListener;
    }

    private long lastTime=0;

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//        long t= System.currentTimeMillis();
//        if (mListener!=null && (t-lastTime)> CLICK_INTERVAL){
//            mListener.onItemClick(adapter,view,position);
//            lastTime=t;
//        }

        if (mListener!=null){
            mListener.onItemClick(adapter,view,position);

        }
    }
}
