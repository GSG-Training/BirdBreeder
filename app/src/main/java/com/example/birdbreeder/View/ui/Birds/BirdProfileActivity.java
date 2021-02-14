package com.example.birdbreeder.View.ui.Birds;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.birdbreeder.Model.BirdBreederConstants;
import com.example.birdbreeder.Model.DataBase.Entity.Bird;
import com.example.birdbreeder.R;
import com.example.birdbreeder.View.ui.Dummy;
import com.example.birdbreeder.ViewModel.BirdViewModel;
import com.example.birdbreeder.ViewModel.SpeciesViewModel;
import com.example.birdbreeder.databinding.ActivityBirdProfileBinding;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BirdProfileActivity extends AppCompatActivity {
    private BirdViewModel birdViewModel ;
    private ActivityBirdProfileBinding binding ;
    private Observer<Bird> observer ;
    private Observer<List<String>> listObserver ;
    private SpeciesViewModel speciesViewModel ;
    private  ArrayAdapter<String> speciesAdapter ;
    private  ArrayAdapter<String> statusAdapter ;
    private boolean enable ;
    private int action ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBirdProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        birdViewModel = new BirdViewModel(getApplication());
        speciesViewModel = new SpeciesViewModel(getApplication());
        setObserver();
        setButtons();
        addStatus();
        setLayout();
        speciesViewModel.getNamesOfSpecies().observeForever(listObserver);


    }


    private void  setLayout(){
        Intent intent = getIntent();
        action = intent.getIntExtra(BirdBreederConstants.BIRD_ACTION , -1);
        if(action==BirdBreederConstants.NEW_BIRD){
            enable = true ;
            setEnable();
        }else{
            enable = false ;
            setEnable();
            int id=intent.getIntExtra(BirdBreederConstants.BIRD_ID , -1);
            if(id!= -1)
                birdViewModel.getBird(id).observeForever(observer);
        }

    }


    private void setObserver(){
        observer = new Observer<Bird>() {
            @Override
            public void onChanged(Bird bird) {
                if(bird!= null){
                   bind(bird);
                }
            }
        };

        listObserver = new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                setSpecies(strings);
            }
        };
    }

    private String getAge(Date bthDate) {
        if(bthDate!=null){
           Date time = Calendar.getInstance().getTime();
          Date age =  new Date(time.getTime() - bthDate.getTime() );
          return  getDate(age);
        }
        return "";
    }

    private String getDate(Date time) {
        if(time!=null){
            java.text.DateFormat dateFormat = DateFormat.getDateFormat(this);
            return dateFormat.format(time);
        }
        return "21/10/2020" ;


    }

    private Date getDate(String time) {
        Date date = new Date()  ;
        java.text.DateFormat dateFormat = DateFormat.getDateFormat(this);
        try {
            date= dateFormat.parse(time);
            return date ;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  date ;
    }

    private void bind(Bird bird){
        binding.birdId.setText(bird.getBirdId()+"");
        binding.birdRing.setText(bird.getRingNo());
        binding.birdBirthDate.setText(getDate(bird.getBthDate()));
        binding.birdAge.setText(getAge(bird.getBthDate()));
        binding.birdDesc.setText(bird.getDesc());
        binding.birdColor.setText(bird.getColor());
        binding.offered.setChecked(bird.isOffered());
        binding.birdCost.setText( bird.getCost()+"");
        binding.birdImage.setImageBitmap(bird.getProfileImage());
        binding.birdSpecies.setSelection(speciesAdapter.getPosition(bird.getSpecies()));
        binding.birdStatus.setSelection(statusAdapter.getPosition(setStatus(bird.getStatus())));
        binding.birdWeight.setText(bird.getWeight()+"");
        binding.gender.setChecked(bird.getGender());
    }



    private void setEnable(){
        binding.birdId.setEnabled(false);
        binding.birdRing.setEnabled(enable);
        binding.birdBirthDate.setEnabled(false);
        binding.birdAge.setEnabled(false);
        binding.birdDesc.setEnabled(enable);
        binding.birdColor.setEnabled(enable);
        binding.offered.setEnabled(enable);
        binding.birdCost.setEnabled(enable);
        binding.birdSpecies.setEnabled(enable);
        binding.birdWeight.setEnabled(enable);
        binding.gender.setEnabled(enable);
        binding.birdStatus.setEnabled(enable);
        if(enable){
            binding.label.setVisibility(View.GONE);
            binding.birdId.setVisibility(View.GONE);
            binding.profileButton.setText(R.string.save);
            binding.editImage.setVisibility(View.VISIBLE);
        }else{
            binding.label.setVisibility(View.VISIBLE);
            binding.birdId.setVisibility(View.VISIBLE);
            binding.profileButton.setText(R.string.edit);
            binding.editImage.setVisibility(View.GONE);
        }

    }

    private void  setSpecies(List<String> species){
         speciesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, species);
        speciesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.birdSpecies.setAdapter(speciesAdapter);
    }


    private void  setButtons(){
        binding.offered.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) binding.birdCost.setVisibility(View.VISIBLE);
                else  binding.birdCost.setVisibility(View.GONE);
            }
        });

        binding.profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(enable){
                    Bird bird = new Bird();
                    if(action==BirdBreederConstants.SHOW_BIRD)
                    bird.setBirdId(toInteger(binding.birdId.getText().toString()));
                    bird.setBthDate(getDate(binding.birdBirthDate.getText().toString()));
                    bird.setGender(binding.gender.isChecked());
                    bird.setColor(binding.birdColor.getText().toString());
                    bird.setCost(toDouble(binding.birdCost.getText().toString()));
                    bird.setWeight(toDouble(binding.birdWeight.getText().toString()));
                    bird.setOffered(binding.offered.isChecked());
                    bird.setProfileImage(binding.birdImage.getDrawingCache());
                    bird.setDesc(binding.birdDesc.getText().toString());
                    bird.setRingNo(binding.birdRing.getText().toString());
                    bird.setStatus(getStatus(binding.birdStatus.getSelectedItemPosition()));
                    bird.setSpecies(speciesAdapter.getItem(binding.birdSpecies.getSelectedItemPosition()));
                    birdViewModel.addBird(bird);
                    enable=false;
                    setEnable();
                    Toast.makeText(BirdProfileActivity.this, "New Bird Added", Toast.LENGTH_SHORT).show();
              }else {
                    enable=true;
                    setEnable();
                }
           }
       });
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
    }


    private int toInteger(String num){
        if(!num.isEmpty()){
            int integer = Integer.parseInt(num);
            return integer ;
        }
        return -1 ;
    }
    private double toDouble(String num){
        if(!num.isEmpty()){
            double integer = Double.parseDouble(num);
            return integer ;
        }
        return -1 ;
    }

    private void addStatus(){
        statusAdapter =  new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.bird_status));
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.birdStatus.setAdapter(statusAdapter);
    }
    private String setStatus(int status){
         switch (status){
             case BirdBreederConstants.SICK :
                 return statusAdapter.getItem(1);

             case BirdBreederConstants.SOLD :
                 return statusAdapter.getItem(2);

             case BirdBreederConstants.IN_REST :
                 return statusAdapter.getItem(3);

             case BirdBreederConstants.IN_BREEDING :
                 return statusAdapter.getItem(4);

         }

         return statusAdapter.getItem(3);
    }
    private int getStatus(int statusPosition){
        switch (statusPosition){
            case 1 :
                return  BirdBreederConstants.SICK ;
            case 2 :
                return  BirdBreederConstants.SOLD ;
            case 3 :
                return  BirdBreederConstants.IN_REST ;
            case 4 :
                return  BirdBreederConstants.IN_BREEDING ;
        }
        return  BirdBreederConstants.IN_REST ;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == BirdBreederConstants.PICK_IMAGE){
            //data.getData().
        }

    }
}