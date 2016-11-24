package com.fansu.yimaomiao.view.activity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.fansu.yimaomiao.App;
import com.fansu.yimaomiao.Constans;
import com.fansu.yimaomiao.R;
import com.fansu.yimaomiao.base.Result;
import com.fansu.yimaomiao.base.mvp.BaseView;
import com.fansu.yimaomiao.base.mvp.MvpActivity;
import com.fansu.yimaomiao.customview.ChoosePhotoDialog;
import com.fansu.yimaomiao.entity.LoginBean;
import com.fansu.yimaomiao.event.UserInfoEvent;
import com.fansu.yimaomiao.inter.OnQiuNiuListener;
import com.fansu.yimaomiao.presenter.UserInfoPercenter;
import com.fansu.yimaomiao.utils.QNUtils;
import com.fansu.yimaomiao.utils.SharedPreferencesUtils;
import com.fansu.yimaomiao.utils.Utils;
import com.fansu.yimaomiao.utils.transform.GlideCircleTransform;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * Created by huangbo on 16/11/20.
 */

public class UserInfoActivity extends MvpActivity<UserInfoPercenter> implements BaseView<Result>, ChoosePhotoDialog.OnChoosePhotoListener, OnQiuNiuListener {

    @BindView(R.id.image_head)
    ImageView mHead;
    @BindView(R.id.edit_sex)
    TextView mSex;
    @BindView(R.id.edit_nickname)
    TextView mNickName;
    /**
     * 是否需要更新
     */
    private boolean isUpda;
    private Bitmap mBitmapg;


    @Override
    protected int setRootView() {
        return R.layout.activity_user_info;
    }

    @Override
    protected void initData() {


    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        Glide.with(this).load(Utils.getQiniuHead(this)).transform(new GlideCircleTransform(this))
                .error(R.mipmap.default_head)
                .into(mHead);
        LoginBean mLoginBean = App.mLoginBean;
        mNickName.setText(mLoginBean.getNickname());
        mSex.setText(mLoginBean.getSex());

    }

    @Override
    public void isSuccess(Result bean) {
        EventBus.getDefault().post(new UserInfoEvent());
        showToast("更新信息数据成功~");
        finish();

    }

    @Override
    public void isFailure(String msg) {
        showToast("更新信息出错请重试~");
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
    protected UserInfoPercenter createPresenter() {
        return new UserInfoPercenter(this);
    }

    /**
     * 修改
     *
     * @param view
     */
    public void update(View view) {
        if (isUpda)
            //上传七牛
            if (mBitmapg != null) {
                QNUtils.getInstance()
                        .upLoadSingleFile(mBitmapg,
                                Base64.encodeToString(SharedPreferencesUtils.getString(mContext, Constans.USER_NAME, "").getBytes(), Base64.NO_WRAP)
                                , this);
                showLoading(false);
            } else {

            }

//            mvpPresenter.updateInfo();
    }


    /**
     * 返回
     */
    public void back(View view) {
        finish();
    }

    @OnClick(R.id.image_head)
    public void updataHead() {
        ChoosePhotoDialog choosePhotoDialog = new ChoosePhotoDialog(this, this);
        choosePhotoDialog.show();
    }

    @OnClick(R.id.linear_sex)
    public void updataSex() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("选择一个性别");
        final String[] cities = {"男", "女"};
        builder.setItems(cities, (dialog, which) -> {
            if (!cities[which].equals(mSex.getText().toString())) {
                isUpda = true;
                mSex.setText(cities[which]);
            }
        });
        builder.setNeutralButton("取消", null);
        builder.show();

    }

    @OnClick(R.id.linear_nickname)
    public void updataName() {
        final EditText inputServer = new EditText(this);
        inputServer.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)}); //最大输入长度
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("修改昵称").setView(inputServer)
                .setNegativeButton(getString(R.string.cancel), null);
        builder.setPositiveButton(getString(R.string.right), (dialog, which) -> {
            if (inputServer.getText().length() <= 0) {
                showToast("昵称不能为空~");
            } else {
                isUpda = true;
                mNickName.setText(inputServer.getText());
                dialog.dismiss();
            }


        });
        builder.show();
    }

    /**
     * 选择图片成功
     *
     * @param code
     * @param photoInfoList
     */
    @Override
    public void success(int code, List<PhotoInfo> photoInfoList) {
        isUpda = true;
        Glide.with(mContext)
                .load(new File(photoInfoList.get(0).getPhotoPath()))
                .transform(new GlideCircleTransform(mContext))
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable glideDrawable, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        isUpda = true;
                        mBitmapg = Utils.drawableToBitmap(glideDrawable);
                        mHead.setImageBitmap(mBitmapg);
                    }
                });
    }

    /**
     * 现在图片失败
     *
     * @param code
     * @param msg
     */
    @Override
    public void failue(int code, String msg) {
        showToast(msg);

    }

    /**
     * 七牛成功
     *
     * @param key
     */
    @Override
    public void isSuccess(String key) {
        closeLoading();
        showToast("上传成功~" + key);

    }

    /**
     * 七牛失败
     *
     * @param message
     */
    @Override
    public void isFlld(String message) {
        closeLoading();
        showToast("上传失败~");
    }

}
