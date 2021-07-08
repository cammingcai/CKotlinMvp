package com.example.kotlinmvp

import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.view.DisplayCutout
import android.view.View
import android.view.WindowInsets
import com.camming.mvp.mvp.BasePresenter
import com.camming.mvp.ui.BaseActivity
import com.camming.mvp.utils.NotchScreenUtil
import com.camming.mvp.utils.ScreenUtils
import com.camming.mvp.utils.StatusBarUtil
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import qiu.niorgai.StatusBarCompat


/**
 * Create by Cabbage Camming 2021/4/6.
 */
abstract class XBaseActivit<P : BasePresenter<*>>: BaseActivity() {

    abstract val activityName: String
    protected var mIsDarkMode = true
    protected var mvpPresenter: P? = null
    @Subscribe
    public override  fun onCreate(savedInstanceState: Bundle?) {
        mvpPresenter = createPresenter()
        super.onCreate(savedInstanceState)
//        StatusBarUtil.immersive(this)
        StatusBarCompat.translucentStatusBar(this)
        EventBus.getDefault().register(this)
    }

    override fun onResume() {
        super.onResume()

    }
    override fun getResources(): Resources {
        // 字体大小不跟随系统
        val res = super.getResources()
        val config = Configuration()
        config.setToDefaults()
        res.updateConfiguration(config, res.displayMetrics)
        return res
    }

    protected abstract fun createPresenter(): P
    //适配刘海屏相关
    private fun getNotchHeight(onOk: (height: Int) -> Unit){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
            //google官方接口
            val decorView: View = window.decorView

            decorView.post(Runnable {
                val rootWindowInsets: WindowInsets = decorView.rootWindowInsets
                if (rootWindowInsets?.displayCutout == null) {
//                    Log.e("TAG", "rootWindowInsets为空了")
                    onOk.invoke(ScreenUtils.dp2px(this, 25f))
                    return@Runnable
                }
                val displayCutout: DisplayCutout = rootWindowInsets.displayCutout!!
                onOk.invoke(displayCutout.safeInsetTop)
//                Log.e("TAG", "安全区域距离屏幕左边的距离 SafeInsetLeft:" + displayCutout.getSafeInsetLeft())

            })
        }else{
            //厂商接口(暂未适配小米)
            when(NotchScreenUtil.getDeviceBrand()){
                NotchScreenUtil.DEVICE_BRAND_OPPO -> {
                    onOk.invoke(NotchScreenUtil.getNotchSizeAtOppo())
                }
                NotchScreenUtil.DEVICE_BRAND_HUAWEI -> {
                    onOk.invoke(NotchScreenUtil.getNotchSizeAtHuawei(this))
                }
                NotchScreenUtil.DEVICE_BRAND_VIVO -> {
                    onOk.invoke(NotchScreenUtil.getNotchSizeAtVivo(this))
                }
                else ->{
                    onOk.invoke(ScreenUtils.dp2px(this, 25f))
                }
            }
        }
    }


    /**将指定view的高度设置为状态栏的高度*/
    fun adaptiveNotchScreenByView(v: View?){
        v?.let { bar->
            getNotchHeight { h->
                val p = bar.layoutParams
                p.height = h
                bar.layoutParams = p
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        mvpPresenter?.detachView()

        EventBus.getDefault().unregister(this)

    }

}
