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
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class DataBaseHelper extends AsyncTask<Void, Void, Void> {

    public int ID;
    public String myURL;
    public int detail;
    String data = "";
public ArrayList<String> s;
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

        if (ID == 0 && detail == 0){
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

        else if (ID != 0 && detail == 0){
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

//        else if (ID == 9933 && detail == 1){
        else {
            try {
                JSONObject key = new JSONObject(data);
                JSONArray jsonKey = key.getJSONArray("lists");

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

                    MainActivity.detailCarList.add(myDetailCar);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


    } //  endPostExecute method


} // end DataBaseHelper class
