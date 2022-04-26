package com.bolatovyernur.woopaysecondtask.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "service_table")
public class Service {
    @PrimaryKey
    public int id;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "platform")
    public String platform;
    @ColumnInfo(name = "picture_url")
    public String picture_url;
}
