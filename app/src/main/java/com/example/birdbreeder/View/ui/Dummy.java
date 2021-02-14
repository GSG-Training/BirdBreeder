package com.example.birdbreeder.View.ui;

import android.app.Application;
import android.net.CaptivePortal;

import com.example.birdbreeder.Model.BirdBreederConstants;
import com.example.birdbreeder.Model.DataBase.Entity.Bird;
import com.example.birdbreeder.Model.DataBase.Entity.Egg;
import com.example.birdbreeder.Model.DataBase.Entity.Notification;
import com.example.birdbreeder.Model.DataBase.Entity.Species;
import com.example.birdbreeder.ViewModel.BirdViewModel;
import com.example.birdbreeder.ViewModel.EggViewModel;
import com.example.birdbreeder.ViewModel.MatingViewModel;
import com.example.birdbreeder.ViewModel.NotificationViewModel;
import com.example.birdbreeder.ViewModel.SpeciesViewModel;

import java.util.Date;

public class Dummy {
    public static void addBirds(Application application){
        BirdViewModel viewModel = new BirdViewModel(application);
//        viewModel.addBird(new Bird("H2152",1,new Date(),BirdBreederConstants.MALE));
//        viewModel.addBird(new Bird("H214",2,new Date(),BirdBreederConstants.MALE));
//        viewModel.addBird(new Bird("H2752",2,new Date(),BirdBreederConstants.FEMALE));
//        viewModel.addBird(new Bird("H2552",1,new Date(),BirdBreederConstants.FEMALE));
    }


    public static void addSpecies(Application application){
        SpeciesViewModel viewModel = new SpeciesViewModel(application);
        viewModel.addSpecies(new Species("Love Birds" , BirdBreederConstants.WILD_BIRDS));
        viewModel.addSpecies(new Species("Kanari" , BirdBreederConstants.PET_BIRDS));
        viewModel.addSpecies(new Species("Cocktail" , BirdBreederConstants.PET_BIRDS));
        viewModel.addSpecies(new Species("Birds" , BirdBreederConstants.WILD_BIRDS));
        viewModel.addSpecies(new Species("Eagle" , BirdBreederConstants.WILD_BIRDS));
    }



    public static void addNotifications(Application application){
        NotificationViewModel viewModel = new NotificationViewModel(application);
        viewModel.addNotification(new Notification("Title" , "DESCCCCCCCCCCCCCCCCCCCCCCCC"));
        viewModel.addNotification(new Notification("Title 1" , "DESCCCCCCCCCCCCCCCCCCCCCCCC"));
        viewModel.addNotification(new Notification("Title 2" , "DESCCCCCCCCCCCCCCCCCCCCCCCC"));
        viewModel.addNotification(new Notification("Title 3" , "DESCCCCCCCCCCCCCCCCCCCCCCCC"));
        viewModel.addNotification(new Notification("Title 4" , "DESCCCCCCCCCCCCCCCCCCCCCCCC"));


    }
}
