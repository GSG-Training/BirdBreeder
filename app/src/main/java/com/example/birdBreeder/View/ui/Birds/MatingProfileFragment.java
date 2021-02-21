package com.example.birdBreeder.View.ui.Birds;

import android.app.DatePickerDialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import com.example.birdBreeder.Model.Constants;
import com.example.birdBreeder.Model.DataBase.Entity.Bird;
import com.example.birdBreeder.Model.DataBase.Entity.Mating;
import com.example.birdBreeder.R.array;
import com.example.birdBreeder.View.Pickers.DatePickerFragment;
import com.example.birdBreeder.View.ui.Activites.Helpers.BrowserHelper;
import com.example.birdBreeder.View.ui.Activites.Helpers.ValuesHelper;
import com.example.birdBreeder.ViewModel.BirdViewModel;
import com.example.birdBreeder.ViewModel.MatingViewModel;
import com.example.birdBreeder.databinding.FragmentMatingProfileBinding;
import java.util.List;



public class MatingProfileFragment extends Fragment implements DatePickerDialog.OnDateSetListener  {
    public static final String TAG = "com.example.birdBreeder.View.ui.Birds.MatingProfileFragment";
    private FragmentMatingProfileBinding binding;
    private int action, id;
    private MatingViewModel viewModel;
    private Observer<Mating> observer;
    private Observer<Bird> birdObserver;
    private Observer<Bird> updateBirdObserver;
    private Observer<List<String>> listObserver ;
    private Mating mating;
    private BirdViewModel birdViewModel ;
    private boolean checked = Constants.FEMALE ;
    private boolean fromRing = true ;
    private ArrayAdapter<String> femalesAdapter;
    private ArrayAdapter<String> statusAdapter;
    private ArrayAdapter<String> malesAdapter;
    private String species ;


    private MatingProfileFragment() {
        // Required empty public constructor
    }

