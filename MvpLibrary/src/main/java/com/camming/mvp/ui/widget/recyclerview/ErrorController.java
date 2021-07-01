package com.camming.mvp.ui.widget.recyclerview;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;


import com.chad.library.adapter.base.BaseQuickAdapter;

public class ErrorController {

    private RecyclerView mRecyclerView;
    private BaseQuickAdapter mAdapter;


    ErrorController(RecyclerView recyclerView, BaseQuickAdapter adapter){
        this.mRecyclerView = recyclerView;
        this.mAdapter = adapter;
    }


//    public void setErrorView(String btnText, View.OnClickListener onClickListener){
//        View emptyView = new QMUIViewBuilder(QMUIViewBuilder.TYPE.ERROR_VIEW,
//                btnText,onClickListener).build(mRecyclerView.getContext());
//        if (emptyView != null)
//            mAdapter.setEmptyView(emptyView);
//    }
}
