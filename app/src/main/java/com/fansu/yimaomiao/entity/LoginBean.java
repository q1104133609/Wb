package com.fansu.yimaomiao.entity;

import java.io.Serializable;

/**
 * Created by huangbo on 2016/11/13.
 */
public class LoginBean implements Serializable{

    /**
     * lat : null
     * id : 2
     * password : null
     * userjifen : 10
     * userid : 18362084956
     * money : 0.0
     * usertx : http://img0.imgtn.bdimg.com/it/u=3264805915,1360440417&fm=11&gp=0.jpg
     * sex : 男
     * lng : null
     * nickname : 小毛
     * createtime : 1479529872000
     * updatetime : null
     */

    private String lat;
    private int id;
    private String password;
    private int userjifen;
    private String userid;
    private double money;
    private String usertx;
    private String sex;
    private String lng;
    private String nickname;
    private long createtime;
    private String updatetime;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserjifen() {
        return userjifen;
    }

    public void setUserjifen(int userjifen) {
        this.userjifen = userjifen;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getUsertx() {
        return usertx;
    }

    public void setUsertx(String usertx) {
        this.usertx = usertx;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(long createtime) {
        this.createtime = createtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }
}
