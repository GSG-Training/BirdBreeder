package com.example.birdBreeder.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.birdBreeder.Model.DataBase.Entity.Species;
import com.example.birdBreeder.Model.Repositories.SpeciesRepo;

import java.util.List;

public class SpeciesViewModel extends AndroidViewModel {
    private SpeciesRepo repo;

    public SpeciesViewModel(@NonNull Application application) {
        super(application);
        repo = new SpeciesRepo(application);
    }

    public void addSpecies(Species species) {
        repo.addSpecies(species);
    }

    public void updateSpecies(Species species) {
        repo.updateSpecies(species);
    }

    public void deleteSpecies(Species species) {
        repo.deleteSpecies(species);
    }

    public LiveData<Species> getSpecies(int id) {
        return repo.getSpecies(id);
    }

    public LiveData<List<Species>> getAllSpecies() {
        return repo.getAllSpecies();
    }


    public LiveData<List<String>> getNamesOfSpecies() {
        return repo.getNameOfSpecies();
    }

    public void deleteAll() {
        repo.deleteAll();
    }


}
