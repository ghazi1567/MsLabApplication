package me.kevingleason.mslab.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
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
import java.util.ArrayList;
import java.util.List;

import me.kevingleason.mslab.Activities.Adapters.SavedOrderListAdapter;
import me.kevingleason.mslab.Adapters.SelectedProductListAdapter;
import me.kevingleason.mslab.BhattiParsers.BhattiParsers;
import me.kevingleason.mslab.BhattiSharedPreferencesManager.BhattiPreferencesManager;
import me.kevingleason.mslab.DatabaseWork.Orders;
import me.kevingleason.mslab.DatabaseWork.ProductDatabase;
import me.kevingleason.mslab.ModelClasses.SavedOrderModal;
import me.kevingleason.mslab.ModelClasses.SelectedProducts;
import me.kevingleason.mslab.R;
import me.kevingleason.mslab.RestfullApi.RESTFullApi;

public class SycnchedOrders extends AppCompatActivity {


    //this is for the list of the Fields
    public Button  update,remove,hideButton; //this is for the buttons
    public TextView  selection_counter; //this is for the selectionCounter
    public ListView  mainListForSavedOrders,  listForDetail; //this is for the List
    //end of the list of the Fields




    //this is for the Lists for the Adapter
    public List<SelectedProducts>   selectedProducts=new ArrayList<SelectedProducts>(); //this  is for  the selected Products Lists
    public List<SavedOrderModal>    savedOrderModalList=new ArrayList<SavedOrderModal>(); //this   is for the Saved Order Form the database
    //edn of the Lists for  the




    //this is for the adapters  for the list for the Work
    public SelectedProductListAdapter selectedProductListAdapter; //this is for the adapter for the selectedListAdapter
    public SavedOrderListAdapter  savedOrderListAdapter; //this is for those adapter
    //end of the  adapters for the list for the Work



    //this is for the OverView
    public LinearLayout overViewContainer;
    //end of the OverView




    //this is for the pointer to point the points
    public int selectionPointer=-1; //this is for the current pointer to which we have to point
    //end of the pointer to point the current selection



    //this is for Bhatti SharedPreference
    public BhattiPreferencesManager bhattiPreferencesManager; //this is for the Bhatti SharedPreference
    //end of the Bhatti SharedPreference


