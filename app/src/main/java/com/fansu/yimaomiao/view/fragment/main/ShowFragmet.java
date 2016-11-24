package com.fansu.yimaomiao.view.fragment.main;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.fansu.yimaomiao.Constans;
import com.fansu.yimaomiao.R;
import com.fansu.yimaomiao.adapter.ShowAdapter;
import com.fansu.yimaomiao.base.Result;
import com.fansu.yimaomiao.base.mvp.BaseView;
import com.fansu.yimaomiao.base.mvp.MvpFagment;
import com.fansu.yimaomiao.entity.ShowBean;
import com.fansu.yimaomiao.http.WBService;
import com.fansu.yimaomiao.http.Wbm;
import com.fansu.yimaomiao.inter.OnItemClickListener;
import com.fansu.yimaomiao.presenter.home.ShowPercenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import rx.Subscriber;

import static com.fansu.yimaomiao.App.city;

/**
 * Created by leo on 16/11/1.
 */

public class ShowFragmet extends MvpFagment<ShowPercenter> implements BGARefreshLayout.BGARefreshLayoutDelegate, OnItemClickListener, BaseView<Result<ShowBean>> {

    @BindView(R.id.base_bga)
    BGARefreshLayout mBgaRefreshLayout;
    @BindView(R.id.base_recycler)
    RecyclerView mRecyclerView;
    private List<ShowBean> mList = new ArrayList<>();
    private ShowAdapter mAdapter;
    private ShowPercenter mShowPercenter;
    private int pn = 1;


    @Override
    protected int setRootView() {
        return R.layout.fragment_show;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initRefreshLayout();
        initRecycler();
        mBgaRefreshLayout.beginRefreshing();
    }

    private void initRecycler() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter = new ShowAdapter());
        mAdapter.setOnItemClick(this);
    }

    private void initRefreshLayout() {
        mBgaRefreshLayout.setDelegate(this);
        BGARefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(mActivity, true);
        mBgaRefreshLayout.setRefreshViewHolder(refreshViewHolder);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mList.clear();
        pn = 1;
        mShowPercenter.getShowList(pn);

    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        pn += 1;
        mShowPercenter.getShowList(pn);
        return true;
    }


    @Override
    public void OnItemClickListener(View view, int position) {

    }

    @Override
    public void isSuccess(Result<ShowBean> bean) {
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
        mActivity.showToast("获取数据失败，请刷新重试~");
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
    protected ShowPercenter createPresenter() {
        return mShowPercenter = new ShowPercenter(this);
    }


}
