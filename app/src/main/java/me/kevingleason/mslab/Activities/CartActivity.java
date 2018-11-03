package me.kevingleason.mslab.Activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import me.kevingleason.mslab.Adapters.SelectedProductListAdapter;
import me.kevingleason.mslab.BhattiParsers.BhattiParsers;
import me.kevingleason.mslab.BhattiSharedPreferencesManager.BhattiPreferencesManager;
import me.kevingleason.mslab.DatabaseWork.Orders;
import me.kevingleason.mslab.DatabaseWork.PartyDatabase;
import me.kevingleason.mslab.DatabaseWork.ProductDatabase;
import me.kevingleason.mslab.ModelClasses.OrderModel;
import me.kevingleason.mslab.ModelClasses.PartyWork;
import me.kevingleason.mslab.ModelClasses.Product;
import me.kevingleason.mslab.ModelClasses.SelectedProducts;
import me.kevingleason.mslab.R;
import me.kevingleason.mslab.RestfullApi.RESTFullApi;
import me.kevingleason.mslab.Utility.Utility;

public class CartActivity extends AppCompatActivity {

   //this is for the List of All View Fields
    public ListView  selectedItemsList; //this is for the selected Items List
    public List<PartyWork> partyList=new ArrayList<PartyWork>(); //this is for the partyList
    public List<String>    partyAutoCompleteList=new ArrayList<String>();  //this is for the party AutoComplete
    public List<String>    productAutoCompleteList=new ArrayList<String>(); //this is for  the product AutoComplete
    public List<Product>   productList=new ArrayList<Product>(); //this is for the product List
   //end of the List of All View Fields



    //this is for the selectedItemsList
    public List<SelectedProducts>  selectedProducts_list=new ArrayList<SelectedProducts>(); //this is for the selectedProductsList
    //end of the selectedItemsList



   //this  is for the other View
    public TextView counterForItemSelection; //this is for the selected Items Counter
   //end of the other View


    //this  is for the counter top area
    public AutoCompleteTextView   productAutoComplete; //this is for the product auto Complete
    public AutoCompleteTextView   partyAutoComplete; //this is for the party auto Complete
    public EditText   productCounter; //this  is for the product Counter
    public Handler  handler ; //this is for the handler
    //end of the top counter area

    //this is for the product remarks
    public EditText  productRemarks;  //this is for the product remarks
    //end of the product remarks

    //this is for the object of the SharedPrefernece
    public BhattiPreferencesManager  bhattiPreferencesManager;  //this is for the shared Preference
    public BhattiParsers  parsers; //this is for the bhatti parsers
    //end of the Object of the SharedPrefence

    //this  is for the database Objects
    public PartyDatabase partyDatabase;  //this is for the partyWork
    public ProductDatabase productDatabase; //this is for the productDatabase
    //end of the database Objects

    //this is for the list for the selected items
    public List<SelectedProducts> selectedProduces=new ArrayList<SelectedProducts>(); //this is for the selected products list
    public SelectedProductListAdapter   selectedProductListAdapter; //this is for the selected products list adapter
    //end of the selected items list




    //this is for to get all items
    public TextView   clear_textView; //this is for the clear textView
    public ImageView  delete_button; //this is for the delete button
    public Button updateButton, addButton;  //this is for the button
    //end of getting all items


    //this is for the initialization of the button
    public void InitializeMyViews(){
        try{//this is for the try
            updateButton=(Button) findViewById(R.id.updateButton); //this is for to the update Button
            delete_button=(ImageView)  findViewById(R.id.removeSelectedItems); //this is for to remove the selected Items
            clear_textView=(TextView)  findViewById(R.id.textViewToClear); //this is for to clear the items
            addButton=(Button) findViewById(R.id.btn_add); //this is for the add Button
           }//end of the try
        catch (Exception ex){
            PrintLog("Exception of type is as follows  "+ex.getMessage()+" while initialize the View");
        }//end of the catch
    }
    //end of the initialization of the buttons






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        //this is for the database Work
        partyDatabase=new PartyDatabase(getApplicationContext()); //this is for the PartyWork
        productDatabase=new ProductDatabase(getApplicationContext()); //this is for the Product database
        //end of the database Work


        //this  is for the sharedPreference
        bhattiPreferencesManager=new BhattiPreferencesManager(getApplicationContext()); //this is for the bhatti Shared Preference
        parsers=new BhattiParsers();
        //end of the sharedPreference


        //this is for  to initialize the other View elements
        InitializeMyViews();
        //end of the initialization of the View elements


        //this is for to Initialize
        InitializeView();  //this is for to Initialize the View
        //end of initialization



        //this is for  the click listener
        //this is for the selection and loading
        partyAutoComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //this  is for to check for the partyAutoComplete
                if(partyList.size()==0){
                    if(isOnline()){
                        FetchPartyData();
                    }
                }
                //end of the partyAutoComplete



