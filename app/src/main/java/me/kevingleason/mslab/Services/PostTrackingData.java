package me.kevingleason.mslab.Services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import me.kevingleason.mslab.BhattiParsers.BhattiParsers;
import me.kevingleason.mslab.BhattiSharedPreferencesManager.BhattiPreferencesManager;
import me.kevingleason.mslab.DatabaseWork.TrackingData;
import me.kevingleason.mslab.RestfullApi.RESTFullApi;
import me.kevingleason.mslab.Utility.Utility;

/**
 * Created by Mashhood on 8/16/2018.
 */

public class PostTrackingData extends Service {  //this is for to Track The Data




    //this is for fields
    public Context  context; //this is for the Context
    //end of the fields



    //this  is for the  Methods
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }  //end of OnBinder


    //this is for the Flag
    public static boolean  flag=true;
    public int counter=0;
    public Handler handler;
    //end of the  Flag


    //this is for to Get Service

    @Override
    public int onStartCommand(Intent intent, final int flags, int startId) {

        //this  is for the fields
        handler=new Handler();
        this.context=getApplicationContext();
        //end of the fields



        //this is for the try And Catch
        try{


               Thread  t=new Thread(new Runnable() {
                   @Override
                   public void run() {


                       //this is for the Thread Sleep Work
                       try{


                           while (flag){ //this is for the Loop



                               //this is for the counter
                               counter++; //this is for to increase the Counter
                               //end of the counter


                               //this is for to Post the Data
                               FetchTheSession();
                               //end of Going For To Post Data




                               //this is for Log Line
                               PrintLog("The Counter Line  "+counter); //this is for the Counter Line
                               //end of the Log Line



                               //this is for the Interval
                               Thread.sleep(6000);
                               //end of the Interval



                           }//end of the Loop






                          }//end of the try
                       catch(Exception ex){ //this  is for the Exception
                          PrintLog("Exception of type is as follows  "+ex.getMessage()+" while going for  to Start the Service");
                       }
                       //end of the Inner Work





                   }//end of the Run Work
               });



            //this  is for To Start  the Thread
            t.start();  //this is for to Start the Thread
            //end of Going For To Start the Thread




           }//end of the try
        catch(Exception ex){ //this  is for the Exception
            PrintLog("Exception of type is as follows  "+ex.getMessage()+" while going for to Run the Thread in Service");
        }
        //end of the try And Catch





     return   START_STICKY;
    }

    //end of getting the service



    //end of the Methods





    //this is for the Log And Toast

    //this is for the Log
    private static final String TAG = "PostTrackingData";

    //this is for the Log Printing
    public void PrintLog(String Line){ //this is for the Log Printing
        Log.d(TAG,Line);  //this is for the
    }
    //end of the Log Printing
    //end of the Log


    //this is for the Toast
    public void PrintToast(String Line){
        Toast.makeText(context,Line,Toast.LENGTH_SHORT).show(); //this is for the Printing  the Toast
    }
    //end of the Toast
    //end of the Log And Toast







    //this is for the Service Posting
    //this  is for to fetch the  Session
    public String temp_Line_0="", getTemp_Line_1="";
    public void FetchTheSession(){ //this is for to fetch the session
        //this  is for to fetch the session data
        //this  is for the try
        try{



            //this is for to check either online or not
            if(!isOnline()){
                PrintLog("Not Online For Current Service");
                return;
            }
            //end of checking either online or not



            //this is for the Bhatti Parsers
            final BhattiParsers  bhattiParsers=new BhattiParsers(); //this  is for the Bhatti Parsers
            final  BhattiPreferencesManager bhattiPreferencesManager=new BhattiPreferencesManager(context);
            //end of the Bhatti Parsers



            //this is for the Thread
            Thread t=new Thread(new Runnable() {
                @Override
                public void run() {

                    //this is for the try and catch
                    try{//this is for the try Work
                        URL url=new URL(RESTFullApi.GetSessionId(String.valueOf(bhattiPreferencesManager.GetUserID()))); //this is for the url

                        URLConnection urlConnection=url.openConnection(); //this is for to Open the Connection

                        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(urlConnection.getInputStream())); //this is for the bufferedReader


                        String temporaryLine=null; //this is for the temporary Line

                        String line="";

                        //this is for the Line Reading
                        while((temporaryLine=bufferedReader.readLine())!=null){ //this  must not be null
                            line+=temporaryLine;
                        }
                        //end of the Line  Reading



                        bufferedReader.close(); //this is for to close it


                        //this is for the line Printing
                        PrintLog("Session data is as follows "+line+"while id id as follows  "+String.valueOf(bhattiPreferencesManager.GetUserID()));
                        //end of the Line Printing


                        //now sending it to parse
                        final   String session=bhattiParsers.ParseSession(line);
                        //end of parsing



                        //this is for if the session is not null
                        if(session!=null){ //this is for if the session is not null
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    bhattiPreferencesManager.SetUserSession(session);  //this is for to set the session
                                    MakePost(session);
                                    //end  of getting the Party Data
                                }
                            }); //end of the





                        }
                        //end of if the session is not null
                    }//end of the try
                    catch (Exception ex){
                        PrintLog("Exception of type is as follows  "+ex.getMessage());
                    }
                    //end of the try and catch



                }
            });
            t.start();
            //end of Thread





        }//end of the try
        catch (Exception ex){
            PrintLog("Exception of type is  as follows  "+ex.getMessage()+" while fetching the Session Data");
        }//end of the catch
        //end  of the try and catch
        //end of fetching the session data
        }
    //end of fetching the Session
    //end of the Service Posting






    //this is for the method to Make the Post
    public void   MakePost(String session){





        //this is for the Objects
        TrackingData trackingData=new TrackingData(getApplicationContext()); //this is for the Tracking Work
        //end of the Objects


        //this is for the SharedPreference
        BhattiPreferencesManager bhattiPreferencesManager=new BhattiPreferencesManager(context);
        //end of the SharedPreference



        //this is for to Show the Data
        Cursor cursor=trackingData.GetData(Utility.GiveMeDateForTracking());
        cursor.moveToFirst();
        //end of  Going for To Show the Data






        //this is for the json Array
        final JSONArray jsonArray=new JSONArray(); //this is for the json Array
        //end of the josn Array








        //this is for the URL
        String url=RESTFullApi.GetTracking();
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
                    JSONObject jsonObject=new JSONObject(); //this is for the JSOn Object
                    jsonObject.put("sessionID",session);
                    jsonObject.put("salesManID",bhattiPreferencesManager.GetUserID());
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


            //this is for the Request Queue
            RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());

            //end of the Request Queue



            //this is for the string request
            StringRequest stringRequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {




                    //this is for the Bhatti Parsers

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
            PrintLog("Sorry Data  is null for the cursor in Service");
        }
        //end of checking  the cursor






    }
    //end of going for to Make the Post







    //this is for to check either we are online or not
    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }
    //end of checking either we are online or not





}//end of going fcr to Post the Tracking Data
