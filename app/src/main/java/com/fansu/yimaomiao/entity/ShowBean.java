package com.fansu.yimaomiao.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by huangbo on 16/11/19.
 */

public class ShowBean implements Serializable{

    /**
     * size : 8
     * total : 23
     * pageNum : 1
     * pageSize : 8
     * startRow : 1
     * endRow : 8
     * pages : 3
     * list : [{"address":"天机路128号","id":1,"status":"5","kdgs":"圆通快递","provice":"江苏省","shopid":2,"orderid":"wewewewewew23232","area":"浦口区","zipcode":"210000","city":"南京市","createtime":1479562020000,"tupian1":"http: //img1.mm131.com/pic/1472/3.jpg","tupian2":"http: //img1.mm131.com/pic/1319/2.jpg","kddh":"11111111111","pfen":"5","tupain3":"http: //img1.mm131.com/pic/1921/2.jpg","pjsj":1479562024000,"pjcontent":"非常好","userid":"18362083956","user":null},{"address":"天机路128号","id":2,"status":"5","kdgs":"圆通快递","provice":"江苏省","shopid":2,"orderid":"wewewewewew23232","area":"浦口区","zipcode":"210000","city":"南京市","createtime":1479562020000,"tupian1":"http: //img1.mm131.com/pic/1472/3.jpg","tupian2":"http: //img1.mm131.com/pic/1319/2.jpg","kddh":"11111111111","pfen":"5","tupain3":"http: //img1.mm131.com/pic/1921/2.jpg","pjsj":1479562024000,"pjcontent":"非常好","userid":"18362083956","user":null},{"address":"天机路128号","id":3,"status":"5","kdgs":"圆通快递","provice":"江苏省","shopid":2,"orderid":"wewewewewew23232","area":"浦口区","zipcode":"210000","city":"南京市","createtime":1479562020000,"tupian1":"http: //img1.mm131.com/pic/1472/3.jpg","tupian2":"http: //img1.mm131.com/pic/1319/2.jpg","kddh":"11111111111","pfen":"5","tupain3":"http: //img1.mm131.com/pic/1921/2.jpg","pjsj":1479562024000,"pjcontent":"非常好","userid":"18362083956","user":null},{"address":"天机路128号","id":4,"status":"5","kdgs":"圆通快递","provice":"江苏省","shopid":2,"orderid":"wewewewewew23232","area":"浦口区","zipcode":"210000","city":"南京市","createtime":1479562020000,"tupian1":"http: //img1.mm131.com/pic/1472/3.jpg","tupian2":"http: //img1.mm131.com/pic/1319/2.jpg","kddh":"11111111111","pfen":"5","tupain3":"http: //img1.mm131.com/pic/1921/2.jpg","pjsj":1479562024000,"pjcontent":"非常好","userid":"18362083956","user":null},{"address":"天机路128号","id":5,"status":"5","kdgs":"圆通快递","provice":"江苏省","shopid":2,"orderid":"wewewewewew23232","area":"浦口区","zipcode":"210000","city":"南京市","createtime":1479562020000,"tupian1":"http: //img1.mm131.com/pic/1472/3.jpg","tupian2":"http: //img1.mm131.com/pic/1319/2.jpg","kddh":"11111111111","pfen":"5","tupain3":"http: //img1.mm131.com/pic/1921/2.jpg","pjsj":1479562024000,"pjcontent":"非常好","userid":"18362083956","user":null},{"address":"天机路128号","id":6,"status":"5","kdgs":"圆通快递","provice":"江苏省","shopid":2,"orderid":"wewewewewew23232","area":"浦口区","zipcode":"210000","city":"南京市","createtime":1479562020000,"tupian1":"http: //img1.mm131.com/pic/1472/3.jpg","tupian2":"http: //img1.mm131.com/pic/1319/2.jpg","kddh":"11111111111","pfen":"5","tupain3":"http: //img1.mm131.com/pic/1921/2.jpg","pjsj":1479562024000,"pjcontent":"非常好","userid":"18362083956","user":null},{"address":"天机路128号","id":7,"status":"5","kdgs":"圆通快递","provice":"江苏省","shopid":2,"orderid":"wewewewewew23232","area":"浦口区","zipcode":"210000","city":"南京市","createtime":1479562020000,"tupian1":"http: //img1.mm131.com/pic/1472/3.jpg","tupian2":"http: //img1.mm131.com/pic/1319/2.jpg","kddh":"11111111111","pfen":"5","tupain3":"http: //img1.mm131.com/pic/1921/2.jpg","pjsj":1479562024000,"pjcontent":"非常好","userid":"18362083956","user":null},{"address":"天机路128号","id":8,"status":"5","kdgs":"圆通快递","provice":"江苏省","shopid":2,"orderid":"wewewewewew23232","area":"浦口区","zipcode":"210000","city":"南京市","createtime":1479562020000,"tupian1":"http: //img1.mm131.com/pic/1472/3.jpg","tupian2":"http: //img1.mm131.com/pic/1319/2.jpg","kddh":"11111111111","pfen":"5","tupain3":"http: //img1.mm131.com/pic/1921/2.jpg","pjsj":1479562024000,"pjcontent":"非常好","userid":"18362083956","user":null}]
     * firstPage : 1
     * prePage : 0
     * navigatePages : 8
     * isFirstPage : true
     * isLastPage : false
     * lastPage : 3
     * hasNextPage : true
     * nextPage : 2
     * hasPreviousPage : false
     * navigatepageNums : [1,2,3]
     */

