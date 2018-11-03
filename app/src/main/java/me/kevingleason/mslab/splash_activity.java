package me.kevingleason.mslab;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import me.kevingleason.mslab.UserAuthentication.AuthUser;

public class splash_activity extends AppCompatActivity {

    //this is for the Fields
    public Handler  handler; //this is for the handler
    //end  for the Fields












    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_activity);


        //this is for the initialization of the Work
        Initialization();
        //end  of the initialization of the Work

    }//end of onCreate





    //this is for the initialization
    public void Initialization(){
        try{

            //this is for the handler
            handler=new Handler();  //this is for the handler
            //end of the handler



            //this is for to manage the Threads
            ManageThreads();
            //end of managing the Threads



            }//end of the try
        catch(Exception ex){
            PrintintLog("Exception of type  "+ex.getMessage()+" While  going for to initialize View componnets in "+TAG);
        }//end of the catch
    }
    //end of the initialization






    //this is for to Manage the Threads

    //this is for the Thread Controlling
    public boolean flag=true; //this is for the flag
    public int counter=0; //this  is for the counter
    public int counterLimit=2;
    public Thread t;
    //end of the thread controlling

    public void ManageThreads(){
        try{//this is for the try block





            //This is for the Thread
            t=new Thread(new Runnable() {
                @Override
                public void run() { //this is for the run


                    //this is for the loop to count
                    while(flag){  //this is for the flag

                        try{//this is for the try


                            counter++;


                            if(counter>=counterLimit){ //we need to stop
                                flag=false;


                                //this  is for the handler
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() { //this is for the run
                                        Intent i=new Intent(getApplicationContext(), AuthUser.class); //this is for the Intent
                                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(i);
                                        finish();
                                    }//end of the run
                                });
                                //end of the handler
                            }
                            Thread.sleep(2000);
                           }//end of the try
                        catch (Exception ex){
                            PrintintLog("Exception of type is as follows "+ex.getMessage()+" while loop counter in Thread "+TAG);
                        }//end of the catch




                    }
                    //end of counting the loop



                }//end of the run
            });
            t.start();  //this is for to start it
            //end of the Thread







           }//end of the try
        catch (Exception ex){
            PrintintLog("Exception while managing the threads for the Splash "+ex.getMessage()+"\t "+TAG);
        }//end of the catch
    }
    //end of manging the Threads






    //this is for the Toast
    public void PrintingToast(String Line){
        Toast.makeText(getApplicationContext(),Line,Toast.LENGTH_SHORT).show(); //this is for the toast Printing
    }
    //end of the Toasting





    //this is for the Log Printing
    private static final String TAG = "splash_activity";


    //this is for to Print the Log in console for debugging
    public void PrintintLog(String Line){
        Log.d(TAG,Line); //this is for the log
    }
    //end of Printing the Log in the console


    //end of the Log Printing



}//end of Activity
