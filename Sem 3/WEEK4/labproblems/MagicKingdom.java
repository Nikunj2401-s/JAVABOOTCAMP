package WEEK4.labproblems;

import java.util.*;
import java.util.stream.Collectors;

/* =========================
   Abstract Base + Chaining
   ========================= */
abstract class MagicalStructure {
    protected String structureName;
    protected int magicPower;
    protected String location;
    protected boolean isActive;

    // Master constructor
    protected MagicalStructure(String structureName, int magicPower, String location, boolean isActive) {
        this.structureName = (structureName == null || structureName.isBlank()) ? "Unnamed Structure" : structureName;
        this.magicPower    = Math.max(0, magicPower);
        this.location      = (location == null || location.isBlank()) ? "Unknown Realm" : location;
        this.isActive      = isActive;
    }

    // Overloads chaining to master
    protected MagicalStructure(String structureName, int magicPower, String location) {
        this(structureName, magicPower, location, true);
    }

    protected MagicalStructure(String structureName, int magicPower) {
        this(structureName, magicPower, "Unknown Realm", true);
    }

    protected MagicalStructure(String structureName) {
        this(structureName, 10, "Unknown Realm", true);
    }

    public abstract String castMagicSpell();

    public String getStructureName() { return this.structureName; }
    public int getMagicPower() { return this.magicPower; }
    public String getLocation() { return this.location; }
    public boolean isActive() { return this.isActive; }

    @Override public String toString() {
        return "%s@%s (power=%d, active=%s)".formatted(this.structureName, this.location, this.magicPower, this.isActive);
    }
}

/* =========================
   Derived Structures
   ========================= */
// WizardTower: spell capacity + known spells; multiple constructor patterns
class WizardTower extends MagicalStructure {
    private int spellCapacity;
    private final List<String> knownSpells;

    // Fully equipped (master)
    public WizardTower(String name, int magicPower, String location, boolean isActive,
                       int spellCapacity, List<String> spells) {
        super(name, magicPower, location, isActive);
        this.spellCapacity = Math.max(0, spellCapacity);
        this.knownSpells   = new ArrayList<>();
        if (spells != null) {
            for (String s : spells) if (s != null && !s.isBlank()) this.knownSpells.add(s.trim());
        }
    }

    // Built empty
    public WizardTower(String name) {
        this(name, 40, "Sky Ridge", true, 2, List.of());
    }

    // With basic spells
    public WizardTower(String name, String... basicSpells) {
        this(name, 55, "Sky Ridge", true, Math.max(3, basicSpells == null ? 0 : basicSpells.length),
             basicSpells == null ? List.of() : Arrays.asList(basicSpells));
    }

    // Fully equipped, variant (no location)
    public WizardTower(String name, int magicPower, int spellCapacity, List<String> spells) {
        this(name, magicPower, "Sky Ridge", true, spellCapacity, spells);
    }

    public int getSpellCapacity() { return this.spellCapacity; }
    public List<String> getKnownSpells() { return Collections.unmodifiableList(this.knownSpells); }

    public void learnSpell(String spell) {
        if (spell == null || spell.isBlank()) return;
        if (this.knownSpells.size() < this.spellCapacity) this.knownSpells.add(spell.trim());
    }
    public void setSpellCapacity(int cap) { this.spellCapacity = Math.max(0, cap); }

    @Override public String castMagicSpell() {
        String spell = knownSpells.isEmpty() ? "Arcane Bolt" : knownSpells.get(new Random().nextInt(knownSpells.size()));
        return "%s unleashes %s!".formatted(this.structureName, spell);
    }

    @Override public String toString() {
        return "WizardTower{%s, cap=%d, spells=%s}".formatted(super.toString(), this.spellCapacity, this.knownSpells);
    }
}

// EnchantedCastle: defense + drawbridge
class EnchantedCastle extends MagicalStructure {
    private int defenseRating;
    private boolean hasDrawbridge;

