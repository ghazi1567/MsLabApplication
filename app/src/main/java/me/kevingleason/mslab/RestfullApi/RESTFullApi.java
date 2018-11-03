package me.kevingleason.mslab.RestfullApi;

/**
 * Created by Mashhood on 7/1/2018.
 */

public class RESTFullApi {

    //this is for the static class fields
    public static String baseUrl="http://18.188.127.105:5000/api/Api/"; //this is for the base url
    //end of the static class fields



    //this is for the static Methods
    public static String GetUrlAuth(String name,String password){
        String url=baseUrl+"AuthSalesMan/"+name+"/"+password;
        return  url; //this is for to return the url
    }
    //end of the static Methods

    //this is for to get the url to get the party List
    public static   String GetUrlForPartyList(String session,String partyId){
        String url=baseUrl+"GetParty/"+session+"/"+partyId;
        return url;
    }
    //end of the url to get the party List



    //this is for to get the Session Id
    public  static String GetSessionId(String salesManId){
        String  url=baseUrl+"GetSession/"+salesManId;
        return url;
    }
    //end of getting the Session Id


    //this is for to get the Products List
    public static  String GetProductUrl(String  session, String salesId){
         String url=baseUrl+"GetProduct/"+session+"/"+salesId;
         return  url;
    }
    //end of getting the Products List




    //this is for to get the url  to post order
    public static  String  UrlToPOSTOrder(){
        String url=baseUrl+"PlaceOrder";
        return  url;
    }
    //end of posting the order




    //this is for To Post  Tracking
    public static  String  GetTracking(){
        String url=baseUrl+"PuchLocation";
        return   url;
    }
    //end of Going To Tracking






}//end of the RESTFulApi
