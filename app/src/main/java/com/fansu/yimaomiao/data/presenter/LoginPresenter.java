package com.fansu.yimaomiao.data.presenter;

import com.fansu.yimaomiao.base.Result;
import com.fansu.yimaomiao.base.mvp.ApiCallback;
import com.fansu.yimaomiao.base.mvp.BasePresenter;
import com.fansu.yimaomiao.base.mvp.BaseView;
import com.fansu.yimaomiao.base.mvp.SubscriberCallBack;
import com.fansu.yimaomiao.data.entity.LoginBean;
import com.fansu.yimaomiao.http.Wbm;

/**
 * Created by huangbo on 2016/11/13.
 */

public class LoginPresenter extends BasePresenter<BaseView<Result<LoginBean>>>{
    public LoginPresenter(BaseView<Result<LoginBean>> view) {
        attachView(view);
    }

    public void loadData(String user,String pwd) {
        mvpView.isLoading();
        addSubscription(wbm.create(Wbm.class).toLogin(user,pwd), new SubscriberCallBack<>(new ApiCallback<Result<LoginBean>>() {
            @Override
            public void onSuccess(Result<LoginBean> loginBeanResult) {
                mvpView.isSuccess(loginBeanResult);
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
