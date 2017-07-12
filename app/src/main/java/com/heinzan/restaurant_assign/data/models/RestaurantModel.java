package com.heinzan.restaurant_assign.data.models;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.heinzan.restaurant_assign.R;
import com.heinzan.restaurant_assign.data.vos.RestaurantVO;
import com.heinzan.restaurant_assign.events.RestaurantLoadEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by HAZin on 7/6/2017.
 */

public class RestaurantModel extends BaseModel {
    public static final String BROADCAST_DATA_LOADED = "BROADCAST_DATA_LOADED";

    private static RestaurantModel objInstance;

    private Context mContext;

    private RestaurantModel(Context context) {
        super();
    }

    public static RestaurantModel getInstance() {
        if (objInstance == null) {
            throw new RuntimeException("Error");
        }
        return objInstance;
    }

    public static void init(Context context) {
        objInstance = new RestaurantModel(context);
    }

    public void loadRestaurants() {
        dataAgent.loadRestaurants();
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void receiveRestaurantList(RestaurantLoadEvent event) {
        Log.d("RestaurantLIst",""+event.getRestaurantVOList().get(1).getAd());
        RestaurantVO.saveRestaurants(mContext, event.getRestaurantVOList());

    }
}
