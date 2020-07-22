package com.example.mante2ty.Models;

import java.util.List;

public class ConfigsModel {

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

        public ConfigBean config;
        public List<CategoriesBean> categories;
        public List<OffersBean> offers;
        public List<AreasBean> areas;
        public List<EmergencyPlacesBean> emergencyPlaces;
        public List<String> contactUsTypes;
        public List<SlidersBean> sliders;

        public ConfigBean getConfig() {
            return config;
        }

        public void setConfig(ConfigBean config) {
            this.config = config;
        }

        public List<CategoriesBean> getCategories() {
            return categories;
        }

        public void setCategories(List<CategoriesBean> categories) {
            this.categories = categories;
        }

        public List<OffersBean> getOffers() {
            return offers;
        }

        public void setOffers(List<OffersBean> offers) {
            this.offers = offers;
        }

        public List<AreasBean> getAreas() {
            return areas;
        }

        public void setAreas(List<AreasBean> areas) {
            this.areas = areas;
        }

        public List<EmergencyPlacesBean> getEmergencyPlaces() {
            return emergencyPlaces;
        }

        public void setEmergencyPlaces(List<EmergencyPlacesBean> emergencyPlaces) {
            this.emergencyPlaces = emergencyPlaces;
        }

        public List<String> getContactUsTypes() {
            return contactUsTypes;
        }

        public void setContactUsTypes(List<String> contactUsTypes) {
            this.contactUsTypes = contactUsTypes;
        }

        public List<SlidersBean> getSliders() {
            return sliders;
        }

        public void setSliders(List<SlidersBean> sliders) {
            this.sliders = sliders;
        }

        public static class ConfigBean {

            public int id;
            public String email;
            public String mobile;
            public String facebook;
            public String google;
            public String twitter;
            public String instagram;
            public String terms;
            public String about;
            public String interface_title;
            public String interface_image;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getFacebook() {
                return facebook;
            }

            public void setFacebook(String facebook) {
                this.facebook = facebook;
            }

            public String getGoogle() {
                return google;
            }

            public void setGoogle(String google) {
                this.google = google;
            }

            public String getTwitter() {
                return twitter;
            }

            public void setTwitter(String twitter) {
                this.twitter = twitter;
            }

            public String getInstagram() {
                return instagram;
            }

            public void setInstagram(String instagram) {
                this.instagram = instagram;
            }

            public String getTerms() {
                return terms;
            }

            public void setTerms(String terms) {
                this.terms = terms;
            }

            public String getAbout() {
                return about;
            }

            public void setAbout(String about) {
                this.about = about;
            }

            public String getInterface_title() {
                return interface_title;
            }

            public void setInterface_title(String interface_title) {
                this.interface_title = interface_title;
            }

            public String getInterface_image() {
                return interface_image;
            }

            public void setInterface_image(String interface_image) {
                this.interface_image = interface_image;
            }
        }

        public static class CategoriesBean {

            public int id;
            public String title;
            public Object image;

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

            public void setImage(Object image) {
                this.image = image;
            }
        }

        public static class OffersBean {

            public int id;
            public String title;
            public String body;
            public String image;
            public int type;
            public String type_text;
            public String start_date;
            public String end_date;

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
        }

        public static class AreasBean {

            public int id;
            public String title;
            public List<ChildrenBeanX> children;

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

            public List<ChildrenBeanX> getChildren() {
                return children;
            }

            public void setChildren(List<ChildrenBeanX> children) {
                this.children = children;
            }

            public static class ChildrenBeanX {

                public int id;
                public String title;
                public List<ChildrenBean> children;

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

                public List<ChildrenBean> getChildren() {
                    return children;
                }

                public void setChildren(List<ChildrenBean> children) {
                    this.children = children;
                }

                public static class ChildrenBean {

                    public int id;
                    public String title;

                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }
                }
            }
        }

        public static class EmergencyPlacesBean {

            public int id;
            public String title;
            public Object title_en;
            public String phone;

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

            public Object getTitle_en() {
                return title_en;
            }

            public void setTitle_en(Object title_en) {
                this.title_en = title_en;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }
        }

        public static class SlidersBean {

            public int id;
            public String image;
            public String title;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
