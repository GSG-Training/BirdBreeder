package com.example.birdbreeder.Model.DataBase.Entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.birdbreeder.Model.Constants;


@Entity (tableName = Constants.SPECIES_TABLE)
public class Species {
    @PrimaryKey(autoGenerate = true)
    private int speciesId;
    private int imageRes ;
    private String name ;
    private int daysForEgg;
    private int type;

    public Species() {
    }

    @Ignore
    public Species(String name, int type) {
        this.name = name;
        this.type = type;
    }

    public int getSpeciesId() {
        return speciesId;
    }

    public void setSpeciesId(int speciesId) {
        this.speciesId = speciesId;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDaysForEgg() {
        return daysForEgg;
    }

    public void setDaysForEgg(int daysForEgg) {
        this.daysForEgg = daysForEgg;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
