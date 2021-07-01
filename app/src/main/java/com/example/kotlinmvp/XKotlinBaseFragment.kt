package com.example.kotlinmvp


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.camming.mvp.mvp.BasePresenter
import com.camming.mvp.ui.fragment.BaseFragment
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe


abstract class XKotlinBaseFragment<P : BasePresenter<*>> : BaseFragment() {

    abstract val fragmentName: String



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        EventBus.getDefault().register(this);
        return super.onCreateView(inflater, container, savedInstanceState)
    }
    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }



}