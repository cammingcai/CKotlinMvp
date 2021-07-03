package com.camming.mvp.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;

import android.view.View;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;


/**
 * 此类主要是用来放一些系统过时方法的处理
 */
public class XOutdatedUtils {

    private XOutdatedUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * setBackgroundDrawable过时方法处理
     *
     * @param view
     * @param drawable
     */
    public static void setBackground(@NonNull View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            view.setBackground(drawable);
        else
            view.setBackgroundDrawable(drawable);
    }

    /**
     * getDrawable过时方法处理
     *
     * @param id
     * @return
     */
    public static Drawable getDrawable(Context mC,@DrawableRes int id) {
        return ContextCompat.getDrawable(mC, id);
    }

//    /**
//     * getDrawable过时方法处理
//     *
//     * @param id 资源id
//     * @param theme 指定主题
//     * @return
//     */
//    public static Drawable getDrawable(@DrawableRes int id,
//                                       @Nullable Resources.Theme theme) {
//        return ResourcesCompat.getDrawable(XFrame.getResources(), id, theme);
//    }

    /**
     * getColor过时方法处理
     *
     * @param id
     * @return
     */
    public static int getColor(Context mC,@ColorRes int id) {
        return ContextCompat.getColor(mC, id);
    }

/*   /**
     * getColor过时方法处理
     *
     * @param id 资源id
     * @param theme 指定主题
     * @return
     *//*
    public static int getColor(@ColorRes int id, @Nullable Resources.Theme theme) {
        return ResourcesCompat.getColor(XFrame.getResources(), id, theme);
    }*/



}
