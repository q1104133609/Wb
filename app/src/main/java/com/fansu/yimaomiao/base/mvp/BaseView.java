package com.fansu.yimaomiao.base.mvp;

/**
 * Created by leo on 16/10/24.
 */

public interface BaseView<T> {
    /**
     * 成功
     *
     * @param bean bean
     */
    void isSuccess(T bean);
    /**
     * 失敗
     *
     * @param msg msg
     */
    void isFailure(String msg);

    /**
     * 正在加載
     */
    void isLoading();


    /**
     * 加載結束
     */
    void hideLoading();
}
