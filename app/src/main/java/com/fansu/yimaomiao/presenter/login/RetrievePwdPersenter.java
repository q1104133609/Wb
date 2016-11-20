package com.fansu.yimaomiao.presenter.login;

import com.fansu.yimaomiao.base.Result;
import com.fansu.yimaomiao.base.mvp.ApiCallback;
import com.fansu.yimaomiao.base.mvp.BasePresenter;
import com.fansu.yimaomiao.base.mvp.BaseView;
import com.fansu.yimaomiao.base.mvp.SubscriberCallBack;
import com.fansu.yimaomiao.http.Wbm;

/**
 * Created by huangbo on 16/11/19.
 */

public class RetrievePwdPersenter extends BasePresenter<BaseView<Result>>{

    public RetrievePwdPersenter(BaseView<Result> view) {attachView(view);

    }

    public void toRegisterPersenter(String user, String pwd, String yanzm) {
        mvpView.isLoading();
        addSubscription(wbm.create(Wbm.class).retrievePwd(user, pwd, yanzm), new SubscriberCallBack<>(new ApiCallback<Result>() {
            @Override
            public void onSuccess(Result registerBeanResult) {
                mvpView.isSuccess(registerBeanResult);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.isFailure(msg);
            }

            @Override
            public void onCompleted() {
                mvpView.hideLoading();
            }
        }));
    }
}
