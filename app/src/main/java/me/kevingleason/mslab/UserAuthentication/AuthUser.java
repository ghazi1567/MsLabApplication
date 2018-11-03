package me.kevingleason.mslab.UserAuthentication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import me.kevingleason.mslab.Activities.one;
import me.kevingleason.mslab.BhattiParsers.BhattiParsers;
import me.kevingleason.mslab.BhattiSharedPreferencesManager.BhattiPreferencesManager;
import me.kevingleason.mslab.DatabaseWork.UserWork;
import me.kevingleason.mslab.ModelClasses.User;
import me.kevingleason.mslab.R;
import me.kevingleason.mslab.RestfullApi.RESTFullApi;

public class AuthUser extends AppCompatActivity {


    //this is for the Progress Dialog
    public ProgressDialog  progressDialog;
    //end of the progress Dialog


    //this is for the handler
    public Handler handler;
    //end of handler



    //this is for the Databse
    public UserWork  userWork;
    //end of the database




    //this is for to manage  the progressDialog
    public void ShowProgressDialog(String line){
        if(progressDialog!=null){
             progressDialog.setMessage(line);
             progressDialog.show();
        }
    }//end of going for to show


    //this is for to hide the Progress Dialog
    public void HideProgressDialog(){
         if(progressDialog!=null){
             progressDialog.dismiss(); //this is to terminate the progress dialog
         }//end of the if
    }
    //end of going for to hide the Progress Dialog



