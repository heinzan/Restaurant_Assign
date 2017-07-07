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

    private List<RestaurantVO> mRestaurantList;

    private Context mContext;

    @BindView(R.id.list_restaurant)
    TextView listRestaurant;

    private RestaurantModel() {
        super();
        mRestaurantList = new ArrayList<>();

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    public static RestaurantModel getInstance() {
        if (objInstance == null) {
            objInstance = new RestaurantModel();
        }
        return objInstance;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public void loadRestaurants() {
        dataAgent.loadRestaurants();
    }

    public List<RestaurantVO> getmRestaurantList() {
        return mRestaurantList;
    }

    /*public void notifyAttractionsLoaded(List<RestaurantVO> restaurantList) {
        //Notify that the data is ready - using LocalBroadcast
        mRestaurantList = restaurantList;

        //keep the data in persistent layer.
        RestaurantVO.saveRestaurants(context, mRestaurantList);

        //broadcastAttractionLoadedWithEventBus();
        //broadcastAttractionLoadedWithLocalBroadcastManager();
    }*/

    public void setStoredData(List<RestaurantVO> restaurantList) {
        mRestaurantList = restaurantList;
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void receiveRestaurantList(RestaurantLoadEvent event) {
        mRestaurantList= event.getRestaurantVOList();
        Log.d("RestaurantLIst",""+event.getRestaurantVOList().get(1).getAd());
        RestaurantVO.saveRestaurants(mContext, mRestaurantList);

    }
}
