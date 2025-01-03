package com.project.rydrotin.ui.login;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "Login.db";
    public DBHelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(email TEXT primary key, username TEXT, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }

    public Boolean insertData(String email, String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("email", email);
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = MyDB.insert("users", null, contentValues);
        return result != -1;
    }

    public String getUsername(String in_email) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        String username = null;
        @SuppressLint("Recycle") Cursor cursor = MyDB.rawQuery("SELECT username FROM users WHERE email = ?", new String[]{in_email});
        if (cursor.moveToFirst()) {
            // Get the username from the first row
            return cursor.getString(0); // 0 is the index of the username column
        }
        return null;
    }

    public Boolean checkEmail(String email) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = MyDB.rawQuery("Select * from users where email = ?", new String[]{email});
        return cursor.getCount() > 0;
    }

    public Boolean checkUsername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username});
        return cursor.getCount() > 0;
    }

    public Boolean checkValidateLogin(String email, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        // Declare cursor outside try block
        try (Cursor cursor = MyDB.rawQuery("Select * from users where email = ? and password = ?", new String[]{email, password})) {
            return cursor.getCount() > 0;
        }
    }
}