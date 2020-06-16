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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class ContactsAdapter extends
        RecyclerView.Adapter<ContactsAdapter.ViewHolder> {
       // public boolean mTwoPane;

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;

        //public Button messageButton;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.contact_name);
            //messageButton = (Button) itemView.findViewById(R.id.message_button);
        }
    }
        // Store a member variable for the contacts
        private List<CarObject> mContacts;
    public boolean mTwoPane;
    public void m2p(){
        mTwoPane=true;
    }
    // public ContactsAdapter(){}
        // Pass in the contact array into the constructor
        public ContactsAdapter(List<CarObject> contacts) {
            //super();
            mContacts = contacts;
        }
        // Usually involves inflating a layout from XML and returning the holder
        @Override
        public ContactsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            // Inflate the custom layout
            View contactView = inflater.inflate(R.layout.item_contact, parent, false);

            // Return a new holder instance
            ViewHolder viewHolder = new ViewHolder(contactView);
            return viewHolder;
        }

        // Involves populating data into the item through holder
        @Override
        public void onBindViewHolder(final ContactsAdapter.ViewHolder viewHolder, int position) {
            // Get the data model based on position
            CarObject contact = mContacts.get(position);

            // Set item views based on your views and data model
            TextView textView = viewHolder.nameTextView;
            textView.setText(contact.make);
            //Button button = viewHolder.messageButton;
            //button.setText(contact.isOnline() ? "Message" : "Offline");
            //button.setEnabled(contact.isOnline());

           // CarObject co= new CarObject();
            //Context context = parent.getContext();
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CarObject co= new CarObject();
                    final Intent intent;

                    if (mTwoPane) {
                        Context context = v.getContext();

                        int selectedSong = viewHolder.getAdapterPosition();
                        co=mContacts.get(selectedSong);
                        carInfoFragment fragment=carInfoFragment.newInstance(co,"hello");

                        ((FragmentActivity)context).getSupportFragmentManager().beginTransaction()
                                .replace(R.id.song_detail_container, fragment)
                                .commit();

                     } else {

                        Context context = v.getContext();
                        int selectedSong = viewHolder.getAdapterPosition();
                        co=mContacts.get(selectedSong);
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
