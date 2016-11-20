package com.fansu.yimaomiao.entity;

import java.io.Serializable;

/**
 * Created by huangbo on 16/11/19.
 */

public class ShowBean implements Serializable{
    // 自增主键
    private Integer id;
    // 商品id
    private Integer shopid;
    //订单id
    private String orderid;
    // 用户id
    private String userid;
    // 省
    private String provice;
    // 市
    private String city;
    // 区
    private String area;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getShopid() {
        return shopid;
    }

    public void setShopid(Integer shopid) {
        this.shopid = shopid;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getProvice() {
        return provice;
    }

    public void setProvice(String provice) {
        this.provice = provice;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getKdgs() {
        return kdgs;
    }

    public void setKdgs(String kdgs) {
        this.kdgs = kdgs;
    }

    public String getKddh() {
        return kddh;
    }

    public void setKddh(String kddh) {
        this.kddh = kddh;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getPfen() {
        return pfen;
    }

    public void setPfen(String pfen) {
        this.pfen = pfen;
    }

    public String getPjcontent() {
        return pjcontent;
    }

    public void setPjcontent(String pjcontent) {
        this.pjcontent = pjcontent;
    }

    public String getTupian1() {
        return tupian1;
    }

    public void setTupian1(String tupian1) {
        this.tupian1 = tupian1;
    }

    public String getTupian2() {
        return tupian2;
    }

    public void setTupian2(String tupian2) {
        this.tupian2 = tupian2;
    }

    public String getTupain3() {
        return tupain3;
    }

    public void setTupain3(String tupain3) {
        this.tupain3 = tupain3;
    }

    public String getPjsj() {
        return pjsj;
    }

    public void setPjsj(String pjsj) {
        this.pjsj = pjsj;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // 详细地址
    private String address;
    // 邮编
    private String zipcode;
    // 快递公司
    private String kdgs;
    // 快递单号
    private String kddh;
    // 状态1:下单完成 2：填写地址  3：发货 4:确认收货5评价
    private String status;
    // 创建时间
    private String createtime;
    // 评分（5分）
    private String pfen;
    // 评价内容
    private String pjcontent;
    // 晒图1
    private String tupian1;
    // 图片2
    private String tupian2;
    // 图片3
    private String tupain3;
    // 评价时间
    private String pjsj;

    private User user;
}
