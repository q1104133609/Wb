package com.fansu.yimaomiao.view.fragment.main;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.fansu.yimaomiao.Constans;
import com.fansu.yimaomiao.R;
import com.fansu.yimaomiao.adapter.NearAdater;
import com.fansu.yimaomiao.base.Result;
import com.fansu.yimaomiao.base.mvp.BaseView;
import com.fansu.yimaomiao.base.mvp.MvpFagment;
import com.fansu.yimaomiao.entity.NearBean;
import com.fansu.yimaomiao.http.WBService;
import com.fansu.yimaomiao.http.Wbm;
import com.fansu.yimaomiao.inter.OnItemClickListener;
import com.fansu.yimaomiao.presenter.home.NearPercenter;
import com.fansu.yimaomiao.utils.SharedPreferencesUtils;
import com.fansu.yimaomiao.utils.Utils;
import com.tbruyelle.rxpermissions.RxPermissions;

import butterknife.BindView;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;
import rx.Subscriber;


/**
 * Created by leo on 16/11/1.
 */

public class NearFragment extends MvpFagment<NearPercenter> implements BGARefreshLayout.BGARefreshLayoutDelegate, OnItemClickListener, BaseView<Result<NearBean>> {
    @BindView(R.id.base_bga)
    BGARefreshLayout mBgaRefreshLayout;
    @BindView(R.id.base_recycler)
    RecyclerView mRecyclerView;
    private NearAdater mAdapter;
    private int pn = 1;
    private String lat;
    private String lon;
    private NearLocationListener mListener = new NearLocationListener();
    public LocationClient mLocationClient = null;
    private NearPercenter mNearPercenter;
    private String mSex = "女", mAge = "18", mTime = "";
    private boolean isFirstNear;

    @Override
    protected int setRootView() {
        return R.layout.fragment_near;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initRefreshLayout();
        initRecycler();
        RxPermissions rxPermissions = new RxPermissions(mActivity);
        rxPermissions
                .request(Manifest.permission.ACCESS_COARSE_LOCATION
                        , Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe(granted -> {
                    if (granted) {
                        Utils.initLocation(mActivity, mLocationClient, mListener, 4000);
                        mBgaRefreshLayout.beginRefreshing();
                    } else {
                        mActivity.showToast("定位权限没开启，附近推荐人可能不正确哟~");
                        mBgaRefreshLayout.beginRefreshing();
                    }
                });
    }

    private void initRecycler() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter = new NearAdater());
        mAdapter.setOnItemClick(this);
    }

    private void initRefreshLayout() {
        mBgaRefreshLayout.setDelegate(this);
        BGARefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(mActivity, true);
        mBgaRefreshLayout.setRefreshViewHolder(refreshViewHolder);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        pn = 1;
        mNearPercenter.getNearPeole(pn, lon, lat, mAge, mSex, mTime);

    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        pn += 1;
        mNearPercenter.getNearPeole(pn, lon, lat, mAge, mSex, mTime);
        return true;
    }

    @Override
    public void OnItemClickListener(View view, int position) {

    }


    @Override
    public void isSuccess(Result<NearBean> bean) {
        if (bean.getCode() == Constans.SERVICE_SUCCESS) {
            if (pn > 1) {
                if (bean.getPagination().getLastPage() >= pn) {
                    mAdapter.addData(bean.getPagination().getList());
                } else {
                    mActivity.showToast("没有更多数据了哟~");
                }
            } else {
                mAdapter.setData(bean.getPagination().getList());
            }

        }

    }

    @Override
    public void isFailure(String msg) {
        mActivity.showToast("获取附近人列表失败，请重试~");

    }

    @Override
    public void isLoading() {

    }

    @Override
    public void hideLoading() {
        if (pn > 1) {
            mBgaRefreshLayout.endLoadingMore();
        } else {
            mBgaRefreshLayout.endRefreshing();
        }

    }

    @Override
    protected NearPercenter createPresenter() {
        return mNearPercenter = new NearPercenter(this);
    }

    /**
     * 定位监听
     */
    public class NearLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            //Receive Location
            lat = String.valueOf(location.getLatitude());
            lon = String.valueOf(location.getLongitude());
            //第一次获取更新地址
            if (!isFirstNear) {
                isFirstNear = true;
                updateAddress();
            }

        }
    }


    /**
     * 更新用户地址
     */
    public void updateAddress() {
        if (Utils.checkLoginNo(mActivity)) {
            WBService.getService().create(Wbm.class).updateAddress(lon, lat, SharedPreferencesUtils.getString(mActivity, Constans.USER_NAME, "")).subscribe(new Subscriber<Result>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(Result result) {

                }
            });
        }
    }
}
