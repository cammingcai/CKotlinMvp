package com.camming.mvp.iml;

/**
 *
 *下载文件接口
 */
public interface DownloadListener {
    /**
     * 开始下载
     */
     void start();

    /**
     * 更新下载进度
     * @param progress
     */
     void update(int progress);

    /**
     * 下载成功
     */
     void success();

    /**
     * 下载错误
     */
     void error();
}
