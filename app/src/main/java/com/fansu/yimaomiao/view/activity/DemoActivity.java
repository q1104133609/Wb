package com.fansu.yimaomiao.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fansu.yimaomiao.R;
import com.fansu.yimaomiao.base.BaseActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by leo on 16/11/7.
 */

public class DemoActivity extends BaseActivity {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private List<String> mList = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected int setRootView() {
        return R.layout.activity_demo;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mList.add(" 热身 ");
        mList.add("张三");
        mList.add("张三");
        mList.add("张三");
        mList.add("张三");
        mList.add("张三");
        mList.add(" 热身 ");
        mList.add("张三");
        mList.add("张三");
        mList.add("张三");
        mList.add("张三");
        mList.add(" 热身 ");
        mList.add("张三");
        mList.add("张三");
        mList.add("张三");
        mList.add("张三");
        mRecyclerView.setLayoutManager(linearLayoutManager = new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new DemoAdater());

    }

    public class DemoAdater extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private final int IS_TITLE = 1;
        private final int IS_CONTENT = 2;
        private int isVisible = -1;

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return viewType == IS_TITLE ? new HeadHolder(getLayoutInflater().inflate(R.layout.item_demo_title, parent, false)) :
                    new ContentViewhoder(getLayoutInflater().inflate(R.layout.item_demo_content, null));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (getItemViewType(position) == IS_TITLE) {
                ((HeadHolder) holder).tv_title.setText(mList.get(position));
            } else {
                ((ContentViewhoder) holder).mContent.setText(mList.get(position));
                Glide.with(mContext).load("http://img2.imgtn.bdimg.com/it/u=1556106265,432542065&fm=21&gp=0.jpg").into(((ContentViewhoder) holder).mImage);
                GifDrawable gifFromResource = null;
                try {
                    gifFromResource = new GifDrawable(getResources(), R.drawable.anim);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ((ContentViewhoder) holder).mGifImageView.setImageDrawable(gifFromResource);
                GifDrawable finalGifFromResource = gifFromResource;
                if (isVisible == position) {
                    ((ContentViewhoder) holder).mGifImageView.setVisibility(View.VISIBLE);
                } else {
                    ((ContentViewhoder) holder).mGifImageView.setVisibility(View.GONE);
                }
                ((ContentViewhoder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isVisible == position) {
                            isVisible = -1;
                            notifyDataSetChanged();
                            ((ContentViewhoder) holder).mGifImageView.setVisibility(View.GONE);
                            if (finalGifFromResource != null)
                                finalGifFromResource.stop();
                        } else {
                            if (finalGifFromResource != null) {
                                isVisible = position;
                                notifyDataSetChanged();
                                if (position >= mList.size() - 5) {
                                    linearLayoutManager.setStackFromEnd(true);
                                }
//                                else{
//                                    linearLayoutManager.scrollToPositionWithOffset(position-1, 20);
//
//                                }
                                ((ContentViewhoder) holder).mGifImageView.setVisibility(View.VISIBLE);
                                if (finalGifFromResource.isRunning()) {
                                    finalGifFromResource.reset();
                                } else {
                                    finalGifFromResource.start();

                                }
                            }

                        }

                    }
                });

            }

        }


        @Override
        public int getItemCount() {
            return mList.size();
        }

        @Override
        public int getItemViewType(int position) {
            if (mList.get(position).contains(" ")) {
                return IS_TITLE;
            } else {
                return IS_CONTENT;
            }
        }

        public class HeadHolder extends RecyclerView.ViewHolder {
            TextView tv_title;

            public HeadHolder(View itemView) {
                super(itemView);
                tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            }
        }

        public class ContentViewhoder extends RecyclerView.ViewHolder {
            @BindView(R.id.image)
            ImageView mImage;
            @BindView(R.id.tv_content)
            TextView mContent;
            @BindView(R.id.gif1)
            GifImageView mGifImageView;

            public ContentViewhoder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }
}
