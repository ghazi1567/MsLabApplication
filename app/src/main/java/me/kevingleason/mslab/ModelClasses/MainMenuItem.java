package me.kevingleason.mslab.ModelClasses;

/**
 * Created by Mashhood on 7/20/2018.
 */

public class MainMenuItem { //this is for the Main Menu Item


    //this  is for the number of the fields
    public int ImageId;  //this  is for the image id
    public String text; //this is for the text
    public Class   class_; //this is for the class
    public int secondImage;  //this is for the second image id in case of the click
    //end of the number of the fields


    //this is for the constructor

    public MainMenuItem() {
    }  //end of the parameter less



    //this is for the parameter

    public MainMenuItem(int imageId, String text, Class class_,int secondImage) {
        ImageId = imageId;
        this.text = text;
        this.class_ = class_;
        this.secondImage=secondImage;
    }

    //end of the parameter



    //end of the constructor




    //this is for the getter

    public int getImageId() {
        return ImageId;
    }

    public String getText() {
        return text;
    }

    public Class getClass_() {
        return class_;
    }

    public int getSecondImage() {
        return secondImage;
    }
    //end of the getter



    //this is for the setter

    public void setImageId(int imageId) {
        ImageId = imageId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setClass_(Class class_) {
        this.class_ = class_;
    }

    public void setSecondImage(int secondImage) {
        this.secondImage = secondImage;
    }
    //end of the setter




}//end of the Main Menu Item
