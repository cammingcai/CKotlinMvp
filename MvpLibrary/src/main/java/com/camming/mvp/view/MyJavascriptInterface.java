package com.camming.mvp.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.JavascriptInterface;

/**
 * Created by jingbin on 2016/11/17.
 * js通信接口
 */
public class MyJavascriptInterface {
    private Context context;
    private IMyJSInterface mIMyJSInterface;

    public MyJavascriptInterface(Context context, IMyJSInterface jsInterface) {
        this.context = context;
        this.mIMyJSInterface = jsInterface;
    }

    /**
     * 前端代码嵌入js：
     * imageClick 名应和js函数方法名一致
     *
     * @param src 图片的链接
     */
    @JavascriptInterface
    public void imageClick(String src) {
        Log.e("imageClick", "----点击了图片");
        Log.e("src", src);
    }

    /**
     * 前端代码嵌入js
     * 遍历<li>节点
     *
     * @param type    <li>节点下type属性的值
     * @param item_pk item_pk属性的值
     */
    @JavascriptInterface
    public void textClick(String type, String item_pk) {
        if (!TextUtils.isEmpty(type) && !TextUtils.isEmpty(item_pk)) {
            Log.e("textClick", "----点击了文字");
            Log.e("type", type);
            Log.e("item_pk", item_pk);
        }
    }

    /**
     * 网页使用的js，方法无参数
     */
    @JavascriptInterface
    public void jsFunction() {
        Log.e("startFunction", "----无参");
        if(mIMyJSInterface!=null){
            mIMyJSInterface.fromJsFunction("","");
        }
    }

    /**
     * 网页使用的js，方法有参数，且参数名为data
     *
     * @param data 网页js里的参数名
     */
    @JavascriptInterface
    public void jsFunction(String data) {
        Log.e("startFunction", "----有参" + data);
        if(mIMyJSInterface!=null){
            mIMyJSInterface.fromJsFunction("",data);
        }
    }
    @JavascriptInterface
    public void jsFunction(String fun, String data) {
        Log.e("startFunction", "----有参" +fun+ data);
        if(mIMyJSInterface!=null){
            mIMyJSInterface.fromJsFunction(fun,data);
        }

    }


    public interface IMyJSInterface{

        void fromJsFunction(String fun , String data);
    }

}