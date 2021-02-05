package com.example.birdbreeder.Model.Repositories;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.birdbreeder.Model.BirdBreederConstants;
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

    //TODO: FIX WHERE CLAUSE
    public  void getBird(String ring){
        birdDao.get(ring);
    }
    public LiveData<List<Bird>> getAllBirds(){
        return  birdDao.getAllItems();
    }


    //TODO: FIX PAGINATION
    public LiveData<List<Bird>> getPageOfBirds(int pageNum){
        int to = pageNum* BirdBreederConstants.PAGE_ITEM_COUNT ;
        int from = to - pageNum ;
        return  birdDao.getPage();
    }


}
