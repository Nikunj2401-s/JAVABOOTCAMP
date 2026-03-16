package WEEK7.assignmentproblems;

class MedicalStaff {
    void commonDuties() { System.out.println("General hospital duties: shift scheduling, payroll, ID access."); }
}

class Doctor extends MedicalStaff {
    void diagnose() { System.out.println("Doctor diagnoses and prescribes medicine."); }
}

class Nurse extends MedicalStaff {
    void assist() { System.out.println("Nurse administers medicine and monitors patients."); }
}

class Technician extends MedicalStaff {
    void operate() { System.out.println("Technician runs tests and maintains equipment."); }
}

class Administrator extends MedicalStaff {
    void manage() { System.out.println("Administrator schedules appointments and manages records."); }
}

public class HospitalSystem {
    public static void main(String[] args) {
        MedicalStaff staff1 = new Doctor();   // upcasting
        MedicalStaff staff2 = new Nurse();
        MedicalStaff staff3 = new Technician();
        MedicalStaff staff4 = new Administrator();

        staff1.commonDuties();
        staff2.commonDuties();
        staff3.commonDuties();
        staff4.commonDuties();
    }
}

