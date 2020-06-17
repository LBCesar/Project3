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

        //CarObject co=new CarObject();
        DetailCar co=new DetailCar();


        if ((DetailCar) getIntent().getSerializableExtra("myCar") != null)
        {
            co = (DetailCar) getIntent().getSerializableExtra("myCar");
            makeModel.setText(" "+co.model);
            price.setText(Double.toString(co.price));
            date.setText(co.vin_number);
            summary.setText(co.veh_description);
            //carPic.setImageResource(co.carPic);


        }







    }
}