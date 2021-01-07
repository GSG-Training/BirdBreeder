package com.example.birdbreeder.View.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.birdbreeder.Model.DataBase.Entity.Notification;
import com.example.birdbreeder.R;

import java.util.ArrayList;
import java.util.List;

public class NotificationAdapter  extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {
    List<Notification> notifications = new ArrayList<>();
    OnItemClickListener listener;


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.bird_list_item, parent, false);
        return new MyViewHolder(itemView);
    }// end onCreateViewHolder(..)

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }//end onBindViewHolder(..)

    @Override
    public int getItemCount() {
        return notifications.size();
    }//end getItemCount()

    public void setItems(List<Notification> notifications) {
        this.notifications = notifications;
        notifyDataSetChanged();
    }//set Posts

    public Notification getItemAt(int position) {
        return notifications.get(position);
    }

    //MyViewHolder Class
    public class MyViewHolder extends RecyclerView.ViewHolder {
        //post_item views declaration
        TextView postTitle, userId, postText;

        //main constructor
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //attaching java views to XML

            //attaching the listener
            //end onClick
            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(notifications.get(position));

                }//end if
            });
        }//end MyViewHolder(itemView)

    }//end MyViewHolder class

    public interface OnItemClickListener {
        void onItemClick(Notification notification);
    }//end OnItemClickListener

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }// end setOnItemClickListener
}//end  Class