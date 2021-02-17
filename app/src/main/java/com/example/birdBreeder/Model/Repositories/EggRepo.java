package com.example.birdBreeder.Model.Repositories;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.birdBreeder.Model.Constants;
import com.example.birdBreeder.Model.DataBase.BirdsDataBase;
import com.example.birdBreeder.Model.DataBase.DAO.EggDao;
import com.example.birdBreeder.Model.DataBase.Entity.Egg;

import java.util.List;

public class EggRepo {
    private EggDao eggDao;

    public EggRepo(Context context) {
        eggDao = BirdsDataBase.getInstance(context).eggDao();
    }

    public void addEgg(Egg egg) {
        eggDao.insert(egg);
    }

    public void updateEgg(Egg egg) {
        eggDao.update(egg);
    }

    public void deleteEgg(Egg egg) {
        eggDao.delete(egg);
    }

    //TODO: FIX WHERE CLAUSE
    public void getEgg(int eggId) {
        //eggDao.get(eggId);
    }

    public LiveData<List<Egg>> getAllEggs() {
        return eggDao.getAllItems();
    }


    //TODO: FIX PAGINATION
    public LiveData<List<Egg>> getPageOfEggs(int pageNum) {
        int to = pageNum * Constants.PAGE_ITEM_COUNT;
        int from = to - pageNum;
        return eggDao.getAllItems();
    }

    public void deleteAll() {
        eggDao.deleteAllItems();
    }
}
