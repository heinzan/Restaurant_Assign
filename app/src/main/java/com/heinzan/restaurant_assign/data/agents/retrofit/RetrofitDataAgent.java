package com.heinzan.restaurant_assign.data.agents.retrofit;

import android.util.Log;

import com.google.gson.Gson;
import com.heinzan.restaurant_assign.data.agents.RestaurantDataAgent;
import com.heinzan.restaurant_assign.data.responses.RestaurantListResponse;
import com.heinzan.restaurant_assign.events.RestaurantLoadEvent;
import com.heinzan.restaurant_assign.utils.RestaurantConstants;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by HAZin on 6/21/2017.
 */

public class RetrofitDataAgent implements RestaurantDataAgent {

    private static RetrofitDataAgent objInstance;

    private final RestaurantApi theApi;

    private RetrofitDataAgent() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RestaurantConstants.RESTAURANTS_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(okHttpClient)
                .build();

        theApi = retrofit.create(RestaurantApi.class);
    }

    public static RetrofitDataAgent getInstance() {
        if (objInstance == null) {
            objInstance = new RetrofitDataAgent();
        }
        return objInstance;
    }

    public void loadRestaurants() {
        Call<RestaurantListResponse> loadRestaurantCall = theApi.loadRestaurants();

        loadRestaurantCall.enqueue(new Callback<RestaurantListResponse>() {
            @Override
            public void onResponse(Call<RestaurantListResponse> call, Response<RestaurantListResponse> response) {

                RestaurantListResponse restaurantListResponse=response.body();
                Log.d("APICall","RESPONSE"+restaurantListResponse);

                EventBus.getDefault().post(new RestaurantLoadEvent(restaurantListResponse.getRestaurnatList()));


            }

            @Override
            public void onFailure(Call<RestaurantListResponse> call, Throwable t) {

            }
        });

            }

}
