package WEEK4.labproblems;

import java.util.*;

/**
 * Practice Problem 1: Virtual Pet Evolution Simulator
 * Topics: Constructor Overloading, this() Chaining, final, static
 */
class VirtualPet {
    // ===== Static & constants =====
    public static final String[] EVOLUTION_STAGES = {"Egg", "Baby", "Child", "Teen", "Adult", "Elder"};
    private static final String[] SPECIES_POOL = {"Dracoon", "Pawbit", "Slimee", "Voltkit", "Leafling", "Pebblor"};
    public static int totalPetsCreated = 0;
    private static final Random RNG = new Random();

    // ===== Fields =====
    public final String petId;             // unique & immutable
    private String petName;
    private String species;                // becomes "Ghost" when dead
    private int age;                       // in days
    private int happiness;                 // 0..100
    private int health;                    // 0..100
    private int stageIndex;                // index into EVOLUTION_STAGES
    private boolean isGhost;               // after death

    // ===== Master constructor (all fields) =====
    private VirtualPet(String petId, String petName, String species,
                       int age, int happiness, int health,
                       int stageIndex, boolean isGhost) {
        // disambiguation with this.field = param
        this.petId     = (petId == null || petId.isEmpty()) ? generatePetId() : petId;
        this.petName   = (petName == null || petName.trim().isEmpty()) ? "Mystery" : petName.trim();
        this.species   = (species == null || species.trim().isEmpty()) ? randomSpecies() : species.trim();
        this.age       = Math.max(0, age);
        this.happiness = clamp(happiness, 0, 100);
        this.health    = clamp(health, 0, 100);
        this.stageIndex = clamp(stageIndex, 0, EVOLUTION_STAGES.length - 1);
        this.isGhost   = isGhost;
        totalPetsCreated++;
    }

    // ===== Constructor Overloads + this() chaining =====

    // Default: mysterious egg with random species (Egg stage, low age)
    public VirtualPet() {
        this(null, "Mystery", randomSpecies(),
             0, 50, 60, 0, false);
    }

    // Name only: starts as Baby
    public VirtualPet(String petName) {
        this(null, petName, randomSpecies(),
             1, 60, 70, 1, false);
    }

    // Name + species: starts as Child
    public VirtualPet(String petName, String species) {
        this(null, petName, species,
             3, 65, 70, 2, false);
    }

    // Full constructor: you set stats and stage (by String)
    public VirtualPet(String petName, String species, int age, int happiness, int health, String stage) {
        this(null, petName, species, age, happiness, health,
             stageToIndex(stage), false);
    }

    // ===== Core methods =====
    public static String generatePetId() {
        // UUID-like ID
        return "PET-" + UUID.randomUUID().toString();
    }

    private static int stageToIndex(String stage) {
        if (stage == null) return 0;
        for (int i = 0; i < EVOLUTION_STAGES.length; i++) {
            if (EVOLUTION_STAGES[i].equalsIgnoreCase(stage.trim())) return i;
        }
        return 0; // default to Egg if unknown
    }

    private static String randomSpecies() {
        return SPECIES_POOL[RNG.nextInt(SPECIES_POOL.length)];
    }

    private static int clamp(int v, int lo, int hi) {
        return Math.max(lo, Math.min(hi, v));
    }

    public String getPetStatus() {
        return isGhost ? "Ghost" : EVOLUTION_STAGES[stageIndex];
    }

    public String getName() { return petName; }
    public String getSpecies() { return species; }
    public int getAge() { return age; }
    public int getHappiness() { return happiness; }
    public int getHealth() { return health; }
    public boolean isGhost() { return isGhost; }

    // ===== Care actions =====
    public void feedPet() {
        if (isGhost) return; // ghosts don't eat
        happiness = clamp(happiness + 4, 0, 100);
        health = clamp(health + 6, 0, 100);
    }

    public void playWithPet() {
        if (isGhost) return; // ghosts don't play
        happiness = clamp(happiness + 8, 0, 100);
        // play can tire the pet slightly
        health = clamp(health - 2, 0, 100);
    }

    public void healPet() {
        if (isGhost) return; // ghosts can't be healed
        health = clamp(health + 10, 0, 100);
        // healing might not make them happier
        happiness = clamp(happiness + 1, 0, 100);
    }

    // ===== Evolution logic =====
    public void evolvePet() {
        if (isGhost) return; // ghosts can't evolve
        // Stages based on age with care gates:
        // Gate: must have decent health & happiness to evolve on time
        int targetStage = determineStageByAge();
        boolean careOK = (health >= 40 && happiness >= 40);

        if (targetStage > stageIndex && careOK) {
            stageIndex = targetStage;
        } else if (targetStage > stageIndex && !careOK) {
            // poor care delays evolution; 25% chance to still evolve
            if (RNG.nextInt(100) < 25) stageIndex++;
        }
        // If extremely well cared for, allow early evolution (once)
        if (health >= 85 && happiness >= 85 && stageIndex < EVOLUTION_STAGES.length - 1) {
            if (RNG.nextInt(100) < 15) stageIndex++;
        }
    }

