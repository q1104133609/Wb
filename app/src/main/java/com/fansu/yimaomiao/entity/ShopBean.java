package com.fansu.yimaomiao.entity;

import java.io.Serializable;

/**
 * Created by huangbo on 16/11/19.
 */
public class ShopBean implements Serializable{
    // 自增主键
    private Integer id;
    // 商品的名称
    private String shopname;
    // 商品的价格
    private Integer shopjg;
    // 商品的描述
    private String shopms;
    // 商品的图片
    private String shopphoto;
    // 商品的图片1
    private String shopphoto1;

    public String getMssj() {
        return mssj;
    }

    public void setMssj(String mssj) {
        this.mssj = mssj;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public Integer getShopjg() {
        return shopjg;
    }

    public void setShopjg(Integer shopjg) {
        this.shopjg = shopjg;
    }

    public String getShopms() {
        return shopms;
    }

    public void setShopms(String shopms) {
        this.shopms = shopms;
    }

    public String getShopphoto() {
        return shopphoto;
    }

    public void setShopphoto(String shopphoto) {
        this.shopphoto = shopphoto;
    }

    public String getShopphoto1() {
        return shopphoto1;
    }

    public void setShopphoto1(String shopphoto1) {
        this.shopphoto1 = shopphoto1;
    }

    public String getShopphoto2() {
        return shopphoto2;
    }

    public void setShopphoto2(String shopphoto2) {
        this.shopphoto2 = shopphoto2;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getKu() {
        return ku;
    }

    public void setKu(Integer ku) {
        this.ku = ku;
    }

    public String getSfwc() {
        return sfwc;
    }

    public void setSfwc(String sfwc) {
        this.sfwc = sfwc;
    }

    public String getHtmlurl() {
        return htmlurl;
    }

    public void setHtmlurl(String htmlurl) {
        this.htmlurl = htmlurl;
    }

    public String getYj() {
        return yj;
    }

    public void setYj(String yj) {
        this.yj = yj;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // 商品的图片2
    private String shopphoto2;
    // 创建时间
    private String createtime;
    // 修改时间
    private String updatetime;
    //数量
    private Integer ku;
    // 秒杀时间到分钟
    private String mssj;
    // 是否完成 1是 0否
    private String sfwc;
    // 静态资源地址
    private String htmlurl;

    //商品原价
    private String yj;

    //商品类别(1.秒杀，2.积分)
    private String type;

}
