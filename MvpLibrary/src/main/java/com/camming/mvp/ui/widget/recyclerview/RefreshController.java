package com.camming.mvp.ui.widget.recyclerview;

import androidx.annotation.NonNull;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;

public class RefreshController {

    private SmartRefreshLayout mSmartRefreshLayout;

    private OnRefreshListener mOnRefreshListener;

    RefreshController(@NonNull SmartRefreshLayout smartRefreshLayout) {
        this.mSmartRefreshLayout = smartRefreshLayout;
    }

    public void autoRefresh() {
        mSmartRefreshLayout.autoRefresh();
    }

    public void setEnablePullToRefresh(boolean enable) {
        mSmartRefreshLayout.setEnableRefresh(enable);
    }

    public void setOnRefreshListener(OnRefreshListener listener) {
        mOnRefreshListener = listener;
    }

    /**
     * 是否启用纯滚动模式（只有回弹效果）
     *
     * @param enable
     */
    public void setEnablePureScrollMode(boolean enable) {
        mSmartRefreshLayout.setEnablePureScrollMode(enable);
    }

    /**
     * 是否启用嵌套滚动
     *
     * @param enable
     */
    public void setEnableNestedScroll(boolean enable) {
        mSmartRefreshLayout.setEnableNestedScroll(enable);
    }

    /**
     * 是否启用越界回弹
     *
     * @param enable
     */
    public void setEnableOverScrollBounce(boolean enable) {
        mSmartRefreshLayout.setEnableOverScrollBounce(enable);
    }

    /**
     * 是否启用越界拖动
     *
     * @param enable
     */
    public void setEnableOverScrollDrag(boolean enable) {
        mSmartRefreshLayout.setEnableOverScrollDrag(enable);
    }

    public void refreshComplete() {
        if (mSmartRefreshLayout != null) mSmartRefreshLayout.finishRefresh();
    }

    public boolean isRefreshing() {
        return mSmartRefreshLayout.getState() == RefreshState.Refreshing;
    }

    public void setEnableLoadMore(Boolean hasMore){
        mSmartRefreshLayout.setEnableLoadMore(hasMore);
    }

    OnRefreshListener getOnRefreshListener() {
        return mOnRefreshListener;
    }

    public static interface OnRefreshListener {
        void onRefreshing(AppRecyclerView appRecyclerView);
    }
}
