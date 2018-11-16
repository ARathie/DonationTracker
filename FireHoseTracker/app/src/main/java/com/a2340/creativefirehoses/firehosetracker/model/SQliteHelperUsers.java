package com.a2340.creativefirehoses.firehosetracker.model;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

@SuppressWarnings("UnusedAssignment")
public class SQliteHelperUsers extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "UsersDatabase";
    private static final int DATABASE_VERSION = 1;

    public SQliteHelperUsers (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * creates database
     * @param db database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE users (username TEXT PRIMARY KEY, password Text, accountType Text)";
        db.execSQL(sql);
    }

    /**
     * drops earlier table if there is a duplicate
     * @param db bd
     * @param i first table
     * @param i2 another table
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }

    /**
     *
     * @param username username
     * @param password password
     * @param accountType account
     */
    public void saveUser (String username, String password, String accountType)
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

    }

    /**
     *
     * @param username name of user
     * @return true if the user of the username exists in the database, else false
     */
    public boolean checkUserExists (String username) {

        SQLiteDatabase db = this.getReadableDatabase();
//        String sql = "SELECT column1 FROM users WHERE users MATCH '" + username + "'";

        Cursor cursor = getUser(username);
        return cursor.getCount() != 0;
    }

    /**
     *
     * @return all the users
     */
    public Cursor getAll() {
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT * FROM users";

        return db.rawQuery(sql, null);
    }

    /**
     *
     * @param username name of user
     * @return a Cursor with the user of the particular username
     */
    private Cursor getUser(String username){

        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT * FROM users WHERE username=?";

        return db.rawQuery(sql, new String[] { username });
    }

    /**
     * deletes a user from the database
     * @param username username
     */
    public void deleteUser(String username){

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete("users", "username=?", new String[] { username });
    }

}
