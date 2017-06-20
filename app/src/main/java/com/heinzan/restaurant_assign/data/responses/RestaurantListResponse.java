package com.heinzan.restaurant_assign.data.responses;

import com.google.gson.annotations.SerializedName;
import com.heinzan.restaurant_assign.data.vos.RestaurantVO;

import java.util.ArrayList;

/**
 * Created by HAZin on 6/20/2017.
 */

public class RestaurantListResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("timestamp")
    private String timestamp;

    @SerializedName("restaurants")
    private ArrayList<RestaurantVO> restaurnatList;

    public String getTimestamp() {
        return timestamp;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<RestaurantVO> getRestaurnatList() {
        return restaurnatList;
    }


}
