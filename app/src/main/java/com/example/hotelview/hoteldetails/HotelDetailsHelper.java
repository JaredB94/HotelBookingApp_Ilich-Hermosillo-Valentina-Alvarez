package com.example.hotelview.hoteldetails;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HotelDetailsHelper extends SQLiteOpenHelper {
    public HotelDetailsHelper(Context context) {
        super(context, "HotelDetails.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table HotelDetails(detailID INTEGER PRIMARY KEY AUTOINCREMENT, txtTitleHotel TEXT, txtDescription TEXT, txtAmenitiesD TEXT, txtExactLocation TEXT, PriceHotel INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("DROP TABLE IF EXISTS HotelDetails");
        onCreate(DB);
    }

    public boolean insertUserData(String txtTitleHotel, String txtDescription, String txtAmenitiesD, String txtExactLocation) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("txtTitleHotel", txtTitleHotel);
        contentValues.put("txtDescription", txtDescription);
        contentValues.put("txtAmenitiesD", txtAmenitiesD);
        contentValues.put("txtExactLocation", txtExactLocation);


        long result = DB.insert("HotelDetails", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getData() {
        SQLiteDatabase DB = this.getReadableDatabase();
        return DB.rawQuery("SELECT * FROM HotelDetails ORDER BY detailID DESC LIMIT 1", null);
    }
}
