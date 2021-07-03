package com.example.kotlinmvp.ui.adapter

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.camming.mvp.ui.widget.recyclerview.AppBaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.kotlinmvp.R
import com.example.kotlinmvp.event.MessageEvent
import com.example.kotlinmvp.model.news.NewsData



class NewsAdapter(val mC:Context) : AppBaseQuickAdapter<NewsData, BaseViewHolder>(R.layout.adapter_item_news){

    override fun convert(helper: BaseViewHolder, item: NewsData?) {
        item?.let {

            var title = helper.getView<TextView>(R.id.tv_title)
            var category = helper.getView<TextView>(R.id.category)
            var author_name = helper.getView<TextView>(R.id.author_name)
            var thumb = helper.getView<ImageView>(R.id.iv_thumb)
            title.text = it.title
            category.text = it.category
            author_name.text = it.author_name

            Glide.with(mC).load(it.thumbnail_pic_s).into(thumb)
        }
    }



}