    // Master
    public EnchantedCastle(String name, int magicPower, String location, boolean isActive,
                           int defenseRating, boolean hasDrawbridge) {
        super(name, magicPower, location, isActive);
        this.defenseRating = Math.max(0, defenseRating);
        this.hasDrawbridge = hasDrawbridge;
    }

    // Simple fort
    public EnchantedCastle(String name) {
        this(name, 20, "Stone Vale", true, 35, true);
    }

    // Royal castle
    public EnchantedCastle(String name, boolean royal) {
        this(name, royal ? 50 : 30, "Stone Vale", true, royal ? 80 : 50, true);
    }

    // Impregnable fortress
    public static EnchantedCastle fortress(String name, String location) {
        return new EnchantedCastle(name, 70, location, true, 120, true);
    }

    public int getDefenseRating() { return this.defenseRating; }
    public boolean hasDrawbridge() { return this.hasDrawbridge; }
    public void reinforce(int amount) { this.defenseRating = Math.max(0, this.defenseRating + amount); }

    @Override public String castMagicSpell() {
        return "%s radiates a protective Aegis!".formatted(this.structureName);
    }

    @Override public String toString() {
        return "EnchantedCastle{%s, defense=%d, drawbridge=%s}".formatted(super.toString(), this.defenseRating, this.hasDrawbridge);
    }
}

// MysticLibrary: books + ancient language
class MysticLibrary extends MagicalStructure {
    private int bookCount;
    private String ancientLanguage;

    // Master
    public MysticLibrary(String name, int magicPower, String location, boolean isActive,
                         int bookCount, String ancientLanguage) {
        super(name, magicPower, location, isActive);
        this.bookCount = Math.max(0, bookCount);
        this.ancientLanguage = (ancientLanguage == null || ancientLanguage.isBlank()) ? "Elder Tongue" : ancientLanguage.trim();
    }

    // Few books
    public MysticLibrary(String name) {
        this(name, 25, "Whisper Hollow", true, 200, "Elder Tongue");
    }

    // Ancient collection
    public static MysticLibrary ancient(String name, int books, String language) {
        return new MysticLibrary(name, 60, "Whisper Hollow", true, Math.max(books, 1000), language);
    }

    public int getBookCount() { return this.bookCount; }
    public String getAncientLanguage() { return this.ancientLanguage; }
    public void addBooks(int n) { this.bookCount = Math.max(0, this.bookCount + n); }

    @Override public String castMagicSpell() {
        return "%s whispers %s incantations from %d tomes.".formatted(this.structureName, this.ancientLanguage, this.bookCount);
    }

    @Override public String toString() {
        return "MysticLibrary{%s, books=%d, lang=%s}".formatted(super.toString(), this.bookCount, this.ancientLanguage);
    }
}

// DragonLair: dragon type + treasure
class DragonLair extends MagicalStructure {
    private String dragonType;
    private int treasureValue;

    // Master
    public DragonLair(String name, int magicPower, String location, boolean isActive,
                      String dragonType, int treasureValue) {
        super(name, magicPower, location, isActive);
        this.dragonType = (dragonType == null || dragonType.isBlank()) ? "Fire Drake" : dragonType.trim();
        this.treasureValue = Math.max(0, treasureValue);
    }

    // Default lair by type
    public DragonLair(String name, String dragonType) {
        this(name,
             switch (dragonType == null ? "" : dragonType.toLowerCase()) {
                 case "ice wyrm" -> 65;
                 case "storm serpent" -> 75;
                 default -> 60;
             },
             "Ashen Peaks",
             true,
             dragonType,
             switch (dragonType == null ? "" : dragonType.toLowerCase()) {
                 case "ice wyrm" -> 800;
                 case "storm serpent" -> 1200;
                 default -> 1000;
             });
    }

    // Wealth-focused lair
    public static DragonLair hoard(String name, int treasureValue) {
        return new DragonLair(name, 70, "Ashen Peaks", true, "Fire Drake", treasureValue);
    }

    public String getDragonType() { return this.dragonType; }
    public int getTreasureValue() { return this.treasureValue; }

