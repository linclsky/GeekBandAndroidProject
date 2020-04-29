package com.geekban.geekbandandroidproject.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.geekban.geekbandandroidproject.database.DatabaseHelper;

/**
 * Created by Administrator on 2020/3/4.
 */

public class  TestContentProvider extends ContentProvider {
    private static UriMatcher sUriMatcher;
    public static final int URI_MATCH_USRE_CODE = 1;
    public static final int URI_MATCH_BOOK_CODE = 2;
    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        //content://com.geekban.geekbandandroidproject/user 1
        //content://com.geekban.geekbandandroidproject/book 2
        sUriMatcher.addURI(URIList.AUTHORITY, DatabaseHelper.USER_TABLE_NAME,URI_MATCH_USRE_CODE);
        sUriMatcher.addURI(URIList.AUTHORITY, DatabaseHelper.BOOK_TABLE_NAME,URI_MATCH_BOOK_CODE);
    }

    private DatabaseHelper mDatabaseHelper;

    @Override
    public boolean onCreate() {
        mDatabaseHelper = new DatabaseHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        String tableName = getTableName(uri);
        if (TextUtils.isEmpty(tableName)){
            return null;
        }
        Cursor cursor = mDatabaseHelper.getReadableDatabase().
                query(tableName, projection,selection,selectionArgs,null,null,sortOrder);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        String tableName = getTableName(uri);
        if (TextUtils.isEmpty(tableName)){
            return null;
        }
       Long id =  mDatabaseHelper.getWritableDatabase().insert(tableName,null,values);
        //返回content+id
        //content://com.geekban.geekbandandroidproject/user/id
        return ContentUris.withAppendedId(uri,id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        String tableName = getTableName(uri);
        if (TextUtils.isEmpty(tableName)){
            return -1;
        }
        //tableName表名，selection条件，selectionArgs条件参数
        int conut = mDatabaseHelper.getWritableDatabase().delete(tableName,selection,selectionArgs);
        return conut;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        String tableName = getTableName(uri);
        if (TextUtils.isEmpty(tableName)){
            return -1;
        }
        int count = mDatabaseHelper.getWritableDatabase().update(tableName,values,selection,selectionArgs);
        return count;
    }
    private String getTableName(Uri uri){
        int type = sUriMatcher.match(uri);
        String tableName = null;
        switch (type){
            case URI_MATCH_USRE_CODE:
                tableName = DatabaseHelper.USER_TABLE_NAME;
                break;
            case URI_MATCH_BOOK_CODE:
                tableName = DatabaseHelper.BOOK_TABLE_NAME;
                break;
        }
        return tableName;
    }
}
