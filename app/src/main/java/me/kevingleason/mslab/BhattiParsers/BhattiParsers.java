package me.kevingleason.mslab.BhattiParsers;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import me.kevingleason.mslab.ModelClasses.PartyWork;
import me.kevingleason.mslab.ModelClasses.Product;
import me.kevingleason.mslab.ModelClasses.User;

/**
 * Created by Mashhood on 7/1/2018.
 */

public class BhattiParsers { //this si for the Parsers


    //this is for to get the User
    public User  GiveMeUser(String Line){

        //this is for the temporary User
        User user=null;
        //end of getting the temporary User



        //this is for the user parsers
         try{//this is for the try block





             //this is for to get the JSON Object
             JSONObject   jsonObject_forUser=new JSONObject(Line); //this is for the JSON Object
             //end of getting the JOSN Object



             //this is for to check success
             boolean succeeded=jsonObject_forUser.getBoolean("succeeded");
             PrintLog("Success level "+succeeded);
             //end of checking for the success



             //this is for to decide either information is ok or do not
             if(!succeeded){ //it means it was not ok
                 return  user;
             }
             //end of going for to get the information


             else{ //it means we can move forward




                 //this is for to get the values
                 //this is for to get the Result Object
                 JSONObject result=jsonObject_forUser.getJSONObject("result");
                 //end getting the Result Object


                 //this is for getting the required data
                 int id=result.getInt("id");
                 String name=result.getString("name");
                 String address=result.getString("address");
                 String  contactPerson=result.getString("contactPerson");
                 String  mobile=result.getString("mobile");
                 String  mobileAlternate =result.getString("mobileAlternate");
                 String   code =result.getString("code");
                 String  loginName=result.getString("loginName");
                 String  password=result.getString("password"); ;
                 //end of getting the required data
                 //end of getting the values

                 user=new User(id,name,address,contactPerson,mobile,mobileAlternate,code,loginName,password);


             }//end of initializing the user






               //this is for to return the user
                return  user;
               //end of going to return the user





            }//end of the try block
         catch (Exception ex){
                 PrintLog("Exception  of type is as follows "+ex.getMessage()+ "While going  to parse the user data");
         }//end of the catch
        //end of the user parsers

        return null;
    }
    //end of getting the User




    //this is for to parse the session
    public String  ParseSession(String line){ //this is for to parse the string

        //this  is for the session
        String session="";
        //end of the session

        //this is for to parse it
        try{//this is for to try


            JSONObject  jsonObject=new JSONObject(line); //this is for the json Object
            session=jsonObject.getString("sessionID");


            return  session; //this is for the session


           }//end  of the try
        catch (Exception ex){
            PrintLog("Exception of the ");
        }
        //end  of parsing the session


        return  session; //this is for to return the session
    }
    //end of parsing the session




     //this is for to parse the  Party Data
    public List<PartyWork>  ParseParties(String line){

        List<PartyWork>  list=new ArrayList<PartyWork>(); //this is for the party work


        //this is for the exception handling
        try{

            //this is for to parse the data
            JSONObject   jsonObject=new JSONObject(line); //this  is for the json object


            //this  is for the to ensure the lines
            PrintLog("The actual Party List is as follows  "+line);
            //end of going to ensure the lines for the parties



            //this is for to fetch the data
            JSONArray partyArray=jsonObject.getJSONArray("result"); //this is for to fetch the json array


            //this  is for to loop out
            for(int i=0; i<partyArray.length(); i++){//this is for the increment in the array



                //this  is for to get the items
                try{
                    //this is for to get the one Object
                    JSONObject  anItem=partyArray.getJSONObject(i); //this is for to get the json object
                    //end of getting one Object



                    //this is for to get the  items
                    long id=anItem.getLong("id"); //this is for the id
                    String name=anItem.getString("name"); //this is for to get the name
                    String address=anItem.getString("address"); //this  is for the address
                    Object contactPerson=  Filter(anItem.getString("contactPerson")); //this is for contactPerson
                    Object mobile= Filter(anItem.getString("mobile")); //this is for the mobile
                    long saleManID=anItem.getLong("saleManID"); //this is for the saleManId
                    long discountPercentage=anItem.getLong("discountPercentage"); //this is for the discount percentage
                    String code=anItem.getString("code"); //this is for code
                    double balance=anItem.getDouble("balance");
                    //end of getting the items




                    //this is for to set an Object
                    PartyWork  partyWork=new PartyWork(); //this is for the party Work
                    partyWork.setId(id);  //this is for the party Id
                    partyWork.setName(name);  //this  is for the name
                    partyWork.setAddress(address);  //this is for address
                    partyWork.setContactPerson(((contactPerson!=null)? (String)contactPerson: ""));  //this is for the contact person
                    partyWork.setMobile(((mobile!=null)? (String) mobile: ""));  //this is for the mobile
                    partyWork.setSaleManID(saleManID); //this is for the saleManId
                    partyWork.setDiscountPercentage(discountPercentage);  //this is for the discount Percentage
                    partyWork.setCode(code);
                    //end of setting an Object





                    //this is for to add the object to the list
                    list.add(partyWork); //this is for to add the object to the list
                    //end of adding the object to the list
                }//end of the try
                catch (Exception ex){
                    PrintLog("Exception of type is as follows  "+ex.getMessage()+" while parsing the data party Data");
                }
                //end of getting the items








            } //end of looping through it


            //end of fetching the data


            //end of parsing the data



           }//end of the try
        catch (Exception ex){
           PrintLog("Exception of type is as follows  "+ex.getMessage()+" while parsing the party JSON data");
        }
        //end of handling the exception



        return list; //this is for to return the line
    }
     //end of parsing the Party Data