    @Override public String castMagicSpell() {
        return "%s breathes %s fury! (treasure=%d)".formatted(this.structureName, this.dragonType, this.treasureValue);
    }

    @Override public String toString() {
        return "DragonLair{%s, dragon=%s, treasure=%d}".formatted(super.toString(), this.dragonType, this.treasureValue);
    }
}

/* =========================
   Kingdom Rules & Twists
   ========================= */
class KingdomRules {
    // WizardTower + Library = Knowledge boost (double spell capacity)
    public static void applyKnowledgeBoost(List<MagicalStructure> structures) {
        List<WizardTower> towers = structures.stream().filter(s -> s instanceof WizardTower).map(s -> (WizardTower)s).toList();
        boolean hasLibrary = structures.stream().anyMatch(s -> s instanceof MysticLibrary);
        if (hasLibrary) {
            for (WizardTower t : towers) {
                t.setSpellCapacity(t.getSpellCapacity() * 2);
            }
        }
    }

    // Castle + DragonLair = Dragon guard (triple defense)
    public static void applyDragonGuard(List<MagicalStructure> structures) {
        boolean hasLair = structures.stream().anyMatch(s -> s instanceof DragonLair);
        for (MagicalStructure s : structures) {
            if (s instanceof EnchantedCastle c && hasLair) {
                c.reinforce(c.getDefenseRating() * 2); // doubles added to original => ~triple total
            }
        }
    }

    // Multiple towers = Magic network (shared spell pool)
    public static void establishMagicNetwork(List<MagicalStructure> structures) {
        List<WizardTower> towers = structures.stream().filter(s -> s instanceof WizardTower).map(s -> (WizardTower)s).toList();
        if (towers.size() <= 1) return;
        // Build a shared pool of unique spells across towers
        Set<String> pool = new LinkedHashSet<>();
        for (WizardTower t : towers) pool.addAll(t.getKnownSpells());
        // Distribute pool up to each tower's capacity
        for (WizardTower t : towers) {
            int cap = t.getSpellCapacity();
            int i = 0;
            for (String sp : pool) {
                if (t.getKnownSpells().contains(sp)) continue;
                if (i >= cap) break;
                t.learnSpell(sp);
                i++;
            }
        }
    }

    public static boolean canStructuresInteract(MagicalStructure s1, MagicalStructure s2) {
        if (s1 == null || s2 == null || !s1.isActive() || !s2.isActive()) return false;
        // Simple rules: Towers can interact with anything; Libraries pair with Towers/Castles; Lairs with Castles/Libraries
        if (s1 instanceof WizardTower || s2 instanceof WizardTower) return true;
        if ((s1 instanceof MysticLibrary && (s2 instanceof WizardTower || s2 instanceof EnchantedCastle)) ||
            (s2 instanceof MysticLibrary && (s1 instanceof WizardTower || s1 instanceof EnchantedCastle))) return true;
        if ((s1 instanceof DragonLair && (s2 instanceof EnchantedCastle || s2 instanceof MysticLibrary)) ||
            (s2 instanceof DragonLair && (s1 instanceof EnchantedCastle || s1 instanceof MysticLibrary))) return true;
        return s1.getClass() == s2.getClass(); // same types can always interact
    }

    // Very simple battle scoring with twists
    public static String performMagicBattle(MagicalStructure attacker, MagicalStructure defender) {
        if (!canStructuresInteract(attacker, defender)) {
            return "The structures cannot interact—no battle occurs.";
        }
        int atk = baseOffense(attacker);
        int def = baseDefense(defender);

        // Twists: knowledge boost (Tower+Library nearby) and dragon guard already modify stats externally

        int score = atk - def + new Random().nextInt(11) - 5; // ±5 randomness

        String a = attacker.getClass().getSimpleName() + " \"" + attacker.getStructureName() + "\"";
        String d = defender.getClass().getSimpleName() + " \"" + defender.getStructureName() + "\"";

        if (score > 0) return "%s overwhelms %s!".formatted(a, d);
        if (score < 0) return "%s holds against %s.".formatted(d, a);
        return "%s and %s clash evenly; stalemate.".formatted(a, d);
    }

