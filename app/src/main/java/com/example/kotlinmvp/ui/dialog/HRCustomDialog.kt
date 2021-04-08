package com.hellorobotedu.aiparent.ui.dialog

import android.app.Dialog
import android.content.Context
import com.example.kotlinmvp.R


/**
 * Create by Cabbage Camming 2021/4/7.
 */
class HRCustomDialog:Dialog {
    constructor(context: Context,title:String,onSureClick:()->Unit):super(context, R.style.DialogActionSheetStyle){
        setCanceledOnTouchOutside(true)
//        setContentView(R.layout.hr_dialog_ranks_qrcode)

    }

    override fun onStart() {
        super.onStart()


    }


}