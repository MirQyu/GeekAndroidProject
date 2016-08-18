package com.geekband.Test01;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.geekband.Test01.database.DatabaseHelper;

/**
 * Created by MirQ on 16/7/27.
 */
public class DatabaseButtonActivity extends AppCompatActivity{

    private SQLiteDatabase mSqLiteDatabase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        mSqLiteDatabase = databaseHelper.getWritableDatabase();

        // insert

        findViewById(R.id.add_data_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();


                // query
                // 游标 要查找的数据集合
                Cursor cursor = mSqLiteDatabase.query(DatabaseHelper.USER_TABLE_NAME, null, null, null, null, null, null);

                if (cursor.moveToFirst()) {
                    int count = cursor.getCount();
                    for (int i = 0; i < count; i++) {
                        String username = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.USERNAME));

                    }
                }

            }
        });

        findViewById(R.id.delete_data_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // delete
                String whereClauseString = "username=?";
                String[] whereArgsString = {"俊浩"};
                mSqLiteDatabase.delete(DatabaseHelper.USER_TABLE_NAME, whereClauseString, whereArgsString);
            }
        });

        findViewById(R.id.update_data_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(DatabaseHelper.AGE, 100);
                String whereClauseString = "username=?";
                String[] whereArgsString = {"俊浩"};
                mSqLiteDatabase.update(DatabaseHelper.USER_TABLE_NAME, contentValues, whereClauseString, whereArgsString);
            }
        });

        mSqLiteDatabase.execSQL("insert into user(username, age) values('小妞', 10);");

        // 插入10000条数据

        // 开始食物  此时db会被锁定
//        mSqLiteDatabase.beginTransaction();
//        try {
//            // operation
//            for (int i = 0; i < 1000; i++) {
//                mSqLiteDatabase.execSQL("insert into user(username, age) values('欧阳锋', 10);");
//            }
//            mSqLiteDatabase.setTransactionSuccessful();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            mSqLiteDatabase.endTransaction();
//        }


    }

    private void addData() {
        // IO operation , advise background
        ContentValues contenValues = new ContentValues();
        contenValues.put(DatabaseHelper.USERNAME, "俊浩");
        contenValues.put(DatabaseHelper.AGE, 15);

        long rowNumber = mSqLiteDatabase.insert(DatabaseHelper.USER_TABLE_NAME, null, contenValues);
        if (rowNumber != -1) {
            Toast.makeText(DatabaseButtonActivity.this, "插入成功", Toast.LENGTH_SHORT).show();
        }
    }
}
