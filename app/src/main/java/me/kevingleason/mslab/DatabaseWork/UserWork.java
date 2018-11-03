package me.kevingleason.mslab.DatabaseWork;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import me.kevingleason.mslab.ModelClasses.User;

/**
 * Created by Mashhood on 7/1/2018.
 */

public class UserWork extends SQLiteOpenHelper  {


    //this is for the class fields
    public Context ctx; //this is for the context
    //end of the class fields



    //this is for the other fields
    public static  final String  DATABASE="MSLAB.db";
    public static  final String  TABLE="user";
    //end of the other fields in the table



    //this is for the fields in the table
    public   static final  String id="id";
    public   static final  String name="name";
    public   static final  String address="address";
    public   static final  String contactPerson="contactPerson";
    public   static final  String mobile="mobile";
    public   static final  String mobileAlternate="mobileAlternate";
    public   static final  String code="code";
    public   static final  String loginName="loginName";
    public   static final  String password="password";
    //end of fields in the table


    //end of the other fields






    //this is for the Constructor
    public UserWork(Context context) {
        super(context, DATABASE, null, 1);
        this.ctx = context;
    }
    //end of the Constructor






    //this is for to implement a few methods
    @Override
    public void onCreate(SQLiteDatabase db) {

        //this is for to create the table
        db.execSQL("  CREATE TABLE  user\n" +
                "  (\n" +
                "     id int,\n" +
                "     name text,\n" +
                "     address text,\n" +
                "     contactPerson text,\n" +
                "     mobile text,\n" +
                "     mobileAlternate text,\n" +
                "     code text,\n" +
                "     loginName text,\n" +
                "     password text\n" +
                " );");
        //end of for going to crate the table


    }//end of on Create

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db. execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }
    //end of implement a few methods here





    //this is for to sync with the database
    public void SysncWithDataBase(User  user){
        try{//this is for the try


            //this is for the first query to remove if use data exit here

            //this is for the SQL Readable database
            SQLiteDatabase   database=getReadableDatabase(); //this is

            //this is for the query
            database.execSQL("DELETE FROM user WHERE user.id="+user.getId()); //this is for the delete query
            //end of the query
            //end of the SQL readable database
            //end of for going to remove if user data exist here



            //this is for to insert the data in the table

            //this is for to get the writeable database
            SQLiteDatabase   database_writeAble=getWritableDatabase();



            //this is for the content Values
            ContentValues  contentValues=new ContentValues(); //this is for the content Values
            contentValues.put("id",user.getId());
            contentValues.put("name",user.getName());
            contentValues.put("address",user.getAddress());
            contentValues.put("contactPerson",user.getContactPerson());
            contentValues.put("mobile",user.getMobile());
            contentValues.put("mobileAlternate",user.getMobileAlternate());
            contentValues.put("code",user.getCode());
            contentValues.put("loginName",user.getLoginName());
            contentValues.put("password",user.getPassword());
            //end of getting the content Values



            //end of going for to get the writeable database
            database_writeAble.insert("user",null,contentValues); //this is for writing to the database
            //end of going for to insert the data in the table




            //this is for that datahas been Synched


           }//end of the try
        catch (Exception ex){
                PrintLog("Exception of type is as "+ex.getMessage()+" while going for to synch the data with database");
         }//end of the catch exception
    }
    //end of for going to syn the database






    //this is for to Get User data
    public Cursor  GetData(String loginName, String password){


        //this is for to get the Data
        try{//this is for the try start


            //this  is for to get the Readable Data
            SQLiteDatabase   database=getReadableDatabase();
            Cursor  cursor   =database.rawQuery("SELECT * FROM user where user.loginName='"+loginName+"' AND  user.password='"+password+"'",null); //this is for the query
            //end of for going to get the Readable Data


            //this  is for to return the cursor
            return  cursor;
            //end of going for to get the cursor



            }//end of the try
        catch (Exception ex){
            PrintLog("Exception of type is as follows "+ex.getMessage()+"  while getting the user data");
        }
        //end of getting the Data



        return null;

    }
    //end of getting the user data





    //this is for the Console And Toast Work
    //this is for the Log
    private static final String TAG = "UserWork";
    //this  is for the Log
    public void PrintLog(String Line){ //this is for the Line
        Log.d(TAG,Line); //this is for the Log Printing
    }
    //end of the Log
    //end of the Log



    //this is for the Toast
    public void PrintToast(String Line){
        Toast.makeText(ctx,Line,Toast.LENGTH_SHORT).show(); //this is for the Toast Printing
    }
    //end of the Toast
    //end of Console And Toast Work






}//end of User Work
