package com.example.birdBreeder.View.ui.Birds;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.birdBreeder.Model.Constants;
import com.example.birdBreeder.Model.DataBase.Entity.Egg;
import com.example.birdBreeder.Model.DataBase.Entity.Mating;
import com.example.birdBreeder.View.Adapters.EggAdapter;
import com.example.birdBreeder.View.ui.Activites.Helpers.BrowserHelper;
import com.example.birdBreeder.ViewModel.EggViewModel;
import com.example.birdBreeder.ViewModel.MatingViewModel;
import com.example.birdBreeder.databinding.FragmentMatingControlBinding;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MatingControlFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MatingControlFragment extends Fragment implements EggAdapter.OnEggClickListener {
    public static final String TAG = "com.example.birdBreeder.View.ui.Birds.MatingControlFragment";
    private int id ;
    private FragmentMatingControlBinding binding ;
    private Observer<Mating> matingObserver ;
    private MatingViewModel matingViewModel ;
    private EggViewModel eggViewModel ;
    private Observer<List<Egg>> listObserver ;
    private Mating mating ;
    private EggAdapter eggAdapter ;


    private MatingControlFragment() {
        // Required empty public constructor
    }


    public static MatingControlFragment newInstance(int id) {
        MatingControlFragment fragment = new MatingControlFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.MATING_ID, id);
        fragment.setArguments(args);
        return fragment;

    }//newInstance

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getInt(Constants.MATING_ID);
        }
        matingViewModel = new MatingViewModel(requireActivity().getApplication());
        eggViewModel = new EggViewModel(requireActivity().getApplication());
        eggAdapter = new EggAdapter();
    }//onCreate

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMatingControlBinding.inflate(inflater , container , false);
        setButtons();
        setObservers();
        matingViewModel.getMating(id).observe(getViewLifecycleOwner() , matingObserver);
        return binding.getRoot();
    }//onCreateView


    //ClickListeners attaching
    private void setButtons(){
        binding.matingCard.matingEditDetails.setOnClickListener(this::edit);
        binding.newEggFb.setOnClickListener(this::newEgg);
    }//setButtons


    //Init Observers
    private void setObservers(){
        matingObserver = observedMating -> {
            if(observedMating!=null){
                mating =  observedMating ;
                setMating();
                eggViewModel.getAllEggs(id).observe(getViewLifecycleOwner() , listObserver);
            }
        };

        listObserver = eggs ->{ if(eggs!=null)eggAdapter.setItems(eggs); };
    }//setObservers


    //Shoe Mating info at the card
    private void setMating(){
        binding.matingCard.mateId.setText(String.format("%s", mating.getMatingId()));
        binding.matingCard.maleId.setText(String.format("%s",mating.getMaleId()));
        binding.matingCard.femaleId.setText(String.format("%s",mating.getFemaleId()));
        binding.matingCard.mateBreedTxt.setText(mating.getSpecies());
        binding.matingCard.totalEggsNo.setText(String.format("%s",mating.getTotalEggsNum()));
        binding.matingCard.producedChicksNo.setText(String.format("%s",mating.getHatchedEggsNum()));
        binding.matingCard.currentEggs.setText(String.format("%s",mating.getIncubatedEggsNum()));
    }// setMating

    //edit button at the mating card clickFunction
    private void edit(View view){
        BrowserHelper.toMatingProfile(requireActivity().getSupportFragmentManager() ,
                TAG , Constants.EDIT_MATING , id );
    }

    //edit button at the mating card clickFunction
    private void newEgg(View view){
        BrowserHelper.toEggProfile(requireActivity().getSupportFragmentManager() ,
                TAG , Constants.EDIT_MATING , -1 );
    }

    @Override
    public void onEggClick(int position) {
        Egg egg = eggAdapter.getItemAt(position);
       BrowserHelper.toEggProfile(requireActivity().getSupportFragmentManager() ,
               TAG , Constants.EDIT_EGG , egg.getEggId());
    }

    @Override
    public void onResume() {
        super.onResume();
        matingViewModel.getMating(id).observe(getViewLifecycleOwner() , matingObserver);
    }
}