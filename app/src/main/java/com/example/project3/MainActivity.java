package com.example.project3;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    String[] MakeArray;

    Spinner make;
    public static String makeData = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        make = findViewById(R.id.spinner_make);

        RecyclerView recyclerView =  findViewById(R.id.recylerview);
//        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(SongUtils.SONG_ITEMS));


        DataBaseHelper x = new DataBaseHelper();
        x.execute();    // makeData in the Databasehelper has the right heroku values
        // needa parse it
        // need to use arrays
        // also array for the spinner (drop down menu for make)

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