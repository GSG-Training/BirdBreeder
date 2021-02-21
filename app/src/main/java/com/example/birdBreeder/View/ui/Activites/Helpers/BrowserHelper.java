package com.example.birdBreeder.View.ui.Activites.Helpers;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.birdBreeder.Model.Constants;
import com.example.birdBreeder.Model.DataBase.Entity.Bird;
import com.example.birdBreeder.R;
import com.example.birdBreeder.View.ui.Activites.MainActivity.HomeFragment;
import com.example.birdBreeder.View.ui.Activites.MainActivity.MainActivity;
import com.example.birdBreeder.View.ui.Activites.MainActivity.NotificationFragment;
import com.example.birdBreeder.View.ui.Activites.MainActivity.ProfileFragment;
import com.example.birdBreeder.View.ui.Birds.BirdProfileFragment;
import com.example.birdBreeder.View.ui.Birds.BirdsFragment;
import com.example.birdBreeder.View.ui.Birds.EggProfileFragment;
import com.example.birdBreeder.View.ui.Birds.MatingProfileFragment;

public class BrowserHelper {


    /* intent for a the bird profile
  with 2 states
  *1 new bird
  *2 show bird
  */
    public static void toBirdProfile(FragmentManager fragmentManager, String from, int action, int id) {
        BirdProfileFragment nextFrag = BirdProfileFragment.newInstance(action, id);
        toFragment(fragmentManager , nextFrag , from);
    }//toBirdProfile

    /* intent for a the Mating profile
     with 2 states
     *1 new Mating
     *2 show Mating
     */
    public static void toMatingProfile(FragmentManager fragmentManager,
                                       String from,
                                       int action, int id) {

        MatingProfileFragment nextFrag = MatingProfileFragment.newInstance(action, id);
        toFragment(fragmentManager , nextFrag , from);

    }//toMatingProfile


    /* intent for a the Egg profile
     with 2 states
     *1 new Egg
     *2 show Egg
     */ public static void toEggProfile(FragmentManager fragmentManager, String from, int action, int id , int matingId , String spec) {
        EggProfileFragment nextFrag = EggProfileFragment.newInstance(action, id , matingId , spec);
        toFragment(fragmentManager , nextFrag , from);
    }
//toEggProfile


    public static void toFragment(FragmentManager fragmentManager , Fragment nextFrag ,
                                  String from){
        fragmentManager.beginTransaction()
                .replace(R.id.main_container, nextFrag)
                .addToBackStack(from)
                .commit();
    }

     public static Intent toMainActivity(Context context , int selectedItem){
         Intent intent = new Intent(context , MainActivity.class);
         intent.putExtra(Constants.SELECTED_ITEM , selectedItem);
         intent.getBundleExtra(Constants.SELECTED_ITEM).putInt(Constants.SELECTED_ITEM , selectedItem);
     return intent ; }


}
