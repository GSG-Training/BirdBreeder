package com.example.birdbreeder.View.ui.Birds;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.birdbreeder.Model.BirdBreederConstants;
import com.example.birdbreeder.Model.DataBase.Entity.Bird;
import com.example.birdbreeder.View.Adapters.BirdsAdapter;
import com.example.birdbreeder.ViewModel.BirdViewModel;
import com.example.birdbreeder.databinding.FragmentBirdListBinding;

import java.util.List;

public class BirdListFragment extends Fragment {
  private FragmentBirdListBinding birdListBinding ;
  private BirdsAdapter adapter ;
  private BirdViewModel viewModel ;
  private Observer<List<Bird>> observer ;

    public BirdListFragment() {
    }


    public static BirdListFragment newInstance(String param1, String param2) {
        BirdListFragment fragment = new BirdListFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new BirdViewModel(getActivity().getApplication());
        observer = new Observer<List<Bird>>() {
            @Override
            public void onChanged(List<Bird> birds) {
                adapter.setItems(birds);
            }
        };
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        birdListBinding = FragmentBirdListBinding.inflate(inflater , container , false);
        setAdapter();
        viewModel.getAllBirds().observe(getViewLifecycleOwner(),observer);
        birdListBinding.newBirdFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toBirdProfile(-1);
            }
        });
        return birdListBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.getAllBirds().observe(getViewLifecycleOwner(),observer);
    }


    private void setAdapter(){
        adapter = new BirdsAdapter(false , getActivity().getApplication()) {
            @Override
            public void itemClick(int position) {
                int birdId = getItemAt(position).getBirdId();
                toBirdProfile(birdId);
            }
        };
        birdListBinding.birdListRecycler.setAdapter(adapter);
    }

    private void toBirdProfile(int id){
        Intent intent = new Intent(getContext() ,BirdProfileActivity.class );
        if(id == -1) {
            intent.putExtra(BirdBreederConstants.BIRD_ACTION , BirdBreederConstants.NEW_BIRD);
        }
        else {
            intent.putExtra(BirdBreederConstants.BIRD_ACTION , BirdBreederConstants.SHOW_BIRD);
            intent.putExtra(BirdBreederConstants.BIRD_ID , id);
        }
        startActivity(intent);

    }
}