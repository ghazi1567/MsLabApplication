package me.kevingleason.mslab.BhattiTesting;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import me.kevingleason.mslab.BhattiParsers.BhattiParsers;
import me.kevingleason.mslab.DatabaseWork.Orders;
import me.kevingleason.mslab.DatabaseWork.PartyDatabase;
import me.kevingleason.mslab.DatabaseWork.ProductDatabase;
import me.kevingleason.mslab.DatabaseWork.TrackingData;
import me.kevingleason.mslab.ModelClasses.OrderItem;
import me.kevingleason.mslab.ModelClasses.OrderModel;
import me.kevingleason.mslab.ModelClasses.Product;
import me.kevingleason.mslab.R;
import me.kevingleason.mslab.Utility.Utility;

public class BhattiTesting extends AppCompatActivity {


    //this is for the database
    public Orders  orders;
    //end of the database






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bhatti_testing);


        //this is for the initialization of the
        //InitializeWork(); //this is for the initialization
        //end  of the initialization of the Work



        //this  is for the orders
        orders=new Orders(this); //this is for the orders
        //end of the orders



        //this is for the getting the data
        //MakePost(); //this is for the MakePost();
        //end of getting the data



        //this is for to Play With Time and Date
        //PlayWithDateTime();
        //end of going for to Play With the Date And Time



        //this is for to get the List
        GetDataOnProductAuto("");
        //end of getting the List





    }//end of the onCreate




    //this is for the methods


    //this  is for to insert the data
    public void InsertData(OrderModel  orderModel){



        //this is for to insert the orders
        orders.InsertOrder(orderModel);  //this is for to insert the orders
        //end of going for to insert the orders




    }
    //end of going for to insert the data



    //this is for  to Print the All fields
    public void PrintTableData(){

        //this is for to Print the Table Table
        Cursor  cursor=orders.GetOrders(); //this is for to get the



        //this is for to check  the cursor
        if(cursor==null){
            PrintingToast("Orders Cursor Was null");
        }
        else{


            //this is for the other Work that We can read the data for the cursors
            cursor.moveToFirst();


            //this is for the length of the cursors
            for(int i=0; i<cursor.getCount(); i++){ //this is for the start of the loop


                //this is for to get the data
                cursor.moveToPosition(i); //this is for the counter values in that content
                //end of getting the data



                //this  is for the fields
                String id= cursor.getString(0) ; //this is for the id
                String date=  cursor.getString(1)  ; //this is for the date
                String orderDetail= cursor.getString(2) ;  //this is for the order detail
                //end of the inner fields




                //this is for to Print The Orders Work
                PrintLog("BhattiOrders"+"Id:"+id+"\tDate:"+date+"\tDetail:\t"+orderDetail);  //this  is for the Orders Work
                //end of the Printing the Orders Work





                //this is for to process the order for the  json as it should be
                String jsonableLines=orderDetail;




                //this is for the json line
                try{//this is for the try


                    JSONObject  jsonObject=new JSONObject(jsonableLines);
                    jsonObject.put("sessionId","BhattiWorkSession");

                    //this is for to Print
                    PrintLog("The json Data is as follows  "+jsonObject.toString()); //this is for the Log Line for the perfect Work
                    //end of going for to Print it

                   }//end of the try
                catch (Exception ex){ //this is for the catch
                    PrintLog("Exception of type is as follows  "+ex.getMessage()+" while going to parse the JSON Data");
                }
                //end of the json updating process






                //end of the getting the json String Line from  the  Database









            }
            //end of getting the cursors length




            //end of reading the data for the cursors



        }

        //end of checking for the cursor


        //end of Printing the Table Data

    }
    //end of printing the table fields




    //end of the methods for the orders




    //this is for to initialize work
    public void InitializeWork(){
            //this is for the initialization of work
            try{ //this is for the try

               ProductDatabase productWork=new ProductDatabase(getApplicationContext()); //this is for the party work

                //this  is for the partyWork
                Product  product=new Product(10,"Product","code 23",12212,12.0,10.4,"12","Syrup","");
                //end of the partyWork


                //this is for to give me data in the form of the list
                List<Product>  partyList=new ArrayList<Product>(); //this  is for the party List
                partyList.add(product);
                //end of giving me data in the form of the list



                //this  is for the loop
                productWork.InsertIntoTable(partyList); //this is for the party List
                //end of the loop work




                //this is for to get the data from the list as it must be there
                Cursor  cursor=  productWork.GetProducts();

                if(cursor !=null){ //this is for the if work



                    //this is for to load that data
                    int size=cursor.getCount();
                    cursor.moveToFirst(); //this is for  to move to the first
                    PrintingToast("The cursor size is as follows"+size);
                    for(int i=0; i<size; i++){ //this is for the loop
                        PrintingToast("The name is as follows  "+cursor.getString(1));
                    }
                    //end  of loading the data




                }//end of the if work  in  that window
                else{ //end of the else part
                    PrintingToast("Sorry the coming the cursor is null"); //this is for the coming cursor is null
                }


                //end of getting the data from the list






               } //end of the try
            catch (Exception ex){ //this is for the catch
                PrintLog("Exception of type is as follows "+ex.getMessage()+" while testing  in the Initialization of the Work");
            }
            //end of the initialization of the work
    }
    //end of initialize work




    //this is for to make a post
    public void MakePost(){

        //this is for the try and catch
        try{//this is for the try



            //this is for to make things more straight forward
            String url="http://18.218.104.119:5000/api/Api/PlaceOrder"; //this is for the url
            //end of making things more straight forward



            //this is for the quantity
            int productId=649;  //this is for the products Id
            int quantity=4;
            int mobileId=3; //this is for the mobile id
            //end of the quantity



            //this is for the List  of the products
            final List<OrderItem>  orderItems=new ArrayList<OrderItem>(); //this  is for the order Items
            //end of the list of the products



            //this  is for the Array
            //this is for the further objects for the War
            JSONArray order_Items=new JSONArray();
            //this is for the Array
            //end of the Array


            //this is for to add them to the List
            for(int i=0; i<10; i++){ //this is for the list Work



                //this is for to add the Order Items
                orderItems.add(new OrderItem(productId+i,quantity+i,mobileId+i)); //this is for the mobile id
                JSONObject  item=new JSONObject();
                item.put("productID",productId+i); //this is for the item
                item.put("qty",quantity+i);
                item.put("OrderMainMobileID",i);
                order_Items.put(item); //this is for the item
                //end of adding the Order Items


            }
            //end of adding them to the List







            //this is for the JSONObject
            final org.json.JSONObject final_object=new  org.json.JSONObject();


            //this is for to put the values
            try{


                final_object.put("sessionId","28005849b0be479e8e3f05ec8e30a9af"); //this is for the session
                final_object.put("partyID",1133); //this is for the
                final_object.put("orderDate","06/07/2018");
                final_object.put("salesManID","28");
                final_object.put("status","Ok"); //this is for the ok
                final_object.put("Comments","This is for the good work");
                final_object.put("loginName","test");
                final_object.put("password","test");
                final_object.put("orderDetail",order_Items);
                //end of the json Object


               }//end  of the try
            catch (Exception ex){ //this is for the to catch
                PrintLog("Exception of type is as follows  "+ex.getMessage()); //this  is for to Print the Log
            }
            //end of going to put the Values


            //end of the JSONObject


            //end of the further objects for the War








            //this is for to get the Orders
           // PrintTableData();
            //end of getting the orders



            //this is for the to get the Product Name
            ProductDatabase  productDatabase=new ProductDatabase(getApplicationContext());
            PrintingToast("The name is  "+productDatabase.GiveMeProductName("650"));
            //end of going for to get the Product Name













            //this is for the JFrog Volley
            final String baseUrl="http://18.218.104.119:5000/api/Api/PlaceOrder"; //this is for the base Url
            //end of the JFrog Volley



            //this is for the Request Queue
            RequestQueue  requestQueue= Volley.newRequestQueue(getApplicationContext());

            //end of the Request Queue



            //this is for the string request
            StringRequest stringRequest= new StringRequest(Request.Method.POST, baseUrl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    PrintingToast("This is for the response "+response);
                    PrintLog("The response is as follows  "+response);
                }//end of the response
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                          PrintingToast("Error response is as follows  "+error);
                          PrintLog("Error response is as follows  "+error);
                        }
                    }
            ){ //this is for the to override the methods
                @Override
                public byte[] getBody() throws com.android.volley.AuthFailureError {
                    String str = final_object.toString();
                    return str.getBytes();
                };

                public String getBodyContentType()
                {
                    return "application/json; charset=utf-8";
                }

            };
            //end of the string request





            //this is for to add   it to the list
            //  requestQueue.add(stringRequest); //this is for the string request
            //end of adding it to the list





           }//end of the try
        catch (Exception ex){ //this is for the catch
            PrintLog("Exception of type is as follows  "+ex.getMessage()+" while going for to post the products for it");
        }
        //end of the try and catch

    }
    //end of going for to make a post





    //this is for to Play With the Time And Tracking Work
    public void PlayWithDateTime(){


        //this is for the Objects
        TrackingData  trackingData=new TrackingData(getApplicationContext()); //this is for the Tracking Work
        //end of the Objects


        //this  is for to delete
         //trackingData.DeleteData(); //this is for to delete the Data
        //end of going for to delete



        //this is for  to make the data
        /* for(int i=0; i<10; i++){


            Tracking   t=new Tracking("10.33,20.44", Utility.GiveMeDateTimeForTrackingInsetion());



            //this is for to insert
            trackingData.InsertValues(t);  //this  is for insert the data in that table for the best
            //end of going for to insert the data




        }   */
        //end of going for to make the data



        //this is for to Show the Data
        Cursor cursor=trackingData.GetData(Utility.GiveMeDateForTracking());
        cursor.moveToFirst();
        //end of  Going for To Show the Data



        //this is for the Session
        String session="1de4074731eb42c1b646bcb8fb04e274"; //this is for the session
        //end of the Session



        //this is for the json Array
         final JSONArray  jsonArray=new JSONArray(); //this is for the json Array
        //end of the josn Array








        //this is for the URL
        String url="http://18.218.104.119:5000/api/Api/PuchLocation";
        //end of the URL



        //this is for to check the cursor
        if(cursor != null){ //this is for to check cursor


               //this is for the Loop
               for(int i=0; i<cursor.getCount(); i++){  //this is for the Loop

                  cursor.moveToPosition(i);

                   String first=cursor.getString(0);
                   String second=cursor.getString(1);
                   String third=cursor.getString(2);

                   //this is for the Log
                   PrintLog("Tracking Data:\t"+"( "+first+" ,  "+second+"  "+third+" )");
                   //end of the Log

                   String []  array=second.split(","); //this is for the array

                   String lati=array[0];  //this is for the lati
                   String longi=array[1];  //this   is for the longi




                   //this is for the try and Catch
                   try{


                       //this is for the Json Object
                       JSONObject  jsonObject=new JSONObject(); //this is for the JSOn Object
                       jsonObject.put("sessionID",session);
                       jsonObject.put("salesManID",28);
                       jsonObject.put("latitude",lati);
                       jsonObject.put("longitude",longi);
                       jsonObject.put("locationDateTime",third);
                       //end of the JSON Object




                       //this is for to put the Json Data
                       jsonArray.put(jsonObject);
                       PrintLog("Array is as follows "+jsonArray.toString());
                       //end of going for to put the JSON data

                   }//end of the catch
                   catch(Exception ex){ //this is for the Catch
                       PrintLog("Exception of type is as follow  "+ex.getMessage()+" while going for Put Data to Array");
                   }
                   //end of the try and Catch






               }
               //end of the Loop







               //this is for to Put the Values to the Array List


            //this  is for the Volley
            final JSONArray  jsonArray1=jsonArray;
            //this is a place for the Volley

            //this is for the Request Queue
            RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());

            //end of the Request Queue



            //this is for the string request
            StringRequest stringRequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {




                    //this is for the Bhatti Parsers
                    BhattiParsers bhattiParsers=new BhattiParsers();
                    String response_=bhattiParsers.GetProductOrderResponse(response);
                    PrintLog("Alert"+response_);
                    //end of the Bhatti Parsers


                    PrintLog("The response is as follows  "+response);

                    }//end of the response
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            PrintLog("Error response is as follows  "+error);
                        }
                    }
            ){ //this is for the to override the methods
                @Override
                public byte[] getBody() throws com.android.volley.AuthFailureError {
                    String str = null;
                        str = jsonArray.toString();
                    PrintLog("The Request is as follows "+str);
                    return str.getBytes();
                };

                public String getBodyContentType()
                {
                    return "application/json; charset=utf-8";
                }

            };
            //end of the string request





            //this is for to add   it to the list
            requestQueue.add(stringRequest); //this is for the string request
            //end of adding it to the list
            //end of the Volley


            //end of Going for to Put the Values to the Array List








        }//end of the of the Cursor
        else{
            PrintingToast("Sorry Data  is null for the cursor");
        }
        //end of checking  the cursor






    }
    //end of Playing With Date and Time







    //this is for to get   the products from the relation on the basis of  given data
    public void GetDataOnProductAuto(String lines){  //this  is for the project lines for the data



        //this is for the lines
        PartyDatabase   productDatabase=new PartyDatabase(getApplicationContext()); //this  is for the product database
        //end of the lines





        //this  is for to get the cursor   object
         List  <String> productListName=productDatabase.GiveMePartyNameForBetterSearch("1521");
           for(String name: productListName){
               PrintLog("The Name  "+name);
           }
           if(productListName.size()>0){
               PrintingToast("Has length greatar than 0");
           }
           else{
                PrintingToast("Sorry it does not have length greater than 0 ");
           }
        //end of getting the cursor Object









    }
    //end of getting the data from the relation on the basis of the given data







    //this  is for the other
    private static final String TAG = "BhattiTesting";
    public void PrintLog(String Line){ //this is for the Line
        Log.d(TAG,Line); //this is for the Line
    }
    //end  of the other



    //this is for Printing the Toast
    public void PrintingToast(String Line){  //this is for the Toast Printing
        Toast.makeText(getApplicationContext(),Line,Toast.LENGTH_SHORT).show(); //this is for to make the toast
    }
    //end of Printing the Toast



}//end of the bhatti testing
