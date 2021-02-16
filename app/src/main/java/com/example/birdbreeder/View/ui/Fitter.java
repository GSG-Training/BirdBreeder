package com.example.birdbreeder.View.ui;
import android.content.Context;
import android.content.Intent;
import android.text.format.DateFormat;
import androidx.fragment.app.FragmentManager;
import com.example.birdbreeder.Model.Constants;
import com.example.birdbreeder.R;
import com.example.birdbreeder.View.ui.Birds.BirdProfileActivity;
import com.example.birdbreeder.View.ui.Birds.BirdProfileFragment;
import com.example.birdbreeder.View.ui.Birds.MatingProfileFragment;
import java.text.ParseException;
import java.util.Date;

public class Fitter {

    private static final int DAY = 0 , MONTH = 1 , YEAR = 2 ;
    //convert String to Integer
    public static int toInteger(String num) {
        if (!num.isEmpty()) {
            return Integer.parseInt(num);
        }
        return -1;
    }//toInteger

    //convert String to double
    public static double toDouble(String num) {
        if (!num.isEmpty()) {
            return Double.parseDouble(num);
        }
        return -1;
    }//toDouble

    //convert Date to String
    public static String getDate(Date time) {
        if (time != null) {
            return DateFormat.format("dd/MM/yyyy", time).toString();
        }
        return "21/10/2020";


    }//getDate

    //convert String to Date
    public static Date getDate(String time , Context context) {
        Date date = new Date();
        java.text.DateFormat dateFormat = DateFormat.getDateFormat(context);
        try {
            date = dateFormat.parse(time);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }//getDate
    public static String setDateString(int year, int monthOfYear, int dayOfMonth) {

        // Increment monthOfYear for Calendar/Date -> Time Format setting
        monthOfYear++;
        String mon = "" + monthOfYear;
        String day = "" + dayOfMonth;

        if (monthOfYear < 10)
            mon = "0" + monthOfYear;
        if (dayOfMonth < 10)
            day = "0" + dayOfMonth;

       return day + "/" + mon + "/" + year;

    }


    //get exact part of the date
    private static int getIndex (String date , int index){
        int indexValue = 0 ;
        if(date!=null){
            String[] inDate= date.split("/");
            if(inDate.length>index)
                indexValue = toInteger(inDate[index]) ;
        }

        return indexValue ;
    }//getIndex

    //compute Age of a bird or an egg
    public static String getAge(String today , String bth){
        String age = "";
        int days , mon , years ;
        if(today!=null && bth!=null){
            years = getIndex(today , YEAR) - getIndex(bth , YEAR);
            mon = getIndex(today , MONTH) - getIndex(bth , MONTH);
            days =getIndex(today , DAY) - getIndex(bth , DAY);
            //Check the negative "LESS THAN" a month or year
            if(days<0) {
                days += 30 ;
                mon -- ;
            }
            if(mon<0) {
                mon += 12 ;
                years -- ;
            }
            //concat the age as a string
            if(years!=0) age =years + " Y " ;
            if(mon!=0) age += mon +" m ";
            if(days!=0) age +=days + " d" ;
            //check the empty age a 0 day
            if(age.isEmpty()) age = "0d";
                }

        return age;
    }//getAge


    /* intent for a the bird profile
     with 2 states
     *1 new bird
     *2 show bird
     */
    public static void toBirdProfile(FragmentManager fragmentManager, String tag , int action , int id ){
        BirdProfileFragment nextFrag= BirdProfileFragment.newInstance(action , id);
        fragmentManager.beginTransaction()
                .replace(R.id.main_container, nextFrag, BirdProfileFragment.TAG)
                .addToBackStack(tag)
                .commit();
    }//toBirdProfile

    /* intent for a the Mating profile
     with 2 states
     *1 new Mating
     *2 show Mating
     */
    public static void toMatingProfile(FragmentManager fragmentManager, String tag , int action , int id ){
        MatingProfileFragment nextFrag= MatingProfileFragment.newInstance(action , id);
        fragmentManager.beginTransaction()
                .replace(R.id.main_container, nextFrag , MatingProfileFragment.TAG )
                .addToBackStack(tag)
                .commit();
    }//toMatingProfile



    /* intent for a the Egg profile
     with 2 states
     *1 new Egg
     *2 show Egg
     */
    public static Intent toEggProfile(Context context , int id){
        Intent intent = new Intent(context , BirdProfileActivity.class );
        if(id == -1) {
            intent.putExtra(Constants.BIRD_ACTION , Constants.NEW_BIRD);
        }
        else {
            intent.putExtra(Constants.BIRD_ACTION , Constants.SHOW_BIRD);
            intent.putExtra(Constants.BIRD_ID , id);
        }
        return  intent ;

    }//toEggProfile






}//Fitter
