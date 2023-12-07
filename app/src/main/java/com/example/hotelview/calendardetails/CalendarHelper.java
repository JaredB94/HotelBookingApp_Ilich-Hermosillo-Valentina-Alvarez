package com.example.hotelview.calendardetails;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CalendarHelper extends SQLiteOpenHelper {

    public CalendarHelper(Context context) {

        super(context, "CalendarDates.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table CalendarDates(detailID INTEGER PRIMARY KEY AUTOINCREMENT, txtCheckInDate TEXT, txtCheckOutDate TEXT, txtnumNights INTEGER, txtnumPersons INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("DROP TABLE IF EXISTS CalendarDates");
        onCreate(DB);
    }

    public boolean insertUserData(String txtCheckInDate, String txtCheckOutDate, int txtnumNights, int txtnumPersons) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("txtCheckInDate", txtCheckInDate);
        contentValues.put("txtCheckOutDate", txtCheckOutDate);
        contentValues.put("txtnumNights", txtnumNights);
        contentValues.put("txtnumPersons", txtnumPersons);

        long result = DB.insert("CalendarDates", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getData() {
        SQLiteDatabase DB = this.getReadableDatabase();
        return DB.rawQuery("SELECT * FROM CalendarDates ORDER BY detailID DESC LIMIT 1", null);
    }
}
