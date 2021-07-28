package com.example.kotlinmvp.ui.app

import android.app.Application
import org.litepal.LitePal

/**
 * Create by Cabbage on 2021/7/1.
 *
 * Glide.with(mContext).asBitmap().load(url)
.submit(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
.get();
 *
 */
class MvpApp:Application() {

    override fun onCreate() {
        super.onCreate()


        LitePal.initialize(this)
    }
}