package me.kevingleason.mslab.Activities.Adapters;

import android.content.Context;
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

import me.kevingleason.mslab.ModelClasses.PartyWork;
import me.kevingleason.mslab.R;

/**
 * Created by Mashhood on 7/2/2018.
 */

public class PartyAdapter  extends ArrayAdapter<PartyWork> {//this is for the Party Adapter

    //this  is for the Party Adapter Fields
     public Context  context;
     public List<PartyWork> list; //this is for the party Work
    //end of the Party Adapter Fields





    //this  is for the Constructor
    public PartyAdapter (Context ctx, List<PartyWork> itemList) {
        super(ctx,-1,itemList);
        this.list=itemList;
        this.context=ctx; //this is for the Context
    }
    //end of the Constructor




    //this is for the inflating the Layout

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        //this  is for to inflate the Layout
        LayoutInflater inflater=(LayoutInflater)  this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); //this is for the LayoutInflator
        View  view= inflater.inflate(R.layout.party_item,parent,false);
        //end of inflating the Layout



        //this   is for the to get the Items from the Layout
        try{

            TextView  name, address, contactPersonName, mobile, discount;



            //this is for the initialization
            name=(TextView) view.findViewById(R.id.partyName);
            address=(TextView) view.findViewById(R.id.address);
            contactPersonName=(TextView) view.findViewById(R.id.personContact);
            mobile=(TextView) view.findViewById(R.id.mobile);
            discount=(TextView) view.findViewById(R.id.discount);
            //end of the initialization





            //this is for to Pick one Item
            PartyWork p=list.get(position); //this is for to get the current item
            //end of getting the One Item



            //this is for setting the Item
            name.setText(p.getName());
            address.setText("Address\t:"+p.getAddress());
            contactPersonName.setText("Contact Person:\t"+p.getContactPerson());
            mobile.setText("Mobile:\t"+p.getMobile());
            discount.setText("Discount:\t  "+p.getDiscountPercentage()+" %");
            //end of setting the Item





          }//end of the try
        catch (Exception ex){
          PrintintLog("Exception of type  is as follows  "+ex.getMessage()+" while going to  place the values in Layout");
        }//end of the catch
        //end of getting the Items From the Layout






        return  view; //this is for going to return the View
    }

    //end of  Inflating the Layout





    //this is for the Toast
    public void PrintingToast(String Line){
        Toast.makeText(context,Line,Toast.LENGTH_SHORT).show(); //this is for the toast Printing
    }
    //end of the Toasting





    //this is for the Log Printing
    private static final String TAG = "PartyAdapter";


    //this is for to Print the Log in console for debugging
    public void PrintintLog(String Line){
        Log.d(TAG,Line); //this is for the log
    }
    //end of Printing the Log in the console

    //end of the Log Printing





}//end of the Party Adapter
