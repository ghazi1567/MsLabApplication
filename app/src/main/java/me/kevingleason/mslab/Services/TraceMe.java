package me.kevingleason.mslab.Services;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import me.kevingleason.mslab.DatabaseWork.TrackingData;
import me.kevingleason.mslab.ModelClasses.Tracking;
import me.kevingleason.mslab.Utility.Utility;

public class TraceMe extends Service
        implements LocationListener
    {
    public TraceMe() {
    }



    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }//end of the IBinder


    //this is for the Previous Values
    public double latitude, longitude;  //this is for the Latitude &  Longitude
    //end of the Previous Values


    //this is for the Location Listener

    @Override
    public void onLocationChanged(Location location) {
        CheckAndUpdateDatabase(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    //end of the Location Listener


    //this is for  the mechanism to update the database


    public void CheckAndUpdateDatabase( final Location location) {
        try {//this is for the try

            if (latitude != location.getLatitude() || longitude != location.getLongitude()) {  //this  is for the if
                latitude = location.getLatitude();   //this is for the Latitude
                longitude = location.getLongitude();  //this is for the Longitude


                //this is for to Show the Location
                PrintLog("Latitude" + latitude + "  , Longitude" + longitude);
                //end of To Show



                //this is for the  Going for to Store the Data
                TrackingData  trackingData=new TrackingData(getApplicationContext()); //this is for the Tracking Data
                trackingData.InsertValues(new Tracking(latitude+","+longitude, Utility.GiveMeDateTimeForTrackingInsetion()));
                //end of  Going for To Store the Data




            } //end of the if


        }//end of the try
        catch (Exception ex) { //this  is for the catch
            PrintLog("Exception of type is as follows " + ex.getMessage() + " while Checking for update");
        }//end of the catch
    }
    //end of updating the database


    //this is for the Log and Toast
    private static final String TAG = "TraceLocationForUser";

    public void PrintLog(String Line) { //this is for the Line
        Log.d(TAG, Line); //this is for the Log
    }//end of the PrintingLog

    public void PrintToast(String Line) { //this is for the Toast
        Toast.makeText(getApplicationContext(), Line, Toast.LENGTH_SHORT).show(); //this is for to show
    } //end  of the Log and Toast


    //this is for to change the Location Manager
    public LocationManager locationManager=null; //this is for the location Manager

    public void InitializeManager() {  //this  is for the LocationManager
        if (locationManager == null) { //this is for to considering the update
            locationManager = (LocationManager) getApplicationContext().getSystemService(getApplicationContext().LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }

        }//end of considering for to update
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 0, this);
    }
    //end of the Location Manager




    //this is for to place the method for the Work

    @Override
    public void onCreate() {
        super.onCreate();  //this is for onCreate
        //this is for to initialize the onCreate
        InitializeManager();  //this is for Initialization of the Manager
    }
    //end of the Placement of the method for the Work







    //this is  for to start the  service
    public  static    boolean flag=true;
    public int counter=0;
    @Override
    public int onStartCommand(Intent intent, final int flags, int startId) {


       PrintLog("From Inside");


        //this is for the Thread
        Thread t=new Thread(new Runnable() {  //this is for the runnable
            @Override
            public void run() { //this is for the run


                //this is for the Loop
                try{//this is for the try
                    while(flag){//this is for the while Loop
                        flag=GiveMeSharedPreference();
                        counter++;
                        PrintLog("GPS Counter"+counter);
                        Thread.sleep(3000);
                    }//end of the while Loop
                }  //end of the try
                catch (Exception ex){
                    PrintLog("Exception of type is as follows  "+ex.getMessage());
                }
                //end of the Loop



            }//end of the run
        }); //this is for the Thread
        t.start();
        //end of the Thread





        return START_STICKY;  //this is for to go back
    }

    //end of starting the service




    //this is for to check





    //this is for getting  the sharedPreference
    boolean GiveMeSharedPreference(){
        boolean value=flag;
        return  value;
    }
    //end of the getting the sharedPreference






    //this is for on destroy and for going to remove the Work
    @Override
    public void onDestroy() {
        super.onDestroy();
        //this is for to unregister
        locationManager.removeUpdates(this);
        PrintLog("Location Service has been stopped");
        //end of going to unregister
    }
    //end  for going to remove the Work

}
