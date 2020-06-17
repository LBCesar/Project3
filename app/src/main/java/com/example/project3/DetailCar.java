package com.example.project3;

import java.io.Serializable;

public class DetailCar implements Serializable {
    public String color;
    public String created_at;
    public int id;
    public String image_url;
    public int mileage;
    public String model;
    public double price;
    public String veh_description;
    public String vehicle_make;
    public String vehicle_url;
    public String vin_number;

    public DetailCar(){

    }

    // constructor
    public DetailCar(String color, String created_at, int id, String image_url,
                     int mileage, String model, double price, String veh_description,
                     String vehicle_make, String vehicle_url, String vin_number) {
        this.color = color;
        this.created_at = created_at;
        this.id = id;
        this.image_url = image_url;
        this.mileage = mileage;
        this.model = model;
        this.price = price;
        this.veh_description = veh_description;
        this.vehicle_make = vehicle_make;
        this.vehicle_url = vehicle_url;
        this.vin_number = vin_number;
    }

    public String getColor() {
        return color;
    }

    public String getCreated_at() {
        return created_at;
    }

    public int getId() {
        return id;
    }

    public String getImage_url() {
        return image_url;
    }

    public int getMileage() {
        return mileage;
    }

    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
    }

    public String getVeh_description() {
        return veh_description;
    }

    public String getVehicle_make() {
        return vehicle_make;
    }

    public String getVehicle_url() {
        return vehicle_url;
    }

    public String getVin_number() {
        return vin_number;
    }

    @Override
    public String toString() {
//        return "DetailCar{" +
//                " ID = " + id +
//                ", Make = '" + vehicle_make + '\'' +
//                ", Price = &" + price +
//                ", Created = '" + created_at + '\'' +
//                '}';
        return model;
    }
}   // end class
