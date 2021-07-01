package com.camming.mvp.ui.widget.recyclerview;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;

public class RecyclerViewController {

    private RecyclerView mRecyclerView;

    private BaseQuickAdapter mBaseQuickAdapter;

    RecyclerViewController(@NonNull RecyclerView recyclerView, @NonNull BaseQuickAdapter adapter) {
        this.mRecyclerView = recyclerView;
        this.mBaseQuickAdapter = adapter;
    }

    public void addItemAnimator(DefaultItemAnimator animator){
        mRecyclerView.setItemAnimator(animator);
    }

    public void removeItemAnimator(){
        mRecyclerView.setItemAnimator(null);
    }

    public void addOnVerticalScrollListener(OnVerticalScrollListener listener) {
        mRecyclerView.addOnScrollListener(listener);
    }

    public void removeOnVerticalScrollListener(OnVerticalScrollListener listener) {
        mRecyclerView.removeOnScrollListener(listener);
    }

    public void addOnScrollListener(RecyclerView.OnScrollListener listener) {
        mRecyclerView.addOnScrollListener(listener);
    }

    public void removeOnScrollListener(RecyclerView.OnScrollListener listener) {
        mRecyclerView.removeOnScrollListener(listener);
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        mRecyclerView.setLayoutManager(layoutManager);
    }

    public void setHasFixedSize(boolean hasFixedSize) {
        mRecyclerView.setHasFixedSize(hasFixedSize);
    }

    public void scrollToTop() {
        if (mRecyclerView.getLayoutManager() != null && mRecyclerView.getAdapter() != null) {
            mRecyclerView.getLayoutManager().scrollToPosition(0);
        }
    }

    public void scrollToPosition(int position) {
        if (mRecyclerView.getLayoutManager() != null && mRecyclerView.getAdapter() != null) {
            mRecyclerView.getLayoutManager().scrollToPosition(position);
        }
    }

    public void scrollToEnd() {
        if (mRecyclerView.getLayoutManager() != null && mRecyclerView.getAdapter() != null) {
            mRecyclerView.getLayoutManager().scrollToPosition(mRecyclerView.getAdapter().getItemCount() - 1);
        }
    }

    public void notifyDataSetChanged() {
        if (mRecyclerView.getAdapter() != null) {
            mRecyclerView.getAdapter().notifyDataSetChanged();
        }
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return mRecyclerView.getLayoutManager();
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    public BaseQuickAdapter getAdapter() {
        return mBaseQuickAdapter;
    }

    /**
     * 是否启用item加载动画，默认启用
     *
     * @param enable
     */
    public void setLoadAnimationEnable(boolean enable) {
        if (enable) {
            mBaseQuickAdapter.openLoadAnimation();
        } else {
            mBaseQuickAdapter.closeLoadAnimation();
        }
    }

    /**
     * 设置Item动画样式
     *
     * @param animType
     */
    public void setLoadAnimationType(int animType) {
        mBaseQuickAdapter.openLoadAnimation(animType);
    }

    /**
     * 设置Item动画是否只有第一次展示的时候才加载
     *
     * @param enable
     */
    public void setLoadAnimationFirstOnly(boolean enable) {
        mBaseQuickAdapter.isFirstOnly(enable);
    }

    public static abstract class OnVerticalScrollListener extends RecyclerView.OnScrollListener {

        @Override
        public final void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            if (!recyclerView.canScrollVertically(-1)) {
                onScrolledToTop();
            } else if (!recyclerView.canScrollVertically(1)) {
                onScrolledToBottom();
            } else if (dy < 0) {
                onScrolledUp();
            } else if (dy > 0) {
                onScrolledDown();
            }
        }

        public void onScrolledUp() {
        }

        public void onScrolledDown() {
        }

        public void onScrolledToTop() {
        }

        public void onScrolledToBottom() {
        }
    }

}
