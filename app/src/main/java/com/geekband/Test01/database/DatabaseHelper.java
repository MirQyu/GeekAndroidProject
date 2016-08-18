package com.geekband.Test01.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

/**
 * Created by MirQ on 16/7/27.
 */
public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "test.db";
    public static final int VERSION = 2;
    public static final String USER_TABLE_NAME = "user";
    public static final String USERNAME = "username";
    public static final String AGE = "age";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + USER_TABLE_NAME + " (" + USERNAME + " varchar(20) not null, " + AGE + " varchar(40) not null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // todo: on database upgrade operation
    }
}
