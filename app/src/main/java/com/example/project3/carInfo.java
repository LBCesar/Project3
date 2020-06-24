package com.example.project3;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class carInfo extends AppCompatActivity {

    // Initialize all the key variables
    private TextView makeModel;
    private TextView price;
    private TextView date;
    private TextView summary;
    private TextView mileage;
    private TextView textView;
    private ImageView carPic;

    // 4th arraylist which holds the instance of MoreDetails class (retrieved from 4th link)
    public static ArrayList<MoreDetails> moreDetailsCarList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_info);

        // Assign the variables to their respective id
        makeModel = findViewById(R.id.makeModel);
        price = findViewById(R.id.price);
        date = findViewById(R.id.lastUpdated);
        summary = findViewById(R.id.carSummary);
        mileage = findViewById(R.id.mileage);
        carPic = findViewById(R.id.carPic);

        DetailCar co = new DetailCar();

        if ((DetailCar) getIntent().getSerializableExtra("myCar") != null) {
            co = (DetailCar) getIntent().getSerializableExtra("myCar");

            makeModel.setText( co.vehicle_make + " _ " + co.model);
//          price.setText("$ " + Double.toString(co.price));
            price.setText(co.getPrice());   // This will give the price in corrected formatted amount
            date.setText(co.vin_number);
            mileage.setText(" " + co.mileage + " miles");
            summary.setText(co.veh_description);

            // Tested URL: "https://i.imgur.com/gwy9G6s.jpg"
            // enable the sizes to position the images correctly
            if(URLUtil.isValidUrl(co.image_url)) {
                Picasso.get()
                        .load(co.image_url)
//                    .resize(600,600)
//                        .fit()
                        .error(R.drawable.coming_soon)
                        .into(carPic);
            }
        }

        moreDetailsCarList = new ArrayList<>();
//      moreDetailsCarList.add(new MoreDetails());

        int url_ID = co.id;         // this id will be used for the url, must come from the 3rd link or array3 id
        String makeURL = "https://thawing-beach-68207.herokuapp.com/cars/" + url_ID;
        DataBaseHelper dbh4 = new DataBaseHelper(321, makeURL, 321);
        try {
            dbh4.execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

//        moreDetailsCarList.add(new MoreDetails());
        textView = findViewById(R.id.mdd);
        textView.setVisibility(TextView.INVISIBLE);

        Button btn_moredetails = findViewById(R.id.btn_moredetails);
        btn_moredetails.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if (textView.getVisibility() == View.VISIBLE){
                    textView.setText(" ");
                    textView.setVisibility(TextView.INVISIBLE);
                }
                else{
                    textView.setVisibility(TextView.VISIBLE);
                    textView.setText(moreDetailsCarList.get(0).toString());
                }
            }
        });

    }

} // end carInfo class









/* -------------------------------------------------------------------------------------------------
/*
    *For self only
   Additional Testing stuff
 */

/* -------------------------------------------------------------------------------------------------
//int url_ID = 3484; // this id will be used for the url, must come from the 3rd link or array3 i
//                    int url_ID=co.id;
//                    String makeURL = "https://thawing-beach-68207.herokuapp.com/cars/" + url_ID;
//                    DataBaseHelper dbh4 = new DataBaseHelper(321, makeURL, 321);
//                    dbh4.execute();
//                    try {
//                        dbh4.execute().get();
//                    } catch (ExecutionException e) {
//                        e.printStackTrace();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//co.adder(MainActivity.moreDetailsCarList.get(0));
//co.adder(MainActivity.mdMain);
//co.adder2();


 */
/* ------------------------------------------------------------------------------------------------
//        moreDetailsCarList = new ArrayList<>();
//        moreDetailsCarList.add(new MoreDetails());

//        MoreDetails moreDetails = new MoreDetails();

//        textView.setText(car1);

// Frerrai 430 first one
//        for (int i=0; i<moreDetailsCarList.size();i++){
//            textView.setText(moreDetailsCarList.get(i).image_url);
////            moreDetails = moreDetailsCarList.get(i);
////            Log.d("myTag", moreDetails.vehicle_url);
////            textView.setText(moreDetails.image_url);
////            textView.setText(moreDetails.toString());
//        }

 */


/* -------------------------------------------------------------------------------------------------
// spinner
//        Spinner spinny = findViewById(R.id.spinny);
//
//        ArrayAdapter<MoreDetails> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, moreDetailsCarList);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinny.setAdapter(adapter);
//
//        StringBuilder shit = new StringBuilder();
//        for (MoreDetails m: moreDetailsCarList){
//            shit.append(m.image_url).append("\n");
//        }
//        textView.setText(shit.toString());



 */
/* ------------------------------------------------------------------------------------------------
         button for more detail
        buttonMoreDetail = findViewById(R.id.btnMoreDetails);
//        final String myID = (String) Id_for_btnDetail.getText().toString();

        buttonMoreDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MoreDetailsActivity.class);
//                intent.putExtra("id", myID);
                startActivity(intent);
            }
        });
 */