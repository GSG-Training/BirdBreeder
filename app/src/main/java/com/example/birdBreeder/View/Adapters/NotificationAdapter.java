package com.example.birdBreeder.View.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.birdBreeder.Model.DataBase.Entity.Notification;
import com.example.birdBreeder.R;
import com.example.birdBreeder.databinding.NotificationItemBinding;

import java.util.ArrayList;
import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {
    List<Notification> notifications = new ArrayList<>();
    OnItemClickListener listener;


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item, parent, false);
        return new MyViewHolder(itemView);
    }// end onCreateViewHolder(..)

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Notification notification = notifications.get(position);
        holder.binding.notificationTitle.setText(notification.getDescription());
    }//end onBindViewHolder(..)

    @Override
    public int getItemCount() {
        return notifications.size();
    }//end getItemCount()

    public void setItems(List<Notification> notifications) {
        this.notifications = notifications;
        notifyDataSetChanged();
    }

    public Notification getItemAt(int position) {
        return notifications.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }// end setOnItemClickListener

    public interface OnItemClickListener {
        void onItemClick(Notification notification);
    }//end OnItemClickListener

    //MyViewHolder Class
    public class MyViewHolder extends RecyclerView.ViewHolder {
        NotificationItemBinding binding;

        //main constructor
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = NotificationItemBinding.bind(itemView);


        }//end MyViewHolder(itemView)

    }//end MyViewHolder class
}//end  Class