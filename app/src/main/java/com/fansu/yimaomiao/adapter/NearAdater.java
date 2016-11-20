package com.fansu.yimaomiao.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fansu.yimaomiao.R;
import com.fansu.yimaomiao.entity.NearBean;
import com.fansu.yimaomiao.inter.OnItemClickListener;
import com.fansu.yimaomiao.utils.transform.GlideCircleTransform;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by leo on 16/11/4.
 */
public class NearAdater extends RecyclerView.Adapter<NearAdater.NearView> {

    private List<NearBean> mList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    @Override
    public NearView onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NearView(LayoutInflater.from(parent.getContext()).inflate(R.layout.near_item, parent, false));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onBindViewHolder(final NearView holder, int position) {
        Glide.with(holder.mHead.getContext()).load("").error(R.mipmap.default_head).transform(new GlideCircleTransform(holder.mHead.getContext())).into(holder.mHead);
//        holder.mSex.setText(mList.get(position));
//        holder.mAge.setText(mList.get(position));
//        holder.mTange.setText(mList.get(position));
//        holder.mName.setText(mList.get(position));
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.OnItemClickListener(holder.itemView, holder.getLayoutPosition());
                }
            });
        }

    }


    public void setData(List<NearBean> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public void addData(List<NearBean> list) {
        mList.addAll(list);
        notifyItemRangeInserted(mList.size() - list.size(), mList.size());
    }


    public class NearView extends RecyclerView.ViewHolder {
        @BindView(R.id.image_head)
        ImageView mHead;
        @BindView(R.id.tv_sex)
        TextView mSex;
        @BindView(R.id.tv_age)
        TextView mAge;
        @BindView(R.id.tv_range)
        TextView mTange;
        @BindView(R.id.tv_name)
        TextView mName;

        public NearView(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

    public void setOnItemClick(OnItemClickListener onItemClick){
        if (onItemClick!=null){
            this.onItemClickListener = onItemClick;
        }
    }
}
