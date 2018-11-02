package com.a2340.creativefirehoses.firehosetracker;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class SQliteHelperItems extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "ItemsDatabase";
    private static final int DATABASE_VERSION = 1;

    public SQliteHelperItems (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE items (itemName TEXT PRIMARY KEY, timeStamp Text, location Text, shortDescription Text, fullDescription Text, value Text, category Text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {
        db.execSQL("DROP TABLE IF EXISTS items");
        onCreate(db);
    }

    public boolean saveItem (String itemName, String timeStamp, String location, String shortDescription, String fullDescription, String value, String category)
    {
        Cursor cursor = getItem(itemName);

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("timeStamp", timeStamp);
        contentValues.put("location", location);
        contentValues.put("shortDescription", shortDescription);
        contentValues.put("fullDescription", fullDescription);
        contentValues.put("value", value);
        contentValues.put("category", category);


        long result;
        if (cursor.getCount() == 0) { // Record does not exist
            contentValues.put("itemName", itemName);
            result = db.insert("items", null, contentValues);
        } else { // Record exists
            result = db.update("items", contentValues, "itemName=?", new String[] { itemName });
        }

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getItemsFromCategory(String category, String location){

        SQLiteDatabase db = this.getReadableDatabase();

        if (location.equals("All")) {
            String sql = "SELECT * FROM items WHERE category=?";

            return db.rawQuery(sql, new String[] { category });
        }
        String sql = "SELECT * FROM items WHERE category=? AND location=?";

        return db.rawQuery(sql, new String[] { category, location});
    }

    public Cursor getItemsFromName(String name, String location){

        SQLiteDatabase db = this.getReadableDatabase();

        if (location.equals("All")) {
            String sql = "SELECT * FROM items WHERE itemName=?";

            return db.rawQuery(sql, new String[] { name });
        }
        String sql = "SELECT * FROM items WHERE name=? AND location=?";

        return db.rawQuery(sql, new String[] { name, location});
    }

    public Cursor getItemsFromLocation(String location){

        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT * FROM items WHERE location=?";

        return db.rawQuery(sql, new String[] {location});
    }

    public Cursor getItem(String itemName){

        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT * FROM items WHERE itemName=?";

        return db.rawQuery(sql, new String[] { itemName });
    }

    public void deleteUser(String itemName){

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete("items", "itemName=?", new String[] { itemName });
    }
}
