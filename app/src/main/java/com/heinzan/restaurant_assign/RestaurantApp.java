package com.heinzan.restaurant_assign;

import android.app.Application;
import android.content.Context;

import com.heinzan.restaurant_assign.data.agents.retrofit.RetrofitDataAgent;

/**
 * Created by HAZin on 7/4/2017.
 */

public class RestaurantApp extends Application {

    public static final String TAG = "RestaurantApp";

    public void onCreate(){
        RetrofitDataAgent.getInstance().loadRestaurants();
    }


    public static Context getContext() {
        return getContext();
    }
}
