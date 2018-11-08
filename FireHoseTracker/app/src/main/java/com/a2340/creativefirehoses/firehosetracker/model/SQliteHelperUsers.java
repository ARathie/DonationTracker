package com.a2340.creativefirehoses.firehosetracker.model;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SQliteHelperUsers extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "UsersDatabase";
    private static final int DATABASE_VERSION = 1;

    public SQliteHelperUsers (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE users (username TEXT PRIMARY KEY, password Text, accountType Text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }

    public boolean saveUser (String username, String password, String accountType)
    {
        Cursor cursor = getUser(username);

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("password", password);
        contentValues.put("accountType", accountType);

        long result;
        if (cursor.getCount() == 0) { // Record does not exist
            contentValues.put("username", username);
            result = db.insert("users", null, contentValues);
        } else { // Record exists
            result = db.update("users", contentValues, "username=?", new String[] { username });
        }

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public boolean checkUserExists (String username) {

        SQLiteDatabase db = this.getReadableDatabase();
//        String sql = "SELECT column1 FROM users WHERE users MATCH '" + username + "'";

        Cursor cursor = getUser(username);
        if (cursor.getCount() == 0) {
            return false;
        }
        return true;
    }

    public Cursor getAll() {
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT * FROM users";

        return db.rawQuery(sql, null);
    }
    public Cursor getUser(String username){

        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT * FROM users WHERE username=?";

        return db.rawQuery(sql, new String[] { username });
    }

    public void deleteUser(String username){

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete("users", "username=?", new String[] { username });
    }

}