                //this is for to show the drop down
                partyAutoComplete.showDropDown();
                //end of going for the drop down as well


            }
        });
        //end  of the selection and loading




        //this is for the product data
        productAutoComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //this  is for the product
                if(productList.size()==0){
                    if(isOnline()){
                        FetchProductData();
                    }
                }
                //end of the product
            }
        });
        //end of the product data
        //end of the click listener






        //this is for the party, product and counter change Listener
        TextWatcher textWatcher=new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                checkForToManageAddButton();
                //this is for to change the Adapter settings
                UpdateProductsByNewData();
                if(productList.size()>0){
                    //productAutoComplete.showDropDown();
                }
                //end of changing the settings for the adapter
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkForToManageAddButton();
                UpdateProductsByNewData();
                if(productList.size()>0){
                    //productAutoComplete.showDropDown();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkForToManageAddButton();
                //this is for to change the Adapter settings
                UpdateProductsByNewData();
                if(productList.size()>0){
                    //productAutoComplete.showDropDown();
                }
                //end of changing the settings for the adapter
            }
        };


        //this is for the textChange Listener
        partyAutoComplete.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                UpdatePartyByNewData();
                if(party.size()>0){
                    partyAutoComplete.showDropDown();
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                UpdatePartyByNewData();
                if(party.size()>0){
                    partyAutoComplete.showDropDown();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                UpdatePartyByNewData();
                if(party.size()>0){
                    partyAutoComplete.showDropDown();
                }
            }
        });
        productAutoComplete.addTextChangedListener(textWatcher);
        productCounter.addTextChangedListener(textWatcher);
        productAutoComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //this is for click Listener
                //productAutoComplete.showDropDown();
                //end of the Click Listener
            }
        });
        //end of the textChange Listener





        //end of the party, product and counter change Listener








    }//end of the onCreate


    //this is for the public
    public List<String>  products;
    public boolean flag_for_product=true;
    //end of the public


    //this  is for to update the Products by selection
    private void UpdateProductsByNewData() {


        //this  is for to update the data
         products=productDatabase.GiveMeProductNameForBetterSearch(productAutoComplete.getText().toString());//this is for to get the Products List
        //end of updating the new data


            if(flag_for_product){
                ArrayAdapter <String>   productListAdapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.product_autocomplete,R.id.item,products); //this is for the product list adapter
                productAutoComplete.setAdapter(productListAdapter); //this is for the adapter for the product
                //productAutoComplete.showDropDown();
               }
               else{
                ArrayAdapter <String>   productListAdapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.product_autocomplete,R.id.item,products); //this is for the product list adapter
                productAutoComplete.setAdapter(productListAdapter); //this is for the adapter for the product
                //productAutoComplete.showDropDown();
                   }





    }
    //end of updating the Products by Selection

    //this  is for the Party Data
    public List<String>  party;
    public boolean flag_for_party=true;
    //end of the Party Data

    //this  is for to update the Party by selection
    private void UpdatePartyByNewData() {


        //this  is for to update the data
        party=partyDatabase.GiveMePartyNameForBetterSearch(partyAutoComplete.getText().toString());//this is for to get the Products List
        //end of updating the new data


        if(flag_for_party){
            ArrayAdapter <String>   partyListAdapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.party_autocomplete,R.id.item,party); //this is for the product list adapter
           partyAutoComplete.setAdapter(partyListAdapter); //this is for the adapter for the product
           partyAutoComplete.showDropDown();
        }
        else{
            ArrayAdapter <String>   productListAdapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.party_autocomplete,R.id.item,party); //this is for the product list adapter
            partyAutoComplete.setAdapter(productListAdapter); //this is for the adapter for the product
            partyAutoComplete.showDropDown();
        }





    }
    //end of updating the Party by Selection

    //this is for the Progress Dialog
    public ProgressDialog progressDialog; //this is for the progress dialog
    //end of the Progress Dialog

    //this is for to manage the Progress Dialog
    public void ManageProgressDialog(String line, int counterForProgress){
        if(counterForProgress==0){ //this is for the counter for the Progress
                progressDialog.dismiss(); //this is for to dismiss it
            } //end of the counter for Progress
        else{
            progressDialog.setMessage(line);
            progressDialog.show();
        }//end of the counter for the progress
    }
    //end of managing the Progress Dialog

    //this is for to initialize the View
    public void InitializeView(){

        //this is for the list initialization
        selectedItemsList=(ListView)  findViewById(R.id.listView); //this is for the list view
        //end of the list




        //this is for the product remarks
        productRemarks=(EditText)   findViewById(R.id.productRemarks); //this is for the product remarks
        //end of the product remarks


        //this  is for the Initializing the View
        productAutoComplete=(AutoCompleteTextView)  findViewById(R.id.productSelector); //this is for the party Selector
        partyAutoComplete=(AutoCompleteTextView)  findViewById(R.id.partySelector); //this is for the party Selector
        productCounter=(EditText)  findViewById(R.id.productCounter); //this is for the product counter
        //end of the Initializing the View

        //this is for the counter for the item selection
           counterForItemSelection=(TextView)  findViewById(R.id.itemCounter); //this is for product counter
        //end of the counter for the item selection


        //this is for the progress Dialog
        progressDialog=new ProgressDialog(CartActivity.this); //this is for the progress
        //end of the progress Dialog


        //this is for the handler
        handler=new Handler(); //this is for the handler
        //end of the handler


        //this is for the selected products List
        selectedProductListAdapter=new SelectedProductListAdapter(getApplicationContext(),selectedProducts_list); //this is for the selected Products list
        selectedItemsList.setAdapter(selectedProductListAdapter);
        //end of  the selected products List adapter



        //this is for to fill the List
        ManageToFillList();
        //end of to fill the list and View things








        //this  is for  click listener
        selectedItemsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                //this is for the click  listener
                try{//this is for the try






                    SelectedProducts products_selected=  selectedProducts_list.get(position);  //this is for the product list


                    //this is for the product selected
                    products_selected.setSelected(!selectedProducts_list.get(position).isSelected());
                    selectedProductListAdapter.notifyDataSetChanged();
                    //end of the selected product



                    //this  is for the counter
                    CountingSelection();
                    //end of the counter




                    if(products_selected.isSelected()){

                        //this is for the index
                        selectionIndexForSelectedProduct=position; //this is for to set the position for the items
                        //end of the index

                        //this is for to manage the selection
                        ManagementOfSelection(position); //this is for the selection for the updating
                        //end of the selection of the item

                    }




                }//end of the try
                catch (Exception ex){ //this is for the exception
                    PrintLog("Exception of type is  as  follows  "+ex.getMessage()+" while click in item selected");
                }
                //end of the click listener


            }
        });
        //end of click listener














    }
    //end of initialize the View

    //this is for to initialize the autoComplete Listener
    public void InitializeAutoComplete(){ //this is for the autoComplete Listener
        try{//this is for the try

            //this  is for the click listener for the parties
            partyAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    //this  is for  to  get the data
                    partySelectionPointer=position; //this is for the party Selection pointer
                    //end of getting the data



                    //this  is for to track the exact location
                    //this is for to get the exact position
                    int counter=0; //this is for the counter
                    for(PartyWork  p:partyList){  //this is for the start of the list



                        //this is for to check where  is the exact point
                        if((p.getName()+"\nCode:"+p.getCode()).equals(partyAutoComplete.getText().toString())){ //this is for the id



                            break;
                        }
                        //end of checking where is the exact point

                        counter++; //this is for the counter

                    }
                    //end of getting the exact position




                    //this is for When found it
                    partySelectionPointer=counter; //this is for when we found it
                    //end of it when found it
                    //end of the exact location for it





                }
            });
            //end of the click listener for the parties




            //this is for the product list
            productAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //this is for to get the product
                    selectedProductList=position; //this  is for the product list
                    PrintLog("Selected Product is as follows  "+selectedProductList+"While Actual Position  is "+String.valueOf(view.getTag()+"id is as follows  "+id));
                    //end of the product



                    //this is for to get the exact position
                    int counter=0; //this is for the counter
                    for(Product  p:productList){  //this is for the start of the list



                        //this is for to check where  is the exact point
                        if((p.getName()+"\nCode:"+p.getCode()).equals(productAutoComplete.getText().toString())){ //this is for the id



                            break;
                        }
                        //end of checking where is the exact point

                        counter++; //this is for the counter

                    }
                    //end of getting the exact position




                    //this is for When found it
                    selectedProductList=counter; //this is for when we found it
                    //end of it when found it




                }
            }); //this is for product autoComplete Listener
            //end of the product list

        }//end of the try
        catch (Exception ex){
           PrintLog("Exception of type is as follows  "+ex.getMessage());
        }//end of the catch
    }
    //end of the autoComplete Listener

    //this is for the index for the selection
    public int selectionIndexForSelectedProduct=-1; //this is for the selection index for the selected product
    //end of the index for the selection

    //this is for the management of the selection
    public void ManagementOfSelection(int position){ //this is for the position

        //this is for the try
        try{



            //this is for to get the selected Product
            SelectedProducts  selectedProducts=selectedProducts_list.get(position); //this is for the selected Products List
            //end of selecting the product



            //this is for to get the product id
            int pCounter=0;
            for(Product  p:productList){
                     if(String.valueOf(p.getId()).equals(selectedProducts.getId())){//this is for the string
                         PrintLog("Product match at "+pCounter+" while Size  is "+productList.size());
                         break; //this is for to stop here and get started to get other things
                     }
                   pCounter++;  //this is for the increment
            }
            //end of the products id




            //this is for the partyCounter
            int partyCounter=0; //this is for the party Counter
            for(PartyWork partyWork_:partyList){ //this is for the party Work

                //this is for the partyWork
                if(String.valueOf(partyWork_.getId()).equals(selectedProducts.getPartyId())){
                    PrintLog("Party match at "+partyCounter+" while Size  is "+partyList.size());
                    break;
                }//end of the checking for the partyWork
                partyCounter++; //this is for the party Counter
            }
            //end  of the partyCounter







            //this is for the counter
            selectedProductList=pCounter;
            partySelectionPointer=partyCounter;
            //end of the counter








            //this is for to update the selected item
                productAutoComplete.setText(selectedProducts.getName()); //this is for the product AutoComplete
                partyAutoComplete.setText(selectedProducts.getCompanyName());  //this is for the partyAutoComplete
            //end of the updating the selected item




            //this  is for  the selection  quantity
            productCounter.setText(String.valueOf(selectedProducts.getQuantity())); //this is for the product counter
            //end of the selection quantity






           }//end of  the try
        catch(Exception ex){ //this is for the exception
            PrintLog("Exception of type is as follows  "+ex.getMessage()+"While going for to update the elements");
        }
        //end of the try and catch

    }
    //end of the selection of the management

    //this is for to check the counter
    public int   selectionCounter=0   ; //this is for  check
    //end of the selected Counter

    //this is  for to check
    public void  CountingSelection(){
           //this  is for to count
           try{

                     //this  is for the counter
                      selectionCounter=0;  //this is for selectionCounter
                     //end of counter

                     for(SelectedProducts products_counter: selectedProducts_list){
                         //this is for the counter
                         if(products_counter.isSelected()){
                             selectionCounter++;  //this is for counter
                         }
                         //end of the selectedCounter
                     }
                     counterForItemSelection.setText(String.valueOf(selectionCounter));


                     //this is for the selection counter
                     if(selectionCounter>0){
                         updateButton.setVisibility(View.VISIBLE);
                         delete_button.setVisibility(View.VISIBLE);
                     }
                     else{
                         delete_button.setVisibility(View.GONE);
                         updateButton.setVisibility(View.GONE);
                     }
                     //end of the selection counter



                     //this is for the clear button
                     if(selectedProducts_list.size()>0){

                     }
                     else{
                         clear_textView.setVisibility(View.GONE);
                     }
                     //end of the clear button



              }//end  of the try
           catch (Exception ex){//this is for the catch
              PrintLog("Exception of type  "+ex.getMessage()+" while counting the  data");  //this is for the Printing the Log
           }
           //end of counting
    }
    //end of checking

    //this is for to fill the List
    public void ManageToFillList(){

        //this is for to fill the List


        //this is for to check for the offline
        if(isOnline()){ //this  is end offLine


              //this  is  for to get the data
               FetchTheSession();
              //end of get the data from the online data



        }//end of the isOnline
        else{
            //this  is for the offline Mode
             PrintingToast("Working in Offline Mode");
             FetchDataFromDatabase(); //this is for to pick the data from the offline database
            //end of the offLine Mode
        }
        //end of checking for the offline




        //end of fill the List

    }
    //end of filling the List

    //this  is for to fetch the  Session
    public String temp_Line_0="", getTemp_Line_1="";
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
                        PrintLog("Session data is as follows "+line+"while id id as follows  "+String.valueOf(bhattiPreferencesManager.GetUserID()));
                        //end of the Line Printing


                        //now sending it to parse
                        final   String session=parsers.ParseSession(line);
                        //end of parsing



                        //this is for if the session is not null
                        if(session!=null){ //this is for if the session is not null
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    bhattiPreferencesManager.SetUserSession(session);  //this is for to set the session
                                    ManageProgressDialog("",0);
                                    //this is for to get the Party Data
                                    FetchPartyData();   //this is for to fetch the party data
                                    FetchProductData(); //this is for to fetch the data
                                    //end  of getting the Party Data
                                }
                            }); //end of the





                        }
                        //end of if the session is not null
                       }//end of the try
                    catch (Exception ex){
                       PrintLog("Exception of type is as follows  "+ex.getMessage());
                    }
                    //end of the try and catch



                }
            });
            t.start();
            //end of Thread





           }//end of the try
        catch (Exception ex){
            PrintLog("Exception of type is  as follows  "+ex.getMessage()+" while fetching the Session Data");
        }//end of the catch
        //end  of the try and catch
        //end of fetching the session data
    }
    //end of fetching the Session

    //this is for to fetch the data in the offline mode
    public void FetchDataFromDatabase(){

        //this is for the try
        try{//this is for the try

            //this is for the cursor
            Cursor partyCursor=partyDatabase.GetPartyList(); //this  is for the party List
            //end of the cursor


            PrintLog("The number  of items "+partyCursor.getCount());


            //this is for to get the parties and list
            partyCursor.moveToPosition(0);
            for(int i=0; i<partyCursor.getCount(); i++){ //this is for the loop


                //this is for the inner try and catch
                try{//this is for the try


                          //this is for to move
                          partyCursor.moveToPosition(i); //this is for to move it to the specification
                          //end of moving the cursor



                          //this is for to get from the cursor
                           String id=   partyCursor.getString(0);  //this is for the id
                           String name=partyCursor.getString(1); //this is for the name
                           String address=partyCursor.getString(2); //this is for the address
                           String contactPerson=partyCursor.getString(3); //this is for the contactPerson
                           String mobile=partyCursor.getString(4); //this is for the mobile
                           String discountPercentage=partyCursor.getString(5); //this is for the discount
                           String code=partyCursor.getString(6); //this is for the code
                           String balance_text=partyCursor.getString(7); //this is for the balance text
                          //end of getting it from the cursor



                           //this is for the party
                           PartyWork   partyWorkoffLine=new PartyWork(Long.parseLong(id),name,address,contactPerson,mobile,bhattiPreferencesManager.GetUserID(),Double.parseDouble("0.0"),code,Double.parseDouble("0.0"));
                           //end of the party offline Work


                           //this is for to add the party to the list
                            partyList.add(partyWorkoffLine);
                           PrintLog("Id="+id+"Party Name="+name+"Address"+address+"Contact Person"+contactPerson+"Mobile"+mobile+"Balance="+balance_text+"Code="+code+"Balance Text"+balance_text);  //this is for the log work
                           //end of adding the party to the list







                   }//end of the try
                catch (Exception ex){ //this is for the catch
                    PrintLog("Exception of type is as follows "+ex.getMessage()+" while getting the party Lines");
                }
                //end of the inner try and catch


            }


            //this is for to make a loop to set the items
            //this is for to clear the data
            partyAutoCompleteList.clear();  //this  is for to make the party list clear
            //end of going to clear the data from the list


            //this is for to get the parties to the autoComplete List
            for(PartyWork partyWork: partyList ){ //this  is for the party Work

                //this is for to add the party items to the list
                partyAutoCompleteList.add(partyWork.getName()+"\nCode:"+partyWork.getCode()); //this is for to add the party autoComplete List
                //end of adding the party items to the List of the party autoComplete

            }
            //end of the parties to the autoComplete  List
            //end of making the  loop to set the items





            ArrayAdapter<String>  itemForPartyAutoComplete=new ArrayAdapter<String>(getApplicationContext(),R.layout.party_autocomplete,R.id.item,partyAutoCompleteList); //this is for the
            partyAutoComplete.setAdapter(itemForPartyAutoComplete);
            InitializeAutoComplete();
            //end of the parties list





            //this is for to fetch the data from the offline database for the products



            //this is for the cursor for the product data
            Cursor  productCursor=productDatabase.GetProducts(); //this is for to get the Products
            //end of the cursor for the product data




            productCursor.moveToPosition(0);



            //this is for to get the data from the cursor
            for(int j=0; j<productCursor.getCount();  j++){ //this is for the j counter loop



                //this is for the try and catch
                try{

                    //this is for to move the cursor to the current loop position
                    productCursor.moveToPosition(j); //this is for to move to the specific position
                    //end of moving the cursor to the current loop position




                //this  is for to get the item at a specific position
                    String id= productCursor.getString(0); //this is for the id  of the product
                    String name=productCursor.getString(1); //this is for the name of the  product
                    String code=productCursor.getString(2); //this is for the code
                    String typeId=productCursor.getString(3); //this is for the typeId
                    String purchasePrice=productCursor.getString(4); //this is for the purchase Price
                    String salePrice=productCursor.getString(5); //this is for the salePrice
                    String size=productCursor.getString(6); //this is for the size
                    String category=productCursor.getString(7);  //this is for the category
                    String  uom=productCursor.getString(8); //this is for the uom
                    //end of to get the item at a specific position


                    //this is for to Print the Log
                    PrintLog("Product Id"+id+"Name"+name+"Code"+code+"typeId"+typeId+"purchasePrice"+purchasePrice+"SalePrice"+salePrice+"Size"+size+"Category"+category+"UOM"+uom);
                    //end of the Printing the Log



                    //this is for the Product
                    Product  product=new Product(Long.parseLong(id),name,code,Long.parseLong(typeId),Double.parseDouble(purchasePrice),Double.parseDouble(salePrice),size,category,uom); //this  is for the Product
                    //end of the Product



                    //this is for to add them to the List
                    productList.add(product); //this   is for to add it to the product List
                    //end of adding it to the List




                }//end of the try
                catch (Exception ex){//this is for the catch
                    PrintLog("Exception of type is as follows  "+ex.getMessage()+" while going to get the products List");
                }
                //end of the try and catch


            }
            //end of fetching the data from the cursor




            //this is for to make the name List
            //this is for the list of the string type
            List<String>  productNameList=new ArrayList<String>(); //this is for the product name list
            //end of the list for the string type



            //this is for to fill the product list
            for(Product product: productList){ //this is for the loop


                //this is for the product list
                productNameList.add(product.getName()+"\nCode:"+product.getCode()); //this is for the product list name
                //end of the product list

            }
            //end of for to fill the product list
            //end of making the List






             //this  is for the AutoComplete
            ArrayAdapter <String>   productListAdapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.product_autocomplete,R.id.item,productNameList); //this is for the product list adapter
            productAutoComplete.setAdapter(productListAdapter); //this is for the adapter for the product
            InitializeAutoComplete();
             //end of the AutoComplete





            //end of the updating the data for the products





            //this is for to check for the Coming Data
            GetValuesFromPreviousActivity();
            //end of the Coming Data



           }//end of the try
        catch (Exception ex){ //this is for the catch
           PrintLog("Exception of type is as follows "+ex.getMessage()+" while loading the data from the database"); //this is for the exception
        }
        //end of the try

    }
    //end of fetching the data in the offline mode

    //this is for to fetch the  party Data
     public  void FetchPartyData(){
         //this  is for the try
         try{//this is for the try

             //this  is for to fetch the Party Data
         final     String partyRestUrl= RESTFullApi.GetUrlForPartyList(bhattiPreferencesManager.GetUserSession(),String.valueOf(bhattiPreferencesManager.GetUserID()));  //this is for the PartyRestUrl
             //end of fetching the Party Data



             //this is for loading the party data
             ManageProgressDialog("Loading Parties Data",1);
             //end of loading the party data


             //this is for the Thread
             Thread t=new Thread(new Runnable() {
                 @Override
                 public void run() {

                     //this is for the try and catch
                     try{//this is for the try Work
                         URL url=new URL(partyRestUrl); //this is for the url

                         URLConnection urlConnection=url.openConnection(); //this is for to Open the Connection

                         BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(urlConnection.getInputStream())); //this is for the bufferedReader


                         String temporaryLine=null,line=""; //this is for the temporary Line



                         //this is for the Line Reading
                         while((temporaryLine=bufferedReader.readLine())!=null){ //this  must not be null
                             line+=temporaryLine;
                         }
                         //end of the Line  Reading



                         bufferedReader.close(); //this is for to close it



                         //now sending it to parse

                         //end of parsing



                         //this is for if the session is not null
                         if(line!=null){ //this is for if the session is not null
                         final    String line_=line;
                             handler.post(new Runnable() {
                                 @Override
                                 public void run() {
                                     ManageProgressDialog("",0);
                                     ManageStreamDataForParties(line_);
                                 }
                             }); //end of the





                         }
                         //end of if the session is not null
                     }//end of the try
                     catch (Exception ex){
                         PrintLog("Exception of type is as follows  "+ex.getMessage());
                         handler.post(new Runnable() {
                             @Override
                              public void run() {
                                  ManageProgressDialog("",0);
                             }
                         });
                     }
                     //end of the try and catch



                 }
             });
             t.start();
             //end of Thread





            }//end of the try
         catch (Exception ex){
             PrintLog("Exception of type is as follows "+ex.getMessage()+" while fetching the party Data");
             ManageProgressDialog("Loading Parties Data",0);
         }
         //end of the try and catch
     }
    //end of fetching the party Data

    //this is for to fetch the Products data
    public void FetchProductData(){
        //this  is for to fetch the Party Data
        final     String productRestUrl= RESTFullApi.GetProductUrl(bhattiPreferencesManager.GetUserSession(),String.valueOf(bhattiPreferencesManager.GetUserID()));  //this is for the PartyRestUrl
        //end of fetching the Party Data



        //this is for loading the party data
        ManageProgressDialog("Loading Products Data",1);
        //end of loading the party data
         try {

        //this is for the Thread
        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {

                //this is for the try and catch
                try{//this is for the try Work
                    URL url=new URL(productRestUrl); //this is for the url

                    URLConnection urlConnection=url.openConnection(); //this is for to Open the Connection

                    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(urlConnection.getInputStream())); //this is for the bufferedReader


                    String temporaryLine=null,line=""; //this is for the temporary Line



                    //this is for the Line Reading
                    while((temporaryLine=bufferedReader.readLine())!=null){ //this  must not be null
                        line+=temporaryLine;
                    }
                    //end of the Line  Reading



                    bufferedReader.close(); //this is for to close it



                    //now sending it to parse

                    //end of parsing



                    //this is for if the session is not null
                    if(line!=null){ //this is for if the session is not null
                        final    String line_=line;
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                ManageProgressDialog("",0);
                                ManageStreamDataForProducts(line_);
                            }
                        }); //end of the





                    }
                    //end of if the session is not null
                }//end of the try
                catch (Exception ex){
                    PrintLog("Exception of type is as follows  "+ex.getMessage());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            ManageProgressDialog("",0);
                        }
                    });
                }
                //end of the try and catch



             }
        });
        t.start();
        //end of Thread

         }//end of the try
         catch (Exception ex){
        PrintLog("Exception of type is as follows "+ex.getMessage()+" while fetching the products Data");
        ManageProgressDialog("Loading Products Data",0);
        }
    //end of the try and catch
    }
    //end of getting the Products data

    //this is for to manage the stream data for the parties
    public int partySelectionPointer=-1;  //this is for the party selection Pointer
    //end of going to manage the stream data for the parties

    //this is for to fetch the Product Data
    public  void ManageStreamDataForParties(String line){
        try{
            PrintLog("The line is as follows for Parties "+line);
            //this   is for the


            //this is for to place it in the database
            List<PartyWork>  list_for_parties=parsers.ParseParties(line); //this  is for to parse the parties

            //this  is for to synch with the sqlite databae
            partyDatabase.InsertPartyData(list_for_parties);  //this is for to synch the party
            //end of synch with   the sqlite database
            //end of going to place that data to the database






            //this is for the actual lists for the parties
            partyList=list_for_parties; //this  is for the list of the parties
            //end  of the list for the parties


            //this is for to clear the data
            partyAutoCompleteList.clear();  //this  is for to make the party list clear
            //end of going to clear the data from the list


           //this is for to get the parties to the autoComplete List
            for(PartyWork partyWork: partyList ){ //this  is for the party Work

                //this is for to add the party items to the list
                partyAutoCompleteList.add(partyWork.getName()+"\nCode:"+partyWork.getCode()); //this is for to add the party autoComplete List
                //end of adding the party items to the List of the party autoComplete

            }
           //end of the parties to the autoComplete  List




            //this is for the party autoComplete to fill the data
            ArrayAdapter<String>  itemForPartyAutoComplete=new ArrayAdapter<String>(getApplicationContext(),R.layout.party_autocomplete,R.id.item,partyAutoCompleteList); //this is for the
            partyAutoComplete.setAdapter(itemForPartyAutoComplete);
            InitializeAutoComplete();
            //end of going for to fill the party data
















           }//end of the try
        catch(Exception ex){//this is for the try
           PrintLog("Exception of type is as follows "+ex.getMessage()+" while Fetching the Parties List");
        }//end of the catch
    }
    //end of fetching the Product Data

    //this is for the list for the products
    public int selectedProductList=-1; //this is for the selected product list
    //end of the list for the products

    //this  is manging the Stream data
    public void ManageStreamDataForProducts(String line){
         PrintLog("Products"+line);

         //this is for the another Layout
         List<Product>  productList_temporary= parsers.ParseProduct(line);  //this is for the product list is here
         //end of the other layout


         //this  is for the product list to the product database
         productDatabase.InsertIntoTable(productList_temporary);  //this  for the product list
         //end of the product list to the product database



        //this is for the try catch block
        try{//this  is for the try


            productList= productList_temporary; //this is for the product list



            //This for the Area for the Bhatti That Might be targeted

            //end of making the traget the lines for the best performance

            //this is for the list of the string type
            List<String>  productNameList=new ArrayList<String>(); //this is for the product name list
            //end of the list for the string type



            //this is for to fill the product list
            for(Product product: productList){ //this is for the loop


                //this is for the product list
                productNameList.add(product.getName()+"\nCode:"+product.getCode()); //this is for the product list name
                //end of the product list

            }
            //end of for to fill the product list




             //this is for to get the more data
               ArrayAdapter <String>   productListAdapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.product_autocomplete,R.id.item,productNameList); //this is for the product list adapter
               productAutoComplete.setAdapter(productListAdapter); //this is for the adapter for the product
               InitializeAutoComplete();
             //end of getting the more data




             //this is for to check for the Coming Data
             GetValuesFromPreviousActivity();
             //end of the Coming Data


           }//end of the try
        catch ( Exception ex){ //this  is for the catch
            PrintLog("Exception of type is as follows  "+ex.getMessage()+" while going to get the data for the adapter");
        }
        //end of the try and catch block






    }
    //end of managing the Stream data

    //this is for the View Methods
    public void AddProduct(View view){


           //this is for to add it to the list
            try{ //this is for the try

                   //this is for to get the party item
                   if(selectedProductList != -1  && partySelectionPointer != -1){//this is for the if


                       //this is for the data
                       Product  product=productList.get(selectedProductList) ; //this is for the product
                       PartyWork  party=partyList.get(partySelectionPointer); //this is for the party
                       //end of  getting the data



                       //this is for the quantity
                        String quantity=productCounter.getText().toString();
                       if(quantity.isEmpty()){ //this is for to check either  it is empty or not
                         PrintingToast("Please Enter quantity for product"); //this is for the alert
                         return; //this is for to back
                       }

                        final int quantityValue=Integer.parseInt(quantity); //this is for the integer value
                       //end of the quantity



                       //this is for to check the quantity
                       if(quantityValue>=1){ //this is for the quantity value

                       }
                       else{//this  is for the else part
                           PrintingToast("Please Enter Some Valid  Quantity");
                           return;
                       }
                       //end of checking for the  quantity






                       //this is for to add
                       SelectedProducts selectedProducts=new SelectedProducts(String.valueOf(product.getId()),product.getName(),String.valueOf(quantityValue),party.getName());

                       //this is for the list adding
                       selectedProducts.setPartyId(String.valueOf(party.getId()));
                       selectedProducts.setCounter(selectedProducts_list.size()+1);
                       //end of going to add





                       //this is for to check that either we have the item or not
                       int index=-1; //this is for the index where it was found before
                       int index_counter=0;
                       for(SelectedProducts selectedProduct:selectedProducts_list){ //this  is for the loop through


                              //this is for to check
                              if(selectedProduct.getId().equals(selectedProducts.getId())){
                                  index=index_counter;  //this is for the index counter
                                  //this is for to break
                                  break;
                                  //end of the break
                              }
                              //end of checking for the best practice


                              //this is for the index counter
                               index_counter++; //this is for the index counter
                              //end of the index counter




                       }
                       //end of checking either we have that item before or not






                       //this is for to check that item
                       if(index!=-1){  //it means we have something to do

                           //this is for the final index
                           final int index_=index; //this is for the index
                           //end of the final index


                           //this  is for the alert
                           AlertDialog.Builder  builder=new AlertDialog.Builder(CartActivity.this); //this is for the builder
                           builder.setIcon(R.drawable.logo); //this is for the logo
                           builder.setTitle("Alert");  //this is for the title
                           builder.setMessage("Dear user you already have same product at number "+(index+1)+" Therefore you can either skip it or add quantity to the previous item  choose the following "); //this is for the message
                           builder.setPositiveButton("Yes Add", new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialog, int which) { //this is for the click



                                   //this is for to get the previous item
                                   SelectedProducts  products_pro=selectedProducts_list.get(index_); //this is for the final index
                                   products_pro.setQuantity(String.valueOf((Integer.parseInt(products_pro.getQuantity())+quantityValue)));
                                   //end of getting the previous item

                                   //this is for to notify
                                   selectedProductListAdapter.notifyDataSetChanged(); //this is for that dataSet has been changed
                                   PrintingToast("Quantity has been added");
                                   selectedProductList=-1;
                                   productAutoComplete.setText("");
                                   productCounter.setText("");
                                   //end of the  notify

                                   //this  is for to manage the clear button
                                   ManageClearButton();
                                   //end of managing the clear button


                               }//end of onClick
                           });
                           builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialog, int which) {
                                   PrintingToast("Not Added");

                                   //this  is for to manage the clear button
                                   ManageClearButton();
                                   //end of managing the clear button
                               }
                           });
                           builder.show(); //this is for to show
                           //end of the alert




                       }
                       //end of checking that item

                       else{
                           selectedProducts_list.add(selectedProducts);  //this is for the adding it to the list
                           selectedProductListAdapter.notifyDataSetChanged(); //this is for the selectedProductListAdapter
                           //end of the list adding
                           //this is for to clear  them
                           productAutoComplete.setText("");  //this is for the Product AutoComplete
                           productCounter.setText("");
                           PrintingToast("Added");
                           //end of the clearing them

                           selectedProductList=-1;
                           productAutoComplete.setText("");
                           productCounter.setText("");

                           //this  is for to manage the clear button
                           ManageClearButton();
                           //end of managing the clear button

                       }














                   }//end of the if work
                   else{ //this is for the else work
                       PrintingToast("Please select product and party from dropdown"); //this is for the printing the toast
                   }
                   //end of getting the party data








               } //end of the try and catch
            catch (Exception ex){//this is for the exception of the type is as follows
                PrintLog("Exception of type is as as follows  "+ex.getMessage()+" while adding the items to the list"); //this is for the exception
            }
           //end of adding  it to the list


    }
    //end of the View Methods

    //this is for to manage the clear Button
    public void ManageClearButton(){
        //this is for the if part
        if(selectedProducts_list.size()>0){
            clear_textView.setVisibility(View.VISIBLE);
        }//end of the if part
        else{//this is for the else part
            clear_textView.setVisibility(View.GONE);
        }//end of the else part
    }
    //end of managing the clear button

    //this is for the Update the Product
    public void UpdateProduct(View view){

         //this is for the try and catch
         try{

             //this is for to check to which element we are going to update
             if(selectionIndexForSelectedProduct!=-1){


                 //this is for to pick the new data
                 //this is for the data
                 Product  product=productList.get(selectedProductList) ; //this is for the product
                 PartyWork  party=partyList.get(partySelectionPointer); //this is for the party
                 //end of  getting the data



                 //this is for the quantity
                 String quantity=productCounter.getText().toString();
                 int quantityValue=Integer.parseInt(quantity); //this is for the integer value
                 //end of the quantity



                 //this is for to check the quantity
                 if(quantityValue>=1){ //this is for the quantity value

                 }
                 else{//this  is for the else part
                     PrintingToast("Please Enter Some Valid  Quantity");
                     return;
                 }
                 //end of checking for the  quantity




                 SelectedProducts selectedProducts=selectedProducts_list.get(selectionIndexForSelectedProduct);
                 selectedProducts.setId(String.valueOf(product.getId()));
                 selectedProducts.setName(product.getName());
                 selectedProducts.setQuantity(quantity);
                 selectedProducts.setCompanyName(party.getName());
                 selectedProducts.setPartyId(String.valueOf(party.getId()));
                 PrintingToast("Updated");
                 //this is for the list adding
                 selectedProducts.setPartyId(String.valueOf(party.getId()));
                 //end of picking the new data



                 //this  is for to update the list
                 selectedProducts_list.set(selectionIndexForSelectedProduct,selectedProducts);
                 selectedProductListAdapter.notifyDataSetChanged();
                 //end of updating the list





             }//end of the if work
             else{//this is for the else part
                 PrintingToast("Please select an Item to update from Items List"); //this is for the Printing the Toast
             }
             //end of updating the product
             }//end of the try
         catch(Exception ex){  //this is for the catch
             PrintLog("Exception of type is as follows  "+ex.getMessage()+" while going for the update");
         }
         //end of the try and catch


    }
    //end of Update the Product

    //this  is for the Log
    private static final String TAG = "CartActivity";
    public void PrintLog(String Line){
        Log.d(TAG,Line); //this is for the Log
    }
    //end of the Log



    //this is for the Toast Printing
    public void PrintingToast(String Line){
        Toast.makeText(getApplicationContext(),Line,Toast.LENGTH_SHORT).show(); //this is for to show
    }
    //end of the Toast Printing




    //this is for to check either we are online or not
    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }
    //end of checking either we are online or not



    //this  is for  for to clear the list
    public void MakeListClear(View view){
        //this is for the try
        try{//this is for to make the list clear


            if(selectedProducts_list.size()>0){

            }
            else{
                PrintingToast("It is already empty");
                return;
            }



            //this is for the alert
            AlertDialog.Builder  builder=new AlertDialog.Builder(this);
            builder.setIcon(R.drawable.logo); //this is for the logo
            builder.setTitle("Confirmation"); //this
            builder.setMessage("Are you sure to clear order list");
            builder.setPositiveButton("Clear List", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                       //this  is for to clear the list
                       selectedProducts_list.clear();  //this is for to make it clear
                       selectedProductListAdapter.notifyDataSetChanged();
                       CountingSelection();
                       //end of clear the list

                }
            });
            builder.setNegativeButton("Cancell", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                     PrintingToast("Has been cancelled");
                }
            });
            //end of the alert


            //this is for the builder
            builder.show(); //this is for to show
            //end of the builder


           }//end  of the try
        catch (Exception ex){
           PrintLog("Exception of type is as follows  "+ex.getMessage()+"While making the list clear");
        }
        //end of the try and catch
    }
    //end of making the list clear



    //this is  for to remove the selected items
    public void RemoveSelectedItems(View view){
        //this is for to Remove Selected


        //this is for to check with the try and catch
        try{//this is for the try



            //this is for the alert
            AlertDialog.Builder  builder=new AlertDialog.Builder(this);
            builder.setIcon(R.drawable.logo); //this is for the logo
            builder.setTitle("Confirmation"); //this
            builder.setMessage("Are you sure to remove  Selected Products from  the order list");
            builder.setPositiveButton("Remove", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    //this  is for to clear the list
                    Iterator<SelectedProducts>  listOfProducts=selectedProducts_list.iterator(); //this is for the selected Products
                    //end of clear the list



                    //this is for to fix the things
                    while(listOfProducts.hasNext()){


                          //this is for the selected Product
                          SelectedProducts  p=listOfProducts.next();
                          //end of selected Product


                          //Is this is deletable
                          if(p.isSelected()){
                              listOfProducts.remove();
                          }
                          //end of is This deletable

                          //this  is for that selected item  has been changed
                          selectedProductListAdapter.notifyDataSetChanged();
                          //end of the selected  item has been changed


                           //this is for to rearrange
                            RearrangeArray();
                           //end of going for to rearrange



                    }
                    //end of fixing the things


                    //this  is for to manage the clear button
                    ManageClearButton();
                    CountingSelection();
                    //end of managing the clear button



                }
            });
            builder.setNegativeButton("Cancell", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    PrintingToast("Has been cancelled");

                    //this  is for to manage the clear button
                    ManageClearButton();
                    CountingSelection();
                    RearrangeArray();
                    //end of managing the clear button


                }
            });
            //end of the alert


            //this is for the builder
            builder.show(); //this is for to show
            //end of the builder





            //this  is for to manage the clear button
            ManageClearButton();
            CountingSelection();
            //end of managing the clear button



           }//end of the try
        catch (Exception ex){ //this  is for the catch
            PrintLog("Exception of type is as follows  "+ex.getMessage()+" while going for to remove items from the List"); //this is for the Log
        }
        //end of the try catch



    }
    //end of removing the selected items






    //this is for the function to get the JSON Array
    public JSONArray  GiveSelectedItemsArray(){

        JSONArray   jsonArray=null;


        //this is for to get the JSON Array

        try{//this  is for the try


            //this is for the initialization of the json array
            jsonArray=new JSONArray(); //this  is for the json array
            //end of the initialization of the json array





            //this is for to put into the json array
            for(SelectedProducts  selectedProducts: selectedProducts_list){  //this is for the selected Products


                //this is for the json Object
                JSONObject  jsonObject=new JSONObject(); //this is for the json Object
                //end of the json object





                //this is for to put the json Object
                jsonObject.put("productID",selectedProducts.getId());
                jsonObject.put("qty",selectedProducts.getQuantity());
                jsonObject.put("OrderMainMobileID",0);
                //end of putting the Json Object




                //this is for to put the data to the array
                jsonArray.put(jsonObject); //this is for the json array
                //end of going to put the data to the array




            }
            //end of going for to put into to the array











           }//end of the try
        catch(Exception ex){ //this is for the exception
            PrintLog("Exception of type is as follows while going to make the json array from the Selected Items"+ex.getMessage()); //this is for to Printing the Log
        }//end of the catch


        //end of the getting the JSON Array


        return jsonArray;
    }
    //end of getting the json array






    //this is for the cart activity
    public void  MakeOrder(View view){ //this is for the MakeOrder


        //this is for the list Printing
        if(selectedProducts_list.size()>0){ //this is for the selected List size

              try{
                  String jsonData=new Gson().toJson(selectedProducts_list); //this is for to

                  //this is for to Print the List
                  PrintLog("JSON Data is as follows  "+jsonData);

                  //this is for to check work

                  //this is for if We are online
                  if(isOnline()){ //this is for the that we online
                      //this is for to do are you sure
                      //this is for the global information to Work
                      SendOrder(GiveSelectedItemsArray());
                      //end of working for the global Work
                      //end of are you sure
                  }//end of the if in that work for the msLab
                  else{ //this is for the offline Work
                      //this is for the alert
                      AlertDialog.Builder  builder=new AlertDialog.Builder(CartActivity.this);
                      builder.setTitle("Alert");
                      builder.setMessage("Order saved offline successfully");
                      builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                          @Override
                          public void onClick(DialogInterface dialog, int which) {
                                String array=GiveSelectedItemsArray().toString();
                                //this is for the other Work
                              //this is for to post the order
                              try {//this is for the try
                                  //this is for to make the json Complete
                                  //this is for the JSONObject
                                  final org.json.JSONObject final_object = new org.json.JSONObject();
                                  //this is for to put the values
                                  try {


                                      final_object.put("sessionId", ""); //this is for the session
                                      final_object.put("partyID", partyList.get(partySelectionPointer).getId()); //this is for the
                                      final_object.put("orderDate", Utility.GiveMeCurrentDateForPost());
                                      final_object.put("salesManID", bhattiPreferencesManager.GetUserID());
                                      final_object.put("status", "Pending"); //this is for the ok
                                      final_object.put("Comments", productRemarks.getText().toString());
                                      final_object.put("loginName", "test");
                                      final_object.put("password", "test");
                                      final_object.put("orderDetail", GiveSelectedItemsArray());
                                      //end of the json Object


                                  }//end  of the try
                                  catch (Exception ex) { //this is for the to catch
                                      PrintLog("Exception of type is as follows  " + ex.getMessage()); //this  is for to Print the Log
                                  }
                                  //end of going to put the Values
                                  //end of making the json Complete
                                  //end of the others Work


                                  Orders orders = new Orders(getApplicationContext()); //this  is for the orders
                                  orders.InsertOrder(new OrderModel("", Utility.GiveMeCurrentDate(), final_object.toString()));
                                  PrintingToast("Has been saved");


                              }
                              catch (Exception ex){  //this is for the Exception
                                  PrintLog("The exception of type is as follows "+ex.getMessage()+" while going for to save the order to the database");  //this is for to Print the Log
                              }//end of the catch Work in that block code



                          }
                      });
                      builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                          @Override
                          public void onClick(DialogInterface dialog, int which) {
                               PrintingToast("Saving has been cancelled");
                          }
                      });
                      builder.show();
                      //end of the alert








                  }//end of the offline Work

                  //end of if we are online








                  //end of checking for the Work











                  //end of Printing the List
              }//end of the try
              catch (Exception ex){
                    PrintLog("Exception of type is as follows "+ex.getMessage()); //this is for the message for the Work
              }//end of the catch


        }//end of the SelectedProducts List
        else{
            PrintingToast("First Add Products To The List");
        }
        //end of list Printing


    }
    //end of the cart activity









    //this is for to go online to make the order
    public void SendOrder(final JSONArray jsonArray){ //this is for to put


        try{//this is for the online order

            //this is for the Alert
            AlertDialog.Builder  alertDialog=new AlertDialog.Builder(CartActivity.this); //this is for the Alert
            alertDialog.setTitle("Confirmation");
            alertDialog.setMessage("Are you sure to process. Items : "+jsonArray.length());
            alertDialog.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                      PrintingToast("Processing Your Order"); //this is for to show that we are processing your order
                      //this is for to get the Session and Process it further
                    try{
                        //this is for to show the Progress
                        ManageProgressDialog("Processing your Order",1);   //this is for to manage the Progress Dialog
                        //end of the Progress

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
                                    PrintLog("Session data is as follows "+line+"while id id as follows  "+String.valueOf(bhattiPreferencesManager.GetUserID()));
                                    //end of the Line Printing


                                    //now sending it to parse
                                    final   String session=parsers.ParseSession(line);
                                    //end of parsing



                                    //this is for if the session is not null
                                    if(session!=null){ //this is for if the session is not null
                                        handler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                bhattiPreferencesManager.SetUserSession(session);  //this is for to set the session
                                                PostActualOrder(session,jsonArray);
                                                //end  of getting the Party Data
                                            }
                                        }); //end of the





                                    }
                                    //end of if the session is not null
                                }//end of the try
                                catch (Exception ex){
                                    PrintLog("Exception of type is as follows  "+ex.getMessage());
                                }
                                //end of the try and catch
                            }
                        });
                        t.start();
                        //end of Thread
                    }//end of the try
                    catch (Exception ex){
                        PrintLog("Exception of type is  as follows  "+ex.getMessage()+" while fetching the Session Data");
                    }//end of the catch
                    //end  of the try and catch
                    //end of fetching the session data
                      //end of getting the Session and Process it further
                }
            });  //in case of positive

            alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    PrintingToast("Cancelled");
                }//end of the onClick
            }); //this is for to cancel it

            //this is for to show
            alertDialog.show(); //this is for to show the alert
            //end of going for to show the alert


            //end of the Alert




           }//end of the try
        catch(Exception ex){
            PrintLog("Exception of type is as follows  "+ex.getMessage()+" while going to post the order");
        }//end of the catch

    }
    //end of making the order online









    //this is for actual Post
    public void PostActualOrder(String Session,JSONArray jsonArray){

        //this is for to post the order
        try{//this is for the try

            //this is for to make the json Complete
            //this is for the JSONObject
            final org.json.JSONObject final_object=new  org.json.JSONObject();

            //this is for to put the values
            try{


                final_object.put("sessionId",Session); //this is for the session
                final_object.put("partyID",partyList.get(partySelectionPointer).getId()); //this is for the
                final_object.put("orderDate", Utility.GiveMeCurrentDateForPost());
                final_object.put("salesManID",bhattiPreferencesManager.GetUserID());
                final_object.put("status","Pending"); //this is for the ok
                final_object.put("Comments",productRemarks.getText().toString());
                final_object.put("loginName","test");
                final_object.put("password","test");
                final_object.put("orderDetail",jsonArray);
                //end of the json Object

            }//end  of the try
            catch (Exception ex){ //this is for the to catch
                PrintLog("Exception of type is as follows  "+ex.getMessage()); //this  is for to Print the Log
            }
            //end of going to put the Values
            //end of making the json Complete

            //this is a place for the Volley

            //this is for the Request Queue
            RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());

            //end of the Request Queue



            //this is for the string request
            StringRequest stringRequest= new StringRequest(Request.Method.POST, RESTFullApi.UrlToPOSTOrder(), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    ManageProgressDialog("",0);


                    //this is for the Bhatti Parsers
                    BhattiParsers bhattiParsers=new BhattiParsers();
                    String response_=bhattiParsers.GetProductOrderResponse(response);
                    ShowAlert("Alert",response_);
                    //end of the Bhatti Parsers


                    PrintLog("The response is as follows  "+response);
                }//end of the response
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            ManageProgressDialog("",0);
                            PrintLog("Error response is as follows  "+error);
                        }
                    }
            ){ //this is for the to override the methods
                @Override
                public byte[] getBody() throws com.android.volley.AuthFailureError {
                    String str = final_object.toString();
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

           }//end of the try
        catch(Exception ex){ //this is for the exception
            PrintLog("Exception of type  is as follows for the actual Post "+ex.getMessage());
            ManageProgressDialog("",0);
        }
        //end of the posting the order


    }
    //end of the actual







    //this is for to rearrange the array
    public  void RearrangeArray(){

        //this is for to rearrange
        try{


                //this is for to list
                int i=1;   //this is for the counter
                for(SelectedProducts selectedProducts:selectedProducts_list){
                           selectedProducts.setCounter(i); //this is for the counter
                           selectedProductListAdapter.notifyDataSetChanged();  //this  is for to invoke the things for to notify the adapter
                            i++;
                }
                //end of the list


           }//end of the try
        catch (Exception ex){//this is for catch
               PrintLog("Exception of type is as follows "+ex.getMessage()+"While going to Rearrange the Array");
        }
        //end of to rearrange


    }
    //end of going to rearrange the array






    //this is for to check either we have something
    public void checkForToManageAddButton(){


           //this  is for the try
            try{//this is for the try


                //this  is for to check the other things
                String partyName=partyAutoComplete.getText().toString();  //this is for the party Name
                String productName=productAutoComplete.getText().toString(); //this is for the product Name
                String quantity=  productCounter.getText().toString(); //this is for  the quantity
                //end of checking for the other things





                //this is for to manage the Add Button
                if(
                                (partyName.length()>0)  &&
                                (productName.length()>0) &&
                                (quantity.length()>0)

                        ){//this is for to check for the empty work

                        //this is for to show
                        addButton.setVisibility(View.VISIBLE);  //this is for to make it visiable
                        //end of for to show

                }
                else{
                    addButton.setVisibility(View.GONE);
                }
                //end of manage the Add Button





               }//end of the try
            catch (Exception ex){ //this  is for the try
                PrintLog("Exception of type is as follows  "+ex.getMessage()+" while going to check for the Widgets ");
            }
           //end of the try

    }
    //end of checking for something





    //this is for to show the alert
    public void ShowAlert(String title,String message){

        //this is for the try
        try{

            AlertDialog.Builder builder=new AlertDialog.Builder(CartActivity.this); //this is for to show Alert
            builder.setTitle(title);
            builder.setMessage(message);
            builder.setCancelable(true);
            builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.show();
           }//end of the try
        catch (Exception ex){
           PrintLog("Exception of type is as follows "+ex.getMessage()+" while going for to show Alert");
        }
        //end of the try and catch

    }
    //end of to show the alert





    //this is for to get the data from the called Activity

    //this  is for  the fields
    public ProductDatabase productDatabase_01; //this is for the orders
    public String id,lines; //ths is for the id and lines for the large data
    //end of the fields

    //this  is for the method
    public void GetValuesFromPreviousActivity(){

        //this  is for the try and catch
        try{

                 //this  is for to get the data
                 Bundle b=getIntent().getExtras();
                 id=b.getString("id"); //this is for to get the id
                 lines=b.getString("lines"); //this is for to get the lines
                 //end of getting the data



                 //this is for to get the things  to the true order

                 //this is for to Parse
                 JSONObject   jsonData=new JSONObject(lines); //this is for the json Object
                 String comments=jsonData.getString("Comments");  //this is for the comments
                 String partyID=jsonData.getString("partyID");  //this is for the partyID
                 String partyName="";
                 //end of getting the Parse Data



                 //this is for the Product Array
                 JSONArray  jsonArray=jsonData.getJSONArray("orderDetail");  //this is for the json Array
                 //end of the products Array


                 //this is for to run the loop and fill the data
                 productDatabase_01= new ProductDatabase(getApplicationContext()) ; //this  is for the Orders for the Work
                 //this is for to get the values


            //this  is for to trace the Party Location
            int location=0;

            //this is for to trace the Party Location
            for(int i=0;  i<partyList.size();i++){

                //this is for the if for the More Work
                if(String.valueOf(partyList.get(i).getId()).equals(partyID)){
                    //this is for the value

                    //end of the value in that Code
                    partyName=partyList.get(i).getName();
                    partySelectionPointer=i;
                    partyAutoComplete.setText(partyName);
                    break; //this is for the Break
                }
                //end of the if for the More Work


            }
            //end of the Party Location



            //end of going for to trace the Party Location


                 //this is for the Loop
                  for(int i=0; i<jsonArray.length(); i++){ //this is for the size


                      //this is for to get the current Object
                      JSONObject  currentObject=jsonArray.getJSONObject(i); //this  is for the json Object
                      //end of getting the Current Object


                      //this  is for the Local variables
                      String id_= currentObject.getString("productID") ;
                      String qty_=currentObject.getString("qty"); //this is for the quantity
                      //end of the Local Variables



                      //this  is for the temporary data
                      SelectedProducts  selectedProducts=new SelectedProducts(id_, productDatabase_01.GiveMeProductName(id_),qty_,partyName);  //this  is for Selection of the Products
                      selectedProducts.setCounter(i+1);
                      selectedProducts.setPartyId(partyID);
                      //end of the temporary data



                      //this is for to add them to the List
                      selectedProducts_list.add(selectedProducts); //this is for the selectedProductsList
                      //end of adding it to the List




                  }
                 //end  of the Loop



                 //end of filling the data for the loop




                 //this is for to notify for the change
                 selectedProductListAdapter.notifyDataSetChanged();
                 //end of notifying for the change


                 //this is for to set the  comments and the other things
                 productRemarks.setText(comments);







                 //end of setting the comments and the other things





                 //end  of getting the things to the true order



                 //this is for the View Button
                 View  updateButton=(View)  findViewById(R.id.btn_update);
                 updateButton.setVisibility(View.VISIBLE);
                 //end of the View Button


                 //this is for the View Button
                 View  processButton=(View)  findViewById(R.id.process);
                 processButton.setVisibility(View.GONE);
                 //end of the View Button





           }//end of the try
        catch(Exception ex){ //this is for to catch the Exception
            PrintLog("Exception of type is as follows  "+ex.getMessage()); //this is for to Print the Log
        }
        //end of the try and catch


    }
    //end of the method for the Work





    //end of calling  getting the called Activity

    //this is for to make the order
    public void updateOrder(View view){  //this is for to update the order

        try{//this  is for the try



            //this is for to get the size
            if(selectedProducts_list.size()>0){

            }
            else{
                PrintingToast("Please First Add Products  To The List");
                return;
            }
            //end of getting the Size




            String array=GiveSelectedItemsArray().toString();


            //this is for the other Work
            //this is for to post the order
            try {//this is for the try


                //this is for to make the json Complete
                //this is for the JSONObject
                final org.json.JSONObject final_object = new org.json.JSONObject();


                //this is for to put the values
                try {


                    final_object.put("sessionId", ""); //this is for the session
                    final_object.put("partyID", partyList.get(partySelectionPointer).getId()); //this is for the
                    final_object.put("orderDate", Utility.GiveMeCurrentDateForPost());
                    final_object.put("salesManID", bhattiPreferencesManager.GetUserID());
                    final_object.put("status", "Pending"); //this is for the ok
                    final_object.put("Comments", productRemarks.getText().toString());
                    final_object.put("loginName", "test");
                    final_object.put("password", "test");
                    final_object.put("orderDetail", GiveSelectedItemsArray());
                    //end of the json Object


                }//end  of the try
                catch (Exception ex) { //this is for the to catch
                    PrintLog("Exception of type is as follows  " + ex.getMessage()); //this  is for to Print the Log
                }
                //end of going to put the Values
                //end of making the json Complete
                //end of the others Work


                Orders orders = new Orders(getApplicationContext()); //this  is for the orders
                OrderModel orderModel= new OrderModel(id, Utility.GiveMeCurrentDate(), final_object.toString());
                orders.UpdateData(orderModel); //this is for to update
                PrintingToast("Has been Updated");

                setResult(RESULT_OK);
                finish();



            }
            catch (Exception ex){  //this is for the Exception
                PrintLog("The exception of type is as follows "+ex.getMessage()+" while going for to save the order to the database");  //this is for to Print the Log
            }//end of the catch Work in that block code








           }//end of the try
           catch(Exception ex){ //this  is for the catch
               PrintLog("Exception of type is as follows  "+ex.getMessage()+" while going for to update the Order");
           }//end of the catch
    }
    //end of the making the order

    //this is for the View Methods
    public void Exit(View view){
        finish();
    }
    //end of the Methods

}//end of the chart activity
