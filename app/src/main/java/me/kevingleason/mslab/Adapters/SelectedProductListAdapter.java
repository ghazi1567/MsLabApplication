package me.kevingleason.mslab.Adapters;

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

import me.kevingleason.mslab.ModelClasses.SelectedProducts;
import me.kevingleason.mslab.R;

/**
 * Created by Mashhood on 7/29/2018.
 */

public class SelectedProductListAdapter  extends ArrayAdapter<SelectedProducts>{ //this  is for the Product List Adapter

    //this is for the list of the fields
    public Context context; //this  is for the context
    public List<SelectedProducts> selectedProductList;
    //end of the list of the fields




    //this  is for the constructor
    public  SelectedProductListAdapter(Context context, List<SelectedProducts> list){ //this  is for the SelectedProducts
           super(context,-1,list);  //this is for the constructor
           this.context=context; //this is for the context
           this.selectedProductList=list; //this is for the selected Products  list
    }
    //end  of the constructor





    //this  is for the to get the View

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //this is for the View
        LayoutInflater  layoutInflater= (LayoutInflater)   context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); //this  is for the Layout Inflater
        View view=layoutInflater.inflate(R.layout.selected_items,parent,false); //this is for the Inflator
        //end of the View


        //this  is  for  to get the View
        try{ //this is for to get  the items



            //this  is for to get the View
            TextView  serialNo, partyName, productName, quantity;
            //end of getting the View


            //this  is for to bind the View Components
            serialNo=(TextView)  view.findViewById(R.id.serialNo); //this is for the serial No
            //partyName=(TextView)  view.findViewById(R.id.partyName); //this is for the party name
            productName=(TextView) view.findViewById(R.id.productId); //this is for the product id
            quantity=(TextView)  view.findViewById(R.id.quantityId); //this is for the quantity
            //end of binding the View Components









            //this  is for to bind the data
            SelectedProducts  product=selectedProductList.get(position); //this is for the selected Product List
            //end of the bind the data



            //this is for the flag for the party name
            boolean doPut=true;

            //this is for the loop
            for(int i=0; i<position; i++){ //this is for to check


                     //this  is for to change the flag
                     if(product.getCompanyName().equals(selectedProductList.get(i).getCompanyName())){

                         doPut=false;
                         break;

                     }
                     //end of going for to change the flag

            }
            //end of loop


            //this is for the changes
            if(doPut){
               // partyName.setText(String.valueOf(product.getCompanyName())); //this  is for the party Name
            }
            else {
               // partyName.setText(String.valueOf("")); //this  is for the party Name
            }
            //end of the changes


            //end of the party name for the flag




            //this is for to fill the List
            serialNo.setText(String.valueOf(product.getCounter()));  //this  is for the serial No

            productName.setText(String.valueOf(product.getName()));   //this  is for product Name
            quantity.setText(String.valueOf(product.getQuantity())); //this  is for the quantity
            //end of to fill the List














            //this  is for to check do we need to to change the background
            if(product.isSelected()){
                view.setBackgroundColor(Color.LTGRAY);
            }
            else{
                view.setBackgroundColor(Color.TRANSPARENT);
            }
            //end  of checking for do need to change the background





           } //end of the try
        catch (Exception ex){
            PrintLog("Exception of type  is as follows  "+ex.getMessage()+" while inflating the Layout");
        }
        //end   of  getting  the View


        return view;
    }
    //end  of getting the View






    //this  is for the Log and Toast
    //this is for the Log
    private static final String TAG = "SelectedProductListAdap";

    public void PrintLog(String Line){//this  is for the Log
        Log.d(TAG,Line); //this  is for the Log
    }//end of the Printing the Log
    //end of the Log

    //this is for the Print Toast
    public void PrintToast(String Line){//this is for the Toast
        Toast.makeText(context,Line,Toast.LENGTH_SHORT).show(); //this is for to show the context
    }
    //end  of the Toast
    //end  of the Log and Toast



} //end of the SelectedProductList
