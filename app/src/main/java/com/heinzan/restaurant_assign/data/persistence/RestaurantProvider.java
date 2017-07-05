package com.heinzan.restaurant_assign.data.persistence;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.heinzan.restaurant_assign.utils.RestaurantConstants;

/**
 * Created by HAZin on 7/4/2017.
 */

public class RestaurantProvider extends ContentProvider {

    public static final int RESTAURANT = 100;
    public static final int RESTAURANTS_TAGS = 200;

    private static final String sAttractionTitleSelection = RestaurantsContract.RestaurantEntry.COLUMN_TITLE + " = ?";

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private RestaurantDBHelper mRestaurantDBHelper;

    private static UriMatcher buildUriMatcher() {
        final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(RestaurantsContract.CONTENT_AUTHORITY, RestaurantsContract.PATH_RESTAURANTS, RESTAURANT);
        uriMatcher.addURI(RestaurantsContract.CONTENT_AUTHORITY, RestaurantsContract.PATH_RESTAURANTS_TAGS, RESTAURANTS_TAGS);

        return uriMatcher;

    }


    @Override
    public boolean onCreate() {
        mRestaurantDBHelper = new RestaurantDBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor queryCursor;

        int matchUri = sUriMatcher.match(uri);
        switch (matchUri){
            case RESTAURANT:
                String attractionTitle = RestaurantsContract.RestaurantEntry.getTitleFromParam(uri);
                if (!TextUtils.isEmpty(attractionTitle)) {
                    selection = sAttractionTitleSelection;
                    selectionArgs = new String[]{attractionTitle};
                }
                queryCursor = mRestaurantDBHelper.getReadableDatabase().query(RestaurantsContract.RestaurantEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null, //group_by
                        null, //having
                        sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri : " + uri);

        }
        Context context = getContext();
        if (context != null) {
            queryCursor.setNotificationUri(context.getContentResolver(), uri);
        }

        return queryCursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        final int matchUri = sUriMatcher.match(uri);
        switch (matchUri) {
            case RESTAURANT:
                return RestaurantsContract.RestaurantEntry.DIR_TYPE;
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
