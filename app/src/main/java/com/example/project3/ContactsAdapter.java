package com.example.project3;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/*
 * Create the basic adapter extending from RecyclerView.Adapter
 * Note that we specify the custom ViewHolder which gives us access to our views
 */
public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    // Store a member variable for the vehicle
    private List<DetailCar> mContacts;
    public boolean mTwoPane;
    // public boolean mTwoPane;

    /*
        Provide a direct reference to each of the views within a data item.
        Used to cache the views within the item layout for fast access
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        //public ImageView lilCar;
        //public Button messageButton;

        /*
            We also create a constructor that accepts the entire item row
            and does the view lookups to find each subview
         */
        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.contact_name);
            // lilCar=(ImageView)itemView.findViewById(R.id.littleCar);
            //messageButton = (Button) itemView.findViewById(R.id.message_button);
        }
    }


    // True, if larger screen is detected
    public void m2p(){
        mTwoPane = true;
    }


    // Pass in the contact array into the constructor
    public ContactsAdapter(List<DetailCar> contacts) {
        // super();
        mContacts = contacts;
    }


    /*
        Usually involves inflating a layout from XML and returning the holder.
        Return a new holder instance
     */
    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.item_contact, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }


    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(final ContactsAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        DetailCar contact = mContacts.get(position);

        // Set item views based on your views and data model
        TextView textView = viewHolder.nameTextView;
        String showText_RViewer = contact.model + "(" + contact.color + ")" + "\t\t" + contact.getPrice();
        textView.setText(showText_RViewer);
//        textView.setText(contact.model + "(" + contact.color + ")" + " $" + contact.price);
        //viewHolder.lilCar.setImageResource(contact.carPic);

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




                    carInfoFragment fragment=carInfoFragment.newInstance(co,"hello");

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

                    //int url_ID = 3484; // this id will be used for the url, must come from the 3rd link or array3 i
//                    int url_ID=co.id;
//                    String makeURL = "https://thawing-beach-68207.herokuapp.com/cars/" + url_ID;
//                    DataBaseHelper dbh4 = new DataBaseHelper(321, makeURL, 321);
//                    dbh4.execute();
//                    try {
//                        dbh4.execute().get();
//                    } catch (ExecutionException e) {
//                        e.printStackTrace();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    //co.adder(MainActivity.moreDetailsCarList.get(0));
                    //co.adder(MainActivity.mdMain);
                    //co.adder2();


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
