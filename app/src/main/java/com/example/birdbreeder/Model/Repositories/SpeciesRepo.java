package com.example.birdbreeder.Model.Repositories;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.birdbreeder.Model.BirdBreederConstants;
import com.example.birdbreeder.Model.DataBase.BirdsDataBase;
import com.example.birdbreeder.Model.DataBase.DAO.SpeciesDao;
import com.example.birdbreeder.Model.DataBase.Entity.Bird;
import com.example.birdbreeder.Model.DataBase.Entity.Species;

import java.util.List;

public class SpeciesRepo {
    private SpeciesDao speciesDao;

    public SpeciesRepo(Context context){
        speciesDao = BirdsDataBase.getInstance(context).speciesDao() ;
    }


    public  void addSpecies(Species species){
        speciesDao.insert(species);
    }

    public  void updateSpecies(Species species){
        speciesDao.update(species);
    }
    public  void deleteSpecies(Species species){
        speciesDao.delete(species);
    }

    public  LiveData<Species> getSpecies(int  id){
       return speciesDao.get(id);
    }
    public LiveData<List<Species>> getAllSpecies(){
        return  speciesDao.getAllItems();
    }


    public LiveData<List<String>> getNameOfSpecies(){
        return  speciesDao.getSpeciesNames();
    }

    public void deleteAll(){
        speciesDao.deleteAllItems();
    }
}
