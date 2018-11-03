package me.kevingleason.mslab.ModelClasses;

/**
 * Created by Mashhood on 7/29/2018.
 */

public class SelectedProducts { //this is for the selected Products

    //this  is for the selected Products
    public   String id; //this is for the id
    public   String name; //this  is for the name of the Product
    public   String quantity; //this  is for the quantity
    public   String companyName; //this is for the company name
    public   boolean isSelected=false; //this is for the isSelected
    public   int counter;  //this is for the serial Number
    public   String  partyId; //this is for the party  id
    //end of the selected Products


    //this  is for the constructor
    public SelectedProducts(){

    }//this  is for the ParameterLess

    public SelectedProducts(String id, String name, String quantity, String companyName) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.companyName = companyName;
    }
    //end of the constructor



    //this  is for the getter

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getCompanyName() {
        return companyName;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public int getCounter() {
        return counter;
    }

    public String getPartyId() {
        return partyId;
    }

    //end of the getter


    //this is for the setter
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }
    //end of the setter



}//end  of the class SelectedProducts
