package com.example.birdbreeder.View.ui.Birds;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.birdbreeder.Model.Constants;
import com.example.birdbreeder.Model.DataBase.Entity.Bird;
import com.example.birdbreeder.ViewModel.BirdViewModel;
import com.example.birdbreeder.databinding.ActivityBirdProfileBinding;
import com.example.birdbreeder.databinding.FragmentMatingProfileBinding;

import java.util.List;


public class MatingProfileFragment extends Fragment {
    public static final String TAG= "com.example.birdbreeder.View.ui.Birds.MatingProfileFragment" ;
    private FragmentMatingProfileBinding binding ;
    private int action , id ;


    public MatingProfileFragment() {
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
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMatingProfileBinding.inflate(inflater , container , false);

        return binding.getRoot();
    }
}