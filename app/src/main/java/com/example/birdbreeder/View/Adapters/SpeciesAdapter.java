package com.example.birdbreeder.View.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.birdbreeder.Model.DataBase.Entity.Notification;
import com.example.birdbreeder.Model.DataBase.Entity.Species;
import com.example.birdbreeder.R;
import com.example.birdbreeder.databinding.NotificationItemBinding;
import com.example.birdbreeder.databinding.SpeciesItemBinding;

import java.util.ArrayList;
import java.util.List;

public class SpeciesAdapter extends RecyclerView.Adapter<SpeciesAdapter.MyViewHolder> {
    List<Species> species = new ArrayList<>();


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.species_item, parent, false);
        return new MyViewHolder(itemView);
    }// end onCreateViewHolder(..)

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
          Species spec = species.get(position);
          holder.binding.speciesName.setText(spec.getName());
    }//end onBindViewHolder(..)

    @Override
    public int getItemCount() {
        return species.size();
    }//end getItemCount()

    public void setItems(List<Species> species) {
        this.species = species;
        notifyDataSetChanged();
    }

    public Species getItemAt(int position) {
        return species.get(position);
    }

    //MyViewHolder Class
    public class MyViewHolder extends RecyclerView.ViewHolder {
         SpeciesItemBinding binding ;

        //main constructor
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = SpeciesItemBinding.bind(itemView);


        }//end MyViewHolder(itemView)

    }//end MyViewHolder class


}//end  Class