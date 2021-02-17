package com.example.birdBreeder.View.Adapters;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.birdBreeder.Model.DataBase.Entity.Mating;
import com.example.birdBreeder.R;
import com.example.birdBreeder.databinding.MatingItemBinding;

import java.util.ArrayList;
import java.util.List;

public class MatingAdapter extends RecyclerView.Adapter<MatingAdapter.MyViewHolder> {
    private List<Mating> matings = new ArrayList<>();
     private OnMatingClickListener listener;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.mating_item, parent, false);
        return new MyViewHolder(itemView);
    }// end onCreateViewHolder(..)

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Mating mating = matings.get(position);
        holder.binding.mateId.setText(String.format("%s", mating.getMatingId()));
        holder.binding.maleId.setText(String.format("%s", mating.getMaleId()));
        holder.binding.mateBreedTxt.setText(mating.getSpecies());
        holder.binding.femaleId.setText(String.format("%s", mating.getFemaleId()));
        if(mating.getFormationDate()!=null)holder.binding.mateDateTxt.setText(DateFormat.format("dd/MM/yyyy", mating.getFormationDate()));
        holder.binding.totalEggsNo.setText(String.format("%s", mating.getTotalEggsNum()));
        holder.binding.currentEggs.setText(String.format("%s", mating.getIncubatedEggsNum()));
        holder.binding.producedChicksNo.setText(String.format("%s", mating.getHatchedEggsNum()));


    }//end onBindViewHolder(..)

    @Override
    public int getItemCount() {
        return matings.size();
    }//end getItemCount()

    public void setItems(List<Mating> matings) {
        this.matings = matings;
        notifyDataSetChanged();
    }//set Items

    public Mating getItemAt(int position) {
        return matings.get(position);
    }

    public void setOnItemClickListener(OnMatingClickListener listener) {
        this.listener = listener;
    }// end setOnItemClickListener


    public interface OnMatingClickListener {
        void onMatingClick(int position);
        void onEditClick(int position);
    }

    //MyViewHolder Class
    public class MyViewHolder extends RecyclerView.ViewHolder {
        MatingItemBinding binding ;

        //main constructor
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //attaching java views to XML
            binding = MatingItemBinding.bind(itemView);
            binding.matingEditDetails.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION)
                listener.onEditClick(position);
            });
            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION)
                listener.onMatingClick(position);
            });
        }//end MyViewHolder(itemView)


    }//end MyViewHolder class

}//end  Class