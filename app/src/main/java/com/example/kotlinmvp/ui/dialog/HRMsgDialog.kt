package com.hellorobotedu.aiparent.ui.dialog

import android.app.Dialog
import android.content.Context
import android.util.DisplayMetrics
import android.view.Gravity
import android.widget.TextView
import com.example.kotlinmvp.R


/**
 * Create by Cabbage Camming 2021/4/7.
 */
class HRMsgDialog:Dialog {
    constructor(context: Context,title:String,onSureClick:()->Unit):super(context, R.style.DialogActionSheetStyle){
        setCanceledOnTouchOutside(true)
//        setContentView(R.layout.hr_msg_dialog)
//        val tv_content = findViewById<TextView>(R.id.tv_content)
//        val tv_sure = findViewById<TextView>(R.id.tv_sure)
//
//        tv_content.text = title
//
//        tv_sure.setClickIntervalListten {
//            onSureClick?.invoke()
//            dismiss()
//        }
    }

    override fun onStart() {
        super.onStart()

        window?.let {
            //设置显示位置
            it.setGravity(Gravity.BOTTOM)
            var displayMetrics  = DisplayMetrics()
            it.windowManager.defaultDisplay.getMetrics(displayMetrics)
            //屏幕宽度
//            displayMetrics.widthPixels
            //屏幕高度
//            displayMetrics.heightPixels
            it.setLayout( displayMetrics.widthPixels,it.attributes.height)

        }
    }


}