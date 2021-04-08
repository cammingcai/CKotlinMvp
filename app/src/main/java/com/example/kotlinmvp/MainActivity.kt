package com.example.kotlinmvp

import android.Manifest
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.Log
import android.view.LayoutInflater
import com.camming.mvp.mvp.MainView
import com.camming.mvp.utils.*
import com.example.kotlinmvp.mvp.MvpKtPresenter
import com.example.kotlinmvp.utils.FileHelper
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_generate_img.*

class MainActivity : XKotlinBaseActivit<MvpKtPresenter>() {

    override fun initLayoutId()  = R.layout.activity_scorllview

    override fun initData() {
//      mvpPresenter.getWeather("b9a05b741d04063963bd964e8d79d06c")


        //Retrofit +OKHTTP+Rxjava请求
//        mvpPresenter.getWether("b9a05b741d04063963bd964e8d79d06c")
//        mvpPresenter.getWetherString("b9a05b741d04063963bd964e8d79d06c")

        //Retrofit +OKHTTP请求
//            mvpPresenter?.getWether("b9a05b741d04063963bd964e8d79d06c")

//        mvpPresenter.getWeatherRetrofit("b9a05b741d04063963bd964e8d79d06c")

        //单独OKhttp请求
        //初始化
//        XHttp.init(OKHttpEngine(this))
//        var maps = HashMap<String,Any>()
//        maps.put("key","b9a05b741d04063963bd964e8d79d06c")
//        XHttp.obtain().get("http://v.juhe.cn/historyWeather/province",maps,object : HttpCallBack<JsonRootBean>() {
//            override fun onFailed(error: String?) {
//                Log.i("MainActivity","getDataFail error=${error}")
//            }
//
//            override fun onSuccess(result: JsonRootBean?) {
//                Log.i("MainActivity","getDataFail result=${result}")
//            }
//
//        })
//
//        var json  = "{\"reason\":\"查询成功\",\"result\":[{\"id\":\"1\",\"province\":\"安徽\"},{\"id\":\"2\",\"province\":\"澳门\"},{\"id\":\"3\",\"province\":\"北京\"},{\"id\":\"4\",\"province\":\"福建\"},{\"id\":\"5\",\"province\":\"甘肃\"},{\"id\":\"6\",\"province\":\"广东\"},{\"id\":\"7\",\"province\":\"广西\"},{\"id\":\"8\",\"province\":\"贵州\"},{\"id\":\"9\",\"province\":\"海南\"},{\"id\":\"10\",\"province\":\"河北\"},{\"id\":\"11\",\"province\":\"河南\"},{\"id\":\"12\",\"province\":\"黑龙江\"},{\"id\":\"13\",\"province\":\"湖北\"},{\"id\":\"14\",\"province\":\"湖南\"},{\"id\":\"15\",\"province\":\"吉林\"},{\"id\":\"16\",\"province\":\"江苏\"},{\"id\":\"17\",\"province\":\"江西\"},{\"id\":\"18\",\"province\":\"辽宁\"},{\"id\":\"19\",\"province\":\"内蒙古\"},{\"id\":\"20\",\"province\":\"宁夏\"},{\"id\":\"21\",\"province\":\"青海\"},{\"id\":\"22\",\"province\":\"山东\"},{\"id\":\"23\",\"province\":\"山西\"},{\"id\":\"24\",\"province\":\"陕西\"},{\"id\":\"25\",\"province\":\"上海\"},{\"id\":\"26\",\"province\":\"四川\"},{\"id\":\"27\",\"province\":\"台湾\"},{\"id\":\"28\",\"province\":\"天津\"},{\"id\":\"29\",\"province\":\"西藏\"},{\"id\":\"30\",\"province\":\"香港\"},{\"id\":\"31\",\"province\":\"新疆\"},{\"id\":\"32\",\"province\":\"云南\"},{\"id\":\"33\",\"province\":\"浙江\"},{\"id\":\"34\",\"province\":\"重庆\"}],\"error_code\":0}"
//
//       var result =  JSON.parseObject(json,JsonRootBean::class.java)
//
//        Log.i("MainActivity","result=${result}")

//        val view = LayoutInflater.from(this).inflate(R.layout.generate_img,null,false)
//        bitmap  =XFileUtil.loadBitmapFromView(view)
//        iv_img.setImageBitmap(bitmap)

    }
    var  bitmap:Bitmap? = null
    private fun   initGenerateImg(){

        Thread {

            var file = XFileUtil.saveFileFromBitmap(XFileUtil.getExternalPackagePath(this),
                "${System.currentTimeMillis()} .jpg",bitmap)
            file?.let {
                runOnUiThread {
                    XToast.getInstance(this).success("保存成功")
                }

                FileHelper.insertImageToGallery(this,it)
            }
        }.start()


    }

    override fun initListener() {
//        btn_save_img.setOnClickListener {
//
//            XPermission.requestPermissions(this,100, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE),object :XPermission.OnPermissionListener{
//                override fun onPermissionDenied() {
//                    XToast.getInstance(this@MainActivity).error("无权限")
//                }
//
//                override fun onPermissionGranted() {
//                    initGenerateImg()
//                }
//            })
//        }
    }
    override fun initView() {

        adaptiveNotchScreenByView(iv_test)
    }





    override fun createPresenter() =
        MvpKtPresenter(object :
            MainView<List<Result>> {
            override fun showLoading(msg: String?) {

            }

            override fun getDataFail(msg: String?) {

                Log.i("MainActivity", "getDataFail msg=$msg")
            }

            override fun showErrorMessage() {

            }

            override fun hideLoading() {

            }

            override fun getDataSuccess(model: List<Result>?) {

                Log.i("MainActivity", "model=${model.toString()}")

                //kotlin 可以直接使用id作为变量使用
                tv_content.text = model.toString()
                model?.let {
                    Log.i("MainActivity", "     it.result[0].province=${it[0].province}")
                }

            }

        })
}