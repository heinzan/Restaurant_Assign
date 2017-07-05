package com.heinzan.restaurant_assign.data.vos;

import android.content.Context;

import com.google.gson.annotations.SerializedName;
import com.heinzan.restaurant_assign.RestaurantApp;

import java.util.List;

/**
 * Created by HAZin on 6/20/2017.
 */

public class RestaurantVO {

    @SerializedName("title")
    private String title;

    @SerializedName("addr-short")
    private String addrShort;

    @SerializedName("total-rating-count")
    private int totalRatingCount;

    @SerializedName("image")
    private String image;

    @SerializedName("average-rating-value")
    private Double averageRatingValue;

    @SerializedName("is-ad")
    private Boolean isAd;

    @SerializedName("tags")

    private String[] tags;

    @SerializedName("lead-time-in-min")
    private Integer leadTimeInMin;

    @SerializedName("is-new")
    private Boolean isNew;

    public String[] getTags() {
        return tags;
    }

    public String getTitle() {
        return title;
    }

    public String getAddrShort() {
        return addrShort;
    }

    public int getTotalRatingCount() {
        return totalRatingCount;
    }

    public String getImage() {
        return image;
    }

    public Double getAverageRatingValue() {
        return averageRatingValue;
    }

    public Boolean getAd() {
        return isAd;
    }

    public Integer getLeadTimeInMin() {
        return leadTimeInMin;
    }

    public Boolean getNew() {
        return isNew;
    }

    public static void saveRestaurants(List<RestaurantVO> restaurantlist){
        Context context = RestaurantApp.getContext();

    }
}