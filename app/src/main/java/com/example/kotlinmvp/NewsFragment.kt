package com.example.kotlinmvp

import android.os.Bundle
import com.camming.mvp.ui.fragment.BaseFragment
import com.example.kotlinmvp.model.news.NewsTitleData
import com.example.kotlinmvp.mvp.MvpKtPresenter
import com.example.kotlinmvp.ui.adapter.PViewPagerAdapter
import kotlinx.android.synthetic.main.activity_news.*


class NewsFragment :BaseFragment(){
//    override val fragmentName: String get() = "NewsFragment"

    private var newsLists = mutableListOf<NewsTitleData>()
    private var newsFragments = mutableListOf<BaseFragment>()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun getFragmentLayoutId(): Int  = R.layout.activity_news

    override fun initView() {

    }
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    fun onEventMessage(event: MessageEvent){
//        Log.i(fragmentName,"${fragmentName}${event.message}")
//        showToast("${fragmentName}${event.message}\"")
//    }


    override fun initData() {


        var newTabTitles = mutableListOf<String>()
//        newsLists.clear()
        enumValues<News>().forEach {
            newTabTitles.add(it.newName)
//            newsLists.add(NewsTitleData().apply {
//                name = it.newName
//                type = it.newType
//            })
            newsFragments.add(EventBusFragment.newInstance(it.newType))
        }

        var mAdapter = PViewPagerAdapter(childFragmentManager)

        vp.offscreenPageLimit = newsFragments.size
        vp.adapter = mAdapter
        mAdapter.setData(newsFragments)

        stl.setViewPager(vp,newTabTitles.toTypedArray())

    }


}