package com.example.project3;

import android.net.Uri;
import android.os.AsyncTask;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class DataBaseHelper extends AsyncTask<Void, Void, Void> {
    public boolean help=false;
    public int ID;
    public String myURL;
    public int detail;
    String data = "";
public ArrayList<String> s;
public ArrayList<DetailCar>dc;
//    public static List<Car> car;

    // Constructor
    public DataBaseHelper(int id, String url, int d){
        this.ID = id;
        this.myURL = url;
        this.detail = d;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        try {   // connect and get the content from the Heroku url
            URL url = new URL(myURL);
            HttpURLConnection connect = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connect.getInputStream();

            // read the data from the url
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String read = "";

            // use a loop to read the data and put it in "data" variable
            while(read!=null){
                read = bufferedReader.readLine();
                data = data + read;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

//        car = new ArrayList<>();

        if (ID == 0 && detail == 0){    // 1st link parsing
            try {
                JSONArray jsonArray = new JSONArray(data);

                for (int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Car myCar = new Car();
                    myCar.id = jsonObject.getInt("id");
                    myCar.make = jsonObject.getString("vehicle_make");
//                car.add(myCar);
                    MainActivity.newCarList.add(myCar);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }   // end if statement

        else if (ID != 0 && detail == 0){   // 2nd link parsing
            MainActivity.carModelList.clear();
            MainActivity.carModelList.add(new CarModel( 999, " ", " "));    // fix bug
            try {
                JSONArray jsonArray = new JSONArray(data);

                for (int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    CarModel myModel = new CarModel();
                    myModel.id = jsonObject.getInt("id");
                    myModel.model = jsonObject.getString("model");
                    myModel.vehicle_make_id = jsonObject.getString("vehicle_make_id");
                    MainActivity.carModelList.add(myModel);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        // added for the 4th link parsing. this goes into moreDetailsCarList into MoreDetailsActivity class
        else if (ID == 321 && detail == 321) {     // 4th link parsing
            try {
                JSONArray jsonArray = new JSONArray(data);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    MoreDetails moreDetails = new MoreDetails();
                    moreDetails.carcondition_id = jsonObject.getInt("carcondition_id");
                    moreDetails.color_id = jsonObject.getInt("color_id");
                    moreDetails.content_local_url = jsonObject.getString("content_local_url");
                    moreDetails.content_url = jsonObject.getString("content_url");
                    moreDetails.created_at = jsonObject.getString("created_at");
                    moreDetails.currency_id = jsonObject.getInt("currency_id");
                    moreDetails.id = jsonObject.getInt("id");
                    moreDetails.image_local_url = jsonObject.getString("image_local_url");
                    moreDetails.image_url = jsonObject.getString("image_url");
                    moreDetails.is_active = jsonObject.getString("is_active");
                    moreDetails.mileage = jsonObject.getInt("mileage");
                    moreDetails.onlinecardealer_id = jsonObject.getInt("onlinecardealer_id");
                    moreDetails.price = jsonObject.getDouble("price");
                    moreDetails.seller_address = jsonObject.getString("seller_address");
                    moreDetails.seller_address_locality = jsonObject.getString("seller_address_locality");
                    moreDetails.seller_address_region = jsonObject.getString("seller_address_region");
                    moreDetails.seller_name = jsonObject.getString("seller_name");
                    moreDetails.seller_telnumber = jsonObject.getString("seller_telnumber");
                    moreDetails.updated_at = jsonObject.getString("updated_at");
                    moreDetails.veh_description = jsonObject.getString("veh_description");
                    moreDetails.vehicle_make_id = jsonObject.getInt("vehicle_make_id");
                    moreDetails.vehicle_model_id = jsonObject.getInt("vehicle_model_id");
                    moreDetails.vehicle_url = jsonObject.getString("vehicle_url");
                    moreDetails.vin_number = jsonObject.getString("vin_number");
                    moreDetails.zipcode_id = jsonObject.getInt("zipcode_id");

                    MoreDetailsActivity.moreDetailsCarList.add(moreDetails);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


//        else if (ID == 9933 && detail == 1){
        else {              // 3rd link parsing
            help=false;
            //helper();
            try {
                JSONObject key = new JSONObject(data);
                JSONArray jsonKey = key.getJSONArray("lists");
                //MainActivity.detailCarList.clear();

                for (int i = 0; i < jsonKey.length(); i++) {
                    JSONObject jsonObject = jsonKey.getJSONObject(i);
                    DetailCar myDetailCar = new DetailCar();
                    myDetailCar.color = jsonObject.getString("color");
                    myDetailCar.created_at = jsonObject.getString("created_at");
                    myDetailCar.id = jsonObject.getInt("id");
                    myDetailCar.image_url = jsonObject.getString("image_url");
                    myDetailCar.mileage = jsonObject.getInt("mileage");
                    myDetailCar.model = jsonObject.getString("model");
                    myDetailCar.price = jsonObject.getDouble("price");
                    myDetailCar.veh_description = jsonObject.getString("veh_description");
                    myDetailCar.vehicle_make = jsonObject.getString("vehicle_make");
                    myDetailCar.vehicle_url = jsonObject.getString("vehicle_url");
                    myDetailCar.vin_number = jsonObject.getString("vin_number");
                   // dc.add(myDetailCar);
                    MainActivity.detailCarList.add(myDetailCar);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            help=true;

        }

//
       // MainActivity.f

//return dc;
    } //  endPostExecute method
//public String helper(){
//        String s="";
//        while(help==false){
//
//        }
//        return s;
//}

} // end DataBaseHelper class
