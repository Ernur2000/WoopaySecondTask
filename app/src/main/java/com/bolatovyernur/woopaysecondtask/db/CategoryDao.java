package com.bolatovyernur.woopaysecondtask.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CategoryDao {
    @Query("SELECT * FROM category_table")
    List<Category> getAllCategories();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCategories(Category... categories);

    @Query("SELECT * FROM SERVICE_TABLE")
    List<Service> getAllTopServices();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTopServices(Service... services);
}
