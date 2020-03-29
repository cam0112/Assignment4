package com.example.assignment4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataHelp extends SQLiteOpenHelper {
    public static final String databaseName = "spending.db";
    public static final String tableName = "Spending";
    public static final String blank2 = "ITEMS";
    public static final String blank3 = "DATES";
    public static final String blank4 = "PRICES";
    public DataHelp(Context context){
        super(context, databaseName, null, 4);
    }
    @Override
    public void onCreate(SQLiteDatabase data){
        data.execSQL("CREATE TABLE " + tableName + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, ITEM varchar(250), DATE varchar(250), PRICE DOUBLE)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase data, int old, int New){
        data.execSQL("DROP TABLE IF EXISTS " + tableName);
        onCreate(data);
    }
    public boolean transaction(String items, String dates, double prices){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(blank2, items);
        values.put(blank3, dates);
        values.put(blank4, prices);
        long result = db.insert(tableName, null, values);
        if (result == -1) { return false; }
        return true;
    }
    public Cursor getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM " + tableName, null);
        return result;
    }
}