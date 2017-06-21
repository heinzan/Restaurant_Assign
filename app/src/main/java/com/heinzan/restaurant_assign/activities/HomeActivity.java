package com.heinzan.restaurant_assign.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.heinzan.restaurant_assign.R;
import com.heinzan.restaurant_assign.adapters.RestaurantAdapter;
import com.heinzan.restaurant_assign.data.agents.retrofit.RetrofitDataAgent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.rv_restaurant)
    RecyclerView rvRestaurant;

    private RestaurantAdapter mRestaurantAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this,this);

        mRestaurantAdapter=new RestaurantAdapter(this);
        rvRestaurant.setLayoutManager(new LinearLayoutManager(this));
        rvRestaurant.setAdapter(mRestaurantAdapter);

        RetrofitDataAgent.getInstance().loadRestaurants();


    }

    @Override
    protected void onStart() {
        super.onStart();
      /*  if(!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }*/
    }


    @Override
    protected void onStop() {
        super.onStop();
       /* if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }*/
    }
}
