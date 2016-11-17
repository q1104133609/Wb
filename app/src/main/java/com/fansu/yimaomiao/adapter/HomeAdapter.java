package com.fansu.yimaomiao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fansu.yimaomiao.R;
import com.fansu.yimaomiao.utils.Utils;
import com.fansu.yimaomiao.utils.transform.GlideRoundTransform;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.iwgang.countdownview.CountdownView;

/**
 * Created by leo on 16/11/1.
 */

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<String> mList = new ArrayList<>();
    public static final int ITEM_TYPE_BOTTOM = 2;
    public static final int ITEM_TYPE_CONTENT = 1;
    /**
     * 沒有更多
     */
    private boolean isNoMore;
    private int mBottomCount = 1;//底部View个数

    public HomeAdapter(Context mContext) {
        this.mContext = mContext;

    }


    public int getContentItemCount() {
        return mList.size();
    }

    //判断当前item类型
    @Override
    public int getItemViewType(int position) {
        int dataItemCount = getContentItemCount();
        if (dataItemCount > 0 && mBottomCount != 0 && position >= dataItemCount) {
            return ITEM_TYPE_BOTTOM;
        } else {
            return ITEM_TYPE_CONTENT;
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = viewType == ITEM_TYPE_BOTTOM ? new BottomViewHolder(LayoutInflater.from(mContext).inflate(R.layout.listview_footer, parent, false)) :
                new HomeContentView(LayoutInflater.from(mContext).inflate(R.layout.item_home_content, parent, false));
        viewHolder.setIsRecyclable(viewType == ITEM_TYPE_BOTTOM?true:false);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == ITEM_TYPE_CONTENT) {
            ((HomeContentView) holder).mName.setText(mList.get(position));
            ((HomeContentView) holder).mMoney.setText(mList.get(position));
            ((HomeContentView) holder).mName.setText(mList.get(position));
            Glide.with(mContext).load("").error(R.mipmap.default_photo).transform(new GlideRoundTransform(mContext)).into(((HomeContentView) holder).mImage_Goods);
            ((HomeContentView) holder).mCountdownView.start(Utils.getChaTime(Long.valueOf(mList.get(position))));
            ((HomeContentView) holder).mCountdownView.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
                @Override
                public void onEnd(CountdownView cv) {
                    cv.setVisibility(View.GONE);
                }
            });
            if (Utils.getChaTime(Long.valueOf(mList.get(position))) <= 0){
                ((HomeContentView) holder).mCountdownView.setVisibility(View.GONE);
            }else{
                ((HomeContentView) holder).mCountdownView.setVisibility(View.VISIBLE);
            }
        } else {
            if (isNoMore) {
                ((BottomViewHolder) holder).mLoading.setVisibility(View.GONE);
                ((BottomViewHolder) holder).tv_nomore.setVisibility(View.VISIBLE);
            } else {
                ((BottomViewHolder) holder).tv_nomore.setVisibility(View.GONE);
                ((BottomViewHolder) holder).mLoading.setVisibility(View.VISIBLE);

            }
        }
        holder.itemView.setOnClickListener(v -> {

        });
    }


    @Override
    public int getItemCount() {
        return mList.size() > 0 ? mList.size() + mBottomCount : 0;
    }


    /**
     * 添加數據
     *
     * @param imageList
     */
    public void addData(List<String> imageList) {
        if (imageList.size() > 0) {
            isNoMore = false;
            mList.addAll(imageList);
            notifyItemRangeChanged(mList.size() - imageList.size(), mList.size());
        } else {
            isNoMore = true;
            notifyDataSetChanged();

        }
    }


    public class HomeContentView extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_goods_money)
        TextView mMoney;
        @BindView(R.id.tv_goods_name)
        TextView mName;
        @BindView(R.id.image_goods)
        ImageView mImage_Goods;
        @BindView(R.id.countdownview)
        CountdownView mCountdownView;


        public HomeContentView(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //底部 ViewHolder
    public static class BottomViewHolder extends RecyclerView.ViewHolder {
        TextView tv_nomore;
        LinearLayout mLoading;

        public BottomViewHolder(View itemView) {
            super(itemView);
            tv_nomore = (TextView) itemView.findViewById(R.id.tv_nomore);
            mLoading = (LinearLayout) itemView.findViewById(R.id.linear_loading);
        }

    }
}
