package me.kevingleason.mslab.ModelClasses;

/**
 * Created by Mashhood on 7/1/2018.
 */

public class User { //this  is for the user class


    //this  is for the fields
              public int id;
              public String name;
              public String address;
              public String  contactPerson;
              public String mobile;
              public String mobileAlternate;
              public String code;
              public String loginName;
              public String password;
    //end of the fields




    //this is for the constructor


    public User() {
    }

    public User(int id, String name, String address, String contactPerson, String mobile, String mobileAlternate, String code, String loginName, String password) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contactPerson = contactPerson;
        this.mobile = mobile;
        this.mobileAlternate = mobileAlternate;
        this.code = code;
        this.loginName = loginName;
        this.password = password;
    }

    //end of the constructor






    //this is for the getters

    public int getId() {
        return id;
    }

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

    public String getMobileAlternate() {
        return mobileAlternate;
    }

    public String getCode() {
        return code;
    }

    public String getLoginName() {
        return loginName;
    }

    public String getPassword() {
        return password;
    }

    //end of the getters



    //this is for the setters

    public void setId(int id) {
        this.id = id;
    }

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

    public void setMobileAlternate(String mobileAlternate) {
        this.mobileAlternate = mobileAlternate;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //end of the setters



}//end of the user
