package com.fansu.yimaomiao.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fansu.yimaomiao.R;
import com.fansu.yimaomiao.entity.ShowBean;
import com.fansu.yimaomiao.inter.OnItemClickListener;
import com.fansu.yimaomiao.utils.transform.GlideCircleTransform;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by leo on 16/11/5.
 */

public class ShowAdapter extends RecyclerView.Adapter<ShowAdapter.ShowView> {

    private List<ShowBean> mList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;


    @Override
    public ShowView onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShowView(LayoutInflater.from(parent.getContext()).inflate(R.layout.show_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ShowView holder, int position) {
        Glide.with(holder.mHead.getContext()).load(mList.get(position).getUser().getUsertx()).error(R.mipmap.default_head).transform(new GlideCircleTransform(holder.mHead.getContext())).into(holder.mHead);
        if (!TextUtils.isEmpty(mList.get(position).getTupian1()) && !TextUtils.isEmpty(mList.get(position).getTupian2()) && !TextUtils.isEmpty(mList.get(position).getTupain3())) {
            holder.mImage_Content1.setVisibility(View.VISIBLE);
            holder.mImage_Content2.setVisibility(View.VISIBLE);
            holder.mImage_Content.setVisibility(View.VISIBLE);
            Glide.with(holder.mHead.getContext()).load(mList.get(position).getTupian2()).error(R.mipmap.default_photo).transform(new GlideCircleTransform(holder.mHead.getContext())).into(holder.mImage_Content1);
            Glide.with(holder.mHead.getContext()).load(mList.get(position).getTupain3()).error(R.mipmap.default_photo).transform(new GlideCircleTransform(holder.mHead.getContext())).into(holder.mImage_Content2);
            Glide.with(holder.mHead.getContext()).load(mList.get(position).getTupian1()).error(R.mipmap.default_photo).transform(new GlideCircleTransform(holder.mHead.getContext())).into(holder.mImage_Content);
        } else if (!TextUtils.isEmpty(mList.get(position).getTupian1()) && !TextUtils.isEmpty(mList.get(position).getTupian1())) {
            holder.mImage_Content1.setVisibility(View.VISIBLE);
            holder.mImage_Content2.setVisibility(View.GONE);
            holder.mImage_Content.setVisibility(View.VISIBLE);
            Glide.with(holder.mHead.getContext()).load(mList.get(position).getTupian2()).error(R.mipmap.default_photo).transform(new GlideCircleTransform(holder.mHead.getContext())).into(holder.mImage_Content1);
            Glide.with(holder.mHead.getContext()).load(mList.get(position).getTupian1()).error(R.mipmap.default_photo).transform(new GlideCircleTransform(holder.mHead.getContext())).into(holder.mImage_Content);
        } else if (!TextUtils.isEmpty(mList.get(position).getTupian1())) {
            holder.mImage_Content1.setVisibility(View.GONE);
            holder.mImage_Content2.setVisibility(View.GONE);
            holder.mImage_Content.setVisibility(View.VISIBLE);
            Glide.with(holder.mHead.getContext()).load(mList.get(position).getTupian1()).error(R.mipmap.default_photo).transform(new GlideCircleTransform(holder.mHead.getContext())).into(holder.mImage_Content);
        }else{
            holder.mImage_Content1.setVisibility(View.GONE);
            holder.mImage_Content2.setVisibility(View.GONE);
            holder.mImage_Content.setVisibility(View.GONE);
        }

        holder.mSex.setText(mList.get(position).getUser().getSex());
        holder.mName.setText(mList.get(position).getUser().getNickname());
        holder.mContent.setText(mList.get(position).getPjcontent());
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.OnItemClickListener(holder.itemView, holder.getLayoutPosition());
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public void setData(List<ShowBean> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public void addData(List<ShowBean> list) {
        mList.addAll(list);
        notifyItemRangeInserted(mList.size() - list.size(), mList.size());
    }

    public class ShowView extends RecyclerView.ViewHolder {

        @BindView(R.id.image_head)
        ImageView mHead;
        @BindView(R.id.tv_sex)
        TextView mSex;
        @BindView(R.id.tv_name)
        TextView mName;
        @BindView(R.id.tv_content)
        TextView mContent;
        @BindView(R.id.image_content)
        ImageView mImage_Content;
        @BindView(R.id.image_content1)
        ImageView mImage_Content1;
        @BindView(R.id.image_content2)
        ImageView mImage_Content2;

        public ShowView(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    public void setOnItemClick(OnItemClickListener onItemClick) {
        if (onItemClick != null) {
            this.onItemClickListener = onItemClick;
        }
    }
}
