package com.example.birdBreeder.View.ui;

import android.app.Application;

import com.example.birdBreeder.Model.Constants;
import com.example.birdBreeder.Model.DataBase.Entity.Species;
import com.example.birdBreeder.ViewModel.SpeciesViewModel;

public class Dummy {



    public static void addSpecies(Application application) {
        SpeciesViewModel viewModel = new SpeciesViewModel(application);
        viewModel.addSpecies(new Species("Love Birds", Constants.WILD_BIRDS));
        viewModel.addSpecies(new Species("Canary", Constants.PET_BIRDS));
        viewModel.addSpecies(new Species("Cocktail", Constants.PET_BIRDS));
        viewModel.addSpecies(new Species("Birds", Constants.WILD_BIRDS));
        viewModel.addSpecies(new Species("Eagle", Constants.WILD_BIRDS));
    }


}
