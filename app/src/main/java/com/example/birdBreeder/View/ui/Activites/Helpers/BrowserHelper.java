package com.example.birdBreeder.View.ui.Activites.Helpers;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.birdBreeder.R;
import com.example.birdBreeder.View.ui.Birds.BirdProfileFragment;
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
     */
//    public static Intent toEggProfile(Context context, int id) {
////        Intent intent = new Intent(context, BirdProfileActivity.class);
////        if (id == -1) {
////            intent.putExtra(Constants.BIRD_ACTION, Constants.NEW_BIRD);
////        } else {
////            intent.putExtra(Constants.BIRD_ACTION, Constants.SHOW_BIRD);
////            intent.putExtra(Constants.BIRD_ID, id);
////        }
////        return intent;
//
//    }//toEggProfile


    public static void toFragment(FragmentManager fragmentManager , Fragment nextFrag ,
                                  String from){
        fragmentManager.beginTransaction()
                .replace(R.id.main_container, nextFrag)
                .addToBackStack(from)
                .commit();
    }
}
