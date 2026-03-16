package WEEK5.labproblems;

import java.util.*;

// ---------- Immutable PetSpecies ----------
final class PetSpecies {
    private final String speciesName;
    private final String[] evolutionStages;
    private final int maxLifespan;
    private final String habitat;

    public PetSpecies(String speciesName, String[] evolutionStages, int maxLifespan, String habitat) {
        if (speciesName == null || speciesName.isBlank())
            throw new IllegalArgumentException("Invalid species name");
        if (evolutionStages == null || evolutionStages.length == 0)
            throw new IllegalArgumentException("Invalid evolution stages");
        if (maxLifespan <= 0)
            throw new IllegalArgumentException("Invalid lifespan");
        if (habitat == null || habitat.isBlank())
            throw new IllegalArgumentException("Invalid habitat");

        this.speciesName = speciesName;
        this.evolutionStages = Arrays.copyOf(evolutionStages, evolutionStages.length);
        this.maxLifespan = maxLifespan;
        this.habitat = habitat;
    }

    public String getSpeciesName() { return speciesName; }
    public String[] getEvolutionStages() { return Arrays.copyOf(evolutionStages, evolutionStages.length); }
    public int getMaxLifespan() { return maxLifespan; }
    public String getHabitat() { return habitat; }
}

// ---------- VirtualPet ----------
class VirtualPet {
    // Immutable core
    private final String petId;
    private final PetSpecies species;
    private final long birthTimestamp;

    // Controlled mutable state
    private String petName;
    private int age;
    private int happiness;
    private int health;

    // Protected static
    protected static final String[] DEFAULT_EVOLUTION_STAGES = {
        "Egg", "Child", "Adult", "Elder"
    };

    // Package-private constants
    static final int MAX_HAPPINESS = 100;
    static final int MAX_HEALTH = 100;

    // Global constant
    public static final String PET_SYSTEM_VERSION = "2.0";

    // Constructors (with chaining)
    public VirtualPet() {
        this("Pet" + new Random().nextInt(1000),
             new PetSpecies("Default", DEFAULT_EVOLUTION_STAGES, 10, "Home"),
             0, 50, 50);
    }

    public VirtualPet(String petName) {
        this(petName, new PetSpecies("Default", DEFAULT_EVOLUTION_STAGES, 10, "Home"));
    }

    public VirtualPet(String petName, PetSpecies species) {
        this(petName, species, 0, 50, 50);
    }

    public VirtualPet(String petName, PetSpecies species, int age, int happiness, int health) {
        this.petId = generatePetId();
        this.birthTimestamp = System.currentTimeMillis();
        this.species = Objects.requireNonNull(species);

        this.petName = Objects.requireNonNull(petName);
        setAge(age);
        setHappiness(happiness);
        setHealth(health);
    }

    // JavaBean Getters/Setters
    public String getPetId() { return petId; }
    public PetSpecies getSpecies() { return species; }
    public long getBirthTimestamp() { return birthTimestamp; }

    public String getPetName() { return petName; }
    public void setPetName(String petName) { this.petName = Objects.requireNonNull(petName); }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = Math.max(0, age); }

    public int getHappiness() { return happiness; }
    public void setHappiness(int happiness) { this.happiness = validateStat(happiness); }

    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = validateStat(health); }

    // Public methods
    public void feedPet(String foodType) {
        int bonus = calculateFoodBonus(foodType);
        modifyHealth(bonus);
    }

    public void playWithPet(String gameType) {
        int bonus = calculateGameEffect(gameType);
        modifyHappiness(bonus);
    }

    // Protected
    protected int calculateFoodBonus(String foodType) {
        return foodType.equalsIgnoreCase("fruit") ? 10 : 5;
    }

    protected int calculateGameEffect(String gameType) {
        return gameType.equalsIgnoreCase("chase") ? 15 : 8;
    }

    // Private helpers
    private void modifyHappiness(int delta) {
        setHappiness(Math.min(MAX_HAPPINESS, happiness + delta));
        updateEvolutionStage();
    }

    private void modifyHealth(int delta) {
        setHealth(Math.min(MAX_HEALTH, health + delta));
        updateEvolutionStage();
    }

    private void updateEvolutionStage() {
        checkEvolution();
    }

    private static int validateStat(int stat) {
        if (stat < 0 || stat > 100) throw new IllegalArgumentException("Stat out of range");
        return stat;
    }

    private static String generatePetId() {
        return "PET-" + System.nanoTime();
    }

    private void checkEvolution() {
        if (happiness + health > 150) {
            System.out.println(petName + " evolved!");
        }
    }

    // Package-private
    String getInternalState() {
        return "ID=" + petId + ", Name=" + petName + ", Age=" + age +
               ", HP=" + health + ", Happy=" + happiness;
    }

    // Overrides
    @Override
    public String toString() {
        return "VirtualPet{" +
                "id='" + petId + '\'' +
                ", name='" + petName + '\'' +
                ", species=" + species.getSpeciesName() +
                ", age=" + age +
                ", happiness=" + happiness +
                ", health=" + health +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VirtualPet)) return false;
        VirtualPet pet = (VirtualPet) o;
        return petId.equals(pet.petId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(petId);
    }
}

// ---------- Specialized Pets (composition) ----------
class DragonPet {
    private final String dragonType;
    private final String breathWeapon;
    private final VirtualPet basePet;

    public DragonPet(String name, String dragonType, String breathWeapon) {
        this.dragonType = Objects.requireNonNull(dragonType);
        this.breathWeapon = Objects.requireNonNull(breathWeapon);
        this.basePet = new VirtualPet(name, new PetSpecies(
                "Dragon", new String[]{"Egg", "Wyrmling", "Drake", "Ancient"}, 200, "Caves"));
    }

    public String getDragonType() { return dragonType; }
    public String getBreathWeapon() { return breathWeapon; }
    public VirtualPet getBasePet() { return basePet; }
}

class RobotPet {
    private boolean needsCharging;
    private int batteryLevel;
    private final VirtualPet basePet;

    public RobotPet(String name) {
        this.basePet = new VirtualPet(name, new PetSpecies(
                "Robot", new String[]{"Prototype", "Model-X", "AI-Unit"}, 50, "Lab"));
        this.batteryLevel = 100;
        this.needsCharging = false;
    }

    public boolean isNeedsCharging() { return needsCharging; }
    public void setNeedsCharging(boolean needsCharging) { this.needsCharging = needsCharging; }

    public int getBatteryLevel() { return batteryLevel; }
    public void setBatteryLevel(int batteryLevel) {
        if (batteryLevel < 0 || batteryLevel > 100) throw new IllegalArgumentException();
        this.batteryLevel = batteryLevel;
        this.needsCharging = batteryLevel < 20;
    }

    public VirtualPet getBasePet() { return basePet; }
}
public class VirtualPetDemo {
    public static void main(String[] args) {
        VirtualPet pet = new VirtualPet("Buddy");
        System.out.println(pet);
        pet.feedPet("fruit");
        pet.playWithPet("chase");
        System.out.println(pet.getInternalState());

        DragonPet dragon = new DragonPet("Smaug", "Fire", "Flame Breath");
        System.out.println(dragon.getBasePet());

        RobotPet robot = new RobotPet("R2D2");
        robot.setBatteryLevel(15);
        System.out.println(robot.getBasePet());
        System.out.println("Needs charging? " + robot.isNeedsCharging());
    }
}
