/*
    Project 3
    Cesar G
    Shoraj M
 */
package com.example.project3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Spinner make;
//    public static String makeData = "";

    public static List<Car> car_make = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        make = findViewById(R.id.spinnerMake);

        DataBaseHelper x = new DataBaseHelper();
        x.execute();

        // this car_make does contain the database make elements.
        if (!car_make.isEmpty()){
            Toast.makeText(getApplicationContext(), "is not empty", Toast.LENGTH_LONG).show();
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
