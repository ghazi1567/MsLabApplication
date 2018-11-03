package me.kevingleason.mslab.DatabaseWork;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import me.kevingleason.mslab.ModelClasses.PartyWork;

/**
 * Created by Mashhood on 7/21/2018.
 */

public class PartyDatabase extends SQLiteOpenHelper { //this is for the PartyDatabase


       //this is for the context Work
       public Context ctx; //this is for the context
       //end of context Work



        //this is for the fields
        public static  final String  DATABASE="MSLAB_.db";
        public static  final String  TABLE="Party";
        //end of the fields




        //this is for the list of the fields
        public static  final String id="id";
        public static  final String name="name";
        public static final String address="address";
        public static  final String contactPerson="contactPerson";
        public static  final String mobile="mobile";
        public static  final String discountPercentage="discountPercentage";
        public static  final String code="code"; //this is for the code
        public static  final String balance="balance"; //this is for the balance
        //end of the list of the fields




        //this is for the constructor
        public PartyDatabase(Context context) {
            super(context, DATABASE, null, 1);
            this.ctx = context;
        }
        //end of the constructor







        //this is for to create the table
        @Override
        public void onCreate(SQLiteDatabase db) {

            //this is for to execute the query
            db.execSQL("CREATE TABLE  Party  (\n" +
                    "   id text,\n" +
                    "   name text,\n" +
                    "   address text,\n" +
                    "   contactPerson text,\n" +
                    "   mobile    text,\n" +
                    "   discountPercentage text,\n" +
                    "   code text,\n" +
                    "   balance text \n" +
                    ");");  //this is for the execution of the query
            //end of the execution of the query

        }

       @Override
       public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
           db. execSQL("DROP TABLE IF EXISTS Party");
           onCreate(db);
        }
        //end  of creating the table






        //this is for to insert the data to  the database
        public void InsertPartyData(List<PartyWork> partyWorks){ //this is for the party Data




            //this  is for to delete the previous data
            SQLiteDatabase  database=getReadableDatabase(); //this is for to get the readable database
            database.execSQL("DELETE FROM  Party");
            //end of deleting the previous data



            //this is for to get the database name
            SQLiteDatabase  sqlWriteDatabase=getWritableDatabase(); //this is for the writable Database
            //end of getting the database name



            //this is for to insert the data to the party database
            for(int i=0; i<partyWorks.size(); i++){ //this is for the counter


                //this  is for to get the current item
                PartyWork item=partyWorks.get(i); //this is for to get an Item
                //end of getting the current item



                //this  is for to put it to the database
                ContentValues  contentValues=new ContentValues(); //this is for the content values
                //CREATE TABLE Party ( id text, name text, address text, contactPerson text, mobile text, discountPercentage text, code text, balance text )
                contentValues.put("id",String.valueOf(item.getId()));
                contentValues.put("name",item.getName());
                contentValues.put("address",item.getAddress());
                contentValues.put("contactPerson",item.getContactPerson());
                contentValues.put("mobile",item.getMobile());
                contentValues.put("discountPercentage",item.getDiscountPercentage());
                contentValues.put("code",item.getCode());
                contentValues.put("balance",balance);
                //end of placement of the values to the database




                //this  is for to put the values in the database
                sqlWriteDatabase.insert("Party",null,contentValues);
                //end of going for to put the values in the database




              }
            //end of insertion of the data in the database





        }
        //end of insertion of the data to the database






        //this is for to get the data form the database
        public Cursor GetPartyList(){
              Cursor list=null ;//this is for the partyWork



            //this is for to get the data from the table
            SQLiteDatabase  readableDatabase=getReadableDatabase();
            list=readableDatabase.rawQuery("SELECT *  FROM Party",null); //this is for the null query
            //end of getting the data form the table



            return list; //this is for the list
        }
        //end of getting the data form the database





    //this is for to get the party by the product name for AutoComplete Proper Functions
    public List<String> GiveMePartyNameForBetterSearch(String query){
        List<String> name=new ArrayList<String>();



        //this is for the try
        try{
            //this is for to get the Product Name
            SQLiteDatabase  database=getReadableDatabase(); //this is for to get the Readable Database
            Cursor cursor=database.rawQuery("SELECT  name, code  FROM  Party where  name Like '%"+query.replace(" ","%")+"%'"+" OR  code Like '%"+query+"%'",null);


            cursor.moveToFirst();



            //this  is for to check for the cursor
            if(cursor!=null){ //this is for if the cursor  is not null


                cursor.moveToFirst();

                if(cursor.getCount()>=1){

                }
                else{
                    return   name;
                }




                //this  is for to go through the loop
                for(int i=0; i<cursor.getCount(); i++){ //this  is for to get the total number of
                    cursor.move(i);
                    String name_=cursor.getString(0);
                    String code=cursor.getString(1);


                    //this is for the List of the List
                    if(name!=null)
                        name.add(name_+"\nCode:"+code);
                    //end of adding the List of the name and other things


                }
                //end of going through the loop








            }
            //end of checking for the cursor



            //end of getting the Product Name
        }//end of the try
        catch(Exception ex){ //this is for the exception
            PrintLog("Exception of type is as follows  "+ex.getMessage()+" while going for to get the Product Name");
        }
        //end of the try



        return  name;
    }
    //end of  getting the party name for AutoComplete Proper Functions





    //this  is for the Log
    private static final String TAG = "PartyDatabase";

    //this is for the Log Printing
    public void PrintLog(String Line){
        Log.d(TAG,Line); //this is for the Line
    }

    //end of the Log Printing

    //end of the Log



}//end of the PartyDatabase
