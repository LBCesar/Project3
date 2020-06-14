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

import static com.example.project3.MainActivity.makeData;

public class DataBaseHelper extends AsyncTask<Void, Void, Void> {

    String data = "";

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
            while(read!=null){
                read = bufferedReader.readLine();
                data = data + read;
            }

            // needa parse this into right form
//            JSONArray jsonArray = new JSONArray(data);
//            for (int i =0; i<jsonArray.length();i++){
//                JSONObject object = (JSONObject) jsonArray.get(i);
//
//            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        MainActivity.makeData = this.data;
    }
}
