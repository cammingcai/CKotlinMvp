package com.example.kotlinmvp

import android.app.Activity
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.camming.mvp.ui.view.loadingview.XLoadingDialog
import com.camming.mvp.ui.view.loadingview.XLoadingView
import com.camming.mvp.utils.ToastUtils
import com.example.kotlinmvp.MvpExpands.showLoading

/**
 * Create by Cabbage on 2021/6/29.
 *
 * 扩展lei
 */
object MvpExpands {

    fun Activity.showToast(msg:CharSequence){
        ToastUtils.show(this,msg.toString())
    }
    fun Fragment.showToast(msg:CharSequence){
        ToastUtils.show(activity ,msg.toString())
    }

    fun Activity.showLoading(msg:String){

        XLoadingDialog.with(this).setMessage(msg).show()
    }
    fun Activity.hideLoading(){
        XLoadingDialog.with(this).dismiss()
    }
    fun Fragment.showLoading(msg:String){

        XLoadingDialog.with(activity).setMessage(msg).show()
    }
    fun Fragment.hideLoading(){
        XLoadingDialog.with(activity).dismiss()
    }
}