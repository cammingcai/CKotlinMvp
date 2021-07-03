package com.example.kotlinmvp

import android.util.Log
import com.camming.mvp.mvp.MvpViewCallback
import com.camming.mvp.utils.XRegexUtils
import com.example.kotlinmvp.MvpExpands.hideLoading
import com.example.kotlinmvp.MvpExpands.showLoading

import com.example.kotlinmvp.MvpExpands.showToast
import com.example.kotlinmvp.event.MessageEvent
import com.example.kotlinmvp.model.PhoneData
import com.example.kotlinmvp.model.news.NewsBean
import com.example.kotlinmvp.mvp.MvpKtPresenter
import com.example.kotlinmvp.mvp.MyRetrofitCallback
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class TestActivity : XKotlinBaseActivit<MvpKtPresenter>() {

    override val activityName: String get() = "TestActivity"
//    data class CammingData(var id:String,var name:String,var url:String)
    override fun initLayoutId()  = R.layout.activity_main


    private val testDatas = mutableListOf<MessageEvent>()
//    private val mTestAdapter by lazy { TestAdapter(this,arv_view,testDatas) }

    override fun initData() {
        Log.i("MainActivity","initData=")
        et_phone.setText("13560048370")
        btn_query.setOnClickListener {
            var phone = et_phone.text.toString()
//            if(XRegexUtils.checkPhone(phone)){

                    showLoading("查询中")
//                mvpPresenter?.queryPhoneArea(phone,object : MyRetrofitCallback<PhoneData>() {
//                    override fun onSuccess(model: PhoneData) {
//                        tv_content.text = "查询成功${model.province}${model.city}--${model.company}--城市代码：${model.areacode}"
//                        hideLoading()
//                    }
//
//                    override fun onFailure(msg: String) {
//                        tv_content.text ="查询失败=${msg}"
//                        hideLoading()
//                    }
//                })


//            }else{
//                showToast("手机号码格式有误")
//            }

        }


//        startActivity(Intent(this,TestLitepalActivity::class.java))


        var be = supportFragmentManager.beginTransaction()
        be.replace(R.id.fl_content,EventBusFragment()).commit()

//        var screenWidth = ScreenUtils.getScreenWidth(this)
//        btn_hide.setOnClickListener {
//
//            Log.i("MainActivity","rl_bottom.height=${rl_bottom.height}")
//                Log.i("MainActivity","rl_bottom.layoutParams.height=${rl_bottom.layoutParams.height}")
//            var  m = ObjectAnimator.ofFloat(rl_bottom,"translationX", 0f,-screenWidth.toFloat())
//            m.duration = 200
//            m.start()
//        }
//        btn_show.setOnClickListener {
//            var  m = ObjectAnimator.ofFloat(rl_bottom,"translationX", -screenWidth.toFloat(),0f)
//            m.duration = 200
//            m.start()
//        }
//
//        var keyData = "glRA0CzLA2NSnzEscUENHN5MW8Zc+ImJemLpx/fF/8m7bmI1GuSNX0GD82fgQT/gzt1GQeSptYbMcNwmOS/HSIGp/EQOPV9+WIoZ4n3PuBsmPaVs+MyFe7m6txbxqz0OnoC+a7bFlnQSIhJOH3jhbe70LG/qvDqNQNODpf0Y9ikq0jjRpasmGAbsbbhRy42saMuxHg4sZyuxWiu99S4Dz08rvBcik4A0r1++TQyn0a0txIfrSsMaNZdrfKjqB/ny4HBFhCPb1W4bX8HyPABr7k0GVLcoUkQANvRHZXKJy485RJGgiJ4QZ1bl81IndxwcgA82lphxJ6hQRLEvFyJjgQ=="
//
//        val rsa = RSAUtils()
//        rsa.keyPairGenerator()
//
//        var result =  rsa.decryptByPublicKey2(keyData)
//        Log.i("MainActivity","initData result=${result}")
//        Log.e("MainActivity","initData result=${result}")




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
//        fixedRateTimer("",false,0,3000){
//            show = !show
//            runOnUiThread {
//                if(show){
//                    mObjectAnimatorShow.start()
//                }else{
////                    mObjectAnimatorHide.start()
//                }
//            }
//
//        }



    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMessage(event:MessageEvent){

        Log.i(activityName,"${activityName}${event.message}")
    }


//    private var show = false
//
//    private lateinit var mObjectAnimatorShow:ObjectAnimator
//    private lateinit var mObjectAnimatorHide:ObjectAnimator
    private fun showAnimator(){
//        mObjectAnimatorShow = ObjectAnimator.ofInt(iv_test,"backgroundColor",0xfff10f0f.toInt(),0xff0f94f1.toInt(),0xffeaf804.toInt(),0xfff92a0f.toInt())
////        mObjectAnimatorShow = ObjectAnimator.ofArgb(iv_test,"backgroundColor",0xfff10f0f,0xff0f94f1,0xffeaf804,0xfff92a0f)
//        mObjectAnimatorShow.duration = 3000
//        mObjectAnimatorShow.setEvaluator(ArgbEvaluator())
    }
    private fun hideAnimator(){
//        mObjectAnimatorHide = ObjectAnimator.ofFloat(iv_test,"translationY", 0f,-iv_test.layoutParams.height.toFloat())
//        mObjectAnimatorHide.duration = 3000
    }
    //ValueAnimator 是对值得平滑过渡  ObjectAnimator是对对象属性的平滑过渡
//    private lateinit var mValueAnimatorShow:ValueAnimator
//    private lateinit var mValueAnimatorHide:ValueAnimator
//
//    private fun showAnimator(){
//
//        mValueAnimatorShow = ValueAnimator.ofFloat(-iv_test.layoutParams.height.toFloat(), 0f)
//        mValueAnimatorShow.duration = 3000
//        mValueAnimatorShow.setTarget(iv_test)
//        mValueAnimatorShow.addUpdateListener {
//            animation ->
//            iv_test.translationY = animation.animatedValue as Float
////            iv_test.alpha = animation.animatedValue as Float
//
//        }
//    }
//    private fun hideAnimator(){
//        mValueAnimatorHide = ValueAnimator.ofFloat(0f,-iv_test.layoutParams.height.toFloat())
//        mValueAnimatorHide.duration = 3000
//        mValueAnimatorHide.setTarget(iv_test)
//        mValueAnimatorHide.addUpdateListener {
//            animation ->
//            iv_test.translationY = animation.animatedValue as Float
////            iv_test.alpha = animation.animatedValue as Float
//
//        }
//    }


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

//        adaptiveNotchScreenByView(iv_test)
    }





    override fun createPresenter() =
        MvpKtPresenter(object :
            MvpViewCallback<PhoneData> {

            override fun getDataFail(msg: String?) {

                Log.i("MainActivity", "getDataFail msg=$msg")
            }

            override fun showErrorMessage() {

            }

            override fun getDataSuccess(model: PhoneData) {

                tv_content.text = "${model.province}${model.city}--${model.company}--城市代码：${model.areacode}"

            }

        })
}