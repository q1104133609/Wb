package com.fansu.yimaomiao.view.activity;

import android.Manifest;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.utils.LogUtils;
import com.fansu.yimaomiao.R;
import com.fansu.yimaomiao.base.BaseActivity;
import com.fansu.yimaomiao.base.mvp.BaseView;
import com.fansu.yimaomiao.data.entity.MovieEntity;
import com.fansu.yimaomiao.permission.MPermission;
import com.fansu.yimaomiao.permission.annotation.OnMPermissionDenied;
import com.fansu.yimaomiao.permission.annotation.OnMPermissionGranted;
import com.fansu.yimaomiao.view.fragment.main.ChatFragment;
import com.fansu.yimaomiao.view.fragment.main.HomeFragment;
import com.fansu.yimaomiao.view.fragment.main.MineFragment;
import com.fansu.yimaomiao.view.fragment.main.NearFragment;
import com.fansu.yimaomiao.view.fragment.main.ShowFragmet;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;


/**
 * 主页面
 */
public class MainActivity extends BaseActivity implements BaseView<MovieEntity>, View.OnClickListener {

    private final int BASIC_PERMISSION_REQUEST_CODE = 100;
    private HomeFragment mHomeFragment;
    private ShowFragmet mShowFragmet;
    private ChatFragment mChatFragment;
    private NearFragment mNearFragment;
    private MineFragment mMineFragment;
    @BindView(R.id.tab1)
    TextView mTab1;
    @BindView(R.id.tab2)
    TextView mTab2;
    @BindView(R.id.tab3)
    TextView mTab3;
    @BindView(R.id.tab4)
    TextView mTab4;
    @BindView(R.id.tab5)
    TextView mTab5;

