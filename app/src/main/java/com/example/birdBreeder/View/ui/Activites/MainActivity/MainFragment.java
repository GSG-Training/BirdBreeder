package com.example.birdBreeder.View.ui.Activites.MainActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.birdBreeder.R;
import com.example.birdBreeder.View.Adapters.TabsLayoutAdapter;
import com.example.birdBreeder.View.ui.Birds.BirdsFragment;
import com.example.birdBreeder.databinding.FragmentMainBinding;

public class MainFragment extends Fragment {
    private FragmentMainBinding binding;
    private TabsLayoutAdapter tabAdapter;

    public MainFragment() {
        // Required empty public constructor
    }


    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        tabAdapter = new TabsLayoutAdapter(getChildFragmentManager());
        tabAdapter.addFragment(new HomeFragment(), getString(R.string.home_fragment_title), getDrawable(R.drawable.ic_baseline_home_24));
        tabAdapter.addFragment(new ProfileFragment(), getString(R.string.profile_fragment_title), getDrawable(R.drawable.ic_baseline_person_24));
        tabAdapter.addFragment(new BirdsFragment(), getString(R.string.bird_fragment_title), getDrawable(R.drawable.ic__bird));
        tabAdapter.addFragment(new NotificationFragment(), getString(R.string.notification_fragment_title), getDrawable(R.drawable.ic_baseline_notifications_active_24));
        binding.mainViewPager.setAdapter(tabAdapter);
        binding.mainTabs.setupWithViewPager(binding.mainViewPager);
        tabAdapter.setIcons(binding.mainTabs);
        return binding.getRoot();
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    private Drawable getDrawable(int id) {
        return requireContext().getDrawable(id);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }
}