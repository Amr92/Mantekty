package com.example.mante2ty.Models;

import java.util.List;

public class StoresModel {

    public int statusCode;
    public String statusText;
    public List<ResultBean> result;

    public static class ResultBean {

        public int id;
        public String title;
        public String logo;
        public String lat;
        public String lng;
        public String address_description;
        public int views;
        public double rate;
        public boolean isFavorite;
        public int generate_qr;
        public String workDaysText;
        public List<?> workDays;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getAddress_description() {
            return address_description;
        }

        public void setAddress_description(String address_description) {
            this.address_description = address_description;
        }

        public int getViews() {
            return views;
        }

        public void setViews(int views) {
            this.views = views;
        }

        public double getRate() {
            return rate;
        }

        public void setRate(double rate) {
            this.rate = rate;
        }

        public boolean isFavorite() {
            return isFavorite;
        }

        public void setFavorite(boolean favorite) {
            isFavorite = favorite;
        }

        public int getGenerate_qr() {
            return generate_qr;
        }

        public void setGenerate_qr(int generate_qr) {
            this.generate_qr = generate_qr;
        }

        public String getWorkDaysText() {
            return workDaysText;
        }

        public void setWorkDaysText(String workDaysText) {
            this.workDaysText = workDaysText;
        }

        public List<?> getWorkDays() {
            return workDays;
        }

        public void setWorkDays(List<?> workDays) {
            this.workDays = workDays;
        }
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
