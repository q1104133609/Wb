package com.fansu.yimaomiao.view.fragment.carousel;

import android.os.Bundle;

import com.fansu.yimaomiao.Constans;
import com.fansu.yimaomiao.R;
import com.fansu.yimaomiao.base.BaseFragment;
import com.fansu.yimaomiao.utils.SharedPreferencesUtils;
import com.fansu.yimaomiao.view.activity.MainActivity;

import butterknife.OnClick;

/**
 * Created by leo on 16/11/8.
 */

public class Carousel4Fragment extends BaseFragment {

    @OnClick(R.id.linear_4)
    public void toLogin() {
        mActivity.startActivity(MainActivity.class);
        SharedPreferencesUtils.saveBoolean(mActivity, Constans.IS_FIRST, true);
    }

    @Override
    protected int setRootView() {
        return R.layout.layout_carous4;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }
}
