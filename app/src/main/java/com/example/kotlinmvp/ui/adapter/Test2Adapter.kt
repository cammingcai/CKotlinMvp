package com.example.kotlinmvp.ui.adapter

import android.content.Context
import com.camming.mvp.ui.widget.recyclerview.AppBaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.kotlinmvp.R
import com.example.kotlinmvp.event.MessageEvent


/**
 * orange-company
 */
class Test2Adapter(val mC:Context) : AppBaseQuickAdapter<MessageEvent, BaseViewHolder>(R.layout.activity_event_bus_test){
    var mPos = -1
    override fun convert(helper: BaseViewHolder, item: MessageEvent?) {
        item?.let {

        }
    }



}