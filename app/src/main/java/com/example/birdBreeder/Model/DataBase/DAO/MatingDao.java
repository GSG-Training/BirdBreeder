package com.example.birdBreeder.Model.DataBase.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.birdBreeder.Model.Constants;
import com.example.birdBreeder.Model.DataBase.Entity.Mating;

import java.util.List;

@Dao
public interface MatingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Mating mating);

    @Delete
    void delete(Mating mating);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Mating mating);

    @Query("DELETE FROM " + Constants.MATING_TABLE)
    void deleteAllItems();

    @Query("SELECT * FROM " + Constants.MATING_TABLE)
    LiveData<List<Mating>> getAllItems();

    @Query("SELECT * FROM " + Constants.MATING_TABLE + " WHERE matingId = :id")
    LiveData<Mating> get(int id);


}

