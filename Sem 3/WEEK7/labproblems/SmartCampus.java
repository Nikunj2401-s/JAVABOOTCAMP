package WEEK7.labproblems;

class SmartDevice {
    String name;
    SmartDevice(String name) { this.name = name; }
}

class SmartClassroom extends SmartDevice {
    SmartClassroom(String n) { super(n); }
    void controlClassroom() { System.out.println(name + " → Controlling lights, AC, and projector."); }
}

class SmartLab extends SmartDevice {
    SmartLab(String n) { super(n); }
    void manageLab() { System.out.println(name + " → Managing equipment and safety systems."); }
}

class SmartLibrary extends SmartDevice {
    SmartLibrary(String n) { super(n); }
    void trackLibrary() { System.out.println(name + " → Tracking occupancy and book availability."); }
}

public class SmartCampus {
    public static void main(String[] args) {
        SmartDevice[] devices = {
            new SmartClassroom("Classroom A"),
            new SmartLab("Physics Lab"),
            new SmartLibrary("Main Library")
        };

        for(SmartDevice d : devices) {
            if(d instanceof SmartClassroom) {
                ((SmartClassroom)d).controlClassroom();
            } else if(d instanceof SmartLab) {
                ((SmartLab)d).manageLab();
            } else if(d instanceof SmartLibrary) {
                ((SmartLibrary)d).trackLibrary();
            }
        }
    }
}

