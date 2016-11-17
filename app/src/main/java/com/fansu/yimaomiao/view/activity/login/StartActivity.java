package com.fansu.yimaomiao.view.activity.login;

import android.os.Bundle;

import com.blankj.utilcode.utils.NetworkUtils;
import com.easemob.EMCallBack;
import com.easemob.chat.EMChatManager;
import com.fansu.yimaomiao.Constans;
import com.fansu.yimaomiao.base.BaseActivity;
import com.fansu.yimaomiao.utils.SharedPreferencesUtils;
import com.fansu.yimaomiao.view.activity.MainActivity;

/**
 * Created by leo on 16/11/1.
 * 开始界面
 */

public class StartActivity extends BaseActivity {
    @Override
    protected int setRootView() {
        return -1;
    }

    @Override
    protected void initData() {

        /**
         * 不是第一次登錄
         */
        if (SharedPreferencesUtils.getBoolean(mContext, Constans.IS_FIRST, false)) {
            if (NetworkUtils.isAvailable(mContext)) {
                loginEase();
            }else{
                showToast("请检查网络是否连接~");
                startActivity(MainActivity.class);
                finish();
            }
        } else {
            startActivity(CarouselActivity.class);
            finish();
        }

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }


    private void loginEase() {
        EMChatManager.getInstance().login("test", "111111", new EMCallBack() {

            @Override
            public void onSuccess() {
                runOnUiThread(() -> {
                    startActivity(MainActivity.class);
                    finish();
                });

            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String error) {
                runOnUiThread(() -> {
                    startActivity(LoginActivity.class);
                    finish();
                });
            }
        });

    }
}
