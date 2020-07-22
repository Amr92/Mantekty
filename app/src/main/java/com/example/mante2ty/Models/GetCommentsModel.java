package com.example.mante2ty.Models;

import java.util.List;

public class GetCommentsModel {


    public int statusCode;
    public String statusText;
    public List<ResultBean> result;

    public static class ResultBean {

        public int id;
        public String username;
        public String image;
        public String comment;
        public int rate;
        public String created_at;

        public ResultBean() {
        }

        public ResultBean(int id, String username, String image, String comment, int rate, String created_at) {
            this.id = id;
            this.username = username;
            this.image = image;
            this.comment = comment;
            this.rate = rate;
            this.created_at = created_at;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public int getRate() {
            return rate;
        }

        public void setRate(int rate) {
            this.rate = rate;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }
    }

    public GetCommentsModel() {
    }

    public GetCommentsModel(int statusCode, String statusText, List<ResultBean> result) {
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
