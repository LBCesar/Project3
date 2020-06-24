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

        int url_ID = co.id;         // this id will be used for the url, must come from the 3rd link or array3 id
        String makeURL = "https://thawing-beach-68207.herokuapp.com/cars/" + url_ID;
        DataBaseHelper dbh4 = new DataBaseHelper(321, makeURL, 321);
        try {
            dbh4.execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

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
