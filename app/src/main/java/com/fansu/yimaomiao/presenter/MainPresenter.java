package com.fansu.yimaomiao.presenter;


import com.fansu.yimaomiao.base.mvp.ApiCallback;
import com.fansu.yimaomiao.base.mvp.BasePresenter;
import com.fansu.yimaomiao.base.mvp.BaseView;
import com.fansu.yimaomiao.base.mvp.SubscriberCallBack;
import com.fansu.yimaomiao.entity.MovieEntity;
import com.fansu.yimaomiao.http.Wbm;

/**
 * Created by leo on 16/10/24.
 */

public class MainPresenter extends BasePresenter<BaseView<MovieEntity>> {
    public MainPresenter(BaseView<MovieEntity> view) {
        attachView(view);
    }

    public void loadData(int pn,int pageSize) {
        mvpView.isLoading();
        addSubscription(wbm.create(Wbm.class).getTopMovie(pn, pageSize), new SubscriberCallBack<>(new ApiCallback<MovieEntity>() {
            @Override
            public void onSuccess(MovieEntity movieEntity) {
                mvpView.isSuccess(movieEntity);
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
