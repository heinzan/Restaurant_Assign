package com.heinzan.restaurant_assign.events;

import com.heinzan.restaurant_assign.data.vos.RestaurantVO;

import java.util.List;

/**
 * Created by HAZin on 6/22/2017.
 */

public class RestaurantLoadEvent {
    private List<RestaurantVO> restaurantVOList;

    public RestaurantLoadEvent(List<RestaurantVO> restaurantVOList) {
        this.restaurantVOList = restaurantVOList;
    }


    public List<RestaurantVO> getRestaurantVOList() {
        return restaurantVOList;
    }
}
