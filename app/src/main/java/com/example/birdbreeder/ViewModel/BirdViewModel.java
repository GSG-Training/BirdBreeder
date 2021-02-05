package com.example.birdbreeder.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.birdbreeder.Model.BirdBreederConstants;
import com.example.birdbreeder.Model.DataBase.Entity.Bird;
import com.example.birdbreeder.Model.Repositories.BirdRepo;

import java.util.List;

public class BirdViewModel extends AndroidViewModel {
    private BirdRepo repo ;

    public BirdViewModel(@NonNull Application application) {
        super(application);
        repo = new BirdRepo(application);
    }


    public  void addBird(Bird bird){
        repo.addBird(bird);
    }

    public  void updateBird(Bird bird){
        repo.updateBird(bird);
    }
    public  void deleteBird(Bird bird){
        repo.deleteBird(bird);
    }

    public  void getBird(String ring){
        repo.getBird(ring);
    }
    public LiveData<List<Bird>> getAllBirds(){
        return  repo.getAllBirds();
    }


    public LiveData<List<Bird>> getPageOfBirds(int pageNum){
        return  repo.getPageOfBirds(pageNum);
    }

    public void deleteAll(){
        repo.deleteAll();
    }
}
