package com.fansu.yimaomiao.presenter.home;

import com.fansu.yimaomiao.Constans;
import com.fansu.yimaomiao.base.Result;
import com.fansu.yimaomiao.base.mvp.ApiCallback;
import com.fansu.yimaomiao.base.mvp.BasePresenter;
import com.fansu.yimaomiao.base.mvp.BaseView;
import com.fansu.yimaomiao.base.mvp.SubscriberCallBack;
import com.fansu.yimaomiao.entity.ShopBean;
import com.fansu.yimaomiao.entity.ShowBean;
import com.fansu.yimaomiao.http.Wbm;

/**
 * Created by huangbo on 16/11/19.
 */
public class ShowPercenter extends BasePresenter<BaseView<Result<ShowBean>>> {
    public ShowPercenter(BaseView<Result<ShowBean>> showFragmet) {
        attachView(showFragmet);
    }

    public void getShowList(int pageNo) {
        mvpView.isLoading();
        addSubscription(wbm.create(Wbm.class).getShowList(pageNo, Constans.PAGE_SIZE), new SubscriberCallBack<>(new ApiCallback<Result<ShowBean>>() {
            @Override
            public void onSuccess(Result<ShowBean> registerBeanResult) {
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
