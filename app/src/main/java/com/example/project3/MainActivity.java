/*
    Project 3: CarApp
    Cesar G
    Shoraj M
 */
package com.example.project3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner make;
    Spinner model;

    public static ArrayList<Car> newCarList = new ArrayList<>();
    public static ArrayList<CarModel> carModelList = new ArrayList<>();
    public static ArrayList<DetailCar> detailCarList = new ArrayList<>();
    public static ArrayList<DetailCar> rvt = new ArrayList<>();
    public static ArrayList<MoreDetails> moreDetailsCarList=new ArrayList<>();
    public static MoreDetails mdMain;
//    public static ArrayList<MoreDetails> moreDetailsCarList;
    ArrayList<CarObject> cars = new ArrayList<>();

    public int run = 0;

    public boolean again = false;
    public boolean mTwoPane = false;
    public boolean finished=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // We need to populate the initial spinner first.
        make = findViewById(R.id.makeSpinner);
        model = findViewById(R.id.modelSpinner);

        String makeURL = "https://thawing-beach-68207.herokuapp.com/carmakes";
        DataBaseHelper dbh = new DataBaseHelper(0, makeURL, 0);
        dbh.execute();    // makeData in the DataBaseHelper has the right Heroku values


        /*
            Using ArrayAdapter to populate the 1st spinner with the data from newCarList ArrayList.
            ArrayList newCarList contains <Car> objects which is retrieved from the make json url
         */
        newCarList.add(new Car(999, " "));  // Note: *Handles specical case. Adapter wont function without this
        ArrayAdapter<Car> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, newCarList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        make.setAdapter(adapter);
        make.setOnItemSelectedListener(this);


        rvt = detailCarList;
        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.carLot);

        ContactsAdapter adapter2 = new ContactsAdapter(rvt);
        rvContacts.setAdapter(adapter2);
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
        rvContacts.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter2.notifyDataSetChanged();

        // adapter
        if (findViewById(R.id.song_detail_container) != null) {
            adapter2.m2p();
        }


    }   // end onCreate method


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();

        if(parent.getId()==R.id.makeSpinner){
            model.setOnItemSelectedListener(this);

            String strMake = parent.getItemAtPosition(position).toString();
            //Toast.makeText(parent.getContext(), "Make: " + strMake, Toast.LENGTH_LONG).show();

            int makeId = newCarList.get(position).getId();
            String makeURL = "https://thawing-beach-68207.herokuapp.com/carmodelmakes/" + makeId;
            DataBaseHelper dbh2 = new DataBaseHelper(makeId, makeURL, 0);
            dbh2.execute();
            carModelList.add(new CarModel( 9933, " ", " "));

            ArrayAdapter<CarModel> carModelArrayAdapter = new ArrayAdapter<CarModel>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, carModelList);
            carModelArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            carModelArrayAdapter.notifyDataSetChanged();

            model.setAdapter(carModelArrayAdapter);
        }

        if(parent.getId()==R.id.modelSpinner){
            String strMake = parent.getItemAtPosition(position).toString();
            //Toast.makeText(parent.getContext(), "Model: " + strMake, Toast.LENGTH_LONG).show();

            String availableMake_id = carModelList.get(position).getVehicle_make_id();
            int availableModel_id = carModelList.get(position).getId();
            String availableURL = "https://thawing-beach-68207.herokuapp.com/cars/" + availableMake_id + "/" + availableModel_id + "/92603";
            DataBaseHelper dbh3 = new DataBaseHelper(0, availableURL, 1);
            //dbh3.execute();

            try {
                dbh3.execute().get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


                RecyclerView rvContacts = (RecyclerView) findViewById(R.id.carLot);
            rvt=detailCarList;
            //rvt.addAll(detailCarList);
                ContactsAdapter adapter2 = new ContactsAdapter(rvt);
            rvContacts.setAdapter(adapter2);

            rvContacts.setLayoutManager(new LinearLayoutManager(this));
                adapter2.notifyDataSetChanged();

                if (findViewById(R.id.song_detail_container) != null) {
                    adapter2.m2p();
                }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


} // end class MainActivity
