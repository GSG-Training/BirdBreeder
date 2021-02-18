package com.example.birdBreeder.View.ui.Birds;


import android.app.DatePickerDialog;
import androidx.fragment.app.DialogFragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import com.example.birdBreeder.Model.Constants;
import com.example.birdBreeder.Model.DataBase.Entity.Bird;
import com.example.birdBreeder.R;
import com.example.birdBreeder.View.Pickers.DatePickerFragment;
import com.example.birdBreeder.View.ui.Activites.Helpers.BrowserHelper;
import com.example.birdBreeder.View.ui.Activites.Helpers.ValuesHelper;
import com.example.birdBreeder.ViewModel.BirdViewModel;
import com.example.birdBreeder.ViewModel.SpeciesViewModel;
import com.example.birdBreeder.databinding.FragmentBirdProfileBinding;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import static android.app.Activity.RESULT_OK;

public class BirdProfileFragment extends Fragment implements DatePickerDialog.OnDateSetListener {
    public static final String TAG = "com.example.birdBreeder.View.ui.Birds.BirdProfileFragment";
    private FragmentBirdProfileBinding binding;
    private int action, id;
    private BirdViewModel birdViewModel;
    private SpeciesViewModel speciesViewModel;
    private Observer<Bird> observer;
    private Observer<List<String>> listObserver;
    private ArrayAdapter<String> speciesAdapter;
    private ArrayAdapter<String> statusAdapter;
    private boolean enable;
    private Bird bird;

    private BirdProfileFragment() {
        // Required empty public constructor
    }

