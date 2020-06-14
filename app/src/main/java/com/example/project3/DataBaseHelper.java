package com.example.project3;

import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

//import static com.example.project3.MainActivity.makeData;

public class DataBaseHelper extends AsyncTask<Void, Void, Void> {

    String data = "";   // final dbmake data to pass to the MainActivity
    String dataParsed = "";
    String singleParsed = "";

    @Override
    protected Void doInBackground(Void... voids) {

        try {   // connect and get the content from the heroku url,
            URL url = new URL("https://thawing-beach-68207.herokuapp.com/carmakes");
            HttpURLConnection connect = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connect.getInputStream();

            // read the data from the url
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String read = "";

            // use a loop to read the data and put it in "data" variable
            while(read != null){
                read = bufferedReader.readLine();
                data = data + read;
            }
            // needa parse this into right form

        } catch (IOException e) {
            e.printStackTrace();
            }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        // this does get the content frm the database
//        MainActivity.makeData = this.data;

        // tryna parse the db content and put in jsonarray which will hold car objects
        List<Car> car = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(data);

            for (int i=0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Car myCar = new Car();
                myCar.id = jsonObject.getInt("id");
                myCar.make = jsonObject.getString("vehicle_make");
                car.add(myCar);

                MainActivity.car_make.addAll(car);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
