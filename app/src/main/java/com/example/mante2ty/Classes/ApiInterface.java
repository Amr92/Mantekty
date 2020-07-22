package com.example.mante2ty.Classes;

import com.example.mante2ty.Models.ConfigsModel;
import com.example.mante2ty.Models.FavouriteItemInfo;
import com.example.mante2ty.Models.GetCommentsModel;
import com.example.mante2ty.Models.NotificationsModel;
import com.example.mante2ty.Models.StoreDetailsModel;
import com.example.mante2ty.Models.SubCategoryModel;
import com.example.mante2ty.Models.UserInfo;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    @Headers({"version: v1","Accept: application/json"})
    @POST("api/register")
    Call<UserInfo> registerUser(@Body Map<String,Object> map);

    @Headers("version: v1")
    @GET("api/configs")
    Call<ConfigsModel> getCategories();

    @Headers("version: v1")
    @GET("api/categories/{subCatId}")
    Call<SubCategoryModel> getSubCategories(@Path("subCatId") int subCat_id);

    @Headers({"version: v1","Accept: application/json"})
    @GET("api/stores/{store_id}")
    Call<StoreDetailsModel> getStoreDetails(@Path("store_id") int storeId);

    @Headers({"version: v1","Accept: application/json"})
    @POST("api/favorites")
    Call<ResponseBody> addToFavorite(@Header("Authorization") String token,@Field("store_id") int storeId);

    @Headers({"version: v1","Accept: application/json"})
    @GET("api/favorites")
    Call<FavouriteItemInfo> getFavoriteList(@Header("Authorization") String token);

    @Headers({"version: v1","Accept: application/json"})
    @GET("api/stores/comments/{id}")
    Call<GetCommentsModel> getCommentsList(@Path("id") int storeId);

    @Headers({"version: v1","Accept: application/json"})
    @GET("api/notifications")
    Call<NotificationsModel> getNotifications(@Header("Authorization") String accessToken);

}
