package com.example.mante2ty.Models;

import java.util.List;

public class SubCategoryModel {

    private ResultBean result;
    private int statusCode;
    private String statusText;

    public SubCategoryModel() {
    }

    public SubCategoryModel(ResultBean result, int statusCode, String statusText) {
        this.result = result;
        this.statusCode = statusCode;
        this.statusText = statusText;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
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

    public static class ResultBean {

        private int id;
        private String title;
        private String image;
        private List<SubCategoriesBean> subCategories;

        public ResultBean() {
        }

        public ResultBean(int id, String title, String image, List<SubCategoriesBean> subCategories) {
            this.id = id;
            this.title = title;
            this.image = image;
            this.subCategories = subCategories;
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

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public List<SubCategoriesBean> getSubCategories() {
            return subCategories;
        }

        public void setSubCategories(List<SubCategoriesBean> subCategories) {
            this.subCategories = subCategories;
        }

        public static class SubCategoriesBean {

            private int id;
            private String title;
            private String image;
            private List<?> children;

            public SubCategoriesBean() {
            }

            public SubCategoriesBean(int id, String title, String image, List<?> children) {
                this.id = id;
                this.title = title;
                this.image = image;
                this.children = children;
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

            public Object getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public List<?> getChildren() {
                return children;
            }

            public void setChildren(List<?> children) {
                this.children = children;
            }
        }
    }
}
