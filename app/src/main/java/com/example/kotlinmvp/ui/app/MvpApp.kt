package com.example.kotlinmvp.ui.app

import android.app.Application
import org.litepal.LitePal

/**
 * Create by Cabbage on 2021/7/1.
 *
 */
class MvpApp:Application() {

    override fun onCreate() {
        super.onCreate()


        LitePal.initialize(this)
    }
}