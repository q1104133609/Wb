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
import com.fansu.yimaomiao.entity.LoginBean;
import com.fansu.yimaomiao.utils.GlideImageLoader;
import com.fansu.yimaomiao.utils.QNUtils;
import com.fansu.yimaomiao.utils.SharedPreferencesUtils;
import com.fansu.yimaomiao.utils.Utils;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.ThemeConfig;

/**
 * Created by leo on 16/10/22.
 */

public class App extends Application {

    private static Context appContext;

    public static String lat = "";
    public static String lon = "";
    public static String city = "";
    public static QNUtils qnUtils;
    public static LoginBean mLoginBean;
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();


    public static Context getAppContext() {
        return appContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (!SharedPreferencesUtils.getString(this, Constans.USER_NAME, "").equals("")) {
            mLoginBean = SharedPreferencesUtils.getObject(this, Constans.USER_INFO, LoginBean.class);
        }
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
        Utils.initLocation(this, mLocationClient, myListener, 0);
        appContext = getApplicationContext();
        initPhoto();
    }

    /**
     * 初始化图片选择器
     */
    public void initPhoto(){
        ThemeConfig theme = new ThemeConfig.Builder()
        .build();
        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)
                .setEnableCrop(true)
                .setEnableRotate(true)
                .setCropSquare(true)
                .setEnableEdit(true)
                .setEnablePreview(true)
        .build();
        ImageLoader imageloader = new GlideImageLoader();
        CoreConfig coreConfig = new CoreConfig.Builder(this, imageloader, theme.DARK)
                .setFunctionConfig(functionConfig)
        .build();
        GalleryFinal.init(coreConfig);
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
