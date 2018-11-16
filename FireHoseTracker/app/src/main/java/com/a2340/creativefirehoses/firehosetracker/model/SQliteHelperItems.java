package com.a2340.creativefirehoses.firehosetracker.model;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


@SuppressWarnings("UnusedAssignment")
public class SQliteHelperItems extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ItemsDatabase";
    private static final int DATABASE_VERSION = 1;

    public SQliteHelperItems (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * creates the database
     * @param db db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE items (itemName TEXT PRIMARY KEY, timeStamp Text, location Text, shortDescription Text, fullDescription Text, value Text, category Text)";
        db.execSQL(sql);
    }

    /**
     * If the database is updated, drops the older version
     * @param db database
     * @param i a table
     * @param i2 another table
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {
        db.execSQL("DROP TABLE IF EXISTS items");
        onCreate(db);
    }

    /**
     *
     * @param itemName name of item
     * @param timeStamp time of query
     * @param location location
     * @param shortDescription short description
     * @param fullDescription full description
     * @param value value
     * @param category category
     */
    public void saveItem (String itemName, String timeStamp, String location, String shortDescription, String fullDescription, String value, String category)
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

    }

    /**
     *
     * @param category category
     * @param location location
     * @return a Cursor with all donationItems of a search category
     */
    public Cursor getItemsFromCategory(String category, String location){

        SQLiteDatabase db = this.getReadableDatabase();

        if (location.equals("All")) {
            String sql = "SELECT * FROM items WHERE category=?";

            return db.rawQuery(sql, new String[] { category });
        }
        String sql = "SELECT * FROM items WHERE category=? AND location=?";

        return db.rawQuery(sql, new String[] { category, location});
    }

    /**
     *
     * @param name name
     * @param location location
     * @return a cursor of all DonationItems with a search name
     */
    public Cursor getItemsFromName(String name, String location){

        SQLiteDatabase db = this.getReadableDatabase();

        if (location.equals("All")) {
            String sql = "SELECT * FROM items WHERE itemName=?";

            return db.rawQuery(sql, new String[] { name });
        }
        String sql = "SELECT * FROM items WHERE name=? AND location=?";

        return db.rawQuery(sql, new String[] { name, location});
    }

    /**
     *
     * @param location location
     * @return a Cursor with all DonationItems from a particular location
     */
    public Cursor getItemsFromLocation(String location){

        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT * FROM items WHERE location=?";

        return db.rawQuery(sql, new String[] {location});
    }

    /**
     *
     * @param itemName name of item
     * @return a Cursor with the DonationItem of the particular itemName
     */
    private Cursor getItem(String itemName){

        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT * FROM items WHERE itemName=?";

        return db.rawQuery(sql, new String[] { itemName });
    }

    /**
     * Remove a user from the database
     * @param itemName name of item
     */
    public void deleteUser(String itemName){

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete("items", "itemName=?", new String[] { itemName });
    }
}
