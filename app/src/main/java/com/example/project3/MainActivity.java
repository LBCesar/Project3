/*
    Project 3
    Cesar G
    Shoraj M
 */
package com.example.project3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    Spinner make;

    public static ArrayList<Car> newCarList;
    public static ArrayList<CarModel> carModelList;
    public static ArrayList<DetailCar> detailCarList;
    //    public static String makeData = "";
    public boolean again = false;


    public static List<Car> car_make = new ArrayList<>();
    Spinner model;

    public boolean mTwoPane = false;
    ArrayList<CarObject> cars = new ArrayList<CarObject>();
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //make = findViewById(R.id.spinnerMake);

        // DataBaseHelper x = new DataBaseHelper();
        // x.execute();

        // this car_make does contain the database make elements.
        if (!car_make.isEmpty()) {
            Toast.makeText(getApplicationContext(), "is not empty", Toast.LENGTH_LONG).show();
        }


        //=======================================================================================
        //We need to populate the initial spinner first.
        //So in onCreate we will fill the first spinner.
        // Spinner element
        make = (Spinner) findViewById(R.id.makeSpinner);
        model = (Spinner) findViewById(R.id.modelSpinner);

        // Spinner click listener
        //make.setOnItemSelectedListener(this);
        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Ferrari");
        categories.add("Aston");
        categories.add("Mazda");
        categories.add("Rolls");
        categories.add("Lambo");
        categories.add("Enzo");
        //*8888888888888888888888888888888888888888888888888888

        String makeURL = "https://thawing-beach-68207.herokuapp.com/carmakes";
        DataBaseHelper dbh = new DataBaseHelper(0, makeURL, 0);
        dbh.execute();    // makeData in the DataBaseHelper has the right Heroku values
        newCarList = new ArrayList<>();
        newCarList.add(new Car(999, " "));

        // newCarList.add(new Car(999, " "));

        ArrayAdapter<Car> dataAdapter = new ArrayAdapter<Car>(this, android.R.layout.simple_spinner_dropdown_item, newCarList);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinner_make.setAdapter(adapter);
        //spinner_make.setSelection(0);


        //******************************************************88
        // Creating adapter for spinner
        //ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        make.setAdapter(dataAdapter);

        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.carLot);
        //Replace this with our json car objects.
        //This should be the last step, once its in this RV it will
        //Do the rest of the work.
       // cars = CarObject.createCarList(5);


        /*
        ContactsAdapter adapter = new ContactsAdapter(detailCarList);
        rvContacts.setAdapter(adapter);
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
        if (findViewById(R.id.song_detail_container) != null) {
            adapter.m2p();
        }
*/




        make.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String strMake = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Make: " + strMake, Toast.LENGTH_LONG).show();

                int makeId = newCarList.get(position).getId();
                String makeURL = "https://thawing-beach-68207.herokuapp.com/carmodelmakes/" + makeId;
                DataBaseHelper dbh2 = new DataBaseHelper(makeId, makeURL, 0);
                dbh2.execute();

                getModel();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }   // end onCreate method


    void getModel() {
        carModelList = new ArrayList<>();
        carModelList.add(new CarModel(9933, " ", " "));

        ArrayAdapter<CarModel> carModelArrayAdapter = new ArrayAdapter<CarModel>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, carModelList);
        carModelArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        model.setAdapter(carModelArrayAdapter);

        model.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String strMake = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Model: " + strMake, Toast.LENGTH_LONG).show();

                String availableMake_id = carModelList.get(position).getVehicle_make_id();
                int availableModel_id = carModelList.get(position).getId();
                String availableURL = "https://thawing-beach-68207.herokuapp.com/cars/" + availableMake_id + "/" + availableModel_id + "/92603";

                Toast.makeText(parent.getContext(), availableURL, Toast.LENGTH_LONG).show();


               // DataBaseHelper dbh3 = new DataBaseHelper(0, availableURL, 1);
                //dbh3.execute();
               //getRV();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    void getRV(){
        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.carLot);

        //cars = CarObject.createCarList(10);
        detailCarList.add(new DetailCar("yellow", "xa", 111, "asd", 6445, "360", 1220.1, "asd", "asd", "asd", "asd"));

        ContactsAdapter adapter = new ContactsAdapter(detailCarList);
        rvContacts.setAdapter(adapter);
        rvContacts.setLayoutManager(new LinearLayoutManager(this));

        if (findViewById(R.id.song_detail_container) != null) {
            adapter.m2p();
        }
    }
}