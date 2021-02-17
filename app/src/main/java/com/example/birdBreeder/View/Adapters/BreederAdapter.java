package com.example.birdBreeder.View.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.birdBreeder.Model.DataBase.Entity.Breeder;
import com.example.birdBreeder.R;

import java.util.ArrayList;
import java.util.List;

public class BreederAdapter extends RecyclerView.Adapter<BreederAdapter.MyViewHolder> {
    List<Breeder> breeders = new ArrayList<>();
    OnItemClickListener listener;


    @NonNull
    @Override
    public BreederAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.breeder_item, parent, false);
        return new BreederAdapter.MyViewHolder(itemView);
    }// end onCreateViewHolder(..)

    @Override
    public void onBindViewHolder(@NonNull BreederAdapter.MyViewHolder holder, int position) {
        Breeder breeder = breeders.get(position);
        if (breeder.getPhone() != null) holder.profileImage.setImageBitmap(breeder.getPhoto());
        holder.userName.setText(breeder.getName());
        holder.soldBirdNum.setText(breeder.getSoldNum() + "");
        holder.speciesNum.setText(breeder.getSpeciesNum() + "");
        holder.description.setText(breeder.getDescription());
        if (breeder.isFav()) holder.fav.setImageLevel(1);
        // holder.
    }//end onBindViewHolder(..)

    @Override
    public int getItemCount() {
        return breeders.size();
    }//end getItemCount()

    public void setItems(List<Breeder> breeders) {
        this.breeders = breeders;
        notifyDataSetChanged();
    }//set Posts

    public Breeder getItemAt(int position) {
        return breeders.get(position);
    }

    public void setOnItemClickListener(BreederAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }// end setOnItemClickListener

    public interface OnItemClickListener {
        void onItemClick(Breeder breeder);
    }//end OnItemClickListener

    //MyViewHolder Class
    public class MyViewHolder extends RecyclerView.ViewHolder {
        //post_item views declaration
        TextView userName, description, speciesNum, soldBirdNum;
        ImageView profileImage, fav;

        //main constructor
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //attaching java views to XML
            userName = itemView.findViewById(R.id.br_user_name);
            description = itemView.findViewById(R.id.br_description);
            speciesNum = itemView.findViewById(R.id.br_own_species_no);
            soldBirdNum = itemView.findViewById(R.id.br_sale_birds_no);
            fav = itemView.findViewById(R.id.br_fav);
            profileImage = itemView.findViewById(R.id.br_user_profile_image);
            //TODO: oc fav clickListener
            //todo: on item clickListener

        }//end MyViewHolder(itemView)

    }//end MyViewHolder class
}//end  Class