package com.fansu.yimaomiao.view.fragment.main;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;


import com.fansu.yimaomiao.R;
import com.fansu.yimaomiao.adapter.HomeAdapter;
import com.fansu.yimaomiao.base.BaseFragment;
import com.fansu.yimaomiao.customview.CycleSlipViewPager;
import com.fansu.yimaomiao.customview.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;

/**
 * Created by leo on 16/11/1.
 */

public class HomeFragment extends BaseFragment implements BGARefreshLayout.BGARefreshLayoutDelegate, CycleSlipViewPager.OnBannerOnClickListener {
    @BindView(R.id.recyclerview)
    LoadMoreRecyclerView mRecyclerView;
    @BindView(R.id.bgarefresh)
    BGARefreshLayout mRefreshLayout;
    @BindView(R.id.banner)
    CycleSlipViewPager mBanner;
    private HomeAdapter mHomeAdapter;
    @BindView(R.id.appbar)
    AppBarLayout appBarLayout;
    /**
     * 是否正在加载
     */
    private boolean mOnLoad;

    @Override
    protected int setRootView() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        List<String> imageList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            imageList.add("http://img4.imgtn.bdimg.com/it/u=556011511,3000000093&fm=21&gp=0.jpg");
        }
        mBanner.initViewpager(mActivity, imageList);
        mBanner.setBannerClickListener(this);
        initRefreshLayout();
        initReycler();
        mRefreshLayout.beginRefreshing();


    }

    /**
     * 初始化recyclerview
     */
    private void initReycler() {
        List<String> imageList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            imageList.add("1"+i+"78338200");
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.setAdapter(mHomeAdapter = new HomeAdapter(mActivity));
        mRecyclerView.setLoadMoreListener(() -> {
            if (!mOnLoad) {
                mOnLoad = true;
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        mActivity.runOnUiThread(new TimerTask() {
                            @Override
                            public void run() {
                                mOnLoad = false;
                                mHomeAdapter.addData(imageList);
                                timer.cancel();
                            }
                        });
                    }
                }, 2000, 2000);

            }
        });
    }


    private void initRefreshLayout() {
        appBarLayout.addOnOffsetChangedListener((appBarLayout1, verticalOffset) -> {
            if (verticalOffset >= 0 && !mOnLoad) {
                mRefreshLayout.setPullDownRefreshEnable(true);
            } else {
                mRefreshLayout.setPullDownRefreshEnable(false);
            }
        });
        mRefreshLayout.setDelegate(this);
        BGARefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(mActivity, false);
        mRefreshLayout.setRefreshViewHolder(refreshViewHolder);

    }


    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        List<String> imageList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            imageList.add("1478"+i+"35800");
        }
        mHomeAdapter.addData(imageList);
        refreshLayout.endRefreshing();

    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }

    /**
     * banner点击
     *
     * @param view
     * @param position
     */
    @Override
    public void onClick(View view, int position) {

    }

    @OnClick(R.id.image_time1)
    public void time1() {

    }

    @OnClick(R.id.image_time2)
    public void time2() {

    }

    @OnClick(R.id.image_time3)
    public void time3() {

    }

    @OnClick(R.id.image_time4)
    public void time4() {

    }

    @OnClick(R.id.image_time5)
    public void time5() {

    }

    @OnClick(R.id.image_time6)
    public void time6() {

    }

    @OnClick(R.id.image_time7)
    public void time7() {

    }

    @OnClick(R.id.image_time8)
    public void time8() {

    }


}
