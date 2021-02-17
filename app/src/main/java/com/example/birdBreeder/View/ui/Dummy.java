package com.example.birdBreeder.View.ui;

import android.app.Application;

import com.example.birdBreeder.Model.Constants;
import com.example.birdBreeder.Model.DataBase.Entity.Notification;
import com.example.birdBreeder.Model.DataBase.Entity.Species;
import com.example.birdBreeder.ViewModel.BirdViewModel;
import com.example.birdBreeder.ViewModel.NotificationViewModel;
import com.example.birdBreeder.ViewModel.SpeciesViewModel;

public class Dummy {
    public static void addBirds(Application application) {
        BirdViewModel viewModel = new BirdViewModel(application);
//        viewModel.addBird(new Bird("H2152",1,new Date(),BirdBreederConstants.MALE));
//        viewModel.addBird(new Bird("H214",2,new Date(),BirdBreederConstants.MALE));
//        viewModel.addBird(new Bird("H2752",2,new Date(),BirdBreederConstants.FEMALE));
//        viewModel.addBird(new Bird("H2552",1,new Date(),BirdBreederConstants.FEMALE));
    }


    public static void addSpecies(Application application) {
        SpeciesViewModel viewModel = new SpeciesViewModel(application);
        viewModel.addSpecies(new Species("Love Birds", Constants.WILD_BIRDS));
        viewModel.addSpecies(new Species("Kanari", Constants.PET_BIRDS));
        viewModel.addSpecies(new Species("Cocktail", Constants.PET_BIRDS));
        viewModel.addSpecies(new Species("Birds", Constants.WILD_BIRDS));
        viewModel.addSpecies(new Species("Eagle", Constants.WILD_BIRDS));
    }


    public static void addNotifications(Application application) {
        NotificationViewModel viewModel = new NotificationViewModel(application);
        viewModel.addNotification(new Notification("Title", "DESCCCCCCCCCCCCCCCCCCCCCCCC"));
        viewModel.addNotification(new Notification("Title 1", "DESCCCCCCCCCCCCCCCCCCCCCCCC"));
        viewModel.addNotification(new Notification("Title 2", "DESCCCCCCCCCCCCCCCCCCCCCCCC"));
        viewModel.addNotification(new Notification("Title 3", "DESCCCCCCCCCCCCCCCCCCCCCCCC"));
        viewModel.addNotification(new Notification("Title 4", "DESCCCCCCCCCCCCCCCCCCCCCCCC"));


    }
}
