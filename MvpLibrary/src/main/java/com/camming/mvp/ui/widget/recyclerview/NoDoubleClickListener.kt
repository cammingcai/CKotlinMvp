package com.camming.mvp.ui.widget.recyclerview

import android.view.View

/**
 * Created by yanggl on 2019/10/29 15:55
 */
class NoDoubleClickListener(val mListener: (v: View) -> Unit) : View.OnClickListener {

    private var lastTime=0L

    override fun onClick(v: View) {
        val t=System.currentTimeMillis()
        if ((t-lastTime)> 800){
            mListener.invoke(v)
            lastTime=t
        }
    }

}