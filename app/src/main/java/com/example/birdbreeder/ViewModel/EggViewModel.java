package com.example.birdbreeder.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.birdbreeder.Model.BirdBreederConstants;
import com.example.birdbreeder.Model.DataBase.Entity.Egg;
import com.example.birdbreeder.Model.Repositories.EggRepo;

import java.util.List;

public class EggViewModel extends AndroidViewModel {
    private EggRepo repo ;
    public EggViewModel(@NonNull Application application) {
        super(application);
        repo = new EggRepo(application);
    }
    public  void addEgg(Egg egg){
        repo.addEgg(egg);
    }

    public  void updateEgg(Egg egg){
        repo.updateEgg(egg);
    }
    public  void deleteEgg(Egg egg){
        repo.deleteEgg(egg);
    }

    public  void getEgg(int eggId){
        repo.getEgg(eggId);
    }
    public LiveData<List<Egg>> getAllEggs(){
        return  repo.getAllEggs();
    }


    public LiveData<List<Egg>> getPageOfEggs(int pageNum){
        return  repo.getPageOfEggs(pageNum);
    }

    public void deleteAll(){
        repo.deleteAll();
    }
}



