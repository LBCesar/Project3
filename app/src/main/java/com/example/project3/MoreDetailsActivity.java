package com.example.project3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/*
 *  *Note: There's another class at the bottom
 *
 *  MoreDetailActivity class was created for handling the 4th url link
 *  for the 3rd actvity upon click of button.
 *
 *  *This is now implemented inside carInfo class!
 */
public class MoreDetailsActivity extends AppCompatActivity {

//    public static ArrayList<MoreDetails> moreDetailsCarList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_details);

//        Spinner s = findViewById(R.id.s);

//        Intent intent = getIntent();
//        String myID = intent.getStringExtra("id");

//        moreDetailsCarList = new ArrayList<>();
//        moreDetailsCarList.add(new MoreDetails());

//        int url_ID = 3484; // this id will be used for the url, must come from the 3rd link or array3 id
//        String makeURL = "https://thawing-beach-68207.herokuapp.com/cars/" + url_ID;
//        DataBaseHelper dbh4 = new DataBaseHelper(321, makeURL, 321);
//        dbh4.execute();

        // testing using a spinner to see if the data is retrieved from the url
//        ArrayAdapter<MoreDetails> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, moreDetailsCarList);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        s.setAdapter(adapter);
    }
}


//---------------------------------------------------------------------------------------------------------
/*
    This class contains an vehicle elements which are
    retrieved from the 4th provided url.
    *Full Detail Info regarding one car
    Car objects will be stored in the ArrayList

    url: https://thawing-beach-68207.herokuapp.com/cars/<carid>
    <carid>: this is the id which comes frm the 3rd url link.
 */
class MoreDetails{
    // Initialize all the variables in the link
    public int carcondition_id;
    public int color_id;
    public String content_local_url;
    public String content_url;
    public String created_at;
    public int currency_id;
    public int id;
    public String image_local_url;
    public String image_url;
    public String is_active;
    public int mileage;
    public int onlinecardealer_id;
    public double price;
    public String seller_address;
    public String seller_address_locality;
    public String seller_address_region;
    public String seller_name;
    public String seller_telnumber;
    public String updated_at;
    public String veh_description;
    public int vehicle_make_id;
    public int vehicle_model_id;
    public String vehicle_url;
    public String vin_number;
    public int zipcode_id;

    // Empty constructor
    public MoreDetails(){

    }

    // Constructor with all the required param
    public MoreDetails(int carcondition_id, int color_id, String content_local_url, String content_url,
                       String created_at, int currency_id, int id, String image_local_url, String image_url,
                       String is_active, int mileage, int onlinecardealer_id, double price, String seller_address,
                       String seller_address_locality, String seller_address_region, String seller_name,
                       String seller_telnumber, String updated_at, String veh_description, int vehicle_make_id,
                       int vehicle_model_id, String vehicle_url, String vin_number, int zipcode_id) {
        this.carcondition_id = carcondition_id;
        this.color_id = color_id;
        this.content_local_url = content_local_url;
        this.content_url = content_url;
        this.created_at = created_at;
        this.currency_id = currency_id;
        this.id = id;
        this.image_local_url = image_local_url;
        this.image_url = image_url;
        this.is_active = is_active;
        this.mileage = mileage;
        this.onlinecardealer_id = onlinecardealer_id;
        this.price = price;
        this.seller_address = seller_address;
        this.seller_address_locality = seller_address_locality;
        this.seller_address_region = seller_address_region;
        this.seller_name = seller_name;
        this.seller_telnumber = seller_telnumber;
        this.updated_at = updated_at;
        this.veh_description = veh_description;
        this.vehicle_make_id = vehicle_make_id;
        this.vehicle_model_id = vehicle_model_id;
        this.vehicle_url = vehicle_url;
        this.vin_number = vin_number;
        this.zipcode_id = zipcode_id;
    }


    // All the getter methods to retrieve the value when required
    public int getCarcondition_id() {
        return carcondition_id;
    }

    public int getColor_id() {
        return color_id;
    }

    public String getContent_local_url() {
        return content_local_url;
    }

    public String getContent_url() {
        return content_url;
    }

    public String getCreated_at() {
        return created_at;
    }

    public int getCurrency_id() {
        return currency_id;
    }

    public int getId() {
        return id;
    }

    public String getImage_local_url() {
        return image_local_url;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getIs_active() {
        return is_active;
    }

    public int getMileage() {
        return mileage;
    }

    public int getOnlinecardealer_id() {
        return onlinecardealer_id;
    }

    public double getPrice() {
        return price;
    }

    public String getSeller_address() {
        return seller_address;
    }

    public String getSeller_address_locality() {
        return seller_address_locality;
    }

    public String getSeller_address_region() {
        return seller_address_region;
    }

    public String getSeller_name() {
        return seller_name;
    }

    public String getSeller_telnumber() {
        return seller_telnumber;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public String getVeh_description() {
        return veh_description;
    }

    public int getVehicle_make_id() {
        return vehicle_make_id;
    }

    public int getVehicle_model_id() {
        return vehicle_model_id;
    }

    public String getVehicle_url() {
        return vehicle_url;
    }

    public String getVin_number() {
        return vin_number;
    }

    public int getZipcode_id() {
        return zipcode_id;
    }

    public String stringer(){
        return "Test";
    }

    @Override
    public String toString() {
//        return "More details about the car: \n" + "\n" +
        return  //"carcondition_id: " + carcondition_id + "\n" + "\n" +
                //"color_id: " + color_id + "\n" + "\n" +
                //"content_local_url: '" + content_local_url + '\'' + "\n" + "\n" +
                "created at: '" + created_at + '\'' + "\n" + "\n" +
                "currency id: " + currency_id + "\n" + "\n" +
                //"id: " + id + "\n" + "\n" +
                //"image_local_url: '" + image_local_url + '\'' + "\n" + "\n" +
                ///"image_url: '" + image_url + '\'' + "\n" + "\n" +
                "is_active: '" + is_active + '\'' + "\n" + "\n" +
                "mileage: " + mileage + "\n" + "\n" +
                "onlinecardealer id: " + onlinecardealer_id + "\n" + "\n" +
                //"price: " + price + "\n" + "\n" +
                "seller address: '" + seller_address + '\'' + "\n" + "\n" +
                "seller address locality: '" + seller_address_locality + '\'' + "\n" + "\n" +
                "seller address region: '" + seller_address_region + '\'' + "\n" + "\n" +
                "seller name: '" + seller_name + '\'' + "\n" + "\n" +
                "seller telephone number: '" + seller_telnumber + '\'' + "\n" + "\n" +
                "updated at: '" + updated_at + '\'' + "\n" + "\n" +
                //"veh_description: '" + veh_description + '\'' + "\n" + "\n" +
                //"vehicle_make_id: " + vehicle_make_id + "\n" + "\n" +
                //"vehicle_model_id: " + vehicle_model_id + "\n" + "\n" +
                "vehicle url: '" + vehicle_url + '\'' + "\n" + "\n" +

                        "content url: '" + content_url + '\'' + "\n" + "\n" +

                        //"vin_number: '" + vin_number + '\'' + "\n" + "\n" +
                "zipcode: " + zipcode_id + "\n" + "\n" + ' ';
//        return image_url;
    }

} // end MoreDetail class