package com.fansu.yimaomiao.view.activity.login;

import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.RegexUtils;
import com.fansu.yimaomiao.R;
import com.fansu.yimaomiao.base.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by leo on 16/11/4.
 * 找回密码界面
 */

public class RetrievePwdActvity extends BaseActivity {
    @BindView(R.id.edit_retrieve_pwd)
    EditText mEdit_Phone;
    @BindView(R.id.edit_retrieve_veri)
    EditText mEdit_veri;
    @BindView(R.id.tv_veri)
    TextView mTv_ver;
    @BindView(R.id.linear_pwd)
    LinearLayout mLinear_pwd;
    @BindView(R.id.linear_phone)
    LinearLayout mLinear_phone;
    @BindView(R.id.linear_yanzm)
    LinearLayout mLinear_yanzm;
    @BindView(R.id.checkbox_new_pwd)
    CheckBox mCheckBox_new_pwd;
    @BindView(R.id.edit_new_password)
    EditText mEditPwd;
    private Timer mTimer;
    private boolean isVerintion = false;

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
        if (!isVerintion && !RegexUtils.isMobileExact(mEdit_Phone.getText().toString())) {
            showToast(R.string.right_phone);
        } else if (!isVerintion) {
            isVerintion = true;
            mTimer = new Timer();
            mTimer.schedule(new TimerTask() {
                int i = 60;

                @Override
                public void run() {
                    runOnUiThread(new TimerTask() {
                        @Override
                        public void run() {
                            i -= 1;
                            if (i == 0) {
                                mTv_ver.setText(getString(R.string.send_again));
                                isVerintion = false;
                                mTimer.cancel();
                            } else {
                                mTv_ver.setText(i + getString(R.string.time_get_ver));
                            }
                        }
                    });
                }
            }, 0, 1000);
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


    public void onNext(View view){
        if (!((TextView)view).getText().toString().equals(getString(R.string.right))) {
            if (RegexUtils.isMobileExact(mEdit_Phone.getText().toString())) {
                if (TextUtils.isEmpty(mEdit_veri.getText().toString())){
                    showToast(R.string.ver_empty);
                }else {
                    if (mTimer != null) {
                        mTimer.cancel();
                    }
                    mLinear_phone.setVisibility(View.GONE);
                    mLinear_pwd.setVisibility(View.VISIBLE);
                    mLinear_yanzm.setVisibility(View.GONE);
                    ((TextView) view).setText(R.string.right);
                }
            }else{
                showToast(R.string.right_phone);
            }
        }else{
            if (mEditPwd.getText().toString().length()<6) {
                showToast(R.string.right_pwd);
            }else {
                finish();
            }
        }
    }


}
