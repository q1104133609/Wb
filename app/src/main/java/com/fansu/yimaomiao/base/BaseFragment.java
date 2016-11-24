package com.fansu.yimaomiao.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.fansu.yimaomiao.R;
import com.fansu.yimaomiao.customview.kyloading.KyLoadingBuilder;
import com.fansu.yimaomiao.event.BaseEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;

/**
 * Created by leo on 16/10/22.
 */

public abstract class BaseFragment extends Fragment {

    protected View mRootView;
    protected BaseActivity mActivity;
    protected KyLoadingBuilder builder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(setRootView(), container, false);
        this.mActivity = (BaseActivity) getActivity();
        builder = new KyLoadingBuilder(getActivity());
        ButterKnife.bind(this, mRootView);
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
        initView(savedInstanceState);
        return mRootView;
    }

    protected abstract int setRootView();


    /**
     * 默认样式
     */
    protected void showLoading(boolean iscancle) {
        builder.setIcon(R.drawable.loading);
        builder.setText("正在加载中...");
        builder.setOutsideTouchable(iscancle);
        builder.setBackTouchable(iscancle);
        builder.show();
    }

    protected void closeLoading() {
        builder.dismiss();
    }

    /**
     * 自定义loading
     *
     * @param drawabl 图片
     * @param message 内容
     */
    protected void showLoading(int drawabl, String message) {
        builder.setIcon(drawabl);
        builder.setText(message);
        builder.show();
    }

    protected abstract void initView(Bundle savedInstanceState);

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BaseEvent event) {

    }

}
