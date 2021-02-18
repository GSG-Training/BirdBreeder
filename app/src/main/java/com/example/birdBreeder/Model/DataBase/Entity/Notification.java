package com.example.birdBreeder.Model.DataBase.Entity;

import androidx.room.Entity;

import androidx.room.PrimaryKey;

import com.example.birdBreeder.Model.Constants;
import com.example.birdBreeder.View.ui.Activites.Helpers.ValuesHelper;

import java.util.Date;

@Entity(tableName = Constants.NOTIFICATION_TABLE)
public class Notification {
    @PrimaryKey
    private int notificationId;
    private String description;
    private Date date ;
    private int status;

    public Notification(int notificationId , Date date, int status) {
        this.notificationId = notificationId;
        this.description = "Egg Id : " + notificationId + "is expected to hatch on " + ValuesHelper.getDate(date);
        this.date = date;
        this.status = status;
    }

    public int getNotificationId() {
        return notificationId;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
