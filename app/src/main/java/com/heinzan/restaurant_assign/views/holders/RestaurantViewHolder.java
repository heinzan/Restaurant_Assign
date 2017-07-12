package com.heinzan.restaurant_assign.views.holders;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.heinzan.restaurant_assign.R;
import com.heinzan.restaurant_assign.data.responses.RestaurantListResponse;
import com.heinzan.restaurant_assign.data.vos.RestaurantVO;

import butterknife.BindView;

/**
 * Created by HAZin on 6/21/2017.
 */

public class RestaurantViewHolder extends BaseViewHolder<RestaurantVO> {

    @BindView(R.id.img_item)
    ImageView imgitem;

    @BindView(R.id.ad_mark)
    ImageView admark;

    @BindView(R.id.myRatingBar)
    RatingBar my_ratingbar;

    @BindView(R.id.tv_rating_count)
    TextView rating_count;

    @BindView(R.id.tv_title)
    TextView tvtitle;

    @BindView(R.id.tv_tags)
    TextView tvtags;

    @BindView(R.id.tv_lead_time)
    TextView tvleadtime;

    @BindView(R.id.tv_add_short)
    TextView tvaddshort;

/*    @BindView(R.id.list_restaurant)
    EditText listRestaurant;*/


    private RestaurantVO mRestaurant;
    private RestaurantListResponse mRestaurnatListResponse;

    public RestaurantViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(RestaurantVO data) {

        mRestaurant = data;

        //listRestaurant.setText(mRestaurnatListResponse.getRestaurnatList().size());
        String addshort=data.getAddrShort();

        my_ratingbar.setRating(data.getAverageRatingValue().floatValue());
        rating_count.setText("(" + data.getTotalRatingCount() + ")");
        tvtitle.setText(data.getTitle() );

        if (addshort != null && !addshort.isEmpty() && !addshort.equals("null")){
            tvaddshort.setText(" ("+addshort+")");
        }else {
            tvaddshort.setVisibility(View.INVISIBLE);
        }
        tvleadtime.setText("delivers in " + data.getLeadTimeInMin() + " min");

        boolean isad = data.getAd();

        if (isad) {
            admark.setVisibility(View.VISIBLE);
        } else {
            admark.setVisibility(View.INVISIBLE);
        }

        StringBuilder builder = new StringBuilder();
        String[] arr = data.getTags();
        for (String s : arr) {
            if(builder.length()>0){
                builder.append(",");
            }
            builder.append(s);

        }

        tvtags.setText(builder.toString());

    }
}
