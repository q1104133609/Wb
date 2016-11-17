package com.fansu.yimaomiao.view.activity.mine;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


import com.fansu.yimaomiao.R;
import com.fansu.yimaomiao.base.BaseActivity;
import com.fansu.yimaomiao.customview.ShareDialog;
import com.fansu.yimaomiao.utils.Utils;

import butterknife.BindView;
import cn.sharesdk.framework.ShareSDK;

/**
 * Created by leo on 16/11/6.
 * 赚积分页面
 */

public class EarnPointsActivity extends BaseActivity {
    @BindView(R.id.image)
    ImageView mImageView;
    private ShareDialog sharedDialog;
    @Override
    protected int setRootView() {
        return R.layout.activity_earn_point;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ShareSDK.initSDK(this);
        mImageView.setImageBitmap(Utils.geterweimaLogo("你们都是呆逼",this));
        mImageView.setOnLongClickListener(v -> {
            showToast(Utils.erweimaJiexi(((BitmapDrawable) mImageView.getDrawable()).getBitmap()));
            return false;
        });



    }


    private void showDialog() {
        if (sharedDialog == null) {
            sharedDialog = new ShareDialog(this, R.style.transparentFrameWindowStyle);
            sharedDialog.setTitleUrl("https://www.baidu.com/s?ie=UTF-8&wd=ShareParams");
            sharedDialog.setImageUrl("https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=4113940709,174972608&fm=58");
            sharedDialog.setTitle("title");
            sharedDialog.setText("message");
        }
        sharedDialog.show();
        dimActivity(sharedDialog, 0.6f);
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        ShareSDK.stopSDK(this);
    }

    public void back(View view) {
       showDialog();

    }

}
