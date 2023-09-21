package com.example.sqlite_sample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "usersdb";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME = "users";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our username column
    private static final String USERNAME_COL = "username";

    // below variable is for our username column
    private static final String AGE_COL = "age";

    // below variable is for our username column
    private static final String PASSWORD_COL = "password";

    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " TEXT PRIMARY KEY, "
                + USERNAME_COL + " TEXT,"
                + AGE_COL + " INT,"
                + PASSWORD_COL + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }


    // this method is use to add new user to our sqlite database.
    public void insertUser(
            String id, String username, int age, String password
    ) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(ID_COL, id);
        values.put(USERNAME_COL, username);
        values.put(AGE_COL, age);
        values.put(PASSWORD_COL, password);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    // we have created a new method for reading all the courses.
    public ArrayList<HashMap<String, String>> getAllUsers() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<HashMap<String, String>> res = new ArrayList<HashMap<String, String>>();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME  , null);


        // moving our cursor to first position.
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> user = new HashMap<String, String>();
                user.put("id", cursor.getString(0));
                user.put("username", cursor.getString(1));
                user.put("age", cursor.getString(2));
                user.put("password", cursor.getString(3));
                res.add(user);
            } while (cursor.moveToNext());
            // moving our cursor to next.
        }

        cursor.close();
        return res;
    }

    public HashMap<String, String> getUserByID(String id) {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + ID_COL + "=" + id, null);

        HashMap<String, String> user = new HashMap<String, String>();

        // moving our cursor to first position.
        if (cursor.moveToFirst()) {
            user.put("id", cursor.getString(0));
            user.put("username", cursor.getString(1));
            user.put("age", cursor.getString(2));
            user.put("password", cursor.getString(3));
        }

        cursor.close();
        return user;
    }

    public ArrayList<HashMap<String, String>> getUserByUsersname(String username) {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<HashMap<String, String>> res = new ArrayList<HashMap<String, String>>();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME  + " WHERE " + USERNAME_COL + "=" + username, null);


        // moving our cursor to first position.
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> user = new HashMap<String, String>();
                user.put("id", cursor.getString(0));
                user.put("username", cursor.getString(1));
                user.put("age", cursor.getString(2));
                user.put("password", cursor.getString(3));
                res.add(user);
            } while (cursor.moveToNext());
            // moving our cursor to next.
        }

        cursor.close();
        return res;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
