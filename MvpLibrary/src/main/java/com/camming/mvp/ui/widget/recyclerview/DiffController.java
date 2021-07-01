package com.camming.mvp.ui.widget.recyclerview;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.diff.BaseQuickDiffCallback;

import java.util.List;

public class DiffController {
    private RecyclerView mRecyclerView;
    private BaseQuickAdapter mAdapter;

    DiffController(RecyclerView recyclerView, BaseQuickAdapter adapter) {
        this.mRecyclerView = recyclerView;
        this.mAdapter = adapter;
    }

    @SuppressWarnings("可能存在数据集过大造成的阻塞")
    public void compareToAndSet(List newList, BaseQuickDiffCallback diffCallback){
        if (mRecyclerView != null && mAdapter != null){
            //初次的数据不需要进行数据集比对
            if (mAdapter.getData().size() == 0
                    || diffCallback == null){
                mAdapter.setNewData(newList);
            }else if (diffCallback != null){
                mAdapter.setNewDiffData(diffCallback,false);
            }
        }
    }

    @SuppressWarnings("可能存在数据集过大造成的阻塞")
    public void compareToAndSet(List newList, DiffUtil.Callback diffCallback){
        if (mRecyclerView != null && mAdapter != null){
            //初次的数据不需要进行数据集比对
            if (mAdapter.getData().size() == 0
                    || diffCallback == null){
                mAdapter.setNewData(newList);
            }else if (diffCallback != null){
                mAdapter.setNewDiffData(DiffUtil.calculateDiff(diffCallback,false),newList);
            }
        }
    }

}
