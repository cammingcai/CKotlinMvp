package com.example.kotlinmvp.ui.adapter

import android.app.Activity
import androidx.recyclerview.widget.RecyclerView
import com.camming.mvp.ui.adapter.XRecyclerViewAdapter
import com.camming.mvp.ui.adapter.XViewHolder
import com.example.kotlinmvp.R

import com.example.kotlinmvp.event.MessageEvent


/**
 * Create by Cabbage on 2021/7/1.
 *
 */
class TestAdapter(val mC: Activity, mRecyclerView: RecyclerView, dataLists: List<MessageEvent>)
    : XRecyclerViewAdapter<MessageEvent>(mRecyclerView, dataLists) {

    override fun getItemLayoutResId(data: MessageEvent, position: Int): Int {
        return R.layout.activity_event_bus_test
    }

    override fun bindData(holder: XViewHolder, data: MessageEvent, position: Int) {
        holder.setText(R.id.btn,"${data.message}---${position}")
    }
}