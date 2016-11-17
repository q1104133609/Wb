package com.fansu.yimaomiao.http;


import com.fansu.yimaomiao.HttpConstans;
import com.fansu.yimaomiao.base.Result;
import com.fansu.yimaomiao.data.entity.LoginBean;
import com.fansu.yimaomiao.data.entity.MovieEntity;

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

    @FormUrlEncoded
    @POST(HttpConstans.LOGIN)
    Observable<Result<LoginBean>> toLogin(@Field("phone") String phone, @Field("pwd") String pwd);

    @FormUrlEncoded
    @POST(HttpConstans.SEND_DXYZ)
    Observable<Result<MovieEntity>> sendDuanxin(@Field("phone") String phone);




}
