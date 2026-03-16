package WEEK4.practicproblems;


import java.util.*;

enum GearType { GUITAR, BASS, AMP, PEDAL, MICROPHONE, MIXER, DRUMS, ACCESSORY }
enum Condition { NEW, MINT, GOOD, FAIR, POOR }

class MusicEquipment {
    // Core fields
    private final String name;
    private final GearType type;
    private final String brand;
    private final double price;
    private final Condition condition;
    private final String serialNumber;
    private boolean rented;

    // --- Master constructor (all fields)
    public MusicEquipment(String name,
                          GearType type,
                          String brand,
                          double price,
                          Condition condition,
                          String serialNumber,
                          boolean rented) {
        this.name = (name == null || name.isBlank()) ? "Untitled Gear" : name;
        this.type = (type == null) ? GearType.ACCESSORY : type;
        this.brand = (brand == null || brand.isBlank()) ? "Generic" : brand;
        this.price = Math.max(price, 0.0);
        this.condition = (condition == null) ? Condition.GOOD : condition;
        this.serialNumber = (serialNumber == null) ? UUID.randomUUID().toString() : serialNumber;
        this.rented = rented;
    }

    // --- Overloaded constructors that CHAIN using this()

    // 1) Default: minimal sensible gear
    public MusicEquipment() {
        this("Standard Cable", GearType.ACCESSORY, "Generic",
             9.99, Condition.NEW, null, false);
    }

    // 2) Name only
    public MusicEquipment(String name) {
        this(name, GearType.ACCESSORY, "Generic",
             0.0, Condition.GOOD, null, false);
    }

    // 3) Name + Type
    public MusicEquipment(String name, GearType type) {
        this(name, type, "Generic",
             0.0, Condition.GOOD, null, false);
    }

    // 4) Name + Type + Brand
    public MusicEquipment(String name, GearType type, String brand) {
        this(name, type, brand,
             0.0, Condition.GOOD, null, false);
    }

    // 5) Name + Type + Brand + Price
    public MusicEquipment(String name, GearType type, String brand, double price) {
        this(name, type, brand,
             price, Condition.GOOD, null, false);
    }

    // 6) Name + Type + Brand + Price + Condition
    public MusicEquipment(String name, GearType type, String brand, double price, Condition condition) {
        this(name, type, brand,
             price, condition, null, false);
    }

    // Convenience behavior
    public void setRented(boolean rented) { this.rented = rented; }

    @Override
    public String toString() {
        return """
               MusicEquipment{
                 name='%s',
                 type=%s,
                 brand='%s',
                 price=%.2f,
                 condition=%s,
                 serial='%s',
                 rented=%s
               }""".formatted(name, type, brand, price, condition, serialNumber, rented);
    }
}

public class Music {
    public static void main(String[] args) {
        // Demonstrate constructor overloading + this() chaining

        // Uses default constructor -> chains to master with defaults
        MusicEquipment cable = new MusicEquipment();

        // Name only -> chains to master
        MusicEquipment sm57 = new MusicEquipment("SM57 Microphone");

        // Name + Type -> chains with type
        MusicEquipment jazzBass = new MusicEquipment("Jazz Bass", GearType.BASS);

        // Name + Type + Brand -> chains with brand
        MusicEquipment tubeScreamer = new MusicEquipment("Tube Screamer", GearType.PEDAL, "Ibanez");

        // Add price -> chains with price
        MusicEquipment fenderAmp = new MusicEquipment("Hot Rod DeVille", GearType.AMP, "Fender", 899.00);

        // Add condition -> chains with condition too
        MusicEquipment roadWornTele = new MusicEquipment(
                "Telecaster", GearType.GUITAR, "Fender", 1199.00, Condition.FAIR
        );

        // Full control via master constructor
        MusicEquipment boutiqueMic = new MusicEquipment(
                "U87 Ai", GearType.MICROPHONE, "Neumann",
                3200.00, Condition.MINT, "NEU-87AI-0001", false
        );

        // Toggle runtime state
        boutiqueMic.setRented(true);

        // Print inventory
        System.out.println("== Inventory ==");
        System.out.println(cable);
        System.out.println(sm57);
        System.out.println(jazzBass);
        System.out.println(tubeScreamer);
        System.out.println(fenderAmp);
        System.out.println(roadWornTele);
        System.out.println(boutiqueMic);
    }
}
