package com.fansu.yimaomiao.customview;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.TextView;

import com.fansu.yimaomiao.R;
import com.fansu.yimaomiao.inter.OnDialogClickListener;


/**
 * Created by leo on 16/11/7.
 */

public class BaseDialog extends  Dialog {
    private TextView mTitle;
    private TextView mCancle;
    private TextView mRight;
    private OnDialogClickListener mOnDialogClickListener;
    private String title;

    public BaseDialog(Activity context, OnDialogClickListener onDialogClickListener,String title) {
        super(context, R.style.transparentFrameWindowStyle);
        mOnDialogClickListener = onDialogClickListener;
        this.title = title;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_base_dialog);
        mCancle = (TextView) findViewById(R.id.tv_dialog_cancle);
        mRight = (TextView) findViewById(R.id.tv_dialog_right);
        mTitle = (TextView) findViewById(R.id.tv_dialog_title);
        mCancle.setOnClickListener(v -> {
            dismiss();
            mOnDialogClickListener.cancle(this);
        });
        mRight.setOnClickListener(v -> {
            dismiss();
            mOnDialogClickListener.right(this);
        });
        mTitle.setText(title);
    }




}
