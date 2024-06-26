package com.example.assignment_android102_ps34696.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.assignment_android102_ps34696.database.Dbhelper;

public class nguoidungDAO {
    private Dbhelper Dbhelper;                 //Dbhelper = java/com.ex/database/Dbhelper

    public nguoidungDAO(Context c){
        Dbhelper = new Dbhelper(c);
    }

    //login

    public boolean checkLogin(String username, String password){
        SQLiteDatabase sqLiteDatabase = Dbhelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Nguoidung WHERE username = ? AND password =?",new String[]{username,password});

        if(cursor.getCount()>0){
            return true;
        }else {
            return false;
        }
    }

    //register

    public boolean register(String user, String pass,String name){
        SQLiteDatabase sqLiteDatabase = Dbhelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("username",user);
        contentValues.put("password",pass);
        contentValues.put("name",name);

        long check = sqLiteDatabase.insert("Nguoidung",null,contentValues);

        /*if(check == - 1){
            return false;
        }else {
            return true;
        }*/

        return check != -1;

    }


    public String checkuser(String username){
        SQLiteDatabase sqLiteDatabase=Dbhelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT password FROM Nguoidung WHERE username",new String[]{username});  //?
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            return cursor.getString(0);
        }
        return "";
    }

}
