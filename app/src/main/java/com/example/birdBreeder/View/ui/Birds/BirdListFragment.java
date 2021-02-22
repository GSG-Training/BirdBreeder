package com.example.birdBreeder.View.ui.Birds;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.birdBreeder.Model.Constants;
import com.example.birdBreeder.Model.DataBase.Entity.Bird;
import com.example.birdBreeder.R;
import com.example.birdBreeder.View.Adapters.BirdsAdapter;
import com.example.birdBreeder.View.ui.Activites.Helpers.BrowserHelper;
import com.example.birdBreeder.ViewModel.BirdViewModel;
import com.example.birdBreeder.databinding.FragmentBirdListBinding;

import java.util.List;

public class BirdListFragment extends Fragment implements BirdsAdapter.OnBirdClickListener  , BirdsAdapter.OnDeleteClickListener {
    public static final String TAG = "com.example.birdBreeder.View.ui.Birds.BirdListFragment";
    private BirdsAdapter adapter;
    private BirdViewModel viewModel;
    private Observer<List<Bird>> observer;

    private BirdListFragment() {
    }

    public static BirdListFragment newInstance() {
        
        Bundle args = new Bundle();
        
        BirdListFragment fragment = new BirdListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new BirdViewModel(requireActivity().getApplication());
        observer = birds -> adapter.setItems(birds);
        adapter = new BirdsAdapter(Constants.FOR_SHOW, requireActivity().getApplication());
        adapter.setOnBirdClickListener(this);
        adapter.setOnDeleteClick(this);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       FragmentBirdListBinding birdListBinding = FragmentBirdListBinding.inflate(inflater, container, false);
        viewModel.getAllBirds().observe(getViewLifecycleOwner(), observer);
        birdListBinding.newBird.setOnClickListener(view -> BrowserHelper.toBirdProfile(requireActivity().getSupportFragmentManager(), TAG, Constants.NEW_BIRD, -1));
        birdListBinding.myBirds.setAdapter(adapter);
        return birdListBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.getAllBirds().observe(getViewLifecycleOwner(), observer);
    }


    @Override
    public void itemClick(int position) {
        Bird bird = adapter.getItemAt(position);
        BrowserHelper.toBirdProfile(requireActivity().getSupportFragmentManager(), TAG, Constants.SHOW_BIRD, bird.getBirdId());
    }

    @Override
    public void onDeleteClick(int position) {
        Bird bird = adapter.getItemAt(position);
        new AlertDialog.Builder(requireContext())
                .setTitle(R.string.delete_item)
                .setMessage(R.string.delete_message)
                .setIcon(R.drawable.ic_delete_outlined)
                .setPositiveButton(android.R.string.yes, dialogInterface(bird))
                .setNegativeButton(android.R.string.no, dialogInterface(bird)).show();

    }


    private  DialogInterface.OnClickListener dialogInterface(Bird bird){
        return (dialog, which) -> {
            switch (which){
                case Dialog.BUTTON_NEGATIVE :
                    dialog.dismiss();
                    break;
                case Dialog.BUTTON_POSITIVE :
                    viewModel.deleteBird(bird);
                    dialog.dismiss();
                    break;
            }
        };

    }
}