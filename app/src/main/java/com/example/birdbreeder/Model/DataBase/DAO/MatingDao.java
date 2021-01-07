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
import com.example.birdbreeder.Model.DataBase.Entity.Mating;

import java.util.List;

@Dao
public interface MatingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Mating mating);

    @Delete
    void delete(Mating mating);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Mating mating);

    @Query("DELETE FROM "+ BirdBreederConstants.MATING_TABLE)
    void deleteAllItems();

    @Query("SELECT * FROM "+ BirdBreederConstants.MATING_TABLE)
    LiveData<List<Mating>> getAllItems();
}

