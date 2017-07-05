package com.heinzan.restaurant_assign.data.persistence;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.heinzan.restaurant_assign.data.persistence.RestaurantsContract.RestaurantEntry;
import com.heinzan.restaurant_assign.data.persistence.RestaurantsContract.RestaurantTagsEntry;

/**
 * Created by HAZin on 7/4/2017.
 */

public class RestaurantDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "restaurant.db";

    private static final String SQL_CREATE_RESTAURANT_TABLE = "CREATE TABLE " + RestaurantEntry.TABLE_NAME + " (" +
            RestaurantEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            RestaurantEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
            RestaurantEntry.COLUMN_ADDR_SHORT + " TEXT NOT NULL, " +
            RestaurantEntry.COLUMN_IMAGE + " TEXT, " +
            RestaurantEntry.COLUMN_TOTAL_RATING_COUNT + " TEXT NOT NULL, " +
            RestaurantEntry.COLUMN_AVERAGE_RATING_VALUE + " TEXT NOT NULL, " +
            RestaurantEntry.COLUMN_IS_AD + " TEXT NOT NULL, " +
            RestaurantEntry.COLUMN_IS_NEW + " TEXT NOT NULL, " +
            RestaurantEntry.COLUMN_LEAD_TIME_IN_MIN + " TEXT NOT NULL, " +

            " UNIQUE (" + RestaurantEntry.COLUMN_TITLE + ") ON CONFLICT IGNORE" +
            " );";


    private static final String SQL_CREATE_ATTRACTION_IMAGE_TABLE = "CREATE TABLE " + RestaurantTagsEntry.TABLE_NAME + " (" +
            RestaurantTagsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            RestaurantTagsEntry.COLUMN_RESTAURANTS_TITLE + " TEXT NOT NULL, " +
            RestaurantTagsEntry.COLUMN_TAGS + " TEXT NOT NULL, " +


            " UNIQUE (" + RestaurantTagsEntry.COLUMN_RESTAURANTS_TITLE + ") ON CONFLICT IGNORE" +
            " );";

    public RestaurantDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_RESTAURANT_TABLE);
        db.execSQL(SQL_CREATE_ATTRACTION_IMAGE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + RestaurantEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + RestaurantTagsEntry.TABLE_NAME);

    }
}
