package WEEK4.practicproblems;

import java.util.*;
import java.util.function.Consumer;

enum DeviceType { SENSOR, BULB, PLUG, CAMERA }
enum Status { OFFLINE, ONLINE }

interface DeviceEventListener {
    void onStatusChanged(SmartDevice device, Status oldStatus, Status newStatus);
}

class SmartDevice {
    private final String id;
    private String name;
    private DeviceType type;
    private String firmware;
    private Status status = Status.OFFLINE;
    private int battery = 100; // %
    private DeviceEventListener listener;
    private Hub connectedHub; // where we pass `this` to register

    // Master constructor
    public SmartDevice(String id, String name, DeviceType type, String firmware, Status status, int battery) {
        // Parameter disambiguation using `this.field = param`
        this.id = (id == null || id.isBlank()) ? UUID.randomUUID().toString() : id;
        this.name = (name == null || name.isBlank()) ? "Unnamed Device" : name;
        this.type = (type == null) ? DeviceType.SENSOR : type;
        this.firmware = (firmware == null || firmware.isBlank()) ? "1.0.0" : firmware;
        this.status = (status == null) ? Status.OFFLINE : status;
        this.battery = Math.max(0, Math.min(100, battery));
    }

    // Constructor chaining with this(...)
    public SmartDevice(String id, String name, DeviceType type) {
        this(id, name, type, "1.0.0", Status.OFFLINE, 100);
    }

    public SmartDevice(String name) {
        this(null, name, DeviceType.SENSOR, "1.0.0", Status.OFFLINE, 100);
    }

    public SmartDevice() {
        this(null, "Generic Sensor", DeviceType.SENSOR, "1.0.0", Status.OFFLINE, 100);
    }

    // Fluent setters returning `this`
    public SmartDevice setName(String name)             { this.name = name; return this; }
    public SmartDevice setType(DeviceType type)         { this.type = type; return this; }
    public SmartDevice setFirmware(String fw)           { this.firmware = fw; return this; }
    public SmartDevice setBattery(int pct)              { this.battery = Math.max(0, Math.min(100, pct)); return this; }
    public SmartDevice onEvent(DeviceEventListener l)   { this.listener = l; return this; }

    // Methods that pass `this`
    public SmartDevice connectTo(Hub hub) {
        this.connectedHub = hub;
        hub.register(this);     // passing `this` to another object
        return this;
    }

    public void goOnline()  { changeStatus(Status.ONLINE); }
    public void goOffline() { changeStatus(Status.OFFLINE); }

    private void changeStatus(Status newStatus) {
        Status old = this.status;
        if (old != newStatus) {
            this.status = newStatus; // `this` clarifies field assignment
            if (this.listener != null) {
                this.listener.onStatusChanged(this, old, newStatus); // pass `this` to callbacks
            }
            if (this.connectedHub != null) {
                this.connectedHub.notifyStatus(this, old, newStatus); // pass `this` to hub
            }
        }
    }

    public String getId()       { return this.id; }
    public String getName()     { return this.name; }
    public DeviceType getType() { return this.type; }
    public String getFirmware() { return this.firmware; }
    public Status getStatus()   { return this.status; }
    public int getBattery()     { return this.battery; }

    @Override public boolean equals(Object o) {
        if (this == o) return true; // identity check using `this`
        if (!(o instanceof SmartDevice other)) return false;
        return Objects.equals(this.id, other.id);
    }

    @Override public int hashCode() { return Objects.hash(this.id); }

    @Override public String toString() {
        return "SmartDevice{id='%s', name='%s', type=%s, fw=%s, status=%s, battery=%d%%}"
                .formatted(this.id, this.name, this.type, this.firmware, this.status, this.battery);
    }
}

class Hub {
    private final String name;
    private final List<SmartDevice> devices = new ArrayList<>();
    private final List<DeviceEventListener> listeners = new ArrayList<>();

    public Hub(String name) {
        this.name = (name == null || name.isBlank()) ? "Main Hub" : name;
    }

    // Parameter name intentionally shadows a concept; we use `this.devices` to be explicit
    public void register(SmartDevice device) {
        Objects.requireNonNull(device);
        if (!this.devices.contains(device)) {
            this.devices.add(device); // `this.devices` clarifies field access
        }
    }

    public void addListener(DeviceEventListener l) { this.listeners.add(l); }

    // Inner class referencing the outer instance with Hub.this
    private class LoggerListener implements DeviceEventListener {
        @Override public void onStatusChanged(SmartDevice d, Status oldS, Status newS) {
            System.out.println("[LOG@" + Hub.this.name + "] " + d.getName() + " -> " + newS);
        }
    }

    public void enableLogging() {
        this.addListener(new LoggerListener()); // inner class capturing Hub.this
    }

    public void notifyStatus(SmartDevice device, Status oldS, Status newS) {
        for (DeviceEventListener l : this.listeners) l.onStatusChanged(device, oldS, newS);
    }

    public Optional<SmartDevice> findById(String id) {
        for (SmartDevice d : this.devices) {
            if (d.getId().equals(id)) return Optional.of(d);
        }
        return Optional.empty();
    }

    public void forEachDevice(Consumer<SmartDevice> action) {
        for (SmartDevice d : this.devices) action.accept(d);
    }

    @Override public String toString() {
        return "Hub{name='%s', devices=%d}".formatted(this.name, this.devices.size());
    }
}

public class SmartDevice {
    public static void main(String[] args) {
        Hub home = new Hub("Home Hub")./* just to show `this` inside Hub methods */toString();

        Hub hub = new Hub("Studio Hub");
        hub.enableLogging();
        hub.addListener((device, oldS, newS) ->
                System.out.println("[BUS@" + device.getType() + "] " + device.getName() + " changed: " + oldS + " -> " + newS));

        // Build devices with constructor chaining + `this` disambiguation
        SmartDevice temp = new SmartDevice("Temp Sensor")          // chains to master
                .setType(DeviceType.SENSOR)
                .setFirmware("1.2.3")
                .setBattery(87)
                .onEvent((d, o, n) -> System.out.println("[DEV@" + d.getName() + "] " + o + " -> " + n))
                .connectTo(hub); // pass `this` into hub

        SmartDevice bulb = new SmartDevice("bulb-01", "Ceiling Bulb", DeviceType.BULB)
                .setFirmware("2.0.0").connectTo(hub);

        SmartDevice cam = new SmartDevice() // default -> chains to master
                .setName("Door Camera").setType(DeviceType.CAMERA).connectTo(hub);

        // Trigger activity
        temp.goOnline();
        bulb.goOnline();
        cam.goOffline(); // stays offline; still triggers logs via hub listeners

        // Iterate devices
        System.out.println("\n== Inventory ==");
        hub.forEachDevice(System.out::println);

        // Find by id, show equals usage context
        String someId = temp.getId();
        System.out.println("\nFind by id: " + someId);
        hub.findById(someId).ifPresent(d -> System.out.println("Found: " + d));
    }
}
