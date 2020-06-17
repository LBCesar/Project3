package com.example.project3;

public class Car {

    public int id;
    public String make;

    public Car(int myId, String myMake){
        this.id = myId;
        this.make = myMake;
    }

    public Car() {

    }

    public int getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public String toString(){
//        return id + " " + make;
        return make;
    }

}

class CarModel{
    public int id;
    public String model;
    public String vehicle_make_id;

    public CarModel(int id, String m, String vm){
        this.id = id;
        this.model = m;
        this.vehicle_make_id = vm;
    }

    public CarModel() {

    }

    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public String getVehicle_make_id() {
        return vehicle_make_id;
    }

    @Override
    public String toString() {
//        return "CarModel{" +
//                "id=" + id +
//                ", model='" + model + '\'' +
//                ", vehicle_make_id='" + vehicle_make_id + '\'' +
//                '}';
        return model;
    }
}
