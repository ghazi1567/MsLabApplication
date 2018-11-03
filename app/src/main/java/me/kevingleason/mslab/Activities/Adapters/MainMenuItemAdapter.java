package me.kevingleason.mslab.Activities.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import me.kevingleason.mslab.ModelClasses.MainMenuItem;
import me.kevingleason.mslab.R;

/**
 * Created by Mashhood on 7/20/2018.
 */

public class MainMenuItemAdapter extends ArrayAdapter<MainMenuItem> { //this  is for the main menu Item adapter

    //this is for the fields
    public Context ctx;    //this  is for the context
    public List<MainMenuItem>   list; //this is for the list
    //end of the fields



    //this is for the constructor
    public MainMenuItemAdapter(Context ctx, List<MainMenuItem> list){ //this is for  the list
           super(ctx,-1,list); //this is for the super
           this.ctx=ctx; //this is for the context
           this.list=list; //this is for the list
    }
    //end of the constructor




    //this is for to get the View

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //this is for the LayoutInflator
        LayoutInflater  layoutInflater=(LayoutInflater) this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);  //this is for to get  the Layout Inflator
        //end of the LayoutInflator


        //this is for to inflate the View
        View view=layoutInflater.inflate(R.layout.main_menu_item_activity,parent,false); //this is for to get the view
        //end  of going for to inflate the layout




        //this is for to run the View
        try{ //this is for the try

            ImageView  imageView; //this is for the imageView
            TextView   textView; //this is for the textView



            //this is for to initialize the View
            imageView=(ImageView) view.findViewById(R.id.image) ;//this is for the imageView
            textView =(TextView) view.findViewById(R.id.textView);
            //end of the initializing the view





            //this is for to to get the position
            MainMenuItem  mainMenuItem=list.get(position);
            //imageView.setImageResource(mainMenuItem.getImageId()); //this is for the image
            textView.setText(mainMenuItem.getText());
            //end of getting the position




            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {//this is for the click Listener


                    //this is for the click Listener
                    if(list.get(position).getClass_()!=null){
                        Intent i=new Intent(ctx,list.get(position).getClass_()); //this is for the intent
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //this is for the new task
                        ctx.startActivity(i);  //this is for to start the new activity
                    }
                    else{
                        PrintToast("The is in development Phase");
                    }
                    //end of the click listener



                  } //end of onClickListener
            });





           }//end of the try
        catch (Exception ex){//this is for the exception
            LogPtinting("Exception of type is as follows "+ex.getMessage()); //this is for to Print  the log
        }
        //end of going for to run the view




        return view;
    }

    //end  of getting the View









    //this is for the log and Toast printing
    private static final String TAG = "MainMenuItemAdapter";


    //this is for the Toast Printing
    public void PrintToast(String Line){
        Toast.makeText(ctx,Line,Toast.LENGTH_SHORT).show(); //this is for to show the toast
    }
    //end of the Toast Printing





    //this is for the Log printing
    public void LogPtinting(String Line){
        Log.d(TAG,Line); //this is for the Log
    }
    //end of the log printing



    //end of the log and Toast Printing




}//end of the main menu adapter
