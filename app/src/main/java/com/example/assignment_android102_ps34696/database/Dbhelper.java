package com.example.assignment_android102_ps34696.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Dbhelper extends SQLiteOpenHelper {

    public Dbhelper(Context c){
        super(c,"QuanLySanPham",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String dNguoidung="CREATE TABLE Nguoidung(username TEXT PRIMARY KEY, password TEXT, name TEXT)";
        db.execSQL(dNguoidung);

        String dSanpham="CREATE TABLE Sanpham(masp INTEGER PRIMARY KEY AUTOINCREMENT, tensp TEXT, giaban INTEGER, soluong INTEGER)";
        db.execSQL(dSanpham);

        String qNguoidung="INSERT INTO Nguoidung VALUES('sa','123','sa'),('sao','1234','sao'),('SA0','1112','sadtps34696@fpt.edu.vn')";
        db.execSQL(qNguoidung);

        String qSanpham="INSERT INTO Sanpham VALUES(1,'Xa phong',25000,2)," +
                                                    "(2,'Mi HaoHao',5000,2)," +
                                                    "(3,'Banh da',2000,2)," +
                                                    "(4,'Tra xanh',10000,2)," +
                                                    "(5,'Matcha',15000,2)," +
                "(6,'Oshi bi do',5000,20)," +
                           "(7,'Duong',20000,78)," +
                                                    "(8,'Socola',50000,102)," +
                                                    "(9,'Nuoc mam',50000,4)";
        db.execSQL(qSanpham);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion){
            db.execSQL("drop table if exists Nguoidung");
            db.execSQL("drop table if exists Sanpham");
            onCreate(db);
        }

    }
}
