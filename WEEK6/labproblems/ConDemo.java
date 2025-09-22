package WEEK6.labproblems;

class Phone {
    String brand, model;

    Phone(String brand, String model) {
        this.brand = brand;
        this.model = model;
        System.out.println("Phone Constructor: " + brand + " " + model);
    }
}

class SmartPhone extends Phone {
    String operatingSystem;

    SmartPhone(String brand, String model, String os) {
        super(brand, model);  // Call parent constructor
        this.operatingSystem = os;
        System.out.println("SmartPhone Constructor: OS = " + os);
    }
}

public class ConDemo {
    public static void main(String[] args) {
        SmartPhone sp = new SmartPhone("Samsung", "Galaxy S24", "Android");
    }
}

