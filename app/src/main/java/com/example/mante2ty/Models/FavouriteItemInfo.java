package com.example.mante2ty.Models;

import java.util.List;

public class FavouriteItemInfo {

    public int statusCode;
    public String statusText;
    public List<ResultBean> result;

    public static class ResultBean {

        public int store_id;
        public String logo;
        public String title;
        public int rate;

        public ResultBean() {
        }

        public ResultBean(int store_id, String logo, String title, int rate) {
            this.store_id = store_id;
            this.logo = logo;
            this.title = title;
            this.rate = rate;
        }

        public int getStore_id() {
            return store_id;
        }

        public void setStore_id(int store_id) {
            this.store_id = store_id;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getRate() {
            return rate;
        }

        public void setRate(int rate) {
            this.rate = rate;
        }
    }

    public FavouriteItemInfo() {
    }

    public FavouriteItemInfo(int statusCode, String statusText, List<ResultBean> result) {
        this.statusCode = statusCode;
        this.statusText = statusText;
        this.result = result;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }
}
