package me.kevingleason.mslab.DatabaseWork;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import me.kevingleason.mslab.ModelClasses.Product;

/**
 * Created by Mashhood on 7/21/2018.
 */

public class ProductDatabase   extends SQLiteOpenHelper { //this is for the Party Database


    //this  is for the List of the fields
    public static  final String  DATABASE="MSLAB_p.db";
    public static  final String  TABLE="Product";
    public static  final  String id="id";
    public static  final  String name="name";
    public static  final  String code="code";
    public static  final  String  typeID="typeID";
    public static  final  String  purchasePrice="purchasePrice";
    public static  final  String  salePrice="salePrice";
    public static  final  String   size="size";
    public static  final  String    category="category";
    public static  final  String   uom="uom";
    //end of the List of the fields



    //this is for the context
    public Context ctx;
    //end of the context




    //this  is for the constructor
    public ProductDatabase(Context context) {
        super(context, DATABASE, null, 1);
        this.ctx = context;
    }
    //this is for the Constructor


    @Override
    public void onCreate(SQLiteDatabase db) {

        //this is for to get the data table created
        db.execSQL("CREATE TABLE  Product(\n" +
                "    id int,\n" +
                "    name text,\n" +
                "    code text,\n" +
                "    typeID text,\n" +
                "    purchasePrice text,\n" +
                "    salePrice text,\n" +
                "    size text,\n" +
                "    category text,\n" +
                "    uom text\n" +
                ");"); //this is for to execute the query
        //end of going for to create the data table

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {//this is for the upGrading
        db. execSQL("DROP TABLE IF EXISTS Product");
        onCreate(db);
    }//end of onUpgrade Work





    //this  is for to insert the data in the dataTable
    public void InsertIntoTable(List<Product> list){  //this is for the list

             try{//this  is for the start of the try


                 //this is for to make the table empty
                 SQLiteDatabase readableDatabase=getReadableDatabase();
                 readableDatabase.execSQL("DELETE from Product");  //this is for to make the table empty
                 //end of going for to make the table empty





                 //this is for the sql database
                 SQLiteDatabase  database=getWritableDatabase(); //this is for the database
                 //end of the sql database





                 //this is for to insert the data to the database
                 for(Product p: list){ //this is for the loop


                     //this is for the content values
                     //this is for to insert the data
                     ContentValues contentValues=new ContentValues(); //this is for the content Values
                     contentValues.put("id",p.getId()); //this is for the  id
                     contentValues.put("name",p.getName()); //this is for the name
                     contentValues.put("code",p.getCode()); //this is for the code
                     contentValues.put("typeID",p.getTypeID()); //this is for the type ID
                     contentValues.put("purchasePrice",p.getPurchasePrice()); //this is for the purchase price
                     contentValues.put("salePrice",p.getSalePrice()); //this is for the sale price
                     contentValues.put("size",p.getSize());
                     contentValues.put("category",p.getCategory());
                     contentValues.put("uom",p.getUom());
                     //end of going for to insert the data
                     //end of the content values



                     //this  is for to insert
                     database.insert("Product",null,contentValues);  //this is for the content values
                     //end of going for to insert



                 }
                 //end of inserting the data to the database








                }//end of the try
             catch (Exception ex){//this is for the catch
                 PrintLog("Exception of type is as follows "+ex.getMessage()+" while going for to add Products To database");
             }//end of the catch



    }
    //end of going to insert the data in the dataTable





    //this is for to get the data
    public Cursor  GetProducts(){
        Cursor  cursor=null; //this is for the cursor
        try{//this is for the try


            //this is for the readable dataBase
            SQLiteDatabase  database=getReadableDatabase(); //this is for to get the readable Database
            //end of the readable database




            cursor=database.rawQuery("SELECT * FROM Product",null); //this is for the rawQuery to get the list of all products








           } //end of  the try
        catch (Exception ex){
               PrintLog("Exception of type is as follows "+ex.getMessage()+" while going for to Get Products");
        }//end of the catch
        return  cursor;
    }
    //end of getting the data




    //this is for the Log
    private static final String TAG = "ProductDatabase";
    public void PrintLog(String Line){
        Log.d(TAG,Line); //end of the Log
    }
    //end of the Log



    //this is for to get the  Product name
    public String GiveMeProductName(String id){
        String name="";



        //this is for the try
        try{
            //this is for to get the Product Name
            SQLiteDatabase  database=getReadableDatabase(); //this is for to get the Readable Database
            Cursor cursor=database.rawQuery("SELECT  name  FROM  Product where id="+id,null);


            cursor.moveToFirst();
            name=cursor.getString(0);

            //end of getting the Product Name
        }//end of the try
        catch(Exception ex){ //this is for the exception
            PrintLog("Exception of type is as follows  "+ex.getMessage()+" while going for to get the Product Name");
        }
        //end of the try



        return name;
    }
    //end of getting the product Name




    //this is for to get the product by the product name for AutoComplete Proper Functions
    public List<String> GiveMeProductNameForBetterSearch(String query){
        List<String> name=new ArrayList<String>();



        //this is for the try
        try{
            //this is for to get the Product Name
            SQLiteDatabase  database=getReadableDatabase(); //this is for to get the Readable Database
            Cursor cursor=database.rawQuery("SELECT  name, code  FROM  Product where  name Like '%"+query.replace(" ","%")+"%'"+" OR  code Like '%"+query+"%'",null);


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
    //end of  getting the products by the products name for AutoComplete Proper Functions







}//end of the ProductDatabase
