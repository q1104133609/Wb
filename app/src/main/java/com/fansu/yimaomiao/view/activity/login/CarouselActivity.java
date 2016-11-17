package com.fansu.yimaomiao.view.activity.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.fansu.yimaomiao.R;
import com.fansu.yimaomiao.base.BaseActivity;
import com.fansu.yimaomiao.view.fragment.carousel.Carousel1Fragment;
import com.fansu.yimaomiao.view.fragment.carousel.Carousel2Fragment;
import com.fansu.yimaomiao.view.fragment.carousel.Carousel3Fragment;
import com.fansu.yimaomiao.view.fragment.carousel.Carousel4Fragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by leo on 16/11/1.
 * 轮播图页面
 *
 */

public class CarouselActivity extends BaseActivity {
    @BindView(R.id.viewpager_carousel)
    ViewPager mViewPager;
    private List<Fragment> mList = new ArrayList<>();

    @Override
    protected int setRootView() {
        return R.layout.activity_carouse;
    }

    @Override
    protected void initData() {
        mList.add(new Carousel1Fragment());
        mList.add(new Carousel2Fragment());
        mList.add(new Carousel3Fragment());
        mList.add(new Carousel4Fragment());
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mList.get(position);
            }

            @Override
            public int getCount() {
                return mList.size();
            }
        });


    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }
}
