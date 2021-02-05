package com.example.birdbreeder.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.birdbreeder.Model.DataBase.Entity.Species;
import com.example.birdbreeder.Model.Repositories.SpeciesRepo;

import java.util.List;

public class SpeciesViewModel extends AndroidViewModel {
    private SpeciesRepo repo ;
    public SpeciesViewModel(@NonNull Application application) {
        super(application);
        repo = new SpeciesRepo(application);
    }
        public  void addSpecies(Species species){
            repo.addSpecies(species);
        }

        public  void updateSpecies(Species species){
            repo.updateSpecies(species);
        }
        public  void deleteSpecies(Species species){
            repo.deleteSpecies(species);
        }

        public  void getSpecies(int  id){
            repo.getSpecies(id);
        }
        public LiveData<List<Species>> getAllSpecies(){
            return  repo.getAllSpecies();
        }


        public LiveData<List<Species>> getPageOfSpecies(int pageNum){

            return  repo.getPageOfSpecies(pageNum);
        }

        public void deleteAll(){
            repo.deleteAll();
        }



}
