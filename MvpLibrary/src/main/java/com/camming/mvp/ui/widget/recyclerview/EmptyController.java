package com.camming.mvp.ui.widget.recyclerview;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.camming.mvp.R;
import com.chad.library.adapter.base.BaseQuickAdapter;

public class EmptyController {


    private RecyclerView mRecyclerView;
    private BaseQuickAdapter mAdapter;


    EmptyController(RecyclerView recyclerView, BaseQuickAdapter adapter){
        this.mRecyclerView = recyclerView;
        this.mAdapter = adapter;
    }


    public void setCustomEmpty(View view){
        mAdapter.setEmptyView(view);
    }

    public void setHelloRobotEmpty(){
        setHelloRobotEmpty(0,"","",null);
    }
    public void setHelloRobotEmpty(NoDoubleClickListener clickListener){
        setHelloRobotEmpty(0,"","",clickListener);
    }
    public void setHelloRobotEmpty(String msg, String strButton, NoDoubleClickListener clickListener){
        setHelloRobotEmpty(0,msg,strButton,clickListener);
    }
    public void setHelloRobotEmpty(int iconResId, String msg , String strButton, NoDoubleClickListener clickListener){
        View emptyView = LayoutInflater.from(mRecyclerView.getContext()).inflate(R.layout.tools_hr_common_empty,null);
        ImageView iv = emptyView.findViewById(R.id.iv);
        TextView tv = emptyView.findViewById(R.id.tv);
        TextView tv2 = emptyView.findViewById(R.id.tv_2);

        if (iconResId != 0){
            iv.setImageResource(iconResId);
        }
        if (!TextUtils.isEmpty(msg)){
            tv.setText(msg);
        }
        if (clickListener!=null){
            tv2.setVisibility(View.VISIBLE);
            tv2.setOnClickListener(clickListener);
            if (!TextUtils.isEmpty(strButton)){
                tv2.setText(strButton);
            }
        }else {
            tv2.setVisibility(View.GONE);
        }
        try{
            //这里需要清空原来的数据
            mAdapter.setNewData(null);
        }catch (Exception e){

        }

        mAdapter.setEmptyView(emptyView);


    }




//    public void setEmpty(String content){
//        View emptyView = LayoutInflater.from(mRecyclerView.getContext()).inflate(R.layout.layout_empty,null);
//        ((TextView)emptyView.findViewById(R.id.empty_textView)).setText(content);
//        mAdapter.setEmptyView(emptyView);
//    }
//
//    public void setEmpty(int iconId, String content){
//        View emptyView = LayoutInflater.from(mRecyclerView.getContext()).inflate(R.layout.layout_empty,null);
//        ((ImageView)emptyView.findViewById(R.id.empty_imageview)).setImageResource(iconId);
//        ((TextView)emptyView.findViewById(R.id.empty_textView)).setText(content);
//        mAdapter.setEmptyView(emptyView);
//    }

}
