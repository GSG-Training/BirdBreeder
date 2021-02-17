package com.example.birdBreeder.Model.Repositories;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.birdBreeder.Model.DataBase.BirdsDataBase;
import com.example.birdBreeder.Model.DataBase.DAO.MatingDao;
import com.example.birdBreeder.Model.DataBase.Entity.Mating;

import java.util.List;

public class MatingRepo {
    private MatingDao matingDao;

    public MatingRepo(Context context) {
        matingDao = BirdsDataBase.getInstance(context).matingDao();
    }


    public void addMating(Mating mating) {
        matingDao.insert(mating);
    }

    public void updateMating(Mating mating) {
        matingDao.update(mating);
    }

    public void deleteMating(Mating mating) {
        matingDao.delete(mating);
    }


    public LiveData<Mating> getMating(int id) {
        return matingDao.get(id);
    }

    public LiveData<List<Mating>> getAllMatings() {
        return matingDao.getAllItems();
    }


    public void deleteAll() {
        matingDao.deleteAllItems();
    }
}
