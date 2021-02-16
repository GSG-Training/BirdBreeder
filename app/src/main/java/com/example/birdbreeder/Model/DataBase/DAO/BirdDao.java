package com.example.birdbreeder.Model.DataBase.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.birdbreeder.Model.Constants;
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

    @Query("DELETE FROM "+ Constants.BIRDS_TABLE)
    void deleteAllItems();

    @Query("SELECT * FROM "+ Constants.BIRDS_TABLE)
    LiveData<List<Bird>> getAllItems();

   @Query( "SELECT * FROM " + Constants.BIRDS_TABLE + " WHERE ringNo  LIKE :ring ")
    LiveData<Bird> get(String ring);
    @Query( "SELECT * FROM " + Constants.BIRDS_TABLE + " WHERE birdId  = :id ")
    LiveData<Bird> get(int id);

    @Query("SELECT ringNo FROM "+ Constants.BIRDS_TABLE)
    LiveData<List<String>> getRingNOfBirds();

    @Query("SELECT ringNo FROM "+ Constants.BIRDS_TABLE + " WHERE gender  =  " + Constants.MALE )
    LiveData<List<String>> getRingNOfMales();

    @Query("SELECT ringNo FROM "+ Constants.BIRDS_TABLE + " WHERE gender  =  " + Constants.FEMALE )
    LiveData<List<String>> getRingNOfFemales();


}

