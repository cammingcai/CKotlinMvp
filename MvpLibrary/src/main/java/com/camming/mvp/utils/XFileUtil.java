package com.camming.mvp.utils;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;


import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017/7/12 0012.
 */

public class XFileUtil {
    public static final String DOWNLOAD_DIR = "download";
    public static final String CACHE_DIR = "cache";
    public static final String ICON_DIR = "icon";


    /**
     * 读取文件内容，返回字符串
     * */

    public static String readFileToString(String filePath) throws IOException {
        File file  = new File(filePath);

        if(!file.exists()){
            throw new FileNotFoundException(filePath);
        }
        if(file.length()>1024*1024*1024){
            throw new IOException("this file is too large!");
        }
        StringBuilder sb = new StringBuilder((int)file.length());
        //创建字节输入流
        FileInputStream inputStream = new FileInputStream(filePath);
        //创建一个长度为1024的buffer
        byte[] bytes = new byte[1024];
        //定义一个用于读取实际的字节数
        int hasRed = 0;

        while ((hasRed  = inputStream.read(bytes))!=-1){
            sb.append(new String(bytes,0,hasRed));
        }
        inputStream.close();
        return sb.toString();

    }

    /**
     * 获取包内相册路径
     *
     * @param context
     * @return
     */
    public static String getPackageDCIMPath(Context context) {
        if (context == null) {
            return "";
        }
        String dcimPath = "";
        File filePath = context.getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_DCIM);
        if (filePath == null) {
            dcimPath = getStorageDCIMPath();
        } else {
            dcimPath = filePath.getAbsolutePath();
        }
        return dcimPath;
    }
    /**
     * 获取外部相册路径
     *
     * @return
     */
    public static String getStorageDCIMPath() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath();
    }

    /**
     * 获取外部音频路径
     *
     * @return
     */
    public static String getStorageAudioPath() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).getAbsolutePath();
    }

    /**
     * 获取外部视频路径
     *
     * @return
     */
    public static String getStorageMoviePath() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).getAbsolutePath();
    }

    /**
     * 获取外部文档目录
     *
     * @return
     */
    public static String getStorageDocumentPath() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath();
        } else {
            return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
        }
    }

    /**
     * 获取外部下载目录
     *
     * @return
     */
    public static String getStorageDownloadPath() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
    }

    /**
     * 获取外部包路径
     *
     * @param context
     * @return
     */
    public static String getExternalPackagePath(Context context) {
        if (context != null) {
            File extPath = Environment.getExternalStorageDirectory();
            String pckPath = extPath.getAbsolutePath() + "/Android/data/" + context.getPackageName();
            return pckPath;
        }
        return "";
    }

    /**
     * 根据文件路径读取byte[] 数组
     */
    public static byte[] readFileByBytes(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException(filePath);
        } else {
            ByteArrayOutputStream bos = new ByteArrayOutputStream((int) file.length());
            BufferedInputStream in = null;

            try {
                in = new BufferedInputStream(new FileInputStream(file));
                short bufSize = 1024;
                byte[] buffer = new byte[bufSize];
                int len1;
                while (-1 != (len1 = in.read(buffer, 0, bufSize))) {
                    bos.write(buffer, 0, len1);
                }

                byte[] var7 = bos.toByteArray();
                return var7;
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                } catch (IOException var14) {
                    var14.printStackTrace();
                }

                bos.close();
            }
        }
    }

    /**
     * 加载本地图片
     * @param path
     * @return
     */
    public static Bitmap getLoacalBitmap(String path) {
        if (new File(path).exists()) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(path);
                return BitmapFactory.decodeStream(fis); // /把流转化为Bitmap图片
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            } finally {
                if(fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    fis = null;
                }
            }
        } else {
            return null;
        }
    }


    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            // 有存储的SDCard
            return true;
        } else {
            return false;
        }
    }
    /**
     * 获取下载目录
     */
    public static String getDownloadDir(Context context) {
        return getDir(DOWNLOAD_DIR,context);
    }

    /**
     * 获取缓存目录
     */
    public static String getCacheDir(Context context) {
        return getDir(CACHE_DIR,context);
    }

    /**
     * 获取icon目录
     */
    public static String getIconDir(Context context) {
        return getDir(ICON_DIR,context);
    }

    /**
     * 获取应用目录，当SD卡存在时，获取SD卡上的目录，当SD卡不存在时，获取应用的cache目录
     */
    public static String getDir(String name, Context context) {
        StringBuilder sb = new StringBuilder();
        if (hasSdcard()) {
            sb.append(Environment.getExternalStorageDirectory());
        } else {
            sb.append(context.getCacheDir());
        }
        sb.append(File.separator);
        sb.append(name);
        sb.append(File.separator);
        String path = sb.toString();
        Log.i("FileUtil","file path="+path);
        if (makeDir(path)) {
            return path;
        } else {
            return null;
        }
    }

    /**
     * 获取目录文件大小
     *
     * @param dir
     * @return
     */
    public static long getDirSize(File dir) {
        if (dir == null) {
            return 0;
        }
        if (!dir.isDirectory()) {
            return 0;
        }
        long dirSize = 0;
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                dirSize += file.length();
            } else if (file.isDirectory()) {
                dirSize += file.length();
                dirSize += getDirSize(file); // 递归调用继续统计
            }
        }
        return dirSize;
    }

    /**
     * 转换文件大小
     *
     * @param fileS
     * @return B/KB/MB/GB
     */
    public static String formatFileSize(long fileS) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }

    public static File saveFileFromBitmap(String filePath, String fileName, Bitmap bitmap){
        boolean bool = makeDir(filePath);
        if(bool){

            ByteArrayOutputStream baos =new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);

            byte[] bytes = baos.toByteArray();
            try {
                File file = new File(filePath, fileName);
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(bytes);
                fos.close();
                return file;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }else
            return null;

    }

    public static void refershSystemAlbum(Context context, File file){
        if(!file.exists()){

            return;
        }
        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), file.getName(), null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
