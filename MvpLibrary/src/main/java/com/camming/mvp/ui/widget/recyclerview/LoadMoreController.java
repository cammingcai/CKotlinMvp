package com.camming.mvp.ui.widget.recyclerview;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.loadmore.LoadMoreView;


public class LoadMoreController implements BaseQuickAdapter.RequestLoadMoreListener {

    private OnLoadMoreListener mOnLoadMoreListener;

    private RecyclerViewController mRecyclerViewController;

    LoadMoreController(@NonNull RecyclerViewController recyclerViewController) {
        this.mRecyclerViewController = recyclerViewController;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        mOnLoadMoreListener = listener;
    }

    OnLoadMoreListener getOnLoadMoreListener() {
        return mOnLoadMoreListener;
    }

    /**
     * 自定义LoadMoreView
     *
     * @param view
     */
    public void setLoadMoreView(LoadMoreView view) {
        mRecyclerViewController.getAdapter().setLoadMoreView(view);
    }

    @Override
    public void onLoadMoreRequested() {

    }

    public static interface OnLoadMoreListener {
        void onLoadMore(AppRecyclerView appRecyclerView);
    }

    public void loadMoreComplete() {
        mRecyclerViewController.getAdapter().loadMoreComplete();
    }

    public void loadMoreEnd() {
        mRecyclerViewController.getAdapter().loadMoreEnd();
    }
    public void loadMoreEndEmpty() {
        mRecyclerViewController.getAdapter().loadMoreEnd();
    }
    public void loadMoreEnd(boolean gone) {
        mRecyclerViewController.getAdapter().loadMoreEnd(gone);
    }

    public void loadMoreFail() {
        mRecyclerViewController.getAdapter().loadMoreFail();
    }
}
