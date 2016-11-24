package com.fansu.yimaomiao.base;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Toast;

import com.blankj.utilcode.utils.EmptyUtils;
import com.blankj.utilcode.utils.LogUtils;
import com.blankj.utilcode.utils.StringUtils;
import com.blankj.utilcode.utils.ToastUtils;
import com.fansu.yimaomiao.Constans;
import com.fansu.yimaomiao.R;
import com.fansu.yimaomiao.customview.kyloading.KyLoadingBuilder;
import com.fansu.yimaomiao.event.BaseEvent;
import com.fansu.yimaomiao.utils.AppManager;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by leo on 16/10/21.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;
    protected KyLoadingBuilder builder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (setRootView() != -1)
            setContentView(setRootView());
        this.mContext = this;

        ButterKnife.bind(this);
        initView(savedInstanceState);
        AppManager.getAppManager().addActivity(this);

        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
        initData();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    protected abstract int setRootView();


    protected abstract void initData();

    protected abstract void initView(Bundle savedInstanceState);

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        AppManager.getAppManager().finishActivity(this);
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BaseEvent event) {

    }


    /**
     * 默认样式
     */
    public void showLoading(boolean iscancle) {
        builder = new KyLoadingBuilder(this);
        builder.setIcon(R.drawable.loading);
        builder.setText("正在加载中...");
        builder.setOutsideTouchable(iscancle);
        builder.setBackTouchable(iscancle);
        builder.show();
    }

    public void showLoading(int message) {
        builder = new KyLoadingBuilder(this);
        builder.setIcon(R.drawable.loading);
        builder.setText(getString(message));
        builder.show();
    }

    public void closeLoading() {
        builder.dismiss();
    }

    /**
     * 自定义loading
     *
     * @param drawabl 图片
     * @param message 内容
     */
    protected void showLoading(int drawabl, String message) {

        builder = new KyLoadingBuilder(this);
        builder.setIcon(drawabl);
        builder.setText(message);
        builder.show();
    }


    /**
     * 显示Toast提示信息
     *
     * @param message 消息文本
     */
    public final void showToast(@NonNull String message) {
        ToastUtils.showShortToast(getApplicationContext(), message);
    }

    /**
     * 显示Toast提示信息
     *
     * @param message 消息文本
     */
    public final void showToast(@NonNull int message) {
        ToastUtils.showShortToast(getApplicationContext(), message);
    }

    /**
     * 发送消息，用于各个组件之间通信，所有的消息事件只能通过MessageEvent发送
     */
    protected final void sendMessage(@NonNull Class<?> obj) {
        // 发布EventBus消息事件
        EventBus.getDefault().post(obj);
    }


    //========================================Intent 模块 ===============================================

    /**
     * 跳转到指定的Activity
     *
     * @param targetActivity 要跳转的目标Activity
     */
    public final void startActivity(@NonNull Class<?> targetActivity) {
        startActivity(new Intent(this, targetActivity));
    }

    /**
     * 跳转到指定的Activity
     *
     * @param flags          intent flags
     * @param targetActivity 要跳转的目标Activity
     */
    protected final void startActivity(int flags, @NonNull Class<?> targetActivity) {
        final Intent intent = new Intent(this, targetActivity);
        intent.setFlags(flags);
        startActivity(new Intent(this, targetActivity));
    }

    /**
     * 跳转到指定的Activity
     *
     * @param data           Activity之间传递数据，Intent的Extra key为Constant.EXTRA_NAME.DATA
     * @param targetActivity 要跳转的目标Activity
     */
    protected final void startActivity(@NonNull Bundle data, @NonNull String key, @NonNull Class<?> targetActivity) {
        final Intent intent = new Intent();
        intent.putExtra(key, data);
        intent.setClass(this, targetActivity);
        startActivity(intent);
    }

    /**
     * 跳转到指定的Activity
     *
     * @param extraName      要传递的值的键名称
     * @param extraValue     要传递的String类型值
     * @param targetActivity 要跳转的目标Activity
     */
    public final void startActivity(@NonNull String extraName, @NonNull String extraValue, @NonNull Class<?> targetActivity) {
        if (StringUtils.isSpace(extraName))
            throw new NullPointerException("传递的值的键名称为null或空");
        final Intent intent = new Intent(getApplicationContext(), targetActivity);
        intent.putExtra(extraName, extraValue);
        startActivity(intent);
    }

    /**
     * 获取通过Intent对象传递过来的参数
     *
     * @param name 参数名称
     * @return 从intent和bundle中都没有取到返回null，否则返回传递过来的参数值
     */
    public final String getStringExtra(@NonNull String name) {
        final Intent intent = getIntent();
        if (EmptyUtils.isNotEmpty(intent)) {
            // 如果intent不为空，则先从intent中取，如果没有再从bundle中取，如果都没有就返回null
            final String extra = intent.getStringExtra(name);
            if (EmptyUtils.isEmpty(extra)) {
                final Bundle data = intent.getExtras();
                if (EmptyUtils.isNotEmpty(data)) {
                    return data.getString(name);
                }
            } else
                return extra;
        }
        return null;
    }

    /**
     * 获取通过Intent对象传递过来的参数
     *
     * @param name         Extra参数名称
     * @param defaultValue 没有在intent或bundle对象中找到时设置的默认值
     * @return 从intent和bundle中都没有取到返回设置的默认值，否则返回传递过来的参数值
     */
    public final String getStringExtra(@NonNull String name, String defaultValue) {
        final String extra = getStringExtra(name);
        if (EmptyUtils.isEmpty(extra))
            return defaultValue;
        return extra;
    }

    /**
     * 获取通过Intent对象传递过来的参数
     *
     * @param name 参数名称
     * @return 从intent和bundle中都没有取到返回null，否则返回传递过来的参数值
     */
    public final boolean getBooleanExtra(@NonNull String name) {
        final Intent intent = getIntent();
        if (EmptyUtils.isNotEmpty(intent)) {
            // 如果intent不为空，则先从intent中取，如果没有再从bundle中取，如果都没有就返回false
            final boolean extra = intent.getBooleanExtra(name, false);
            if (!extra) {
                final Bundle data = intent.getExtras();
                if (EmptyUtils.isNotEmpty(data)) {
                    return data.getBoolean(name);
                }
            }
            return extra;
        }
        return false;
    }

    /**
     * 获取通过Intent对象传递过来的参数
     *
     * @param name         Extra参数名称
     * @param defaultValue 没有在intent或bundle对象中找到时设置的默认值
     * @return 从intent和bundle中都没有取到返回设置的默认值，否则返回传递过来的参数值
     */
    public final boolean getBooleanExtra(@NonNull String name, boolean defaultValue) {
        final boolean extra = getBooleanExtra(name);
        if (extra)
            return defaultValue;
        return extra;
    }

    /**
     * 获取通过Intent对象传递过来的参数
     *
     * @param name 参数名称
     * @return 从intent和bundle中都没有取到返回null，否则返回传递过来的参数值
     */
    public final int getIntExtra(@NonNull String name) {
        final Intent intent = getIntent();
        if (EmptyUtils.isNotEmpty(intent)) {
            // 如果intent不为空，则先从intent中取，如果没有再从bundle中取，如果都没有就返回-1
            final int extra = intent.getIntExtra(name, -1);
            if (extra == -1) {
                final Bundle data = intent.getExtras();
                if (EmptyUtils.isNotEmpty(data)) {
                    return data.getInt(name);
                }
            }
            return extra;
        }
        return -1;
    }

    /**
     * 获取通过Intent对象传递过来的参数
     *
     * @param name         Extra参数名称
     * @param defaultValue 没有在intent或bundle对象中找到时设置的默认值
     * @return 从intent和bundle中都没有取到返回设置的默认值，否则返回传递过来的参数值
     */
    public final int getIntExtra(@NonNull String name, int defaultValue) {
        final int extra = getIntExtra(name);
        if (extra == -1)
            return defaultValue;
        return extra;
    }

    public final short getShortExtra(@NonNull String name) {
        final Intent intent = getIntent();
        if (EmptyUtils.isEmpty(intent))
            return 0;
        return intent.getShortExtra(name, Short.valueOf("0"));
    }

    public final int getShortExtra(@NonNull String name, short defaultValue) {
        final Intent intent = getIntent();
        if (EmptyUtils.isEmpty(intent))
            return 0;
        return intent.getShortExtra(name, defaultValue);
    }

    public final long getLongExtra(@NonNull String name) {
        final Intent intent = getIntent();
        if (EmptyUtils.isEmpty(intent))
            return 0L;
        return intent.getLongExtra(name, 0L);
    }

    public final long getLongExtra(@NonNull String name, long defaultValue) {
        final Intent intent = getIntent();
        if (EmptyUtils.isEmpty(intent))
            return 0L;
        return intent.getLongExtra(name, defaultValue);
    }

    /**
     * 获取通过Intent对象传递过来的参数
     *
     * @param name 参数名称
     * @return 从intent和bundle中都没有取到返回0.0f，否则返回传递过来的参数值
     */
    public final float getFloatExtra(@NonNull String name) {
        final Intent intent = getIntent();
        if (EmptyUtils.isNotEmpty(intent)) {
            // 如果intent不为空，则先从intent中取，如果没有再从bundle中取，如果都没有就返回0.0f
            final float extra = intent.getFloatExtra(name, 0.0f);
            if (extra == 0.0f) {
                final Bundle data = intent.getExtras();
                if (EmptyUtils.isNotEmpty(data)) {
                    return data.getFloat(name);
                }
            }
            return extra;
        }
        return 0.0f;
    }

    /**
     * 获取通过Intent对象传递过来的参数
     *
     * @param name         Extra参数名称
     * @param defaultValue 没有在intent或bundle对象中找到时设置的默认值
     * @return 从intent和bundle中都没有取到返回设置的默认值，否则返回传递过来的参数值
     */
    public final float getFloatExtra(@NonNull String name, float defaultValue) {
        final float extra = getFloatExtra(name);
        if (extra == 0.0f)
            return defaultValue;
        return extra;
    }

    /**
     * 获取通过Intent对象传递过来的参数
     *
     * @param name 参数名称
     * @return 从intent和bundle中都没有取到返回0.0，否则返回传递过来的参数值
     */
    public final double getDoubleExtra(@NonNull String name) {
        final Intent intent = getIntent();
        if (EmptyUtils.isNotEmpty(intent)) {
            // 如果intent不为空，则先从intent中取，如果没有再从bundle中取，如果都没有就返回0.0
            final double extra = intent.getDoubleExtra(name, 0.0);
            if (extra == 0.0) {
                final Bundle data = intent.getExtras();
                if (EmptyUtils.isNotEmpty(data)) {
                    return data.getDouble(name);
                }
            }
            return extra;
        }
        return 0.0;
    }

    /**
     * 获取通过Intent对象传递过来的参数
     *
     * @param name         Extra参数名称
     * @param defaultValue 没有在intent或bundle对象中找到时设置的默认值
     * @return 从intent和bundle中都没有取到返回0.0，否则返回传递过来的参数值
     */
    public final double getDoubleExtra(@NonNull String name, double defaultValue) {
        final double extra = getDoubleExtra(name);
        if (extra == 0.0)
            return defaultValue;
        return extra;
    }

    public final Parcelable getParcelableExtra(@NonNull String name) {
        final Intent intent = getIntent();
        if (EmptyUtils.isEmpty(intent))
            return null;
        return intent.getParcelableExtra(name);
    }

    public final Parcelable[] getParcelableArrayExtra(@NonNull String name) {
        final Intent intent = getIntent();
        if (EmptyUtils.isEmpty(intent))
            return null;
        return intent.getParcelableArrayExtra(name);
    }

    public final ArrayList<Parcelable> getParcelableArrayListExtra(@NonNull String name) {
        final Intent intent = getIntent();
        if (EmptyUtils.isEmpty(intent))
            return null;
        return intent.getParcelableArrayListExtra(name);
    }

    public final Serializable getSerializableExtra(@NonNull String name) {
        final Intent intent = getIntent();
        if (EmptyUtils.isEmpty(intent))
            return null;
        return intent.getSerializableExtra(name);
    }
    //========================================Intent 模块 ===============================================


    //====================================二维码模块=============================

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            /**
             * 扫描二维码返回
             */
            case Constans.ERWEIMA_CODE:
                if (null != data) {
                    Bundle bundle = data.getExtras();
                    if (bundle == null) {
                        return;
                    }
                    if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                        String result = bundle.getString(CodeUtils.RESULT_STRING);
                        checkErweima(result);
                        LogUtils.d("解析结果:" + result);
                    } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                        Toast.makeText(mContext, "解析二维码失败", Toast.LENGTH_LONG).show();
                    }
                }
                break;
            /**
             * 二维码图片
             */
            case Constans.ERWEIMA_IMAGE_CODE:
                if (data != null) {
                    Uri uri = data.getData();
                    String[] proj = {MediaStore.Images.Media.DATA};
                    String res = null;
                    try {
                        Cursor cursor = getContentResolver().query(uri, proj, null, null, null);
                        if (cursor.moveToFirst()) {
                            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                            res = cursor.getString(column_index);
                        }
                        cursor.close();
                        CodeUtils.analyzeBitmap(res, new CodeUtils.AnalyzeCallback() {
                            @Override
                            public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                                checkErweimaPhoto(mBitmap, result);
                                LogUtils.d("解析结果:" + result);
                            }

                            @Override
                            public void onAnalyzeFailed() {
                                Toast.makeText(mContext, "解析二维码失败", Toast.LENGTH_LONG).show();
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    /**
     * 二维码
     */
    public void checkErweima(String resule) {

    }

    /**
     * 二维码相册
     */
    public void checkErweimaPhoto(Bitmap bitmap, String resule) {

    }


    //====================================二维码模块=============================


    public void dimActivity(Dialog dialog, float dimAmount) {
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.dimAmount = dimAmount;
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

}
