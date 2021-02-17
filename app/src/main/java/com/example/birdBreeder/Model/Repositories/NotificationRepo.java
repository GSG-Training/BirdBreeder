package com.example.birdBreeder.Model.Repositories;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.birdBreeder.Model.Constants;
import com.example.birdBreeder.Model.DataBase.BirdsDataBase;
import com.example.birdBreeder.Model.DataBase.DAO.NotificationDao;
import com.example.birdBreeder.Model.DataBase.Entity.Notification;

import java.util.List;

public class NotificationRepo {
    private NotificationDao notificationDao;

    public NotificationRepo(Context context) {
        notificationDao = BirdsDataBase.getInstance(context).notificationDao();
    }


    public void addNotification(Notification notification) {
        notificationDao.insert(notification);
    }

    public void updateNotification(Notification notification) {
        notificationDao.update(notification);
    }

    public void deleteNotification(Notification notification) {
        notificationDao.delete(notification);
    }

    //TODO: FIX WHERE CLAUSE
    public void getNotification(int id) {
        //notificationDao.get(id);
    }

    public LiveData<List<Notification>> getAllNotifications() {
        return notificationDao.getAllItems();
    }


    //TODO: FIX PAGINATION
    public LiveData<List<Notification>> getPageOfNotification(int pageNum) {
        int to = pageNum * Constants.PAGE_ITEM_COUNT;
        int from = to - pageNum;
        return notificationDao.getAllItems();
    }

    public void deleteAll() {
        notificationDao.deleteAllItems();
    }
}
