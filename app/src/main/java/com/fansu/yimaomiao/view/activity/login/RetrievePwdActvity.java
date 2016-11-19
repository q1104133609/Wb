package com.fansu.yimaomiao.view.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.RegexUtils;
import com.fansu.yimaomiao.Constans;
import com.fansu.yimaomiao.R;
import com.fansu.yimaomiao.base.Result;
import com.fansu.yimaomiao.base.mvp.BaseView;
import com.fansu.yimaomiao.base.mvp.MvpActivity;
import com.fansu.yimaomiao.data.presenter.RetrievePwdPersenter;
import com.fansu.yimaomiao.data.presenter.YzmPersenter;
import com.fansu.yimaomiao.inter.YzmListener;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by leo on 16/11/4.
 * 找回密码界面
 */

public class RetrievePwdActvity extends MvpActivity<RetrievePwdPersenter> implements BaseView<Result>, YzmListener<Result> {
    @BindView(R.id.edit_retrieve_pwd)
    EditText mEdit_Phone;
    @BindView(R.id.edit_retrieve_veri)
    EditText mEdit_veri;
    @BindView(R.id.tv_veri)
    TextView mTv_ver;
    @BindView(R.id.linear_phone)
    LinearLayout mLinear_phone;
    @BindView(R.id.linear_yanzm)
    LinearLayout mLinear_yanzm;
    @BindView(R.id.checkbox_new_pwd)
    CheckBox mCheckBox_new_pwd;
    @BindView(R.id.edit_new_password)
    EditText mEditPwd;
    private Timer mTimer;
    private RetrievePwdPersenter mRegisterPersenter;
    private int mTimeCont = 0;


    @Override
    protected int setRootView() {
        return R.layout.activity_retrievepwd;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mCheckBox_new_pwd.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                mEditPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            } else {
                mEditPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
            mEditPwd.setSelection(mEditPwd.getText().length());
        });
    }

    /**
     * 获取验证码
     */
    @OnClick(R.id.tv_veri)
    public void get_veri() {
        if (!RegexUtils.isMobileExact(mEdit_Phone.getText().toString())) {
            showToast(R.string.right_phone);
        } else {
            if (mTimeCont == 0) {
                showLoading(false);
                YzmPersenter.getYZM(mEdit_Phone.getText().toString(), this);
            }
        }
    }


    /**
     * 检查验证时间
     */
    public void checkTimer() {
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new TimerTask() {
                    @Override
                    public void run() {
                        mTimeCont -= 1;
                        if (mTimeCont == 0) {
                            mTv_ver.setText(getString(R.string.send_again));
                            mTimer.cancel();
                        } else {
                            mTv_ver.setText(mTimeCont + getString(R.string.time_get_ver));
                        }
                    }
                });
            }
        }, 0, 1000);
    }


    /**
     * 返回
     *
     * @param view
     */
    public void back(View view) {
        finish();
    }


    public void onNext(View view) {
            if (RegexUtils.isMobileExact(mEdit_Phone.getText().toString())) {
                if (TextUtils.isEmpty(mEdit_veri.getText().toString())) {
                    showToast(R.string.ver_empty);
                } else {
                    if (TextUtils.isEmpty(mEditPwd.getText().toString()) || mEditPwd.getText().length() < 6) {
                        showToast("请输入正确的新密码~");
                    } else {
                        mRegisterPersenter.toRegisterPersenter(mEdit_Phone.getText().toString(), mEditPwd.getText().toString(), mEdit_veri.getText().toString());
                    }
                }
            } else {
                showToast(R.string.right_phone);
            }
    }


    @Override
    public void isSuccess(Result bean) {
        closeLoading();
        if (mTimer != null) {
            mTimer.cancel();
        }
        if (bean.getCode() == Constans.SERVICE_SUCCESS) {
            showToast("修改密码成功~");
            finish();
        }else{
            showToast("修改密码失败，请重试~");
        }

    }

    @Override
    public void isFailure(String msg) {
        closeLoading();
        showToast("修改密码失败，请重试~");
    }

    @Override
    public void isLoading() {
        showLoading(false);

    }

    @Override
    public void hideLoading() {
        closeLoading();

    }

    @Override
    protected RetrievePwdPersenter createPresenter() {
        return mRegisterPersenter = new RetrievePwdPersenter(this);
    }

    @Override
    public void isRight(Result result) {
        closeLoading();
        if (result.getCode() == Constans.SERVICE_SUCCESS) {
            mTimeCont = 60;
            checkTimer();
        }else{
            showToast("获取验证码失败,请重新获取~");
        }
    }

    @Override
    public void isError(Throwable throwable) {
        closeLoading();
        showToast("获取验证码失败,请重新获取~");
    }
}
