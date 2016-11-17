package com.fansu.yimaomiao;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.easemob.easeui.controller.EaseUI;
import com.fansu.yimaomiao.utils.QNUtils;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * Created by leo on 16/10/22.
 */

public class App extends Application {

    private static Context appContext;

    public static String lat = "";
    public static String lon = "";
    public static  String city = "";
    public static QNUtils qnUtils;

    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();


    public static Context getAppContext() {
        return appContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //七牛处理
        qnUtils = QNUtils.getInstance();
        //multidex 初始化
        MultiDex.install(this);
        //百度地图初始化
        SDKInitializer.initialize(getApplicationContext());
        //环信初始化
        EaseUI.getInstance().init(this);
        //二维码初始化
        ZXingLibrary.initDisplayOpinion(this);
        initLocation();
        appContext = getApplicationContext();
    }

    /**
     * 初始化定位
     */
    private void initLocation() {
        mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);    //注册监听函数
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setScanSpan(0);
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(false);//可选，默认false,设置是否使用gps
        option.setLocationNotify(false);//可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        mLocationClient.setLocOption(option);
        mLocationClient.start();
    }


    /**
     * 定位监听
     */
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            //Receive Location
            lat = String.valueOf(location.getLatitude());
            lon = String.valueOf(location.getLongitude());
            city = location.getCity();
        }
    }
}
