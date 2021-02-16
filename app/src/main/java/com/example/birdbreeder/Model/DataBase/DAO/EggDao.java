package com.example.birdbreeder.Model.DataBase.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.birdbreeder.Model.Constants;
import com.example.birdbreeder.Model.DataBase.Entity.Egg;

import java.util.List;

@Dao
public interface EggDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Egg egg);

    @Delete
    void delete(Egg egg);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Egg egg);

    @Query("DELETE FROM "+ Constants.EGG_TABLE)
    void deleteAllItems();

    @Query("SELECT * FROM "+ Constants.EGG_TABLE)
    LiveData<List<Egg>> getAllItems();
//
//    LiveData<List<Egg>> getPage();
//
//    void get(int eggId);
}

