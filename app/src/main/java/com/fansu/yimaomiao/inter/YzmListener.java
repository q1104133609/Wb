package com.fansu.yimaomiao.inter;

/**
 * Created by huangbo on 16/11/19.
 */

public interface YzmListener<T> {
    void isRight(T t);
    void isError(Throwable throwable);
}
