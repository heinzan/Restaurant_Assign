package com.heinzan.restaurant_assign.views.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.heinzan.restaurant_assign.R;
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


    private RestaurantVO mRestaurant;

    public RestaurantViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(RestaurantVO data) {

        mRestaurant = data;

        my_ratingbar.setRating(data.getAverageRatingValue().floatValue());
        rating_count.setText("(" + data.getTotalRatingCount() + ")");
        tvtitle.setText(data.getTitle() + "(" + data.getAddrShort() + ")");
        tvleadtime.setText("delivers in " + data.getLeadTimeInMin() + "min");

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
            builder.append(s).append(s);
        }
        tvtags.setText(builder.toString());
    }
}
