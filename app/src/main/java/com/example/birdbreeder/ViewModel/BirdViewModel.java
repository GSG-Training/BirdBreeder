package com.example.birdbreeder.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

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

    public  LiveData<Bird> getBird(String ring){
       return repo.getBird(ring);
    }

    public   LiveData<Bird> getBird(int id){
     return    repo.getBird(id);
    }
    public LiveData<List<Bird>> getAllBirds(){
        return  repo.getAllBirds();
    }



    public LiveData<List<String>> getRingNOfBirds(){
        return  repo.getRingNOfBirds();

    }

    public LiveData<List<String>> getRingNOfMales(){
        return  repo.getRingNOfMales();

    }

    public LiveData<List<String>> getRingNOfFemales(){
        return  repo.getRingNOfFemales();

    }
    public void deleteAll(){
        repo.deleteAll();
    }

}
