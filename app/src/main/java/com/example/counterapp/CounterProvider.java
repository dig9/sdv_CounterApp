package com.example.counterapp;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class CounterProvider extends ContentProvider {
    private CounterDatabaseHelper dbHelper;
    public static final String AUTHORITY = "com.example.counterapp.provider";
    public static final Uri CONTENT_URI
            = Uri.parse("content://" + AUTHORITY + "/counts");

    @Override
    public boolean onCreate() {
        // ContentProvider가 초기화될 때 호출됨
        dbHelper = new CounterDatabaseHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("counts", projection, selection,
                selectionArgs, null, null, sortOrder);

        // query 결과에 notify를 추적하기 위해 컨텍스트의 contentResolver에
        // 알려줄 수도 있음. 하지만 여기서는 단순 조회이므로 생략.
        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long newId = db.insert("counts", null, values);
        // 새로 추가된 행의 URI 생성
        Uri insertedUri = ContentUris.withAppendedId(CONTENT_URI, newId);
        // 데이터 변경을 리스너들에게 알림
        getContext().getContentResolver().notifyChange(insertedUri, null);
        return insertedUri;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // 이 앱에서는 업데이트 기능 사용 안 함
        return 0;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // 이 앱에서는 삭제 기능 사용 안 함
        return 0;
    }

    @Override
    public String getType(Uri uri) {
        // MIME 타입 정의 (필요시 구현)
        return null;
    }
}
