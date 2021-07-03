package com.example.kotlinmvp

import com.camming.mvp.model.Area
import com.example.kotlinmvp.MvpExpands.showToast
import com.example.kotlinmvp.mvp.MvpKtPresenter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_litepal.*
import org.litepal.LitePal.delete
import org.litepal.LitePal.findAll


class TestLitepalActivity : XKotlinBaseActivit<MvpKtPresenter>() {

    override val activityName: String get() = "TestLitepalActivity"

    override fun initLayoutId()  = R.layout.activity_litepal



    override fun initData() {


    }






    override fun initListener() {
        btn_add.setOnClickListener { v ->  //添加数据
            val area= Area()
            area.name = "福田"
            area.save() //保存
            if (area.save()) {
                showToast("添加数据成功")
            } else {
                showToast("添加数据失败")
            }
        }

        btn_update.setOnClickListener { v ->  //修改数据
            val area = Area()
            area.name = "宝安"
            area.update(1) //修改指定ID的数据

            showToast("修改数据成功")
        }

        btn_query.setOnClickListener { v ->  //查询数据
            //查询指定ID的数据
            //Area area = LitePal.find(Area.class,1);

            //查询所有数据
            val area = findAll(Area::class.java)
            tv_result.text = Gson().toJson(area)
        }

        btn_delete.setOnClickListener { v ->
            delete(Area::class.java, 1) //删除指定id

            //LitePal.deleteAll(Area.class, "name = ?" , "福田");//删除所有name为福田的
            showToast("删除数据成功")
        }


    }
    override fun initView() {

    }





    override fun createPresenter() = MvpKtPresenter(null)

}