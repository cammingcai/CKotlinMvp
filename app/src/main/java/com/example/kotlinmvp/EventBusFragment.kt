package com.example.kotlinmvp

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.camming.mvp.ui.BaseActivity
import com.example.kotlinmvp.MvpExpands.showToast
import com.example.kotlinmvp.event.MessageEvent
import com.example.kotlinmvp.model.news.NewsBean
import com.example.kotlinmvp.mvp.MvpKtPresenter
import com.example.kotlinmvp.mvp.MyRetrofitCallback
import com.example.kotlinmvp.ui.adapter.NewsAdapter
import kotlinx.android.synthetic.main.fragment_event_bus.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


private const val ARG_PARAM1 = "param1"


class EventBusFragment :XBaseFragment<MvpKtPresenter>() {
    override val fragmentName: String get() = "EventBusFragment"
    private var mType: String = "top"


    private val  mNewAdapter by lazy { NewsAdapter(activity!!) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mType = it.getString(ARG_PARAM1).toString()

        }
    }

    override fun createPresenter() =  MvpKtPresenter(null)

    override fun getLayoutId(): Int = R.layout.fragment_event_bus

    override fun initView() {
        var manager  = LinearLayoutManager(activity)
        rv_news.initSingleTypeRecycleView(manager, mNewAdapter, true)

        rv_news.refreshController().setEnableLoadMore(false)
        rv_news.refreshController().setEnablePullToRefresh(true)

        rv_news.refreshController().setOnRefreshListener {

            queryNewsData(1)
        }
        rv_news.loadMoreController().setOnLoadMoreListener {

            mPage++
            queryNewsData(mPage)
        }
        mNewAdapter.setOnItemClickListener { adapter, view, position ->


        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMessage(event: MessageEvent){
        Log.i(fragmentName, "${fragmentName}${event.message}")
        showToast("${fragmentName}${event.message}\"")
    }

    override fun initListener() {

    }
    private var mPage =1
    private var mPageSize =10
    override fun initData() {

        Log.i(fragmentName, "initData")

        rv_news.refreshController().autoRefresh()
//        queryNewsData(mPage)

    }

    override fun isLazyLoad(): Boolean  = true

    private fun queryNewsData(page: Int){
//        showLoading("查询中")
        mvpPresenter?.queryNews(
            mType,
            page.toString(),
            mPageSize.toString(),
            object : MyRetrofitCallback<NewsBean>(activity as BaseActivity) {
                override fun onSuccess(model: NewsBean) {
                    rv_news.refreshController().refreshComplete()
                    rv_news.loadMoreController().loadMoreComplete()
//                    hideLoading()
                    if (page == 1) {
                        mNewAdapter.setNewData(model.data)

                    } else {
                        model.data?.let { mNewAdapter.addData(it) }
                    }
                }

                override fun onFailure(msg: String?) {
                    rv_news.refreshController().refreshComplete()
                    rv_news.loadMoreController().loadMoreComplete()
                }
            })
    }




    companion object {

        fun newInstance(type: String) =
            EventBusFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, type)
                }
            }
    }
}