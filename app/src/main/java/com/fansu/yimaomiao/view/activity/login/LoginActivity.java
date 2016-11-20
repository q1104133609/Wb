package com.fansu.yimaomiao.view.activity.login;

import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.blankj.utilcode.utils.RegexUtils;
import com.fansu.yimaomiao.R;
import com.fansu.yimaomiao.base.Result;
import com.fansu.yimaomiao.base.mvp.BaseView;
import com.fansu.yimaomiao.base.mvp.MvpActivity;
import com.fansu.yimaomiao.customview.ClearableEditTextWithIcon;
import com.fansu.yimaomiao.entity.LoginBean;
import com.fansu.yimaomiao.presenter.login.LoginPresenter;
import com.fansu.yimaomiao.event.LoginEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by leo on 16/11/1.
 * 登录界面
 */

public class LoginActivity extends MvpActivity<LoginPresenter> implements BaseView<Result<LoginBean>> {
    @BindView(R.id.checkbox_pwd)
    CheckBox mChecPwd;
    @BindView(R.id.edit_login_password)
    EditText mEditPwd;
    @BindView(R.id.check_save_pwd)
    CheckBox mSavePwd;
    @BindView(R.id.edit_login_account)
    ClearableEditTextWithIcon mEditAccount;
    LoginPresenter loginPresenter;
    /**
     * 默认保存
     */
    private boolean isSavePwd = true;

    @Override
    protected int setRootView() {
        return R.layout.activity_login;
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
        mSavePwd.setOnCheckedChangeListener((buttonView, isChecked) ->
                isSavePwd = isChecked
        );

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
     * 忘记密码
     */
    @OnClick(R.id.tv_rember_pwd)
    public void remberPwd() {
        startActivity(RetrievePwdActvity.class);
    }

    /**
     * 登录
     */
    @OnClick(R.id.btn_login)
    public void login() {
        if (TextUtils.isEmpty(mEditAccount.getText().toString())) {
            showToast(R.string.account_empty);
        } else if (!RegexUtils.isMobileExact(mEditAccount.getText().toString())) {
            showToast(R.string.right_phone);
        } else if (TextUtils.isEmpty(mEditPwd.getText().toString())) {
            showToast(R.string.pwd_empty);
        } else {
            loginPresenter.toLogin(mEditAccount.getText().toString(), mEditPwd.getText().toString());
        }

    }
    /**
     * 注册
     */
    /**
     * 登录
     */
    @OnClick(R.id.btn_register)
    public void register() {
        startActivity(RegisterActivity.class);
    }


    @Override
    public void isSuccess(Result<LoginBean> bean) {
        closeLoading();
        EventBus.getDefault().post(new LoginEvent());
        showToast("登录成功~");
        finish();
    }

    @Override
    public void isFailure(String msg) {
        closeLoading();
        showToast("登录失败~");
    }

    @Override
    public void isLoading() {
        showLoading(R.string.logining);
    }

    @Override
    public void hideLoading() {
        closeLoading();
    }

    @Override
    protected LoginPresenter createPresenter() {
        return loginPresenter = new LoginPresenter(this);
    }
}
