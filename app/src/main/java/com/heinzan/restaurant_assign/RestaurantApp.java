package com.heinzan.restaurant_assign;

import android.app.Application;
import android.content.ContentProvider;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.heinzan.restaurant_assign.data.agents.retrofit.RetrofitDataAgent;
import com.heinzan.restaurant_assign.data.models.RestaurantModel;

/**
 * Created by HAZin on 7/4/2017.
 */

public class RestaurantApp extends Application {

    public static final String TAG = "RestaurantApp";

    private static Context context;

    public void onCreate(){
        Stetho.initializeWithDefaults(this);
        context = getApplicationContext();
        //RetrofitDataAgent.getInstance().loadRestaurants();
        RestaurantModel.getInstance(getApplicationContext()).loadRestaurants();
    }

}
