package com.example.birdBreeder.Model.DataBase.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.birdBreeder.Model.Constants;
import com.example.birdBreeder.Model.DataBase.Entity.Species;

import java.util.List;

@Dao
public interface SpeciesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Species species);

    @Delete
    void delete(Species species);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Species species);

    @Query("DELETE FROM " + Constants.SPECIES_TABLE)
    void deleteAllItems();

    @Query("SELECT * FROM " + Constants.SPECIES_TABLE)
    LiveData<List<Species>> getAllItems();


    @Query("SELECT * FROM " + Constants.SPECIES_TABLE + " WHERE speciesId = :id")
    LiveData<Species> get(int id);

    @Query("SELECT name FROM " + Constants.SPECIES_TABLE)
    LiveData<List<String>> getSpeciesNames();

    @Query("SELECT * FROM " + Constants.SPECIES_TABLE + " WHERE name = :name")
    LiveData<Species> get(String name);
}

