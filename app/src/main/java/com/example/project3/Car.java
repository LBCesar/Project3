package com.example.project3;

/*
    This class contains an vehicle "id" and "make" which is
    retrieved from the 1st provided url.
    Car objects will be stored in the ArrayList

    url: https://thawing-beach-68207.herokuapp.com/carmakes
 */
public class Car {

    // initialize the variables
    public int id;
    public String make;

    // An empty constructor
    public Car() { }

    /*
     * Constructor which takes in id and make
     */
    public Car(int myId, String myMake){
        this.id = myId;
        this.make = myMake;
    }

    // A get method to return the id
    public int getId() {
        return id;
    }

    // A get method to return the make
    public String getMake() {
        return make;
    }

    public String toString(){
        return make;
    }

}   // end Car class

/*--------------------------------------------------------------------------------------------*/

/*
    This class contain elements which is retrieved from the 2st provided url.
    CarModel objects will be stored in the ArrayList

    url: https://thawing-beach-68207.herokuapp.com/carmodelmakes/<id>
    <id: is retrieved from the 1st link upon selection of the vehicle make>
 */
class CarModel{

    // initialize the class variables
    public int id;
    public String model;
    public String vehicle_make_id;

    //  An empty constructor
    public CarModel() { }

    // Constructor with parameters to initialize on creation of object
    public CarModel(int id, String m, String vm){
        this.id = id;
        this.model = m;
        this.vehicle_make_id = vm;
    }

    // A get method to return the id
    public int getId() {
        return id;
    }

    // A get method to return the model
    public String getModel() {
        return model;
    }

    // A get method to return the make_id of vehicle
    public String getVehicle_make_id() {
        return vehicle_make_id;
    }

    @Override
    public String toString() {
        return model;
    }
}
