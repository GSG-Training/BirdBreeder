package com.example.birdBreeder.Model.DataBase.Entity;

import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.birdBreeder.Model.Constants;

import java.util.Date;

@Entity(tableName = Constants.BIRDS_TABLE)
public class Bird {
    @PrimaryKey(autoGenerate = true)
    private int birdId;
    @ColumnInfo(name = "ring_no")
    private String ringNo;
    private String species;
    private double weight;
    private double cost;
    private int status;
    private Date bthDate;
    private boolean gender;
    private boolean offered;
    private Bitmap profileImage;
    private String desc;
    private String color;

    @Ignore
    public Bird(String ringNo, String species, Date bthDate, boolean gender) {
        this.ringNo = ringNo;
        this.species = species;
        this.bthDate = bthDate;
        this.gender = gender;
    }

    public Bird() {
    }


    @Ignore
    public Bird(String ringNo, String species, double weight, int status, Date bthDate) {
        this.ringNo = ringNo;
        this.species = species;
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

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
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

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
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

    public String getRingNo() {
        return ringNo;
    }

    public void setRingNo(String ringNo) {
        this.ringNo = ringNo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
