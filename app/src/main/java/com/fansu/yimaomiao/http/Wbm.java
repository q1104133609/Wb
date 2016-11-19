package com.fansu.yimaomiao.http;


import com.fansu.yimaomiao.HttpConstans;
import com.fansu.yimaomiao.base.Result;
import com.fansu.yimaomiao.data.entity.LoginBean;
import com.fansu.yimaomiao.data.entity.MovieEntity;
import com.fansu.yimaomiao.data.entity.RegisterBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by leo on 16/10/22.
 */

public interface Wbm {
    @GET("top250")
    Observable<MovieEntity> getTopMovie(@Query("start") int start, @Query("count") int count);

//======================================登录注册==================================================
    /**
     * 登录
     *
     * @param phone
     * @param pwd
     * @return
     */
    @FormUrlEncoded
    @POST(HttpConstans.LOGIN)
    Observable<Result<LoginBean>> toLogin(@Field("phone") String phone, @Field("pwd") String pwd);

    /**
     * 短信验证码
     *
     * @param phone
     * @return
     */
    @FormUrlEncoded
    @POST(HttpConstans.SEND_DXYZ)
    Observable<Result<MovieEntity>> sendDuanxin(@Field("phone") String phone);

    /**
     * 登录
     *
     * @param phone
     * @param pwd
     * @param nickname
     * @param usertx
     * @param sex
     * @param content
     * @return
     */
    @FormUrlEncoded
    @POST(HttpConstans.REGUISTER)
    Observable<Result<RegisterBean>> toRegister(@Field("phone") String phone
            , @Field("pwd") String pwd
            , @Field("nickname") String nickname
            , @Field("usertx") String usertx
            , @Field("sex") String sex
            , @Field("content") String content);


    /**
     * 找回密码
     *
     * @param phone
     * @param pwd
     * @return
     */
    @FormUrlEncoded
    @POST(HttpConstans.RETRIEVE_PWD)
    Observable<Result> retrievePwd(@Field("phone") String phone, @Field("pwd") String pwd, @Field("code") String code);

    //======================================登录注册==================================================


}
