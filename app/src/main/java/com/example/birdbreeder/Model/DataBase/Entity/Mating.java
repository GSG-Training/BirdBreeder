package com.example.birdbreeder.Model.DataBase.Entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.birdbreeder.Model.BirdBreederConstants;

import java.util.Date;

@Entity (tableName = BirdBreederConstants.MATING_TABLE)
public class Mating {
    @PrimaryKey(autoGenerate = true)
    private int matingId ;
    @ForeignKey(entity = Bird.class, parentColumns= "birdId", childColumns = "maleId", deferred = false)
    private String maleId ;
    @ForeignKey(entity = Bird.class, parentColumns= "birdId", childColumns = "femaleId", deferred = false)
    private String femaleId ;
    private String species;
   private Date formationDate ;
   private int  status;
   private int totalEggsNum ;
   private int hatchedEggsNum ;
   private int incubatedEggsNum ;


    public Mating() {
    }

    @Ignore
    public Mating(String maleId, String femaleId, Date formationDate) {
        this.maleId = maleId;
        this.femaleId = femaleId;
        this.formationDate = formationDate;
    }

    public int getMatingId() {
        return matingId;
    }

    public void setMatingId(int matingId) {
        this.matingId = matingId;
    }

    public String getMaleId() {
        return maleId;
    }

    public void setMaleId(String maleId) {
        this.maleId = maleId;
    }

    public String getFemaleId() {
        return femaleId;
    }

    public void setFemaleId(String femaleId) {
        this.femaleId = femaleId;
    }

    public Date getFormationDate() {
        return formationDate;
    }

    public void setFormationDate(Date formationDate) {
        this.formationDate = formationDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public int getTotalEggsNum() {
        return totalEggsNum;
    }

    public void setTotalEggsNum(int totalEggsNum) {
        this.totalEggsNum = totalEggsNum;
    }

    public int getHatchedEggsNum() {
        return hatchedEggsNum;
    }

    public void setHatchedEggsNum(int hatchedEggsNum) {
        this.hatchedEggsNum = hatchedEggsNum;
    }

    public int getIncubatedEggsNum() {
        return incubatedEggsNum;
    }

    public void setIncubatedEggsNum(int incubatedEggsNum) {
        this.incubatedEggsNum = incubatedEggsNum;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }
}
