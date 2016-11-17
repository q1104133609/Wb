package com.fansu.yimaomiao.utils;

import android.graphics.Bitmap;

import com.blankj.utilcode.utils.LogUtils;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.favorite.WechatFavorite;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

/**
 * Created by leo on 16/11/6.
 */
public class SharedUtils {


    public String title = "测试分享的标题";
    public String titleUrl = "标题的超链接";
    public String text = "测试分享的文本";
    public String imageUrl = "http://c.hiphotos.baidu.com/baike/c0%3Dbaike933%2C5%2C5%2C933%2C330/sign=c31a0032d6a20cf4529df68d17602053/d62a6059252dd42ab2ac861e053b5bb5c8eab8da.jpg";
    public Bitmap bitmap = null;
    public String site = "";
    public String siteUrl = "";

    public SharedUtils() {

    }


    public void Shared_xlvb(PlatformActionListener paListener) {
        SinaWeibo.ShareParams sp = new SinaWeibo.ShareParams();
        sp.setShareType(Platform.SHARE_WEBPAGE);
        sp.setTitle(title);
//        sp.setTitleUrl(titleUrl);
        sp.setText(text);
        sp.setImageData(bitmap);
        sp.setUrl(titleUrl);
        LogUtils.e(titleUrl);

        Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
        weibo.setPlatformActionListener(paListener); // 设置分享事件回调
        weibo.authorize();
        // 执行图文分享
        weibo.share(sp);
    }

    public void Shared_wx(PlatformActionListener paListener) {
        Wechat.ShareParams sp = new Wechat.ShareParams();
        sp.setShareType(Platform.SHARE_WEBPAGE);
        sp.setTitle(title);
//        sp.setTitleUrl(titleUrl);
        sp.setText(text);
        sp.setImageData(bitmap);
        sp.setUrl(titleUrl);
        LogUtils.e(titleUrl);

        Platform qzone = ShareSDK.getPlatform(Wechat.NAME);
        qzone.setPlatformActionListener(paListener); // 设置分享事件回调
        // 执行图文分享
        qzone.share(sp);
    }

    public void Shared_wxsc(PlatformActionListener paListener) {
        WechatFavorite.ShareParams sp = new WechatFavorite.ShareParams();
        sp.setShareType(Platform.SHARE_WEBPAGE);
        sp.setTitle(title);
//        sp.setTitleUrl(titleUrl);
        sp.setText(text);
        sp.setImageData(bitmap);
        sp.setUrl(titleUrl);
        LogUtils.e(titleUrl);

        Platform qzone = ShareSDK.getPlatform(WechatFavorite.NAME);
        qzone.setPlatformActionListener(paListener); // 设置分享事件回调
        // 执行图文分享
        qzone.share(sp);
    }

    public void Shared_qq(PlatformActionListener paListener) {
        QQ.ShareParams sp = new QQ.ShareParams();
        sp.setShareType(Platform.SHARE_IMAGE);
        sp.setTitle(title);
        sp.setText(text);
        sp.setTitleUrl(titleUrl);
        Platform qzone = ShareSDK.getPlatform(QQ.NAME);
        qzone.setPlatformActionListener(paListener); // 设置分享事件回调
        // 执行图文分享
        qzone.share(sp);
    }


    public void Shared_qqZom(PlatformActionListener paListener) {
        QZone.ShareParams sp = new QZone.ShareParams();
        sp.setShareType(Platform.SHARE_WEBPAGE);
        sp.setTitle(title);
        sp.setTitleUrl(titleUrl);
        sp.setText(text);
        sp.setImageData(bitmap);
        sp.setUrl(titleUrl);
        LogUtils.e(titleUrl);

        Platform qzone = ShareSDK.getPlatform(QZone.NAME);
        qzone.setPlatformActionListener(paListener); // 设置分享事件回调
        // 执行图文分享
        qzone.share(sp);
    }


    public void Shared_pyq(PlatformActionListener paListener) {
        WechatMoments.ShareParams sp = new WechatMoments.ShareParams();
        sp.setShareType(Platform.SHARE_WEBPAGE);
        sp.setTitle(title);
        sp.setText(text);
        sp.setImageData(bitmap);
        sp.setUrl(titleUrl);

        Platform qzone = ShareSDK.getPlatform(WechatMoments.NAME);
        qzone.setPlatformActionListener(paListener); // 设置分享事件回调
        // 执行图文分享
        qzone.share(sp);
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleUrl() {
        return titleUrl;
    }

    public void setTitleUrl(String titleUrl) {
        this.titleUrl = titleUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }
}
