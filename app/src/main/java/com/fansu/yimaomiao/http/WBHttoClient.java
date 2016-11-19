package com.fansu.yimaomiao.http;

import android.content.SharedPreferences;
import android.os.Environment;

import com.blankj.utilcode.utils.LogUtils;
import com.blankj.utilcode.utils.NetworkUtils;
import com.fansu.yimaomiao.App;
import com.fansu.yimaomiao.Constans;
import com.fansu.yimaomiao.base.Result;
import com.fansu.yimaomiao.data.entity.LoginBean;
import com.fansu.yimaomiao.data.entity.MovieEntity;
import com.fansu.yimaomiao.utils.SharedPreferencesUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by leo on 16/10/22.
 */

public class WBHttoClient {

    public static String mToken;
    public static OkHttpClient getHttpClient() {


        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (NetworkUtils.isAvailable(App.getAppContext())) {
                    Response response = chain.proceed(request);
                    int maxAge = 60 * 60 * 24;
                    return response.newBuilder()
                            .removeHeader("Pragma")
                            .removeHeader("Cache-Control")
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .build();
                } else {
                    request = request.newBuilder()
                            .cacheControl(FORCE_CACHE1)
                            .build();
                    Response response = chain.proceed(request);
                    return response.newBuilder()
                            .build();
                }
            }
        };


        File cacheFile = new File(Environment.getExternalStorageDirectory(), Constans.CACHE_PAHT);
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 10);//缓存文件为10MB

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .cache(cache)
                .retryOnConnectionFailure(true)
                .addInterceptor(new TokenInterceptor())
                .connectTimeout(15, TimeUnit.SECONDS)
                .addNetworkInterceptor(cacheInterceptor)
                .build();
        return client;
    }


    public static class TokenInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            request.newBuilder()
                    .addHeader("token", SharedPreferencesUtils.getString(App.getAppContext(), Constans.TOKEN, ""))
                    .addHeader("deviceType", "ANDROID")
                    .addHeader("deviceModel", android.os.Build.MODEL)
                    .addHeader("type", "APP")
                    .build();
            Response response = chain.proceed(request);
            LogUtils.d("response.code=" + response.code());

            if (isTokenExpired(response)) {
                String newTolen = getNewToken();
                //使用新的Token，创建新的请求
                Request newRequest = chain.request()
                        .newBuilder()
                        .addHeader("token", newTolen)
                        .addHeader("deviceType", "ANDROID")
                        .addHeader("deviceModel", android.os.Build.MODEL)
                        .addHeader("type", "APP")
                        .build();
                //重新请求
                return chain.proceed(newRequest);
            }
            return response;
        }

        /**
         * 根据Response，判断Token是否失效
         *
         * @param response
         * @return
         */
        private boolean isTokenExpired(Response response) {
            if (response.code() == Constans.TOKEN_IS_OUT) {
                return true;
            }
            return false;
        }

        /**
         * 同步请求方式，获取最新的Token
         *
         * @return
         */
        private String getNewToken() throws IOException {

            // 通过一个特定的接口获取新的token，此处要用到同步的retrofit请求
            WBService.getService().create(Wbm.class).toLogin(SharedPreferencesUtils.getString(App.getAppContext(), Constans.USER_NAME, ""),
                    SharedPreferencesUtils.getString(App.getAppContext(), Constans.USER_PWD, ""))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<Result<LoginBean>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(Result<LoginBean> loginBeanResult) {
                            if (loginBeanResult.getCode() == 200) {
                                SharedPreferencesUtils.saveString(App.getAppContext(), Constans.TOKEN, loginBeanResult.getToken());
                                SharedPreferencesUtils.saveObject(App.getAppContext(), Constans.USER_INFO, loginBeanResult.getBean());
                                mToken = loginBeanResult.getToken();
                                App.mLoginBean = loginBeanResult.getBean();
                            }
                        }
                    });
            return mToken;
        }
    }


    public static final CacheControl FORCE_CACHE1 = new CacheControl.Builder()
            .onlyIfCached()
            .maxStale(60 * 60 * 24, TimeUnit.SECONDS)
            .build();

}
