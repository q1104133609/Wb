package com.fansu.yimaomiao.data.presenter;

import com.fansu.yimaomiao.base.Result;
import com.fansu.yimaomiao.base.mvp.ApiCallback;
import com.fansu.yimaomiao.base.mvp.BasePresenter;
import com.fansu.yimaomiao.base.mvp.BaseView;
import com.fansu.yimaomiao.base.mvp.SubscriberCallBack;
import com.fansu.yimaomiao.data.entity.RegisterBean;
import com.fansu.yimaomiao.http.Wbm;

/**
 * Created by huangbo on 16/11/19.
 */
public class RegisterPersenter extends BasePresenter<BaseView<Result<RegisterBean>>> {
    public RegisterPersenter(BaseView<Result<RegisterBean>> view) {attachView(view);

    }

    public void toRegisterPersenter(String user, String pwd, String yanzm) {
        mvpView.isLoading();
        addSubscription(wbm.create(Wbm.class).toRegister(user, pwd, "小毛", "http://img0.imgtn.bdimg.com/it/u=3264805915,1360440417&fm=11&gp=0.jpg", "男", yanzm), new SubscriberCallBack<>(new ApiCallback<Result<RegisterBean>>() {
            @Override
            public void onSuccess(Result<RegisterBean> registerBeanResult) {
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
