package com.fansu.yimaomiao.presenter.home;

import com.fansu.yimaomiao.base.Result;
import com.fansu.yimaomiao.base.mvp.ApiCallback;
import com.fansu.yimaomiao.base.mvp.BasePresenter;
import com.fansu.yimaomiao.base.mvp.BaseView;
import com.fansu.yimaomiao.base.mvp.SubscriberCallBack;
import com.fansu.yimaomiao.entity.ShopBean;
import com.fansu.yimaomiao.http.Wbm;

/**
 * Created by huangbo on 16/11/19.
 */
public class HomeFramgnetPercenter extends BasePresenter<BaseView<Result<ShopBean>>> {
    public HomeFramgnetPercenter(BaseView<Result<ShopBean>> view) {
        attachView(view);

    }

    public void getShopGoods() {
        mvpView.isLoading();
        addSubscription(wbm.create(Wbm.class).getShopGoods(), new SubscriberCallBack<>(new ApiCallback<Result<ShopBean>>() {
            @Override
            public void onSuccess(Result<ShopBean> registerBeanResult) {
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
