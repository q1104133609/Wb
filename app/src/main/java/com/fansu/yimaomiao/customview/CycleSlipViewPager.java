package com.fansu.yimaomiao.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.fansu.yimaomiao.R;

import java.util.List;

/**
 * Created by leo on 16/11/1.
 */

public class CycleSlipViewPager extends LinearLayout {
    private ViewPager viewPager;
    private AdAdapter adAdapter;
    private LinearLayout parentView;
    private ImageView[] imageViews;
    private ImageView[] indicatorImageViews;
    private LinearLayout ll_indicator;
    private int currentPosition;
    private boolean isImageTwo;
    private Context mContext;
    private int time;

    public CycleSlipViewPager(Context context) {
        super(context);
    }

    public CycleSlipViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.cycleslip_attrs);
        time = typedArray.getInteger(R.styleable.cycleslip_attrs_time,3000);

    }

    private void initView(Context context) {
        parentView = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.viewpager_cycle_slip, this);
    }

    private void initIndicator(Context context) {
        ll_indicator = (LinearLayout) findViewById(R.id.ll_indicator);

        if (imageViews.length != 1) {
            if (!isImageTwo) {
                indicatorImageViews = new ImageView[imageViews.length];
                for (int i = 0; i < indicatorImageViews.length; i++) {
                    ImageView imageView = new ImageView(context);
                    LayoutParams layoutParams = new LayoutParams(10, 10);
                    layoutParams.leftMargin = 8;
                    layoutParams.bottomMargin = 8;
                    imageView.setBackgroundResource(R.drawable.round_white_bg);
                    indicatorImageViews[i] = imageView;
                    ll_indicator.addView(imageView, layoutParams);
                }
            } else {
                indicatorImageViews = new ImageView[2];
                for (int i = 0; i < 2; i++) {
                    ImageView imageView = new ImageView(context);
                    LayoutParams layoutParams = new LayoutParams(10, 10);
                    layoutParams.leftMargin = 8;
                    layoutParams.bottomMargin = 8;
                    imageView.setBackgroundResource(R.drawable.round_white_bg);
                    indicatorImageViews[i] = imageView;
                    ll_indicator.addView(imageView, layoutParams);
                }
            }
        }
    }

    private void initViewPager() {
        viewPager = (ViewPager) parentView.findViewById(R.id.vp_ad);
        adAdapter = new AdAdapter();
        viewPager.setAdapter(adAdapter);
        if (imageViews.length != 1) {
            setIndicatorSelect(0);
            currentPosition = (imageViews.length) * 2;
            viewPager.setCurrentItem(currentPosition);
        }
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //初始化的时候会调用一次的。
                if (imageViews.length != 1) {
                    handler.removeMessages(1);
                    handler.sendEmptyMessageDelayed(1, time);
                } else {
                    viewPager.setCurrentItem(0);
                    viewPager.scrollTo(0, 0);
                }
            }

            @Override
            public void onPageSelected(int position) {
                setIndicatorSelect(position);
                currentPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void setIndicatorSelect(int position) {
        if (imageViews.length != 1) {
            for (int i = 0; i < indicatorImageViews.length; i++) {
                indicatorImageViews[i].setBackgroundResource(0);
                indicatorImageViews[i].setBackgroundResource(R.drawable.round_white_bg);
            }
            indicatorImageViews[position % indicatorImageViews.length].setBackgroundResource(R.drawable.round_red_bg);
        }
    }

    /**
     * @param context
     * @param listArray 实体数据
     */
    public void initViewpager(Context context, List<String> listArray) {
        this.mContext = context;
        if (listArray != null && listArray.size() > 0) {
            if (listArray.size() == 2) {
                imageViews = new ImageView[4];
                for (int i = 0; i < imageViews.length; i++) {
                    ImageView imageView = new ImageView(context);
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    loanImage(imageView, listArray.get(i));//image view的初始化
                    imageViews[i] = imageView;
                }
                isImageTwo = true;
            } else {
                imageViews = new ImageView[listArray.size()];
                for (int i = 0; i < imageViews.length; i++) {
                    ImageView imageView = new ImageView(context);
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    loanImage(imageView, listArray.get(i));//image view的初始化
                    imageViews[i] = imageView;
                }
                isImageTwo = false;
            }
            initView(context);
            initIndicator(context);
            initViewPager();
        }
    }

    /**
     * 异步加载图片
     */
    private void loanImage(ImageView imageView, String s) {
        Glide.with(mContext).load(s).into(imageView);

    }

    public void removeHandleMessage() {
        handler.removeMessages(1);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            currentPosition++;
            viewPager.setCurrentItem(currentPosition, true);
            sendEmptyMessageDelayed(1, time);
        }
    };



    class AdAdapter extends PagerAdapter {

        public AdAdapter() {

        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // container.removeView(imageViews[position % imageViews.length]);
        }

        @Override
        public Object instantiateItem(View container, int position) {
            try {
                imageViews[position % imageViews.length].setOnClickListener(new AdvertisementOnClickListener(position));
                ((ViewGroup) container).addView(imageViews[position % imageViews.length]);
            } catch (Exception e) {
                // TODO: handle exception
            }
            return imageViews[position % imageViews.length];
        }

        private class AdvertisementOnClickListener implements OnClickListener {
            private int position;

            public AdvertisementOnClickListener(int position) {
                this.position = position;
            }

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                mOnBannerOnClickListener.onClick(v, position % imageViews.length);
            }
        }
    }


    public interface OnBannerOnClickListener {
        void onClick(View view, int position);
    }

    private OnBannerOnClickListener mOnBannerOnClickListener;

    public void setBannerClickListener(OnBannerOnClickListener onBannerOnClickListener) {
        if (onBannerOnClickListener != null) {
            mOnBannerOnClickListener = onBannerOnClickListener;
        }
    }

}
