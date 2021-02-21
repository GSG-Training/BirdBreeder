package com.example.birdBreeder.View.Adapters;

import android.app.Application;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.birdBreeder.Model.Constants;
import com.example.birdBreeder.Model.DataBase.Entity.Bird;
import com.example.birdBreeder.R;
import com.example.birdBreeder.ViewModel.BirdViewModel;
import com.example.birdBreeder.databinding.BirdListItemBinding;

import java.util.ArrayList;
import java.util.List;

public class BirdsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final boolean forSale;
    private final BirdViewModel birdViewModel;
    private List<Bird> birdList = new ArrayList<>();
    private OnBirdClickListener onBirdClickListener;
    private OnDeleteClickListener onDeleteClick;
    private int width ;


    public BirdsAdapter(boolean forSale, Application application) {
        this.forSale = forSale;
        birdViewModel = new BirdViewModel(application);
        width = application.getApplicationContext(). getResources().getDisplayMetrics().widthPixels ;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (forSale == Constants.FOR_SALE) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sale_bird_item, parent, false);
            return new ForSaleViewHolder(itemView);
        } else {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.bird_list_item, parent, false);
            return new ForShowViewHolder(itemView);
        }
    }// end onCreateViewHolder(..)

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Bird bird = birdList.get(position);
        if (forSale == Constants.FOR_SALE) {
            ForSaleViewHolder viewHolder = (ForSaleViewHolder) holder;
            if (bird.getProfileImage() != null) {
                Bitmap birdImage = bird.getProfileImage();
                viewHolder.birdImage.setImageBitmap(birdImage);
            }
            viewHolder.cost.setText(String.format("%s", bird.getCost()));
            viewHolder.species.setText(bird.getSpecies());
        } else {
            ForShowViewHolder viewHolder = (ForShowViewHolder) holder;
            if (bird.getProfileImage() != null)
                viewHolder.birdImage.setImageBitmap(bird.getProfileImage());
            viewHolder.birdId.setText(bird.getRingNo());
            viewHolder.species.setText(bird.getSpecies());
            if (bird.getGender()) {
                viewHolder.gender.setImageLevel(110);
            } else {
                viewHolder.gender.setImageLevel(111);
            }


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

    public void setOnBirdClickListener(OnBirdClickListener onBirdClickListener) {
        this.onBirdClickListener = onBirdClickListener;
    }

    public void setOnDeleteClick(OnDeleteClickListener onDeleteClick) {
        this.onDeleteClick = onDeleteClick;
    }

    public interface OnBirdClickListener {
        void itemClick(int position);
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(int position);
    }

    //MyViewHolder Class
    public class ForShowViewHolder extends RecyclerView.ViewHolder {
        // views declaration
        CardView cardView ;
        ImageView birdImage, gender;
        TextView species, birdId;
        ImageView delete;

        //main constructor
        public ForShowViewHolder(@NonNull View itemView) {
            super(itemView);
            //attaching java views to XML
            cardView =(CardView)itemView.getRootView() ;
            cardView.setMinimumWidth((int)(width*0.5));
            birdId = itemView.findViewById(R.id.br_id);
            species = itemView.findViewById(R.id.br_species);
            birdImage = itemView.findViewById(R.id.br_profile_image);
            gender = itemView.findViewById(R.id.br_gender);
            delete = itemView.findViewById(R.id.delete_bird);

            delete.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION)
                    onDeleteClick.onDeleteClick(position);
            });
            itemView.setClickable(true);
            //attaching the listener
            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION)
                    onBirdClickListener.itemClick(position);
            });
        }//end MyViewHolder(itemView)

    }//end MyViewHolder class

    //MyViewHolder Class
    public class ForSaleViewHolder extends RecyclerView.ViewHolder {
        // views declaration
        ImageView birdImage, emailBreeder;
        TextView species, cost;

        //main constructor
        public ForSaleViewHolder(@NonNull View itemView) {
            super(itemView);
            //attaching java views to XML
            cost = itemView.findViewById(R.id.bird_cost);
            species = itemView.findViewById(R.id.bird_breed);
            birdImage = itemView.findViewById(R.id.bird_image);
            emailBreeder = itemView.findViewById(R.id.email_breeder);
            itemView.setClickable(true);
            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION)
                    onBirdClickListener.itemClick(position);
            });

        }//end MyViewHolder(itemView)

    }//end MyViewHolder class

}//end  Class