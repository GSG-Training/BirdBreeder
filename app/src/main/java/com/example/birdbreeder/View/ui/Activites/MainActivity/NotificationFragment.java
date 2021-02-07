package com.example.birdbreeder.View.ui.Activites.MainActivity;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import com.example.birdbreeder.Model.DataBase.Entity.Notification;
import com.example.birdbreeder.View.Adapters.NotificationAdapter;
import com.example.birdbreeder.View.ui.Dummy;
import com.example.birdbreeder.ViewModel.NotificationViewModel;
import com.example.birdbreeder.databinding.FragmentNotificationBinding;
import java.util.List;


public class NotificationFragment extends Fragment {
  private FragmentNotificationBinding binding ;
  private Observer<List<Notification>> observer ;
  private NotificationAdapter adapter ;
  private NotificationViewModel viewModel ;
    public NotificationFragment() {
    }


    public static NotificationFragment newInstance(String param1, String param2) {
        NotificationFragment fragment = new NotificationFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   Dummy.addNotifications(getActivity().getApplication());
        adapter = new NotificationAdapter();
        viewModel = new NotificationViewModel(getActivity().getApplication());
        observer = new Observer<List<Notification>>() {
           @Override
           public void onChanged(List<Notification> notifications) {
               adapter.setItems(notifications);
           }
       };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNotificationBinding.inflate(inflater , container , false);
        binding.notificationRecycler.setAdapter(adapter);

        viewModel.getAllNotifications().observe(getViewLifecycleOwner() , observer);

        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.getAllNotifications().observe(getViewLifecycleOwner() , observer);
    }
}