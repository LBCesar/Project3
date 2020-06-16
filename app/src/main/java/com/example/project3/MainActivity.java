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

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner make;
//    public static String makeData = "";

    public static List<Car> car_make = new ArrayList<>();
    Spinner model;
    Spinner availableSpinner;
    RecyclerView recyclerView;
    public boolean mTwoPane = false;
    ArrayList<Contact> contacts;
    ArrayList<CarObject>cars =new ArrayList<CarObject>();;
    //CarObject co=new CarObject();



    //Spinner make;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //cars.add(co);
        //cars.add(co);
        //cars.add(new CarObject());
        ///cars.add(co);
        //cars.add(co);
       // cars.add(co);
        //cars.add(co);

        //make = findViewById(R.id.spinnerMake);

        DataBaseHelper x = new DataBaseHelper();
       // x.execute();

        // this car_make does contain the database make elements.
        if (!car_make.isEmpty()){
            Toast.makeText(getApplicationContext(), "is not empty", Toast.LENGTH_LONG).show();
        }
        //=======================================================================================
        //We need to populate the initial spinner first.
        //So in onCreate we will fill the first spinner.
        // Spinner element
        make = (Spinner) findViewById(R.id.makeSpinner);
        // Spinner click listener
        make.setOnItemSelectedListener(this);
        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Ferrari");
        categories.add("Aston");
        categories.add("Mazda");
        categories.add("Rolls");
        categories.add("Lambo");
        categories.add("Enzo");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        make.setAdapter(dataAdapter);


        model=(Spinner)findViewById(R.id.modelSpinner);




        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.carLot);
        //contacts = Contact.createContactsList(5);
        //Replace this with our json car objects.
        cars=CarObject.createCarList(5);
        ContactsAdapter adapter = new ContactsAdapter(cars);
        rvContacts.setAdapter(adapter);
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
        if (findViewById(R.id.song_detail_container) != null) {
            adapter.m2p();
        }

    }

    //===========================================================================================================



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String item = parent.getItemAtPosition(position).toString();

        //Hard coded example.
        //If our first spinner is ferrari.
        if(item.contentEquals("Ferrari")) {
            model.setOnItemSelectedListener(this);//We will now listen to model spinner
            //Replace this with arraylist of models from json.
            List<String> list = new ArrayList<String>();
            list.add("f430");
            list.add("458");
            list.add("488");
            //Add new data to the adapter.
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            //This new adapter is now listening to you.
            //onItemSlected now responds to spinner 2
            model.setAdapter(dataAdapter);
        }
        //Same as previous, delete later.
        if(item.contentEquals("Aston")) {
            model.setOnItemSelectedListener(this);//listening to model spinner
            List<String> list = new ArrayList<String>();
            list.add("v12");
            list.add("v8");
            list.add("valk");
            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter2.notifyDataSetChanged();
            model.setAdapter(dataAdapter2);
        }
        //This is listening to the second spinner.
        //If we choose v8 in the second spinner you should see an updates RV.
        if(item.contentEquals("v8")) {

            RecyclerView rvContacts = (RecyclerView) findViewById(R.id.carLot);
            //contacts = Contact.createContactsList(010);
            cars=CarObject.createCarList(10);

            ContactsAdapter adapter = new ContactsAdapter(cars);
            rvContacts.setAdapter(adapter);
            rvContacts.setLayoutManager(new LinearLayoutManager(this));

            if (findViewById(R.id.song_detail_container) != null) {
                adapter.m2p();
            }
        }
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }


} // end class MainActivity

/*






 */

//class SimpleItemRecyclerViewAdapter extends RecyclerView.Adapter <SimpleItemRecyclerViewAdapter.ViewHolder> {
//
//    @NonNull
//    @Override
//    public SimpleItemRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        return null;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull SimpleItemRecyclerViewAdapter.ViewHolder viewHolder, int i) {
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
//
//
//
//}
///Push Test.


/*
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <TextView
        android:id="@+id/txtMake"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textSize="20dp"
        android:text="@string/make"
        android:textStyle="bold" />

    <Spinner
        android:id="@+id/spinnerMake"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />


</LinearLayout>

 */