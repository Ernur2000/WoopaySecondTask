package com.bolatovyernur.woopaysecondtask.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "category_table")
public class Category {
    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "picture_url")
    public String picture_url;
}
