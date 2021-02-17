package com.example.birdBreeder.Model.DataBase.Entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.birdBreeder.Model.Constants;

import java.util.Date;

@Entity(tableName = Constants.EGG_TABLE)
public class Egg {
    @PrimaryKey(autoGenerate = true)
    private int eggId;
    @ForeignKey(entity = Mating.class, parentColumns = "matingId", childColumns = "matingId", deferred = false)
    private int matingId;
    private Date layDate;
    private Date incubationDate;
    private Date expectedHatchingDate;
    private int status;

    public Egg() {
    }

    @Ignore
    public Egg(int matingId, Date layDate) {
        this.matingId = matingId;
        this.layDate = layDate;
    }

    public int getEggId() {
        return eggId;
    }

    public void setEggId(int eggId) {
        this.eggId = eggId;
    }

    public int getMatingId() {
        return matingId;
    }

    public void setMatingId(int matingId) {
        this.matingId = matingId;
    }

    public Date getLayDate() {
        return layDate;
    }

    public void setLayDate(Date layDate) {
        this.layDate = layDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getIncubationDate() {
        return incubationDate;
    }

    public void setIncubationDate(Date incubationDate) {
        this.incubationDate = incubationDate;
    }

    public Date getExpectedHatchingDate() {
        return expectedHatchingDate;
    }

    public void setExpectedHatchingDate(Date expectedHatchingDate) {
        this.expectedHatchingDate = expectedHatchingDate;
    }


}
