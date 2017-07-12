package com.heinzan.restaurant_assign.data.vos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.google.gson.annotations.SerializedName;
import com.heinzan.restaurant_assign.RestaurantApp;
import com.heinzan.restaurant_assign.data.persistence.RestaurantsContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HAZin on 6/20/2017.
 */

public class RestaurantVO {

    @SerializedName("title")
    private String title;

    @SerializedName("addr-short")
    private String addrShort;

    @SerializedName("total-rating-count")
    private int totalRatingCount;

    @SerializedName("image")
    private String image;

    @SerializedName("average-rating-value")
    private Double averageRatingValue;

    @SerializedName("is-ad")
    private boolean isAd;

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    @SerializedName("tags")
    private String[] tags;

    @SerializedName("lead-time-in-min")
    private Integer leadTimeInMin;

    @SerializedName("is-new")
    private Boolean isNew;

    public String[] getTags() {
        return tags;
    }

    public String getTitle() {
        return title;
    }

    public String getAddrShort() {
        return addrShort;
    }

    public int getTotalRatingCount() {
        return totalRatingCount;
    }

    public String getImage() {
        return image;
    }

    public Double getAverageRatingValue() {
        return averageRatingValue;
    }

    public Boolean getAd() {
        return isAd;
    }

    public Integer getLeadTimeInMin() {
        return leadTimeInMin;
    }

    public Boolean getNew() {
        return isNew;
    }

    public static void saveRestaurants(Context context, List<RestaurantVO> restaurantlist){

        ContentValues[] restaurantCVs = new ContentValues[restaurantlist.size()];
        for (int index = 0; index < restaurantlist.size(); index++) {
            RestaurantVO restaurant = restaurantlist.get(index);
            restaurantCVs[index] = restaurant.parseToContentValues();

            //Bulk insert into restaurant_tags.
            RestaurantVO.saveRestaurantsTags(context,restaurant.getTitle(), restaurant.getTags());
        }
        int insertedCount = context.getContentResolver().bulkInsert(RestaurantsContract.RestaurantEntry.CONTENT_URI, restaurantCVs);

        Log.d(RestaurantApp.TAG, "Bulk inserted into attraction table : " + insertedCount);

    }

    private static void saveRestaurantsTags(Context context,String title, String[] tags) {
        ContentValues[] restaurantTagCVs = new ContentValues[tags.length];
        for (int index = 0; index < tags.length; index++) {
            String tag = tags[index];

            ContentValues cv = new ContentValues();
            cv.put(RestaurantsContract.RestaurantTagsEntry.COLUMN_RESTAURANTS_TITLE, title);
            cv.put(RestaurantsContract.RestaurantTagsEntry.COLUMN_TAGS, tag);

            restaurantTagCVs[index] = cv;
        }


        int insertCount = context.getContentResolver().bulkInsert(RestaurantsContract.RestaurantTagsEntry.CONTENT_URI, restaurantTagCVs);

        Log.d(RestaurantApp.TAG, "Bulk inserted into attraction table : " + insertCount);
    }

    private ContentValues parseToContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(RestaurantsContract.RestaurantEntry.COLUMN_TITLE, title);
        cv.put(RestaurantsContract.RestaurantEntry.COLUMN_ADDR_SHORT, addrShort);
        cv.put(RestaurantsContract.RestaurantEntry.COLUMN_TOTAL_RATING_COUNT, totalRatingCount);
        cv.put(RestaurantsContract.RestaurantEntry.COLUMN_IMAGE, image);
        cv.put(RestaurantsContract.RestaurantEntry.COLUMN_AVERAGE_RATING_VALUE, averageRatingValue);
        cv.put(RestaurantsContract.RestaurantEntry.COLUMN_IS_AD, isAd);
        cv.put(RestaurantsContract.RestaurantEntry.COLUMN_LEAD_TIME_IN_MIN, leadTimeInMin);
        cv.put(RestaurantsContract.RestaurantEntry.COLUMN_IS_NEW, isNew);
        return cv;


    }

    public static RestaurantVO parseFromCursor(Cursor data) {
        RestaurantVO restaurant = new RestaurantVO();
        restaurant.title = data.getString(data.getColumnIndex(RestaurantsContract.RestaurantEntry.COLUMN_TITLE));
        restaurant.addrShort = data.getString(data.getColumnIndex(RestaurantsContract.RestaurantEntry.COLUMN_ADDR_SHORT));
        restaurant.totalRatingCount = Integer.parseInt(data.getString(data.getColumnIndex(RestaurantsContract.RestaurantEntry.COLUMN_TOTAL_RATING_COUNT)));
        restaurant.image = data.getString(data.getColumnIndex(RestaurantsContract.RestaurantEntry.COLUMN_IMAGE));
        restaurant.averageRatingValue = Double.valueOf(data.getString(data.getColumnIndex(RestaurantsContract.RestaurantEntry.COLUMN_AVERAGE_RATING_VALUE)));
        restaurant.isAd =  data.getInt(data.getColumnIndex(RestaurantsContract.RestaurantEntry.COLUMN_IS_AD)) > 0;
        restaurant.leadTimeInMin = Integer.valueOf(data.getString(data.getColumnIndex(RestaurantsContract.RestaurantEntry.COLUMN_LEAD_TIME_IN_MIN)));
        restaurant.isNew = data.getInt(data.getColumnIndex(RestaurantsContract.RestaurantEntry.COLUMN_IS_NEW)) > 0;
        return restaurant;
    }

    public static String[] loadRestaurnatTagsTitle(Context context,String title) {

        ArrayList<String> tags = new ArrayList<>();

        Cursor cursor = context.getContentResolver().query(RestaurantsContract.RestaurantTagsEntry.buildRestaurnatTagsWithTitle(title),
                null, null, null, null);

        if(cursor != null && cursor.moveToFirst()) {
            do {
                tags.add(cursor.getString(cursor.getColumnIndex(RestaurantsContract.RestaurantTagsEntry.COLUMN_TAGS)));
            } while (cursor.moveToNext());
        }

        String[] imageArray = new String[tags.size()];
        tags.toArray(imageArray);
        return imageArray;
    }
}