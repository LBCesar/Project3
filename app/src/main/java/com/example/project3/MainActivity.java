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
    private boolean mTwoPane = false;
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
        availableSpinner=(Spinner)findViewById(R.id.availableSpinner);
        recyclerView =  findViewById(R.id.carLot);
        //recyclerView.setAdapter
               // (new SimpleItemRecyclerViewAdapter(SongUtils.SONG_ITEMS));
        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.carLot);
        contacts = Contact.createContactsList(20);
        ContactsAdapter adapter = new ContactsAdapter(contacts);
        rvContacts.setAdapter(adapter);
        rvContacts.setLayoutManager(new LinearLayoutManager(this));

    }

    //===========================================================================================================









    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        //Get whatever string is currently showing on the spinner.
        //On our first run the parent will be spinner 1
        //On the second run our parent will be spinner 2
        //On the third run our parent will be spinner 3.
        //Once we are in spinner 3 if we choose "valk"
        //The recycler view is updated.
        //There are 2 recycler classes in the program right now. Im going to choose one later
        //I dont know which is best atm.
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
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
        //This spinner is just for testing, will be deleted later.
        //Unless we want a spinner for the available cars.
        //I think recycler view looks more like the project prompt.
        if(item.contentEquals("v8")) {
            availableSpinner.setOnItemSelectedListener(this);//listening to available spinner.

            List<String> list = new ArrayList<String>();
            list.add("We are in the 3rd level of call backs.");
            list.add("v8");
            list.add("valk");

            ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter3.notifyDataSetChanged();
            availableSpinner.setAdapter(dataAdapter3);
        }
        //Now if you pick valk for spinner 3 you will get to here.
        //In here we create a new RV.
        //Youll notice that the RV switches from the contacts to the old songs program.
        if(item.contentEquals("valk")) {//IF valk was chossen in spinner 3
            List<String> list = new ArrayList<String>();
            list.add("We are in the 3rd level of call backs.");
            list.add("v8");
            list.add("valk");

            recyclerView.setAdapter
                    (new SimpleItemRecyclerViewAdapter(SongUtils.SONG_ITEMS));
            Toast.makeText(parent.getContext(), "Leaving Now", Toast.LENGTH_LONG).show();

            //if (findViewById(R.id.song_detail_container) != null) {
               // mTwoPane = true;
            //}



            //ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this,
              //      android.R.layout.simple_spinner_item, list);
            //dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            //dataAdapter3.notifyDataSetChanged();
            //availableSpinner.setAdapter(dataAdapter3);
        }


    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
    class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter
            <SimpleItemRecyclerViewAdapter.ViewHolder> {
        //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

        private final List<SongUtils.Song> mValues;

        SimpleItemRecyclerViewAdapter(List<SongUtils.Song> items) {
            mValues = items;
        }

        /**
         * This method inflates the layout for the song list.
         * @param parent ViewGroup into which the new view will be added.
         * @param viewType The view type of the new View.
         * @return
         */
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.song_list_content, parent, false);
            return new ViewHolder(view);
        }

        /**
         * This method implements a listener with setOnClickListener().
         * When the user taps a song title, the code checks if mTwoPane
         * is true, and if so uses a fragment to show the song detail.
         * If mTwoPane is not true, it starts SongDetailActivity
         * using an intent with extra data about which song title was selected.
         *
         * @param holder   ViewHolder
         * @param position Position of the song in the array.
         */
        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            holder.mIdView.setText(String.valueOf(position + 1));
            holder.mContentView.setText(mValues.get(position).song_title);
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*
                    Context context = v.getContext();
                    Intent intent = new Intent(context, SongDetailActivity.class);
                    intent.putExtra(SongUtils.SONG_ID_KEY,
                            holder.getAdapterPosition());
                    context.startActivity(intent);
*/
                    //if (mTwoPane) {
                     //   int selectedSong = holder.getAdapterPosition();
                      //  SongDetailFragment fragment =SongDetailFragment.newInstance(selectedSong);
                      //  getSupportFragmentManager().beginTransaction()
                           //     .replace(R.id.song_detail_container, fragment)
                           //     .addToBackStack(null)
                            //    .commit();
                   // } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, SongDetailActivity.class);
                        intent.putExtra(SongUtils.SONG_ID_KEY,
                                holder.getAdapterPosition());
                        context.startActivity(intent);
                   // }





                }
            });
        }

        /**
         * Get the count of song list items.
         * @return
         */
        @Override
        public int getItemCount() {
            return mValues.size();
        }

        /**
         * ViewHolder describes an item view and metadata about its place
         * within the RecyclerView.
         */
        class ViewHolder extends RecyclerView.ViewHolder {
            final View mView;
            final TextView mIdView;
            final TextView mContentView;
            SongUtils.Song mItem;

            ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView =  view.findViewById(R.id.id);
                mContentView =  view.findViewById(R.id.content);
            }
        }
    }

} // end class MainActivity



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