    public static BirdProfileFragment newInstance(int action, int id) {
        BirdProfileFragment fragment = new BirdProfileFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.BIRD_ACTION, action);
        args.putInt(Constants.BIRD_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            action = getArguments().getInt(Constants.BIRD_ACTION);
            id = getArguments().getInt(Constants.BIRD_ID);
        }
        birdViewModel = new BirdViewModel(requireActivity().getApplication());
        speciesViewModel = new SpeciesViewModel(requireActivity().getApplication());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBirdProfileBinding.inflate(inflater, container, false);
        bird = new Bird();
        setObserver();
        setButtons();
        //Date Choose
        binding.birdBirthDate.setClickable(true);
        binding.birdBirthDate.setOnClickListener(this::showDatePickerDialog);
        addStatus();
        speciesViewModel.getNamesOfSpecies().observeForever(listObserver);
        return binding.getRoot();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //result of pick image from gallery for bird profile image
        if (resultCode == RESULT_OK && requestCode == Constants.PICK_IMAGE) {
            Bitmap photo = null;
            if (data != null) {
                try {
                    photo = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), data.getData());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            binding.birdImage.setImageBitmap(photo);
            bird.setProfileImage(photo);

        }

    }

    //The Layout has 2 states
    // 1 : Add a new Bird
    // 2 : Show a Bird and edit it
    private void setLayout() {
        if (action == Constants.NEW_BIRD) {
            enable = true;
            setEnable();
        } else {
            enable = false;
            setEnable();
            if (id != -1)
                birdViewModel.getBird(id).observeForever(observer);
        }

    }//setLayout

    //Activate Observers
    private void setObserver() {
        observer = birdObserved -> {
            if (birdObserved != null) {
                bird = birdObserved;
                bind(birdObserved);
            }
        };

        listObserver = strings -> {
            setSpecies(strings);
            setLayout();
        };
    }//setObserver

    //getAge of a bird
    private String getToday() {
        Date today = Calendar.getInstance().getTime();
        return ValuesHelper.getDate(today);
    }//getToday


    //Show the Bird at the views
    private void bind(Bird bird) {
        binding.birdId.setText(String.format("%s", bird.getBirdId()));
        binding.birdRing.setText(bird.getRingNo());
        binding.birdBirthDate.setText(ValuesHelper.getDate(bird.getBthDate()));
        binding.birdAge.setText(ValuesHelper.getAge(getToday(), ValuesHelper.getDate(bird.getBthDate())));
        binding.birdDesc.setText(bird.getDesc());
        binding.birdColor.setText(bird.getColor());
        binding.offered.setChecked(bird.isOffered());
        binding.birdCost.setText(String.format("%s", bird.getCost()));
        if (bird.getProfileImage() != null)
            binding.birdImage.setImageBitmap(bird.getProfileImage());
        binding.birdSpecies.setSelection(speciesAdapter.getPosition(bird.getSpecies()));
        binding.birdStatus.setSelection(statusAdapter.getPosition(setBirdStatus(bird.getStatus())));
        binding.birdWeight.setText(String.format("%s", bird.getWeight()));
        binding.gender.setChecked(bird.getGender());
    }//bind


    //Control the ability of the views
    private void setEnable() {
        binding.birdId.setEnabled(false);
        binding.birdRing.setEnabled(enable);
        binding.birdBirthDate.setEnabled(enable);
        binding.birdAge.setEnabled(false);
        binding.birdDesc.setEnabled(enable);
        binding.birdColor.setEnabled(enable);
        binding.offered.setEnabled(enable);
        binding.birdCost.setEnabled(enable);
        binding.birdSpecies.setEnabled(enable);
        binding.birdWeight.setEnabled(enable);
        binding.gender.setEnabled(enable);
        binding.birdStatus.setEnabled(enable);
        if (enable) {
            binding.label.setVisibility(View.GONE);
            binding.birdId.setVisibility(View.GONE);
            binding.profileButton.setText(R.string.save);
            binding.editImage.setVisibility(View.VISIBLE);
        } else {
            binding.label.setVisibility(View.VISIBLE);
            binding.birdId.setVisibility(View.VISIBLE);
            binding.profileButton.setText(R.string.edit);
            binding.editImage.setVisibility(View.GONE);
        }

    }//set Enable

    //Show the Species at its Spinner
    private void setSpecies(List<String> species) {
        speciesAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, species);
        speciesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.birdSpecies.setAdapter(speciesAdapter);
    }//setSpecies

    //Activate Buttons in Layout
    private void setButtons() {
        //Offer Changes the visibility of birdCost EditText
        binding.offered.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) binding.birdCost.setVisibility(View.VISIBLE);
            else binding.birdCost.setVisibility(View.GONE);
        });

        //profileButton has 2 states
        // 1 : Save the added or updated Bird
        // 2 : Allow Edit an exist Bird
        binding.profileButton.setOnClickListener(view -> {
            if (enable) {
                //1:
                storeBird();
                if (action == Constants.SHOW_BIRD) {
                    bird.setBirdId(ValuesHelper.toInteger(binding.birdId.getText().toString()));
                    birdViewModel.updateBird(bird);
                    Toast.makeText(getContext(), getText(R.string.update_note), Toast.LENGTH_SHORT).show();
                } else {
                    birdViewModel.addBird(bird);
                    Toast.makeText(getContext(), getText(R.string.add_note), Toast.LENGTH_SHORT).show();
                }
                enable = false;
                BrowserHelper.toFragment(requireActivity().getSupportFragmentManager() ,
                        new BirdsFragment() ,
                        TAG);
            } else {
                //2:
                enable = true;
                setEnable();
            }

        });
        //pick image from gallery for bird profile image
        binding.editImage.setClickable(true);
        binding.editImage.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, getString(R.string.select_image)), Constants.PICK_IMAGE);
        });




    }// setButtons


    //get the values to store them as a bird
    private void storeBird() {
        bird.setGender(binding.gender.isChecked());
        bird.setColor(binding.birdColor.getText().toString());
        bird.setCost(ValuesHelper.toDouble(binding.birdCost.getText().toString()));
        bird.setWeight(ValuesHelper.toDouble(binding.birdWeight.getText().toString()));
        bird.setOffered(binding.offered.isChecked());
        bird.setDesc(binding.birdDesc.getText().toString());
        bird.setRingNo(binding.birdRing.getText().toString());
        bird.setStatus(getBirdStatus(binding.birdStatus.getSelectedItemPosition()));
        bird.setSpecies(speciesAdapter.getItem(binding.birdSpecies.getSelectedItemPosition()));
    }//storeBird


    //add the adapter of bird status to its Spinner
    private void addStatus() {
        statusAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.bird_status));
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.birdStatus.setAdapter(statusAdapter);
    }//addStatus

    //convert intStatus to StringStatus
    private String setBirdStatus(int status) {
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
    private int getBirdStatus(int statusPosition) {
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
        binding.birdBirthDate.setText(date);
        bird.setBthDate(ValuesHelper.getDate(date, getContext()));
        ValuesHelper.getAge(getToday(), date);
    }

    /**
     * Show DatePickerDialogFragment
     */
    private void showDatePickerDialog(View view) {
        DialogFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.setTargetFragment(BirdProfileFragment.this ,  Constants.PICKER_REQUEST);
        datePickerFragment.show(requireActivity().getSupportFragmentManager(), Constants.DATE_PICKER);
    }

}