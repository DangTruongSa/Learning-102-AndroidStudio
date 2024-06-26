package com.example.assignment_android102_ps34696.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.assignment_android102_ps34696.database.Dbhelper;
import com.example.assignment_android102_ps34696.model.ModelSanpham;

import java.util.ArrayList;

public class sanphamDAO {
    private ModelSanpham ModelSanpham;
    private Dbhelper Dbhelper;

    public sanphamDAO(Context c){
        Dbhelper =new Dbhelper(c);
    }

    public ArrayList<ModelSanpham> getDS(){
        ArrayList<ModelSanpham> ls = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = Dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Sanpham",null);
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            do {
                ls.add(new ModelSanpham(cursor.getInt(0), cursor.getString(1),cursor.getInt(2),cursor.getInt(3)));

            }while (cursor.moveToNext());
        }
        return ls;
    }

    //add sp

    public boolean addsp(ModelSanpham modelSanpham){
        SQLiteDatabase sqLiteDatabase = Dbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("tensp",modelSanpham.getTensp());
        contentValues.put("giaban",modelSanpham.getGia());
        contentValues.put("soluong",modelSanpham.getSoluong());

        long check= sqLiteDatabase.insert("Sanpham",null,contentValues);
        //return check !=-1;
        if (check ==-1) return false;
        return true;
    }

    // sua sp

    public boolean chinhsua(ModelSanpham modelSanpham){
        SQLiteDatabase sqLiteDatabase =  Dbhelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("tensp",modelSanpham.getTensp());
        contentValues.put("giaban",modelSanpham.getGia());
        contentValues.put("soluong",modelSanpham.getSoluong());

        int check = sqLiteDatabase.update("Sanpham",contentValues,"masp =?",new String[]{String.valueOf(modelSanpham.getMasp())});
        if (check <= 0) return false;
        return true;
    }

    // xao sp

    public boolean Xoasp(int masp){
        SQLiteDatabase sqLiteDatabase = Dbhelper.getWritableDatabase();

        int check = sqLiteDatabase.delete("Sanpham","masp =?",new String[]{String.valueOf(masp)});
        if (check <= 0) return false;
        return true;
    }
}
