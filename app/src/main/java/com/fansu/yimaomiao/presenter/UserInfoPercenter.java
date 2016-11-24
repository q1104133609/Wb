package com.fansu.yimaomiao.presenter;

import com.fansu.yimaomiao.base.Result;
import com.fansu.yimaomiao.base.mvp.ApiCallback;
import com.fansu.yimaomiao.base.mvp.BasePresenter;
import com.fansu.yimaomiao.base.mvp.BaseView;
import com.fansu.yimaomiao.base.mvp.SubscriberCallBack;
import com.fansu.yimaomiao.http.Wbm;
import com.fansu.yimaomiao.view.activity.UserInfoActivity;

import rx.Subscriber;

/**
 * Created by huangbo on 16/11/20.
 */
public class UserInfoPercenter extends BasePresenter<BaseView<Result>> {


    public UserInfoPercenter(BaseView<Result> baseView) {
        attachView(baseView);
    }

    public void updateInfo() {
        mvpView.isLoading();
        addSubscription(wbm.create(Wbm.class).updateInfo(), new SubscriberCallBack<Result>(new ApiCallback<Result>() {
            @Override
            public void onSuccess(Result o) {
                mvpView.isSuccess(o);
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
