package me.kevingleason.mslab.DatabaseWork;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import me.kevingleason.mslab.ModelClasses.Tracking;

/**
 * Created by Mashhood on 8/15/2018.
 */

public class TrackingData  extends SQLiteOpenHelper { //this  is for the Tracking Service

    //this is for the fields
    public Context ctx; //this is   for the context
    //end  for the fields


    //this is for the other Fields
    public static final   String  DATABASE_NAME="tracking.db";
    public static  final  String  TABLENAME="TRACKING"; //this is for the table Name
    //THIS IS FOR THE NAMES  OF THE OTHER FIELDS
    public static  final  String  ID="id";
    public static  final  String  location="location";
    public static  final String   dateTime="wakat";
    //END OF THE OTHER FIELDS
    //end of the other Fields




    //this is for the Constructor

    public TrackingData(Context context) {
        super(context, DATABASE_NAME, null, 1);  //this is for the Creation
        this.ctx = ctx;
    }
    //end of the Constructor


    //this is for the  to make the methods to be overriden

    @Override
    public void onCreate(SQLiteDatabase db) {

        //this is for to create the Database
        try{

                  //this is for the SQl Work
                   db.execSQL("CREATE TABLE TRACKING (\n" +
                           "    id integer PRIMARY KEY AUTOINCREMENT,\n" +
                           "    location text,\n" +
                           "    wakat text   \n" +
                           ");");  //this is for to run the sql
                  //end of the SQL Work

           }//end of the try
        catch(Exception ex){//this is for the catch
            PrintLog("Exception of type is as follows "+ex.getMessage()+" while going to Create Table for Tracking");
        }//end of the catch

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //this is for to delete the
        try{
                    db.execSQL("DROP TABLE IF EXISTS  TRACKING");  //this is for to drop the Table
           }//end of the try
        catch(Exception ex){ //this is for the Exception
            PrintLog("Exception of type is as follows  "+ex.getMessage());
        }
        //end of going for to delete

    }


    //end of going for to make the methods to be overriden








     //this is for to get the Insert the values in the database
     public void InsertValues(Tracking tracking){

         //this is for the try and catch
         try{


             //this is for the to insert the data
             SQLiteDatabase   database=getWritableDatabase(); //this is for to Write the Database

             //this is for the content values
             ContentValues  contentValues=new ContentValues() ; //this is for the content Values
             contentValues.put("location",tracking.getLocation());  //this  is for the location
             contentValues.put("wakat",tracking.getDate()); //this   is for wakat
             //end of the Content Values


             //this is for to insert the data
             database.insert(TABLENAME,null,contentValues);
             //end of going for to insert  the data



             //end of going for to insert the data




            }//end of the try
           catch(Exception ex){  //this is for to catch
               PrintLog("Exception of type is as follows  "+ex.getMessage()+" while going for to insert the Data");
           }
         //end of the try and catch

     }
     //end of going for to insert the values in the database







    //this is for to get the data
    public Cursor GetData(String time){
        Cursor  cursor=null; //this is for the cursor


        //this is  for to get the Result
        try{

            SQLiteDatabase  database= getReadableDatabase();
            cursor=database.rawQuery("SELECT  *  FROM   TRACKING where wakat LIKE '%"+time+"%'", null);  //this is for to read the orders
           }//end of the try
        catch(Exception ex){ //this is for to Catch
            PrintLog("Exception of type is as follows  "+ex.getMessage()+" while going for Get Data");
        }
        //end of getting the Result


        return   cursor; //this is for to get the Cursor
    }
    //end of getting the data





    //this is for to delete the data
    public void DeleteData(){

        //this is for the try and catch
        try{





             //this is for the data
             SQLiteDatabase   database=getWritableDatabase();  //this is for to get the data
             //end of getting the data




             //this is for the query
             database.execSQL("DELETE  from  TRACKING"); //this is for the query
             //end of the query








           }//end of the try
           catch(Exception ex){ //this is for the exception
               PrintLog("Exception of type is as follows "+ex.getMessage()+" while going for to delete the data");
           }
        //end of the try and catch

    }
    //end of going for to delete the data







    //this is for the line  for the toast and Lines for the logger
    private static final String TAG = "TrackingData";

    //this is for the Log
    public void PrintLog(String Line){ //this is for the Line Printing
        Log.d(TAG,Line); //this is for the Line Printing
    }
    //end of the Log


    //this is for the Toast Printing
    public void PrintingToast(String Line){
        Toast.makeText(ctx,Line,Toast.LENGTH_SHORT).show(); //this is for the Toast
    }
    //end of the Toast Printing

    //end of the lines for the logger and for the Toast


}//end of the Tracking Data
