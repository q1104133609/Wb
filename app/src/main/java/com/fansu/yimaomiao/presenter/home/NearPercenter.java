package com.fansu.yimaomiao.presenter.home;

import com.fansu.yimaomiao.Constans;
import com.fansu.yimaomiao.base.Result;
import com.fansu.yimaomiao.base.mvp.ApiCallback;
import com.fansu.yimaomiao.base.mvp.BasePresenter;
import com.fansu.yimaomiao.base.mvp.BaseView;
import com.fansu.yimaomiao.base.mvp.SubscriberCallBack;
import com.fansu.yimaomiao.entity.NearBean;
import com.fansu.yimaomiao.entity.ShowBean;
import com.fansu.yimaomiao.http.Wbm;
import com.fansu.yimaomiao.view.fragment.main.NearFragment;

/**
 * Created by huangbo on 16/11/19.
 */
public class NearPercenter extends BasePresenter<BaseView<Result<NearBean>>> {

    public NearPercenter(BaseView<Result<NearBean>> view) {
        attachView(view);
    }

    public void getNearPeole(int pn, String lng, String lan, String age, String sex, String time) {
        mvpView.isLoading();
        addSubscription(wbm.create(Wbm.class).getNearList(pn, Constans.PAGE_SIZE, lng, lan, age, sex), new SubscriberCallBack<>(new ApiCallback<Result<NearBean>>() {
            @Override
            public void onSuccess(Result<NearBean> registerBeanResult) {
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