    private int size;
    private int total;
    private int pageNum;
    private int pageSize;
    private int startRow;
    private int endRow;
    private int pages;
    private int firstPage;
    private int prePage;
    private int navigatePages;
    private boolean isFirstPage;
    private boolean isLastPage;
    private int lastPage;
    private boolean hasNextPage;
    private int nextPage;
    private boolean hasPreviousPage;
    private List<ListBean> list;
    private List<Integer> navigatepageNums;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNavigatePages() {
        return navigatePages;
    }

    public void setNavigatePages(int navigatePages) {
        this.navigatePages = navigatePages;
    }

    public boolean isIsFirstPage() {
        return isFirstPage;
    }

    public void setIsFirstPage(boolean isFirstPage) {
        this.isFirstPage = isFirstPage;
    }

    public boolean isIsLastPage() {
        return isLastPage;
    }

    public void setIsLastPage(boolean isLastPage) {
        this.isLastPage = isLastPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public List<Integer> getNavigatepageNums() {
        return navigatepageNums;
    }

    public void setNavigatepageNums(List<Integer> navigatepageNums) {
        this.navigatepageNums = navigatepageNums;
    }

    public static class ListBean {
        /**
         * address : 天机路128号
         * id : 1
         * status : 5
         * kdgs : 圆通快递
         * provice : 江苏省
         * shopid : 2
         * orderid : wewewewewew23232
         * area : 浦口区
         * zipcode : 210000
         * city : 南京市
         * createtime : 1479562020000
         * tupian1 : http: //img1.mm131.com/pic/1472/3.jpg
         * tupian2 : http: //img1.mm131.com/pic/1319/2.jpg
         * kddh : 11111111111
         * pfen : 5
         * tupain3 : http: //img1.mm131.com/pic/1921/2.jpg
         * pjsj : 1479562024000
         * pjcontent : 非常好
         * userid : 18362083956
         * user : null
         */

        private String address;
        private int id;
        private String status;
        private String kdgs;
        private String provice;
        private int shopid;
        private String orderid;
        private String area;
        private String zipcode;
        private String city;
        private long createtime;
        private String tupian1;
        private String tupian2;
        private String kddh;
        private String pfen;
        private String tupain3;
        private long pjsj;
        private String pjcontent;
        private String userid;
        private User user;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getKdgs() {
            return kdgs;
        }

        public void setKdgs(String kdgs) {
            this.kdgs = kdgs;
        }

        public String getProvice() {
            return provice;
        }

        public void setProvice(String provice) {
            this.provice = provice;
        }

        public int getShopid() {
            return shopid;
        }

        public void setShopid(int shopid) {
            this.shopid = shopid;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public long getCreatetime() {
            return createtime;
        }

        public void setCreatetime(long createtime) {
            this.createtime = createtime;
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

        public String getKddh() {
            return kddh;
        }

        public void setKddh(String kddh) {
            this.kddh = kddh;
        }

        public String getPfen() {
            return pfen;
        }

        public void setPfen(String pfen) {
            this.pfen = pfen;
        }

        public String getTupain3() {
            return tupain3;
        }

        public void setTupain3(String tupain3) {
            this.tupain3 = tupain3;
        }

        public long getPjsj() {
            return pjsj;
        }

        public void setPjsj(long pjsj) {
            this.pjsj = pjsj;
        }

        public String getPjcontent() {
            return pjcontent;
        }

        public void setPjcontent(String pjcontent) {
            this.pjcontent = pjcontent;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }
    }
}
