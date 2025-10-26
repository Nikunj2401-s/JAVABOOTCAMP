class HospitalSystem {
    public void manageAppointments(String patientName) {
        System.out.println("Appointment booked for: " + patientName);
    }

    public void updateRecords(String doctorName) {
        System.out.println("Doctor " + doctorName + " updated patient records.");
    }

    public void generateBills(String patientName, double amount) {
        System.out.println("Bill generated for " + patientName + ": ₹" + amount);
        viewReports();
    }

    public void viewReports() {
        System.out.println("Admin viewed hospital report.");
    }
}

public class HospitalManagamentDemo {
    public static void main(String[] args) {
        HospitalSystem hs = new HospitalSystem();

        // Patient action
        hs.manageAppointments("Ravi");

        // Doctor action
        hs.updateRecords("Dr. Meena");

        // Admin action
        hs.generateBills("Ravi", 2500);
    }
}
