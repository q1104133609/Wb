package com.fansu.yimaomiao.utils;

import android.graphics.Bitmap;

import com.blankj.utilcode.utils.ImageUtils;
import com.blankj.utilcode.utils.LogUtils;
import com.fansu.yimaomiao.Constans;
import com.fansu.yimaomiao.base.Result;
import com.fansu.yimaomiao.http.WBService;
import com.fansu.yimaomiao.http.Wbm;
import com.fansu.yimaomiao.inter.OnQiuNiuListener;
import com.qiniu.android.common.Zone;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCancellationSignal;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.storage.UploadOptions;

import java.io.ByteArrayOutputStream;
import java.io.File;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by leo on 16/11/7.
 * 七牛上传
 */

public class QNUtils {
    public boolean isCancelled = false;
    private final int KB = 1024;                 //B

    private final int CHUNK_SIZE = 256 * KB;     //KB

    private final int THRESHOLD = 512 * KB;      //KB

    private final int CONNECT_TIMEOUT = 10;      //连接超时等待秒

    private final int RESPONSE_TIMEOUT = 60;     //响应请求等待秒
    private static QNUtils instance;

    private UploadManager uploadManager;

    private Configuration config;

    public static QNUtils getInstance() {
        if (instance == null)
            instance = new QNUtils();
        return instance;
    }


    private QNUtils() {
        Configuration.Builder builder = new Configuration.Builder();
        builder.chunkSize(CHUNK_SIZE);  //分片上传时，每片的大小。 默认 256K
        builder.putThreshhold(THRESHOLD);  // 启用分片上传阀值。默认 512K
        builder.connectTimeout(CONNECT_TIMEOUT);// 链接超时。默认 10秒
        builder.responseTimeout(RESPONSE_TIMEOUT); // 服务器响应超时。默认 60秒
        builder.zone(Zone.zone0); // 设置区域，指定不同区域的上传域名、备用域名、备用IP。默认 Zone.zone0
        config = builder.build();
        uploadManager = new UploadManager(config);
    }


    public void upLoadSingleFile(Bitmap bitmap, String key, OnQiuNiuListener qiuNiuListener) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 10, stream);
        byte[] byteArray = stream.toByteArray();
        WBService
                .getService()
                .create(Wbm.class)
                .getQiniuToken()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        qiuNiuListener.isFlld("上传失败请重新选择图片~");

                    }

                    @Override
                    public void onNext(Result result) {
                        upLoadFileProgress(byteArray, key+ Constans.PHOEO_TYPE, result.bean.toString(), qiuNiuListener);

                    }
                });
    }


    //====================七牛sdk====================================================

    public void upLoadFileProgress(byte[] data, String key, String token, OnQiuNiuListener qiuNiuListener) {
        UploadManager uploadManager = new UploadManager();
        /**
         * 完成
         */
        uploadManager.put(data, key, token,
                (key1, info, response) -> {
                    if (info.isOK())
                        qiuNiuListener.isSuccess(key1);
                    else
                        qiuNiuListener.isFlld(response.toString());

                },
                /**
                 * 进度
                 */
                new UploadOptions(null, null, false,
                        (key1, percent) -> {
                            LogUtils.d(percent);
                        }, new UpCancellationSignal() {
                    @Override
                    public boolean isCancelled() {
                        return isCancelled;
                    }
                }));
    }

    public void upLoadFileProgress(File file, String key, String token, OnQiuNiuListener qiuNiuListener) {
        UploadManager uploadManager = new UploadManager();
        /**
         * 完成
         */
        uploadManager.put(file, key, token,
                (key1, info, response) -> {
                    if (info.isOK())
                        qiuNiuListener.isSuccess(key1);
                    else
                        qiuNiuListener.isFlld(response.toString());
                },
                /**
                 * 进度
                 */
                new UploadOptions(null, null, false,
                        (key1, percent) -> {
                            LogUtils.d(percent);
                        }, new UpCancellationSignal() {
                    @Override
                    public boolean isCancelled() {
                        return isCancelled;
                    }
                }));
    }


    public void upLoadFileProgress(String fliepath, String key, String token, OnQiuNiuListener qiuNiuListener) {
        UploadManager uploadManager = new UploadManager();
        /**
         * 完成
         */
        uploadManager.put(fliepath, key, token,
                (key1, info, response) -> {
                    if (info.isOK())
                        qiuNiuListener.isSuccess(key1);
                    else
                        qiuNiuListener.isFlld(response.toString());
                },
                /**
                 * 进度
                 */
                new UploadOptions(null, null, false,
                        (key1, percent) -> {
                            LogUtils.d(percent);

                        }, new UpCancellationSignal() {
                    @Override
                    public boolean isCancelled() {
                        return isCancelled;
                    }
                }));
    }


    public void cancell() {
        isCancelled = true;
    }


}
