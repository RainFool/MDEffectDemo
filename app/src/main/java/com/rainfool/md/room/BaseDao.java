package com.rainfool.md.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import java.util.List;

@Dao
public interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(T entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(List<T> entities);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void update(T entity);

    @Delete
    void delete(T entity);


}
