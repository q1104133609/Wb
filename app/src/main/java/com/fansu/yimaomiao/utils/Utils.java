package com.fansu.yimaomiao.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.fansu.yimaomiao.Constans;
import com.fansu.yimaomiao.R;
import com.fansu.yimaomiao.view.activity.login.LoginActivity;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.uuzuche.lib_zxing.camera.BitmapLuminanceSource;
import com.uuzuche.lib_zxing.decoding.DecodeFormatManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

/**
 * Created by leo on 16/11/5.
 */

public class Utils {

    /**
     * 获取时间差
     *
     * @param time 时间戳
     * @return 毫秒数
     */
    public static long getChaTime(long time) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1 = formatter.format(currentTime);
        String date2 = formatData("yyyy-MM-dd HH:mm:ss", time);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date d1 = df.parse(date1);
            Date d2 = df.parse(date2);
            long diff = d2.getTime() - d1.getTime();
            return diff;
        } catch (Exception e) {
        }


        return 0;
    }


    public static String formatData(String dataFormat, long timeStamp) {
        if (timeStamp == 0) {
            return "";
        }
        timeStamp = timeStamp * 1000;
        String result = "";
        SimpleDateFormat format = new SimpleDateFormat(dataFormat);
        result = format.format(new Date(timeStamp));
        return result;
    }

    /**
     * 判断是否登录跳转
     *
     * @param context
     * @return
     */
    public static Boolean checkLogin(Context context) {
        if (SharedPreferencesUtils.getString(context, Constans.USER_NAME, "").equals("")) {
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
            return false;
        } else {
            return true;
        }
    }


    /**
     * 判断是否登录不跳转
     *
     * @param context
     * @return
     */
    public static Boolean checkLoginNo(Context context) {
        if (SharedPreferencesUtils.getString(context, Constans.USER_NAME, "").equals("")) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 二维码相册
     */
    public static void erweimaohoto(Activity context) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        context.startActivityForResult(intent, Constans.ERWEIMA_IMAGE_CODE);
    }

    /**
     * 扫描二维码
     */
    public static void erweima(Activity context) {
        Intent intent = new Intent(context, CaptureActivity.class);
        context.startActivityForResult(intent, Constans.ERWEIMA_CODE);
    }

    /**
     * 生成二维码带LOGo
     */
    public static Bitmap geterweimaLogo(String s, Context context) {
        return CodeUtils.createImage(s, 400, 400, BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher));
    }

    /**
     * 生成二维码
     */
    public static Bitmap geterweima(String s) {
        return CodeUtils.createImage(s, 400, 400, null);
    }


    /**
     * 解析二维码
     */
    public static String erweimaJiexi(Bitmap mBitmap) {
        MultiFormatReader multiFormatReader = new MultiFormatReader();

        // 解码的参数
        Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>(2);
        // 可以解析的编码类型
        Vector<BarcodeFormat> decodeFormats = new Vector<BarcodeFormat>();
        if (decodeFormats == null || decodeFormats.isEmpty()) {
            decodeFormats = new Vector<BarcodeFormat>();

            // 这里设置可扫描的类型，我这里选择了都支持
            decodeFormats.addAll(DecodeFormatManager.ONE_D_FORMATS);
            decodeFormats.addAll(DecodeFormatManager.QR_CODE_FORMATS);
            decodeFormats.addAll(DecodeFormatManager.DATA_MATRIX_FORMATS);
        }
        hints.put(DecodeHintType.POSSIBLE_FORMATS, decodeFormats);
        // 设置继续的字符编码格式为UTF8
        // hints.put(DecodeHintType.CHARACTER_SET, "UTF8");
        // 设置解析配置参数
        multiFormatReader.setHints(hints);
        // 开始对图像资源解码
        Result rawResult = null;
        try {
            rawResult = multiFormatReader.decodeWithState(new BinaryBitmap(new HybridBinarizer(new BitmapLuminanceSource(mBitmap))));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (rawResult != null) {
            return rawResult.getText();
        } else {
            return "解析失败~";
        }
    }


    /**
     * 配置百度地图
     *
     * @param context
     * @param locationClient
     * @param bdLocationListener 监听
     * @param time               间隔时间
     */
    public static void initLocation(Context context, LocationClient locationClient, BDLocationListener bdLocationListener, int time) {
        locationClient = new LocationClient(context);     //声明LocationClient类
        locationClient.registerLocationListener(bdLocationListener);    //注册监听函数
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setScanSpan(time);
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(false);//可选，默认false,设置是否使用gps
        option.setLocationNotify(false);//可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        locationClient.setLocOption(option);
        locationClient.start();
    }


}
