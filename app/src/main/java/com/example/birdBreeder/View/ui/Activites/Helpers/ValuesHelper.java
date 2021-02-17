package com.example.birdBreeder.View.ui.Activites.Helpers;

import android.content.Context;
import android.text.format.DateFormat;

import java.text.ParseException;
import java.util.Date;

public class ValuesHelper {

    private static final int DAY = 0, MONTH = 1, YEAR = 2;

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
    public static Date getDate(String time, Context context) {
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

    //Format the date from date picker in "dd/MM/yyyy"
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

    }//setDateString


    //get exact part of the date
    private static int getIndex(String date, int index) {
        int indexValue = 0;
        if (date != null) {
            String[] inDate = date.split("/");
            if (inDate.length > index)
                indexValue = toInteger(inDate[index]);
        }

        return indexValue;
    }//getIndex

    //compute Age of a bird or an egg
    public static String getAge(String today, String bth) {
        String age = "";
        int days, mon, years;
        if (today != null && bth != null) {
            years = getIndex(today, YEAR) - getIndex(bth, YEAR);
            mon = getIndex(today, MONTH) - getIndex(bth, MONTH);
            days = getIndex(today, DAY) - getIndex(bth, DAY);
            //Check the negative "LESS THAN" a month or year
            if (days < 0) {
                days += 30;
                mon--;
            }
            if (mon < 0) {
                mon += 12;
                years--;
            }
            //concat the age as a string
            if (years != 0) age = years + " Y ";
            if (mon != 0) age += mon + " m ";
            if (days != 0) age += days + " d";
            //check the empty age a 0 day
            if (age.isEmpty()) age = "0d";
        }

        return age;
    }//getAge





}//ValuesHelper
