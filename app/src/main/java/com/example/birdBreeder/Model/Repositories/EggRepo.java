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

    public LiveData<Egg> getEgg(int eggId) {
         return eggDao.get(eggId);
    }

    public LiveData<List<Egg>> getAllEggs(int matingId) {
        return eggDao.getAllItems(matingId);
    }



    public void deleteAll() {
        eggDao.deleteAllItems();
    }
}
