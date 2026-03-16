
class Car {
    String brand;
    String model;
    double price;

    Car(String brand, String model, double price) {
        this.brand = brand;
        this.model = model;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car Details → Brand: " + brand + ", Model: " + model + ", Price: ₹" + price;
    }
}

public class Topic {
    public static void main(String[] args) {
        Car car = new Car("Tesla", "Model S", 8500000);
        System.out.println(car); // invokes toString()
        System.out.println("Class Name: " + car.getClass().getName());
    }
}
