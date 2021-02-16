package com.example.birdbreeder.Model.Repositories;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.birdbreeder.Model.DataBase.BirdsDataBase;
import com.example.birdbreeder.Model.DataBase.DAO.BirdDao;
import com.example.birdbreeder.Model.DataBase.Entity.Bird;

import java.util.List;

public class BirdRepo {
    private BirdDao birdDao;

    public BirdRepo(Context context){
        birdDao = BirdsDataBase.getInstance(context).birdDao() ;
    }


    public  void addBird(Bird bird){
        birdDao.insert(bird);
    }

    public  void updateBird(Bird bird){
        birdDao.update(bird);
    }
    public  void deleteBird(Bird bird){
        birdDao.delete(bird);
    }

    public  LiveData<Bird> getBird(String ring){
      return   birdDao.get(ring);
    }

    public LiveData<List<Bird>> getAllBirds(){
        return  birdDao.getAllItems();
    }


    public LiveData<List<String>> getRingNOfBirds(){
        return  birdDao.getRingNOfBirds();

    }


    public LiveData<List<String>> getRingNOfMales(){
        return  birdDao.getRingNOfMales();

    }

    public LiveData<List<String>> getRingNOfFemales(){
        return  birdDao.getRingNOfFemales();

    }
        public void deleteAll(){
        birdDao.deleteAllItems();
        }

    public  LiveData<Bird> getBird(int id) {
        return  birdDao.get(id)  ;
    }
}
