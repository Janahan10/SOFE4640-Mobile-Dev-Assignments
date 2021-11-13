package com.example.geolocatorapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class LocationDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "locations.db";
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
          LATITUDE + " blob not null, " +
          LONGITUDE + " blob not null" +
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

    public List<LocationModel> getAllLocations() {
        List<LocationModel> result = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from " + LOCATION_TABLE;

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String address = cursor.getString(1);
                Double latitude = cursor.getDouble(2);
                Double longitude = cursor.getDouble(3);


                LocationModel location = new LocationModel(id, latitude, longitude, address);
                result.add(location);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return result;
    }
}
