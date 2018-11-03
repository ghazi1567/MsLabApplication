package me.kevingleason.mslab.Activities;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import java.util.ArrayList;
import java.util.List;

import me.kevingleason.mslab.Activities.Adapters.MainMenuItemAdapter;
import me.kevingleason.mslab.BhattiSharedPreferencesManager.BhattiPreferencesManager;
import me.kevingleason.mslab.ModelClasses.MainMenuItem;
import me.kevingleason.mslab.R;
import me.kevingleason.mslab.Services.PostTrackingData;
import me.kevingleason.mslab.Services.TraceMe;

public class one extends AppCompatActivity
    implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener

   {


    //this is for the View components
    public ListView  listView; //this  is for the ListView
    public MainMenuItemAdapter  mainMenuItemAdapter; //this is for the main menu adapter
    public List<MainMenuItem>  mainMenuItems=new ArrayList<MainMenuItem>(); //this is for the main menu items
    public TextView  titleText; //this is for the title text
    //end of the view components



    //this is for to initialize
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void Initialize(){
        try{//this is for the try








            //this is for to set title
            getSupportActionBar().setTitle("Menu");
            ActionBar  actionBar=getSupportActionBar();  //this is for the actionBar
            actionBar.setBackgroundDrawable(new Drawable() {
                @Override
                public void draw(@NonNull Canvas canvas) {
                      canvas.drawColor(Color.rgb(63, 81, 181));
                }

                @Override
                public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {

                }

                @Override
                public void setColorFilter(@Nullable ColorFilter colorFilter) {

                }

                @Override
                public int getOpacity() {
                    return PixelFormat.TRANSPARENT;
                }
            });
            //end of  setting the title







            //this is  for the title text
            titleText=(TextView) findViewById(R.id.titleText); //this is for the title text
            Typeface  typeFace=Typeface.createFromAsset(getApplicationContext().getAssets(),"precious.ttf");
            //titleText.setTypeface(typeFace);  //this is for the title
            //end of the title text





            //this is for to add the items
            mainMenuItems.add(new MainMenuItem(R.drawable.menu_item_background,"New Order",CartActivity.class,R.drawable.make_orders_click));
            mainMenuItems.add(new MainMenuItem(R.drawable.menu_item_background,"Pending Orders",SycnchedOrders.class,R.drawable.pending_orders_click));
           // mainMenuItems.add(new MainMenuItem(R.drawable.make_orders,"Make Product Order",CartActivity.class,R.drawable.make_orders_click));
            //mainMenuItems.add(new MainMenuItem(R.drawable.pending_orders,"Pending Orders",SycnchedOrders.class,R.drawable.pending_orders_click));
            //end of adding the items



            //this is for the ListView
            listView=(ListView) findViewById(R.id.list);
            mainMenuItemAdapter=new MainMenuItemAdapter(getApplicationContext(),mainMenuItems);
            listView.setAdapter(mainMenuItemAdapter); //this is for the main menu adapter
            //end of the ListView


           }//end of the try
        catch (Exception ex){
           PrintingLog("Exception of type is  as follows "+ex.getMessage());
        }//end of the catch
    }
    //end of initializing


















    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);




        //this is for to initialize
        Initialize(); //this is for the initialize the View components
        //end of the initialize

    }//end of the create




    //this is for the log printing
    private static final String TAG = "one";

    //this is for the log printing
    public void PrintingLog(String Line){
        Log.d(TAG,Line); //this is for the Log
    }
    //end of the log printing


    //this is for to Printing the Toast
    public void PrintingToast(String Line){
        Toast.makeText(getApplicationContext(),Line,Toast.LENGTH_SHORT).show(); //this is for to show the toast
    }
    //end of printing the Toast

    //end of the log printing



    //this is for to inflate the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);

        return true;
    }
    //end of inflating the menus




    //this is  for the menu options click listener

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //this is for to check that item that which one has been selected
        int itemId=item.getItemId(); //this is for to  get the item id


        //this is for to check on Which the click is made
        if(itemId==R.id.logOut){ //this is for the log out


            //this is for to make him logout
            AlertDialog.Builder builder=new AlertDialog.Builder(one.this);  //this  is for the alert dialog builder
            builder.setIcon(R.drawable.logo); //this  is for the logo
            builder.setTitle("Confirmation"); //this is for the title
            builder.setMessage("Are you sure to log out"); //this is for to log out
            builder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {//this is for onClick
                          MakeUserLogout();  //this is for to make the user log out
                }//end of onClick
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                           PrintingToast("Logging you  out has been cancelled");
                }
            });
            //end of making him the log out



            //this is for the logout
            builder.show();
            //end of the logout




        }
        //end of checking that on which click is made










        //end of the menu item selected

        return super.onOptionsItemSelected(item);
    }

    //end  of the menu options click listener





    //this is  the area we need to check

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onStart() {
        super.onStart();

        //here we will check what we  have to check
        BhattiPreferencesManager bhattiPreferencesManager=new BhattiPreferencesManager(getApplicationContext());

        //this is for to get the user data
        int userId=bhattiPreferencesManager.GetUserID(); //this is for to get the user id
        boolean  userStatus=bhattiPreferencesManager.GiveUserState();
        //end of getting the user data
        //end of checking what we have to check


        //this is for to check for the user
        if(userId==-1){
            PrintingToast("You have to login First"); //this is for to check for it that we have to login first
            finish(); //this is for to finish activity
        }
        else{
            PrintingToast("Welcome To MS Lab Main Activity");
        }
        //end of the of the user to check


        ManageService();




        //this is for to   run the Service
        PostTrackingData.flag=true;
        Intent  i=new Intent(getApplicationContext(), PostTrackingData.class); //this is for to Start the Service
        startService(i); //this is for to start the Service
        //end of going for to run the Service




    }

    //end of the area where we want to check




    //this is for to make the user logout
    public void MakeUserLogout(){
        BhattiPreferencesManager bhattiPreferencesManager=new BhattiPreferencesManager(getApplicationContext());
        bhattiPreferencesManager.SetUserID(-1);
        bhattiPreferencesManager.SetUserLogin(false);
        finish();
        PrintingToast("Log out Successfully");
    }
    //end of the user logOut









    //this is for  All About the service
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void ManageService(){

        //this is for the try and catch
        try{ //this is for the try


            //this is for to check the service
            if(CheckPermission(Manifest.permission.ACCESS_FINE_LOCATION)  &&  CheckPermission(Manifest.permission.ACCESS_COARSE_LOCATION)){
                mGoogleApiClient = new GoogleApiClient.Builder(this)
                        .addApi(LocationServices.API)
                        .addConnectionCallbacks(this)
                        .addOnConnectionFailedListener(this).build();
                mGoogleApiClient.connect();
            }
            else{


                //this is for to get the Permission
                if(!CheckPermission(Manifest.permission.ACCESS_FINE_LOCATION)){
                    this.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
                }
                if(!CheckPermission(Manifest.permission.ACCESS_COARSE_LOCATION)){
                    this.requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},2);
                }
                //end of getting the Permission


            }
            //end of checking for the service





           }//end of the try
        catch(Exception ex){ //this  is for the catch
              PrintingLog("Exception of type is as follows  "+ex.getMessage()+" while going for to manage the Service"); //this is for the Log
        }
        //end of the try and catch

    }
    //end of All About the Service






    //this is for to get Boolean Value on Permission Name
    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean CheckPermission(String permission){
        boolean  value=false;

        //this is for the permission
        if(getApplicationContext().checkSelfPermission(permission)== PackageManager.PERMISSION_GRANTED){
            return  true;
        } //this is for the Permission

        return  value;
    }
    //end of getting the Boolean Value on Permission Name




     //this is for the Permission Request

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(CheckPermission(Manifest.permission.ACCESS_FINE_LOCATION)  &&  CheckPermission(Manifest.permission.ACCESS_COARSE_LOCATION)){
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this).build();
            mGoogleApiClient.connect();
        }
        else{

            //this is for to get the Permission
            if(!CheckPermission(Manifest.permission.ACCESS_FINE_LOCATION)){
                this.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            }
            if(!CheckPermission(Manifest.permission.ACCESS_COARSE_LOCATION)){
                this.requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},2);
            }
            //end of getting the Permission


        }
        //end of checking for the service

    }



       //end of Request Permission





       //this is for the Access Location
       public   LocationRequest mLocationRequest;
       public   GoogleApiClient mGoogleApiClient;
       public   PendingResult<LocationSettingsResult> result;
       public   final static int REQUEST_LOCATION = 199;
       //end of the Access Location





       //this is for the Access Methods Work
       @Override
       public void onConnected(Bundle bundle) {


           mLocationRequest = LocationRequest.create();
           mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
           mLocationRequest.setInterval(30 * 1000);
           mLocationRequest.setFastestInterval(5 * 1000);

           LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                   .addLocationRequest(mLocationRequest);
           builder.setAlwaysShow(true);

           result = LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, builder.build());

           result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
               @Override
               public void onResult(LocationSettingsResult result) {
                   final Status status = result.getStatus();
                   //final LocationSettingsStates state = result.getLocationSettingsStates();
                   switch (status.getStatusCode()) {
                       case LocationSettingsStatusCodes.SUCCESS:
                           // All location settings are satisfied. The client can initialize location

                           // requests here.
                           //...
                           break;
                       case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                           // Location settings are not satisfied. But could be fixed by showing the user
                           // a dialog.
                           try {
                               // Show the dialog by calling startResolutionForResult(),
                               // and check the result in onActivityResult().
                               status.startResolutionForResult(
                                       one.this,
                                       REQUEST_LOCATION);
                           } catch (IntentSender.SendIntentException e) {
                               // Ignore the error.
                           }
                           break;
                       case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                           // Location settings are not satisfied. However, we have no way to fix the
                           // settings so we won't show the dialog.
                           //...
                           break;
                   }
               }
           });

       }

       @RequiresApi(api = Build.VERSION_CODES.M)
       @Override
       public void onActivityResult(int requestCode, int resultCode, Intent data)
       {
           PrintingLog("The Result Code is as Follows "+Integer.toString(resultCode));

           //final LocationSettingsStates states = LocationSettingsStates.fromIntent(data);
           switch (requestCode)
           {
               case REQUEST_LOCATION:
                   switch (resultCode)
                   {
                       case Activity.RESULT_OK:
                       {
                           // All required changes were successfully made

                           //this is for  that Location is Enabled
                           Intent i=new Intent(getApplicationContext(), TraceMe.class); //this is for the intent
                           TraceMe.flag=false;
                           stopService(i);
                           TraceMe.flag=true;
                           startService(i); //this is for to start the service
                           //end of the Location is Enabled


                           break;
                       }
                       case Activity.RESULT_CANCELED:
                       {
                           // The user was asked to change settings, but chose not to
                           ManageService();
                           //This  is for that It is cancelled

                           break;
                       }
                       default:
                       {

                           break;
                       }
                   }
                   break;
           }
       }

       @Override
       public void onConnectionSuspended(int i) {

       }

       @Override
       public void onConnectionFailed(ConnectionResult connectionResult) {

       }
       //end of the Access Methods Work







       //this is for the Start And Stop




       @Override
       protected void onDestroy() {
           super.onDestroy();


           //this is for to   run the Service
           PostTrackingData.flag=false;
           Intent  i=new Intent(getApplicationContext(), PostTrackingData.class); //this is for to Start the Service
           stopService(i); //this is for to start the Service
           //end of going for to run the Service

       }
       //end of the Start And Stop





}//end of
