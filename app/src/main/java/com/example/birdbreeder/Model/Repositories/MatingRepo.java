package com.example.birdbreeder.Model.Repositories;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.birdbreeder.Model.BirdBreederConstants;
import com.example.birdbreeder.Model.DataBase.BirdsDataBase;
import com.example.birdbreeder.Model.DataBase.DAO.MatingDao;
import com.example.birdbreeder.Model.DataBase.Entity.Bird;
import com.example.birdbreeder.Model.DataBase.Entity.Mating;

import java.util.List;

public class MatingRepo {
    private MatingDao matingDao;

    public MatingRepo(Context context){
        matingDao = BirdsDataBase.getInstance(context).matingDao() ;
    }


    public  void addMating(Mating mating){
        matingDao.insert(mating);
    }

    public  void updateMating(Mating mating){
        matingDao.update(mating);
    }
    public  void deleteMating(Mating mating){
        matingDao.delete(mating);
    }

    //TODO: FIX WHERE CLAUSE
    public  void getMating(int id){
        //matingDao.get(id);
    }
    public LiveData<List<Mating>> getAllMatings(){
        return  matingDao.getAllItems();
    }


    //TODO: FIX PAGINATION
    public LiveData<List<Mating>> getPageOfMatings(int pageNum){
        int to = pageNum* BirdBreederConstants.PAGE_ITEM_COUNT ;
        int from = to - pageNum ;
        return  matingDao.getAllItems();
    }

    public void deleteAll(){
        matingDao.deleteAllItems();
    }
}
