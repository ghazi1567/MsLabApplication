package me.kevingleason.mslab.ModelClasses;

/**
 * Created by Mashhood on 7/2/2018.
 */

public class PartyWork { //this is for the Party Work

       //this is for the fields
        public long id;
        public String    name;
        public String    address;
        public String    contactPerson;
        public String mobile;
        public long saleManID;
        public double   discountPercentage;
        public  String    code;
        public   double   balance;
        //end  of for the fields


        //this is for the construtor
           public PartyWork() {
           }
        //end of the constructor


         //this is for the paramerter Constructor

    public PartyWork(long id, String name, String address, String contactPerson, String mobile, long saleManID, double discountPercentage, String code, double balance) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contactPerson = contactPerson;
        this.mobile = mobile;
        this.saleManID = saleManID;
        this.discountPercentage = discountPercentage;
        this.code = code;
        this.balance = balance;
    }

    //end of the paramerter Constructor




         //this is for the getter

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public String getMobile() {
        return mobile;
    }

    public long getSaleManID() {
        return saleManID;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public String getCode() {
        return code;
    }

    public double getBalance() {
        return balance;
    }

    public long getId() {
        return id;
    }
    //end of getter



    //this is for the setters

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setSaleManID(long saleManID) {
        this.saleManID = saleManID;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setId(long id) {
        this.id = id;
    }
    //end of the setters



}//end of PartyWork