    //end of managing the Progress Dialog




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_user);


        //this  is for the handler
        handler=new Handler();  //this is for the handler
        //end of handler


        //this is for to initialize the View
        initializeView();
        //end of the initialization of the View


    }//end of onCreate




    //this is for the Fields
    public TextView  authMotive;  //this is for the   auth upper text
    public Typeface forAuthMotive;  //this is for the Auth Motive style
    public Context ctx;
    //end of for the fields




    //this  is for the edit Text
    public EditText email, password;
    //end of EditText





    //this is for the initialization of the View
    public void initializeView(){
         try{



             //this is for the user Database Sign
             userWork=new UserWork(getApplicationContext()); //this is for the user Work
             //end of the user database Sign in




             //this is for to get the context
             this.ctx=getApplicationContext();
             progressDialog=new ProgressDialog(AuthUser.this); //this is for the Progress Dialog
             //end of getting the Context






             //this is for initialization the Auth Motive
             //authMotive=(TextView) findViewById(R.id.authMotive);
             //forAuthMotive=Typeface.createFromAsset(getApplicationContext().getAssets(),"hero.ttf");
             //authMotive.setTypeface(forAuthMotive);
             //end of initialization of the Auth motive




             //this  is for the EditText For the email and Password
             email=(EditText) findViewById(R.id.userName); //this is for the email
             password=(EditText) findViewById(R.id.userPassword); //this is for the user password
             //end of the EditText for the email and Password




            }//end of the try
         catch (Exception ex){
             PrintintLog("Exception of type is  as follows "+ex.getMessage()+" While initialization of the View "+TAG);
         }//end of the catch
    }
    //end of the initialization of the View






    //this is for the  checkBox
    public void PasswordCheckBox(View view){


        try{ //this is for the try

            //this is for to get the checkBox
            CheckBox    passwordCheckBox=(CheckBox)  view;




                  //this is for to check either checkbox is selected or not
                  if(passwordCheckBox.isChecked()){
                        passwordCheckBox.setText("Hide Password");
                        password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                  }
                  else{
                      passwordCheckBox.setText("Show Password");
                      password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                  }
                  //end of checking eithe checkbox is selected or not








            //end of getting the checkBox
           }//end of the try
        catch (Exception ex){
            PrintintLog("Exception of type is  as follows  "+ex.getMessage()+" while managing the Check Box Work");
        }//end of the catch



    }
    //end of the checkBox








       //this is for to check either we are online or not
       public boolean isOnline() {
           ConnectivityManager cm = (ConnectivityManager) this.ctx.getSystemService(this.ctx.CONNECTIVITY_SERVICE);
           return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnectedOrConnecting();
       }
       //end of checking either we are online or not






       //this is for the button click
       public void SignIn(View view){


           String name=email.getText().toString();
           String password_=password.getText().toString();


           if( (!name.isEmpty())  &&  (!password_.isEmpty()) ){
                 MakeUserSignIn(name,password_);  //this is for making user sign in
           }//end of the if
           else{//end of the else part
                PrintingToast("Please Provide  User name and Password");
           }//end of the else part



       }
       //end of the button click






       //this is for to make the user sign in
       public String lines="";
       public void MakeUserSignIn(final String name, final String password){ //this  is for Making user Sign in


           //this is for to handle the Exception
           try{//this is for the try



               //this is for to check for user either online or offline
               if(isOnline()){


                  //this is for to show the dialog
                   ShowProgressDialog("Processing for Sign in...");
                  //end of showing the dialog


                   //this is a time for Volley
                   Thread  t=new Thread(new Runnable() {
                       @Override
                       public void run() {


                           //this is for the inner work
                           try{

                               URL  url=new URL(RESTFullApi.GetUrlAuth(name,password));
                               URLConnection  urlConnection=url.openConnection();



                               //this is for the field variables
                               lines="";
                               String temp=null;
                               //end of the field variables


                               BufferedReader  bufferedReader=new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));  //this is for my buffered Input Stream




                               //this is for the Loop
                               while((temp= bufferedReader.readLine())!=null){
                                      lines +=temp;  //this  is for the lines
                               }
                               //end of the Loop






                               //this is for to finish  the stream reading
                               bufferedReader.close();
                               //end of finishing the stream reading




                               //this is for going to   call response Print
                               handler.post(new Runnable() {
                                   @Override
                                   public void run() {
                                        HideProgressDialog();
                                        ProcessServerResponse(lines);
                                   }
                               });
                               //end of going for to call response Print




                              }//end of the try
                             catch (Exception ex){
                             PrintintLog("Exception of type is as follows "+ex.getMessage()+" while Processing the Threads");
                             HideProgressDialog();
                              }
                           //end of the inner work






                       }//end of the run
                   });
                   t.start();  //this  is for to start the Thread
                   //end of the request Queue



               }
               //end of checking either online or offline

               else{ //this is for the offline





                   //this is for to work in offline mode
                   try{
                       Cursor   user_data=userWork.GetData(name,password);


                       if(user_data!=null){//this is for the user data




                          }//end of the if Work
                       else{//this is for to check things
                           PrintingToast("Please get Internet Connection to Get you Signed In Since System does not have you information");
                           HideProgressDialog();
                           return;
                       }//end of check things






                       //this is for to get the number of the rows
                       int numberOfRows=user_data.getCount();  //this is for counting the number of rows
                       //end of number of the rows



                       //this is for to decide
                       if(numberOfRows==0){
                           PrintingToast("Username or password is incorrect. Please connect internet");
                           return;
                       }
                       //end of going for to decide





                       if(user_data!=null){





                            //this is for to get the user data
                           try{


                               //this is for move to first
                               user_data.moveToFirst();


                               //this  is for the temporary User
                               User  temporary_user=null;
                               //end of the temporary User


                               //this is for looping
                               for(int i=0; i<user_data.getCount();i++){ //this is for to decide



                                   user_data.move(i);


                                   //this is for to extract the cursor data
                                   temporary_user=new User();
                                   temporary_user.setId(user_data.getInt(user_data.getColumnIndex(UserWork.id)));
                                   temporary_user.setName(user_data.getString(user_data.getColumnIndex(UserWork.name)));
                                   temporary_user.setAddress(user_data.getString(user_data.getColumnIndex(UserWork.address)));
                                   temporary_user.setCode(user_data.getString(user_data.getColumnIndex(UserWork.code)));
                                   temporary_user.setContactPerson(user_data.getString(user_data.getColumnIndex(UserWork.contactPerson)));
                                   temporary_user.setLoginName(user_data.getString(user_data.getColumnIndex(UserWork.loginName)));
                                   temporary_user.setMobile(user_data.getString(user_data.getColumnIndex(UserWork.mobile)));
                                   temporary_user.setMobileAlternate(user_data.getString(user_data.getColumnIndex(UserWork.mobileAlternate)));
                                   temporary_user.setPassword(user_data.getString(user_data.getColumnIndex(UserWork.password)));
                                   //end of getting the data from cursor








                               } //end of the looping



                               //end  of moving to the first





                               //this is for to Print the Current User
                                HideProgressDialog();
                                PrintingToast("You have successfully logged in as "+temporary_user.getName());
                               //end of Printing the Current User




                                //this is for to place data in SharedPreferences
                               //this  is for to set the user id
                               BhattiPreferencesManager bhattiPreferencesManager=new BhattiPreferencesManager(getApplicationContext());
                               bhattiPreferencesManager.SetUserID(temporary_user.getId());
                               bhattiPreferencesManager.SetUserLogin(true);
                               //end of going for to set the user id
                                //end of placing data in SharedPreferences







                               //this is for to load the main activity
                               LoadMainActivity();
                               //this is for to load the Main Activity




                              }//end of the try
                           catch (Exception ex){
                              PrintintLog("Exception of type "+ex.getMessage()+" While going for to get user data ");
                               HideProgressDialog();
                           }
                            //end of going for to get the user data






                       }
                       else{
                           PrintingToast("Sorry Provided Data is fake");
                           HideProgressDialog();
                       }//end of the else part



                      }//end of the try
                   catch (Exception ex){
                       HideProgressDialog();
                       PrintintLog("Exception of type is as follows  while going for  parse the cursor "+ex.getMessage());
                   }
                   //end of going for offline mode




               }//end of the offline









              }//end of the try
           catch (Exception ex){
              PrintintLog("Exception of type is as follows "+ex.getMessage()+" while going for to make the user sign in");
              HideProgressDialog();
           }
           //end of handling the Exception


       }
       //end of going for to make the user sign in








    //this is for to manage the Response
    public void ProcessServerResponse(String Line){
        PrintintLog("The line is as follows   \n"+Line);


        //this is for to Parse And get the user
        User  user=new BhattiParsers().GiveMeUser(Line);
        //end of Parsing and Getting the user



        //this is for checking the user  value
        if(user!=null){


            PrintingToast("You have loged in  as  "+user.getName());




            //this is for  to synch with Database
            userWork.SysncWithDataBase(user);
            //end of sync with database




            //this  is for to set the user id
            BhattiPreferencesManager bhattiPreferencesManager=new BhattiPreferencesManager(getApplicationContext());
            bhattiPreferencesManager.SetUserID(user.getId());
            bhattiPreferencesManager.SetUserLogin(true);
            //end of going for to set the user id



            //this is for to load the main activity
            LoadMainActivity();
            //this is for to load the Main Activity






        }//it means user is not null
        else{ //it means user is null
            PrintingToast("Sorry Provided Information is Fake");
            HideProgressDialog();
        }
        //end of checking the user






    }
    //end of managing the response








    //this is for the Toast
    public void PrintingToast(String Line){
        Toast.makeText(getApplicationContext(),Line,Toast.LENGTH_SHORT).show(); //this is for the toast Printing
    }
    //end of the Toasting





    //this is for the Log Printing
    private static final String TAG = "AuthUser";


    //this is for to Print the Log in console for debugging
    public void PrintintLog(String Line){
        Log.d(TAG,Line); //this is for the log
    }
    //end of Printing the Log in the console

    //end of the Log Printing




    //this is for to move the user to the next activity that is the main activity
    public void LoadMainActivity(){
        Intent  i=new Intent(getApplicationContext(), one.class); //this is for the intent
        startActivity(i);  //this is for to start the activity
        finish();
    }
    //end of moving the user to the next activity named as the main activity




    //this is for to check the user status
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

        }
        else{
            LoadMainActivity();

        }
        //end of the of the user to check


    }

    //end of the area where we want to check
    //end of checking for the user status





}//end of the AuthUser
