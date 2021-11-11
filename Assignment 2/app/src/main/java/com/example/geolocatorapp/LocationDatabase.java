package com.example.geolocatorapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LocationDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Locations.db";
    private static final String LOCATION_TABLE = "locations";
    private static final String LOCATION_ID = "id";
    private static final String ADDRESS = "address";
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";

    public LocationDatabase(Context context) {

        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
          "create table " + LOCATION_TABLE + " (" +
          LOCATION_ID + " integer primary key autoincrement, " +
          ADDRESS + " text not null, " +
          LATITUDE + " real not null, " +
          LONGITUDE + " real not null" +
          ");"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + LOCATION_TABLE);
        onCreate(db);
    }

    public boolean addLocation(LocationModel location) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ADDRESS, location.getAddress());
        values.put(LATITUDE, location.getLatitude());
        values.put(LONGITUDE, location.getLongitude());

        long insertStatus = db.insert(LOCATION_TABLE, null, values);

        if (insertStatus == -1) {
            return false;
        }

        return true;
    }
}
