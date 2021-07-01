package com.example.kotlinmvp

import android.app.Activity
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.camming.mvp.utils.ToastUtils

/**
 * Create by Cabbage on 2021/6/29.
 *
 * 扩展lei
 */
object MvpExpands {

    fun Activity.mvpToast(msg:CharSequence){
        ToastUtils.show(this,msg.toString())
    }
    fun Fragment.mvpToast(msg:CharSequence){
        ToastUtils.show(activity ,msg.toString())
    }
}