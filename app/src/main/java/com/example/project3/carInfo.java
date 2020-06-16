package com.example.project3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class carInfo extends AppCompatActivity {
    private TextView makeModel;
    private TextView price;
    private TextView date;
    private TextView summary;
    private ImageView carPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_info);


        makeModel=findViewById(R.id.makeModel);
        price=findViewById(R.id.price);
        date=findViewById(R.id.lastUpdated);
        summary=findViewById(R.id.carSummary);
        carPic=findViewById(R.id.carPic);



        Intent intent=getIntent();
        CarObject co=new CarObject();


        if ((CarObject) getIntent().getSerializableExtra("myCar") != null)
        {
           // credentials =  (HashMap<String, UserData>) getIntent().getSerializableExtra("data");
            co = (CarObject) getIntent().getSerializableExtra("myCar");
            makeModel.setText(co.make+" "+co.model);
            price.setText(co.price);
            date.setText(co.date);
            summary.setText(co.description);
            carPic.setImageResource(co.carPic);


        }







    }
}