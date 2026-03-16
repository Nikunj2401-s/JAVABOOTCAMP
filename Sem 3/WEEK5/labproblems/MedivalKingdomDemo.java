    package WEEK5.labproblems;

    import java.util.*;

    // ---------- Immutable KingdomConfig ----------
    final class KingdomConfig {
        private final String kingdomName;
        private final int foundingYear;
        private final String[] allowedStructureTypes;
        private final Map<String, Integer> resourceLimits;

        public KingdomConfig(String kingdomName, int foundingYear, String[] allowedTypes,
                            Map<String, Integer> resourceLimits) {
            if (kingdomName == null || kingdomName.isBlank())
                throw new IllegalArgumentException("Invalid kingdom name");
            if (foundingYear <= 0)
                throw new IllegalArgumentException("Invalid year");
            if (allowedTypes == null || allowedTypes.length == 0)
                throw new IllegalArgumentException("Allowed types missing");
            if (resourceLimits == null || resourceLimits.isEmpty())
                throw new IllegalArgumentException("Resource limits missing");

            this.kingdomName = kingdomName;
            this.foundingYear = foundingYear;
            this.allowedStructureTypes = Arrays.copyOf(allowedTypes, allowedTypes.length);
            this.resourceLimits = new HashMap<>(resourceLimits);
        }

        public String getKingdomName() { return kingdomName; }
        public int getFoundingYear() { return foundingYear; }
        public String[] getAllowedStructureTypes() {
            return Arrays.copyOf(allowedStructureTypes, allowedStructureTypes.length);
        }
        public Map<String, Integer> getResourceLimits() {
            return new HashMap<>(resourceLimits);
        }

        // Factory methods
        public static KingdomConfig createDefaultKingdom() {
            return new KingdomConfig("Avaloria", 1024,
                    new String[]{"WizardTower", "EnchantedCastle", "MysticLibrary", "DragonLair"},
                    Map.of("gold", 10000, "mana", 5000));
        }

        public static KingdomConfig createFromTemplate(String type) {
            switch (type.toLowerCase()) {
                case "small":
                    return new KingdomConfig("SmallKingdom", 1200,
                            new String[]{"Castle"}, Map.of("gold", 1000, "mana", 200));
                case "empire":
                    return new KingdomConfig("GrandEmpire", 800,
                            new String[]{"WizardTower", "Castle", "Library", "Lair"},
                            Map.of("gold", 50000, "mana", 20000));
                default:
                    return createDefaultKingdom();
            }
        }
    }

    // ---------- Base MagicalStructure ----------
    class MagicalStructure {
        // Immutable identity
        private final String structureId;
        private final long constructionTimestamp;

        // Immutable properties
        private final String structureName;
        private final String location;

        // Controlled state
        private int magicPower;
        private boolean isActive;
        private String currentMaintainer;

        // Package constants
        static final int MIN_MAGIC_POWER = 0;
        static final int MAX_MAGIC_POWER = 1000;

        // Global constant
        public static final String MAGIC_SYSTEM_VERSION = "3.0";

        // Constructor chaining
        public MagicalStructure(String name, String location) {
            this(name, location, 100, true);
        }

        public MagicalStructure(String name, String location, int power) {
            this(name, location, power, true);
        }

        public MagicalStructure(String name, String location, int power, boolean active) {
            if (name == null || name.isBlank())
                throw new IllegalArgumentException("Invalid name");
            if (location == null || location.isBlank())
                throw new IllegalArgumentException("Invalid location");
            if (power < MIN_MAGIC_POWER || power > MAX_MAGIC_POWER)
                throw new IllegalArgumentException("Invalid magic power");

            this.structureId = "STR-" + System.nanoTime();
            this.constructionTimestamp = System.currentTimeMillis();
            this.structureName = name;
            this.location = location;
            this.magicPower = power;
            this.isActive = active;
            this.currentMaintainer = "Unknown";
        }

        // Getters/Setters
        public String getStructureId() { return structureId; }
        public long getConstructionTimestamp() { return constructionTimestamp; }
        public String getStructureName() { return structureName; }
        public String getLocation() { return location; }

        public int getMagicPower() { return magicPower; }
        public void setMagicPower(int magicPower) {
            if (magicPower < MIN_MAGIC_POWER || magicPower > MAX_MAGIC_POWER)
                throw new IllegalArgumentException("Out of range");
            this.magicPower = magicPower;
        }

        public boolean isActive() { return isActive; }
        public void setActive(boolean active) { isActive = active; }

        public String getCurrentMaintainer() { return currentMaintainer; }
        public void setCurrentMaintainer(String currentMaintainer) {
            this.currentMaintainer = Objects.requireNonNull(currentMaintainer);
        }

        @Override
        public String toString() {
            return "MagicalStructure{" +
                    "id='" + structureId + '\'' +
                    ", name='" + structureName + '\'' +
                    ", location='" + location + '\'' +
                    ", power=" + magicPower +
                    ", active=" + isActive +
                    ", maintainer='" + currentMaintainer + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof MagicalStructure)) return false;
            MagicalStructure that = (MagicalStructure) o;
            return structureId.equals(that.structureId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(structureId);
        }
    }

    // ---------- Specialized Structures ----------
    class WizardTower {
        private final int maxSpellCapacity;
        private List<String> knownSpells;
        private String currentWizard;
        private final MagicalStructure base;

        // Empty tower
        public WizardTower(String name, String location) {
            this.base = new MagicalStructure(name, location);
            this.maxSpellCapacity = 10;
            this.knownSpells = new ArrayList<>();
            this.currentWizard = "None";
        }

        // Basic spells
        public WizardTower(String name, String location, List<String> spells) {
            this.base = new MagicalStructure(name, location, 300);
            this.maxSpellCapacity = 20;
            this.knownSpells = new ArrayList<>(spells);
            this.currentWizard = "Apprentice";
        }

        // Fully equipped
        public WizardTower(String name, String location, int capacity, String wizard) {
            this.base = new MagicalStructure(name, location, 600);
            this.maxSpellCapacity = capacity;
            this.knownSpells = new ArrayList<>(Arrays.asList("Fireball", "Teleport", "Shield"));
            this.currentWizard = wizard;
        }

        @Override
        public String toString() {
            return "WizardTower{" + base +
                    ", maxSpellCapacity=" + maxSpellCapacity +
                    ", knownSpells=" + knownSpells +
                    ", currentWizard='" + currentWizard + '\'' +
                    '}';
        }
    }

    class EnchantedCastle {
        private final String castleType;
        private int defenseRating;
        private boolean hasDrawbridge;
        private final MagicalStructure base;

        // Simple fort
        public EnchantedCastle(String name, String location) {
            this.base = new MagicalStructure(name, location);
            this.castleType = "Fort";
            this.defenseRating = 100;
            this.hasDrawbridge = false;
        }

        // Royal castle
        public EnchantedCastle(String name, String location, boolean drawbridge) {
            this.base = new MagicalStructure(name, location, 400);
            this.castleType = "Royal";
            this.defenseRating = 500;
            this.hasDrawbridge = drawbridge;
        }

        // Impregnable fortress
        public EnchantedCastle(String name, String location, int defense) {
            this.base = new MagicalStructure(name, location, 800);
            this.castleType = "Fortress";
            this.defenseRating = defense;
            this.hasDrawbridge = true;
        }

        @Override
        public String toString() {
            return "EnchantedCastle{" + base +
                    ", castleType='" + castleType + '\'' +
                    ", defenseRating=" + defenseRating +
                    ", hasDrawbridge=" + hasDrawbridge +
                    '}';
        }
    }

    class MysticLibrary {
        private final Map<String, String> bookCollection;
        private int knowledgeLevel;
        private final MagicalStructure base;

        // Few books
        public MysticLibrary(String name, String location) {
            this.base = new MagicalStructure(name, location);
            this.bookCollection = new HashMap<>();
            this.bookCollection.put("Intro Magic", "Beginner");
            this.knowledgeLevel = 50;
        }

        // Moderate collection
        public MysticLibrary(String name, String location, Map<String, String> books) {
            this.base = new MagicalStructure(name, location, 300);
            this.bookCollection = new HashMap<>(books);
            this.knowledgeLevel = 200;
        }

        // Ancient archives
        public MysticLibrary(String name, String location, int knowledgeLevel) {
            this.base = new MagicalStructure(name, location, 700);
            this.bookCollection = new HashMap<>();
            this.bookCollection.put("Arcane Secrets", "Ancient");
            this.knowledgeLevel = knowledgeLevel;
        }

        @Override
        public String toString() {
            return "MysticLibrary{" + base +
                    ", books=" + bookCollection +
                    ", knowledgeLevel=" + knowledgeLevel +
                    '}';
        }
    }

    class DragonLair {
        private final String dragonType;
        private long treasureValue;
        private int territorialRadius;
        private final MagicalStructure base;

        // Fire dragon
        public DragonLair(String name, String location) {
            this.base = new MagicalStructure(name, location, 500);
            this.dragonType = "Fire";
            this.treasureValue = 1000;
            this.territorialRadius = 50;
        }

        // Ice dragon
        public DragonLair(String name, String location, long treasure) {
            this.base = new MagicalStructure(name, location, 700);
            this.dragonType = "Ice";
            this.treasureValue = treasure;
            this.territorialRadius = 80;
        }

        // Custom dragon
        public DragonLair(String name, String location, String type, int radius) {
            this.base = new MagicalStructure(name, location, 900);
            this.dragonType = type;
            this.treasureValue = 5000;
            this.territorialRadius = radius;
        }

        @Override
        public String toString() {
            return "DragonLair{" + base +
                    ", dragonType='" + dragonType + '\'' +
                    ", treasureValue=" + treasureValue +
                    ", territorialRadius=" + territorialRadius +
                    '}';
        }
    }

    // ---------- KingdomManager ----------
    class KingdomManager {
        private final List<Object> structures;
        private final KingdomConfig config;

        public KingdomManager(KingdomConfig config) {
            this.config = Objects.requireNonNull(config);
            this.structures = new ArrayList<>();
        }

        public void addStructure(Object structure) { structures.add(structure); }

        public static boolean canStructuresInteract(Object s1, Object s2) {
            return (s1 instanceof WizardTower && s2 instanceof MysticLibrary) ||
                (s1 instanceof DragonLair && s2 instanceof EnchantedCastle);
        }

        public static String performMagicBattle(Object attacker, Object defender) {
            if (attacker instanceof DragonLair && defender instanceof WizardTower) {
                return "Dragon burns the tower!";
            } else if (attacker instanceof WizardTower && defender instanceof DragonLair) {
                return "Wizard deflects with spells!";
            }
            return "No battle occurred.";
        }

        public static int calculateKingdomPower(Object[] structures) {
            int total = 0;
            for (Object s : structures) {
                if (s instanceof WizardTower) total += 300;
                else if (s instanceof EnchantedCastle) total += 200;
                else if (s instanceof MysticLibrary) total += 150;
                else if (s instanceof DragonLair) total += 500;
            }
            return total;
        }

        private String determineStructureCategory(Object s) {
            if (s instanceof WizardTower) return "Wizard Tower";
            if (s instanceof EnchantedCastle) return "Castle";
            if (s instanceof MysticLibrary) return "Library";
            if (s instanceof DragonLair) return "Dragon Lair";
            return "Unknown";
        }
    }

    // ---------- Demo Main ----------
    public class MedivalKingdomDemo {
        public static void main(String[] args) {
            KingdomConfig config = KingdomConfig.createDefaultKingdom();
            KingdomManager manager = new KingdomManager(config);

            WizardTower tower = new WizardTower("Arcana", "North Hill", 30, "Merlin");
            EnchantedCastle castle = new EnchantedCastle("Stormhold", "Valley", true);
            MysticLibrary library = new MysticLibrary("Ancient Archives", "City Center", 500);
            DragonLair lair = new DragonLair("Smaug's Rest", "Mountain", "Fire", 100);

            manager.addStructure(tower);
            manager.addStructure(castle);
            manager.addStructure(library);
            manager.addStructure(lair);

            System.out.println(tower);
            System.out.println(castle);
            System.out.println(library);
            System.out.println(lair);

            System.out.println("Can interact? " +
                    KingdomManager.canStructuresInteract(tower, library));
            System.out.println("Battle: " +
                    KingdomManager.performMagicBattle(lair, tower));
            System.out.println("Total Kingdom Power: " +
                    KingdomManager.calculateKingdomPower(new Object[]{tower, castle, library, lair}));
        }
    }

