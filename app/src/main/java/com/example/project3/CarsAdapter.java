package com.example.project3;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/*
 * Create the basic adapter extending from RecyclerView.Adapter
 * Note that we specify the custom ViewHolder which gives us access to our views
 */
public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.ViewHolder> {

    // Store a member variable for the vehicle
    private List<DetailCar> mContacts;
    public boolean mTwoPane;


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        //public ImageView lilCar;
        //public Button messageButton;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.contact_name);
        }
    }


    // True, if larger screen is detected
    public void m2p(){
        mTwoPane = true;
    }


    // Pass in the contact array into the constructor
    public CarsAdapter(List<DetailCar> contacts) {
        // super();
        mContacts = contacts;
    }

    @Override
    public CarsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.item_contact, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CarsAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        DetailCar contact = mContacts.get(position);

        // Set item views based on your views and data model
        TextView textView = viewHolder.nameTextView;
        String showText_RViewer = contact.model + "(" + contact.color + ")" + "\t\t" + contact.getPrice();
        textView.setText(showText_RViewer);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailCar co = new DetailCar();



                final Intent intent;

                if (mTwoPane) {
                    /*
                        This will update our fragment and place it in the
                        fragment_car_info.xml
                        container in activity_main.xml(w900dp)
                        carInfoFragment.java
                     */
                    Context context = v.getContext();
                    int selectedSong = viewHolder.getAdapterPosition();
                    co=mContacts.get(selectedSong);
                    carInfoFragment fragment=carInfoFragment.newInstance(co);
                    ((FragmentActivity)context).getSupportFragmentManager().beginTransaction()
                            .replace(R.id.song_detail_container, fragment)
                            .commit();

                 } else {

                    /*
                        carInfo.java
                        activity_car_info.xml
                        This will open a new activity with car details.
                     */
                    Context context = v.getContext();
                    int selectedSong = viewHolder.getAdapterPosition();
                    co = mContacts.get(selectedSong);
                    intent = new Intent(context, carInfo.class);
                    intent.putExtra("myCar", co);
                    context.startActivity(intent);
                }

            }
        });
    }


    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mContacts.size();
    }

}
