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

import com.blankj.utilcode.utils.LogUtils;
import com.fansu.yimaomiao.R;
import com.fansu.yimaomiao.base.BaseActivity;
import com.fansu.yimaomiao.utils.SharedUtils;

import java.util.HashMap;
import java.util.List;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;

/**
 * Created by leo on 16/11/6.
 */

public class ShareDialog extends Dialog {

    private BaseActivity activity;
    private SharedUtils sharedUtils;
    private Button cancelButton;
    private LinearLayout wx;
    private LinearLayout pyq;
    private LinearLayout xlwb;
    private LinearLayout qq;

    private View.OnClickListener OnCancelClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dismiss();
        }
    };

    private View.OnClickListener wxClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(isWeixinAvilible(activity)){
                activity.showToast(activity.getString(R.string.is_opne_wx));
                activity.showLoading(true);
                sharedUtils.Shared_wx(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        Log.d("Shared_wx","onComplete");
                        activity.showToast(activity.getString(R.string.share_success));
                        activity.closeLoading();
                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {
                        LogUtils.e(i+""+throwable.toString());
                        Log.d("Shared_wx", "onError");
                        activity.showToast(activity.getString(R.string.share_error));
                        activity.closeLoading();
                    }

                    @Override
                    public void onCancel(Platform platform, int i) {
                        Log.d("Shared_wx","onCancel");
                        activity.showToast(activity.getString(R.string.share_cancle));
                        activity.closeLoading();
                    }
                });
            }else{
                activity.showToast(activity.getString(R.string.please_install_wx));
            }
            dismiss();
        }
    };

    private View.OnClickListener qqClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(isQQAvilible(activity)){
                ((BaseActivity)activity).showToast(activity.getString(R.string.is_open_QQ));
                activity.showLoading(true);
                sharedUtils.Shared_qq(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        LogUtils.d("onComplete");
                        Log.d("pyqClick","onComplete");
                        activity.showToast(activity.getString(R.string.share_success));
                        activity.closeLoading();
                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {
                        LogUtils.e(i+""+throwable.toString());
                        activity.showToast(activity.getString(R.string.share_error));
                        activity.closeLoading();
                    }

                    @Override
                    public void onCancel(Platform platform, int i) {
                        LogUtils.d("onCancel");
                        activity.showToast(activity.getString(R.string.share_cancle));
                        activity.closeLoading();
                    }
                });
            }else{
                ((BaseActivity)activity).showToast(activity.getString(R.string.please_install_qq));
            }
            dismiss();
        }
    };




    private View.OnClickListener pyqClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(isWeixinAvilible(activity)){
                ((BaseActivity)activity).showToast(activity.getString(R.string.is_open_wx));
                activity.showLoading(true);
                sharedUtils.Shared_pyq(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        LogUtils.d("onComplete");
                        Log.d("pyqClick","onComplete");
                        activity.showToast(activity.getString(R.string.share_success));
                        activity.closeLoading();
                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {
                        LogUtils.e(i+""+throwable.toString());
                        activity.showToast(activity.getString(R.string.share_error));
                        activity.closeLoading();
                    }

                    @Override
                    public void onCancel(Platform platform, int i) {
                        LogUtils.d("onCancel");
                        activity.showToast(activity.getString(R.string.share_cancle));
                        activity.closeLoading();
                    }
                });
            }else{
                ((BaseActivity)activity).showToast(activity.getString(R.string.please_install_wx));
            }
            dismiss();
        }
    };


    private View.OnClickListener xlwbClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(isXlwbAvilible(activity)){
                ((BaseActivity)activity).showToast(activity.getString(R.string.is_open_xlwb));
                activity.showLoading(true);
                sharedUtils.Shared_xlvb(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        activity.showToast(activity.getString(R.string.share_success));
                        activity.closeLoading();
                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {
                        LogUtils.e(i+""+throwable.toString());
                        activity.showToast(activity.getString(R.string.share_error));
                        activity.closeLoading();
                    }

                    @Override
                    public void onCancel(Platform platform, int i) {
//                        activity.showToast(activity.getString(R.string.share_cancle));
                        activity.closeLoading();
                    }
                });
            }else{
                ((BaseActivity)activity).showToast(activity.getString(R.string.please_install_xlwb));
            }
            dismiss();
        }
    };


    private View.OnClickListener qqZom = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

//            if(isQzon(activity)){
            ((BaseActivity)activity).showToast(activity.getString(R.string.is_open_qqkongjian));
            activity.showLoading(true);
            sharedUtils.Shared_qqZom(new PlatformActionListener() {
                @Override
                public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                    activity.showToast(activity.getString(R.string.share_success));
                    activity.closeLoading();
                }

                @Override
                public void onError(Platform platform, int i, Throwable throwable) {
                    LogUtils.e(i+""+throwable.toString());
                    activity.showToast(activity.getString(R.string.share_error));
                    activity.closeLoading();
                }

                @Override
                public void onCancel(Platform platform, int i) {
                    activity.showToast(activity.getString(R.string.share_cancle));
                    activity.closeLoading();
                }
            });
//            、
            dismiss();
        }
    };


    public ShareDialog(BaseActivity activity, int themeResId) {
        super(activity, themeResId);
        this.activity = activity;
        sharedUtils = new SharedUtils();
        BitmapDrawable bd = (BitmapDrawable) activity.getResources().getDrawable(R.mipmap.ic_launcher);
        setBitmap(bd.getBitmap());
    }

    public ShareDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_photo_choose);
        wx = (LinearLayout) findViewById(R.id.dialog_shared_wx);
        pyq = (LinearLayout) findViewById(R.id.dialog_shared_pyq);
        xlwb = (LinearLayout) findViewById(R.id.dialog_shared_xlwb);
        qq = (LinearLayout) findViewById(R.id.dialog_shared_qq);
        cancelButton = (Button) findViewById(R.id.button_cancel);
        init();
    }

    private void init() {
        cancelButton.setOnClickListener(OnCancelClick);
        wx.setOnClickListener(wxClick);
        pyq.setOnClickListener(pyqClick);
        xlwb.setOnClickListener(xlwbClick);
        qq.setOnClickListener(qqClick);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);
        window.setGravity(Gravity.BOTTOM);
        setCanceledOnTouchOutside(true);
    }

    public static boolean isAvilible(Context context,String packageNamestr) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals(packageNamestr)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean isWeixinAvilible(Context context) {
        return isAvilible(context,"com.tencent.mm");
    }

    public static boolean isQQAvilible(Context context) {
        return isAvilible(context,"com.tencent.mobileqq");
    }

    public static boolean isXlwbAvilible(Context context) {
        return isAvilible(context,"com.sina.weibo");
    }



    public void setTitle(String title) {
        sharedUtils.title = title;
    }

    public void setTitleUrl(String titleUrl) {
        sharedUtils.titleUrl = titleUrl;
    }

    public void setText(String text) {
        sharedUtils.text = text;
    }

    public void setImageUrl(String imageUrl) {
        sharedUtils.imageUrl = imageUrl;
    }


    public void setBitmap(Bitmap bitmap) {
        sharedUtils.bitmap = bitmap;
    }

    public void setSite(String site) {
        sharedUtils.site = site;
    }

    public void setSiteUrl(String siteUrl) {
        sharedUtils.siteUrl = siteUrl;
    }
}
