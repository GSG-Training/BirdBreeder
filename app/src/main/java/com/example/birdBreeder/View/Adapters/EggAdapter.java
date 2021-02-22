package com.example.birdBreeder.View.Adapters;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.birdBreeder.Model.DataBase.Entity.Egg;
import com.example.birdBreeder.R;
import com.example.birdBreeder.databinding.EggItemBinding;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EggAdapter extends RecyclerView.Adapter<EggAdapter.MyViewHolder> {
    private List<Egg> eggs = new ArrayList<>();
     private OnEggClickListener listener  ;
     private OnDeleteClickListener onDeleteClickListener ;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.egg_item, parent, false);
        return new MyViewHolder(itemView);
    }// end onCreateViewHolder(..)

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Egg egg = eggs.get(position);
        holder.binding.eggId.setText(String.format("%s", egg.getEggId()));
        holder.binding.laidDate.setText(getDate(egg.getLayDate()));
        holder.binding.expectDate.setText(getDate(egg.getExpectedHatchingDate()));

        holder.binding.eggStatus.setImageLevel(egg.getStatus());
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

    public CharSequence getDate(Date date) {
        if(date!= null)
        return DateFormat.format("dd/MM/yyyy", date ) ;

        return "" ;
    }

    public void setOnEggClickListener(OnEggClickListener listener) {
        this.listener = listener;
    }

    public void setOnDeleteClickListener(OnDeleteClickListener onDeleteClickListener) {
        this.onDeleteClickListener = onDeleteClickListener;
    }

    //MyViewHolder Class
    public  class MyViewHolder extends RecyclerView.ViewHolder {

         EggItemBinding binding ;


        //main constructor
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //attaching java views to XML
         binding = EggItemBinding.bind(itemView);
            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition() ;
                if (position != RecyclerView.NO_POSITION)
                    listener.onEggClick(position);

            });

            binding.deleteEgg.setOnClickListener(v -> {
                int position = getAdapterPosition() ;
                if (position != RecyclerView.NO_POSITION)
                    onDeleteClickListener.onDeleteClick(position);

            });
        }//end MyViewHolder(itemView)

    }//end MyViewHolder class

  public interface OnEggClickListener{
        void onEggClick(int position);
  }

    public interface OnDeleteClickListener{
        void onDeleteClick(int position);
    }

}//end  Class