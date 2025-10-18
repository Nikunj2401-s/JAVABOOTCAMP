package WEEK9.practiceproblems;

// HospitalManagement.java

class Hospital {
    private String hospitalName;
    private String address;

    // Constructor for outer class
    public Hospital(String hospitalName, String address) {
        this.hospitalName = hospitalName;
        this.address = address;
    }

    // Member Inner Class — logically represents a department *inside* a hospital
    class Department {
        private String deptName;
        private int totalDoctors;

        // Constructor for inner class
        public Department(String deptName, int totalDoctors) {
            this.deptName = deptName;
            this.totalDoctors = totalDoctors;
        }

        // Inner class can access private members of the outer class
        public void showDetails() {
            System.out.println("Hospital: " + hospitalName);
            System.out.println("Address: " + address);
            System.out.println("Department: " + deptName);
            System.out.println("Total Doctors: " + totalDoctors);
        }
    }

    // Method in outer class
    public void displayInfo() {
        System.out.println("Welcome to " + hospitalName + " located at " + address);
    }
}

// Main class
public class HospitalManagement {
    public static void main(String[] args) {
        // Create outer class object
        Hospital hospital = new Hospital("CityCare MultiSpeciality Hospital", "Downtown Street, Mumbai");

        // Create inner class object using outer class reference
        Hospital.Department cardioDept = hospital.new Department("Cardiology", 12);
        Hospital.Department orthoDept = hospital.new Department("Orthopedics", 8);

        System.out.println("=== Hospital Management System ===\n");
        hospital.displayInfo();

        System.out.println("\n--- Department Details ---");
        cardioDept.showDetails();
        System.out.println();
        orthoDept.showDetails();
    }
}
