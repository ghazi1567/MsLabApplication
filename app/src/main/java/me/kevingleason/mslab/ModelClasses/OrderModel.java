package me.kevingleason.mslab.ModelClasses;

/**
 * Created by Mashhood on 8/9/2018.
 */

public class OrderModel {

   //this is for the fields
    public  String id; //this is for the order id
    public  String data; //this is for the date
    public  String order_detail; //this is for the order detail
   //end of the fields



   //this is for the methods
    public OrderModel() {
    }//end of the OrderModal


    //this is for the OrderModal

    public OrderModel(String id, String data, String order_detail) {
        this.id = id;
        this.data = data;
        this.order_detail = order_detail;
    }
    //end of the orderModal
    //end of the methods



    //this is for the getter
    public String getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public String getOrder_detail() {
        return order_detail;
    }
    //end of the getter


    //this is for the setter
    public void setId(String id) {
        this.id = id;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setOrder_detail(String order_detail) {
        this.order_detail = order_detail;
    }
    //end of the setter




}//end of the OrderModel
