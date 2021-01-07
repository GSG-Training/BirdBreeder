package com.example.birdbreeder.Model.DataBase.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.birdbreeder.Model.BirdBreederConstants;
import com.example.birdbreeder.Model.DataBase.Entity.Bird;

import java.util.List;

@Dao
public interface BirdDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Bird bird);

    @Delete
    void delete(Bird bird);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Bird bird);

    @Query("DELETE FROM "+ BirdBreederConstants.BIRDS_TABLE)
    void deleteAllItems();

    @Query("SELECT * FROM "+ BirdBreederConstants.BIRDS_TABLE)
    LiveData<List<Bird>> getAllItems();
}

