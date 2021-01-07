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
    @PrimaryKey
    private String birdId ;
    @ForeignKey(entity = Species.class, parentColumns= "speciesId", childColumns = "speciesId", deferred = false)
    private int speciesId;
    private String species ;
    private double weight;
    private double cost;
    private int status ;
    private Date bthDate ;
    private int gender ;
    private boolean offered ;
    private Bitmap profileImage ;

    @Ignore
    public Bird(String birdId, int speciesId, Date bthDate , int gender) {
        this.birdId = birdId;
        this.speciesId = speciesId;
        this.bthDate = bthDate;
        this.gender=gender;
    }

    public Bird() {
    }
     @Ignore
    public Bird(String birdId, int speciesId, double weight, int status, Date bthDate) {
        this.birdId = birdId;
        this.speciesId = speciesId;
        this.weight = weight;
        this.status = status;
        this.bthDate = bthDate;
    }

    public String getBirdId() {
        return birdId;
    }

    public void setBirdId(String birdId) {
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
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }
}
