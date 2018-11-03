package me.kevingleason.mslab.BhattiSharedPreferencesManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * Created by Mashhood on 7/1/2018.
 */

public class BhattiPreferencesManager { //this is for the Preference Maanger


    //this is for the fields
    public Context ctx;
    //end of for the fields


    //this is for the constructor

    public BhattiPreferencesManager(Context ctx) {
        this.ctx = ctx;
    }

    //end of the constructor



    //this is for to set the  user login
    public void SetUserLogin(boolean login){  //this is for to set the Login

        //this is for the User Login
        try{//this  is for the try


            SharedPreferences   sharedPreferences= PreferenceManager.getDefaultSharedPreferences(ctx);



            //this is for to to set the User login state
            SharedPreferences.Editor  editor =sharedPreferences.edit();
            editor.putBoolean("userLoginState",login); //this is for the editor\
            editor.commit();
            //end  of for user login state



           }//end of the try
        catch (Exception ex){ //this is for the catch
              PrintLog("Exception of type is as follows  "+ex.getMessage()+"  while going for setting the user login ");
        }
        //end of for the User Login


    }
    //end of setting the user login





    //this is for the To get the User Login State
    public boolean GiveUserState(){
        boolean  userState=false; //this is for the boolean state


        //this is for to get the values from the Shared Preference
        try{//this is for the try

            //this is for to get the SharedPreferences
            SharedPreferences  sharedPreferences=PreferenceManager.getDefaultSharedPreferences(ctx);
            return sharedPreferences.getBoolean("userLoginState",false);
            //end of getting the SharedPreferences



           }//end of the try
        catch (Exception ex){
            PrintLog("Exception of type is as follows  "+ex.getMessage()+" While getting the user state");
        }
        //end of getting the values from the Shared Preferences



        return  userState;
    }
    //end of getting the user Login State






    //this  is for set the session
    public void SetUserSession(String  session){  //this is for to set the Login

        //this is for the User Session setting
        try{//this  is for the try


            SharedPreferences   sharedPreferences= PreferenceManager.getDefaultSharedPreferences(ctx);



            //this is for to to set the User login state
            SharedPreferences.Editor  editor =sharedPreferences.edit();
            editor.putString("session",session); //this is for the editor\
            editor.commit();
            //end  of for user login state



        }//end of the try
        catch (Exception ex){ //this is for the catch
            PrintLog("Exception of type is as follows  "+ex.getMessage()+"  while going for setting the user session ");
        }
        //end of for the User Login


    }
    //end of going for setting the session






    //this is for to get the Session
    public String GetUserSession(){  //this is for to set the Login

        String session="";


        //this is for the User Session
        try{//this  is for the try


            SharedPreferences   sharedPreferences= PreferenceManager.getDefaultSharedPreferences(ctx);



            //this is for to to get the User login state Session
            return sharedPreferences.getString("session",""); //this is for getting the session
            //end  of for user login state Session



        }//end of the try
        catch (Exception ex){ //this is for the catch
            PrintLog("Exception of type is as follows  "+ex.getMessage()+"  while going for getting user session ");
        }
        //end of for the User Login

        return session;
    }
    //end of getting the Session







    //this  is for  to work  on the id
    public void SetUserID(int id){  //this is for to set the Login

        //this is for the User id setting
        try{//this  is for the try


            SharedPreferences   sharedPreferences= PreferenceManager.getDefaultSharedPreferences(ctx);



            //this is for to to set the User login state
            SharedPreferences.Editor  editor =sharedPreferences.edit();
            editor.putInt("id",id); //this is for the editor\
            editor.commit();
            //end  of for user login state



        }//end of the try
        catch (Exception ex){ //this is for the catch
            PrintLog("Exception of type is as follows  "+ex.getMessage()+"  while going for setting the user id ");
        }
        //end of for the User Login


    }
    //end of  working on the id






    //this is for to get the user id
    public int GetUserID(){  //this is for to set the Login

        int userId=-1;


        //this is for the User id getting
        try{//this  is for the try


            SharedPreferences   sharedPreferences= PreferenceManager.getDefaultSharedPreferences(ctx);



            //this is for to to set the User login state
            userId=sharedPreferences.getInt("id",-1);
            return  userId;  //this is for the user id
            //end  of for user login state



        }//end of the try
        catch (Exception ex){ //this is for the catch
            PrintLog("Exception of type is as follows  "+ex.getMessage()+"  while going for setting the user id ");
        }
        //end of for the User Login

       return  userId;
    }
    //end of getting the user id






    //this is for to set the Login
    public void SetDeveloperKey(boolean flag){

        //this is for the User id setting
        try{//this  is for the try


            SharedPreferences   sharedPreferences= PreferenceManager.getDefaultSharedPreferences(ctx);



            //this is for to to set the User login state
            SharedPreferences.Editor  editor =sharedPreferences.edit();
            editor.putBoolean("flag",flag); //this is for the editor\
            editor.commit();
            //end  of for user login state



        }//end of the try
        catch (Exception ex){ //this is for the catch
            PrintLog("Exception of type is as follows  "+ex.getMessage()+"  while going for setting the developer flag");
        }
        //end of for the User Login


    }//end of  setting the Flag


    //this is for to get The developer Flag
    public boolean SetDeveloperKey(){

        //this is for the User id setting
        try{//this  is for the try


            SharedPreferences   sharedPreferences= PreferenceManager.getDefaultSharedPreferences(ctx);



            //this is for to to set the User login state
             return sharedPreferences.getBoolean("flag",true);
            //end  of for user login state



        }//end of the try
        catch (Exception ex){ //this is for the catch
            PrintLog("Exception of type is as follows  "+ex.getMessage()+"  while going for setting the developer flag");
        }
        //end of for the User Login

        return  true;

    }//end of getting the developer flag










    //this is for the  Log Printing
    private static final String TAG = "BhattiPreferencesManage";


    //this is for to Printing the Log
    public void PrintLog(String Line){
        Log.d(TAG,Line);   //this is for the Line
    }
    //end of Printing the Log

    //end of the Log Printing







}//end of the Preference Manager
