package com.fansu.yimaomiao.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by huangbo on 16/11/19.
 */
public class NearBean implements Serializable{

    /**
     * size : 1
     * total : 1
     * firstPage : 1
     * prePage : 0
     * isFirstPage : true
     * endRow : 1
     * list : [{"id":null,"money":null,"userjifen":null,"userid":"18362084956","sex":"男","nickname":"小毛","lat":null,"usertx":"http://img.woyaogexing.com/2016/11/13/c50506564c2470f4!200x200.jpg","distance":"0","age":null,"lng":null,"password":null,"updatetime":null,"createtime":null}]
     * nextPage : 0
     * isLastPage : true
     * hasNextPage : false
     * navigatePages : 8
     * lastPage : 1
     * pages : 1
     * navigatepageNums : [1]
     * hasPreviousPage : false
     * pageSize : 20
     * pageNum : 1
     * startRow : 1
     */

    private int size;
    private int total;
    private int firstPage;
    private int prePage;
    private boolean isFirstPage;
    private int endRow;
    private int nextPage;
    private boolean isLastPage;
    private boolean hasNextPage;
    private int navigatePages;
    private int lastPage;
    private int pages;
    private boolean hasPreviousPage;
    private int pageSize;
    private int pageNum;
    private int startRow;
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

    public boolean isIsFirstPage() {
        return isFirstPage;
    }

    public void setIsFirstPage(boolean isFirstPage) {
        this.isFirstPage = isFirstPage;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public boolean isIsLastPage() {
        return isLastPage;
    }

    public void setIsLastPage(boolean isLastPage) {
        this.isLastPage = isLastPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public int getNavigatePages() {
        return navigatePages;
    }

    public void setNavigatePages(int navigatePages) {
        this.navigatePages = navigatePages;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
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
         * id : null
         * money : null
         * userjifen : null
         * userid : 18362084956
         * sex : 男
         * nickname : 小毛
         * lat : null
         * usertx : http://img.woyaogexing.com/2016/11/13/c50506564c2470f4!200x200.jpg
         * distance : 0
         * age : null
         * lng : null
         * password : null
         * updatetime : null
         * createtime : null
         */

        private String id;
        private String money;
        private String userjifen;
        private String userid;
        private String sex;
        private String nickname;
        private String lat;
        private String usertx;
        private String distance;
        private String age;
        private String lng;
        private String password;
        private String updatetime;
        private String createtime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getUserjifen() {
            return userjifen;
        }

        public void setUserjifen(String userjifen) {
            this.userjifen = userjifen;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getUsertx() {
            return usertx;
        }

        public void setUsertx(String usertx) {
            this.usertx = usertx;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }
    }
}
