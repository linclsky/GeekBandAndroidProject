package com.geekban.geekbandandroidproject.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2020/3/3.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String USER_TABLE_NAME = "user";
    public static final String USERNAME = "username";
    public static final String AGE = "age";
    public static final String DATABASE_NAME = "test.db";
    public static final int VERSION = 1;
    public static final String BOOK_TABLE_NAME = "book";
    public static final String BOOK_NAME = "book_name";
    public static final String BOOK_PRICE = "book_price";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建一个表,表的名称叫user,里面有2个字段username 字符为20个，不能为空，password，60个字符，不能为空
        db.execSQL("create table " + USER_TABLE_NAME + "(" + USERNAME + " varchar(20) not null," + AGE + " varchar(60) not null);");
        db.execSQL("create table " + BOOK_TABLE_NAME + "(" + BOOK_NAME + " varchar(20) not null," + BOOK_PRICE + " varchar(60) not null);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    //  升级数据库，用老版本覆盖新版本
    }
}
