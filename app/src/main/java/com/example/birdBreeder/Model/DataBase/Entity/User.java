package com.example.birdBreeder.Model.DataBase.Entity;

import android.graphics.Bitmap;


//TODO SAVE IN SHARED_PREFERENCES
public class User {

    private int breederId;
    private String name;
    private Bitmap photo;
    private String password;
    private String email;
    private String phone;
    private String facebook;
    private String insta;
    private String twitter;

    public User() {
    }

    public User(int breederId, String name, String password, String email) {
        this.breederId = breederId;
        this.name = name;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

