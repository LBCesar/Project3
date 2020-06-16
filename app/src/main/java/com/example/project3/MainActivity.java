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


    //Spinner make;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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


        //Creating the second spinner and the third spinner that will be deleted later.
        //The recycler view for our cars is also declared in oncreate.
        model=(Spinner)findViewById(R.id.modelSpinner);
       // availableSpinner=(Spinner)findViewById(R.id.availableSpinner);
        recyclerView =  findViewById(R.id.carLot);
        //recyclerView.setAdapter
               // (new SimpleItemRecyclerViewAdapter(SongUtils.SONG_ITEMS));
        //recyclerView.setAdapter
               // (new SimpleItemRecyclerViewAdapter(SongUtils.SONG_ITEMS));


        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.carLot);
        contacts = Contact.createContactsList(5);
        ContactsAdapter adapter = new ContactsAdapter(contacts);
        rvContacts.setAdapter(adapter);
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
        if (findViewById(R.id.song_detail_container) != null) {
            //ContactsAdapter= true;
            Toast.makeText(this, "===================", Toast.LENGTH_LONG).show();

            adapter.m2p();
        }

    }

    //===========================================================================================================









    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        //Get whatever string is currently showing on the spinner.
        //On our first run the parent will be spinner 1
        //On the second run our parent will be spinner 2

        //The recycler view is updated.
        //There are 2 recycler classes in the program right now. Im going to choose one later
        //I dont know which is best atm.
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
        //If spinner 1== Ferrari then we will set the second spinner to the list.
        if(item.contentEquals("Ferrari")) {
            model.setOnItemSelectedListener(this);//We will now listen to model spinner
            List<String> list = new ArrayList<String>();
            list.add("f430");
            list.add("458");
            list.add("488");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            model.setAdapter(dataAdapter);
        }
        //IF spinner 1==Aston we will set the second spinner to the list.
        // This second one isnt needed, this was just for testing.
        //In our real program we will have if(item.contentEquals("Whatever code"))
        // I think the json uses a code or ID to tell the models apart.
        //List<String> list = new ArrayList<String>(); will be replaced by whatever is in json.
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

        //I think recycler view looks more like the project prompt.
        if(item.contentEquals("v8")) {
            //availableSpinner.setOnItemSelectedListener(this);//listening to available spinner.

            List<String> list = new ArrayList<String>();
            list.add("We are in the 3rd level of call backs.");
            list.add("v8");
            list.add("valk");

            //recyclerView.setAdapter
                   // (new SimpleItemRecyclerViewAdapter(SongUtils.SONG_ITEMS));
            //Toast.makeText(parent.getContext(), "Leaving Now", Toast.LENGTH_LONG).show();
            RecyclerView rvContacts = (RecyclerView) findViewById(R.id.carLot);
            contacts = Contact.createContactsList(010);
            ContactsAdapter adapter = new ContactsAdapter(contacts);
            rvContacts.setAdapter(adapter);
            rvContacts.setLayoutManager(new LinearLayoutManager(this));

            if (findViewById(R.id.song_detail_container) != null) {
             //ContactsAdapter= true;
                Toast.makeText(parent.getContext(), "===================", Toast.LENGTH_LONG).show();

                adapter.m2p();
            }



            //ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this,
            //      android.R.layout.simple_spinner_item, list);
            //dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            //dataAdapter3.notifyDataSetChanged();
            //availableSpinner.setAdapter(dataAdapter3);
        }
        //Now if you pick valk for spinner 3 you will get to here.
        //In here we create a new RV.
        //Youll notice that the RV switches from the contacts to the old songs program.



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