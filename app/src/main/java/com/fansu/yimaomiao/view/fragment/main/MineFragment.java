package com.fansu.yimaomiao.view.fragment.main;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;


import com.fansu.yimaomiao.Constans;
import com.fansu.yimaomiao.R;
import com.fansu.yimaomiao.base.BaseFragment;
import com.fansu.yimaomiao.customview.BaseDialog;
import com.fansu.yimaomiao.event.LoginEvent;
import com.fansu.yimaomiao.inter.OnDialogClickListener;
import com.fansu.yimaomiao.utils.SharedPreferencesUtils;
import com.fansu.yimaomiao.utils.Utils;
import com.fansu.yimaomiao.view.activity.login.LoginActivity;
import com.fansu.yimaomiao.view.activity.login.RegisterActivity;
import com.fansu.yimaomiao.view.activity.mine.EarnPointsActivity;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by leo on 16/11/1.
 */

public class MineFragment extends BaseFragment {
    @BindView(R.id.linear_no_login)
    LinearLayout linear_no_login;
    @BindView(R.id.linear_is_login)
    LinearLayout linear_is_login;

    @Override
    protected int setRootView() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
    }

    @OnClick(R.id.btn_loginout)
    public void loginout() {
        new BaseDialog(getActivity(), new OnDialogClickListener() {
            @Override
            public void cancle(Dialog dialog) {

            }

            @Override
            public void right(Dialog dialog) {
                SharedPreferencesUtils.saveString(mActivity, Constans.USER_NAME, "");
                mActivity.startActivity(LoginActivity.class);
                linear_no_login.setVisibility(View.VISIBLE);
                linear_is_login.setVisibility(View.GONE);
            }
        }, getString(R.string.loginout)).show();

    }


    /**
     * 积分商场
     */
    @OnClick(R.id.tv_jifen_shangc)
    public void jifenshangc() {

    }


    @Override
    public void onResume() {
        super.onResume();

    }

    /**
     * 赚积分
     */
    @OnClick(R.id.tv_get_jifen)
    public void getjifen() {
        if (Utils.checkLogin(mActivity))
            mActivity.startActivity(EarnPointsActivity.class);

    }

    /**
     * 秒杀记录
     */
    @OnClick(R.id.linear_miaosha)
    public void miaoshao() {

    }

    /**
     * 订单记录
     */
    @OnClick(R.id.linear_dingdan)
    public void order() {

    }

    /**
     * 提现记录
     */
    @OnClick(R.id.linear_tixian)
    public void tixian() {

    }

    /**
     * 充值记录
     */
    @OnClick(R.id.linear_chongzhi)
    public void chongzhi() {

    }

    /**
     * 收货管理
     */
    @OnClick(R.id.linear_shouhuo)
    public void shouhuo() {

    }


    /**
     * 我的评价
     */
    @OnClick(R.id.linear_pingjia)
    public void pingjia() {

    }

    /**
     * 反馈
     */
    @OnClick(R.id.linear_fankui)
    public void fankui() {

    }


    /**
     * 联系客服
     */
    @OnClick(R.id.linear_lianxi)
    public void lianxi() {

    }

    /**
     * 用户信息
     */
    @OnClick(R.id.linear_info)
    public void info() {

    }

    /**
     * 充值
     */
    @OnClick(R.id.tv_recharge)
    public void recharge() {

    }

    /**
     * 注册
     */
    @OnClick(R.id.tv_register)
    public void register() {
        Intent intent = new Intent(getActivity(), RegisterActivity.class);
        startActivity(intent);

    }

    /**
     * 登录
     */
    @OnClick(R.id.tv_login)
    public void login() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);

    }

    @Subscribe
    public void isLogin(LoginEvent loginEvent) {
        if (Utils.checkLogin(mActivity)) {
            linear_no_login.setVisibility(View.GONE);
            linear_is_login.setVisibility(View.VISIBLE);
        } else {
            linear_no_login.setVisibility(View.VISIBLE);
            linear_is_login.setVisibility(View.GONE);
        }
    }
}