    private int determineStageByAge() {
        // Simple age thresholds: 0 Egg, 1 Baby, 3 Child, 6 Teen, 10 Adult, 16 Elder
        if      (age >= 16) return 5;
        else if (age >= 10) return 4;
        else if (age >= 6)  return 3;
        else if (age >= 3)  return 2;
        else if (age >= 1)  return 1;
        else                return 0;
    }

    // ===== Simulation =====
    public void simulateDay() {
        if (isGhost) {
            // ghosts slowly fade (no change to health/happiness, just ambience)
            return;
        }

        age += 1;

        // Random daily effects
        int moodSwing = RNG.nextInt(11) - 5; // -5..+5
        int appetite  = RNG.nextInt(7) - 3;  // -3..+3
        happiness = clamp(happiness + moodSwing, 0, 100);
        health    = clamp(health + appetite, 0, 100);

        // Chance of catching a cold if health is middling
        if (health >= 20 && health <= 50 && RNG.nextInt(100) < 10) {
            health = clamp(health - 8, 0, 100);
        }

        // Natural recovery a bit if very low happiness
        if (happiness < 25 && RNG.nextInt(100) < 20) {
            happiness = clamp(happiness + 5, 0, 100);
        }

        // Death condition
        if (health <= 0) {
            becomeGhost();
            return;
        }

        evolvePet();
    }

    private void becomeGhost() {
        isGhost = true;
        species = "Ghost";
        stageIndex = 0; // (ignored by status; ghost overrides)
        happiness = 0;
        health = 0;
    }

    // Ghost ability: haunt other pets (cannot haunt ghosts)
    public void haunt(VirtualPet other) {
        if (!this.isGhost || other == null || other.isGhost) return;
        // Spook them a little
        other.happiness = clamp(other.happiness - 6, 0, 100);
        // Some pets get stressed and lose a bit of health
        if (RNG.nextInt(100) < 40) {
            other.health = clamp(other.health - 3, 0, 100);
            if (other.health == 0) other.becomeGhost();
        }
    }

    // ===== Pretty printing =====
    @Override
    public String toString() {
        return String.format(
            "%s (%s) | ID=%s | Age=%dd | Stage=%s | ❤=%d ☺=%d%s",
            petName, species, petId, age, getPetStatus(), health, happiness,
            isGhost ? " | 👻" : ""
        );
    }
}

public class PetSimulator {
    public static void main(String[] args) {
        // Daycare: create pets using different constructors
        VirtualPet eggMystery      = new VirtualPet();                                       // Egg
        VirtualPet babyNamed       = new VirtualPet("Pippin");                               // Baby
        VirtualPet childNamedSpec  = new VirtualPet("Zara", "Voltkit");                      // Child
        VirtualPet customStats     = new VirtualPet("Grom", "Dracoon", 5, 62, 58, "Teen");   // Full

        List<VirtualPet> daycare = new ArrayList<>();
        daycare.add(eggMystery);
        daycare.add(babyNamed);
        daycare.add(childNamedSpec);
        daycare.add(customStats);

        System.out.println("=== Virtual Pet Daycare ===");
        printRoster(daycare);

        // Simulate several days
        int days = 10;
        for (int d = 1; d <= days; d++) {
            System.out.println("\n--- Day " + d + " ---");

            // Simple care routine per day
            for (VirtualPet pet : daycare) {
                // Random care each day
                int action = new Random().nextInt(100);
                if (!pet.isGhost()) {
                    if (action < 35) pet.feedPet();
                    else if (action < 70) pet.playWithPet();
                    else pet.healPet();
                }
                pet.simulateDay();
            }

            // If any ghost exists, it might haunt a random living pet
            Optional<VirtualPet> ghost = daycare.stream().filter(VirtualPet::isGhost).findAny();
            if (ghost.isPresent()) {
                List<VirtualPet> living = new ArrayList<>();
                for (VirtualPet p : daycare) if (!p.isGhost()) living.add(p);
                if (!living.isEmpty()) {
                    VirtualPet target = living.get(new Random().nextInt(living.size()));
                    ghost.get().haunt(target);
                    System.out.println("👻 " + ghost.get().getName() + " haunted " + target.getName() + "!");
                }
            }

            printRoster(daycare);
        }

        System.out.println("\nTotal pets ever created: " + VirtualPet.totalPetsCreated);
    }

    private static void printRoster(List<VirtualPet> pets) {
        for (VirtualPet p : pets) System.out.println(p);
    }
}
