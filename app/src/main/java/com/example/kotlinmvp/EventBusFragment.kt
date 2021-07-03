package com.example.kotlinmvp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinmvp.MvpExpands.hideLoading
import com.example.kotlinmvp.MvpExpands.showLoading
import com.example.kotlinmvp.MvpExpands.showToast

import com.example.kotlinmvp.event.MessageEvent
import com.example.kotlinmvp.model.news.NewsBean
import com.example.kotlinmvp.mvp.MvpKtPresenter
import com.example.kotlinmvp.mvp.MyRetrofitCallback
import com.example.kotlinmvp.ui.adapter.NewsAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_event_bus.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class EventBusFragment :XKotlinBaseFragment<MvpKtPresenter>() {
    override val fragmentName: String get() = "EventBusFragment"
    private var param1: String? = null
    private var param2: String? = null

    private val  mNewAdapter by lazy { NewsAdapter(activity!!) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun createPresenter() =  MvpKtPresenter(null)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return super.onCreateView(inflater, container, savedInstanceState)
    }
    override fun getFragmentLayout(): Int = R.layout.fragment_event_bus

    override fun initView(view: View?) {

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMessage(event: MessageEvent){
        Log.i(fragmentName,"${fragmentName}${event.message}")
        showToast("${fragmentName}${event.message}\"")
    }

    private var mPage =1
    private var mPageSize =10
    override fun initData() {

//        btn_event.setOnClickListener {
//            startActivity(Intent(activity,EventBusTestActivity::class.java))
//        }

        var manager  = LinearLayoutManager(activity)
        rv_news.initSingleTypeRecycleView(manager,mNewAdapter,true)

        rv_news.refreshController().setEnableLoadMore(false)
        rv_news.refreshController().setEnablePullToRefresh(true)

        rv_news.refreshController().setOnRefreshListener {

            queryNewsData(1)
        }
        rv_news.loadMoreController().setOnLoadMoreListener {

            mPage++
            queryNewsData(mPage)
        }

        queryNewsData(mPage)

        mNewAdapter.setOnItemClickListener { adapter, view, position ->


        }

    }

    private fun queryNewsData( page:Int){
        showLoading("查询中")
        mvpPresenter?.queryNews(page.toString(),mPageSize.toString(),object : MyRetrofitCallback<NewsBean>() {
            override fun onSuccess(model: NewsBean) {
                rv_news.refreshController().refreshComplete()
                rv_news.loadMoreController().loadMoreComplete()
                hideLoading()
                if(page==1){
                    mNewAdapter.setNewData(model.data)

                }else{
                    mNewAdapter.addData(model.data)
                }
            }

            override fun onFailure(msg: String?) {
                rv_news.refreshController().refreshComplete()
                rv_news.loadMoreController().loadMoreComplete()
            }
        })
    }


    companion object {

        fun newInstance(param1: String, param2: String) =
            EventBusFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}