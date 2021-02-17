package com.example.birdBreeder.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.birdBreeder.Model.DataBase.Entity.Notification;
import com.example.birdBreeder.Model.Repositories.NotificationRepo;

import java.util.List;

public class NotificationViewModel extends AndroidViewModel {
    private NotificationRepo repo;

    public NotificationViewModel(@NonNull Application application) {
        super(application);
        repo = new NotificationRepo(application);
    }

    public void addNotification(Notification notification) {
        repo.addNotification(notification);
    }

    public void updateNotification(Notification notification) {
        repo.updateNotification(notification);
    }

    public void deleteNotification(Notification notification) {
        repo.deleteNotification(notification);
    }

    public void getNotification(int id) {
        repo.getNotification(id);
    }

    public LiveData<List<Notification>> getAllNotifications() {
        return repo.getAllNotifications();
    }


    public LiveData<List<Notification>> getPageOfNotification(int pageNum) {
        return repo.getPageOfNotification(pageNum);
    }

    public void deleteAll() {
        repo.deleteAll();
    }
}