    private static int baseOffense(MagicalStructure s) {
        int power = s.getMagicPower();
        if (s instanceof WizardTower t) {
            power += Math.min(t.getSpellCapacity(), 20);
            power += Math.min(t.getKnownSpells().size(), 10);
        } else if (s instanceof DragonLair l) {
            power += Math.min(l.getTreasureValue() / 50, 25);
        } else if (s instanceof MysticLibrary lib) {
            power += Math.min(lib.getBookCount() / 200, 15);
        } else if (s instanceof EnchantedCastle c) {
            power += Math.min(c.getDefenseRating() / 10, 10);
        }
        return power;
    }

    private static int baseDefense(MagicalStructure s) {
        int power = s.getMagicPower();
        if (s instanceof EnchantedCastle c) {
            power += c.getDefenseRating();
        } else if (s instanceof DragonLair l) {
            power += Math.min(l.getTreasureValue() / 20, 60);
        } else if (s instanceof MysticLibrary lib) {
            power += Math.min(lib.getBookCount() / 50, 40);
        } else if (s instanceof WizardTower t) {
            power += Math.min(t.getSpellCapacity() * 2, 30);
        }
        return power;
    }

    public static int calculateKingdomMagicPower(MagicalStructure[] structures) {
        if (structures == null) return 0;
        int sum = 0;
        for (MagicalStructure s : structures) if (s != null && s.isActive()) sum += s.getMagicPower();
        return sum;
    }
}

/* =========================
   Kingdom Manager
   ========================= */
class KingdomManager {
    private final List<MagicalStructure> structures = new ArrayList<>();

    public void add(MagicalStructure s) { if (s != null) structures.add(s); }

    public Map<Class<?>, List<MagicalStructure>> categorize() {
        Map<Class<?>, List<MagicalStructure>> map = new LinkedHashMap<>();
        map.put(WizardTower.class, new ArrayList<>());
        map.put(EnchantedCastle.class, new ArrayList<>());
        map.put(MysticLibrary.class, new ArrayList<>());
        map.put(DragonLair.class, new ArrayList<>());
        for (MagicalStructure s : structures) {
            if      (s instanceof WizardTower)     map.get(WizardTower.class).add(s);
            else if (s instanceof EnchantedCastle) map.get(EnchantedCastle.class).add(s);
            else if (s instanceof MysticLibrary)   map.get(MysticLibrary.class).add(s);
            else if (s instanceof DragonLair)      map.get(DragonLair.class).add(s);
        }
        return map;
    }

    // Simple tax policy per type (per year)
    public double computeTaxes() {
        double total = 0;
        for (MagicalStructure s : structures) {
            if (s instanceof WizardTower) {
                total += 100 + s.getMagicPower() * 1.2;
            } else if (s instanceof EnchantedCastle c) {
                total += 200 + c.getDefenseRating() * 0.8;
            } else if (s instanceof MysticLibrary lib) {
                total += 150 + (lib.getBookCount() * 0.05);
            } else if (s instanceof DragonLair l) {
                total += 300 + (l.getTreasureValue() * 0.02);
            }
        }
        return Math.round(total * 100.0) / 100.0;
    }

    public String specialization() {
        int magicFocus = 0, defenseFocus = 0, knowledgeFocus = 0, wealthFocus = 0;
        for (MagicalStructure s : structures) {
            if (s instanceof WizardTower) magicFocus += 2;
            if (s instanceof EnchantedCastle) defenseFocus += 3;
            if (s instanceof MysticLibrary) knowledgeFocus += 3;
            if (s instanceof DragonLair) wealthFocus += 3;
        }
        int max = Math.max(Math.max(magicFocus, defenseFocus), Math.max(knowledgeFocus, wealthFocus));
        if (max == 0) return "Unfocused";
        if (max == magicFocus) return "Magic-focused";
        if (max == defenseFocus) return "Defense-focused";
        if (max == knowledgeFocus) return "Knowledge-focused";
        return "Wealth-focused";
    }

