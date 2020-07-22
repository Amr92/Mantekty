package com.example.mante2ty.Models;

public class OfferDetailsModel {

    public ResultBean result;
    public int statusCode;
    public String statusText;

    public static class ResultBean {

        public int id;
        public String title;
        public String body;
        public String image;
        public int type;
        public String type_text;
        public String start_date;
        public String end_date;
        public int views;
        public StoreBean store;

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

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getType_text() {
            return type_text;
        }

        public void setType_text(String type_text) {
            this.type_text = type_text;
        }

        public String getStart_date() {
            return start_date;
        }

        public void setStart_date(String start_date) {
            this.start_date = start_date;
        }

        public String getEnd_date() {
            return end_date;
        }

        public void setEnd_date(String end_date) {
            this.end_date = end_date;
        }

        public int getViews() {
            return views;
        }

        public void setViews(int views) {
            this.views = views;
        }

        public StoreBean getStore() {
            return store;
        }

        public void setStore(StoreBean store) {
            this.store = store;
        }

        public static class StoreBean {

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
            public int discount_amount;
            public String discount_type;
            public String discount_text;
            public String workDaysText;

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

            public int getDiscount_amount() {
                return discount_amount;
            }

            public void setDiscount_amount(int discount_amount) {
                this.discount_amount = discount_amount;
            }

            public String getDiscount_type() {
                return discount_type;
            }

            public void setDiscount_type(String discount_type) {
                this.discount_type = discount_type;
            }

            public String getDiscount_text() {
                return discount_text;
            }

            public void setDiscount_text(String discount_text) {
                this.discount_text = discount_text;
            }

            public String getWorkDaysText() {
                return workDaysText;
            }

            public void setWorkDaysText(String workDaysText) {
                this.workDaysText = workDaysText;
            }
        }
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
}
