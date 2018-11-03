package me.kevingleason.mslab.ModelClasses;

/**
 * Created by Mashhood on 8/10/2018.
 */

public class SavedOrderModal { //this is for the SavedOrderModal


    //this is for the fields
    public String  id; //this  is for the id from the database
    public int serial; //this is for the serial number
    public String orderDescription; //this is for the order  Description
    public String date; //this is for the date
    //end of the fields



    //this is for the other fields
    public boolean isSelected=false; //this is for if is selected
    //end of the other fields



    //this is for the constructor

    public SavedOrderModal() {
    }



    //this is for the parameter wala Constructor
    public SavedOrderModal(String id, int serial, String orderDescription, String date) {
        this.id = id;
        this.serial = serial;
        this.orderDescription = orderDescription;
        this.date = date;
    }
    //end of the parameter wala Constructor
    //end of the constructor




    //this is for the getter
    public String getId() {
        return id;
    }

    public int getSerial() {
        return serial;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public String getDate() {
        return date;
    }

    public boolean isSelected() {
        return isSelected;
    }

    //end  of the getter




    //this is for the setter
    public void setId(String id) {
        this.id = id;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


    //end of the setter



}//end of the SavedOrderModal
