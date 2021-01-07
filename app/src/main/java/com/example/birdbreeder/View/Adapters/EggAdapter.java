package com.example.birdbreeder.View.Adapters;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.birdbreeder.Model.DataBase.Entity.Egg;
import com.example.birdbreeder.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EggAdapter extends RecyclerView.Adapter<EggAdapter.MyViewHolder> {
    List<Egg> eggs = new ArrayList<>();


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.egg_item, parent, false);
        return new MyViewHolder(itemView);
    }// end onCreateViewHolder(..)

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
      Egg egg = eggs.get(position);
      holder.eggId.setText(egg.getEggId()+"");
      holder.layDate.setText(getDate(egg.getLayDate()));
      holder.hatchingDate.setText(getDate(egg.getExpectedHatchingDate()));
      holder.incubationDate.setText(getDate(egg.getIncubationDate()));
      holder.status.setImageLevel(egg.getStatus());
    }//end onBindViewHolder(..)

    @Override
    public int getItemCount() {
        return eggs.size();
    }//end getItemCount()

    public void setItems(List<Egg> eggs) {
        this.eggs = eggs;
        notifyDataSetChanged();
    }//set Posts

    public Egg getItemAt(int position) {
        return eggs.get(position);
    }

    public String getDate(Date date){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      return  df.format(date);
    }

    //MyViewHolder Class
    public class MyViewHolder extends RecyclerView.ViewHolder {
        //post_item views declaration
        TextView eggId, hatchingDate;
        ImageView delete, status;
        EditText layDate , incubationDate ;


        //main constructor
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //attaching java views to XML
         eggId = itemView.findViewById(R.id.egg_id);
         hatchingDate = itemView.findViewById(R.id.expect_date);
         layDate = itemView.findViewById(R.id.laid_date);
         delete = itemView.findViewById(R.id.delete_egg);
         incubationDate = itemView.findViewById(R.id.start_date);
         status = itemView.findViewById(R.id.egg_status);
            //todo: add time pickers
            //todo : delete activation
            //todo : egg status picker

        }//end MyViewHolder(itemView)

    }//end MyViewHolder class


}//end  Class