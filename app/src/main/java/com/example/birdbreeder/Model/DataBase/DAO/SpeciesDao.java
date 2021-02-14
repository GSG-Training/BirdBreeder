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
import com.example.birdbreeder.Model.DataBase.Entity.Species;

import java.util.List;

@Dao
public interface SpeciesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Species species);

    @Delete
    void delete(Species species);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Species species);

    @Query("DELETE FROM "+ BirdBreederConstants.SPECIES_TABLE)
    void deleteAllItems();

    @Query("SELECT * FROM "+ BirdBreederConstants.SPECIES_TABLE)
    LiveData<List<Species>> getAllItems();


    @Query("SELECT * FROM "+ BirdBreederConstants.SPECIES_TABLE + " WHERE speciesId = :id")
    LiveData<Species> get(int id);

    @Query("SELECT name FROM "+ BirdBreederConstants.SPECIES_TABLE )
    LiveData<List<String>> getSpeciesNames();
}

