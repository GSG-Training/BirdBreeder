package com.example.birdBreeder.Model.DataBase.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.birdBreeder.Model.Constants;
import com.example.birdBreeder.Model.DataBase.Entity.Notification;

import java.util.List;

@Dao
public interface NotificationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Notification notification);

    @Delete
    void delete(Notification notification);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Notification notification);

    @Query("DELETE FROM " + Constants.NOTIFICATION_TABLE)
    void deleteAllItems();

    @Query("SELECT * FROM " + Constants.NOTIFICATION_TABLE)
    LiveData<List<Notification>> getAllItems();

//    LiveData<List<Notification>> getPage();
//
//    void get(int id);
}

