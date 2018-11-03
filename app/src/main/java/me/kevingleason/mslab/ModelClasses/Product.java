package me.kevingleason.mslab.ModelClasses;

/**
 * Created by Mashhood on 7/21/2018.
 */

public class Product { //this  is for the Product

    //this is for the list of the fields
    public  long id; //this is for the id
    public  String name; //this is for the name
    public  String code; //this is for the code
    public  long typeID; //this is for the type id
    public  double purchasePrice; //this is for the purchase price
    public  double salePrice; //this is for the sale price
    public  String  size; //this is for the size
    public  String  category; //this is for the category
    public  String  uom; //this is for the uom
    //end of the fields



    //this is for the constructor

    public Product() {
    }


    //this is for the Parameter Constructor

    public Product(long id, String name, String code, long typeID, double purchasePrice, double salePrice, String size, String category, String uom) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.typeID = typeID;
        this.purchasePrice = purchasePrice;
        this.salePrice = salePrice;
        this.size = size;
        this.category = category;
        this.uom = uom;
    }


    //end of the constructor


    //end of the constructor



    //this is for the getter

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public long getTypeID() {
        return typeID;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public String getSize() {
        return size;
    }

    public String getCategory() {
        return category;
    }

    public String getUom() {
        return uom;
    }

    //end of the getter



    //this is for the setter

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setTypeID(long typeID) {
        this.typeID = typeID;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    //end of the setter


}//end of the Product
