package com.heinzan.restaurant_assign.data.agents.retrofit;

import com.heinzan.restaurant_assign.data.responses.RestaurantListResponse;
import com.heinzan.restaurant_assign.utils.RestaurantConstants;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by HAZin on 6/21/2017.
 */

public interface RestaurantApi {

    @GET(RestaurantConstants.API_GET_RESTAURANT_LIST)
    Call<RestaurantListResponse> loadRestaurants();
}
