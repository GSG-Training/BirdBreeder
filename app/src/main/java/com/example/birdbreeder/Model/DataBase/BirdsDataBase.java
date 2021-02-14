package com.example.birdbreeder.Model.DataBase;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.birdbreeder.Model.BirdBreederConstants;
import com.example.birdbreeder.Model.DataBase.DAO.BirdDao;
import com.example.birdbreeder.Model.DataBase.DAO.EggDao;
import com.example.birdbreeder.Model.DataBase.DAO.MatingDao;
import com.example.birdbreeder.Model.DataBase.DAO.NotificationDao;
import com.example.birdbreeder.Model.DataBase.DAO.SpeciesDao;
import com.example.birdbreeder.Model.DataBase.Entity.Bird;
import com.example.birdbreeder.Model.DataBase.Entity.Egg;
import com.example.birdbreeder.Model.DataBase.Entity.Mating;
import com.example.birdbreeder.Model.DataBase.Entity.Notification;
import com.example.birdbreeder.Model.DataBase.Entity.Species;
import com.example.birdbreeder.Model.DataBase.TypeConverters.BitmapConverter;
import com.example.birdbreeder.Model.DataBase.TypeConverters.DateConverter;

@Database(entities = {Bird.class , Egg.class , Mating.class , Notification.class , Species.class} , version = BirdBreederConstants.DB_VERSION , exportSchema = false)
@TypeConverters({DateConverter.class , BitmapConverter.class})
public abstract class BirdsDataBase extends RoomDatabase {
    private static BirdsDataBase instance;
    //TODO: EXECUTER NEEDED IN MAIN ACTIVITY
    public static synchronized BirdsDataBase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),BirdsDataBase.class, BirdBreederConstants.DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }//end if
        return instance;
    }// end getInstance(..)

    //DAOs

    public abstract BirdDao birdDao();
    public abstract SpeciesDao speciesDao();
    public abstract NotificationDao notificationDao();
    public abstract MatingDao matingDao();
    public abstract EggDao eggDao();

}//end class