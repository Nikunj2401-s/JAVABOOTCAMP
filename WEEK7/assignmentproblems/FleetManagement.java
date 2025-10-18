package WEEK7.assignmentproblems;

abstract class Vehicle {
    abstract void dispatch();
}

class Bus extends Vehicle {
    void dispatch() { System.out.println("🚌 Bus dispatched on fixed route, tracking passengers."); }
}

class Taxi extends Vehicle {
    void dispatch() { System.out.println("🚕 Taxi dispatched, calculating fare by distance."); }
}

class Train extends Vehicle {
    void dispatch() { System.out.println("🚆 Train dispatched on schedule, managing cars."); }
}

class Bike extends Vehicle {
    void dispatch() { System.out.println("🚴 Bike dispatched for eco-friendly short trips."); }
}

public class FleetManagement {
    public static void main(String[] args) {
        Vehicle[] fleet = {new Bus(), new Taxi(), new Train(), new Bike()};
        for(Vehicle v : fleet) v.dispatch();
    }
}

