package me.kevingleason.mslab.ModelClasses;

/**
 * Created by Mashhood on 8/15/2018.
 */

public class Tracking { //this is for the class start


    //this is for the fields
    public String   location;
    public String   date;
    //end of the fields

    //this is for the  constructor
    public Tracking(String location, String date) {
        this.location = location;
        this.date = date;
    }
    //end of the constructor


    //this is for to getter
    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }
    //end of the getter


    //this is for the setter

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDate(String date) {
        this.date = date;
    }

    //end of the setter




}//end of the Class
