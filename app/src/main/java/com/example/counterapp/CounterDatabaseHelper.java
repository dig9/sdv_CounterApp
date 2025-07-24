package com.example.counterapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class CounterDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "counter.db";
    private static final  int DB_VERSION = 1;

    // 생성자 선언
    public CounterDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 테이블 생성
        db.execSQL("CREATE TABLE counts (_id INTEGER PRIMARY KEY AUTOINCREMENT, count INTEGER);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 버전 업그레이드 시 (현재는 호출되지 않음)
        if (oldVersion < newVersion) {
            db.execSQL("DROP TABLE IF EXISTS counts;");
            onCreate(db);
        }
    }


}
