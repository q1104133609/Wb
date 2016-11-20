package com.fansu.yimaomiao.view.fragment.main;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.fansu.yimaomiao.R;
import com.fansu.yimaomiao.adapter.HomeAdapter;
import com.fansu.yimaomiao.base.Result;
import com.fansu.yimaomiao.base.mvp.BaseView;
import com.fansu.yimaomiao.base.mvp.MvpFagment;
import com.fansu.yimaomiao.customview.CycleSlipViewPager;
import com.fansu.yimaomiao.customview.LoadMoreRecyclerView;
import com.fansu.yimaomiao.entity.ShopBean;
import com.fansu.yimaomiao.presenter.home.HomeFramgnetPercenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;

/**
 * Created by leo on 16/11/1.
 */

public class HomeFragment extends MvpFagment<HomeFramgnetPercenter> implements BGARefreshLayout.BGARefreshLayoutDelegate, CycleSlipViewPager.OnBannerOnClickListener, BaseView<Result<ShopBean>> {
    @BindView(R.id.recyclerview)
    LoadMoreRecyclerView mRecyclerView;
    @BindView(R.id.bgarefresh)
    BGARefreshLayout mRefreshLayout;
    @BindView(R.id.banner)
    CycleSlipViewPager mBanner;
    private HomeAdapter mHomeAdapter;
    @BindView(R.id.appbar)
    AppBarLayout appBarLayout;
    private List<ShopBean> mList = new ArrayList<>();
    /**
     * 是否正在加载
     */
    private boolean mOnLoad;
    private HomeFramgnetPercenter mHomeFramgnetPercenter;

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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.setAdapter(mHomeAdapter = new HomeAdapter(mActivity));
        mRecyclerView.setLoadMoreListener(() -> {
//            if (!mOnLoad) {
//                mHomeFramgnetPercenter
//                mOnLoad = true;
//                mOnLoad = false;
//                mHomeAdapter.addData(imageList);
//            }
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
        mHomeFramgnetPercenter.getShopGoods();

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


    @Override
    public void isSuccess(Result<ShopBean> bean) {
        if (bean.getCode() == 200) {
            mList.clear();
            mList.addAll(bean.getListBean());
            mHomeAdapter.setData(mList);
            mRefreshLayout.endRefreshing();
        }else{
            mActivity.showToast("获取列表失败,请刷新重试~");
        }
    }

    @Override
    public void isFailure(String msg) {
        mActivity.showToast("获取列表失败,请刷新重试~");
        mRefreshLayout.endRefreshing();
    }

    @Override
    public void isLoading() {

    }

    @Override
    public void hideLoading() {
        mRefreshLayout.endRefreshing();

    }

    @Override
    protected HomeFramgnetPercenter createPresenter() {
        return mHomeFramgnetPercenter = new HomeFramgnetPercenter(this);
    }
}
