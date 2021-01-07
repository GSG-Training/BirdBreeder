package com.example.birdbreeder.View.Adapters;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.birdbreeder.Model.DataBase.Entity.Mating;
import com.example.birdbreeder.R;

import java.util.ArrayList;
import java.util.List;

public class MatingAdapter  extends RecyclerView.Adapter<MatingAdapter.MyViewHolder> {
    List<Mating> matings = new ArrayList<>();


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.mating_item, parent, false);
        return new MyViewHolder(itemView);
    }// end onCreateViewHolder(..)

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Mating mating = matings.get(position);
        holder.mateId.setText(mating.getMatingId()+"");
        holder.species.setText(mating.getSpecies());
        holder.maleId.setText(mating.getMaleId());
        holder.femaleId.setText(mating.getFemaleId());
        holder.date.setText(DateFormat.format("yyyy-MM-dd",mating.getFormationDate()));
        holder.totalNum.setText(mating.getTotalEggsNum()+"");
        holder.incubatedNum.setText(mating.getIncubatedEggsNum()+"");
        holder.hatchedNum.setText(mating.getHatchedEggsNum()+"");


    }//end onBindViewHolder(..)

    @Override
    public int getItemCount() {
        return matings.size();
    }//end getItemCount()

    public void setItems(List<Mating> matings) {
        this.matings = matings;
        notifyDataSetChanged();
    }//set Posts

    public Mating getItemAt(int position) {
        return matings.get(position);
    }

    //MyViewHolder Class
    public class MyViewHolder extends RecyclerView.ViewHolder {
        //post_item views declaration
        TextView mateId, maleId, femaleId , hatchedNum , totalNum , incubatedNum , date , species;
        Button edit ;

        //main constructor
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //attaching java views to XML
           mateId = itemView.findViewById(R.id.mate_id);
           edit = itemView.findViewById(R.id.mating_edit_details);
           species= itemView.findViewById(R.id.mate_breed_txt);
            maleId= itemView.findViewById(R.id.male_id);
            femaleId= itemView.findViewById(R.id.female_id);
            hatchedNum= itemView.findViewById(R.id.produced_chicks_no);
            totalNum= itemView.findViewById(R.id.total_eggs_no);
            incubatedNum= itemView.findViewById(R.id.current_eggs);
            //todo:edit on click listener

        }//end MyViewHolder(itemView)

    }//end MyViewHolder class


}//end  Class