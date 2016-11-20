package com.fansu.yimaomiao.entity;

import java.io.Serializable;

/**
 * Created by huangbo on 16/11/19.
 */
public class User implements Serializable{
    // 自增id
    private Integer id;
    // 用户手机号
    private String userid;
    // 用户密码
    private String password;
    // 用户昵称
    private String nickname;
    // 用户头像
    private String usertx;
    // 用户金额
    private Double money;
    // 用户积分
    private Integer userjifen;

    public Integer getUserjifen() {
        return userjifen;
    }

    public void setUserjifen(Integer userjifen) {
        this.userjifen = userjifen;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsertx() {
        return usertx;
    }

    public void setUsertx(String usertx) {
        this.usertx = usertx;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    // 创建时间
    private String createtime;
    // 修改时间
    private String updatetime;

    //用户性别(男，女)
    private String sex;

    //经度
    private String lng;
    //纬度
    private String lat;

    //相对距离
    private String distance;

}
