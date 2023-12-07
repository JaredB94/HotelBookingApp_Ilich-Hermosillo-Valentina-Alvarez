package com.example.hotelview.paymentdetails;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PaymentHelper extends SQLiteOpenHelper {
    public PaymentHelper(Context context) {
        super(context, "PaymentDetails.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE PaymentDetails (detailID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "txtHolderName TEXT, " +
                "displayHotelName TEXT, " +
                "displayRoomType TEXT, " +
                "displayCheckInDate TEXT, " +
                "displayCheckOutDate TEXT, " +
                "displayNumP TEXT, " +
                "displayNumNight TEXT, " +
                "txtExpirDate TEXT, " +
                "txtCardNumber TEXT, " +
                "txtCVC TEXT, " +
                "displayFinalPrice TEXT" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("DROP TABLE IF EXISTS PaymentDetails");
        onCreate(DB);
    }

    public boolean insertUserData(String txtHolderName, String displayHotelName, String displayRoomType,
                                  String displayCheckInDate, String displayCheckOutDate, String displayNumP,
                                  String displayNumNight, String txtExpirDate, String txtCardNumber,
                                  String txtCVC, String displayFinalPrice) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("txtHolderName", txtHolderName);
        contentValues.put("displayHotelName", displayHotelName);
        contentValues.put("displayRoomType", displayRoomType);
        contentValues.put("displayCheckInDate", displayCheckInDate);
        contentValues.put("displayCheckOutDate", displayCheckOutDate);
        contentValues.put("displayNumP", displayNumP);
        contentValues.put("displayNumNight", displayNumNight);
        contentValues.put("txtExpirDate", txtExpirDate);
        contentValues.put("txtCardNumber", txtCardNumber);
        contentValues.put("txtCVC", txtCVC);
        contentValues.put("displayFinalPrice", displayFinalPrice);

        long result = DB.insert("PaymentDetails", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getData() {
        SQLiteDatabase DB = this.getReadableDatabase();
        return DB.rawQuery("SELECT * FROM PaymentDetails ORDER BY detailID DESC LIMIT 1", null);
    }
}


