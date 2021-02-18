package com.example.birdBreeder.View.Adapters;

import android.app.Application;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.birdBreeder.Model.Constants;
import com.example.birdBreeder.Model.DataBase.Entity.Bird;
import com.example.birdBreeder.R;
import com.example.birdBreeder.ViewModel.BirdViewModel;

import java.util.ArrayList;
import java.util.List;

public class BirdsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final boolean forSale;
    private final BirdViewModel birdViewModel;
    private List<Bird> birdList = new ArrayList<>();
    private OnBirdClickListener onBirdClickListener;

    public BirdsAdapter(boolean forSale, Application application) {
        this.forSale = forSale;
        birdViewModel = new BirdViewModel(application);
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
                //TODO : RESIZE IMAGE TO FIT
                Bitmap birdImage = bird.getProfileImage();
//              Bitmap resized = Bitmap.createScaledBitmap(birdImage,(int)(birdImage.getWidth()*0.8), (int)(birdImage.getHeight()*0.8), true);
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

    public void setOnBirdClickListener(OnBirdClickListener onBirdClickListener) {
        this.onBirdClickListener = onBirdClickListener;
    }

    public interface OnBirdClickListener {
        void itemClick(int position);
    }

    //MyViewHolder Class
    public class ForShowViewHolder extends RecyclerView.ViewHolder {
        // views declaration
        ImageView birdImage, gender;
        TextView species, birdId;
        ToggleButton offered;

        //main constructor
        public ForShowViewHolder(@NonNull View itemView) {
            super(itemView);
            //attaching java views to XML
            birdId = itemView.findViewById(R.id.br_id);
            species = itemView.findViewById(R.id.br_species);
            birdImage = itemView.findViewById(R.id.br_profile_image);
            gender = itemView.findViewById(R.id.br_gender);
            offered = itemView.findViewById(R.id.br_offered);

            offered.setOnCheckedChangeListener((compoundButton, b) -> {
                Bird bird = birdList.get(getAdapterPosition());
                bird.setOffered(offered.isChecked());
                birdViewModel.updateBird(bird);
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