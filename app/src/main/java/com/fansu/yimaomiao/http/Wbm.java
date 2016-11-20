package com.fansu.yimaomiao.http;


import com.fansu.yimaomiao.HttpConstans;
import com.fansu.yimaomiao.base.Result;
import com.fansu.yimaomiao.entity.LoginBean;
import com.fansu.yimaomiao.entity.MovieEntity;
import com.fansu.yimaomiao.entity.NearBean;
import com.fansu.yimaomiao.entity.RegisterBean;
import com.fansu.yimaomiao.entity.ShopBean;
import com.fansu.yimaomiao.entity.ShowBean;

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

    //======================================主页4页面==================================================

    /**
     * 获取主页商品列表
     *
     * @return
     */
    @GET(HttpConstans.HSHOP_INDEX)
    Observable<Result<ShopBean>> getShopGoods();

    /**
     * 获取晒单列表
     *
     * @return
     */
    @GET(HttpConstans.GET_SHOW_LIST)
    Observable<Result<ShowBean>> getShowList(@Query("pageNo") int pageNo,@Query("pageSize") int pageSize);

    /**
     * 获取附近人列表
     *
     * @return
     */
    @GET(HttpConstans.HSHOP_INDEX)
    Observable<Result<NearBean>> getNearList(@Query("pageNo") int pageNo,@Query("pageSize") int pageSize);

    //======================================主页4页面==================================================

}
