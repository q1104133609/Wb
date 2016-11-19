package com.fansu.yimaomiao.view.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.fansu.yimaomiao.customview.ClearableEditTextWithIcon;
import com.fansu.yimaomiao.data.entity.RegisterBean;
import com.fansu.yimaomiao.data.presenter.RegisterPersenter;
import com.fansu.yimaomiao.data.presenter.YzmPersenter;
import com.fansu.yimaomiao.inter.YzmListener;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by leo on 16/11/1.
 * 注册界面
 */

public class RegisterActivity extends MvpActivity<RegisterPersenter> implements BaseView<Result<RegisterBean>>, YzmListener<Result> {

    @BindView(R.id.tv_register_one)
    TextView mTv_one;
    @BindView(R.id.tv_register_three)
    TextView mTv_three;
    @BindView(R.id.btn_register)
    TextView mRegister;
    @BindView(R.id.edit_login_account)
    ClearableEditTextWithIcon edit_login_account;
    @BindView(R.id.check_is_red)
    CheckBox mCheck;
    @BindView(R.id.tv_send_message)
    TextView tv_send_message;
    @BindView(R.id.linear_Verification)
    LinearLayout linear_Verification;
    @BindView(R.id.linear_phone)
    LinearLayout linear_phone;
    @BindView(R.id.linear_pwd)
    LinearLayout linear_pwd;
    @BindView(R.id.checkbox_pwd)
    CheckBox mChecPwd;
    @BindView(R.id.edit_register_password)
    EditText mEditPwd;
    @BindView(R.id.linear_xieyi)
    LinearLayout linear_xieyi;
    @BindView(R.id.edit_login_yanzm)
    EditText mYanZm;
    @BindView(R.id.tv_yanzm_again)
    TextView mYanzm_again;
    //倒计时timer
    private Timer mTimer;
    //倒计时时间
    private int mTimeCont = 60;
    /**
     * 注册
     */
    private int mStep = 1;
    private RegisterPersenter mRegisterPersenter;


    @Override
    protected int setRootView() {
        return R.layout.activity_register;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mChecPwd.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                mEditPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            } else {
                mEditPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
            mEditPwd.setSelection(mEditPwd.getText().length());
        });
    }


    @OnClick(R.id.btn_register)
    public void register() {
        switch (mStep) {
            case 1:
                if (!mCheck.isChecked()) {
                    showToast(R.string.agree_xieyi);
                } else if (RegexUtils.isMobileExact(edit_login_account.getText().toString())) {
                    showLoading(false);
                    YzmPersenter.getYZM(edit_login_account.getText().toString(), this);
                } else {
                    showToast(R.string.right_phone);
                }
                break;
            case 2:
                if (TextUtils.isEmpty(mEditPwd.getText().toString())) {
                    showToast("密码不能为空~");
                } else if (mEditPwd.getText().toString().length() < 6) {
                    showToast("密码长度不能小于6位~");
                } else if (mYanZm.getText().toString().length() != 6 || TextUtils.isEmpty(mYanZm.getText().toString())) {
                    showToast("请输入正确的验证码~");
                } else {
                    mRegisterPersenter.toRegisterPersenter(edit_login_account.getText().toString(), mEditPwd.getText().toString(), mYanZm.getText().toString());
                }
                break;
        }
    }


    /**
     * 返回
     *
     * @param view
     */
    public void back(View view) {
        finish();
    }

    /**
     * 协议
     */
    @OnClick(R.id.tv_xieyi)
    public void xieyi() {

    }

    @Override
    protected RegisterPersenter createPresenter() {
        return mRegisterPersenter = new RegisterPersenter(this);
    }

    @Override
    public void isSuccess(Result<RegisterBean> bean) {
        closeLoading();
        if (bean.getCode() == Constans.IS_REGISTER) {
            showToast("该账号已经注册~");
        } else if (bean.getCode() == Constans.SERVICE_SUCCESS) {
            showToast("注册成功~");
            Intent intent = new Intent(mContext, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            showToast("注册失败请重试~");
        }
    }

    @Override
    public void isFailure(String msg) {
        closeLoading();
        showToast("注册失败，请重试~");

    }

    @Override
    public void isLoading() {
        showLoading(false);
    }

    @Override
    public void hideLoading() {
        closeLoading();

    }

    public void startTime() {
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                mTimeCont -= 1;
                handler.sendEmptyMessage(0);
                if (mTimeCont == 0) {
                    mTimer.cancel();
                }
            }
        }, 0, 1000);
    }

    /**
     * 计时器动态修改
     */
    Handler handler = new Handler() {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            if (msg.what == 0) {
                if (mTimeCont == 0)
                    mYanzm_again.setText("重新获取");
                else
                    mYanzm_again.setText(String.valueOf(mTimeCont) + "s后可重试");
            }
        }
    };

    /**
     * 验证码重新获取
     */
    @OnClick(R.id.tv_yanzm_again)
    public void yzm_again() {
        if (mTimeCont == 0) {
            showLoading(false);
            YzmPersenter.getYZM(edit_login_account.getText().toString(), this);
        }
    }

    /**
     * 请求验证码成功
     *
     * @param result
     */
    @Override
    public void isRight(Result result) {
        closeLoading();
        if (result.code == Constans.SERVICE_SUCCESS) {
            mTv_three.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            mRegister.setText(getResources().getText(R.string.comit_yanzhen));
            mStep = 2;
            mRegister.setText(getResources().getText(R.string.commit));
            tv_send_message.setVisibility(View.VISIBLE);
            tv_send_message.setText("短信验证码已发送到" + edit_login_account.getText().toString());
            linear_Verification.setVisibility(View.VISIBLE);
            linear_phone.setVisibility(View.GONE);
            linear_xieyi.setVisibility(View.GONE);
            linear_pwd.setVisibility(View.VISIBLE);
            mTimeCont = 60;
            startTime();
        } else {
            showToast("请求验证码失败，重试~");
        }
    }

    /**
     * 验证码失败
     *
     * @param throwable
     */
    @Override
    public void isError(Throwable throwable) {
        closeLoading();
        showToast("请求验证码失败，重试~");

    }
}
