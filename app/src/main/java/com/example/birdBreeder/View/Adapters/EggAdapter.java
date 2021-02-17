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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EggAdapter extends RecyclerView.Adapter<EggAdapter.MyViewHolder> {
    private List<Egg> eggs = new ArrayList<>();
     OnEggClickListener listener ;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.egg_item, parent, false);
        return new MyViewHolder(itemView);
    }// end onCreateViewHolder(..)

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Egg egg = eggs.get(position);
        holder.eggId.setText(String.format("%s", egg.getEggId()));
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

    public CharSequence getDate(Date date) {
        if(date!= null)
        return DateFormat.format("dd/MM/yyyy", date ) ;

        return "" ;
    }

    public void setListener(OnEggClickListener listener) {
        this.listener = listener;
    }

    //MyViewHolder Class
    public  class MyViewHolder extends RecyclerView.ViewHolder {

        TextView eggId, hatchingDate;
        ImageView delete, status;
        EditText layDate, incubationDate;


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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition() ;
                    if (position != RecyclerView.NO_POSITION)
                        listener.onEggClick(position);

                }
            });
        }//end MyViewHolder(itemView)

    }//end MyViewHolder class

  public interface OnEggClickListener{
        void onEggClick(int position);
  }

}//end  Class