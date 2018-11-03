package me.kevingleason.mslab.DatabaseWork;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import me.kevingleason.mslab.ModelClasses.OrderModel;
import me.kevingleason.mslab.Utility.Utility;

/**
 * Created by Mashhood on 8/9/2018.
 */

public class Orders extends SQLiteOpenHelper { //this  is for the orders class

    //this  is for the list of the fields
     public Context context; //this is for the context
    //end of the fields of the orders



    //this is for the other fields
    public static final String DATABASE="MSLAB12.db"; //this is for the database
    //end of the other fields





    //this is for the constructor
    public Orders(Context ctx){
        super(ctx,DATABASE,null,1);
        this.context=ctx; //this  is for the context

    }
    //end of the constructor





    //this is for the other fields
    @Override
    public void onCreate(SQLiteDatabase db) {

        //this  is for to create the table
        try{
            db.execSQL("CREATE TABLE orders" +
                    " ( id integer PRIMARY KEY AUTOINCREMENT," +
                    " timing text," +
                    " order_detail text );");  //this is for the sql to run
           }//end of the try
        catch(Exception ex){ //this is for the exception
            PrintingLog("Exception of type is as follows "+ex.getMessage()+" while going for to create the Table"); //this is for to Print the Log
        }
        //end of going to create the table
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db. execSQL("DROP TABLE IF EXISTS orders");
        onCreate(db);
    }
    //end of the other fields






    //this is for to insert the data to the orders Table
    public void  InsertOrder(OrderModel orderItem){

        //this is for to insert the order item
        try{

            //this  is for to get the database
            SQLiteDatabase   database=getWritableDatabase(); //this is for to write the orders to the database
            //end of getting the database

            //this is for the content values
            ContentValues  contentValues=new ContentValues(); //this is for the content Values
            contentValues.put("order_detail",orderItem.getOrder_detail());  //this  is for the order_detail
            contentValues.put("timing",Utility.GiveMeCurrentDate());
            //end of the content values

            //this  is for to write the data to the sql
            database.insert("orders",null,contentValues); //this is for to insert
            //end of writing the data to the sqldatabase

           }//end of the try
        catch (Exception ex){
           PrintingLog("Exception of type is as follows "+ex.getMessage()+" while inserting the Order to Database");
        }
        //end of inserting the order item

    }
    //end of going to insert the data to the orders Table


    //this is for to get the orders
    public Cursor GetOrders(){
        Cursor  cursor=null; //this is for the cursor

        //this  is for to read the database
        try{//this  is for the try

            //this is for readable database
            SQLiteDatabase  database= getReadableDatabase() ; //this is for the sqlDatabase
            cursor =database.rawQuery("SELECT * FROM  orders ",null); //this is for the raw query
            return  cursor;
            //end of readable database

           }//end of the try
        catch (Exception ex){//this is for the catch
            PrintingLog("Exception of type is as follows  "+ex.getMessage()+" while reading the database  for the orders");
        }
        //end of reading the database

        return cursor; //
    }
    //end of  getting the orders

    //this is for to delete the order
    public void DeleteOrder(String id){

        //this is for the try
        try{
            SQLiteDatabase  database=this.getWritableDatabase()  ; //this  is for the order

            //this is for the query
            database.delete("orders","id=?",new String[]{id}); //this is for to delete the data in that data from the dataTable
            //end of the query

           }//end of the try
        catch (Exception ex){ //this is for the exception
            PrintingLog("Exception of type is as follows while delete the order "+ex.getMessage()); //this is for the Log Printing
        }
        //end of the try

    }
    //end of going for to delete the orders

    //this is for to update the data
    public void UpdateData(OrderModel  orderModel){

        //this  is for the try and catch
        try{


            //this is for to get the database
            SQLiteDatabase  database=this.getWritableDatabase(); //this is for the readable database
            //end of getting the database for to update




            //this  is for the content values
            ContentValues  contentValues=new ContentValues(); //this is for the content Values
            contentValues.put("order_detail",orderModel.getOrder_detail());  //this  is for the order_detail
            contentValues.put("timing",Utility.GiveMeCurrentDate());
            //end of the content values




            //this  is for to run the database
            database.update("orders",contentValues,"id=?",new String[]{orderModel.getId()});
            //end of going for to run the database




           }//end of the try
        catch(Exception ex){
            PrintingLog("Exception of type is as follows  "+ex.getMessage()+" while going for to update the data");
        }
        //end of the try catch

    }
    //end of going for to update the data










    //this  is for the log and Toast printing
    private static final String TAG = "Orders";


    //this is for the log Printing
    public void PrintingLog(String Line){
        Log.d(TAG,Line);  //this is for to Printing the Log
    }
    //end of the log Printing


    //this is for to Print the Toast
    public void PrintToast(String Line){ //this  is for to Print the Toast
        Toast.makeText(this.context,Line,Toast.LENGTH_SHORT).show(); //this is for to show the Line
    }
    //end of Printing the Toast


    //end of the toast and log printing


}//end of the orders class
