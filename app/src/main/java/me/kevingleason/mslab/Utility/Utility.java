package me.kevingleason.mslab.Utility;

import java.text.SimpleDateFormat;

/**
 * Created by Mashhood on 8/9/2018.
 */

public class Utility { //this is for the utility Class


    //this is for to get the Current date
    public static String GiveMeCurrentDate(){
        SimpleDateFormat sfd = new SimpleDateFormat("MMMM dd, yyyy   hh:mm:ss a");
        return sfd.format(new java.util.Date());
    }
    //end of getting the currnet date


    //this is for to get the Current date
    public static String GiveMeCurrentDateForPost(){
        SimpleDateFormat sfd = new SimpleDateFormat("MM-dd-yyyy   hh:mm:ss a");
        return sfd.format(new java.util.Date());
    }
    //end of getting the currnet date




    //these are the methods for the  Other Work
    public static  String GiveMeDateTimeForTrackingInsetion(){
        SimpleDateFormat sfd = new SimpleDateFormat("MM-dd-yyyy  hh:mm:ss");
        return sfd.format(new java.util.Date());
    }
    //end of the Methods for the Other Work



    //this is for to to get the  only  date for the Tracking Work
    public static String  GiveMeDateForTracking(){
        SimpleDateFormat sfd = new SimpleDateFormat("MM-dd-yyyy");
        return sfd.format(new java.util.Date());
    }
    //end of giving the date for Tracking  Work




}//end of the Utility
