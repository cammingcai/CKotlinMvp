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


/**
 * Create by Cabbage Camming 2021/4/6.
 */
abstract class XKotlinBaseActivit<P : BasePresenter<*>>: BaseActivity() {

    abstract val activityName: String
    protected var mIsDarkMode = true
    protected var mvpPresenter: P? = null
    @Subscribe
    public override  fun onCreate(savedInstanceState: Bundle?) {
        mvpPresenter = createPresenter()
        super.onCreate(savedInstanceState)
        StatusBarUtil.immersive(this)
//        StatusBarUtil.justMDarkMode(this, mIsDarkMode)
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
    fun getNotchHeight(onOk: (height: Int) -> Unit){
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
//                Log.e("TAG", "安全区域距离屏幕右部的距离 SafeInsetRight:" + displayCutout.getSafeInsetRight())
//                Log.e("TAG", "安全区域距离屏幕顶部的距离 SafeInsetTop:" + displayCutout.getSafeInsetTop())
//                Log.e("TAG", "安全区域距离屏幕底部的距离 SafeInsetBottom:" + displayCutout.getSafeInsetBottom())
//                val rects: List<Rect> = displayCutout.getBoundingRects()
//                if (rects == null || rects.isEmpty()) {
//                    Log.e("TAG", "不是刘海屏")
//                } else {
//                    Log.e("TAG", "刘海屏数量:" + rects.size())
//                    for (rect in rects) {
//                        Log.e("TAG", "刘海屏区域：$rect")
//                    }
//                }
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
//    /**设置自定义HRCustomToolbar的顶部状态栏的高度*/
//    fun adaptiveNotchScreen(toolbar:HRCustomToolbar?){
//        toolbar?.let {bar->
//            getNotchHeight { h->
//                bar.setNotchHeight(h)
//            }
//        }
//    }

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
