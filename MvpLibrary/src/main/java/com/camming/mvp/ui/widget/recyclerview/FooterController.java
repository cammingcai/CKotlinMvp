package com.camming.mvp.ui.widget.recyclerview;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;


import com.camming.mvp.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class FooterController {

    private SmartRefreshLayout mSmartRefreshLayout;

    private RecyclerViewController mRecyclerViewController;

    FooterController(@NonNull SmartRefreshLayout smartRefreshLayout, @NonNull RecyclerViewController recyclerViewController) {
        this.mSmartRefreshLayout = smartRefreshLayout;
        this.mRecyclerViewController = recyclerViewController;
    }

    public void setVerticalNailFooter(View view, int height) {
        view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) (height * view.getContext().getResources().getDisplayMetrics().density)));
        ViewGroup footerLayout = mSmartRefreshLayout.findViewById(R.id.nail_footer_layout);
        footerLayout.removeAllViews();
        footerLayout.addView(view);
        footerLayout.requestLayout();
    }

    public void setHorizontalNailFooter(View view, int width) {
        view.setLayoutParams(new LinearLayout.LayoutParams((int) (width * view.getContext().getResources().getDisplayMetrics().density), LinearLayout.LayoutParams.MATCH_PARENT));
        ViewGroup footerLayout = mSmartRefreshLayout.findViewById(R.id.nail_footer_layout);
        footerLayout.removeAllViews();
        footerLayout.addView(view);
        footerLayout.requestLayout();
    }

    public void removeNailFooter() {
        ViewGroup footerLayout = mSmartRefreshLayout.findViewById(R.id.nail_footer_layout);
        footerLayout.removeAllViews();
    }

    public void addFooter(View view) {
        mRecyclerViewController.getAdapter().addFooterView(view);
    }

    public void addFooter(View view, int index) {
        mRecyclerViewController.getAdapter().addFooterView(view, index);
    }

    public void addFooter(View view, int index, int orientation) {
        mRecyclerViewController.getAdapter().addFooterView(view, index, orientation);
    }

    public void removeAllFooterView() {
        mRecyclerViewController.getAdapter().removeAllFooterView();
    }
}
