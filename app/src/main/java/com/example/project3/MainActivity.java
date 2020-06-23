/*
    Project 3: CarApp
    Cesar G
    Shoraj M
 */
package com.example.project3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner make;
    Spinner model;

    public static ArrayList<Car> newCarList = new ArrayList<>();
    public static ArrayList<CarModel> carModelList = new ArrayList<>();
    public static ArrayList<DetailCar> detailCarList = new ArrayList<>();
    public static ArrayList<DetailCar> rvt = new ArrayList<>();
    public static ArrayList<MoreDetails> moreDetailsCarList = new ArrayList<>();
    public static MoreDetails mdMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        make = findViewById(R.id.makeSpinner);
        model = findViewById(R.id.modelSpinner);
        //Retrieving our list of makes
        String makeURL = "https://thawing-beach-68207.herokuapp.com/carmakes";
        DataBaseHelper dbh = new DataBaseHelper(0, makeURL, 0);
        dbh.execute();


        /*
            Using ArrayAdapter to populate the 1st spinner with the data from newCarList ArrayList.
            ArrayList newCarList contains <Car> objects which is retrieved from the make json url
        */
        newCarList.add(new Car(999, " "));  // Note: *Handles specical case. Adapter wont function without this
        ArrayAdapter<Car> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, newCarList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        make.setAdapter(adapter);
        make.setOnItemSelectedListener(this);

        //Setting up our recycler view that will end up displaying the available models.
        rvt = detailCarList;
        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.carLot);
        CarsAdapter adapter2 = new CarsAdapter(rvt);
        rvContacts.setAdapter(adapter2);
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
        rvContacts.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter2.notifyDataSetChanged();

        //If song_detal_container is not null, then we have enough screen space to
        //launch the app in tablet mode.
        if (findViewById(R.id.song_detail_container) != null) {
            adapter2.m2p();
        }

    }   // end onCreate method


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();

        if (parent.getId() == R.id.makeSpinner) {
            model.setOnItemSelectedListener(this);

            //String strMake = parent.getItemAtPosition(position).toString();
            //Toast.makeText(parent.getContext(), "Make: " + strMake, Toast.LENGTH_LONG).show();

            //Retrieving the resulting car models based on the users make choice.
            int makeId = newCarList.get(position).getId();
            String makeURL = "https://thawing-beach-68207.herokuapp.com/carmodelmakes/" + makeId;
            DataBaseHelper dbh2 = new DataBaseHelper(makeId, makeURL, 0);
            dbh2.execute();
            carModelList.add(new CarModel(9933, " ", " "));

            //Setting up the adapter with the resulting car models.
            ArrayAdapter<CarModel> carModelArrayAdapter = new ArrayAdapter<CarModel>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, carModelList);
            carModelArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            carModelArrayAdapter.notifyDataSetChanged();

            model.setAdapter(carModelArrayAdapter);
        }

        if (parent.getId() == R.id.modelSpinner) {
            //String strMake = parent.getItemAtPosition(position).toString();
            //Toast.makeText(parent.getContext(), "Model: " + strMake, Toast.LENGTH_LONG).show();

            //Retrieving a list of cars based on the chosen make and model.
            String availableMake_id = carModelList.get(position).getVehicle_make_id();
            int availableModel_id = carModelList.get(position).getId();
            String availableURL = "https://thawing-beach-68207.herokuapp.com/cars/" + availableMake_id + "/" + availableModel_id + "/92603";
            DataBaseHelper dbh3 = new DataBaseHelper(0, availableURL, 1);
            try {
                dbh3.execute().get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }

            //Using the list of cars based on the chosen make and model to populate the recycler view.
            RecyclerView rvContacts = (RecyclerView) findViewById(R.id.carLot);
            rvt = detailCarList;
            CarsAdapter adapter2 = new CarsAdapter(rvt);
            rvContacts.setAdapter(adapter2);
            rvContacts.setLayoutManager(new LinearLayoutManager(this));
            adapter2.notifyDataSetChanged();
            //Tablet mode, might not be needed later.
            if (findViewById(R.id.song_detail_container) != null) {
                adapter2.m2p();
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
} // end class MainActivity
