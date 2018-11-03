package me.kevingleason.mslab.ModelClasses;

/**
 * Created by Mashhood on 8/4/2018.
 */

public class OrderItem {

    //this is for the List of the items
    public  int productID; //this is for the id
    public  int qty; //this is for the quantity
    public  int OrderMainMobileID; //this is for the OrderMainMobileID
    //end of the List of the items


    //this  is for the Constructor

    public OrderItem() {
    } //end of OrderItem

    public OrderItem(int productID, int qty, int orderMainMobileID) {
        this.productID = productID;
        this.qty = qty;
        OrderMainMobileID = orderMainMobileID;
    }

    //end of the Constructor





    //this is for the getter

    public int getProductID() {
        return productID;
    }

    public int getQty() {
        return qty;
    }

    public int getOrderMainMobileID() {
        return OrderMainMobileID;
    }

    //end of the getter



    //this is for the setter
    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setOrderMainMobileID(int orderMainMobileID) {
        OrderMainMobileID = orderMainMobileID;
    }
    //end of the setter






}//end of the OrderItem
