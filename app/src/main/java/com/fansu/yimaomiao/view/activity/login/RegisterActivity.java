package com.fansu.yimaomiao.view.activity.login;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.RegexUtils;
import com.fansu.yimaomiao.R;
import com.fansu.yimaomiao.base.BaseActivity;
import com.fansu.yimaomiao.customview.ClearableEditTextWithIcon;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by leo on 16/11/1.
 * 注册界面
 */

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.tv_register_one)
    TextView mTv_one;
    @BindView(R.id.tv_register_two)
    TextView mTv_two;
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
    private String mUserNmae;
    /**
     * 注册揍
     */
    private int mStep = 1;


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
                    mTv_two.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    mRegister.setText(getResources().getText(R.string.comit_yanzhen));
                    mStep = 2;
                    tv_send_message.setVisibility(View.VISIBLE);
                    tv_send_message.setText("短信验证码已发送到" + edit_login_account.getText().toString());
                    linear_Verification.setVisibility(View.VISIBLE);
                    linear_phone.setVisibility(View.GONE);
                } else {
                    showToast(R.string.right_phone);
                }
                break;
            case 2:
                mTv_three.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                mStep = 3;
                linear_pwd.setVisibility(View.VISIBLE);
                tv_send_message.setVisibility(View.GONE);
                linear_Verification.setVisibility(View.GONE);
                mRegister.setText(getResources().getText(R.string.commit));
                mUserNmae = edit_login_account.getText().toString();
                linear_xieyi.setVisibility(View.GONE);
                break;
            case 3:
                mStep = 0;
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
}
