package com.heinzan.restaurant_assign.activities;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.heinzan.restaurant_assign.R;
import com.heinzan.restaurant_assign.adapters.RestaurantAdapter;
import com.heinzan.restaurant_assign.data.agents.retrofit.RetrofitDataAgent;
import com.heinzan.restaurant_assign.data.responses.RestaurantListResponse;
import com.heinzan.restaurant_assign.data.vos.RestaurantVO;
import com.heinzan.restaurant_assign.events.RestaurantLoadEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.rv_restaurant)
    RecyclerView rvRestaurant;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout srl;

    @BindView(R.id.list_restaurant)
    TextView listRestaurant;

    private RestaurantAdapter mRestaurantAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this,this);

        RetrofitDataAgent.getInstance().loadRestaurants();

        mRestaurantAdapter=new RestaurantAdapter(this);
        rvRestaurant.setLayoutManager(new LinearLayoutManager(this));
        rvRestaurant.setAdapter(mRestaurantAdapter);



        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvRestaurant.getContext(),DividerItemDecoration.VERTICAL);
        rvRestaurant.addItemDecoration(dividerItemDecoration);

    }

        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_search) {
            Toast.makeText(getApplicationContext(), "Clicked Search icon", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void RestaurantListEvent(RestaurantLoadEvent event) {

        srl.setRefreshing(false);
        mRestaurantAdapter.setNewData(event.getRestaurantVOList());
        //String listCount = String.valueOf(event.getRestaurantVOList().size());
        listRestaurant.setText("  "+String.valueOf(event.getRestaurantVOList().size())+" restaurants deliver to you");
    }
}
