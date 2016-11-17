package com.fansu.yimaomiao.base.mvp;

/**
 * Created by leo on 16/10/24.
 */
public interface ApiCallback<T> {
    void onSuccess(T t);

    void onFailure(int code, String msg);


    void onCompleted();
}
