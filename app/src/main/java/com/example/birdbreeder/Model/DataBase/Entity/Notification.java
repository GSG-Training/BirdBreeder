package com.example.birdbreeder.Model.DataBase.Entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.birdbreeder.Model.Constants;

@Entity (tableName = Constants.NOTIFICATION_TABLE)
public class Notification {
    @PrimaryKey(autoGenerate = true)
    private int notificationId;
    private String title ;
    private int status ;
    private String description;

    public Notification() {
    }

    @Ignore
    public Notification(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
