package com.fansu.yimaomiao.view.fragment.main;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.fansu.yimaomiao.R;
import com.fansu.yimaomiao.adapter.NearAdater;
import com.fansu.yimaomiao.base.BaseFragment;
import com.fansu.yimaomiao.inter.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;

/**
 * Created by leo on 16/11/1.
 */

public class NearFragment extends BaseFragment implements BGARefreshLayout.BGARefreshLayoutDelegate, OnItemClickListener {
    @BindView(R.id.base_bga)
    BGARefreshLayout mBgaRefreshLayout;
    @BindView(R.id.base_recycler)
    RecyclerView mRecyclerView;
    private List<String> mList = new ArrayList<>();
    private NearAdater mAdapter;

    @Override
    protected int setRootView() {
        return R.layout.fragment_near;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initRefreshLayout();
        initRecycler();
        mBgaRefreshLayout.beginRefreshing();
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
        mList.clear();
        mList.add("张三");
        mList.add("李四");
        mList.add("王麻子");
        mList.add("张三");
        mList.add("李四");
        mList.add("王麻子");
        mList.add("张三");
        mList.add("李四");
        mList.add("王麻子");
        mAdapter.setData(mList);
        refreshLayout.endRefreshing();

    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        getData();
        return true;
    }

    private void getData() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                mActivity.runOnUiThread(new TimerTask() {
                    @Override
                    public void run() {
                        mList = new ArrayList<>();
                        mList.add("张三");
                        mList.add("李四");
                        mList.add("王麻子");
                        mList.add("张三");
                        mList.add("李四");
                        mList.add("王麻子");
                        mList.add("张三");
                        mList.add("李四");
                        mList.add("王麻子");
                        mAdapter.addData(mList);
                        mBgaRefreshLayout.endLoadingMore();
                        timer.cancel();
                    }
                });

            }
        },2000,2000);

    }

    @Override
    public void OnItemClickListener(View view, int position) {

    }
}
