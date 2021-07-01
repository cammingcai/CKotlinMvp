package com.camming.mvp.ui.widget.recyclerview;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by yanggl on 2019/10/29 13:59
 */
public abstract class AppBaseQuickAdapter<T, K extends BaseViewHolder> extends BaseQuickAdapter<T,K> {

    public AppBaseQuickAdapter(int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
    }

    public AppBaseQuickAdapter(@Nullable List<T> data) {
        super(data);
    }

    public AppBaseQuickAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    public void setOnItemClickListener(OnItemClickListener listener) {
        super.setOnItemClickListener(new AppOnItemClickListener(listener));
    }

    @Override
    public void setOnItemChildClickListener(OnItemChildClickListener listener) {
        super.setOnItemChildClickListener(new AppOnItemChildClickListener(listener));
    }
}
