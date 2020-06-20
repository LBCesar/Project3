package com.example.project3;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class carInfo extends AppCompatActivity {
    private TextView makeModel;
    private TextView price;
    private TextView date;
    private TextView summary;
    private TextView mileage;
    private ImageView carPic;

    private Button buttonMoreDetail;
//    private TextView Id_for_btnDetail;
    private int myID;

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


        //CarObject co=new CarObject();
        DetailCar co = new DetailCar();

        if ((DetailCar) getIntent().getSerializableExtra("myCar") != null) {
            co = (DetailCar) getIntent().getSerializableExtra("myCar");
            makeModel.setText( co.vehicle_make + " _ " + co.model);
            price.setText("$ " + Double.toString(co.price) + "0");
            date.setText(co.vin_number);
            mileage.setText(" " + co.mileage + " miles");
            summary.setText(co.veh_description);

            Picasso.get()
                    .load("https://i.imgur.com/gwy9G6s.jpg")
                    .resize(600,600)
                    .into(carPic);
            //carPic.setImageResource(co.carPic);
        }

        // button for more detail
//        buttonMoreDetail = findViewById(R.id.btnMoreDetails);
////        final String myID = (String) Id_for_btnDetail.getText().toString();
//
//        buttonMoreDetail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), MoreDetailsActivity.class);
////                intent.putExtra("id", myID);
//                startActivity(intent);
//            }
//        });

    }
}