package com.example.yolobob.testsqlite;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

/**
 * @Description : Practicing with SQLite and lab 06
 * @author : Frédérick Gaudet - 8035208
 */

public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "mydb.db";

    public static final String TABLE = "user";
    public static final String COLUMN_ID = "userid";
    public static final String COLUMN_NAME = "username";
    public static final String COLUMN_PW = "password";


    /** Constructor using context
     * @param context
     */
    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE + "( " +
                COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME +
                " TEXT," + COLUMN_PW + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    /**
     * Method to add user
     * @param user
     */
    public void insertUser(User user){


        try {
            SQLiteDatabase db = this.getWritableDatabase();//Open the connection with the DB

            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, user.getUsername());
            values.put(COLUMN_PW, user.getPassword());

            db.insert(TABLE, null, values);

            db.close();//Close the connection with the DB
        }
        catch(Exception ex)
        {

        }

    }

    /**
     * find a user by his username
     * @param username
     * @return a product object
     */
    public User findUser(String username, String password) {

        SQLiteDatabase db = this.getReadableDatabase();//Connect to db

        //Build the query to search user
        String query = "Select * FROM " + TABLE + " WHERE " + COLUMN_NAME +
                " = \"" + username + "\" AND " + COLUMN_PW + " = \"" + password + "\"";

        Cursor cursor = db.rawQuery(query, null);

        User user = new User();

        if(cursor.moveToFirst()){
            user.setId(cursor.getString(0));
            user.setUsername(cursor.getString(1));
            user.setPassword(cursor.getString(2));
            cursor.close();
        } else {
            user = null;
        }
        db.close();
        return user;
    }

    public boolean deleteUser( String username, String password){
        boolean result = false;


        String idStr = getUserID(username,password);

        if(!idStr.equals("-1")) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE, COLUMN_ID + "=" + idStr, null);
            db.close();

            result = true;
        }
        return result;

    }

    public String getUserID(String username, String password){
        String id ="-1";

        String query = "Select * FROM " + TABLE + " WHERE " + COLUMN_NAME +
                " = \"" + username + "\" AND " + COLUMN_PW + " = \"" + password + "\"";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            id = cursor.getString(0);
            cursor.close();
        }
        db.close();
        return id;
    }
}