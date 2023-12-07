package com.example.hotelview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {


    // Create the constructor for initializing the database and setting its name and version
    public DBHelper(Context context ) {
        super(context,"UserData.db",null, 1);
    }

    //Create the SQLite database table
    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table UserDetails(userID TEXT primary key,name TEXT,password INTEGER,number INTEGER)");
    }

    //Make sure of upgrading the db schema if the database version changes
    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists UserDetails");
    }

    // Inserting user data into an SQLite database table
    public Boolean insetUserData(String name,String number,String email,String password){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("number",number);
        contentValues.put("userID",email);
        contentValues.put("password",password);

        long result= DB.insert("UserDetails",null,contentValues);
        if (result == -1){
            return false;
        }else {
            return true;
        }
    }

    // Retrieving data from an SQLite database table named 'Userdetails'
    public Cursor getData(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails",null);
        return cursor;
    }
}