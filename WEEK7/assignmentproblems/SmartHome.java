package WEEK7.assignmentproblems;

class SmartDevice {
    String name;
    SmartDevice(String n) { name = n; }
}

class SmartTV extends SmartDevice {
    SmartTV(String n) { super(n); }
    void controlTV() { System.out.println(name + " → Managing channels and streaming apps."); }
}

class SmartThermostat extends SmartDevice {
    SmartThermostat(String n) { super(n); }
    void controlTemp() { System.out.println(name + " → Controlling temperature and energy modes."); }
}

class SmartSecurity extends SmartDevice {
    SmartSecurity(String n) { super(n); }
    void controlSecurity() { System.out.println(name + " → Monitoring cameras and alarms."); }
}

class SmartKitchen extends SmartDevice {
    SmartKitchen(String n) { super(n); }
    void controlKitchen() { System.out.println(name + " → Managing cooking and recipes."); }
}

public class SmartHome {
    public static void main(String[] args) {
        SmartDevice[] devices = {
            new SmartTV("Living Room TV"),
            new SmartThermostat("Nest Thermostat"),
            new SmartSecurity("Front Door Security"),
            new SmartKitchen("Smart Oven")
        };

        for(SmartDevice d : devices) {
            if(d instanceof SmartTV) ((SmartTV)d).controlTV();
            else if(d instanceof SmartThermostat) ((SmartThermostat)d).controlTemp();
            else if(d instanceof SmartSecurity) ((SmartSecurity)d).controlSecurity();
            else if(d instanceof SmartKitchen) ((SmartKitchen)d).controlKitchen();
        }
    }
}