    @Override
    protected int setRootView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        requestBasicPermission();
        mTab1.setOnClickListener(this);
        mTab2.setOnClickListener(this);
        mTab3.setOnClickListener(this);
        mTab4.setOnClickListener(this);
        mTab5.setOnClickListener(this);
        setSelect(0);
    }


    private void setSelect(int i) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction trs = fm.beginTransaction();
        hideFragment(trs);

        switch (i) {
            case 0:
                if (mHomeFragment == null) {
                    mHomeFragment = new HomeFragment();
                    trs.add(R.id.framelayout, mHomeFragment);
                } else {
                    trs.show(mHomeFragment);
                }
                break;
            case 1:
                if (mShowFragmet == null) {
                    mShowFragmet = new ShowFragmet();
                    trs.add(R.id.framelayout, mShowFragmet);
                } else {
                    trs.show(mShowFragmet);
                }
                break;

            case 2:
                if (mChatFragment == null) {
                    mChatFragment = new ChatFragment();
                    trs.add(R.id.framelayout, mChatFragment);
                } else {
                    trs.show(mChatFragment);
                }
                break;
            case 3:
                if (mNearFragment == null) {
                    mNearFragment = new NearFragment();
                    trs.add(R.id.framelayout, mNearFragment);
                } else {
                    trs.show(mNearFragment);
                }
                break;
            case 4:
                if (mMineFragment == null) {
                    mMineFragment = new MineFragment();
                    trs.add(R.id.framelayout, mMineFragment);
                } else {
                    trs.show(mMineFragment);
                }
                break;

        }
        trs.commit();
    }


    /*
   * 隐藏所有的Fragment
   */
    private void hideFragment(FragmentTransaction trs) {

        if (mHomeFragment != null) {
            trs.hide(mHomeFragment);
        }
        if (mShowFragmet != null) {
            trs.hide(mShowFragmet);
        }
        if (mChatFragment != null) {
            trs.hide(mChatFragment);
        }
        if (mNearFragment != null) {
            trs.hide(mNearFragment);
        }
        if (mMineFragment != null) {
            trs.hide(mMineFragment);
        }

    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        switch (i) {
            case R.id.tab1:
                mTab1.setCompoundDrawables(null, setDrawable(R.mipmap.tab_home_pressed), null, null);
                mTab2.setCompoundDrawables(null, setDrawable(R.mipmap.tab_announced_default), null, null);
                mTab3.setCompoundDrawables(null, setDrawable(R.mipmap.tab_discover_default), null, null);
                mTab4.setCompoundDrawables(null, setDrawable(R.mipmap.tab_camera_default), null, null);
                mTab5.setCompoundDrawables(null, setDrawable(R.mipmap.tab_user_default), null, null);
                mTab1.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                mTab2.setTextColor(getResources().getColor(R.color.tab_text_color));
                mTab3.setTextColor(getResources().getColor(R.color.tab_text_color));
                mTab4.setTextColor(getResources().getColor(R.color.tab_text_color));
                mTab5.setTextColor(getResources().getColor(R.color.tab_text_color));
                setSelect(0);
                break;
            case R.id.tab2:
                mTab1.setCompoundDrawables(null, setDrawable(R.mipmap.tab_home_default), null, null);
                mTab2.setCompoundDrawables(null, setDrawable(R.mipmap.tab_announced_pressed), null, null);
                mTab3.setCompoundDrawables(null, setDrawable(R.mipmap.tab_discover_default), null, null);
                mTab4.setCompoundDrawables(null, setDrawable(R.mipmap.tab_camera_default), null, null);
                mTab5.setCompoundDrawables(null, setDrawable(R.mipmap.tab_user_default), null, null);
                mTab2.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                mTab1.setTextColor(getResources().getColor(R.color.tab_text_color));
                mTab3.setTextColor(getResources().getColor(R.color.tab_text_color));
                mTab4.setTextColor(getResources().getColor(R.color.tab_text_color));
                mTab5.setTextColor(getResources().getColor(R.color.tab_text_color));
                setSelect(1);
                break;
            case R.id.tab3:
                mTab1.setCompoundDrawables(null, setDrawable(R.mipmap.tab_home_default), null, null);
                mTab2.setCompoundDrawables(null, setDrawable(R.mipmap.tab_announced_default), null, null);
                mTab3.setCompoundDrawables(null, setDrawable(R.mipmap.tab_discover_pressed), null, null);
                mTab4.setCompoundDrawables(null, setDrawable(R.mipmap.tab_camera_default), null, null);
                mTab5.setCompoundDrawables(null, setDrawable(R.mipmap.tab_user_default), null, null);
                mTab3.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                mTab2.setTextColor(getResources().getColor(R.color.tab_text_color));
                mTab1.setTextColor(getResources().getColor(R.color.tab_text_color));
                mTab4.setTextColor(getResources().getColor(R.color.tab_text_color));
                mTab5.setTextColor(getResources().getColor(R.color.tab_text_color));
                setSelect(2);
                break;
            case R.id.tab4:
                mTab1.setCompoundDrawables(null, setDrawable(R.mipmap.tab_home_default), null, null);
                mTab2.setCompoundDrawables(null, setDrawable(R.mipmap.tab_announced_default), null, null);
                mTab3.setCompoundDrawables(null, setDrawable(R.mipmap.tab_discover_default), null, null);
                mTab4.setCompoundDrawables(null, setDrawable(R.mipmap.tab_camera_pressed), null, null);
                mTab5.setCompoundDrawables(null, setDrawable(R.mipmap.tab_user_default), null, null);
                mTab4.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                mTab2.setTextColor(getResources().getColor(R.color.tab_text_color));
                mTab3.setTextColor(getResources().getColor(R.color.tab_text_color));
                mTab1.setTextColor(getResources().getColor(R.color.tab_text_color));
                mTab5.setTextColor(getResources().getColor(R.color.tab_text_color));
                setSelect(3);
                break;
            case R.id.tab5:
                mTab1.setCompoundDrawables(null, setDrawable(R.mipmap.tab_home_default), null, null);
                mTab2.setCompoundDrawables(null, setDrawable(R.mipmap.tab_announced_default), null, null);
                mTab3.setCompoundDrawables(null, setDrawable(R.mipmap.tab_discover_default), null, null);
                mTab4.setCompoundDrawables(null, setDrawable(R.mipmap.tab_camera_default), null, null);
                mTab5.setCompoundDrawables(null, setDrawable(R.mipmap.tab_user_pressed), null, null);
                mTab4.setTextColor(getResources().getColor(R.color.tab_text_color));
                mTab2.setTextColor(getResources().getColor(R.color.tab_text_color));
                mTab3.setTextColor(getResources().getColor(R.color.tab_text_color));
                mTab1.setTextColor(getResources().getColor(R.color.tab_text_color));
                mTab5.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                setSelect(4);
                break;
        }
    }


    public Drawable setDrawable(int resource) {
        Drawable drawable = mContext.getResources().getDrawable(resource);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        return drawable;
    }


    /**
     * 基本权限管理
     */
    private void requestBasicPermission() {
        MPermission.with(MainActivity.this)
                .addRequestCode(BASIC_PERMISSION_REQUEST_CODE)
                .permissions(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.READ_CALL_LOG,
                        Manifest.permission.WRITE_CALL_LOG,
                        Manifest.permission.ADD_VOICEMAIL,
                        Manifest.permission.USE_SIP,
                        Manifest.permission.PROCESS_OUTGOING_CALLS,


                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                )
                .request();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        MPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @OnMPermissionGranted(BASIC_PERMISSION_REQUEST_CODE)
    public void onBasicPermissionSuccess() {
        LogUtils.d("授权成功~");
    }

    @OnMPermissionDenied(BASIC_PERMISSION_REQUEST_CODE)
    public void onBasicPermissionFailed() {
        LogUtils.d("授权失败~");
    }


    @Override
    public void isSuccess(MovieEntity bean) {
    }


    @Override
    public void isFailure(String msg) {
        Log.e("huangbo", msg);
    }

    @Override
    public void isLoading() {

    }

    @Override
    public void hideLoading() {

    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click();
        }
        return false;
    }

    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (!isExit) {
            isExit = true;
            Toast.makeText(this, getString(R.string.is_exit), Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false;
                }
            }, 2000);

        } else {
            finish();
            System.exit(0);
        }
    }
}