    //this is for to initialize Views
    public void InitializeView(){

        //this is for to initialize View
        try{

                //this is for the Bhatti SharedPreference
                bhattiPreferencesManager=new BhattiPreferencesManager(getApplicationContext()); //this is for the Bhatti SharedPreference
                //end of the Bhatti SharedPreference


                 //this is for the initialization of the Buttons
                 update=(Button)  findViewById(R.id.updateButton);
                 remove=(Button)  findViewById(R.id.btn_remove);
                 hideButton=(Button)  findViewById(R.id.hideButton);
                 //end of the initialization of the Buttons

                 //this  is for the selection
                 selection_counter=(TextView) findViewById(R.id.itemCounter); //this is for the selectionCounter
                 //end of the selection


                //this is for the List Work
                mainListForSavedOrders=(ListView) findViewById(R.id.listView); //this is for the main List for the saved List
                listForDetail=(ListView)  findViewById(R.id.listView_1); //this is for the list View for the detail Work
                //end of the List Work for the initialization for the best Work



                //this  is for the list of the adapters for  the MultiList
                savedOrderListAdapter=new SavedOrderListAdapter(getApplicationContext(),savedOrderModalList); //this is for the savedOrderListAdapter
                //end of the adapters for the MultiList Work as it should be in that content


                //this   is for the List View for the Work
                mainListForSavedOrders.setAdapter(savedOrderListAdapter);  //this  is for to set the Adapters
                //end of the List View for the Work in that content



                //this  is for the overflow container
                overViewContainer=(LinearLayout) findViewById(R.id.overflowContainer); //this is for the overViewContainer
                //end of the overflow container




                //this is for the click listeners
                mainListForSavedOrders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                             //this is for the to manage the Click Listner
                             SavedOrderModal  savedOrderModal=savedOrderModalList.get(position);  //this is for to get if is selected
                             savedOrderModal.setSelected(! savedOrderModal.isSelected());  //this is for is Selected
                             //end of managing the click listener


                             //this is for the item selection
                             selectionPointer=position; //this is for  the selection Pointer
                             //end of the item selection




                            //this is for the All Changes
                             ManageClickListeners();
                            //end of all changes


                            //this is for to manage the Notification
                            savedOrderListAdapter.notifyDataSetChanged(); //this is for the notification that it has been changed
                            //end of the notification




                    }
                });
                //end of the click listeners




                //this is for the Long ClickListener
                mainListForSavedOrders.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {


                        //this is for the long click Listener
                        SavedOrderModal savedOrderModal=savedOrderModalList.get(position); //this is for the savedOrderList
                        FillFurtherItems(savedOrderModal.getOrderDescription()); //this is for the FillFurtherItems
                        //end of the Long Listener


                        //this is for the item selection
                        selectionPointer=position; //this is for  the selection Pointer
                        //end of the item selection




                        return false;
                    }
                });
                //end of the Long ClickListener



                //this  is for the hide click Listener
                hideButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) { //this is for the onClick for the buttons
                        overViewContainer.setVisibility(View.GONE);
                        hideButton.setVisibility(View.GONE);
                    }//end of the onClick
                });
                //end of the hiding the click Listener





           }//end of the try
        catch(Exception ex){ //this is for to catch the Exception
            PrintintLog("Exception of type  "+ex.getMessage()+" while going for to Initialize the View Components");
        }
        //end of the Initialize the View

    }
    //end of the in initialize







    //this is for the to fill the View
    public void FillFurtherItems(String lines){

        //this is for the try
        try{

            selectedProducts.clear();

                        //this is for to Parse the data and fill the data
            JSONObject    jsonObject=new JSONObject(lines);  //this is for the lines
            JSONArray     jsonArray=jsonObject.getJSONArray("orderDetail");
                        //end of parsing the data and filling


            //this is for the Product Name
            ProductDatabase  productDatabase=new ProductDatabase(getApplicationContext()); //this is for the Product Database
            //end of Product Name


            //this is for the to get the data
            for(int i=0; i<jsonArray.length(); i++){



                   //this is for to parsing exception
                    try{//this is for the try


                        JSONObject current=jsonArray.getJSONObject(i);



                        //this is for to get the values
                        String productName=productDatabase.GiveMeProductName(current.getString("productID"));
                        int quantity=current.getInt("qty");
                        //end of the getting the values


                        SelectedProducts selectedProducts_=new SelectedProducts(current.getString("productID"),productName,String.valueOf(quantity),"");
                        selectedProducts_.setCounter(i+1);

                        selectedProducts.add(selectedProducts_);



                       }//end of the try
                    catch(Exception ex){ //this is for exception
                        PrintintLog("Exception of type is as follows  "+ex.getMessage()+" while going for to parse the JSON Array");
                    }
                   //end of the parsing exception



            }
            //end of reading the data from the json array





            //this is for to set the adapter and list
            selectedProductListAdapter=new SelectedProductListAdapter(getApplicationContext(),selectedProducts);
            listForDetail.setAdapter(selectedProductListAdapter);
            overViewContainer.setVisibility(View.VISIBLE);
            hideButton.setVisibility(View.VISIBLE);
            //end of setting the adapter





           }//end of the try
        catch (Exception ex){ //this  is for the catch
           PrintintLog("Exception of type is as follows "+ex.getMessage()+" while going for to Fill the Further Items");
        }
        //end of the try and catch
    }
    //end of filling the View









    //this is for to manage the  click Listeners

    //this  is for the fields
    public int CounterForSelection=-1; //this  is for the counter Selection
    //end  of the internal fields

    public void ManageClickListeners(){

        //this  is for the try and catch
        try{


            //this is for the counter
            int counter=0; //this is for the counter
            //end of the counter



            //this is for the up count the counter
            for(SavedOrderModal  savedOrderModal: savedOrderModalList){ //this is for the savedOrderList


                //this is for to check for the selection
                if(savedOrderModal.isSelected()){ //this is for if selected
                    counter++;
                }
                //end of the selection

            }
            //end of upCounting the Counter




            //this is for the selection
            selection_counter.setText(String.valueOf(counter)); //this is for the text Selection
            //end of setting the selection




            //this is for to initialize the
            if(counter>0){
                update.setVisibility(View.VISIBLE);
                remove.setVisibility(View.VISIBLE);
            }
            else{
                update.setVisibility(View.GONE);
                remove.setVisibility(View.GONE);
            }
            //end of initializing the buttons




            //this is for the counter Selection
            this.CounterForSelection=counter;
            //end of the counter selection



           }//end of the try
        catch(Exception ex){ //this is for the catch
             PrintintLog("Exception of type is as follows "+ex.getMessage()+" while going for  to monitor the click");
        }
        //end of the try and catch

    }
    //end of the click Listeners






     //this is for the Orders Database
     public Orders  orders; //this is for the orders
     //end of doing things for the database for the orders





      //this is for to read the Orders from the database
      public void ReadDataOrdersDatabase(){

          //this is for the reading the orders
          try{



               //this is for the cursors
               Cursor  cursor=orders.GetOrders(); //this is for to get the orders
               //this  is for to get the data from that cursor


              //this is for the other Work that We can read the data for the cursors
              cursor.moveToFirst();

              //this is for to make the  list
              savedOrderListAdapter.clear(); //this is for to make the list clear
              //end of making the list clear



              if(cursor.getCount()==0){
                  PrintToast("No Order is Pending Now");
              }



              //this is for the length of the cursors
              for(int i=0; i<cursor.getCount(); i++) { //this is for the start of the loop


                  //this is for to get the data
                  cursor.moveToPosition(i); //this is for the counter values in that content
                  //end of getting the data


                  //this  is for the fields
                  String id = cursor.getString(0); //this is for the id
                  String date = cursor.getString(1); //this is for the date
                  String orderDetail = cursor.getString(2);  //this is for the order detail
                  //end of the inner fields



                  //this  is for the size
                  int size=0;
                  if(savedOrderModalList.size()==0){
                      size=1;    //this  is for to set the size
                  }
                  else{
                      size=savedOrderModalList.size();  //this is for to get the size
                  }
                  //end of the size


                  //this is for to put it in the list
                    savedOrderListAdapter.add(new SavedOrderModal(id,size,orderDetail,date));
                  //end of going for to put it in the list




              }//end of the for loop while reading the data from the list


              //this is for to notifying the adapter for that change
              savedOrderListAdapter.notifyDataSetChanged(); //this is for to notify that data Set Has been changed
              //end of notifying the change for that adapter



               //end of the cursors





             }//end of the try
          catch(Exception ex){  //this is for the exception
             PrintintLog("Exception of type is as follows "+ex.getMessage()+" while going for to Read the data for the orders");
          }
          //end of reading  the orders

      }
      //end of the reading the data from the database




  //this is for the handler
    public Handler  handler; //this  is for the handler
  //end of the handler



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sycnched_orders);

        //this is for to initialize the Orders
        orders=new Orders(getApplicationContext()); //this is for the orders for the List Work
        //end of going for to initialize the Orders


        //this is for the handler
        handler=new Handler(); //this is for the handler
        //end  of the handler



        //this  is for to initialize the View
        InitializeView(); //this is for to initialize the View
        //end of going for to initialize the View


        //this is for to invoke it for the orders
        ReadDataOrdersDatabase(); //this is for to read the orders
        //end of going for to read the orders



    } //end of the onCreate



    //this is for the Log And Toast
    //this is for to Printing the Toast
    public void  PrintToast(String Line){ //this is for to Print the Toast
        Toast.makeText(getApplicationContext(),Line ,Toast.LENGTH_SHORT).show();  //this is for to show the toast
    }
    //end of the Printing the Toast





    //this  is for the Log
    private static final String TAG = "SynchedOrders";
    //this is for the Log Printing
    public void PrintintLog(String Line){
        Log.d(TAG,Line); //this is for the Log Line
    }
    //end of the Log Printing
    //end of the Log
    //end of the Log And Toast






    //this is for to remove
    public void RemoveSelectedOrders(View view){ //this is for to remove the selected Items

        //this is for to remove
         try{



             //this is for to check the selection
             if(this.CounterForSelection>0){

             }
             else{ //this  is for the else part
                 PrintToast("Please first Orders To remove"); //this  is for the Alert
                 return;  //this  is for to return
             }
             //end of the selection Counter








             //this is for to Alert
             AlertDialog.Builder   builder=new AlertDialog.Builder(SycnchedOrders.this); //this is for the builder
             builder.setTitle("Confirmation");
             builder.setMessage("Are you sure to remove selected "+CounterForSelection+" Orders");
             builder.setPositiveButton("Remove", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int which) {




                     //this  is for the loop
                     Orders  orders=new Orders(SycnchedOrders.this);


                     //this   is for the Actual Loop
                     for(SavedOrderModal  savedOrderModal: savedOrderModalList){ //this is for the start of the Loop



                         //this is for to check for the Order Selection
                         if(savedOrderModal.isSelected()){  //this is for to check that order has been selected


                                 //this  is for to remove
                                  orders.DeleteOrder(savedOrderModal.getId());  //this is for to delete that Order
                                 //end of going for to remove




                         }//end of the if Work
                         else {  //this  is for that it is not selection

                         }
                         //end of the Order Selection



                     }
                     //end of the Actual Loop



                     //this is for to show the Actual Order
                     ReadDataOrdersDatabase();   //this is for to read the Data Orders
                     ManageClickListeners();
                     //end of the Actual Order



                     //end of the loop







                 }
             });
             builder.setNegativeButton("Not", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int which) {


                     //this is for to if not
                     PrintToast("The task has been canceled"); //this  is for to Print toast
                     //end of the if not


                 }
             });
             //end of the Alert





             //this is for to show the alert
             builder.show(); //this is for to Show
             //end of going for to show alert




            }//end of the try
        catch(Exception ex){ //this is for to catch
            PrintintLog("Exception while going for to remove the Selected Orders "+ex.getMessage());  //this  is for to remove the Exception
        }
        //end of  going for to remove

    }
    //end of the remove




    //this is for to the update
    public void UpdateSavedOrders(View view){

        //this  is for the try
        try{ //this  is for  the try



            //this is for to check the selection Counter
            if(this.CounterForSelection>0){ //this is for the if part

            }//end of the  if Work
            else{
                PrintToast("Please first select An order To Update"); //this is for the Toast
                return;  //this is for to go back
            }
            //end of the checking for the selection Counter





            //this is for to the Alert
            AlertDialog.Builder  builder=new AlertDialog.Builder(SycnchedOrders.this); //this is for the builder
            builder.setTitle("Confirmation");
            builder.setMessage("Are you sure to update the selected order"); //this is for the message
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    //this is for to Process




                    try{//this is for the start of the try




                        //this is for  going for the Update
                        SavedOrderModal savedOrderModal=savedOrderModalList.get(selectionPointer);
                        //end of going for to update



                        //this is for to  get the order Id and Lines
                        String id= savedOrderModal.getId(); //this is for the  id
                        String lines=savedOrderModal.getOrderDescription();
                        //end of getting the Orders Id and Lines





                        //this is  for the counter
                        int counter_flag=6212; //this is for the counter
                        //end of the counter




                        //this is for the Posting the Values
                        Bundle  bundle=new Bundle(); //this is for the bundle
                        bundle.putInt("signal",counter_flag);  //this   is for to put the int
                        bundle.putString("id",id);
                        bundle.putString("lines",lines);  //this  is for the lines


                        //this is for the intent
                        Intent intent=new Intent(getApplicationContext(),CartActivity.class); //this is for the intent
                        intent.putExtras(bundle); //this is for to put the bundle
                        startActivityForResult(intent,checks);  //this is for to start the Activity
                        //end of the posting the intent



                        //end of Posting the Values













                       }//end of the try
                    catch(Exception ex){ //this is for the Catch
                        PrintintLog("Exception of type is as follows "+ex.getMessage()+" while going for update the savedOrders");
                    }//end of the catch







                    //end of the Processing the Orders

                }
            }); //this is for the Positive Button
            builder.setNegativeButton("Not", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    PrintToast("Has been cancelled");  //this is for the Toast
                }
            }); //this is for the Negative Button
            //end of the Alert




            //this is for to show the Builder
            builder.show(); //this is for to show
            //end of going for to Show the Builder




        }//end of the  try
        catch(Exception ex){//this  is for to catch the Exception
            PrintintLog("Exception of type is as follows  "+ex.getMessage()+" while going for to  update");
        }//end of the catch
        //end of the catch

    }
    //end of the update





    //this is for the   on  the result return back to activity


    //this is for the on result
    public int checks=1; //this is for the flag for the result
    //end of the result checks



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        //this is for to get the results
        if(resultCode==RESULT_OK){  //this  is for the checks and results

            //this is for to get the result
            ReadDataOrdersDatabase();  //this is for to read the data from the Database for the Database
            //end  of getting the result

        }
        //end of getting the results


    }
    //end of getting the result back to the     activity




    //this is for to check either we are online or not
    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }
    //end of checking either we are online or not






    //this  is for the session Work
    public void FetchTheSession(){ //this is for to fetch the session
        //this  is for to fetch the session data
        //this  is for the try
        try{



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
                        PrintintLog("Session data is as follows "+line+"while id id as follows  "+String.valueOf(bhattiPreferencesManager.GetUserID()));
                        //end of the Line Printing


                        //now sending it to parse
                        final   String session=new BhattiParsers().ParseSession(line);
                        //end of parsing



                        //this is for if the session is not null
                        if(session!=null){ //this is for if the session is not null
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    bhattiPreferencesManager.SetUserSession(session);  //this is for to set the session


                                    //this  is for the Actual Orders
                                    ActualOrderProcess(session);  //this is for the Actual Session
                                    //end of the Actual Orders


                                }
                            }); //end of the





                        }
                        //end of if the session is not null
                    }//end of the try
                    catch (Exception ex){
                        PrintintLog("Exception of type is as follows  "+ex.getMessage());
                    }
                    //end of the try and catch



                }
            });
            t.start();
            //end of Thread





        }//end of the try
        catch (Exception ex){
            PrintintLog("Exception of type is  as follows  "+ex.getMessage()+" while fetching the Session Data");
        }//end of the catch
        //end  of the try and catch
        //end of fetching the session data
    }
    //end of fetching the Session
    //end of session Work





    //this  is for to Process the Orders
    public void ProcessOrders(View view){ //this is for to Process the Orders

        //this is for the try and catch
        try{


            //this is for to check for the orders
            if(savedOrderModalList.size()>0){

            }//end of the savedOrdersModalList
            else{
                PrintToast("Sorry There are no pending Orders To Process");
                return;
            }
            //end of checking for the orders





            //this is for to Process the Orders
            AlertDialog.Builder  builder=new AlertDialog.Builder(SycnchedOrders.this);  //this is for the Order
            builder.setIcon(R.drawable.logo);
            builder.setTitle("Confirmation"); //this is for the Title
            builder.setMessage("Are you sure to Process the Orders");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {


                    //this is for to Process Things
                    if(isOnline()){  //this is for to Check Online
                        FetchTheSession();
                        PrintToast("Processing  Your  Order");
                    }//end of the if close
                    else{ //this is for the else part
                        PrintToast("Please Get Internet Connection");  //this  is for the Internet Connection
                        return;
                    }
                    //end of going for Processing the Things



                }
            });
            builder.setNegativeButton("Not", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                        PrintToast("Cancelled");  //this  is for to Cancel
                }
            });
            //end of Going For the Processing the Orders




            //this is for to show the alert
            builder.show(); //this is for to show the Alert
            //end of going for to show the alert



           }//end of the try
        catch(Exception ex){ //this is for the Exception
            PrintintLog("Exception of type is as follows  "+ex.getMessage()+" while going for ");
        }
        //end of the try and catch

    }
    //end of Going For Process Orders









    //this is for to Process the Order
    public void ActualOrderProcess(String session){ //this  is for the Order Processing with only the few things Like as follows

        //this is for the Actual Order Processing
         try{



             //this is for Processing
             totalOrders=savedOrderModalList.size();
             processed=0;
             //end of Processing


             //this  is for the Loop
             for(SavedOrderModal  savedOrderModal:savedOrderModalList){ //this is for the saved Order List




                 String id=savedOrderModal.getId();
                 String detail=savedOrderModal.getOrderDescription();


                 //this is for the jsonObject
                 JSONObject jsonObject=new JSONObject(detail);
                 jsonObject.put("sessionId",session);  //this is for the session
                 //end of the jsonObject



                    //this is for the POST
                    Post(id,jsonObject.toString());   //this is for the POST
                    //end of the POST







             }
             //end of the Loop




            }//end of the try
         catch(Exception ex){ //this is for the catch Exception
            PrintintLog("Exception of type is as follows  "+ex.getMessage()+" while going for Actual Processing  of the Order");
            }
        //end of the Actual Order Processing

    }
    //end of Processing the Order





    //this is for the counter system
    public int totalOrders=0;
    public int processed=0;
    //end of the counter System






    //this is for to post
    public void Post(final String id, final String lines){ //this is for the Lines

        //this is for the try
        try{



            //this  is for the Volley
            //this is a place for the Volley

            //this is for the Request Queue
            RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());

            //end of the Request Queue



            //this is for the string request
            StringRequest stringRequest= new StringRequest(Request.Method.POST, RESTFullApi.UrlToPOSTOrder(), new Response.Listener<String>() {
                public final String id_=id;
                @Override
                public void onResponse(String response) {




                    //this is for the Bhatti Parsers
                    BhattiParsers bhattiParsers=new BhattiParsers();
                    boolean response_=bhattiParsers.GetProductOrderBooleanResponse(response);
                    if(response_){



                        processed++;




                        //this is for the id Process
                        Orders  orders=new Orders(SycnchedOrders.this);
                        orders.DeleteOrder(this.id_);
                        ReadDataOrdersDatabase();
                        PrintToast("Total Processing is "+processed+"/"+totalOrders);
                        //end of Processing





                    }else{
                        PrintintLog("Order Posting Failed Due to Reason that Session has been expired");
                    }
                    //end of the Bhatti Parsers


                    PrintintLog("The response is as follows  "+response);
                }//end of the response
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            PrintintLog("Error response is as follows  "+error);
                        }
                    }
            ){ //this is for the to override the methods
                @Override
                public byte[] getBody() throws com.android.volley.AuthFailureError {
                    String str = lines;
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


            //end of the place for the Volley

            //end of the Volley





           }//end of the try
           catch(Exception ex){ //this  is for the Exception
               PrintintLog("Exception of type is as follows  "+ex.getMessage()+" while going for the  Order Post");
           }
        //end of the try


    }
    //end of going for the Post




}//end of the SycnchedOrders
