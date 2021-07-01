package com.camming.mvp.ui.widget.recyclerview;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;


import com.camming.mvp.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;


public class HeaderController {

    private SmartRefreshLayout mSmartRefreshLayout;

    private RecyclerViewController mRecyclerViewController;

    HeaderController(@NonNull SmartRefreshLayout smartRefreshLayout, @NonNull RecyclerViewController recyclerViewController) {
        this.mSmartRefreshLayout = smartRefreshLayout;
        this.mRecyclerViewController = recyclerViewController;
    }

    public void setNailHeader(View view, int height) {
        view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) (height * view.getContext().getResources().getDisplayMetrics().density)));
        ViewGroup headerLayout = mSmartRefreshLayout.findViewById(R.id.nail_header_layout);
        headerLayout.removeAllViews();
        headerLayout.addView(view);
        headerLayout.requestLayout();
    }

    public void removeNailHeader() {
        ViewGroup headerLayout = mSmartRefreshLayout.findViewById(R.id.nail_header_layout);
        headerLayout.removeAllViews();
    }

    public void addHeader(View view) {
        mRecyclerViewController.getAdapter().addHeaderView(view);
    }

    public void addHeader(View view, int index) {
        mRecyclerViewController.getAdapter().addHeaderView(view, index);
    }

    public void addHeader(View view, int index, int orientation) {
        mRecyclerViewController.getAdapter().addHeaderView(view, index, orientation);
    }

    public void removeAllHeaderView() {
        mRecyclerViewController.getAdapter().removeAllHeaderView();
    }
}
