package com.example.project3;

import java.io.Serializable;
import java.util.ArrayList;

public class CarObject implements Serializable {
    public String make="Aston";
    public String model="v8";
    public String price="100,000";
    public String date="6/15/2020";
    public String description="This is an aston martin v8.";
    public int carPic = R.drawable.flower;
    public CarObject(String x){
        make=make+x;

    }
    public CarObject(){}
    public static ArrayList<CarObject> createCarList(int numContacts) {
        ArrayList<CarObject> contacts = new ArrayList<CarObject>();

        for (int i = 1; i <= numContacts; i++) {
            contacts.add(new CarObject(Integer.toString(i)));
        }

        return contacts;
    }

}
