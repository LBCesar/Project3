package com.example.project3;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class carInfo extends AppCompatActivity {

    private TextView makeModel;
    private TextView price;
    private TextView date;
    private TextView summary;
    private TextView mileage;
    private ImageView carPic;

    public static ArrayList<MoreDetails> moreDetailsCarList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_info);

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
//            price.setText("$ " + Double.toString(co.price));
            price.setText(co.getPrice());
            date.setText(co.vin_number);
            mileage.setText(" " + co.mileage + " miles");
            summary.setText(co.veh_description);

            //carPic.setImageResource(co.carPic);
            Picasso.get()
                    .load("https://i.imgur.com/gwy9G6s.jpg")
//                    .resize(600,600)
                    .into(carPic);
        }


        TextView textView = findViewById(R.id.mdd);
//        MoreDetails moreDetails = new MoreDetails();
        moreDetailsCarList = new ArrayList<>();
//        moreDetailsCarList.add(new MoreDetails());

        int url_ID = co.id; // this id will be used for the url, must come from the 3rd link or array3 id
        String makeURL = "https://thawing-beach-68207.herokuapp.com/cars/" + url_ID;
        DataBaseHelper dbh4 = new DataBaseHelper(321, makeURL, 321);
        dbh4.execute();

//        moreDetailsCarList = new ArrayList<>();
//        moreDetailsCarList.add(new MoreDetails());

        MoreDetails moreDetails = new MoreDetails();
        for (int i=0; i<moreDetailsCarList.size();i++){
            moreDetails = moreDetailsCarList.get(i);
            Log.d("myTag", moreDetails.vehicle_url);
            textView.setText(moreDetails.image_url);
//            textView.setText(moreDetails.toString());
        }

    }


} // end carInfo class







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