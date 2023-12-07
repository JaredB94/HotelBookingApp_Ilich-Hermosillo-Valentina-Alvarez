package com.example.hotelview.roomdetails;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RoomDetailsHelper extends SQLiteOpenHelper {

    //To create the SQLie database
    public RoomDetailsHelper(Context context) {
        super(context, "RoomDetails.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table RoomDetails(roomID INTEGER PRIMARY KEY AUTOINCREMENT, RoomType TEXT, NumPeople INTEGER, RoomSize INTEGER, RoomDetails TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("DROP TABLE IF EXISTS RoomDetails");
        onCreate(DB);
    }

    public boolean insertUserData(String RoomType, int NumPeople, int RoomSize, String RoomDetails) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("RoomType", RoomType);
        contentValues.put("NumPeople", NumPeople);
        contentValues.put("RoomSize", RoomSize);
        contentValues.put("RoomDetails", RoomDetails);


        long result = DB.insert("RoomDetails", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getData() {
        SQLiteDatabase DB = this.getReadableDatabase();
        return DB.rawQuery("SELECT * FROM RoomDetails ORDER BY roomID DESC LIMIT 1", null);
    }
}
