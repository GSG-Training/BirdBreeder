package com.example.birdBreeder.View.ui.Birds;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.birdBreeder.Model.Constants;
import com.example.birdBreeder.Model.DataBase.Entity.Egg;
import com.example.birdBreeder.Model.DataBase.Entity.Notification;
import com.example.birdBreeder.Model.DataBase.Entity.Species;
import com.example.birdBreeder.R;
import com.example.birdBreeder.View.Pickers.DatePickerFragment;
import com.example.birdBreeder.View.ui.Activites.Helpers.BrowserHelper;
import com.example.birdBreeder.View.ui.Activites.Helpers.ValuesHelper;
import com.example.birdBreeder.ViewModel.EggViewModel;
import com.example.birdBreeder.ViewModel.NotificationViewModel;
import com.example.birdBreeder.ViewModel.SpeciesViewModel;
import com.example.birdBreeder.databinding.FragmentEggProfileBinding;

import java.util.Date;
import java.util.concurrent.TimeUnit;


public class EggProfileFragment extends Fragment implements DatePickerDialog.OnDateSetListener {
    public static final String TAG = "com.example.birdBreeder.View.ui.Birds.EggProfileFragment";
    private FragmentEggProfileBinding binding ;
    private Egg egg ;
    private int id , action ;
    private EggViewModel eggViewModel ;
    private Observer<Egg> observer ;
    private SpeciesViewModel speciesViewModel ;
    private Observer<Species> speciesObserver ;
    private ArrayAdapter<String> statusAdapter ;
    private NotificationViewModel viewModel ;


    private EggProfileFragment() {
        // Required empty public constructor
    }


    public static EggProfileFragment newInstance(int action, int id ,int matingId , String species) {
        EggProfileFragment fragment = new EggProfileFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.EGG_ACTION, action);
        args.putInt(Constants.EGG_ID, id);
        args.putString(Constants.EGG_SPECIES, species);
        args.putInt(Constants.MATING_ID, matingId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eggViewModel = new EggViewModel(requireActivity().getApplication());
        speciesViewModel = new SpeciesViewModel(requireActivity().getApplication());
        viewModel = new NotificationViewModel(requireActivity().getApplication());
        egg= new Egg();
        if (getArguments() != null) {
            action = getArguments().getInt(Constants.EGG_ACTION);
            id = getArguments().getInt(Constants.EGG_ID);
            egg.setSpecies(getArguments().getString(Constants.EGG_SPECIES));
            egg.setMatingId(getArguments().getInt(Constants.MATING_ID));
        }


    }//onCreate

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentEggProfileBinding.inflate(inflater , container , false);

        addStatus();
        setLayout();
        setObservers();
        if(action == Constants.EDIT_EGG) eggViewModel.getEgg(id).observe(getViewLifecycleOwner() , observer);
        return binding.getRoot();
    }

    //set THE RIGHT LAYOUT
    private void  setLayout(){
        binding.eggStartDate.setEnabled(false);
        binding.showEggId.setEnabled(false);
        binding.eggLaidDate.setClickable(true);
        binding.eggLaidDate.setOnClickListener(this::showDatePickerDialog);
        binding.saveEgg.setOnClickListener(this::storeEgg);

    }//setLayout

    //Init Observers
    private void setObservers(){
       observer = observedEgg -> {
           egg = observedEgg ;
           setDetails();
       };

       speciesObserver = species -> {
           int days = species.getDaysForEgg() ;
           getExpectedDate(days);
       };
    }//setObservers



    //getDetails as egg
    private void storeEgg(View view){
        egg.setStatus(getEggStatus(binding.eggStatusSpinner.getSelectedItemPosition()));
        if(id!=-1){
            egg.setEggId(id);
            eggViewModel.updateEgg(egg);
            Toast.makeText(getContext(), R.string.egg_updated, Toast.LENGTH_SHORT).show();
        }else{
            eggViewModel.addEgg(egg);
            Toast.makeText(getContext(), R.string.egg_added, Toast.LENGTH_SHORT).show();
        }
        viewModel.addNotification(new Notification( egg.getEggId()  , egg.getExpectedHatchingDate() , Constants.UN_VIEWED));
        BrowserHelper.toFragment(requireActivity().getSupportFragmentManager() ,
                 MatingControlFragment.newInstance(egg.getMatingId()) ,
                        TAG );
    }//storeEgg

    //Details
    private void setDetails(){
        binding.showEggId.setText(String.format("%s", egg.getEggId()));
        binding.eggLaidDate.setText(ValuesHelper.getDate(egg.getLayDate()));
        binding.eggStartDate.setText(ValuesHelper.getDate(egg.getExpectedHatchingDate()));
        binding.eggStatusSpinner.setSelection(statusAdapter.getPosition(setEggStatus(egg.getStatus())));
    }//setDetails

    //add the adapter of bird status to its Spinner
    private void addStatus() {
        statusAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.egg_status));
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.eggStatusSpinner.setAdapter(statusAdapter);
    }//addStatus

    //convert intStatus to StringStatus
    private String setEggStatus(int status) {
        switch (status) {
            case Constants.FAILED_INCUBATING:
                return statusAdapter.getItem(0);

            case Constants.INCUBATED:
                return statusAdapter.getItem(1);

            case Constants.UNFERTILIZED:
                return statusAdapter.getItem(2);

            case Constants.HATCHED_OK:
                return statusAdapter.getItem(3);

        }

        return statusAdapter.getItem(1);
    }//setStatus

    //convert StringStatus to intStatus
    private int getEggStatus(int statusPosition) {
        switch (statusPosition) {
            case 0:
                //todo : Delete the egg
                return Constants.FAILED_INCUBATING;
            case 1:
                return Constants.INCUBATED;
            case 2:
                return Constants.UNFERTILIZED;
            case 3:
                //todo : Add as a New bird
                return Constants.HATCHED_OK;
        }
        return Constants.INCUBATED;

    }//getStatus

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        String date = ValuesHelper.setDateString(year, monthOfYear, dayOfMonth);
        binding.eggLaidDate.setText(date);
        egg.setLayDate(ValuesHelper.getDate(date, getContext()));
        speciesViewModel.getSpecies(egg.getSpecies()).observe(getViewLifecycleOwner() , speciesObserver);
    }//onDateSet

    /**
     * Show DatePickerDialogFragment
     */
    private void showDatePickerDialog(View view) {
        DialogFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.setTargetFragment(EggProfileFragment.this , Constants.PICKER_REQUEST);
        datePickerFragment.show(requireActivity().getSupportFragmentManager(), Constants.DATE_PICKER);
    }//showDatePickerDialog


    //Calculate the date
    private void getExpectedDate(int days) {
        Date date = new Date();
        date.setTime(egg.getLayDate().getTime() + TimeUnit.DAYS.toMillis(days));
        egg.setExpectedHatchingDate(date);
        binding.eggStartDate.setText(ValuesHelper.getDate(egg.getExpectedHatchingDate()));

    }//getExpectedDate


}