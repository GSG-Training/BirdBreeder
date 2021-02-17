package com.example.birdBreeder.Model.Repositories;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.birdBreeder.Model.DataBase.BirdsDataBase;
import com.example.birdBreeder.Model.DataBase.DAO.SpeciesDao;
import com.example.birdBreeder.Model.DataBase.Entity.Species;

import java.util.List;

public class SpeciesRepo {
    private SpeciesDao speciesDao;

    public SpeciesRepo(Context context) {
        speciesDao = BirdsDataBase.getInstance(context).speciesDao();
    }


    public void addSpecies(Species species) {
        speciesDao.insert(species);
    }

    public void updateSpecies(Species species) {
        speciesDao.update(species);
    }

    public void deleteSpecies(Species species) {
        speciesDao.delete(species);
    }

    public LiveData<Species> getSpecies(int id) {
        return speciesDao.get(id);
    }

    public LiveData<List<Species>> getAllSpecies() {
        return speciesDao.getAllItems();
    }


    public LiveData<List<String>> getNameOfSpecies() {
        return speciesDao.getSpeciesNames();
    }

    public void deleteAll() {
        speciesDao.deleteAllItems();
    }
}
