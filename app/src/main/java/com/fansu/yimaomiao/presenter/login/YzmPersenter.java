package com.fansu.yimaomiao.presenter.login;

import com.fansu.yimaomiao.base.Result;
import com.fansu.yimaomiao.http.WBService;
import com.fansu.yimaomiao.http.Wbm;
import com.fansu.yimaomiao.inter.YzmListener;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by huangbo on 16/11/19.
 */

public class YzmPersenter {


    public static void getYZM(String phone, YzmListener<Result> listener) {
        WBService.getService().create(Wbm.class).sendDuanxin(phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.isError(e);


                    }

                    @Override
                    public void onNext(Result movieEntityResult) {
                        listener.isRight(movieEntityResult);

                    }
                });
    }
}