//        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse(file.getParent())));
//        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file.getName())));

    }
    public static File saveFile(String filePath, String fileName, byte[] bt){
        if(!makeDir(filePath)){
            return null;
        }
        File file = new File(filePath,fileName);

        if(file!=null&&file.exists())
            file.delete();
        try {
            //File file = new File(filePath, fileName);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bt);
            fos.close();
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**创建目录*/
    public static String createTmpDir(Context context, String path){
        String tmpDir = path;
        if(!makeDir(path)){
            tmpDir = context.getExternalFilesDir(path).getAbsolutePath();
        }
        return tmpDir;
    }

    /**
     * 判断目录是否存在
     *
     * */
    public static boolean makeDir(String dirPath) {
        File file = new File(dirPath);
        if (!file.exists()) {
            return file.mkdirs();
        } else {
            return true;
        }
    }
    public static boolean deleteFile(String dirPath){
        File file = new File(dirPath);
        if(file.exists()){

            if(file.isDirectory()){
                for (File mFile:file.listFiles()) {
                    mFile.delete();
                }
            }
            return true;
        }
        return false;
    }
    /**根据路径判断文件是否存在*/
    public static boolean fileExists(String dirPath, String fileName) {
        File file = new File(dirPath,fileName);
        return file.exists();
    }
    /**
     * 写入文件到sd卡
     * 如下载apk
     *
     * */
    public static boolean writeToFile(ResponseBody body, File file) {
        try {
            InputStream inputStream = null;
            OutputStream outputStream = null;

            long currentLength = 0;
            long totalLength = body.contentLength();
            try {
//                if(listener!=null)
//                    listener.start();
                byte[] fileReader = new byte[2048];
                inputStream =body.byteStream();
                outputStream = new FileOutputStream(file);

                int len;
                while ((len = inputStream.read(fileReader))!=-1) {
                    outputStream.write(fileReader, 0, len);
                    currentLength += len;
                   // Log.d("FileUtils","当前长度: " + currentLength);
                    int progress = (int) (100 * currentLength / totalLength);
                    //listener.update(progress);
                 //   Log.d("FileUtils","当前进度: " + progress);
                }
                outputStream.flush();
//                if(listener!=null)
//                    listener.success();
                return true;
            } catch (IOException e) {
//                if(listener!=null)
//                    listener.error();
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }if (outputStream != null) {
                    outputStream.close();}
            }
        } catch (IOException e) {
            return false;
        }
    }


    public static String getAssets(Context context, String path) {
        String params = "";

        AssetManager assetManager = context.getResources().getAssets();
        try {
            //available读写操作前先得知数据流里有多少个字节可以读取 一般用于本地数据流读取
            InputStream ins = assetManager.open(path);
            byte[] buffer = new byte[ins.available()];

            ins.read(buffer);
            ins.close();

            params = new String(buffer);

           // JSONObject paramsJson = new JSONObject(params);

           // params = paramsJson.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return params;
    }    public static Bitmap loadBitmapFromView(View v){
        v.setDrawingCacheEnabled(true);
        v.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
//        v.setDrawingCacheBackgroundColor(Color.WHITE);

//        int w = v.getWidth();
//        int h = v.getHeight();
        int w = XDensityUtils.getMeasuredWidth(v);
        int h = XDensityUtils.getMeasuredHeight(v);
        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmp);
        /** 如果不设置canvas画布为白色，则生成透明 */
//        c.drawColor(Color.WHITE);

        v.layout(0, 0, w, h);
        v.draw(c);

        return bmp;

    }

    /**
     * 复制文件
     *
     * @param fromFile         原文件
     * @param toFile           目标文件
     * @param rewrite          是否覆盖
     * @param isDeleteFromFile 复制完成是否删除原文件
     * @return 是否复制成功
     */
    public static boolean copyFile(File fromFile, File toFile, Boolean rewrite,
                                   Boolean isDeleteFromFile) {
        if (!fromFile.exists() || !fromFile.isFile() || !fromFile.canRead()) {
            return false;
        }
        if (toFile.exists() && rewrite) {
            toFile.delete();
        }
        FileInputStream from = null;
        FileOutputStream to = null;
        boolean isSuccess = true;
        try {
            from = new FileInputStream(fromFile);
            to = new FileOutputStream(toFile);
            byte[] content = new byte[1024];
            int reads = 0;
            while ((reads = from.read(content)) > 0) {
                to.write(content, 0, reads);
            }
            to.flush();
        } catch (Exception ex) {
            isSuccess = false;
        } finally {
            if (from != null) {
                try {
                    from.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (to != null) {
                try {
                    to.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (isDeleteFromFile && isSuccess) {
                fromFile.delete();
            }
        }
        return isSuccess;
    }

}
