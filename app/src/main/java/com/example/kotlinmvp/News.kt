package com.example.kotlinmvp

/**
 * Create by Cabbage on 2021/7/3.
 *
 */
enum class News(val newName:String,val newType:String) {

    NEWS_TOP("推荐","top"),
    NEWS_GUONEI("国内","guonei"),
    NEWS_GUOJI("国际","guoji"),
    NEWS_YULE("娱乐","yule"),
    NEWS_TIYU("体育","tiyu"),
    NEWS_JUNSHI("军事","junshi"),
    NEWS_KEJI("科技","keji"),
    NEWS_CAIJING("财经","caijing"),
    NEWS_SHISHANG("时尚","shishang"),
    NEWS_YOUXI("游戏","youxi"),
    NEWS_QICHE("汽车","qiche"),
    NEWS_JIANKANG("健康","jiankang")
}