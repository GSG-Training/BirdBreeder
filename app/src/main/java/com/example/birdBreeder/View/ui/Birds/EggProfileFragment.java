package com.example.birdBreeder.View.ui.Birds;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;

import com.example.birdBreeder.Model.Constants;
import com.example.birdBreeder.Model.DataBase.Entity.Egg;
import com.example.birdBreeder.Model.DataBase.Entity.Species;
import com.example.birdBreeder.R;
import com.example.birdBreeder.View.Pickers.DatePickerFragment;
import com.example.birdBreeder.View.ui.Activites.Helpers.ValuesHelper;
import com.example.birdBreeder.ViewModel.EggViewModel;
import com.example.birdBreeder.ViewModel.SpeciesViewModel;
import com.example.birdBreeder.databinding.FragmentEggProfileBinding;


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
    private EggProfileFragment() {
        // Required empty public constructor
    }


    public static EggProfileFragment newInstance(int action, int id) {
        EggProfileFragment fragment = new EggProfileFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.EGG_ACTION, action);
        args.putInt(Constants.EGG_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            action = getArguments().getInt(Constants.EGG_ACTION);
            id = getArguments().getInt(Constants.EGG_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentEggProfileBinding.inflate(inflater , container , false);
        addStatus();
        binding.eggLaidDate.setClickable(true);
        binding.eggLaidDate.setOnClickListener(this::showDatePickerDialog);
        return binding.getRoot();
    }


    //add the adapter of bird status to its Spinner
    private void addStatus() {
        statusAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.egg_status));
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.eggStatusSpinner.setAdapter(statusAdapter);
    }//addStatus

    //convert intStatus to StringStatus
    private String setEggStatus(int status) {
        switch (status) {
            case Constants.SICK:
                return statusAdapter.getItem(1);

            case Constants.SOLD:
                return statusAdapter.getItem(2);

            case Constants.IN_REST:
                return statusAdapter.getItem(3);

            case Constants.IN_BREEDING:
                return statusAdapter.getItem(4);

        }

        return statusAdapter.getItem(3);
    }//setStatus

    //convert StringStatus to intStatus
    private int getEggStatus(int statusPosition) {
        switch (statusPosition) {
            case 1:
                return Constants.SICK;
            case 2:
                return Constants.SOLD;
            case 3:
                return Constants.IN_REST;
            case 4:
                return Constants.IN_BREEDING;
        }
        return Constants.IN_REST;

    }//getStatus

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        String date = ValuesHelper.setDateString(year, monthOfYear, dayOfMonth);
        binding.eggLaidDate.setText(date);
        //egg.setLayDate(ValuesHelper.getDate(date, getContext()));

    }

    /**
     * Show DatePickerDialogFragment
     */
    private void showDatePickerDialog(View view) {
        DialogFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.setTargetFragment(EggProfileFragment.this , Constants.PICKER_REQUEST);
        datePickerFragment.show(requireActivity().getSupportFragmentManager(), Constants.DATE_PICKER);
    }


}