    public static MatingProfileFragment newInstance(int action, int id) {
        MatingProfileFragment fragment = new MatingProfileFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.MATING_ACTION, action);
        args.putInt(Constants.MATING_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            action = getArguments().getInt(Constants.MATING_ACTION);
            id = getArguments().getInt(Constants.MATING_ID);
        }
        mating = new Mating();
        viewModel = new MatingViewModel(requireActivity().getApplication());
        birdViewModel = new BirdViewModel(requireActivity().getApplication());

    }//onCreate

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMatingProfileBinding.inflate(inflater, container, false);
        setObserver();
        setButtons();
        setLayout();
        return binding.getRoot();
    }//onCreateView

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        String date = ValuesHelper.setDateString(year, monthOfYear, dayOfMonth);
        binding.matingDate.setText(date);
        mating.setFormationDate(ValuesHelper.getDate(date, getContext()));
    }//onDateSet

    /**
     * Show DatePickerDialogFragment
     */
    private void showDatePickerDialog(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.setTargetFragment(MatingProfileFragment.this,  Constants.PICKER_REQUEST);
        newFragment.show(requireActivity().getSupportFragmentManager(), Constants.DATE_PICKER);
    }
    //setting the right concept of the layout
    private void setLayout(){
        addStatus();
        checked = Constants.MALE ;
        birdViewModel.getRingNOfMales().observe(getViewLifecycleOwner() , listObserver );
        if (id != -1){
            binding.nestId.setVisibility(View.VISIBLE);
            binding.labelId.setVisibility(View.VISIBLE);
            viewModel.getMating(id).observe(getViewLifecycleOwner(), observer);
        }
        else{
            binding.nestId.setVisibility(View.GONE);
            binding.labelId.setVisibility(View.GONE);
        }
        binding.femaleRingNo.setEnabled(false);
        binding.nestId.setEnabled(false);
        binding.maleId.setEnabled(false);
        binding.femaleId.setEnabled(false);
    }//setLayout

    //Observers Init
    private void setObserver() {
        //if the layout state is editing  a mating
        //Observer the mating from the id
        observer = observedMating -> {
            if(mating!=null){
                mating = observedMating;
                showMating();
            }
        };
        //Getting the list of string "ringNo" for both the males and females
        listObserver = strings -> {
            if(strings!=null){
                if(checked==Constants.FEMALE){
                    femalesAdapter = setSpinner(binding.femaleRingNo, strings);
                }else{
                    malesAdapter = setSpinner(binding.maleRingNo, strings);
                }
            }
        };

        birdObserver = bird -> {
            if(bird!=null){
               //if the bird getting from an id then set the right ringNo at selected spinner
            if(!fromRing)
            if(checked==Constants.FEMALE){
                int position = femalesAdapter.getPosition(bird.getRingNo());
                binding.femaleRingNo.setSelection(position);
            }else{
                int position = malesAdapter.getPosition(bird.getRingNo());
                binding.maleRingNo.setSelection(position);

            }
            else
                //if the bird is getting from the ringNo then fill the id
            if(checked==Constants.FEMALE){
               binding.femaleId.setText(String.format("%s", bird.getBirdId()));
            }else{
                binding.maleId.setText(String.format("%s", bird.getBirdId()));
                //After setting the right male enable the female and set the all the females from the same species
                binding.femaleRingNo.setEnabled(true);
                checked = Constants.FEMALE ;
                species=bird.getSpecies();
                birdViewModel.getRingNOfFemales(species).observe(getViewLifecycleOwner() , listObserver );
            }
          }
        };
        //updating the bird status according the mating status
        updateBirdObserver = bird -> {
            if(bird!=null){
                //there is no SEPARATED status at the bird
                if(mating.getStatus()!= Constants.SEPARATED)
                    bird.setStatus(mating.getStatus());
                else
                    bird.setStatus(Constants.IN_REST);
                birdViewModel.updateBird(bird);
            }
        };
    }//setObserver

    //Show the mating details in the layout
    private void showMating() {
        binding.nestId.setEnabled(false);
        binding.nestId.setText(String.format("%s", mating.getMatingId()));
        binding.femaleId.setText(String.format("%s", mating.getFemaleId()));
        binding.maleId.setText(String.format("%s", mating.getMaleId()));
        binding.matingDate.setText(ValuesHelper.getDate(mating.getFormationDate()));
        binding.matingStatus.setSelection(statusAdapter.getPosition(setMatingStatus(mating.getStatus())));
        checked = Constants.MALE ;
        birdViewModel.getBird(mating.getMaleId()).observe(getViewLifecycleOwner() , birdObserver );
        checked = Constants.FEMALE ;
        birdViewModel.getBird(mating.getFemaleId()).observe(getViewLifecycleOwner() , birdObserver );
    }//showMating


    //SAVE the mating details as mating
    private void storeMating() {
        mating.setFemaleId(binding.femaleId.getText().toString());
        mating.setMaleId(binding.maleId.getText().toString());
        mating.setStatus(getMatingStatus(binding.matingStatus.getSelectedItemPosition()));
        mating.setSpecies(species);
        mating.setFormationDate(ValuesHelper.getDate(binding.matingDate.getText().toString() ,
                requireContext()));
        //update the birds status
        birdViewModel.getBird(mating.getFemaleId()).observe(getViewLifecycleOwner() , updateBirdObserver);
        birdViewModel.getBird(mating.getMaleId()).observe(getViewLifecycleOwner() , updateBirdObserver);
    }//storeMating

    //activate buttons
    private void setButtons(){
        binding.saveMating.setOnClickListener(v -> {
            storeMating();
            if(action == Constants.EDIT_MATING){
                mating.setMatingId(id);
                viewModel.updateMating(mating);
            }else{
                viewModel.addMating(mating);
            }
            startActivity(BrowserHelper.toMainActivity(requireContext() , Constants.BIRDS_ITEM));
        });


        binding.maleRingNo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fromRing = true ;
                checked = Constants.MALE ;
                birdViewModel.getBird(malesAdapter.getItem(position)).observe(getViewLifecycleOwner() , birdObserver);
              

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                binding.maleId.setText("");
            }
        });
        binding.femaleRingNo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fromRing = true ;
                checked = Constants.FEMALE ;
                birdViewModel.getBird(femalesAdapter.getItem(position)).observe(getViewLifecycleOwner() , birdObserver);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                binding.femaleId.setText("");
            }
        });

        binding.matingDate.setClickable(true);
        binding.matingDate.setOnClickListener(this::showDatePickerDialog);
    }//setButtons

    //attach Adapter to the spinner
    private ArrayAdapter<String> setSpinner(Spinner spinner, List<String> items) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        return adapter ;
    }//SetSpinner

    //attach StatusAdapter to the spinner
    private void addStatus() {
        statusAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, getResources().getStringArray(array.mating_status));
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.matingStatus.setAdapter(statusAdapter);
    }//addStatus


    //convert intStatus to StringStatus
    private String setMatingStatus(int status) {
        switch (status) {
            case Constants.IN_BREEDING:
                return statusAdapter.getItem(1);

            case Constants.IN_REST:
                return statusAdapter.getItem(2);

            case Constants.SEPARATED:
                return statusAdapter.getItem(3);
        }

        return statusAdapter.getItem(1);
    }//setStatus

    //convert StringStatus to intStatus
    private int getMatingStatus(int statusPosition) {
        switch (statusPosition) {
            case 1:
                return Constants.IN_BREEDING;
            case 2:
                return Constants.IN_REST;
            case 3:
                return Constants.SEPARATED;
        }
        return Constants.IN_BREEDING;
    }//getStatus




}