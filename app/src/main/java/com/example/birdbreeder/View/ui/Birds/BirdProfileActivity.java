package com.example.birdbreeder.View.ui.Birds;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.birdbreeder.Model.BirdBreederConstants;
import com.example.birdbreeder.Model.DataBase.Entity.Bird;
import com.example.birdbreeder.R;
import com.example.birdbreeder.View.Adapters.Fitter;
import com.example.birdbreeder.View.Pickers.DatePickerFragment;
import com.example.birdbreeder.ViewModel.BirdViewModel;
import com.example.birdbreeder.ViewModel.SpeciesViewModel;
import com.example.birdbreeder.databinding.ActivityBirdProfileBinding;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BirdProfileActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private BirdViewModel birdViewModel;
    private ActivityBirdProfileBinding binding;
    private Observer<Bird> observer;
    private Observer<List<String>> listObserver;
    private SpeciesViewModel speciesViewModel;
    private ArrayAdapter<String> speciesAdapter;
    private ArrayAdapter<String> statusAdapter;
    private boolean enable;
    private int action;
    private Bird bird ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBirdProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        bird = new Bird();
        birdViewModel = new BirdViewModel(getApplication());
        speciesViewModel = new SpeciesViewModel(getApplication());
        setObserver();
        setButtons();
        addStatus();
        speciesViewModel.getNamesOfSpecies().observeForever(listObserver);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //result of pick image from gallery for bird profile image
        if (resultCode == RESULT_OK && requestCode == BirdBreederConstants.PICK_IMAGE) {
            Bitmap photo = null;
            if (data != null) {
                try {
                    photo = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
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
        Intent intent = getIntent();
        action = intent.getIntExtra(BirdBreederConstants.BIRD_ACTION, -1);
        if (action == BirdBreederConstants.NEW_BIRD) {
            enable = true;
            setEnable();
        } else {
            enable = false;
            setEnable();
            int id = intent.getIntExtra(BirdBreederConstants.BIRD_ID, -1);
            if (id != -1)
                birdViewModel.getBird(id).observeForever(observer);
        }

    }//setLayout

    //Activate Observers
    private void setObserver() {
        observer = new Observer<Bird>() {
            @Override
            public void onChanged(Bird birdObserved) {
                if (birdObserved != null) {
                    bird = birdObserved ;
                    bind(birdObserved);
                }
            }
        };

        listObserver = new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                setSpecies(strings);
                setLayout();
            }
        };
    }//setObserver

    //getAge of a bird
    private String getToday() {
            Date today = Calendar.getInstance().getTime();
            return Fitter.getDate(today , this);
    }//getToday


    //Show the Bird at the views
    private void bind(Bird bird) {
        binding.birdId.setText(bird.getBirdId() + "");
        binding.birdRing.setText(bird.getRingNo());
        binding.birdBirthDate.setText(Fitter.getDate(bird.getBthDate() , this));
        binding.birdAge.setText(Fitter.getAge(getToday() , Fitter.getDate(bird.getBthDate() , this )));
        binding.birdDesc.setText(bird.getDesc());
        binding.birdColor.setText(bird.getColor());
        binding.offered.setChecked(bird.isOffered());
        binding.birdCost.setText(bird.getCost() + "");
        if (bird.getProfileImage() != null)
            binding.birdImage.setImageBitmap(bird.getProfileImage());
        binding.birdSpecies.setSelection(speciesAdapter.getPosition(bird.getSpecies()));
        binding.birdStatus.setSelection(statusAdapter.getPosition(setBirdStatus(bird.getStatus())));
        binding.birdWeight.setText(bird.getWeight() + "");
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
        speciesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, species);
        speciesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.birdSpecies.setAdapter(speciesAdapter);
    }//setSpecies

    //Activate Buttons in Layout
    private void setButtons() {
        //Offer Changes the visibility of birdCost EditText
        binding.offered.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) binding.birdCost.setVisibility(View.VISIBLE);
                else binding.birdCost.setVisibility(View.GONE);
            }
        });

        //profileButton has 2 states
        // 1 : Save the added or updated Bird
        // 2 : Allow Edit an exist Bird
        binding.profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(enable) {
                    //1:
                     storeBird();
                    if (action == BirdBreederConstants.SHOW_BIRD) {
                        bird.setBirdId(Fitter.toInteger(binding.birdId.getText().toString()));
                        birdViewModel.updateBird(bird);
                        Toast.makeText(BirdProfileActivity.this, getText(R.string.update_note), Toast.LENGTH_SHORT).show();
                    } else {
                        birdViewModel.addBird(bird);
                        Toast.makeText(BirdProfileActivity.this, getText(R.string.add_note), Toast.LENGTH_SHORT).show();
                    }
                    enable = false;
                    setEnable();
                } else {
                    //2:
                    enable = true;
                    setEnable();
                }
            }
        });
        //pick image from gallery for bird profile image
        binding.editImage.setClickable(true);
        binding.editImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, getString(R.string.select_image)), BirdBreederConstants.PICK_IMAGE);
            }
        });


        //Date Choose
        binding.birdBirthDate.setClickable(true);
        binding.birdBirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               showDatePickerDialog();
            }
        });

    }// setButtons


    //get the values to store them as a bird
    private void storeBird(){
        bird.setGender(binding.gender.isChecked());
        bird.setColor(binding.birdColor.getText().toString());
        bird.setCost(Fitter.toDouble(binding.birdCost.getText().toString()));
        bird.setWeight(Fitter.toDouble(binding.birdWeight.getText().toString()));
        bird.setOffered(binding.offered.isChecked());
        bird.setDesc(binding.birdDesc.getText().toString());
        bird.setRingNo(binding.birdRing.getText().toString());
        bird.setStatus(getBirdStatus(binding.birdStatus.getSelectedItemPosition()));
        bird.setSpecies(speciesAdapter.getItem(binding.birdSpecies.getSelectedItemPosition()));
    }//storeBird



    //add the adapter of bird status to its Spinner
    private void addStatus() {
        statusAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.bird_status));
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.birdStatus.setAdapter(statusAdapter);
    }//addStatus

    //convert intStatus to StringStatus
    private String setBirdStatus(int status) {
        switch (status) {
            case BirdBreederConstants.SICK:
                return statusAdapter.getItem(1);

            case BirdBreederConstants.SOLD:
                return statusAdapter.getItem(2);

            case BirdBreederConstants.IN_REST:
                return statusAdapter.getItem(3);

            case BirdBreederConstants.IN_BREEDING:
                return statusAdapter.getItem(4);

        }

        return statusAdapter.getItem(3);
    }//setStatus

    //convert StringStatus to intStatus
    private int getBirdStatus(int statusPosition) {
        switch (statusPosition) {
            case 1:
                return BirdBreederConstants.SICK;
            case 2:
                return BirdBreederConstants.SOLD;
            case 3:
                return BirdBreederConstants.IN_REST;
            case 4:
                return BirdBreederConstants.IN_BREEDING;
        }
        return BirdBreederConstants.IN_REST;

     }//getStatus

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        String date = Fitter.setDateString(year , monthOfYear , dayOfMonth) ;
        binding.birdBirthDate.setText(date);
        bird.setBthDate(Fitter.getDate(date, this));
        Fitter.getAge( getToday() , date );
    }

    /**
     * Show DatePickerDialogFragment
     */
    private void showDatePickerDialog() {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), BirdBreederConstants.DATE_PICKER);
    }

 }// Activity