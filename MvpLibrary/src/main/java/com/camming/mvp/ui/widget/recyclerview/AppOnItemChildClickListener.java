package com.camming.mvp.ui.widget.recyclerview;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;



/**
 * Created by yanggl on 2019/10/29 14:14
 */
public class AppOnItemChildClickListener implements BaseQuickAdapter.OnItemChildClickListener {

    private BaseQuickAdapter.OnItemChildClickListener mListener;

    public AppOnItemChildClickListener(BaseQuickAdapter.OnItemChildClickListener l){
        mListener = l;
    }

//    private long lastTime=0;

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        long t= System.currentTimeMillis();
//        if (mListener!=null && (t-lastTime)> CLICK_INTERVAL){
//            mListener.onItemChildClick(adapter,view,position);
//            lastTime=t;
//        }
        if (mListener!=null) {
            mListener.onItemChildClick(adapter,view,position);

        }
    }
}
