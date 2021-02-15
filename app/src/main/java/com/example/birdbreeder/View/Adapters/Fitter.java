package com.example.birdbreeder.View.Adapters;

import android.content.Context;
import android.text.format.DateFormat;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.ParseException;
import java.util.Date;

public class Fitter {

    private static int DAY = 0 , MONTH = 1 , YEAR = 2 ;
    //convert String to Integer
    public static int toInteger(String num) {
        if (!num.isEmpty()) {
            int integer = Integer.parseInt(num);
            return integer;
        }
        return -1;
    }//toInteger

    //convert String to double
    public static double toDouble(String num) {
        if (!num.isEmpty()) {
            double parseDouble = Double.parseDouble(num);
            return parseDouble;
        }
        return -1;
    }//toDouble

    //convert Date to String
    public static String getDate(Date time , Context context) {
        if (time != null) {
            java.text.DateFormat dateFormat = DateFormat.getDateFormat(context);
            return dateFormat.format(time);
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



    private static int getIndex (String date , int index){
        int indexValue = 0 ;
        if(date!=null){
            String[] inDate= date.split("/");
            if(inDate.length>index)
                indexValue = toInteger(inDate[index]) ;
        }

        return indexValue ;
    }

    public static String getAge(String today , String bth){
        String age = "";
        int days , mon , years ;
        if(today!=null && bth!=null){
            years = getIndex(today , YEAR) - getIndex(bth , YEAR);
            mon = getIndex(today , MONTH) - getIndex(bth , MONTH);
            days =getIndex(today , DAY) - getIndex(bth , DAY);
            if(days<0) {
                days += 30 ;
                mon -- ;
            }
            if(mon<0) {
                mon += 12 ;
                years -- ;
            }

            if(years!=0) age =years + " Y " ;
            if(mon!=0) age += mon +" m ";
            if(days!=0) age +=days + " d" ;

            if(age.isEmpty()) age = "0d";

                }

        return age;
    }


}
