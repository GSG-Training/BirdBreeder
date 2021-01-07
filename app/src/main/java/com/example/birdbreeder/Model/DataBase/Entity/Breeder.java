package com.example.birdbreeder.Model.DataBase.Entity;

import android.graphics.Bitmap;


public class Breeder {

    private int breederId ;
    private String name;
    private Bitmap photo;
    private String email;
    private String phone;
    private String facebook;
    private String insta ;
    private String twitter;
    private String description ;
    private int soldNum ;
    private int speciesNum ;
    private boolean fav ;

    public Breeder() {
    }

    public Breeder(int breederId, String name, String email) {
        this.breederId = breederId;
        this.name = name;
        this.email = email;
    }

    public int getBreederId() {
        return breederId;
    }

    public void setBreederId(int breederId) {
        this.breederId = breederId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSoldNum() {
        return soldNum;
    }

    public void setSoldNum(int soldNum) {
        this.soldNum = soldNum;
    }

    public int getSpeciesNum() {
        return speciesNum;
    }

    public void setSpeciesNum(int speciesNum) {
        this.speciesNum = speciesNum;
    }

    public boolean isFav() {
        return fav;
    }

    public void setFav(boolean fav) {
        this.fav = fav;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInsta() {
        return insta;
    }

    public void setInsta(String insta) {
        this.insta = insta;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }
}