      //this is for to get the Product List
      public List<Product>  ParseProduct(String line){
          List<Product>   productList=new ArrayList<Product>(); //this is for the product list


          //this  is for the product List
          try{//this  is for the try
              //this is for to get the JSON Object
              JSONObject  object=new JSONObject(line); //this is for the json Object

              //this is for to fetch the array
              JSONArray   jsonArray=object.getJSONArray("result"); //this is for to get the json Array
              //end of fetching the array




              //this is for the loop through
              for(int i=0;i<jsonArray.length(); i++){ //this is  for the start of the loop


                  JSONObject  item=(JSONObject) jsonArray.get(i); //this is for to get the current item

                  //this is for to get the items
                  long id=item.getLong("id");
                  String name=( (item.getString("name") !=null)? ((String)item.getString("name")): "" );
                  String code=( (item.getString("code") !=null)? ((String)item.getString("code")): "" );
                  long typeID=item.getLong("typeID");
                  double purchasePrice=item.getDouble("purchasePrice");
                  double salePrice=item.getDouble("salePrice");
                  String size=( (item.getString("size") !=null)? ((String)item.getString("size")): "" );
                  String category=( (item.getString("uom") !=null)? ((String)item.getString("category")): "" );
                  String uom=( (item.getString("uom") !=null)? ((String)item.getString("uom")): "" );
                  //end of getting the items





                  //this is for to make the JSON Object
                  Product  product=new Product(); //this is for the product
                  product.setId(id); //this  is for the id
                  product.setCode(code);
                  product.setName(name);
                  product.setTypeID(typeID);
                  product.setPurchasePrice(purchasePrice);
                  product.setSalePrice(salePrice);
                  product.setSize(size);
                  product.setCategory(category);
                  product.setUom(uom);
                  //end of JSON Object





                  //this  is for to add it to the list
                  productList.add(product); //this is for the product list
                  //end  of adding it to the list




              }
              //end  of the loop through
             }//end of the try
          catch (Exception ex){
              PrintLog("Exception of type is as follows "+ex.getMessage()+" while parsing the product data");
          }
          //end of the product List


          return productList;
      }
      //end of getting the Product List




    //this is for the Log
     private static final String TAG = "BhattiParsers";

     //this is for to Print the Log
     public void PrintLog(String Line){
         Log.d(TAG,Line); //this is  for the Line
     }
     //end of Print the Log


    //end of Printing the Log




    //this  is for to get the filtered data
    public Object  Filter(Object  obj){
        String value=""; //this is for the value

        if(obj !=null ){
            return obj;
        }

        return  value;
    }
    //end of getting the filtered data




    //this is for to Parse the Data for the  order
    public  String GetProductOrderResponse(String lines){
        String response=null;


        //this is for the try catch
        try{

            JSONObject  jsonObject=new JSONObject(lines); //this is for the JSONObject


            //this is for the boolean value
            boolean  flag=jsonObject.getBoolean("succeeded");
            //end of the boolean value


            if(flag){
                response="Order has been saved and will be approved by Admin";
            }
            else{
                response=jsonObject.getString("erroressage");
            }//end of the else




        }//end of the try
        catch(Exception ex){
            PrintLog("Exception of type is as follows  "+ex.getMessage());
        }//end of the catch
        //end of the try and catch

        return response;
    }
    //end of the Parse the Data for the order




    //this is for to Parse the Data for the  order
    public  boolean GetProductOrderBooleanResponse(String lines){
        boolean  flag=false;


        //this is for the try catch
        try{

            JSONObject  jsonObject=new JSONObject(lines); //this is for the JSONObject


            //this is for the boolean value
              flag=jsonObject.getBoolean("succeeded");
            //end of the boolean value


            return  flag;




        }//end of the try
        catch(Exception ex){
            PrintLog("Exception of type is as follows  "+ex.getMessage());
        }//end of the catch
        //end of the try and catch

        return flag;
    }
    //end of the Parse the Data for the order



}//end of the BhattiParsers
