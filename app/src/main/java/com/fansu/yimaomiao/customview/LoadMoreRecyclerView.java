package com.fansu.yimaomiao.customview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;

/**
 *
 * 添加加载更多功能
 * Created by chs .
 */
public class LoadMoreRecyclerView extends RecyclerView {

    private boolean isScrollingToBottom = true;
    private FloatingActionButton floatingActionButton;
    private LoadMoreListener mLoadingListener;

    public LoadMoreRecyclerView(Context context) {
        super(context);
    }

    public LoadMoreRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public LoadMoreRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    public void setLoadMoreListener(LoadMoreListener loadMoreListener){
        this.mLoadingListener = loadMoreListener;
    }

    @Override
    public void onScrolled(int dx, int dy) {
        isScrollingToBottom = dy > 0;
        if (floatingActionButton != null) {
            if (isScrollingToBottom) {
                if (floatingActionButton.isShown())
                    floatingActionButton.hide();
            } else {
                if (!floatingActionButton.isShown())
                    floatingActionButton.show();
            }
        }
    }

    @Override
    public void onScrollStateChanged(int state) {
        if (state == RecyclerView.SCROLL_STATE_IDLE && mLoadingListener != null) {
            LayoutManager layoutManager = getLayoutManager();
            int lastVisibleItemPosition;
            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            if (layoutManager instanceof GridLayoutManager) {
                lastVisibleItemPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                int[] into = new int[((StaggeredGridLayoutManager) layoutManager).getSpanCount()];
                ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(into);
                lastVisibleItemPosition = findMax(into);
            } else {
                lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
            }
            if (visibleItemCount > 0 && state == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition == totalItemCount - 1 && mLoadingListener != null) {
                mLoadingListener.onLoadMore();
            }
        }
    }
    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }
    public interface LoadMoreListener {
        void onLoadMore();
    }
}