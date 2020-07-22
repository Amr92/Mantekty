package com.example.mante2ty.Models;

import java.util.List;

public class NotificationsModel {

    public int statusCode;
    public String statusText;
    public List<ResultBean> result;

    public static class ResultBean {

        public int id;
        public String title;
        public String body;
        public String type;
        public int record_id;
        public String created_at;

        public ResultBean() {
        }

        public ResultBean(int id, String title, String body, String type, int record_id, String created_at) {
            this.id = id;
            this.title = title;
            this.body = body;
            this.type = type;
            this.record_id = record_id;
            this.created_at = created_at;
        }

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

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getRecord_id() {
            return record_id;
        }

        public void setRecord_id(int record_id) {
            this.record_id = record_id;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }
    }

    public NotificationsModel() {
    }

    public NotificationsModel(int statusCode, String statusText, List<ResultBean> result) {
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
