package com.example.kotlinmvp


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.camming.mvp.mvp.BasePresenter
import com.camming.mvp.ui.fragment.BaseFragment
import org.greenrobot.eventbus.EventBus


abstract class XKotlinBaseFragment<P : BasePresenter<*>> : BaseFragment() {

    abstract val fragmentName: String

    protected var mvpPresenter: P? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mvpPresenter = createPresenter()
        EventBus.getDefault().register(this);
        return super.onCreateView(inflater, container, savedInstanceState)
    }


    protected abstract fun createPresenter(): P


    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }



}