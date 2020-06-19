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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {

    Spinner make;
    Spinner model;

    public static ArrayList<Car> newCarList= new ArrayList<>();
    public static ArrayList<CarModel> carModelList= new ArrayList<>();
    public static ArrayList<DetailCar> detailCarList= new ArrayList<>();
    public static ArrayList<DetailCar> rvt= new ArrayList<>();
    public int run=0;
    // RecyclerView rvContacts;
    //public RecyclerView rvContacts;
    //public ContactsAdapter adapter2;

    public boolean again = false;
    public boolean mTwoPane = false;
    public boolean finished=false;
    ArrayList<CarObject> cars = new ArrayList<CarObject>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        newCarList = new ArrayList<>();
//        carModelList = new ArrayList<>();
//        detailCarList = new ArrayList<>();
//        rvt=new ArrayList<>();

//=======================================================================================
        // We need to populate the initial spinner first.
        // So in onCreate we will fill the first spinner.
        // Spinner element
        make = (Spinner) findViewById(R.id.makeSpinner);
        model = (Spinner) findViewById(R.id.modelSpinner);

        // rvContacts = findViewById(R.id.carLot);
        //rvContacts.setAdapter(null);
        //rvContacts.removeAllViewsInLayout();

//        Car car = new Car();
//        CarModel carModel = new CarModel();

        String makeURL = "https://thawing-beach-68207.herokuapp.com/carmakes";
        DataBaseHelper dbh = new DataBaseHelper(0, makeURL, 0);
        dbh.execute();    // makeData in the DataBaseHelper has the right Heroku values

       // newCarList = new ArrayList<>();
        newCarList.add(new Car(999, " "));

        ArrayAdapter<Car> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, newCarList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        make.setAdapter(adapter);
        //adapter.notifyDataSetChanged();
        make.setOnItemSelectedListener(this);


        detailCarList.add(new DetailCar("test", "test", 9999, "test",
                0000, " ", 000, "test",
                " ", "test", "test"));
        rvt = detailCarList;
        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.carLot);
        //rvt.addAll(detailCarList);
        //detailCarList;
        ContactsAdapter adapter2 = new ContactsAdapter(rvt);
        rvContacts.setAdapter(adapter2);
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
        adapter2.notifyDataSetChanged();

        //adapter2.notifyDataSetChanged();

        // adapter
        if (findViewById(R.id.song_detail_container) != null) {
            adapter2.m2p();
        }


        /*
        make.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String strMake = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Make: " + strMake, Toast.LENGTH_LONG).show();


                int makeId = newCarList.get(position).getId();
                String makeURL = "https://thawing-beach-68207.herokuapp.com/carmodelmakes/" + makeId;
                DataBaseHelper dbh2 = new DataBaseHelper(makeId, makeURL, 0);
                dbh2.execute();

               // getModel();
                // carModelList = new ArrayList<>();
                carModelList.add(new CarModel( 9933, " ", " "));

                ArrayAdapter<CarModel> carModelArrayAdapter = new ArrayAdapter<CarModel>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, carModelList);
                carModelArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                carModelArrayAdapter.notifyDataSetChanged();



                model.setAdapter(carModelArrayAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        model.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String strMake = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Model: " + strMake, Toast.LENGTH_LONG).show();

                String availableMake_id = carModelList.get(position).getVehicle_make_id();
                int availableModel_id = carModelList.get(position).getId();
                String availableURL = "https://thawing-beach-68207.herokuapp.com/cars/" + availableMake_id + "/" + availableModel_id + "/92603";

                Toast.makeText(parent.getContext(), availableURL, Toast.LENGTH_LONG).show();


                DataBaseHelper dbh3 = new DataBaseHelper(0, availableURL, 1);
                dbh3.execute();

                detailCarList.add(new DetailCar("test", "test", 9999, "test",
                        1112, "test", 1.00, "test",
                        "test", "test", "test"));
//                RecyclerView rvContacts = (RecyclerView) findViewById(R.id.carLot);
//                ContactsAdapter adapter = new ContactsAdapter(detailCarList);
//                rvContacts.setLayoutManager(new LinearLayoutManager(view.getContext()));
//                rvContacts.setAdapter(adapter);
//                adapter.notifyDataSetChanged();
//
//                if (findViewById(R.id.song_detail_container) != null) {
//                    adapter.m2p();
//                }
                rvt.clear();
                rvt.addAll(detailCarList);
                adapter2.notifyDataSetChanged();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

         */

    }   // end onCreate method


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        Toast.makeText(parent.getContext(), "Parent:"+parent.getId(), Toast.LENGTH_LONG).show();
//        Toast.makeText(parent.getContext(), "Spinner:"+R.id.makeSpinner, Toast.LENGTH_LONG).show();
        String item = parent.getItemAtPosition(position).toString();
        /*
        RecyclerView rvContacts2 = (RecyclerView) findViewById(R.id.carLot);
        rvt=detailCarList;
        //rvt.addAll(detailCarList);
        ContactsAdapter adapter22 = new ContactsAdapter(rvt);
        rvContacts2.setAdapter(adapter22);

        rvContacts2.setLayoutManager(new LinearLayoutManager(this));
        adapter22.notifyDataSetChanged();



        if (findViewById(R.id.song_detail_container) != null) {
            adapter22.m2p();
        }


         */
        if(parent.getId()==R.id.makeSpinner){
            model.setOnItemSelectedListener(this);

            String strMake = parent.getItemAtPosition(position).toString();
            //Toast.makeText(parent.getContext(), "Make: " + strMake, Toast.LENGTH_LONG).show();

            int makeId = newCarList.get(position).getId();
            String makeURL = "https://thawing-beach-68207.herokuapp.com/carmodelmakes/" + makeId;
            DataBaseHelper dbh2 = new DataBaseHelper(makeId, makeURL, 0);
            dbh2.execute();

            // getModel();
            // carModelList = new ArrayList<>();
            carModelList.add(new CarModel( 9933, " ", " "));

            ArrayAdapter<CarModel> carModelArrayAdapter = new ArrayAdapter<CarModel>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, carModelList);
            carModelArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            carModelArrayAdapter.notifyDataSetChanged();

            model.setAdapter(carModelArrayAdapter);
        }

        if(parent.getId()==R.id.modelSpinner){

//            Toast.makeText(parent.getContext(), "Parent:"+parent.getId(), Toast.LENGTH_LONG).show();
//            Toast.makeText(parent.getContext(), "Spinner:"+R.id.modelSpinner, Toast.LENGTH_LONG).show();
            String strMake = parent.getItemAtPosition(position).toString();
            //Toast.makeText(parent.getContext(), "Model: " + strMake, Toast.LENGTH_LONG).show();

            String availableMake_id = carModelList.get(position).getVehicle_make_id();
            int availableModel_id = carModelList.get(position).getId();
            String availableURL = "https://thawing-beach-68207.herokuapp.com/cars/" + availableMake_id + "/" + availableModel_id + "/92603";

//            Toast.makeText(parent.getContext(), availableURL, Toast.LENGTH_LONG).show();


            DataBaseHelper dbh3 = new DataBaseHelper(0, availableURL, 1);

            try {
                dbh3.execute().get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //String str_result= new RunInBackGround().execute().get();
//            try {
//                Object result = dbh3.execute().get();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            try {
//                dbh3.get(1000, TimeUnit.MILLISECONDS);
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (TimeoutException e) {
//                e.printStackTrace();
//            }
            //detailCarList.add(new DetailCar("test", "test", 9999, "test", 1112, "test", 1.00, "test", "test", "test", "test"));
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
//            rvt.clear();
//            rvt.addAll(detailCarList);
//            adapter2.notifyDataSetChanged();


        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


} // end class MainActivity
