package com.example.kotlinmvp.ui.adapter

import android.content.Context

import com.camming.mvp.ui.widget.recyclerview.AppBaseMultiItemQuickAdapter

import com.chad.library.adapter.base.BaseViewHolder
import com.example.kotlinmvp.R
import com.example.kotlinmvp.event.MessageEvent


class Test3Adapter(val mC:Context) : AppBaseMultiItemQuickAdapter<MessageEvent, BaseViewHolder>(null) {


    init {

        addItemType(0, R.layout.activity_event_bus_test)
        addItemType(1, R.layout.activity_event_bus_test)
    }

    override fun convert(helper: BaseViewHolder, result: MessageEvent?) {
//        helper.addOnClickListener(R.id.btn_phone)
        result?.let {


        }

    }
}