package com.example.birdBreeder.Model.DataBase.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.birdBreeder.Model.Constants;
import com.example.birdBreeder.Model.DataBase.Entity.Bird;

import java.util.List;

@Dao
public interface BirdDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Bird bird);

    @Delete
    void delete(Bird bird);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Bird bird);

    @Query("DELETE FROM " + Constants.BIRDS_TABLE)
    void deleteAllItems();

    @Query("SELECT * FROM " + Constants.BIRDS_TABLE)
    LiveData<List<Bird>> getAllItems();

    @Query("SELECT * FROM " + Constants.BIRDS_TABLE + " WHERE ring_no  LIKE :ring ")
    LiveData<Bird> get(String ring);

    @Query("SELECT * FROM " + Constants.BIRDS_TABLE + " WHERE birdId  = :id ")
    LiveData<Bird> get(int id);

    @Query("SELECT ring_no FROM " + Constants.BIRDS_TABLE)
    LiveData<List<String>> getRingNOfBirds();

    @Query("SELECT ring_no FROM " + Constants.BIRDS_TABLE + " WHERE gender  =  " + Constants.MALE)
    LiveData<List<String>> getRingNOfMales();

    @Query("SELECT ring_no FROM " + Constants.BIRDS_TABLE + " WHERE gender  =  " + Constants.FEMALE)
    LiveData<List<String>> getRingNOfFemales();

    @Query("SELECT ring_no FROM " + Constants.BIRDS_TABLE + " WHERE gender  =  " + Constants.FEMALE + " AND species LIKE :species")
    LiveData<List<String>> getRingNOfFemales(String species);
}

