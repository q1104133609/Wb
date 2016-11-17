package com.fansu.yimaomiao.base.mvp;

/**
 * Created by leo on 16/10/24.
 */
public interface Presenter<V> {
    void attachView(V mvpView);

    void detachView();
}