    public List<MagicalStructure> list() { return Collections.unmodifiableList(structures); }

    public void applyGlobalTwists() {
        KingdomRules.applyKnowledgeBoost(structures);
        KingdomRules.applyDragonGuard(structures);
        KingdomRules.establishMagicNetwork(structures);
    }

    public String report() {
        Map<Class<?>, List<MagicalStructure>> cat = categorize();
        StringBuilder sb = new StringBuilder("\n=== Kingdom Report ===\n");
        for (var e : cat.entrySet()) {
            String key = e.getKey().getSimpleName();
            String names = e.getValue().stream().map(MagicalStructure::getStructureName).collect(Collectors.joining(", "));
            sb.append("%-16s: %d  [%s]\n".formatted(key, e.getValue().size(), names));
        }
        sb.append("Specialization     : ").append(specialization()).append("\n");
        sb.append("Total Base Power   : ").append(KingdomRules.calculateKingdomMagicPower(structures.toArray(new MagicalStructure[0]))).append("\n");
        sb.append("Yearly Taxes (est.): ").append(computeTaxes()).append("\n");
        return sb.toString();
    }
}

/* =========================
   Demo / Main
   ========================= */
public class MagicKingdom {
    public static void main(String[] args) {
        // Build structures via different constructor patterns
        WizardTower towerEmpty     = new WizardTower("Azure Spire"); // empty tower
        WizardTower towerBasic     = new WizardTower("Crimson Pinnacle", "Fireball", "Shield", "Blink"); // with basic spells
        WizardTower towerEquipped  = new WizardTower("Obsidian Needle", 70, 6, List.of("Chain Lightning", "Meteor", "Ward"));

        EnchantedCastle fort       = new EnchantedCastle("Stoneward");            // simple fort
        EnchantedCastle royal      = new EnchantedCastle("Highkeep", true);       // royal castle
        EnchantedCastle fortress   = EnchantedCastle.fortress("Iron Bastion", "Granite Plateau"); // impregnable

        MysticLibrary smallLib     = new MysticLibrary("Whisper Archive");        // few books
        MysticLibrary ancientLib   = MysticLibrary.ancient("Arcanum Athenaeum", 3000, "Draconic"); // ancient collection

        DragonLair fireLair        = new DragonLair("Cinder Vault", "Fire Drake"); // type-based
        DragonLair stormLair       = new DragonLair("Tempest Hollow", "Storm Serpent");
        DragonLair richLair        = DragonLair.hoard("Gilded Grotto", 5000);     // wealth lair

        // Teach a couple more spells (to see network effect)
        towerEmpty.learnSpell("Gust");
        towerBasic.learnSpell("Frost Nova");

        KingdomManager km = new KingdomManager();
        for (MagicalStructure s : List.of(
                towerEmpty, towerBasic, towerEquipped,
                fort, royal, fortress,
                smallLib, ancientLib,
                fireLair, stormLair, richLair
        )) km.add(s);

        // Apply global twists (knowledge boost, dragon guard, magic network)
        km.applyGlobalTwists();

        // Kingdom report
        System.out.println(km.report());

        // Sample interactions & battles
        System.out.println(towerBasic.castMagicSpell());
        System.out.println(ancientLib.castMagicSpell());
        System.out.println(fortress.castMagicSpell());
        System.out.println(stormLair.castMagicSpell());

        System.out.println("\n=== Interactions & Battles ===");
        System.out.println("Can Tower & Library interact? " +
                KingdomRules.canStructuresInteract(towerEquipped, ancientLib));
        System.out.println("Can Castle & Lair interact? " +
                KingdomRules.canStructuresInteract(royal, fireLair));
        System.out.println("Battle: " + KingdomRules.performMagicBattle(towerEquipped, fortress));
        System.out.println("Battle: " + KingdomRules.performMagicBattle(stormLair, royal));
        System.out.println("Battle: " + KingdomRules.performMagicBattle(ancientLib, richLair));
    }
}
