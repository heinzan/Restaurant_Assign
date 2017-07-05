package com.heinzan.restaurant_assign.data.persistence;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

import com.heinzan.restaurant_assign.RestaurantApp;

import retrofit2.http.PUT;

/**
 * Created by HAZin on 7/4/2017.
 */

public class RestaurantsContract {

    public static final String CONTENT_AUTHORITY= RestaurantApp.class.getPackage().getName();

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_RESTAURANTS = "restaurantss";
    public static final String PATH_RESTAURANTS_TAGS = "restaurant_tags";

    public static final class RestaurantEntry implements BaseColumns{
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_RESTAURANTS).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RESTAURANTS;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RESTAURANTS;

        public static final String TABLE_NAME = "restaurants";

        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_ADDR_SHORT="add_short";
        public static final String COLUMN_IMAGE="image";
        public static final String COLUMN_TOTAL_RATING_COUNT="total_rating_count";
        public static final String COLUMN_AVERAGE_RATING_VALUE="average_rating_value";
        public static final String COLUMN_IS_AD="is_ad";
        public static final String COLUMN_IS_NEW="is_new";
        public static final String COLUMN_LEAD_TIME_IN_MIN="lead_time_in_min";

        public static String getTitleFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_TITLE);
        }

    }

    public static final class RestaurantTagsEntry implements BaseColumns{
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_RESTAURANTS_TAGS).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RESTAURANTS_TAGS;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RESTAURANTS_TAGS;

        public static final String TABLE_NAME = "restaurants_tags";

        public static final String COLUMN_RESTAURANTS_TITLE="restaurants_title";
        public static final String COLUMN_TAGS="tags";



    }
}
