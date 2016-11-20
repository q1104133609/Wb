package com.fansu.yimaomiao.customview;

import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.LogUtils;
import com.fansu.yimaomiao.Constans;
import com.fansu.yimaomiao.R;
import com.fansu.yimaomiao.base.BaseActivity;
import com.fansu.yimaomiao.utils.SharedUtils;

import java.util.HashMap;
import java.util.List;

import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;

/**
 * Created by huangbo on 16/11/20.
 */

public class ChoosePhotoDialog extends Dialog implements View.OnClickListener {

    private BaseActivity activity;
    private TextView cancelButton;
    private TextView mPhoto;
    private TextView mCamera;
    private OnChoosePhotoListener mOnChoosePhotoListener;

    public ChoosePhotoDialog(BaseActivity activity, OnChoosePhotoListener onChoosePhotoListener) {
        super(activity, R.style.transparentFrameWindowStyle);
        this.activity = activity;
        this.mOnChoosePhotoListener = onChoosePhotoListener;

    }

    public ChoosePhotoDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_choose_photo);
        mPhoto = (TextView) findViewById(R.id.button_photo);
        mCamera = (TextView) findViewById(R.id.button_camera);
        cancelButton = (TextView) findViewById(R.id.button_cancel);
        mPhoto.setOnClickListener(this);
        mCamera.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
        init();
    }

    private void init() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);
        window.setGravity(Gravity.BOTTOM);
        setCanceledOnTouchOutside(true);

    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        switch (i) {
            case R.id.button_camera:
                GalleryFinal.openCamera(Constans.REQUEST_CODE_CAMERA, new GalleryFinal.OnHanlderResultCallback() {
                    @Override
                    public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                        mOnChoosePhotoListener.success(reqeustCode, resultList);
                    }

                    @Override
                    public void onHanlderFailure(int requestCode, String errorMsg) {
                        mOnChoosePhotoListener.failue(requestCode, errorMsg);
                    }
                });
                dismiss();
                break;
            case R.id.button_photo:
                GalleryFinal.openGallerySingle(Constans.REQUEST_CODE_GALLERY, new GalleryFinal.OnHanlderResultCallback() {
                    @Override
                    public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                        mOnChoosePhotoListener.success(reqeustCode, resultList);
                    }

                    @Override
                    public void onHanlderFailure(int requestCode, String errorMsg) {
                        mOnChoosePhotoListener.failue(requestCode, errorMsg);
                    }
                });
                dismiss();
                break;
            case R.id.button_cancel:
                dismiss();
                break;
        }

    }


    public interface OnChoosePhotoListener {
        void success(int code, List<PhotoInfo> photoInfoList);

        void failue(int code, String msg);
    }
}
