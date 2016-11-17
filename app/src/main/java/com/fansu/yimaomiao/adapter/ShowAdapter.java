package com.fansu.yimaomiao.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fansu.yimaomiao.R;
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

    private List<String> mList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;


    @Override
    public ShowView onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShowView(LayoutInflater.from(parent.getContext()).inflate(R.layout.show_item, parent,false));
    }

    @Override
    public void onBindViewHolder(ShowView holder, int position) {
        Glide.with(holder.mHead.getContext()).load("").error(R.mipmap.default_head).transform(new GlideCircleTransform(holder.mHead.getContext())).into(holder.mHead);
        Glide.with(holder.mHead.getContext()).load("").error(R.mipmap.default_photo).transform(new GlideCircleTransform(holder.mHead.getContext())).into(holder.mImage_Content);

        holder.mSex.setText(mList.get(position));
        holder.mName.setText(mList.get(position));
        holder.mContent.setText(mList.get(position));
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


    public void setData(List<String> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public void addData(List<String> list) {
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

        public ShowView(View itemView) {
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
