package com.example.kotlinmvp.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.view.PixelCopy
import android.view.View
import android.view.Window
import com.camming.mvp.utils.ScreenUtils
import com.camming.mvp.utils.XDensityUtils


/**
 * Create by Cabbage Camming 2021/3/15.
 */
object BitmapUtil {

    /**将两张图片合成一张 */
    fun mergeBitmap(first: Bitmap, second: Bitmap): Bitmap? {
        val w1 = first.width
        val h1 = first.height
        val w2 = second.width
        val h2 = second.height
        val bitmap = Bitmap.createBitmap(w1, h1 + h2, first.config)
        val canvas = Canvas(bitmap)

        canvas.drawBitmap(first, 0f, 0f, null)
        canvas.drawBitmap(second, w1.toFloat(), 0f, null)
        return bitmap
    }
    /**将多张图片并排合成一张 */
    fun mergeBitmaps(bits: List<Bitmap>): Bitmap? {
        if (bits.isEmpty()) return null
        //        if(bits.size()==1) return bits.get(0);
        var mergeBitmapWidth = 0
        var mergeBitmapHeight = bits[0].height
        for (bitmap in bits) {
            //计算总宽度
            mergeBitmapWidth += bitmap.width
            if (mergeBitmapHeight < bitmap.height) mergeBitmapHeight = bitmap.height
        }

        val mBitmap = Bitmap.createBitmap(mergeBitmapWidth, mergeBitmapHeight, bits[0].config)
        val canvas = Canvas(mBitmap)
        //白底
        canvas.drawARGB(255, 255, 255, 255)
        var currentWidth = 0f
        for (bit in bits) {
            canvas.drawBitmap(bit, currentWidth, 0f,null)
            currentWidth += bit.width
        }
        return mBitmap
    }

    fun createEmptyBitmap(context:Context):Bitmap{
        val mBitmap =  Bitmap.createBitmap(XDensityUtils.getScreenWidth(context)/2,
            XDensityUtils.getScreenHeight(context)/2, Bitmap.Config.ARGB_8888);
        val canvas = Canvas(mBitmap)
        canvas.drawARGB(255, 255, 255, 255)
        return mBitmap
    }

    /**
     * 先测量和布局，再生成Bitmap
     */
    fun getBitmap(mC:Context,view: View): Bitmap {
        // 测量
        //todo 为什么一定是EXACTLY和AT_MOST
        val widthSpec = View.MeasureSpec.makeMeasureSpec(
            ScreenUtils.dp2px(mC, 313f),
            View.MeasureSpec.EXACTLY
        )
        val heightSpec = View.MeasureSpec.makeMeasureSpec(
            ScreenUtils.dp2px(mC, 221f),
            View.MeasureSpec.EXACTLY

        )
//        View.MeasureSpec.AT_MOST  这里用这个不行 ？

        view.measure(widthSpec, heightSpec)
        // 布局
        val measuredWidth = view.measuredWidth
        val measuredHeight = view.measuredHeight


        view.layout(0, 0, measuredWidth, measuredHeight)
        // 绘制
        val width = view.width
        val height = view.height

        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }

    /**
     * 将一个未show的布局转换成bitmap
     * */
    fun createUnShowBitmap(mC: Context,v:View,width:Float,height:Float):Bitmap {
        v.measure(
            View.MeasureSpec.makeMeasureSpec(ScreenUtils.dp2px(mC, width), View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec( ScreenUtils.dp2px(mC, height), View.MeasureSpec.UNSPECIFIED))
        v.layout(0, 0, v.measuredWidth, v.measuredHeight)
        val bitmap = Bitmap.createBitmap(v.width, v.height,Bitmap.Config.ARGB_8888)
        v.draw(Canvas(bitmap))
        return bitmap
    }
    /**
     * 将一个已show的布局转换成bitmap
     * */
    fun createShowBitmap(mC: Context,window: Window,width:Float,height:Float,v:View,result:(bitmap:Bitmap)->Unit) {
        v.measure(
            View.MeasureSpec.makeMeasureSpec(ScreenUtils.dp2px(mC, width), View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec( ScreenUtils.dp2px(mC, height), View.MeasureSpec.UNSPECIFIED))
        v.layout(0, 0, v.measuredWidth, v.measuredHeight)

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val bitmap = Bitmap.createBitmap(v.width, v.height,Bitmap.Config.ARGB_8888)
            val location = IntArray(2)
            v.getLocationInWindow(location)
            PixelCopy.request(window, Rect(location[0],location[1],location[0]+v.width,location[1]+v.height),
            bitmap, PixelCopy.OnPixelCopyFinishedListener {
                copyResult ->
                    if(copyResult==PixelCopy.SUCCESS){
                        result.invoke(bitmap)
                    }
                }, Handler(Looper.getMainLooper())
                )
        }else{
            v.buildDrawingCache()
            var drawingCache = v.drawingCache
            result.invoke(drawingCache)
            v.destroyDrawingCache()
        }
    }

}