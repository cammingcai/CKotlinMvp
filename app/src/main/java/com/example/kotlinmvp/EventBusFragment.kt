package com.example.kotlinmvp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlinmvp.MvpExpands.mvpToast
import com.example.kotlinmvp.event.MessageEvent
import com.example.kotlinmvp.mvp.MvpKtPresenter
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

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
        mvpToast("${fragmentName}${event.message}\"")
    }
    override fun initData() {

        btn_event.setOnClickListener {
            startActivity(Intent(activity,EventBusTestActivity::class.java))
        }

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