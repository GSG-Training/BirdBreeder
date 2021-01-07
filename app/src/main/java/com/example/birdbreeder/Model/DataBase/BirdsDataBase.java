package com.example.birdbreeder.Model.DataBase;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.birdbreeder.Model.BirdBreederConstants;
import com.example.birdbreeder.Model.DataBase.Entity.Bird;
import com.example.birdbreeder.Model.DataBase.Entity.Egg;
import com.example.birdbreeder.Model.DataBase.Entity.Mating;
import com.example.birdbreeder.Model.DataBase.Entity.Notification;
import com.example.birdbreeder.Model.DataBase.Entity.Species;

@Database(entities = {Bird.class , Egg.class , Mating.class , Notification.class , Species.class} , version = BirdBreederConstants.DB_VERSION)
public abstract class BirdsDataBase extends RoomDatabase {
    private static BirdsDataBase instance;
    public static synchronized BirdsDataBase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),BirdsDataBase.class, BirdBreederConstants.DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }//end if
        return instance;
    }// end getInstance(..)

    //DAOs

//    public abstract BirdDao birdDao();
//    public abstract BreederDao breederDao();
//    public abstract NotificationDao notificationDao();
//    public abstract MatingDao matingDao();
//    public abstract EggDao eggDao();

}//end class