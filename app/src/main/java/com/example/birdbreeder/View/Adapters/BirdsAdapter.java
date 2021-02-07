package com.example.birdbreeder.View.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.birdbreeder.Model.DataBase.Entity.Bird;
import com.example.birdbreeder.R;

import java.util.ArrayList;
import java.util.List;

public class BirdsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Bird> birdList = new ArrayList<>();
    private OnItemClickListener listener;
    private  boolean forSale ;
    public BirdsAdapter( boolean forSale) {
        this.forSale = forSale;
    }
    public BirdsAdapter(List<Bird> birdList, boolean forSale) {
        this.birdList = birdList;
        this.forSale = forSale;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (forSale){
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sale_bird_item, parent, false);
            return new ForSaleViewHolder(itemView);
        }else {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.bird_list_item, parent, false);
            return new ForShowViewHolder(itemView);
        }
    }// end onCreateViewHolder(..)

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Bird bird = birdList.get(position);
      if(forSale){
          ForSaleViewHolder viewHolder = (ForSaleViewHolder) holder ;
          //viewHolder.birdImage.setImageBitmap(bird.getProfileImage());
          viewHolder.cost.setText("$" + bird.getCost());
          viewHolder.species.setText(bird.getSpecies());
      }else{
          ForShowViewHolder viewHolder = (ForShowViewHolder) holder ;
         // viewHolder.birdImage.setImageBitmap(bird.getProfileImage());
          viewHolder.birdId.setText(bird.getBirdId());
          viewHolder.species.setText(bird.getSpecies());
          viewHolder.gender.setImageLevel(bird.getGender());
          viewHolder.offered.setChecked(bird.isOffered());

      }

    }//end onBindViewHolder(..)

    @Override
    public int getItemCount() {
        return birdList.size();
    }//end getItemCount()

    public void setItems(List<Bird> birds) {
        this.birdList = birds;
        notifyDataSetChanged();
    }//set Posts

    public Bird getItemAt(int position) {
        return birdList.get(position);
    }

    //MyViewHolder Class
    public class ForShowViewHolder extends RecyclerView.ViewHolder {
        // views declaration
         ImageView birdImage , gender ;
         TextView species , birdId ;
         ToggleButton offered ;
        //main constructor
        public ForShowViewHolder(@NonNull View itemView) {
            super(itemView);
            //attaching java views to XML
           birdId = itemView.findViewById(R.id.br_id);
           species = itemView.findViewById(R.id.br_species);
           birdImage = itemView.findViewById(R.id.br_profile_image);
           gender = itemView.findViewById(R.id.br_gender);
           offered= itemView.findViewById(R.id.br_offered);
            // TODO:offered OnClick
            offered.setOnCheckedChangeListener((compoundButton, b) -> {

            });
            //attaching the listener
            // TODO:ItemOnClick
            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(birdList.get(position));

                }//end if
            });
        }//end MyViewHolder(itemView)

    }//end MyViewHolder class

    //MyViewHolder Class
    public class ForSaleViewHolder extends RecyclerView.ViewHolder {
        // views declaration
        ImageView birdImage , emailBreeder;
        TextView species , cost ;

        //main constructor
        public ForSaleViewHolder(@NonNull View itemView) {
            super(itemView);
            //attaching java views to XML
            cost = itemView.findViewById(R.id.bird_cost);
            species = itemView.findViewById(R.id.bird_breed);
            birdImage = itemView.findViewById(R.id.bird_image);
            emailBreeder = itemView.findViewById(R.id.email_breeder);
            // TODO:email_breeder OnClick
            //attaching the listener
            // TODO:ItemOnClick
            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(birdList.get(position));

                }//end if
            });
        }//end MyViewHolder(itemView)

    }//end MyViewHolder class


    public interface OnItemClickListener {
        void onItemClick(Bird bird);
    }//end OnItemClickListener


}//end  Class