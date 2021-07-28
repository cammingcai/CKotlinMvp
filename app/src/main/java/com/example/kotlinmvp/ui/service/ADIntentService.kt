package com.example.kotlinmvp.ui.service

import android.app.IntentService
import android.content.Intent
import android.content.Context

import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.camming.mvp.utils.XFileUtil

import org.litepal.LitePal
import java.io.File


private const val ACTION_FOO = "com.example.kotlinmvp.ui.service.action.FOO"

private const val PARAM_URL = "com.example.kotlinmvp.ui.service.extra.URL"
private const val PARAM_IMG_ID = "com.example.kotlinmvp.ui.service.extra.IMG_ID"
/**
 *  Create by cabbage on 2021/04/20
 *
 *  首次缓存广告图片到本地
 *
 * */

class ADIntentService : IntentService("ADIntentService") {

    override fun onHandleIntent(intent: Intent?) {
        when (intent?.action) {
            ACTION_FOO -> {
                val adUrl = intent.getStringExtra(PARAM_URL)
                val adId = intent.getStringExtra(PARAM_IMG_ID)
                if (adId != null) {
                    if (adUrl != null) {
                        handleActionAd(adUrl, adId)
                    }
                }
            }

        }
    }


    private fun handleActionAd(url: String, id: String) {

        try {

            var adFile = Glide.with(this).load(url)
                .downloadOnly(Target.SIZE_ORIGINAL,Target.SIZE_ORIGINAL)
                .get()
            XFileUtil.makeDir(XFileUtil.getExternalPackagePath(this) + "/ad")
            var path = XFileUtil.getExternalPackagePath(this) + "/ad"+"/hello_robot_ad_${System.currentTimeMillis()}.jpg"
            val destFile = File(path)

            var success = XFileUtil.copyFile(adFile,destFile,true,true)
//
//            try {
//                LitePal.deleteAll(AdInfo::class.java,"imgId=?",id)
//            }catch (e:Exception){
//
//            }

//            AdInfo().also {
//                it.imgId = id
//                it.imgPath = destFile.absolutePath
//                it.imgUrl = url
//                it.save()
//                LogUtil.i("缓存广告路径 destFile.absolutePath=${destFile.absolutePath}")
//                LogUtil.i("缓存广告路径 path=${destFile.absolutePath}")
//            }
        }catch (e:Exception){

        }
    }



    companion object {

        fun startServiceAd(context: Context, param1: String, param2: String) {
            val intent = Intent(context, ADIntentService::class.java).apply {
                action = ACTION_FOO
                putExtra(PARAM_URL, param1)
                putExtra(PARAM_IMG_ID, param2)
            }
            context.startService(intent)
        }

    }
}