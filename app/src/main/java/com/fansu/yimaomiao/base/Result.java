/*
 * Copyright 2016 ikidou
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fansu.yimaomiao.base;


import java.io.Serializable;
import java.util.List;

public class Result<T>  implements Serializable{
    public String status;
    public String message;
    public T bean;
    public String token;
    public int code;
    public T pagination;
    private List<T> listBean;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getBean() {
        return bean;
    }

    public void setBean(T bean) {
        this.bean = bean;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getPagination() {
        return pagination;
    }

    public void setPagination(T pagination) {
        this.pagination = pagination;
    }

    public List<T> getListBean() {
        return listBean;
    }

    public void setListBean(List<T> listBean) {
        this.listBean = listBean;
    }
}
