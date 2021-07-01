package com.example.kotlinmvp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinmvp.event.MessageEvent
import com.example.kotlinmvp.mvp.MvpKtPresenter
import kotlinx.android.synthetic.main.activity_event_bus_test.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class EventBusTestActivity : XKotlinBaseActivit<MvpKtPresenter>() {

    override val activityName: String get() = "EventBusTestActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  setContentView(R.layout.activity_event_bus_test)


    }

    override fun createPresenter()= MvpKtPresenter(null)

    override fun initView() {

    }

    override fun initData() {

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    override fun initListener() {

        btn.setOnClickListener {
            EventBus.getDefault().post(MessageEvent("我是EventBus发来的消息"))
        }
    }

    override fun initLayoutId() = R.layout.activity_event_bus_test
}