package com.bolatovyernur.woopaysecondtask.api;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "categoryDb";
    public static final String TABLE_CATEGORIES = "categories";

    public static final String KEY_ID = "_id";
    public static final String KEY_PARENT_ID = "parent_id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_NAME = "name";
    public static final String KEY_PICTURE = "picture";
    public static final String KEY_PARENT = "parent";
    public static final String KEY_CHILDREN = "children";
    public static final String KEY_BLACKLIST = "blacklist";
    public static final String KEY_LANGUAGE = "language";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_POSITION = "position";
    public static final String KEY_PICTURE_URL = "picture_url";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_CATEGORIES + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_PARENT_ID + " INTEGER," + KEY_TITLE + " text," + KEY_NAME + " text," + KEY_PICTURE + " text," +
                KEY_PARENT + " text," + KEY_CHILDREN + " text," + KEY_BLACKLIST + " text," + KEY_LANGUAGE + " text," +
                KEY_DESCRIPTION + " text," + KEY_POSITION + " INTEGER," + KEY_PICTURE_URL + " text" + ") ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_CATEGORIES);
        onCreate(sqLiteDatabase);
    }
}
