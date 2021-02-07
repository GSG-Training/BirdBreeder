package com.example.birdbreeder.Model.DataBase.Entity;

import android.graphics.Bitmap;
import android.media.MediaActionSound;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.birdbreeder.Model.BirdBreederConstants;

import java.util.Date;
@Entity (tableName = BirdBreederConstants.BIRDS_TABLE)
public class Bird {
    @PrimaryKey(autoGenerate = true)
    private int birdId ;
    private String ringNo ;
    @ForeignKey(entity = Species.class, parentColumns= "speciesId", childColumns = "speciesId", deferred = false)
    private int speciesId;
    private String species = "CANARY" ;
    private double weight;
    private double cost;
    private int status ;
    @Ignore
    private Date bthDate ;
    private int gender ;
    private boolean offered ;
    @Ignore
    private Bitmap profileImage ;
    //private int breederId "Foreign KEY"

    @Ignore
    public Bird(String ringNo, int speciesId, Date bthDate , int gender) {
        this.ringNo = ringNo;
        this.speciesId = speciesId;
        this.bthDate = bthDate;
        this.gender=gender;
    }

    public Bird() {
    }
     @Ignore
    public Bird(String ringNo, int speciesId, double weight, int status, Date bthDate) {
         this.ringNo = ringNo;
        this.speciesId = speciesId;
        this.weight = weight;
        this.status = status;
        this.bthDate = bthDate;
    }

    public int getBirdId() {
        return birdId;
    }

    public void setBirdId(int birdId) {
        this.birdId = birdId;
    }

    public int getSpeciesId() {
        return speciesId;
    }

    public void setSpeciesId(int speciesId) {
        this.speciesId = speciesId;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getBthDate() {
        return bthDate;
    }

    public void setBthDate(Date bthDate) {
        this.bthDate = bthDate;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public boolean isOffered() {
        return offered;
    }

    public void setOffered(boolean offered) {
        this.offered = offered;
    }

    public Bitmap getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Bitmap profileImage) {
        this.profileImage = profileImage;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getSpecies() {
        //TODO:  SPECIES STRING FIXING
        return "species";
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getRingNo() {
        return ringNo;
    }

    public void setRingNo(String ringNo) {
        this.ringNo = ringNo;
    }
}
