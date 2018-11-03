package me.kevingleason.mslab.Activities.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import me.kevingleason.mslab.ModelClasses.SavedOrderModal;
import me.kevingleason.mslab.R;

/**
 * Created by Mashhood on 8/10/2018.
 */

public class SavedOrderListAdapter extends ArrayAdapter<SavedOrderModal> {

    //this  is for the fields
    public List<SavedOrderModal> list; //this is for the list for the adapter
    public Context context;  //this is for the context
    //end of  the fields


    //this  is for the Constructor
    public SavedOrderListAdapter(Context context,List<SavedOrderModal> list){ //this is for the list
        super(context,-1,list); //this is for the Super
        this.context=context; //this is for the context
        this.list=list; //this is for the List
    }
    //end of the Constructor


    //this is for to get the View

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //this is for to get the View
        LayoutInflater layoutInflater=(LayoutInflater)  this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);  //this is for the LayoutInflater
        View view=layoutInflater.inflate(R.layout.saved_order,parent,false); //this is for the Layout Inflator
        //end of getting the View



        //this is for the Try catch
        try{


            //there are two textViews
            TextView  serialNumber=(TextView)  view.findViewById(R.id.serialNo); //this is for the serial Number
            TextView  date=(TextView)  view.findViewById(R.id.date); //this is for the date
            //end of the textViews


            //this is for to Get Current
            SavedOrderModal  savedOrderModal= this.list.get(position)  ; //this  is for the savedOrderModal
            //end of getting the Current


            //this is for to fill the View
            serialNumber.setText(String.valueOf(position+1));  //this is for the int for the serial Number
            date.setText(savedOrderModal.getDate());  //this is for the Date
            //end of going for to fill View




            //this  is for to check for the selection
            if(savedOrderModal.isSelected){

                view.setBackgroundColor(Color.LTGRAY);

            }
            else{
                view.setBackgroundColor(Color.TRANSPARENT);
            }
            //end of checking for the selection



           }//end of the try
        catch(Exception ex){ //this is for the catch
           PrintLog("Exception of type is as as follows "+ex.getMessage()+" while getting the View");
        }
        //end of the try catch





        return view;
    }

    //end of going for to get the View




    //this  is for the Log and Toast
    private static final String TAG = "SavedOrderListAdapter";

    //this  is for the Log
    public void PrintLog(String Line){  //this  is for to Print the Line
        Log.d(TAG,Line); //this is for to Print the Line for the Logger
    }
    //end of the Log Printing



    //this is for the Toast
    public void PrintToast(String Line){  //this is for the Toast Printing
        Toast.makeText(this.context,Line,Toast.LENGTH_SHORT).show(); //this is for to show the Toast
    }
    //end of the Toast Printing




    //end of getting the Toast and Log




}//end of the SavedOrderListAdapter
