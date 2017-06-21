package com.heinzan.restaurant_assign.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.heinzan.restaurant_assign.R;
import com.heinzan.restaurant_assign.data.vos.RestaurantVO;
import com.heinzan.restaurant_assign.views.holders.BaseViewHolder;
import com.heinzan.restaurant_assign.views.holders.RestaurantViewHolder;

/**
 * Created by HAZin on 6/21/2017.
 */

public class RestaurantAdapter extends BaseRecyclerAdapter<RestaurantViewHolder,RestaurantVO> {


    public RestaurantAdapter(Context context) {
        super(context);
    }

    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=mLayoutInflater.inflate(R.layout.view_item_restaurant,parent,false);

        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder holder, int position) {
        holder.bind(mData.get(position));

    }
}
