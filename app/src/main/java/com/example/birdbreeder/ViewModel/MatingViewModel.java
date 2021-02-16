package com.example.birdbreeder.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.example.birdbreeder.Model.DataBase.Entity.Mating;
import com.example.birdbreeder.Model.Repositories.MatingRepo;

import java.util.List;

public class MatingViewModel extends AndroidViewModel {
    MatingRepo repo ;
    public MatingViewModel(@NonNull Application application) {
        super(application);
        repo = new MatingRepo(application);
    }

    public  void addMating(Mating mating){
        repo.addMating(mating);
    }

    public  void updateMating(Mating mating){
        repo.updateMating(mating);
    }
    public  void deleteMating(Mating mating){
        repo.deleteMating(mating);
    }

    public  LiveData<Mating> getMating(int id){
         return repo.getMating(id);
    }
    public LiveData<List<Mating>> getAllMatings(){
        return  repo.getAllMatings();
    }



    public void deleteAll(){
        repo.deleteAll();
    }
}
