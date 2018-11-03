package me.kevingleason.mslab.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import me.kevingleason.mslab.Activities.Adapters.PartyAdapter;
import me.kevingleason.mslab.ModelClasses.PartyWork;
import me.kevingleason.mslab.R;

public class PartyActivity extends AppCompatActivity {


    //this is for the fields
    public ListView  partyListView;
    public AutoCompleteTextView  partyAutoComplete;  //this is for the public AutoComplete
    public PartyAdapter partyAdapter;
    //end of the fields


    //this  is for the Lists
    public List<PartyWork>         partyList=new ArrayList<PartyWork>();  //this is for the party Work
    //end of the for th Lists





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party);


        //this is for initializing the Party View
        InitializePartiesView();  //this is for the party View Initialization
        //end of initializing the party View


    }//end of onCreate




    //this is for to initialize the View for the parties
    public void InitializePartiesView(){
        try{

            //this  is for setting the title
            getSupportActionBar().setTitle("Select Party To Place Order");
            //end of setting the title



            //this  is for the List View
            partyListView=(ListView) findViewById(R.id.list);
            partyAdapter=new PartyAdapter(getApplicationContext(),partyList);
            partyListView.setAdapter(partyAdapter);
            //end of the ListView



            //this is for the Party List
            FillPartyList();
            //end of the Party List


           }//end of the try
        catch (Exception ex){
           PrintintLog("Exception of type while going for to initialize the View for Parties "+ex.getMessage());
        }//end of the catch
    }
    //end of Initializing the View of the parties





    //this is for to fill the Party
    public void FillPartyList(){
        try{//this is for the try


            for(int i=0; i<5; i++){

                partyList.add(new PartyWork(10,"MILAT HOMOEO STORE CHINOT BAZAR", "CHINOT BAZAR", "", "", 10, 40, "1212", 1000));

            }//end of the Loop



            partyAdapter.notifyDataSetChanged();


           }//end of the try
        catch (Exception ex){ //this   is for the Exception
            PrintintLog("Exception of type is as follows  "+ex.getMessage()+" while filling the List");
        }//end of the catch
    }
    //end of filling the party




    //this is for the Toast
    public void PrintingToast(String Line){
        Toast.makeText(getApplicationContext(),Line,Toast.LENGTH_SHORT).show(); //this is for the toast Printing
    }
    //end of the Toasting





    //this is for the Log Printing
    private static final String TAG = "PartyActivity";


    //this is for to Print the Log in console for debugging
    public void PrintintLog(String Line){
        Log.d(TAG,Line); //this is for the log
    }
    //end of Printing the Log in the console

    //end of the Log Printing




}//end of PartyActivity
