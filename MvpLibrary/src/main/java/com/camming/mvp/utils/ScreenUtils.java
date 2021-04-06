package com.camming.mvp.utils;


import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScreenUtils {
    private static Map<Float, Integer> sDP2PXMap = new HashMap();
    private static Map<Float, Integer> sPX2DPMap = new HashMap();

    public ScreenUtils() {
    }

    public static final boolean isScreenLocked(Context context) {
        if(context == null) {
            return false;
        } else {
            KeyguardManager mKeyguardManager = (KeyguardManager)context.getSystemService("keyguard");
            return mKeyguardManager.inKeyguardRestrictedInputMode();
        }
    }

    public static String getDpi(Context context) {
        int width = context.getResources().getDisplayMetrics().widthPixels;
        int height = context.getResources().getDisplayMetrics().heightPixels;
        String dpi = "320*480";
        if(width > 960 && height > 1280) {
            dpi = "960*1280";
        } else if(width > 640 && height > 960) {
            dpi = "640*960";
        }

        return dpi;
    }

    public static String getScreenSize(Context context) {
        int width = context.getResources().getDisplayMetrics().widthPixels;
        int height = context.getResources().getDisplayMetrics().heightPixels;
        return width + "*" + height;
    }

    public static int getScreenWidth(Context context) {
        return context == null?0:context.getResources().getDisplayMetrics().widthPixels;
    }

//    public static int getScreenHeight(Context context) {
//        return context == null?0:context.getResources().getDisplayMetrics().heightPixels;
//    }

    /**
     * @return 获取屏幕的高 单位：px
     */
    public static int getScreenHeight(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        if (windowManager != null) {
//            windowManager.getDefaultDisplay().getMetrics(dm);
            windowManager.getDefaultDisplay().getRealMetrics(dm);
            return dm.heightPixels;
        }
        return 0;

    }

    public static int getWindowsWidth(Context context) {
        return context == null?0:context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getWindowsHeight(Context context) {
        return context == null?0:context.getResources().getDisplayMetrics().heightPixels - getStausBarHeight(context);
    }

    public static int getStausBarHeight(Context context) {
        if(VERSION.SDK_INT >= 21) {
            return dp2px(context, 25.0F);
        } else {
            try {
                Class c = Class.forName("com.android.internal.R$dimen");
                Object obj = c.newInstance();
                Field field = c.getField("status_bar_height");
                int height = Integer.parseInt(field.get(obj).toString());
                if(height < 90) {
                    return dp2px(context, (float)height);
                }
            } catch (Exception var5) {
                ;
            }

            return dp2px(context, 25.0F);
        }
    }

    public static int px2dp(Context context, float pxValue) {
        if(sPX2DPMap.containsKey(Float.valueOf(pxValue))) {
            return ((Integer)sPX2DPMap.get(Float.valueOf(pxValue))).intValue();
        } else {
            float scale = context.getResources().getDisplayMetrics().density;
            int value = (int)(pxValue / scale + 0.5F);
            sPX2DPMap.put(Float.valueOf(pxValue), Integer.valueOf(value));
            return value;
        }
    }

    public static int dp2px(Context context, float dipValue) {
        if(sDP2PXMap.containsKey(Float.valueOf(dipValue))) {
            return ((Integer)sDP2PXMap.get(Float.valueOf(dipValue))).intValue();
        } else {
            float scale = context.getResources().getDisplayMetrics().density;
            int value = (int)(dipValue * scale + 0.5F);
            sDP2PXMap.put(Float.valueOf(dipValue), Integer.valueOf(value));
            return value;
        }
    }

    public static int px2sp(Context context, float pxValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int)(pxValue / fontScale + 0.5F);
    }

    public static int sp2px(Context context, float spValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int)(spValue * fontScale + 0.5F);
    }

    public static long formatsSize(String size) {
        try {
            Pattern p = Pattern.compile("(KB|Kb|kB|kb|MB|Mb|mB|mb|GB|Gb|gB|gb|b|B)$");
            Matcher m = p.matcher(size);
            String value = null;
            String unit = null;
            if(m.find()) {
                unit = m.group(1);
                if(unit == null) {
                    throw new NumberFormatException("");
                }

                value = size.replace(m.group(1), "").trim();
                if(TextUtils.isEmpty(value)) {
                    throw new NumberFormatException("");
                }
            }

            float v = Float.valueOf(value).floatValue();
            if(unit.equalsIgnoreCase("GB")) {
                return (long)(v * 1024.0F * 1024.0F * 1024.0F);
            } else if(unit.equalsIgnoreCase("MB")) {
                return (long)(v * 1024.0F * 1024.0F);
            } else if(unit.equalsIgnoreCase("KB")) {
                return (long)(v * 1024.0F);
            } else if(unit.equalsIgnoreCase("B")) {
                return (long)v;
            } else {
                throw new NumberFormatException("");
            }
        } catch (NumberFormatException var6) {
            return 0L;
        }
    }

    public static void cancelFullScreen(Activity activity) {
        if(activity != null) {
            activity.getWindow().clearFlags(1024);
        }
    }
}
