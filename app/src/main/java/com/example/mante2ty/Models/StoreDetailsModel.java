package com.example.mante2ty.Models;

import java.util.List;

public class StoreDetailsModel {

    public ResultBean result;
    public int statusCode;
    public String statusText;

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

        public int id;
        public String title;
        public String description;
        public String area;
        public String sub_area;
        public int sub_area_id;
        public String sub_sub_area;
        public int sub_sub_area_id;
        public String logo;
        public String lat;
        public String lng;
        public String address_description;
        public String video;
        public Object phone;
        public String mobile;
        public Object email;
        public Object website;
        public Object instagram;
        public String facebook;
        public Object twitter;
        public Object manager_name;
        public int views;
        public double rate;
        public boolean isFavorite;
        public int open_comment;
        public int open_rate;
        public int generate_qr;
        public String workDaysText;
        public List<FacilitiesBean> facilities;
        public List<String> images;
        public List<?> branches;
        public List<CommentsBean> comments;
        public List<?> nearStores;

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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getSub_area() {
            return sub_area;
        }

        public void setSub_area(String sub_area) {
            this.sub_area = sub_area;
        }

        public int getSub_area_id() {
            return sub_area_id;
        }

        public void setSub_area_id(int sub_area_id) {
            this.sub_area_id = sub_area_id;
        }

        public String getSub_sub_area() {
            return sub_sub_area;
        }

        public void setSub_sub_area(String sub_sub_area) {
            this.sub_sub_area = sub_sub_area;
        }

        public int getSub_sub_area_id() {
            return sub_sub_area_id;
        }

        public void setSub_sub_area_id(int sub_sub_area_id) {
            this.sub_sub_area_id = sub_sub_area_id;
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

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public Object getPhone() {
            return phone;
        }

        public void setPhone(Object phone) {
            this.phone = phone;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public Object getWebsite() {
            return website;
        }

        public void setWebsite(Object website) {
            this.website = website;
        }

        public Object getInstagram() {
            return instagram;
        }

        public void setInstagram(Object instagram) {
            this.instagram = instagram;
        }

        public String getFacebook() {
            return facebook;
        }

        public void setFacebook(String facebook) {
            this.facebook = facebook;
        }

        public Object getTwitter() {
            return twitter;
        }

        public void setTwitter(Object twitter) {
            this.twitter = twitter;
        }

        public Object getManager_name() {
            return manager_name;
        }

        public void setManager_name(Object manager_name) {
            this.manager_name = manager_name;
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

        public int getOpen_comment() {
            return open_comment;
        }

        public void setOpen_comment(int open_comment) {
            this.open_comment = open_comment;
        }

        public int getOpen_rate() {
            return open_rate;
        }

        public void setOpen_rate(int open_rate) {
            this.open_rate = open_rate;
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

        public List<FacilitiesBean> getFacilities() {
            return facilities;
        }

        public void setFacilities(List<FacilitiesBean> facilities) {
            this.facilities = facilities;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public List<?> getBranches() {
            return branches;
        }

        public void setBranches(List<?> branches) {
            this.branches = branches;
        }

        public List<CommentsBean> getComments() {
            return comments;
        }

        public void setComments(List<CommentsBean> comments) {
            this.comments = comments;
        }

        public List<?> getNearStores() {
            return nearStores;
        }

        public void setNearStores(List<?> nearStores) {
            this.nearStores = nearStores;
        }

        public static class FacilitiesBean {

            public int id;
            public String title;
            public String image;

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
        }

        public static class CommentsBean {

            public int id;
            public String username;
            public String image;
            public String comment;
            public int rate;
            public String created_at;

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
    }
}
