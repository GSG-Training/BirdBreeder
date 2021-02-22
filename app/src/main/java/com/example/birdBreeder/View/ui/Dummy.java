package com.example.birdBreeder.View.ui;

import android.app.Application;

import com.example.birdBreeder.Model.Constants;
import com.example.birdBreeder.Model.DataBase.Entity.Species;
import com.example.birdBreeder.ViewModel.SpeciesViewModel;

public class Dummy {



    public static void addSpecies(Application application) {
        SpeciesViewModel viewModel = new SpeciesViewModel(application);
        viewModel.deleteAll();
        viewModel.addSpecies(new Species("Love Birds", 23));
        viewModel.addSpecies(new Species("Canary", 14));
        viewModel.addSpecies(new Species("Cockatiel", 28));
        viewModel.addSpecies(new Species("Goldfinch",13));
        viewModel.addSpecies(new Species("House Sparrow", 14));
    }


}
